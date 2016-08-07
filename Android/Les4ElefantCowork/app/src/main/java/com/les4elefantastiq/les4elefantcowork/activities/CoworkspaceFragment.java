package com.les4elefantastiq.les4elefantcowork.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.les4elefantastiq.les4elefantcowork.models.Coworker;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;
import com.les4elefantastiq.les4elefantcowork.models.LiveFeedMessage;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoworkspaceFragment extends Fragment {

    // -------------- Objects, Variables -------------- //

    public static final String EXTRA_COWORKSPACE_ID = "EXTRA_COWORKSPACE_ID";

    private LiveFeedMessagesAsyncTask mLiveFeedmessagesAsyncTaks;

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

        setHasOptionsMenu(true);

        mListView = (ListView) view.findViewById(R.id.listview);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

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

            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<LiveFeedMessage> doInBackground(Void... voids) {
            mCoworkspace = CoworkspacesManager.getCoworkspace(getArguments().getString(EXTRA_COWORKSPACE_ID));

            if (mCoworkspace != null)
                return LivefeedManager.getLiveFeedMessages(mCoworkspace);
            else
                return null;
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        protected void onPostExecute(List<LiveFeedMessage> liveFeedMessages) {
            super.onPostExecute(liveFeedMessages);

            mProgressBar.setVisibility(View.GONE);

            ((BaseActivity) getActivity()).getSupportActionBar().setTitle(mCoworkspace.name);

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
            TextView textView_Subdescription, textView_Badge;
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
                convertView = getActivity().getLayoutInflater().inflate(R.layout.coworkspace_fragment_coworkers, parent, false);

            ArrayList<ImageView> imageViews = new ArrayList<>();
            imageViews.add((ImageView) convertView.findViewById(R.id.imageview_coworker_1));
            imageViews.add((ImageView) convertView.findViewById(R.id.imageview_coworker_2));
            imageViews.add((ImageView) convertView.findViewById(R.id.imageview_coworker_3));
            imageViews.add((ImageView) convertView.findViewById(R.id.imageview_coworker_4));

            for (int i = 0; i < 4; i++) {
                if (mCoworkspace.coworkers.size() > i) {
                    ImageView imageView = imageViews.get(i);
                    Coworker coworker = mCoworkspace.coworkers.get(i);

                    Picasso.with(getContext())
                            .load(coworker.pictureUrl)
                            .into(imageView);

                    imageView.setTag(coworker);

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Coworker coworker = (Coworker) view.getTag();
                            startActivity(new Intent(getActivity(), CoworkerActivity.class).putExtra(CoworkerActivity.EXTRA_COWORKER_ID, coworker.linkedInId));
                        }
                    });

                }
            }

            convertView.findViewById(R.id.textview_see_more).setOnClickListener(onSeeMoreCowerkerClickListener);

            return convertView;
        }

        private View getLiveFeedMessageView(LiveFeedMessage liveFeedMessage, View convertView, ViewGroup parent) {
            ObjectsHolder objectsHolder;

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.coworkspace_fragment_item, parent, false);
                objectsHolder = new ObjectsHolder();
                convertView.setTag(objectsHolder);

                objectsHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
                objectsHolder.textView_Name = (TextView) convertView.findViewById(R.id.textview_name);
                objectsHolder.textView_Description = (TextView) convertView.findViewById(R.id.textview_description);
                objectsHolder.textView_Subdescription = (TextView) convertView.findViewById(R.id.textview_subdescription);
                objectsHolder.textView_Date = (TextView) convertView.findViewById(R.id.textview_date);
                objectsHolder.textView_Badge = (TextView) convertView.findViewById(R.id.textview_badge);
            } else
                objectsHolder = (ObjectsHolder) convertView.getTag();

            objectsHolder.textView_Name.setText(liveFeedMessage.title);
            objectsHolder.textView_Description.setText(liveFeedMessage.text);
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(liveFeedMessage.dateTime);
                String dateString = DateUtils.getRelativeTimeSpanString(date.getTime()).toString();
                objectsHolder.textView_Date.setText(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
                objectsHolder.textView_Date.setText("-");
            }

            // Arrival of a coworker
            if (liveFeedMessage.type == LiveFeedMessage.TYPE_ARRIVAL) {
                objectsHolder.textView_Badge.setText("Arrivée");

                Picasso.with(getActivity())
                        .load(liveFeedMessage.pictureUrl)
                        .placeholder(R.drawable.user)
                        .into(objectsHolder.imageView);

                if (liveFeedMessage.isBirthday) {
                    objectsHolder.textView_Subdescription.setVisibility(View.VISIBLE);
                    objectsHolder.textView_Subdescription.setText("C'est son anniversaire !");
                } else
                    objectsHolder.textView_Subdescription.setVisibility(View.GONE);
            }

            // Admin message
            else if (liveFeedMessage.type == LiveFeedMessage.TYPE_COWORKSPACE_ADMIN) {
                objectsHolder.textView_Badge.setText("Info");

                objectsHolder.imageView.setImageResource(R.drawable.ic_live_feed_admin);

                objectsHolder.textView_Subdescription.setVisibility(View.GONE);
            }

            // Twitter message
            else if (liveFeedMessage.type == LiveFeedMessage.TYPE_TWITTER) {
                objectsHolder.textView_Badge.setText("Retweet");

                Picasso.with(getActivity())
                        .load(liveFeedMessage.pictureUrl)
                        .placeholder(R.drawable.ic_live_feed_tweeter)
                        .into(objectsHolder.imageView);

                objectsHolder.textView_Subdescription.setVisibility(View.GONE);

            } else if (liveFeedMessage.type == LiveFeedMessage.TYPE_COWORKSPACE_OPENING) {
                objectsHolder.textView_Badge.setText("Info");

                objectsHolder.imageView.setImageResource(R.drawable.ic_live_feed_clock);
                objectsHolder.textView_Subdescription.setVisibility(View.VISIBLE);
                objectsHolder.textView_Subdescription.setText("Fermeture du coworkspace");
            }

            return convertView;
        }

        private View.OnClickListener onSeeMoreCowerkerClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CoworkersActivity.class).putExtra(EXTRA_COWORKSPACE_ID, getArguments().getString(EXTRA_COWORKSPACE_ID)));
            }

        };

    }


    // --------------------- Menu --------------------- //

    private final int MENU_FAVORITE = 1;
    private final int MENU_INFORMATION = 2;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem menuItemInformation = menu.add(0, MENU_INFORMATION, 0, "Information");
        menuItemInformation.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuItemInformation.setIcon(R.drawable.ic_info_outline);

        MenuItem menuItemFavorite = menu.add(0, MENU_FAVORITE, 0, "Favoris");
        menuItemFavorite.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuItemFavorite.setIcon(R.drawable.ic_favorite);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_FAVORITE:
                break;

            case MENU_INFORMATION:
                Intent intent = new Intent(getContext(), CoworkspaceDetailsActivity.class);
                intent.putExtra(CoworkspaceDetailsActivity.EXTRA_COWORKSPACE_ID, mCoworkspace.id);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}