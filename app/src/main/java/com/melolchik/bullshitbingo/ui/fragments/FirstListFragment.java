package com.melolchik.bullshitbingo.ui.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.objects.Item;
import com.melolchik.bullshitbingo.ui.adapters.ItemListAdapter;

import butterknife.BindView;

/**
 * Created by melolchik on 21.01.2017.
 */

public class FirstListFragment extends BaseFragmentWithToolbar implements ItemListAdapter.OnItemClickListener {

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
        //if(savedInstanceState == null) {
            mListData = new FirstListData(rootView);
            mListData.init(this);
        //}

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    @Override
    public void onItemClick(Item item) {
        if(item != null){
            showFragment(SecondDetailsFragment.createInstance(item),true,true);
        }
    }
}
