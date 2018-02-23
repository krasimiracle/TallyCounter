package com.stoyanov5.tallycounter.addtally;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.stoyanov5.tallycounter.data.Tally;
import com.stoyanov5.tallycounter.data.source.TalliesDataSource;

/**
 * Created by B3f0r on 23-Feb-18.
 */

public class AddTallyPresenter implements AddTallyContract.Presenter, TalliesDataSource.GetTallyCallback {

    @NonNull
    private TalliesDataSource talliesDataSource;

    @NonNull
    private final AddTallyContract.View addTallyView;

    @Nullable
    private String tallyId;

    public AddTallyPresenter(String tallyId, @NonNull TalliesDataSource talliesDataSource, @NonNull AddTallyContract.View addTallyView) {
        this.tallyId = tallyId;
        this.talliesDataSource = talliesDataSource;
        this.addTallyView = addTallyView;

        addTallyView.setPresenter(this);
    }

    @Override
    public void start() {
        if (!isNewTask()) {
            populateTally();
        }
    }

    @Override
    public void saveTally(String title) {
        if (isNewTask()) {
            createTally(title);
        }
    }

    @Override
    public void populateTally() {
        if (isNewTask()) {
            throw new RuntimeException("populateTally() was called but tally is new");
        }
        else {
            talliesDataSource.getTally(tallyId, this);
        }
    }

    @Override
    public void onTallyLoaded(Tally tally) {

    }

    @Override
    public void onDataNotAvailable() {

    }

    private boolean isNewTask() {
        return tallyId == null;
    }

    private void createTally(String title) {
        Tally newTally = new Tally(title);
        if (newTally.isEmpty()) {
            addTallyView.showEmptyTallyError();
        }
        else {
            talliesDataSource.saveTally(newTally);
            addTallyView.showTalliesList();
        }
    }
}
