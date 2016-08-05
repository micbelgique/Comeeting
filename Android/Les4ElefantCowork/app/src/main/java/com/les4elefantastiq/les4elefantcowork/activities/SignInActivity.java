package com.les4elefantastiq.les4elefantcowork.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.activities.utils.BaseActivity;
import com.les4elefantastiq.les4elefantcowork.managers.ProfileManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends BaseActivity {

    // -------------- Objects, Variables -------------- //

    private LinkedInAsyncTask mLinkedInAsyncTask;


    // -------------------- Views --------------------- //

    // ------------------ LifeCycle ------------------- //

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
        manageToolbar();

        getSupportActionBar().setTitle("Sign in");

        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mLinkedInAsyncTask != null)
            mLinkedInAsyncTask.cancel(false);
    }


    // ------------------ Listeners ------------------- //

    @OnClick(R.id.button_linked_in)
    public void signInLinkedIn() {
        if (mLinkedInAsyncTask != null)
            mLinkedInAsyncTask.cancel(false);

        mLinkedInAsyncTask = new LinkedInAsyncTask();
        mLinkedInAsyncTask.execute();
    }


    // ------------------- Methods -------------------- //


    // ------------------ AsyncTasks ------------------ //

    private class LinkedInAsyncTask extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(SignInActivity.this, null, "Please wait ...", true, false);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ProfileManager.signWithLinkedIn();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            progressDialog.dismiss();

            // TODO : if (success)

            startActivity(new Intent(SignInActivity.this, NavigationActivity.class));
            finish();
        }

    }

}