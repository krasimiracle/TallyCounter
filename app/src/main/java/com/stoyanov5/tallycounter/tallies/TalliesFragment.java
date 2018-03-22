package com.stoyanov5.tallycounter.tallies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.stoyanov5.tallycounter.R;
import com.stoyanov5.tallycounter.addtally.AddTallyActivity;
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
        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.setClipToPadding(false);
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
                talliesPresenter.addNewTally();
            }
        });

        final ScrollChildSwipeRefresh scrollChildSwipeRefreshLayout =
                root.findViewById(R.id.refresh_layout);

        scrollChildSwipeRefreshLayout.setScrollUpChild(listView);

        scrollChildSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                talliesPresenter.loadTallies(false);
            }
        });

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void setPresenter(TalliesContract.Presenter presenter) {
        talliesPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        talliesPresenter.result(requestCode, resultCode);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout swipeRefreshLayout = getView().findViewById(R.id.refresh_layout);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showTallies(List<Tally> tallies) {
        talliesAdapter.replaceData(tallies);

        talliesView.setVisibility(View.VISIBLE);
        noTalliesView.setVisibility(View.GONE);
    }

    @Override
    public void showAddTally() {
        Intent intent = new Intent(getContext(), AddTallyActivity.class);
        startActivityForResult(intent, AddTallyActivity.REQUEST_ADD_TALLY);
    }

    @Override
    public void showNoTallies() {
        showNoTalliesView("You have no Tallies!", R.drawable.ic_launcher_foreground);
    }

    @Override
    public void showSuccessfullySavedMessage() {
        Snackbar.make(checkNotNull(getView()), "Tally saved", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingTalliesError() {
        Snackbar.make(checkNotNull(getView()), R.string.loading_tallies_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    TallyItemListener tallyItemListener = new TallyItemListener() {
        @Override
        public void onTallyClicked(Tally clickedTally) {
            // presenter.openTallyDetail
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

        private void setList(List<Tally> tallies) {
            this.tallies = checkNotNull(tallies);
        }

        public void replaceData(List<Tally> tallies) {
            setList(tallies);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return tallies.size();
        }

        @Override
        public Tally getItem(int i) {
            return tallies.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = view;

            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                rowView = inflater.inflate(R.layout.tally_item, viewGroup, false);
            }

            final Tally tally = getItem(i);

            TextView title = rowView.findViewById(R.id.tally_title);
            title.setText(tally.getTitle());

            final TextView counterText = rowView.findViewById(R.id.tally_counterText);

            Button incrementButton = rowView.findViewById(R.id.incrementButton);
            Button decrementButton = rowView.findViewById(R.id.decrementButton);

            incrementButton.setTag(getItem(i));
            incrementButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counterText.setText(String.valueOf(Integer.parseInt(counterText.getText().toString()) + 1));
                }
            });

            decrementButton.setTag(getItem(i));
            decrementButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counterText.setText(String.valueOf(Integer.parseInt(counterText.getText().toString()) - 1));
                }
            });

            return rowView;
        }
    }

    public interface TallyItemListener {

        void onTallyClicked(Tally clickedTally);

    }
}
