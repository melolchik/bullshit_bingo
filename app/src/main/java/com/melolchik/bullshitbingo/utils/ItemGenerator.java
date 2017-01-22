package com.melolchik.bullshitbingo.utils;

import com.melolchik.bullshitbingo.objects.BingoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melolchik on 21.01.2017.
 */

public class ItemGenerator {
    public static final int PAGE_SIZE = 15;
    public static final int PAGE_COUNT = 5;

    private final int mPage;

    public static ItemGenerator createGenerator(){
        return new ItemGenerator(1);
    }

    private ItemGenerator(int page){
        mPage = page;
    }
    public boolean hasNext(){
        return mPage < PAGE_COUNT;
    }
    public ItemGenerator getNext(){
        if(hasNext())
            return new ItemGenerator(mPage + 1);
        return null;
    }

    public int getPage() {
        return mPage;
    }

    public int getFirstItemIndex(){
        return (mPage - 1) * PAGE_SIZE;
    }
    public List<BingoItem> generate(){
        List<BingoItem> list = new ArrayList<>();
        int position = getFirstItemIndex() + 1;
        for(int i = 0; i < PAGE_SIZE; i++){
            list.add(BingoItem.generateItem(position + i));
        }
        return list;
    }
}
