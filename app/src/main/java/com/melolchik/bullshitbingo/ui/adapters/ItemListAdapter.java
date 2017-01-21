package com.melolchik.bullshitbingo.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.melolchik.bullshitbingo.objects.Item;
import com.melolchik.bullshitbingo.ui.views.ItemView;

/**
 * Created by melolchik on 21.01.2017.
 */

public class ItemListAdapter extends BaseListAdapter<Item> {

    protected OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(Item item);
    }

    public ItemListAdapter(RecyclerView recyclerView){
        super(recyclerView);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemView item = new ItemView(getContext());
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

        private final ItemView mItemView;
        public ItemHolder(ItemView itemView){
            super(itemView);
            mItemView = itemView;
        }

        public void setData(Item item){
            mItemView.bind(item);
            mItemView.setClickable(true);
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mOnItemClickListener != null){
                        int position = mRecyclerView.getChildAdapterPosition(mItemView);
                        mOnItemClickListener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
}
