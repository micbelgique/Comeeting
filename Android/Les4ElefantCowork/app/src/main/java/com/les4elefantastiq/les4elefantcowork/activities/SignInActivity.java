package com.les4elefantastiq.les4elefantcowork.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.activities.utils.BaseActivity;
import com.les4elefantastiq.les4elefantcowork.managers.ProfileManager;

public class SignInActivity extends BaseActivity implements View.OnClickListener {

    // -------------- Objects, Variables -------------- //

    private LinkedInAsyncTask mLinkedInAsyncTask;


    // -------------------- Views --------------------- //

    // ------------------ LifeCycle ------------------- //

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);

        // Set status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        findViewById(R.id.button_linked_in).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mLinkedInAsyncTask != null)
            mLinkedInAsyncTask.cancel(false);
    }


    // ------------------ Listeners ------------------- //

    @Override
    public void onClick(View view) {
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