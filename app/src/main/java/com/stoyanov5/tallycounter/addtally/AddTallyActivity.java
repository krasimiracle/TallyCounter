package com.stoyanov5.tallycounter.addtally;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by B3f0r on 23-Feb-18.
 */

public class AddTallyActivity extends AppCompatActivity {

    private AddTallyPresenter addTallyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.addtally_activity);

        //AddTallyFragment addTallyFragment = getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        //addTallyPresenter = new AddTallyPresenter(tallyId, Injection.provideTalliesRepository(getApplicationContext()), addTallyFragment);
    }
}
