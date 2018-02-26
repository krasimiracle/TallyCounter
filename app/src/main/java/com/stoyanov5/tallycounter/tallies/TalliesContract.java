package com.stoyanov5.tallycounter.tallies;

import com.stoyanov5.tallycounter.BasePresenter;
import com.stoyanov5.tallycounter.BaseView;
import com.stoyanov5.tallycounter.data.Tally;

import java.util.List;

/**
 * Created by B3f0r on 08-Feb-18.
 */

public interface TalliesContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showTallies(List<Tally>tallies);

        void showAddTally();

        void showSuccessfullySavedMessage();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadTallies(boolean forceUpdate);

        void addNewTally();

    }
}
