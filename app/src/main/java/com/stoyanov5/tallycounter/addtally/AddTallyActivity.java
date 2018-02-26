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

    private AddTallyPresenter addTallyPresenter;

    private ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtally_activity);

        // Set up toolbar.
        Toolbar toolbar = findViewById(R.id.addtally_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        AddTallyFragment addTallyFragment = (AddTallyFragment) getSupportFragmentManager().findFragmentById(R.id.addtally_contentFrame);

        String tallyId = getIntent().getStringExtra(AddTallyFragment.ARGUMENT_TALLY_ID);

        actionBar.setTitle("New Tally");

        if (addTallyFragment == null) {
            addTallyFragment = AddTallyFragment.newInstance();

            if (getIntent().hasExtra(AddTallyFragment.ARGUMENT_TALLY_ID)) {
                Bundle bundle = new Bundle();
                bundle.putString(AddTallyFragment.ARGUMENT_TALLY_ID, tallyId);
                addTallyFragment.setArguments(bundle);
            }

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addTallyFragment, R.id.addtally_contentFrame);
        }

        addTallyPresenter = new AddTallyPresenter(tallyId, Injection.provideTalliesRepository(getApplicationContext()), addTallyFragment);
    }
}
