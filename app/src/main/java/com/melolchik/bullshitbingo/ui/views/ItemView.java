package com.melolchik.bullshitbingo.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.objects.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by melolchik on 21.01.2017.
 */

public class ItemView extends FrameLayout {

    protected
    @BindView(R.id.image)
    UrlImageView mUrlImageView;

    protected @BindView(R.id.text)
    TextView mTextView;

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_list_item, this);
        ButterKnife.bind(this,this);
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

    public void bind(Item item){
        if(item == null) return;
        mTextView.setText(item.getTitle());
        mUrlImageView.setUrl(item.getImageUrl());
    }
}
