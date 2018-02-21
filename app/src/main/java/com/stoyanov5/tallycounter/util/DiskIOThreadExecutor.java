package com.stoyanov5.tallycounter.util;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by B3f0r on 21-Feb-18.
 */

class DiskIOThreadExecutor implements Executor {

    private final  Executor diskIO;

    DiskIOThreadExecutor(){diskIO = Executors.newSingleThreadExecutor();}

    @Override
    public void execute(@NonNull Runnable runnable) {
        diskIO.execute(runnable);
    }
}
