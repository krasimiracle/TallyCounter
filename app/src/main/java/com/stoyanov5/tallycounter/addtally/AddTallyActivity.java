package com.stoyanov5.tallycounter.addtally;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.stoyanov5.tallycounter.Injection;
import com.stoyanov5.tallycounter.R;
import com.stoyanov5.tallycounter.util.ActivityUtils;

/**
 * Created by B3f0r on 23-Feb-18.
 */

public class AddTallyActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_TALLY = 1;

    public static final String SHOULD_LOAD_DATA_FROM_REPO_KEY = "SHOULD_LOAD_DATA_FROM_REPO_KEY";

    private AddTallyPresenter addTallyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtally_activity);

        AddTallyFragment addTallyFragment = (AddTallyFragment) getSupportFragmentManager()
                .findFragmentById(R.id.addtally_contentFrame);

        String tallyId = getIntent().getStringExtra(AddTallyFragment.ARGUMENT_TALLY_ID);

        // Set up toolbar.
        Toolbar toolbar = findViewById(R.id.addtally_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(tallyId);
        }

        if (addTallyFragment == null) {
            addTallyFragment = AddTallyFragment.newInstance();

            if (getIntent().hasExtra(AddTallyFragment.ARGUMENT_TALLY_ID)) {
                Bundle bundle = new Bundle();
                bundle.putString(AddTallyFragment.ARGUMENT_TALLY_ID, tallyId);
                addTallyFragment.setArguments(bundle);
            }

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addTallyFragment, R.id.addtally_contentFrame);
        }

        boolean shouldLoadDataFromRepo = true;

        if (savedInstanceState != null) {
            shouldLoadDataFromRepo = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY);
        }

        addTallyPresenter = new AddTallyPresenter(tallyId, Injection.provideTalliesRepository(getApplicationContext()),
                addTallyFragment, shouldLoadDataFromRepo);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY, addTallyPresenter.isDataMissing());
        super.onSaveInstanceState(outState);
    }
}
