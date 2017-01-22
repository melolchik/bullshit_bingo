package com.melolchik.bullshitbingo.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.melolchik.bullshitbingo.objects.BingoItem;
import com.melolchik.bullshitbingo.ui.views.BingoCardView;

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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BingoCardView item = new BingoCardView(getContext());
        item.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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
