package com.stoyanov5.tallycounter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.stoyanov5.tallycounter.data.source.TalliesRepository;
import com.stoyanov5.tallycounter.data.source.local.TalliesLocalDataSource;
import com.stoyanov5.tallycounter.data.source.local.TallyDatabase;
import com.stoyanov5.tallycounter.util.AppExecutors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by B3f0r on 21-Feb-18.
 */

public class Injection {

    public static TalliesRepository provideTalliesRepository(@NonNull Context context) {
        checkNotNull(context);
        TallyDatabase database = TallyDatabase.getInstance(context);
        return TalliesRepository.getInstance(TalliesLocalDataSource.getInstance(new AppExecutors(),
                database.tallyDao()));
    }
}
