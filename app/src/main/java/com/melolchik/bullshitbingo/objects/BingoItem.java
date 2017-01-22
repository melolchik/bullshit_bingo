package com.melolchik.bullshitbingo.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * Created by melolchik on 21.01.2017.
 */

public class BingoItem implements Parcelable {

    protected String mTitle;
    protected String mImageUrl;

    public static BingoItem generateItem(int position) {
        BingoItem item = new BingoItem();
        item.setTitle("BingoItem " + position);

        Random randomGenerator = new Random();
        int width = randomGenerator.nextInt(300) + 200;
        int height = randomGenerator.nextInt(300) + 200;
        String url = String.format("https://www.fillmurray.com/%d/%d", width, height);

        item.setImageUrl(url);

        return item;
    }

    private BingoItem(){

    }
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    //region Parcelable

    public static final Parcelable.Creator<BingoItem> CREATOR = new Parcelable.Creator<BingoItem>() {

        @Override
        public BingoItem createFromParcel(Parcel source) {
            return new BingoItem(source);
        }

        @Override
        public BingoItem[] newArray(int size) {
            return new BingoItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public BingoItem(Parcel source) {
        setTitle(source.readString());
        setImageUrl(source.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(getTitle());
        dest.writeString(getImageUrl());

    }
    //endregion
}
