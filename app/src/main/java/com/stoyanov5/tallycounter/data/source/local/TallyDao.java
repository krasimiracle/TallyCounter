package com.stoyanov5.tallycounter.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.stoyanov5.tallycounter.data.Tally;

import java.util.List;

/**
 * Created by B3f0r on 12-Feb-18.
 */

@Dao
public interface TallyDao {

    /**
     * Select all tallies from the tallies table
     * @return all tallies.
     */
    @Query("Select * FROM Tallies")
    List<Tally> getTallies();


    /**
     * Select a tally by id.
     * @param tallyId the tally id.
     * @return a tally with tallyId.
     */
    @Query("SELECT * FROM Tallies WHERE entryid = :tallyId")
    Tally getTallyById(String tallyId);

    /**
     * Insert a tally in the database. If the tally already exists, it will get replaced.
     * @param tally the tally to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTally(Tally tally);

    /**
     * Update a tally
     * @param tally the tally to be updated
     * @return the number of tallies updated. This should always be 1.
     */
    @Update
    int updateTally(Tally tally);

    /**
     * Delete all tallies.
     */
    @Query("DELETE FROM Tallies")
    void deleteTallies();
}
