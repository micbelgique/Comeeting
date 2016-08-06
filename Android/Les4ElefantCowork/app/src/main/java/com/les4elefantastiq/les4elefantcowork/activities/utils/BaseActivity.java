package com.les4elefantastiq.les4elefantcowork.activities.utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.les4elefantastiq.les4elefantcowork.R;

public class BaseActivity extends AppCompatActivity {

    // -------------- Objects, Variables -------------- //

    // -------------------- Views --------------------- //

    // ------------------ LifeCycle ------------------- //

    // ------------------ Listeners ------------------- //

    // ------------------- Methods -------------------- //

    protected void manageToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    // ------------------ AsyncTasks ------------------ //

    // ----------------- GUI Adapter ------------------ //

    // --------------------- Menu --------------------- //


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

}