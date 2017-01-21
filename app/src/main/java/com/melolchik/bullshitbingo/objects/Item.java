package com.melolchik.bullshitbingo.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * Created by melolchik on 21.01.2017.
 */

public class Item implements Parcelable {

    protected String mTitle;
    protected String mImageUrl;

    public static Item generateItem(int position) {
        Item item = new Item();
        item.setTitle("Item " + position);

        Random randomGenerator = new Random();
        int width = randomGenerator.nextInt(300) + 200;
        int height = randomGenerator.nextInt(300) + 200;
        String url = String.format("https://www.fillmurray.com/%d/%d", width, height);

        item.setImageUrl(url);

        return item;
    }

    private Item(){

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

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {

        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public Item(Parcel source) {
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
