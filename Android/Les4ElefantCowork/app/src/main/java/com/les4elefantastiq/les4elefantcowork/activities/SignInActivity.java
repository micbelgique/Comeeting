package com.les4elefantastiq.les4elefantcowork.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.activities.utils.BaseActivity;
import com.les4elefantastiq.les4elefantcowork.dataaccess.CoworkerDataAccess;
import com.les4elefantastiq.les4elefantcowork.dataaccess.CoworkspaceDataAccess;
import com.les4elefantastiq.les4elefantcowork.dataaccess.LivefeedDataAccess;
import com.les4elefantastiq.les4elefantcowork.managers.CoworkerManager;
import com.les4elefantastiq.les4elefantcowork.managers.ProfileManager;
import com.les4elefantastiq.les4elefantcowork.models.Coworker;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;
import com.les4elefantastiq.les4elefantcowork.models.linkedinmodels.LinkedInCoworker;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

import java.util.List;

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


        // Store a reference to the current activity
        final Activity thisActivity = this;

        LISessionManager.getInstance(getApplicationContext()).init(thisActivity, buildScope(), new AuthListener() {
            @Override
            public void onAuthSuccess() {
                // Authentication was successful.

                // Get profile data
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url = "https://api.linkedin.com/v1/people/~:(first-name,last-name,id,picture-urls::(original),positions,summary,headline)?format=json";

                        APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
                        apiHelper.getRequest(thisActivity, url, new ApiListener() {
                            @Override
                            public void onApiSuccess(final ApiResponse apiResponse) {
                                // Login coworker
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        LinkedInCoworker linkedInCoworker = new Gson().fromJson(apiResponse.getResponseDataAsString(), LinkedInCoworker.class);
                                        CoworkerManager.login(linkedInCoworker.getCoworker());
                                    }
                                }).start();
                            }

                            @Override
                            public void onApiError(LIApiError liApiError) {
                                Log.d("Blop", "onApiError"); // Probablement une erreur de connection
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onAuthError(LIAuthError error) {
                Log.d("Blop", "onAuthError"); // Probablement une erreur de connection
            }
        }, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Add this line to your existing onActivityResult() method
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
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

    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }

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