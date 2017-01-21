package com.melolchik.bullshitbingo.ui.adapters.decorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.melolchik.bullshitbingo.utils.Util;

/**
 * Created by melolchik on 23.03.2016.
 */
public class AlignmentItemDecoration extends RecyclerView.ItemDecoration {

    protected int mLeftPadding = 10;
    protected int mTopButton = 10;

    public AlignmentItemDecoration() {
    }

    public AlignmentItemDecoration(int leftPadding, int topPadding) {
        mLeftPadding = leftPadding;
        mTopButton = topPadding;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final int parentWidth = parent.getWidth();
        int leftPadding = (int) Util.convertDpToPixel(mLeftPadding);
        int topPadding = (int) Util.convertDpToPixel(mTopButton);
        outRect.left = leftPadding;
        outRect.right = leftPadding;
        outRect.top = topPadding;
        outRect.bottom = 0;



        int count = parent.getAdapter().getItemCount();
        if(count > 0){
            int tag = -1;
            if(view.getTag() != null){
                tag = (Integer) view.getTag();
                if(tag == count - 1){
                    outRect.bottom = topPadding;
                }
            }
            if(view instanceof TextView){
                outRect.left = 0;
                outRect.right = 0;
                if(tag == 0){
                    outRect.top = 0;
                }
            }

        }

    }
}