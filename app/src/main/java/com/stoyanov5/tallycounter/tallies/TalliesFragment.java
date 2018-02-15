package com.stoyanov5.tallycounter.tallies;

import android.support.v4.app.Fragment;

import com.stoyanov5.tallycounter.data.Tally;

import java.util.List;

/**
 * Created by B3f0r on 14-Feb-18.
 */

public class TalliesFragment extends Fragment implements TalliesContract.View {

    public static TalliesFragment newInstance() {
        return new TalliesFragment();
    }

    @Override
    public void setPresenter(TalliesContract.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showTallies(List<Tally> tallies) {

    }

    @Override
    public void showAddTally() {

    }
}
