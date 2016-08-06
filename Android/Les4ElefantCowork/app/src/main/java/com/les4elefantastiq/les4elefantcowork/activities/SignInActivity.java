package com.les4elefantastiq.les4elefantcowork.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.activities.utils.BaseActivity;
import com.les4elefantastiq.les4elefantcowork.managers.CoworkerManager;
import com.les4elefantastiq.les4elefantcowork.managers.ProfileManager;
import com.les4elefantastiq.les4elefantcowork.models.linkedinmodels.LinkedInCoworker;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

public class SignInActivity extends BaseActivity implements View.OnClickListener, ApiListener, AuthListener {

    // -------------- Objects, Variables -------------- //

    private ProgressDialog progressDialog;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Add this line to your existing onActivityResult() method
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    // ------------------ Listeners ------------------- //

    @Override
    public void onClick(View view) {
        progressDialog = ProgressDialog.show(SignInActivity.this, null, "Please wait ...", true, false);
        ProfileManager.signWithLinkedIn(SignInActivity.this);
    }

    @Override
    public void onApiSuccess(ApiResponse apiResponse) {
        // Login coworker
        new LinkedInAsyncTask().execute(apiResponse);
    }

    @Override
    public void onApiError(LIApiError LIApiError) {
        Log.d("Blop", "onApiError"); // Probablement une erreur de connection
    }

    @Override
    public void onAuthSuccess() {
        // Authentication was successful.

        // Get profile data
        String url = "https://api.linkedin.com/v1/people/~:(first-name,last-name,id,picture-urls::(original),positions,summary,headline)?format=json";

        APIHelper apiHelper = APIHelper.getInstance(this);
        apiHelper.getRequest(getApplicationContext(), url, this);
    }

    @Override
    public void onAuthError(LIAuthError error) {
        Log.d("Blop", "onAuthError"); // Probablement une erreur de connection
    }

    // ------------------- Methods -------------------- //

    public static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }

    // ------------------ AsyncTasks ------------------ //
    // ------------------ AsyncTasks ------------------ //

    private class LinkedInAsyncTask extends AsyncTask<ApiResponse, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(ApiResponse... apiResponses) {
            LinkedInCoworker linkedInCoworker = new Gson().fromJson(apiResponses[0].getResponseDataAsString(), LinkedInCoworker.class);
            ProfileManager.linkedInId = linkedInCoworker.linkedInId;
            Boolean success = CoworkerManager.login(linkedInCoworker.getCoworker());

            return success;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            progressDialog.dismiss();

            if (success) {
                startActivity(new Intent(SignInActivity.this, NavigationActivity.class));
                finish();
            }
        }
    }
}