package com.les4elefantastiq.les4elefantcowork.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.activities.utils.BaseActivity;

public class CoworkspaceActivity extends BaseActivity {

    // -------------- Objects, Variables -------------- //

    // -------------------- Views --------------------- //

    // ------------------ LifeCycle ------------------- //

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coworkspace_activity);

        manageToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    // ------------------ Listeners ------------------- //

    // ------------------- Methods -------------------- //

    // ------------------ AsyncTasks ------------------ //

    // ----------------- GUI Adapter ------------------ //

    // --------------------- Menu --------------------- //

}