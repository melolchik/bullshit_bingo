package com.melolchik.bullshitbingo.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;


import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.network.NetworkStateReceiver;
import com.melolchik.bullshitbingo.ui.fragments.BaseFragment;
import com.melolchik.bullshitbingo.utils.Util;

import java.util.List;


/**
 * Created by Olga Melekhina on 21.01.2017.
 */
public abstract class BaseActivity extends AppCompatActivity implements
        NetworkStateReceiver.NetworkStateReceiverListener {

    /**
     * The constant ID_FRAGMENT_CONTAINER.
     */
    public final static int ID_FRAGMENT_CONTAINER = R.id.fragment_container;

    private BroadcastReceiver mBroadcastReceiver;

    /**
     * The M permission manager.
     */
    //protected PermissionManager mPermissionManager = new PermissionManager();

    /**
     * Gets content view id.
     *
     * @return the content view id
     */
    protected abstract int getContentViewId();

    /**
     * The M network state receiver.
     */
    protected NetworkStateReceiver mNetworkStateReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        initBroadcastReceiver();
        mNetworkStateReceiver = new NetworkStateReceiver(this);

    }

    @Override
    public void networkAvailable() {


    }

    @Override
    public void networkUnavailable() {

    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mNetworkStateReceiver, mNetworkStateReceiver.getIntentFilter());

        IntentFilter filter = getBroadcastIntentFilter();
        if (filter != null) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, getBroadcastIntentFilter());
        }

        if (mNeedGoToPrevFragment) {
            if (TextUtils.isEmpty(mPrevFragmentTag)) {
                goToPrevFragmentIfExist();
            } else {
                goToPrevFragment(mPrevFragmentTag);
            }
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mNetworkStateReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }


    /**
     * Gets broadcast intent filter.
     *
     * @return the broadcast intent filter
     */
//region Broadcast Receiver
    protected IntentFilter getBroadcastIntentFilter() {
        return null;
    }

    private void initBroadcastReceiver() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                BaseActivity.this.onReceive(context, intent);
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
        log("onReceive");
    }
    //endregion

    //region HockeyApp

    //region Working with fragments

    /**
     * The M need go to prev fragment.
     */
    protected boolean mNeedGoToPrevFragment = false;
    /**
     * The M prev fragment tag.
     */
    protected String mPrevFragmentTag = "";

    /**
     * Go to prev fragment.
     *
     * @param tag the tag
     */
    public void goToPrevFragment(String tag) {
        //Util.hideSoftKeyboard(this);
        try {
            FragmentManager fm = getSupportFragmentManager();
            fm.popBackStack(tag, 0);
            fm.executePendingTransactions();
            mNeedGoToPrevFragment = false;
            mPrevFragmentTag = "";
        } catch (IllegalStateException ignored) {
            // There's no way to avoid getting this if saveInstanceState has already been called.
            log("IllegalStateException " + ignored);
            mNeedGoToPrevFragment = true;
            mPrevFragmentTag = tag;
        }
    }

    /**
     * Go to prev fragment if exist boolean.
     *
     * @return true, if prev fragment exist and went to prev, false, if prev fragment not exist and stay in present state
     */
    public boolean goToPrevFragmentIfExist() {
        //Util.hideSoftKeyboard(this);
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        // log("goToPrevFragmentIfExist count = " + count);
        if (count > 1) {
            try {
                fm.popBackStack();
                fm.executePendingTransactions();
                mNeedGoToPrevFragment = false;
                mPrevFragmentTag = "";
                return true;
            } catch (IllegalStateException ignored) {
                // There's no way to avoid getting this if saveInstanceState has already been called.
                log("IllegalStateException " + ignored);
                mNeedGoToPrevFragment = true;
                mPrevFragmentTag = "";
                return false;
            }


        }
        return false;
    }


    /**
     * Show fragment.
     *
     * @param newFragment       the new fragment
     * @param addToBackStack    the add to back stack
     * @param withFadeAnimation the with fade animation
     */
    public void showFragment(BaseFragment newFragment, boolean addToBackStack, boolean withFadeAnimation) {
        if (newFragment == null) return;
        FragmentManager fm = getSupportFragmentManager();
        // BaseFragment currentFragment = (BaseFragment) fm.findFragmentById(ID_FRAGMENT_CONTAINER);
        //log("changeFragment:currentFragment = " + currentFragment);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (withFadeAnimation) {
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        if (addToBackStack) {
            fragmentTransaction.add(ID_FRAGMENT_CONTAINER, newFragment);
            fragmentTransaction.addToBackStack(newFragment.getTagForStack());
        } else {
            fragmentTransaction.replace(ID_FRAGMENT_CONTAINER, newFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }


    /**
     * Gets current fragment.
     *
     * @return the current fragment
     */
    public BaseFragment getCurrentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(ID_FRAGMENT_CONTAINER);
        return (BaseFragment) currentFragment;
    }


    /**
     * Clean back stack.
     */
    protected void cleanBackStack() {
        try {
            FragmentManager fm = getSupportFragmentManager();
            fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fm.executePendingTransactions();
        } catch (IllegalStateException ignored) {
            // There's no way to avoid getting this if saveInstanceState has already been called.
            log("IllegalStateException " + ignored);

        }

    }
    //endregion

    @Override
    public void onBackPressed() {
        BaseFragment fragment = getCurrentFragment();
        if (fragment != null) {
            if (fragment.onBackPressed()) return;
        }
        if (goToPrevFragmentIfExist()) return;
        finish();
        super.onBackPressed();

    }

    /**
     * On home.
     */
    public void onHome() {
        Util.hideSoftKeyboard(this);
        if (goToPrevFragmentIfExist()) return;
    }


    /**
     * Log.
     *
     * @param message the message
     */
    protected void log(String message) {
        // AppLogger.log(this.getClass().getSimpleName() + " " + message);
    }


    //endregion PERMISSIONS
}
