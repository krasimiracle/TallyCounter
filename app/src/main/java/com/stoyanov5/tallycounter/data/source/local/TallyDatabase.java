package com.stoyanov5.tallycounter.data.source.local;

/**
 * Created by B3f0r on 12-Feb-18.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.stoyanov5.tallycounter.data.Tally;

/**
 * The Room Database that contains the Tally table
 */
@Database(entities = {Tally.class}, version = 1)
public abstract class TallyDatabase extends RoomDatabase {

    private static TallyDatabase INSTANCE;

    public abstract TallyDao tallyDao();

    private static final Object sLock = new Object();

    public static TallyDatabase getINSTANCE(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        TallyDatabase.class, "Tallies.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
