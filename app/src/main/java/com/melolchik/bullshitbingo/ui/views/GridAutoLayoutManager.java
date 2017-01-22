package com.melolchik.bullshitbingo.ui.views;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.melolchik.bullshitbingo.AppLogger;
import com.melolchik.bullshitbingo.utils.Util;

/**
 * Created by melolchik on 22.01.2017.
 */

public class GridAutoLayoutManager extends GridLayoutManager {
    final int mSpanCountPortrait;
    final int mSpanCountLandscape;

    public GridAutoLayoutManager(Context context, int spanCountPortrait, int spanCountLandscape) {
        super(context, Util.getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT ? spanCountPortrait : spanCountLandscape);
        mSpanCountPortrait = spanCountPortrait;
        mSpanCountLandscape = spanCountLandscape;

    }

    public void onConfigurationChanged(Configuration newConfig) {
        setSpanCount(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT ? mSpanCountPortrait : mSpanCountLandscape);
        requestLayout();


    }

    protected void log(String message) {
        AppLogger.log(this.getClass().getSimpleName() + " " + message);
    }


}
