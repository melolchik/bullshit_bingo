package com.melolchik.bullshitbingo.ui.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.objects.BingoItem;
import com.melolchik.bullshitbingo.ui.adapters.BingoItemListAdapter;
import com.melolchik.bullshitbingo.ui.views.FixedStaggeredGridLayoutManager;
import com.melolchik.bullshitbingo.ui.views.swipe.BottomSwipeRefreshLayout;
import com.melolchik.bullshitbingo.utils.ItemGenerator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by melolchik on 21.01.2017.
 */

public class FirstListData implements BottomSwipeRefreshLayout.OnRefreshListener{

    protected final static int SPAN_COUNT = 2;
    protected final static int PORTRAIT_SPAN_COUNT = 1;
    protected final static int LANDSCAPE_SPAN_COUNT = 4;

    protected
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    protected
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mRefreshLayout;

    protected
    @BindView(R.id.load_more_swipe_layout)
    BottomSwipeRefreshLayout mLoadMoreSwipeLayout;

    protected final Context mContext;
    protected static ItemGenerator mItemGenerator;
    protected static List<BingoItem> mItemList;

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
        StaggeredGridLayoutManager layoutManager = new FixedStaggeredGridLayoutManager(SPAN_COUNT,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new BingoItemListAdapter(mRecyclerView);
        mAdapter.setOnItemClickListener(listener);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.addItemDecoration(new AlignmentItemDecoration(10,10));

        if(mItemList == null) {
            mItemList = mItemGenerator.generate();
        }
        mAdapter.setData(mItemList);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mItemGenerator = ItemGenerator.createGenerator();
                mItemList = mItemGenerator.generate();
                mAdapter.setData(mItemList);
                mRefreshLayout.setRefreshing(false);
                mLoadMoreSwipeLayout.setEnabled(true);
            }
        });
        mLoadMoreSwipeLayout.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        if(mItemGenerator == null){
            mItemGenerator = ItemGenerator.createGenerator();
        }else {
            if(mItemGenerator.hasNext()){
                mItemGenerator = mItemGenerator.getNext();
            }else {
                mLoadMoreSwipeLayout.setEnabled(false);
                mItemGenerator = null;
            }
        }
        if(mItemGenerator != null){
            mItemList.addAll(mItemGenerator.generate());
            mAdapter.notifyItemRangeInserted(mItemGenerator.getFirstItemIndex(),ItemGenerator.PAGE_SIZE);
        }
        mLoadMoreSwipeLayout.setRefreshing(false);
    }


    public void onConfigurationChanged(Configuration newConfig) {

      /*  ((GridAutoLayoutManager)mRecyclerView.getLayoutManager()).onConfigurationChanged(newConfig);*/
        mRecyclerView.requestLayout();
        mAdapter.notifyDataSetChanged();
        mRecyclerView.invalidateItemDecorations();

    }


}
