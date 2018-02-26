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

    private boolean isDataMissing;

    public AddTallyPresenter(String tallyId, @NonNull TalliesDataSource talliesDataSource,
                             @NonNull AddTallyContract.View addTallyView, boolean shouldLoadDataFromRepo) {
        this.tallyId = tallyId;
        this.talliesDataSource = talliesDataSource;
        this.addTallyView = addTallyView;
        isDataMissing = shouldLoadDataFromRepo;

        addTallyView.setPresenter(this);
    }

    @Override
    public void start() {
        if (!isNewTally() && isDataMissing) {
            populateTally();
        }
    }

    @Override
    public void saveTally(String title) {
        if (isNewTally()) {
            createTally(title);
        }
        else {
            updateTally(title);
        }
    }

    @Override
    public void populateTally() {
        if (isNewTally()) {
            throw new RuntimeException("populateTally() was called but tally is new");
        } else {
            talliesDataSource.getTally(tallyId, this);
        }
    }

    @Override
    public void onTallyLoaded(Tally tally) {
        if (addTallyView.isActive()) {
            addTallyView.setTitle(tally.getTitle());
        }
    }

    @Override
    public void onDataNotAvailable() {
        if (addTallyView.isActive()) {
            addTallyView.showEmptyTallyError();
        }
    }

    private boolean isNewTally() {
        return tallyId == null;
    }

    private void createTally(String title) {
        Tally newTally = new Tally(title);
        if (newTally.isEmpty()) {
            addTallyView.showEmptyTallyError();
        } else {
            talliesDataSource.saveTally(newTally);
            addTallyView.showTalliesList();
        }
    }

    private void updateTally(String title) {
        if (isNewTally()) {
            throw new RuntimeException("updateTally() was called but tally is new");
        }
        talliesDataSource.saveTally(new Tally(title, tallyId));
        addTallyView.showTalliesList();
    }

}
