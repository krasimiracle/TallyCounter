package com.stoyanov5.tallycounter.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.stoyanov5.tallycounter.data.Tally;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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

    boolean cacheIsDirty = false;

    // Singleton constructor
    private TalliesRepository(@NonNull TalliesDataSource talliesLocalDataSource) {
        this.talliesLocalDataSource = checkNotNull(talliesLocalDataSource);
    }

    /**
     * Returns the single instance of this class
     *
     * @param tasksLocalDataSource the device storage data source
     * @return the (@link TalliesRepository) instance
     */
    public static TalliesRepository getInstance(TalliesDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TalliesRepository(tasksLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to destroy the current instance of the class. A new one would be build when getInstance is called
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getTally(@NonNull final String tallyId, @NonNull final GetTallyCallback callback) {
        checkNotNull(tallyId);
        checkNotNull(callback);

        final Tally cachedTally = getTallyWithId(tallyId);

        if (cachedTally != null) {
            callback.onTallyLoaded(cachedTally);
            return;
        }

        talliesLocalDataSource.getTally(tallyId, new GetTallyCallback() {
            @Override
            public void onTallyLoaded(Tally tally) {
                if (cachedTallies == null) {
                    cachedTallies = new LinkedHashMap<>();
                }
                cachedTallies.put(tally.getId(), tally);
                callback.onTallyLoaded(tally);
            }

            @Override
            public void onDataNotAvailable() {
                // If local data not available, use remote data
            }
        });
    }

    @Override
    public void getTallies(@NonNull final LoadTalliesCallback callback) {
        checkNotNull(callback);

        if (cachedTallies != null && !cacheIsDirty) {
            callback.onTalliesLoaded(new ArrayList<>(cachedTallies.values()));
            return;
        }

        talliesLocalDataSource.getTallies(new LoadTalliesCallback() {
            @Override
            public void onTalliesLoaded(List<Tally> tallies) {
                refreshCache(tallies);
                callback.onTalliesLoaded(new ArrayList<>(cachedTallies.values()));
            }

            @Override
            public void onDataNotAvailable() {
                // Get the data from remote data source
            }
        });
    }

    @Override
    public void refreshTallies() {
        cacheIsDirty = true;
    }

    @Override
    public void saveTally(@NonNull Tally tally) {
        checkNotNull(tally);
        talliesLocalDataSource.saveTally(tally);

        if (cachedTallies == null) {
            cachedTallies = new LinkedHashMap<>();
        }
        cachedTallies.put(tally.getId(), tally);
    }

    @Override
    public void deleteTally(@NonNull String tallyId) {
        talliesLocalDataSource.deleteTally(tallyId);

        cachedTallies.remove(tallyId);
    }

    @Override
    public void deleteAllTallies() {
        talliesLocalDataSource.deleteAllTallies();

        if (cachedTallies == null) {
            cachedTallies = new LinkedHashMap<>();
        }
        cachedTallies.clear();
    }

    private void refreshCache(List<Tally> tallies) {
        if (cachedTallies == null) {
            cachedTallies = new LinkedHashMap<>();
        }
        cachedTallies.clear();
        for (Tally tally : tallies) {
            cachedTallies.put(tally.getId(), tally);
        }
        cacheIsDirty = false;
    }

    @Nullable
    private Tally getTallyWithId(@NonNull String id) {
        checkNotNull(id);
        if (cachedTallies == null || cachedTallies.isEmpty()) {
            return null;
        } else {
            return cachedTallies.get(id);
        }
    }

    private void refreshLocalDataSource(List<Tally> tallies) {
        talliesLocalDataSource.deleteAllTallies();
        for (Tally tally : tallies) {
            talliesLocalDataSource.saveTally(tally);
        }
    }
}
