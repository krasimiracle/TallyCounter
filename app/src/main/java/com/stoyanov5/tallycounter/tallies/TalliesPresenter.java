package com.stoyanov5.tallycounter.tallies;

import android.support.annotation.NonNull;

import com.stoyanov5.tallycounter.data.source.TalliesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by B3f0r on 08-Feb-18.
 */

public class TalliesPresenter implements TalliesContract.Presenter {

    private final TalliesRepository talliesRepository;

    private final TalliesContract.View talliesView;

    private boolean firstLoad = true;

    public TalliesPresenter(@NonNull TalliesRepository talliesRepository, TalliesContract.View talliesView) {
        this.talliesRepository = checkNotNull(talliesRepository, "talliesRepository cannot be null");
        this.talliesView = checkNotNull(talliesView, "talliesView cannot be null");

        talliesView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTallies(false);
    }

    @Override
    public void addNewTally() {

    }

    @Override
    public void loadTallies(boolean forceUpdate) {
        if (forceUpdate) {
            //Refresh tallies

        }
    }


}
