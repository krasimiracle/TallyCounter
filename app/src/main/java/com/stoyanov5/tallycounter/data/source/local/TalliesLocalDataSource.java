package com.stoyanov5.tallycounter.data.source.local;

import android.support.annotation.NonNull;

import com.stoyanov5.tallycounter.data.Tally;
import com.stoyanov5.tallycounter.data.source.TalliesDataSource;
import com.stoyanov5.tallycounter.util.AppExecutors;

/**
 * Created by B3f0r on 21-Feb-18.
 */

public class TalliesLocalDataSource implements TalliesDataSource {

    private static volatile TalliesLocalDataSource INSTANCE;

    private TallyDao tallyDao;

    private AppExecutors appExecutors;

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
    public void getTally(@NonNull String tallyId, @NonNull GetTallyCallback callback) {

    }

    @Override
    public void getTallies(@NonNull LoadTalliesCallback callback) {

    }

    @Override
    public void refreshTallies() {

    }

    @Override
    public void saveTally(@NonNull Tally tally) {

    }

    @Override
    public void deleteTally(@NonNull String tallyId) {

    }

    @Override
    public void deleteAllTallies() {

    }
}
