package com.stoyanov5.tallycounter.data.source;

/**
 * Created by B3f0r on 10-Feb-18.
 */

import android.support.annotation.NonNull;

import com.stoyanov5.tallycounter.data.Tally;

import java.util.List;

/**
 * Main entry point for accessing tallies data.
 * <p>
 * For simplicity, only getTally() and getTallies() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network/database errors or successful operations.
 */
public interface TalliesDataSource {

    interface GetTallyCallback {

        void onTallyLoaded(Tally tally);

        void onDataNotAvailable();
    }

    interface LoadTalliesCallback {

        void onTalliesLoaded(List<Tally> tallies);

        void onDataNotAvailable();
    }

    void getTally(@NonNull String tallyId, @NonNull GetTallyCallback callback);

    void getTallies(@NonNull LoadTalliesCallback callback);

    void refreshTallies();

    void saveTally(@NonNull Tally tally);

    void deleteTally(@NonNull String tallyId);

    void deleteAllTallies();
}
