package com.stoyanov5.tallycounter.data;

/**
 * Created by B3f0r on 08-Feb-18.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Strings;

import java.util.UUID;

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
    @ColumnInfo(name = "title")
    private String title;
    
    @Ignore
    public Tally(@Nullable String title) {
        this(UUID.randomUUID().toString(), title, 0);
    }

    @Ignore
    public Tally(@Nullable String title,@NonNull String id) {
        this(title, id, 0);
    }

    public Tally(@NonNull String id, @Nullable String title, @NonNull int counter) {
        this.id = id;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(title);
    }
}
