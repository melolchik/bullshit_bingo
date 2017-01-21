package com.melolchik.bullshitbingo.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.melolchik.bullshitbingo.AppLogger;
import com.melolchik.bullshitbingo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Olga on 14.11.2016.
 */

public class UrlImageView extends ImageView {
    /**
     * The M max width.
     */
    protected int mMaxWidth = 0;
    /**
     * The M max height.
     */
    protected int mMaxHeight = 0;

    protected ImageLoader mImageLoader;

    public UrlImageView(Context context) {
        this(context, null);
    }

    public UrlImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UrlImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.UrlImageView,
                0, 0);
        try {
            mMaxHeight = array.getDimensionPixelSize(R.styleable.UrlImageView_maxHeight, 0);
            mMaxWidth = array.getDimensionPixelSize(R.styleable.UrlImageView_maxWidth, 0);
        } finally {
            array.recycle();
        }

        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getMaxWidth() {
        return mMaxWidth;
    }

    @Override
    public int getMaxHeight() {
        return mMaxHeight;
    }

    public void setUrl(String url) {
        log("setUrl = " + url);
        BitmapFactory.Options bm_options = new BitmapFactory.Options();
        bm_options.inJustDecodeBounds = true;


        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // .showImageOnLoading(placeholderID)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(false)

                .build();
        try {
            mImageLoader.displayImage(url, this, options, null);
            // isLoaded = true;
        } catch (Exception ex) {
            //isLoaded = false;
            log(" Exception = " + ex);
        }
    }

    protected void log(String message) {
        AppLogger.log(getClass().getSimpleName() + " " + message);
    }
}
