package com.les4elefantastiq.les4elefantcowork.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.managers.CoworkspacesManager;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoworkspacesFragment extends Fragment {

    // -------------- Objects, Variables -------------- //

    private CoworkspacesAsyncTask mCoworkspacesAsyncTask;


    // -------------------- Views --------------------- //

    @BindView(R.id.listview)
    ListView listView;


    // ------------------ LifeCycle ------------------- //

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coworkspaces_fragment, container, false);

        ButterKnife.bind(this, view);

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

    private class CoworkspacesAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Coworkspace> coworkspaceList = CoworkspacesManager.getCoworkspaces();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            listView.setAdapter(new Adapter());
        }

    }


    // ----------------- GUI Adapter ------------------ //

    private class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }

    }


    // --------------------- Menu --------------------- //

}