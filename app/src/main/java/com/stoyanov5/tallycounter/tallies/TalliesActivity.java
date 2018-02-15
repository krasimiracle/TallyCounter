package com.stoyanov5.tallycounter.tallies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.stoyanov5.tallycounter.R;
import com.stoyanov5.tallycounter.util.ActivityUtils;

/**
 * Created by B3f0r on 08-Feb-18.
 */

public class TalliesActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private TalliesPresenter talliesPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tallies_activity);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Set navigation drawer.
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        if (navigationView != null){
            setupDrawerContent();
        }

        // Create fragment
        TalliesFragment talliesFragment = (TalliesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (talliesFragment == null) {
            // Create the fragment
            talliesFragment = TalliesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), talliesFragment, R.id.contentFrame);
        }

        // Create Presenter
        //talliesPresenter = new TalliesPresenter();
    }

    private void setupDrawerContent(){

    }
}
