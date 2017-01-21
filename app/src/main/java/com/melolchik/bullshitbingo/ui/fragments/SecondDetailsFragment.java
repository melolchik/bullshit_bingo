package com.melolchik.bullshitbingo.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.objects.Item;
import com.melolchik.bullshitbingo.ui.views.UrlImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by melolchik on 21.01.2017.
 */

public class SecondDetailsFragment extends BaseFragmentWithToolbar {

    protected final static String ARG_ITEM = SecondDetailsFragment.class.getCanonicalName() + "ARG_ITEM";

    protected Item mItem;

    protected
    @BindView(R.id.image)
    UrlImageView mUrlImageView;

    public static SecondDetailsFragment createInstance(Item item) {
        SecondDetailsFragment fragment = new SecondDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE_STRING, item != null ? item.getTitle() : "");
        args.putParcelable(ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_second_details;
    }

    @Override
    protected int getHomeUpIconId() {
        return R.drawable.icn_arrow_back;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mItem = args.getParcelable(ARG_ITEM);
        }
    }

    @Override
    protected void onCreateView(View rootView, Bundle savedInstanceState) {
        super.onCreateView(rootView, savedInstanceState);
        ButterKnife.bind(this,rootView);
        if(mItem != null){
            mUrlImageView.setUrl(mItem.getImageUrl());
        }
    }
}
