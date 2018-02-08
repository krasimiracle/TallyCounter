package com.stoyanov5.tallycounter.tallies;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by B3f0r on 08-Feb-18.
 */

public class TalliesPresenter implements TalliesContract.Presenter {

    private final TalliesContract.View talliesView;

    private boolean firstLoad = true;

    public TalliesPresenter(TalliesContract.View talliesView) {
        this.talliesView = checkNotNull(talliesView, "talliesView cannot be null");

        talliesView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTallies(false);
    }

    @Override
    public void addNewTallie() {

    }

    @Override
    public void loadTallies(boolean forceUpdate) {
        if (forceUpdate){
            //Refresh tallies

        }
    }


}
