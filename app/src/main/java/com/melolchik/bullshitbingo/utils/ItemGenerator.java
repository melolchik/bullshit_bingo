package com.melolchik.bullshitbingo.utils;

import com.melolchik.bullshitbingo.objects.Item;

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

    public List<Item> generate(){
        List<Item> list = new ArrayList<>();
        int position = (mPage - 1) * PAGE_SIZE + 1;
        for(int i = 0; i < PAGE_SIZE; i++){
            list.add(Item.generateItem(position + i));
        }
        return list;
    }
}
