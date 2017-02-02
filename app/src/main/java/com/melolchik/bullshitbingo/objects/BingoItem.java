package com.melolchik.bullshitbingo.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * Created by melolchik on 21.01.2017.
 */
public class BingoItem implements Parcelable {

    /**
     * The M title.
     */
    protected String mTitle;
    /**
     * The M image url.
     */
    protected String mImageUrl;

    /**
     * Generate item bingo item.
     *
     * @param position the position
     * @return the bingo item
     */
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

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Sets title.
     *
     * @param mTitle the m title
     */
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageUrl() {
        return mImageUrl;
    }

    /**
     * Sets image url.
     *
     * @param imageUrl the image url
     */
    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    /**
     * Get share image name string.
     *
     * @return the string
     */
    public String getShareImageName(){
        return getTitle() + "image";
    }

    /**
     * Get share text name string.
     *
     * @return the string
     */
    public String getShareTextName(){
        return getTitle() + "text";
    }

    //region Parcelable

    /**
     * The constant CREATOR.
     */
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

    /**
     * Instantiates a new Bingo item.
     *
     * @param source the source
     */
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
