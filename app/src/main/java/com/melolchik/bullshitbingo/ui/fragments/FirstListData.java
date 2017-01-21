package com.melolchik.bullshitbingo.ui.fragments;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.ui.adapters.ItemListAdapter;
import com.melolchik.bullshitbingo.utils.ItemGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by melolchik on 21.01.2017.
 */

public class FirstListData {

    protected
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    protected final Context mContext;
    protected ItemGenerator mItemGenerator;

    private LinearLayoutManager mLinearLayoutManager;
    protected ItemListAdapter mAdapter;


    public FirstListData(View rootView){

        ButterKnife.bind(this,rootView);
        mContext = rootView.getContext();
        mItemGenerator = ItemGenerator.createGenerator();
    }

    public Context getContext() {
        return mContext;
    }

    public void init(){
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new ItemListAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setData(mItemGenerator.generate());

    }
}
