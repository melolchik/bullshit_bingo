package com.melolchik.bullshitbingo.utils;

import com.melolchik.bullshitbingo.objects.BingoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melolchik on 21.01.2017.
 */
public class ItemGenerator {
    /**
     * The constant PAGE_SIZE.
     */
    public static final int PAGE_SIZE = 15;
    /**
     * The constant PAGE_COUNT.
     */
    public static final int PAGE_COUNT = 5;

    private final int mPage;

    /**
     * Create generator item generator.
     *
     * @return the item generator
     */
    public static ItemGenerator createGenerator(){
        return new ItemGenerator(1);
    }

    private ItemGenerator(int page){
        mPage = page;
    }

    /**
     * Has next boolean.
     *
     * @return the boolean
     */
    public boolean hasNext(){
        return mPage < PAGE_COUNT;
    }

    /**
     * Get next item generator.
     *
     * @return the item generator
     */
    public ItemGenerator getNext(){
        if(hasNext())
            return new ItemGenerator(mPage + 1);
        return null;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public int getPage() {
        return mPage;
    }

    /**
     * Get first item index int.
     *
     * @return the int
     */
    public int getFirstItemIndex(){
        return (mPage - 1) * PAGE_SIZE;
    }

    /**
     * Generate list.
     *
     * @return the list
     */
    public List<BingoItem> generate(){
        List<BingoItem> list = new ArrayList<>();
        int position = getFirstItemIndex() + 1;
        for(int i = 0; i < PAGE_SIZE; i++){
            list.add(BingoItem.generateItem(position + i));
        }
        return list;
    }
}
