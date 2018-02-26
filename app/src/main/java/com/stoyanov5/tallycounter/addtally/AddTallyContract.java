package com.stoyanov5.tallycounter.addtally;

import com.stoyanov5.tallycounter.BasePresenter;
import com.stoyanov5.tallycounter.BaseView;

/**
 * Created by B3f0r on 23-Feb-18.
 */

public interface AddTallyContract {

    interface View extends BaseView<Presenter> {

        void showEmptyTallyError();

        void showTalliesList();

        void setTitle(String title);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void saveTally(String title);

        void populateTally();
    }
}
