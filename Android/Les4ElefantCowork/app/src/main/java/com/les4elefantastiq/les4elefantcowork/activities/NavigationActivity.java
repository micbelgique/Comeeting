package com.les4elefantastiq.les4elefantcowork.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import com.les4elefantastiq.les4elefantcowork.R;
import com.les4elefantastiq.les4elefantcowork.activities.utils.BaseActivity;
import com.les4elefantastiq.les4elefantcowork.managers.CoworkspacesManager;
import com.les4elefantastiq.les4elefantcowork.managers.ProfileManager;
import com.les4elefantastiq.les4elefantcowork.models.Coworkspace;

public class NavigationActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    // -------------- Objects, Variables -------------- //

    private CurrentCoworkspaceAsynctask mCurrentCoworkspaceAsynctask;


    // -------------------- Views --------------------- //

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;


    // ------------------ LifeCycle ------------------- //

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        if (ProfileManager.isLogged()) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);

            // Manage Toolbar/ActionBar
            setSupportActionBar(mToolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(R.string.app_name);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);

            manageNavigationDrawer();

            mCurrentCoworkspaceAsynctask = new CurrentCoworkspaceAsynctask();
            mCurrentCoworkspaceAsynctask.execute();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mCurrentCoworkspaceAsynctask != null)
            mCurrentCoworkspaceAsynctask.cancel(false);
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
            case MENU_CURRENT_COWORKSPACE:
                fragment = new CoworkspaceFragment();

                // Pass the CoworkspaceId to the Fragment
                Bundle bundle = new Bundle();
                bundle.putString(CoworkspaceFragment.EXTRA_COWORKSPACE_ID, getIntent().getStringExtra(CoworkspaceFragment.EXTRA_COWORKSPACE_ID));
                fragment.setArguments(bundle);
                break;

            case MENU_MORE_COWORKSPACE:
                fragment = new CoworkspacesFragment();
                break;
        }

        showFragment(fragment);

        return true;
    }


    // ------------------- Methods -------------------- //

    private static final int MENU_CURRENT_COWORKSPACE = 1;
    private static final int MENU_MORE_COWORKSPACE = 2;

    private void manageNavigationDrawer() {
        // Manage DrawerLayout and his toggle
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(NavigationActivity.this, mDrawerLayout, mToolbar, R.string.Open, R.string.Close) {

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
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void manageNavigationDrawerMenu() {

        Menu menu = mNavigationView.getMenu();

        // If currently in a coworkspace
        menu.add(0, MENU_CURRENT_COWORKSPACE, 0, "Super coworkspace");

        // If favorites cowordspaces
        // SubMenu subMenu2 = menu.addSubMenu("Mes coworkings");
        // subMenu2.add(0, 0, 0, "blablabla");

        // More coworkspaces
        SubMenu subMenu3 = menu.addSubMenu("Plus de coworkings");
        subMenu3.add(0, MENU_MORE_COWORKSPACE, 0, "Voir les autres coworkings");
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_Content, fragment)
                .commit();

        mDrawerLayout.closeDrawers();
    }


    // ------------------ AsyncTasks ------------------ //

    private class CurrentCoworkspaceAsynctask extends AsyncTask<Void, Void, Coworkspace> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(NavigationActivity.this, null, getString(R.string.Please_wait), true, false);
        }

        @Override
        protected Coworkspace doInBackground(Void... voids) {
            return CoworkspacesManager.getCoworkspaces().get(0);
        }

        @Override
        protected void onPostExecute(Coworkspace coworkspace) {
            super.onPostExecute(coworkspace);

            progressDialog.dismiss();

            if (coworkspace != null) {
                manageNavigationDrawerMenu();
                showFragment(new CoworkspacesFragment());
            } else
                Toast.makeText(NavigationActivity.this, R.string.Whoops_an_error_has_occured__Check_your_internet_connection, Toast.LENGTH_LONG).show();
        }
    }


    // ----------------- GUI Adapter ------------------ //

    // --------------------- Menu --------------------- //

}