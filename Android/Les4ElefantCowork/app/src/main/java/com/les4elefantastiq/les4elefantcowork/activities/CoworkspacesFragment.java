package com.les4elefantastiq.les4elefantcowork.activities;

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

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.managers.CoworkspacesManager;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoworkspacesFragment extends Fragment {

    // -------------- Objects, Variables -------------- //

    private CoworkspacesAsyncTask mCoworkspacesAsyncTask;


    // -------------------- Views --------------------- //

    private ListView listView;


    // ------------------ LifeCycle ------------------- //

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coworkspaces_fragment, container, false);

        listView = (ListView) view.findViewById(R.id.listview);

        mCoworkspacesAsyncTask = new CoworkspacesAsyncTask();
        mCoworkspacesAsyncTask.execute();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mCoworkspacesAsyncTask != null)
            mCoworkspacesAsyncTask.cancel(false);
    }


    // ------------------ Listeners ------------------- //

    // ------------------- Methods -------------------- //

    // ------------------ AsyncTasks ------------------ //

    private class CoworkspacesAsyncTask extends AsyncTask<Void, Void, List<Coworkspace>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Coworkspace> doInBackground(Void... voids) {
            return CoworkspacesManager.getCoworkspaces();
        }

        @Override
        protected void onPostExecute(List<Coworkspace> coworkspaces) {
            super.onPostExecute(coworkspaces);

            listView.setAdapter(new Adapter(coworkspaces));
        }

    }


    // ----------------- GUI Adapter ------------------ //

    private class Adapter extends BaseAdapter {

        private List<Coworkspace> coworkspaces;

        private class ObjectsHolder {
            Coworkspace coworkspace;
            ImageView imageView;
            TextView textView_Name, textView_Distance, textView_CowerkersCount;
        }

        public Adapter(List<Coworkspace> coworkspaces) {
            this.coworkspaces = coworkspaces;
        }

        @Override
        public int getCount() {
            return coworkspaces.size();
        }

        @Override
        public Coworkspace getItem(int position) {
            return coworkspaces.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Coworkspace coworkspace = getItem(position);
            ObjectsHolder objectsHolder;

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.coworspaces_fragment_item, parent, false);
                objectsHolder = new ObjectsHolder();
                objectsHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
                objectsHolder.textView_Name = (TextView) convertView.findViewById(R.id.textview_name);
                objectsHolder.textView_CowerkersCount = (TextView) convertView.findViewById(R.id.textview_coworkers_count);
                objectsHolder.textView_Distance = (TextView) convertView.findViewById(R.id.textview_distance);
                convertView.setTag(objectsHolder);

            } else
                objectsHolder = (ObjectsHolder) convertView.getTag();

            Picasso.with(getActivity())
                    .load(coworkspace.pictureUrl)
                    .into(objectsHolder.imageView);

            objectsHolder.textView_Name.setText(coworkspace.name);
            objectsHolder.textView_CowerkersCount.setText(coworkspace.coworkers.length + " coworkers actuellement");
            // objectsHolder.textView_Distance.setText(coworkspace.);

            convertView.setOnClickListener(onCoworkspaceClickListener);

            return convertView;
        }

        private View.OnClickListener onCoworkspaceClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Coworkspace coworkspace = ((ObjectsHolder) view.getTag()).coworkspace;
                // Intent intent = new Intent(getActivity(), .class)
            }

        };

    }


    // --------------------- Menu --------------------- //

}