package com.melolchik.bullshitbingo.ui.views;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.objects.BingoItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by melolchik on 21.01.2017.
 */

public class BingoCardView extends FrameLayout {

    public
    @BindView(R.id.bingo_image)
    UrlImageView mUrlImageView;

    public  @BindView(R.id.bingo_text)
    TextView mTextView;

    public BingoCardView(Context context) {
        this(context, null);
    }

    public BingoCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BingoCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_bingo_list_item, this);
        ButterKnife.bind(this,this);
        //setBackgroundColor(ContextCompat.getColor(context,R.color.color_transparent));

    }

   /* @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth = measure(widthMeasureSpec);
        int mHeight = measure(heightMeasureSpec);
        int size = Math.min(mWidth, mHeight);
        setMeasuredDimension(size, size);
    }

    private int measure(int measureSpec) {
        int result = 0;
        int specMoge = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMoge == MeasureSpec.UNSPECIFIED) result = 200;
        else result = specSize;
        return result;
    }*/

    public void bind(BingoItem item){
        if(item == null) return;
        mTextView.setText(item.getTitle());
        mUrlImageView.setUrl(item.getImageUrl());
        ViewCompat.setTransitionName(mUrlImageView,item.getShareImageName());
        ViewCompat.setTransitionName(mTextView,item.getShareTextName());
    }

    public void clear(){
        mTextView.setText("");
        mUrlImageView.setImageDrawable(null);
        ViewCompat.setTransitionName(mUrlImageView,"");
        ViewCompat.setTransitionName(mTextView,"");
    }
}
