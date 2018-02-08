package com.stoyanov5.tallycounter.data;

/**
 * Created by B3f0r on 08-Feb-18.
 */

/**
 * Immutable model class for a Tally.
 */
@Entity(tableName = "tallies")
public final class Tally {

    private int counter;

    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
