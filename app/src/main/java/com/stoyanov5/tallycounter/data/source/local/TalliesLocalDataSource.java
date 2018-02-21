package com.stoyanov5.tallycounter.data.source.local;

import android.support.annotation.NonNull;

import com.stoyanov5.tallycounter.data.Tally;
import com.stoyanov5.tallycounter.data.source.TalliesDataSource;
import com.stoyanov5.tallycounter.util.AppExecutors;

import java.util.List;

/**
 * Created by B3f0r on 21-Feb-18.
 */

/**
 * Concrete implementation of a data source as a database.
 */

public class TalliesLocalDataSource implements TalliesDataSource {

    private static volatile TalliesLocalDataSource INSTANCE;

    private TallyDao tallyDao;

    private AppExecutors appExecutors;

    // Prevent direct instantiation.
    private TalliesLocalDataSource(@NonNull AppExecutors appExecutors, @NonNull TallyDao tallyDao) {
        this.appExecutors = appExecutors;
        this.tallyDao = tallyDao;
    }

    public static TalliesLocalDataSource getInstance(@NonNull AppExecutors appExecutors, @NonNull TallyDao tallyDao) {
        if (INSTANCE == null) {
            synchronized (TalliesLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TalliesLocalDataSource(appExecutors, tallyDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getTally(@NonNull final String tallyId, @NonNull final GetTallyCallback callback) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Tally tally = tallyDao.getTallyById(tallyId);

                appExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (tally != null) {
                            callback.onTallyLoaded(tally);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                });
            }
        };

        appExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getTallies(@NonNull final LoadTalliesCallback callback) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Tally> talliesList = tallyDao.getTallies();

                appExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (talliesList.isEmpty()) {
                            // It will be called if the table is new or empty
                            callback.onDataNotAvailable();
                        } else {
                            callback.onTalliesLoaded(talliesList);
                        }
                    }
                });
            }
        };
        appExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void refreshTallies() {
        // Not required since TalliesRepository handles the logic for refreshing.
    }

    @Override
    public void saveTally(@NonNull final Tally tally) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                tallyDao.insertTally(tally);
            }
        };
        appExecutors.getDiskIO().execute(saveRunnable);
    }

    @Override
    public void deleteTally(@NonNull final String tallyId) {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                tallyDao.deleteTallyById(tallyId);
            }
        };
        appExecutors.getDiskIO().execute(deleteRunnable);
    }

    @Override
    public void deleteAllTallies() {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                tallyDao.deleteTallies();
            }
        };
        appExecutors.getDiskIO().execute(deleteRunnable);
    }
}
