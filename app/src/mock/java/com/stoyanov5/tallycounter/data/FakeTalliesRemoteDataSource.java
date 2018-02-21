package com.stoyanov5.tallycounter.data;

/**
 * Created by B3f0r on 21-Feb-18.
 */

public class FakeTalliesRemoteDataSource {

    private static FakeTalliesRemoteDataSource INSTANCE = new FakeTalliesRemoteDataSource();

    // Required for instantiation
    private FakeTalliesRemoteDataSource() {}

    public static FakeTalliesRemoteDataSource getInstance() {
        return INSTANCE;
    }
}
