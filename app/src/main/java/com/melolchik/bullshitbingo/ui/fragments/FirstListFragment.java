package com.melolchik.bullshitbingo.ui.fragments;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.objects.BingoItem;
import com.melolchik.bullshitbingo.ui.adapters.BingoItemListAdapter;
import com.melolchik.bullshitbingo.ui.views.BingoCardView;
import com.melolchik.bullshitbingo.utils.Util;

import static com.melolchik.bullshitbingo.ui.activities.BaseActivity.ID_FRAGMENT_CONTAINER;

/**
 * Created by melolchik on 21.01.2017.
 */
public class FirstListFragment extends BaseFragmentWithToolbar implements BingoItemListAdapter.OnItemClickListener {

    /**
     * The M list data.
     */
    protected FirstListData mListData;

    /**
     * Create instance first list fragment.
     *
     * @return the first list fragment
     */
    public static FirstListFragment createInstance() {
        FirstListFragment fragment = new FirstListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE_STRING_ID, R.string.app_name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_first_list;
    }

    @Override
    protected void onCreateView(View rootView, Bundle savedInstanceState) {
        super.onCreateView(rootView, savedInstanceState);
        log("onCreateView savedInstanceState = " + savedInstanceState);
        //if(savedInstanceState == null) {
        mListData = new FirstListData(rootView);
        mListData.init(this);
        //}

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        log("onConfigurationChanged newConfig = " + newConfig);
        if (mListData != null) {
            mListData.onConfigurationChanged(newConfig);
        }


    }

    @Override
    public void onItemClick(BingoCardView view, BingoItem item) {

        if (item != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && view != null) {
                showDetailsFragment(SecondDetailsFragment.createInstance(item), view);
            } else {
                showFragment(SecondDetailsFragment.createInstance(item), true, true);
            }
        }
    }

    /**
     * Show details fragment.
     *
     * @param newFragment   the new fragment
     * @param bingoCardView the bingo card view
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showDetailsFragment(BaseFragment newFragment, BingoCardView bingoCardView) {
        if (getActivity() == null) return;
        FragmentManager fm = getActivity().getSupportFragmentManager();
        // BaseFragment currentFragment = (BaseFragment) fm.findFragmentById(ID_FRAGMENT_CONTAINER);
        //log("changeFragment:currentFragment = " + currentFragment);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        Transition changeTransform = TransitionInflater.from(Util.getContext()).
                inflateTransition(R.transition.change_image_transform);

        /*int duration = 2000;
        Transition changeTransition2 = new TransitionSet().
                addTransition(new ChangeTransform().setDuration(duration))
                .addTransition(new ChangeImageTransform().setDuration(duration))
                .addTransition(new ChangeBounds().setDuration(duration));
        changeTransition2.setDuration(duration);*/
        Transition explodeTransform = TransitionInflater.from(Util.getContext()).
                inflateTransition(android.R.transition.fade);

        // Setup exit transition on first fragment
        this.setSharedElementReturnTransition(changeTransform);
        this.setExitTransition(explodeTransform);

        // Setup enter transition on second fragment
        newFragment.setSharedElementEnterTransition(changeTransform);
        newFragment.setEnterTransition(explodeTransform);

        // Find the shared element (in first fragment)
        // ImageView ivProfile = (ImageView) findViewById(R.id.ivProfile);
        ImageView imageView = bingoCardView.mUrlImageView;
        TextView textView = bingoCardView.mTextView;


        if (imageView != null) {

            fragmentTransaction.addSharedElement(imageView, imageView.getTransitionName());
        }
        if (textView != null) {

            fragmentTransaction.addSharedElement(textView, textView.getTransitionName());
        }
        fragmentTransaction.replace(ID_FRAGMENT_CONTAINER, newFragment);
        fragmentTransaction.addToBackStack(newFragment.getTagForStack());

        fragmentTransaction.commitAllowingStateLoss();
    }


}
