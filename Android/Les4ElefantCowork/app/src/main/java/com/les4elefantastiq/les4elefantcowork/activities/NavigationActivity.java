package com.les4elefantastiq.les4elefantcowork.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.activities.utils.BaseActivity;
import com.les4elefantastiq.les4elefantcowork.dataaccess.CoworkspaceDataAccess;
import com.les4elefantastiq.les4elefantcowork.managers.ProfileManager;
import com.les4elefantastiq.les4elefantcowork.models.Coworker;

import java.util.List;

public class NavigationActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    // -------------- Objects, Variables -------------- //

    private Fragment mCurrentFragment;


    // -------------------- Views --------------------- //

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;


    // ------------------ LifeCycle ------------------- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        if (ProfileManager.isLogged()) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            manageToolbar(toolbar);
            manageNavigationDrawer(toolbar);

            // Open the choosen fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.layout_Content, new CoworkspacesFragment())
                    .commit();

        } else {
            startActivity(new Intent(NavigationActivity.this, SignInActivity.class));
            finish();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


    // ------------------ Listeners ------------------- //

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);

        getSupportActionBar().setTitle(menuItem.getTitle());

        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            // case R.id.:
            //    fragment = new xFragment();
            //    break;
        }

        showFragment(fragment);

        return true;
    }


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

    private void showFragment(Fragment fragment) {
        mCurrentFragment = fragment;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_Content, fragment)
                .commit();

        mDrawerLayout.closeDrawers();
    }

    
    // ------------------ AsyncTasks ------------------ //

    // ----------------- GUI Adapter ------------------ //

    // --------------------- Menu --------------------- //

}