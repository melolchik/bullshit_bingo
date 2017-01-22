package com.melolchik.bullshitbingo.ui.adapters;

import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.melolchik.bullshitbingo.objects.BingoItem;
import com.melolchik.bullshitbingo.ui.views.BingoCardView;
import com.melolchik.bullshitbingo.utils.Util;

/**
 * Created by melolchik on 21.01.2017.
 */

public class BingoItemListAdapter extends BaseListAdapter<BingoItem> {

    protected OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(BingoCardView view,BingoItem item);
    }

    public BingoItemListAdapter(RecyclerView recyclerView){
        super(recyclerView);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    protected int getSpanCount(){
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BingoCardView item = new BingoCardView(getContext());
        int width = Util.getScreenWidth();
        int height = Util.getScreenHeight();
        int currentWidth = Util.getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT ? Math.min(width,height) : Math.max(width,height);
        currentWidth /= getSpanCount();
        item.setLayoutParams(new RecyclerView.LayoutParams(currentWidth, currentWidth));
        item.requestLayout();
        return new ItemHolder(item);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof  ItemHolder){
            ((ItemHolder) holder).setData(getItem(position));
        }
    }

    protected class ItemHolder extends RecyclerView.ViewHolder{

        private final BingoCardView mItemView;
        public ItemHolder(BingoCardView itemView){
            super(itemView);
            mItemView = itemView;
        }

        public void setData(BingoItem item){
            mItemView.clear();
            mItemView.bind(item);
            mItemView.setClickable(true);
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mOnItemClickListener != null){
                        int position = mRecyclerView.getChildAdapterPosition(mItemView);
                        mOnItemClickListener.onItemClick(mItemView,getItem(position));
                    }
                }
            });
        }
    }
}
