package com.les4elefantastiq.les4elefantcowork.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.dataaccess.CoworkspaceDataAccess;

import java.io.IOException;

public class NavigationActivity extends BaseActivity {

    // -------------- Objects, Variables -------------- //

    // -------------------- Views --------------------- //

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;


    // ------------------ LifeCycle ------------------- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        manageToolbar(toolbar);
        manageNavigationDrawer(toolbar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CoworkspaceDataAccess.doSomeStuff();
                } catch (IOException e) {
                    Log.d("Blop", "EXPLOSION ATOMIQUE\n" + e.getMessage());
                }
            }
        }).start();
    }


    // ------------------ Listeners ------------------- //

    // ------------------- Methods -------------------- //

    @SuppressWarnings("ConstantConditions")
    private void manageToolbar(Toolbar toolbar) {
        // Manage Toolbar/ActionBar
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    private void manageNavigationDrawer(@NonNull Toolbar toolbar) {
        // Manage DrawerLayout and his toggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(NavigationActivity.this, drawerLayout, toolbar, R.string.Open, R.string.Close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        drawerLayout.addDrawerListener(drawerToggle);

        navigationView = ((NavigationView) findViewById(R.id.navigationView));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }


    // ------------------ AsyncTasks ------------------ //

    // ----------------- GUI Adapter ------------------ //

    // --------------------- Menu --------------------- //

}