package com.melolchik.bullshitbingo;

import android.app.Application;

import com.melolchik.bullshitbingo.utils.Util;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by melolchik on 21.01.2017.
 */

public class BingoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Util.init(getApplicationContext());
        initImageLoader();
    }

    protected void initImageLoader() {
        File cacheDir = StorageUtils.getIndividualCacheDirectory(this, "BingoPhoto");

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory());

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;
        //final int cacheSize = 2 * 1024 * 1024;

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800) // width, height
                .diskCacheExtraOptions(Util.getScreenWidth(), Util.getScreenHeight(), null)
                .threadPoolSize(5)
                .threadPriority(Thread.MIN_PRIORITY + 2)
                .denyCacheImageMultipleSizesInMemory()


                .memoryCache(new UsingFreqLimitedMemoryCache(cacheSize)) // 2 Mb
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .diskCacheFileCount(1000)
                .diskCacheSize(50 * 1024 * 1024)//50Mb
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .imageDownloader(new BaseImageDownloader(getApplicationContext()))
                .imageDecoder(new BaseImageDecoder(true))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }
}
