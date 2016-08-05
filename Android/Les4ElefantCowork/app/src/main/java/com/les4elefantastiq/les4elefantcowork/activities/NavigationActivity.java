package com.les4elefantastiq.les4elefantcowork.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.managers.ProfileManager;

public class NavigationActivity extends BaseActivity {

    // -------------- Objects, Variables -------------- //

    // -------------------- Views --------------------- //

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;


    // ------------------ LifeCycle ------------------- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        if(ProfileManager.isLogged()) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            manageToolbar(toolbar);
            manageNavigationDrawer(toolbar);
        }
        else{
            startActivity(new Intent(NavigationActivity.this, SignInActivity.class));
            finish();
        }
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
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(NavigationActivity.this, mDrawerLayout, toolbar, R.string.Open, R.string.Close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mNavigationView = ((NavigationView) findViewById(R.id.navigationView));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


    // ------------------ AsyncTasks ------------------ //

    // ----------------- GUI Adapter ------------------ //

    // --------------------- Menu --------------------- //

}