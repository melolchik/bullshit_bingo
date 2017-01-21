package com.melolchik.bullshitbingo.objects;

import java.util.Random;

/**
 * Created by melolchik on 21.01.2017.
 */

public class Item {

    protected String mTitle;
    protected String mImageUrl;

    public static Item generateItem(int position){
        Item item = new Item();
        item.setTitle("Item " + position);

        Random randomGenerator = new Random();
        int width = randomGenerator.nextInt(300) + 200;
        int height = randomGenerator.nextInt(300) + 200;
        String url = String.format("https://www.fillmurray.com/%d/%d",width,height);

        item.setImageUrl(url);

        return item;
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
}
