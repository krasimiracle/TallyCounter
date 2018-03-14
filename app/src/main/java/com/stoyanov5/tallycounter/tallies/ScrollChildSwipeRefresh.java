package com.stoyanov5.tallycounter.tallies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by B3f0r on 14-Mar-18.
 */

public class ScrollChildSwipeRefresh extends SwipeRefreshLayout {

    private View scrollUpChild;

    public ScrollChildSwipeRefresh(@NonNull Context context) {
        super(context);
    }

    public ScrollChildSwipeRefresh(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canChildScrollUp() {
        if (scrollUpChild != null) {
            return scrollUpChild.canScrollVertically(-1);
        }
        return super.canChildScrollUp();
    }

    public void setScrollUpChild(View view) {
        scrollUpChild = view;
    }
}
