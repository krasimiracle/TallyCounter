package com.stoyanov5.tallycounter.data.source;

import android.support.annotation.NonNull;

import com.stoyanov5.tallycounter.data.Tally;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by B3f0r on 11-Feb-18.
 */

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 */
public class TalliesRepository implements TalliesDataSource {

    private static TalliesRepository INSTANCE = null;

    private final TalliesDataSource talliesLocalDataSource;

    /**
     * Package local visibility so it can be tested.
     */
    Map<String, Tally> cachedTallies;


    private TalliesRepository(@NonNull TalliesDataSource talliesLocalDataSource) {
        this.talliesLocalDataSource = checkNotNull(talliesLocalDataSource);
    }

    /**
     * Returns the single instance of this class
     * @param tasksLocalDataSource the device storage data source
     * @return the (@link TalliesRepository) instance
     */
    public static TalliesRepository getInstance(TalliesDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TalliesRepository(tasksLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getTally(@NonNull String tallyId, @NonNull GetTallyCallback callback) {

    }

    @Override
    public void getTallies(@NonNull LoadTalliesCallback callback) {

    }

    @Override
    public void refreshTallies() {

    }

    @Override
    public void deleteTally(@NonNull String tallyId) {

    }

    @Override
    public void deleteAllTallies() {

    }
}
