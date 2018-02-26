package com.stoyanov5.tallycounter.addtally;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stoyanov5.tallycounter.R;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by B3f0r on 23-Feb-18.
 */

public class AddTallyFragment extends Fragment implements AddTallyContract.View {

    public static final String ARGUMENT_TALLY_ID = "TALLY_ID";

    private AddTallyContract.Presenter addTallyPresenter;

    private TextView title;

    public AddTallyFragment() {

    }

    public static AddTallyFragment newInstance() {
        return new AddTallyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.addtally_fragment, container, false);
        title = root.findViewById(R.id.add_tally_title);
        setHasOptionsMenu(false);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        addTallyPresenter.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_tally_save);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTallyPresenter.saveTally(title.getText().toString());
            }
        });
    }

    @Override
    public void setPresenter(AddTallyContract.Presenter presenter) {
        addTallyPresenter = checkNotNull(presenter);
    }

    @Override
    public void showEmptyTallyError() {
        Snackbar.make(title, "Tallies cannot be empty", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTalliesList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
