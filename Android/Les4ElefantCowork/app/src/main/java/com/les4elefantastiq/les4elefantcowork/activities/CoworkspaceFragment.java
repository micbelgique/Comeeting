package com.les4elefantastiq.les4elefantcowork.activities;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.activities.utils.BaseActivity;
import com.les4elefantastiq.les4elefantcowork.managers.LivefeedManager;
import com.les4elefantastiq.les4elefantcowork.managers.ProfileManager;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;
import com.les4elefantastiq.les4elefantcowork.models.LiveFeedMessage;

import java.util.List;

public class CoworkspaceFragment extends Fragment {

    // -------------- Objects, Variables -------------- //

    private LiveFeedMessagesAsyncTask mLiveFeedmessagesAsyncTaks;

    private Coworkspace mCoworkspace;


    // -------------------- Views --------------------- //

    private ListView mListView;
    private ProgressDialog mProgressDialog;


    // ------------------ LifeCycle ------------------- //

    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.live_feed_fragment, container, false);

        mCoworkspace = ProfileManager.getCurrentCowerkspace();

        ((BaseActivity) getActivity()).getSupportActionBar().setTitle(mCoworkspace.name);

        mListView = (ListView) view.findViewById(R.id.listview);

        mLiveFeedmessagesAsyncTaks = new LiveFeedMessagesAsyncTask();
        mLiveFeedmessagesAsyncTaks.execute();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mLiveFeedmessagesAsyncTaks != null)
            mLiveFeedmessagesAsyncTaks.cancel(false);
    }


    // ------------------ Listeners ------------------- //

    // ------------------- Methods -------------------- //

    // ------------------ AsyncTasks ------------------ //

    private class LiveFeedMessagesAsyncTask extends AsyncTask<Void, Void, List<LiveFeedMessage>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(getActivity(), null, "Please wait", true, false);
        }

        @Override
        protected List<LiveFeedMessage> doInBackground(Void... voids) {
            return LivefeedManager.getLiveFeedMessages(mCoworkspace);
        }

        @Override
        protected void onPostExecute(List<LiveFeedMessage> liveFeedMessages) {
            super.onPostExecute(liveFeedMessages);

            mProgressDialog.dismiss();

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

            // TODO : set the cowerkers and clicklistener
            convertView.setOnClickListener(onViewMoreCowerkerClickListener);

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

        private View.OnClickListener onViewMoreCowerkerClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CoworkersActivity.class));
            }

        };

    }


    // --------------------- Menu --------------------- //

}