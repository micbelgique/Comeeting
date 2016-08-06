package com.les4elefantastiq.les4elefantcowork.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Toast;

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.activities.utils.BaseActivity;
import com.les4elefantastiq.les4elefantcowork.managers.CoworkerManager;
import com.les4elefantastiq.les4elefantcowork.models.Coworker;
import com.squareup.picasso.Picasso;

public class CoworkerActivity extends BaseActivity {

    // -------------- Objects, Variables -------------- //

    public static final String EXTRA_COWORKER_ID = "EXTRA_COWORKER_ID";

    public CoworkerAsyncTask mCoworkerAsyncTask;


    // -------------------- Views --------------------- //

    private ImageView imageView;


    // ------------------ LifeCycle ------------------- //

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coworker_activity);

        manageToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Coworker");

        imageView = (ImageView) findViewById(R.id.imageview);

        mCoworkerAsyncTask = new CoworkerAsyncTask();
        mCoworkerAsyncTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mCoworkerAsyncTask != null)
            mCoworkerAsyncTask.cancel(false);
    }


    // ------------------ Listeners ------------------- //

    // ------------------- Methods -------------------- //

    // ------------------ AsyncTasks ------------------ //

    private class CoworkerAsyncTask extends AsyncTask<Void, Void, Coworker> {

        @Override
        protected Coworker doInBackground(Void... voids) {
            return CoworkerManager.getCoworker(getIntent().getStringExtra(EXTRA_COWORKER_ID));
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        protected void onPostExecute(Coworker coworker) {
            super.onPostExecute(coworker);

            if (coworker != null) {
                getSupportActionBar().setTitle(coworker.firstName + " " + coworker.lastName);

                Picasso.with(getBaseContext())
                        .load(coworker.pictureUrl)
                        .placeholder(R.drawable.user)
                        .into(imageView);

            } else
                Toast.makeText(CoworkerActivity.this, R.string.Whoops_an_error_has_occured__Check_your_internet_connection, Toast.LENGTH_LONG).show();
        }

    }


    // ----------------- GUI Adapter ------------------ //

    // --------------------- Menu --------------------- //

}