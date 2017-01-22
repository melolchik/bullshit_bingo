package com.melolchik.bullshitbingo.ui.fragments;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.objects.BingoItem;
import com.melolchik.bullshitbingo.ui.adapters.BingoItemListAdapter;
import com.melolchik.bullshitbingo.utils.ItemGenerator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by melolchik on 21.01.2017.
 */

public class FirstListData implements SwipeRefreshLayout.OnRefreshListener{

    protected
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    protected
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mRefreshLayout;

    protected final Context mContext;
    protected static ItemGenerator mItemGenerator;
    protected static List<BingoItem> mItemList;

    private LinearLayoutManager mLinearLayoutManager;
    protected BingoItemListAdapter mAdapter;


    public FirstListData(View rootView){

        ButterKnife.bind(this,rootView);
        mContext = rootView.getContext();
        mItemGenerator = ItemGenerator.createGenerator();
    }

    public Context getContext() {
        return mContext;
    }

    public void init(BingoItemListAdapter.OnItemClickListener listener){
        mLinearLayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new BingoItemListAdapter(mRecyclerView);
        mAdapter.setOnItemClickListener(listener);
        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.addItemDecoration(new AlignmentItemDecoration(10,10));

        if(mItemList == null) {
            mItemList = mItemGenerator.generate();
        }
        mAdapter.setData(mItemList);
        mRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        mItemGenerator = ItemGenerator.createGenerator();
        mItemList = mItemGenerator.generate();
        mAdapter.setData(mItemList);
        mRefreshLayout.setRefreshing(false);
    }
}
