package com.melolchik.bullshitbingo.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.melolchik.bullshitbingo.R;

import butterknife.BindView;

/**
 * Created by melolchik on 21.01.2017.
 */

public class FirstListFragment extends BaseFragmentWithToolbar {

    protected FirstListData mListData;

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
        mListData = new FirstListData(rootView);
        mListData.init();

    }
}
