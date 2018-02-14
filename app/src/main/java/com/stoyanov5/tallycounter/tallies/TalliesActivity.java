package com.stoyanov5.tallycounter.tallies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.stoyanov5.tallycounter.R;

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
    }

    private void setupDrawerContent(){

    }
}
