package com.les4elefantastiq.les4elefantcowork.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.activities.utils.BaseActivity;
import com.les4elefantastiq.les4elefantcowork.managers.CoworkspacesManager;
import com.les4elefantastiq.les4elefantcowork.managers.LivefeedManager;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;
import com.les4elefantastiq.les4elefantcowork.models.LiveFeedMessage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CoworkspaceFragment extends Fragment {

    // -------------- Objects, Variables -------------- //

    public static final String EXTRA_COWORKSPACE_ID = "EXTRA_COWORKSPACE_ID";

    private LiveFeedMessagesAsyncTask mLiveFeedmessagesAsyncTaks;
    private CoworkspaceAsyncTask mCoworkspaceAsyncTask;

    private Coworkspace mCoworkspace;


    // -------------------- Views --------------------- //

    private ListView mListView;
    private ProgressBar mProgressBar;


    // ------------------ LifeCycle ------------------- //

    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coworkspace_fragment, container, false);

        ((BaseActivity) getActivity()).getSupportActionBar().setTitle("Coworkspace");

        mListView = (ListView) view.findViewById(R.id.listview);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        mCoworkspaceAsyncTask = new CoworkspaceAsyncTask();
        mCoworkspaceAsyncTask.execute();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mCoworkspaceAsyncTask != null)
            mCoworkspaceAsyncTask.cancel(false);

        if (mLiveFeedmessagesAsyncTaks != null)
            mLiveFeedmessagesAsyncTaks.cancel(false);
    }


    // ------------------ Listeners ------------------- //

    // ------------------- Methods -------------------- //

    // ------------------ AsyncTasks ------------------ //

    private class CoworkspaceAsyncTask extends AsyncTask<Void, Void, Coworkspace> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Coworkspace doInBackground(Void... voids) {
            return CoworkspacesManager.getCoworkspace(getArguments().getString(EXTRA_COWORKSPACE_ID));
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        protected void onPostExecute(Coworkspace coworkspace) {
            super.onPostExecute(coworkspace);

            if (coworkspace != null) {
                mCoworkspace = coworkspace;

                ((BaseActivity) getActivity()).getSupportActionBar().setTitle(coworkspace.name);

                if (mLiveFeedmessagesAsyncTaks != null)
                    mLiveFeedmessagesAsyncTaks.cancel(false);

                mLiveFeedmessagesAsyncTaks = new LiveFeedMessagesAsyncTask();
                mLiveFeedmessagesAsyncTaks.execute();
            } else {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), R.string.Whoops_an_error_has_occured__Check_your_internet_connection, Toast.LENGTH_LONG).show();
            }
        }

    }

    private class LiveFeedMessagesAsyncTask extends AsyncTask<Void, Void, List<LiveFeedMessage>> {

        @Override
        protected List<LiveFeedMessage> doInBackground(Void... voids) {
            return LivefeedManager.getLiveFeedMessages(mCoworkspace);
        }

        @Override
        protected void onPostExecute(List<LiveFeedMessage> liveFeedMessages) {
            super.onPostExecute(liveFeedMessages);

            mProgressBar.setVisibility(View.GONE);

            if (liveFeedMessages != null)
                mListView.setAdapter(new Adapter(liveFeedMessages));
            else
                Toast.makeText(getActivity(), R.string.Whoops_an_error_has_occured__Check_your_internet_connection, Toast.LENGTH_LONG).show();
        }

    }


    // ----------------- GUI Adapter ------------------ //

    private class Adapter extends BaseAdapter {

        private List<LiveFeedMessage> liveFeedMessages;

        private class ObjectsHolder {
            ImageView imageView;
            TextView textView_Name, textView_Description, textView_Date;
        }

        public Adapter(List<LiveFeedMessage> liveFeedMessages) {
            this.liveFeedMessages = liveFeedMessages;
        }

        @Override
        public int getCount() {
            return liveFeedMessages.size() + 1;
        }

        @Override
        public LiveFeedMessage getItem(int position) {
            if (position == 0)
                return null;
            else
                return liveFeedMessages.get(position - 1);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return 0;
            else
                return 1;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LiveFeedMessage liveFeedMessage = getItem(position);

            if (position == 0)
                return getCoworkersView(convertView, parent);
            else
                return getLiveFeedMessageView(liveFeedMessage, convertView, parent);
        }

        private View getCoworkersView(View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = getActivity().getLayoutInflater().inflate(R.layout.live_feed_fragment_coworkers, parent, false);

            ArrayList<ImageView> imageViews = new ArrayList<>();
            imageViews.add((ImageView) convertView.findViewById(R.id.imageview_coworker_1));
            imageViews.add((ImageView) convertView.findViewById(R.id.imageview_coworker_2));
            imageViews.add((ImageView) convertView.findViewById(R.id.imageview_coworker_3));
            imageViews.add((ImageView) convertView.findViewById(R.id.imageview_coworker_4));

            for (int i = 0; i < 4; i++)
                if (mCoworkspace.coworkers.size() > i)
                    Picasso.with(getContext())
                            .load(mCoworkspace.coworkers.get(i).pictureUrl)
                            .into(imageViews.get(i));

            convertView.findViewById(R.id.textview_see_more).setOnClickListener(onSeeMoreCowerkerClickListener);

            return convertView;
        }

        private View getLiveFeedMessageView(LiveFeedMessage liveFeedMessage, View convertView, ViewGroup parent) {
            ObjectsHolder objectsHolder;

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.live_feed_fragment_item, parent, false);
                objectsHolder = new ObjectsHolder();
                convertView.setTag(objectsHolder);

                objectsHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
                objectsHolder.textView_Name = (TextView) convertView.findViewById(R.id.textview_name);
                objectsHolder.textView_Description = (TextView) convertView.findViewById(R.id.textview_description);
                objectsHolder.textView_Date = (TextView) convertView.findViewById(R.id.textview_date);
            } else
                objectsHolder = (ObjectsHolder) convertView.getTag();

            // TODO : set values to views

            objectsHolder.textView_Name.setText(liveFeedMessage.text);
            objectsHolder.textView_Date.setText(liveFeedMessage.dateTime);

            return convertView;
        }

        private View.OnClickListener onSeeMoreCowerkerClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CoworkersActivity.class));
            }

        };

    }


    // --------------------- Menu --------------------- //

}