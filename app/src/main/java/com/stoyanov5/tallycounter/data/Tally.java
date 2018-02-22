package com.stoyanov5.tallycounter.data;

/**
 * Created by B3f0r on 08-Feb-18.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Immutable model class for a Tally.
 */
@Entity(tableName = "tallies")
public final class Tally {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryid")
    private final String id;

    @ColumnInfo(name = "counter")
    private int counter = 0;

    @Nullable
    @ColumnInfo(name = "name")
    private String name;

    public Tally(@NonNull String id, @Nullable String name, @NonNull int counter) {
        this.id = id;
        this.name = name;
        this.counter = counter;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
