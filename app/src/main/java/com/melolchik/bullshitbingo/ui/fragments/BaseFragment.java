package com.melolchik.bullshitbingo.ui.fragments;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.melolchik.bullshitbingo.ui.activities.BaseActivity;
import com.melolchik.bullshitbingo.utils.Util;

import java.io.File;


/**
 * Created by Olga Melekhina on 21.01.2017.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * Gets view layout id.
     *
     * @return the view layout id
     */
    protected abstract int getViewLayoutID();

    /**
     * On create view.
     *
     * @param rootView the root view
     */
    protected abstract void onCreateView(View rootView, Bundle savedInstanceState);

    private BroadcastReceiver mBroadcastReceiver;

    /**
     * Is nested fragment boolean.
     *
     * @return the boolean
     */
    protected boolean isNestedFragment() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!isNestedFragment()) {
            setRetainInstance(true);
        }
        super.onCreate(savedInstanceState);
        initBroadcastReceiver();
        IntentFilter filter = getBroadcastIntentFilter();
        if (filter != null) {
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiver, getBroadcastIntentFilter());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //log("onCreateView savedInstanceState = " + savedInstanceState);
        View view = inflater.inflate(getViewLayoutID(), container, false);
        view.setClickable(true);
        onCreateView(view, savedInstanceState);
        //initBroadcastReceiver();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Util.hideSoftKeyboard(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Util.hideSoftKeyboard(getActivity());
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    /**
     * Gets broadcast intent filter.
     *
     * @return the broadcast intent filter
     */
    protected IntentFilter getBroadcastIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        return intentFilter;
    }

    private void initBroadcastReceiver() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                BaseFragment.this.onReceive(context, intent);
            }
        };
    }

    /**
     * On receive.
     *
     * @param context the context
     * @param intent  the intent
     */
    protected void onReceive(Context context, Intent intent) {
        //log("onReceive = " + intent.getAction());

    }

    /**
     * Gets tag for stack.
     *
     * @return the tag for stack
     */
    public String getTagForStack() {
        return this.getClass().getCanonicalName();
    }

     /**
     * Show fragment.
     *
     * @param newFragment       the new fragment
     * @param addToBackStack    the add to back stack
     * @param withFadeAnimation the with fade animation
     */
    public void showFragment(BaseFragment newFragment, boolean addToBackStack, boolean withFadeAnimation) {
        FragmentActivity activity = getActivity();
        if (activity == null) return;
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showFragment(newFragment, addToBackStack, withFadeAnimation);
        }
    }

    public BaseFragment getCurrentFragment() {
        FragmentActivity activity = getActivity();
        if (activity == null) return null;
        if (activity instanceof BaseActivity) {
            return ((BaseActivity) activity).getCurrentFragment();
        }
        return null;
    }

    protected void goToPrevFragmentInParentActivity(String tag) {

        FragmentActivity activity = getActivity();
        if (activity == null) return;
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).goToPrevFragment(tag);
        }

    }

    /**
     * Go to prev fragment in parent activity.
     */
    protected void goToPrevFragmentInParentActivity() {

        FragmentActivity activity = getActivity();
        if (activity == null) return;
        log("goToPrevFragmentInParentActivity activity = " + activity);
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).goToPrevFragmentIfExist();
        }

    }

    /**
     * On back pressed boolean.
     *
     * @return the boolean
     */
    public boolean onBackPressed() {
        return false;
    }


    /**
     * Log.
     *
     * @param message the message
     */
    protected void log(String message) {
        //AppLogger.log(this.getClass().getSimpleName() + " " + message);
    }


}
