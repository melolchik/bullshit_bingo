package com.melolchik.bullshitbingo.ui.fragments;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.melolchik.bullshitbingo.AppLogger;
import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.objects.BingoItem;
import com.melolchik.bullshitbingo.ui.views.UrlImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by melolchik on 21.01.2017.
 */
public class SecondDetailsFragment extends BaseFragmentWithToolbar {

    /**
     * The constant ARG_ITEM.
     */
    protected final static String ARG_ITEM = SecondDetailsFragment.class.getCanonicalName() + "ARG_ITEM";

    /**
     * The M item.
     */
    protected BingoItem mItem;

    protected View mRoot;

    /**
     * The M url image view.
     */
    protected
    @BindView(R.id.bingo_image)
    UrlImageView mUrlImageView;

    protected
    @BindView(R.id.bingo_text)
    TextView mTextView;

    /**
     * Create instance second details fragment.
     *
     * @param item the item
     * @return the second details fragment
     */
    public static SecondDetailsFragment createInstance(BingoItem item) {
        SecondDetailsFragment fragment = new SecondDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE_STRING, item != null ? item.getTitle() : "");
        args.putParcelable(ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_second_details;
    }

    @Override
    protected int getHomeUpIconId() {
        return R.drawable.icn_arrow_back;
    }

    @Override
    protected int getMenuResId() {
        return R.menu.menu_details_screen;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.menu_rotate){
            rotate();
        }
        return super.onMenuItemClick(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mItem = args.getParcelable(ARG_ITEM);
        }
    }

    @Override
    protected void onCreateView(View rootView, Bundle savedInstanceState) {
        super.onCreateView(rootView, savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(mToolbarTitle,getString(R.string.item_text_transition_name));
        }
        mRoot = rootView;
        ButterKnife.bind(this,rootView);
        if(mItem != null){
            mUrlImageView.setUrl(mItem.getImageUrl());
            mTextView.setVisibility(View.GONE);
        }
    }

    protected void rotate(){
        startRotateAnimation();
    }

    protected ValueAnimator mRotateAnimator = null;

    protected void startRotateAnimation() {
        if(mRotateAnimator!= null && mRotateAnimator.isRunning()) return;

        mRotateAnimator = new ValueAnimator();
        mRotateAnimator.setInterpolator(new android.view.animation.LinearInterpolator());
        mRotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //if (!mIsDetectionState) {
                int currentValue = (Integer) animation.getAnimatedValue();
                //log("currentValue = " + currentValue);
                //float currentAngleRadians = (float) Math.toRadians(currentValue);
                ViewCompat.setRotation(mUrlImageView, currentValue);

            }
        });

        int start = 0;
        int end = 720;
        mRotateAnimator.setIntValues(start, end);
        mRotateAnimator.setDuration(2000);
        mRotateAnimator.start();
    }

    protected void stopRotateAnimation() {
        if (mRotateAnimator != null) {
            mRotateAnimator.cancel();
            mRotateAnimator = null;
        }
    }

    protected void log(String message) {
        AppLogger.log(this.getClass().getSimpleName() + " " + message);
    }

}
