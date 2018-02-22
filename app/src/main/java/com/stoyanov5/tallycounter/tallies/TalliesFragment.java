package com.stoyanov5.tallycounter.tallies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stoyanov5.tallycounter.data.Tally;

import java.util.List;

/**
 * Created by B3f0r on 14-Feb-18.
 */

public class TalliesFragment extends Fragment implements TalliesContract.View {

    // Required empty constructor
    public TalliesFragment() {

    }

    public static TalliesFragment newInstance() {
        return new TalliesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
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
