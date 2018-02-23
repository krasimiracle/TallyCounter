package com.stoyanov5.tallycounter.tallies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.stoyanov5.tallycounter.R;
import com.stoyanov5.tallycounter.data.Tally;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by B3f0r on 14-Feb-18.
 */

public class TalliesFragment extends Fragment implements TalliesContract.View {

    private TalliesContract.Presenter talliesPresenter;

    private TalliesAdapter talliesAdapter;

    private LinearLayout talliesView;

    private View noTalliesView;

    private ImageView noTalliesImage;

    private TextView noTalliesText;

    // Required empty constructor
    public TalliesFragment() {

    }

    public static TalliesFragment newInstance() {
        return new TalliesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        talliesAdapter = new TalliesAdapter(new ArrayList<Tally>(0), tallyItemListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        talliesPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tallies_fragment, container, false);

        // Tallies View
        ListView listView = root.findViewById(R.id.tallies_list);
        listView.setAdapter(talliesAdapter);
        talliesView = root.findViewById(R.id.tallies_linearlayout);

        // No Tallies View
        noTalliesView = root.findViewById(R.id.noTallies);
        noTalliesImage = root.findViewById(R.id.noTallyIcon);
        noTalliesText = root.findViewById(R.id.noTallyText);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_tally);
        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //talliesPresenter.addNewTally();
            }
        });

        return root;
    }

    @Override
    public void setPresenter(TalliesContract.Presenter presenter) {
        talliesPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showTallies(List<Tally> tallies) {
        talliesView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddTally() {
        // Intent to addTallyActivity
    }

    TallyItemListener tallyItemListener = new TallyItemListener() {
        @Override
        public void onTallyClicked(Tally clickedTally) {
            // presenter.openTallyDetail
        }

        @Override
        public void onTallyIncremented(Tally incrementedTally) {
            // presenter.incrementTally
        }

        @Override
        public void onTallyDecremented(Tally decrementedTally) {
            // presenter.decrementTally
        }
    };

    private void showNoTalliesView(String text, int iconRes) {
        talliesView.setVisibility(View.GONE);
        noTalliesView.setVisibility(View.VISIBLE);

        noTalliesText.setText(text);
        noTalliesImage.setImageDrawable(getResources().getDrawable(iconRes));
    }

    private static class TalliesAdapter extends BaseAdapter {

        private List<Tally> tallies;
        private TallyItemListener tallyItemListener;

        public TalliesAdapter(List<Tally> tallies, TallyItemListener tallyItemListener) {
            setList(tallies);
            this.tallyItemListener = tallyItemListener;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }

        private void setList(List<Tally> tallies) {
            this.tallies = checkNotNull(tallies);
        }
    }

    public interface TallyItemListener {

        void onTallyClicked(Tally clickedTally);

        void onTallyIncremented(Tally incrementedTally);

        void onTallyDecremented(Tally decrementedTally);
    }
}
