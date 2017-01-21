package com.melolchik.bullshitbingo.ui.fragments;


import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.ui.activities.BaseActivity;

/**
 * Created by Olga Melekhina on 21.01.2017.
 */
public abstract class BaseFragmentWithToolbar extends BaseFragment implements Toolbar.OnMenuItemClickListener {

    /**
     * The constant ARG_TITLE_STRING.
     */
    public final static String ARG_TITLE_STRING = "arg_title";
    public final static String ARG_TITLE_STRING_ID = "arg_title_id";


    /**
     * The Id toolbar.
     */
    protected final int ID_TOOLBAR = R.id.toolbar;
    /**
     * The M toolbar.
     */
    protected Toolbar mToolbar;
    /**
     * The M toolbar title.
     */
    protected TextView mToolbarTitle;
    /**
     * The M title.
     */
    protected String mTitle;
    protected @StringRes
    int mTitleId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle args = getArguments();
        if (args != null) {
            mTitle = args.getString(ARG_TITLE_STRING);
            mTitleId = args.getInt(ARG_TITLE_STRING_ID,0);
        }
    }

    @Override
    protected void onCreateView(View rootView, Bundle savedInstanceState) {
        mToolbar = (Toolbar) rootView.findViewById(ID_TOOLBAR);
        if(mToolbar != null) {
            mToolbar.setTitle("");
            mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
            if(TextUtils.isEmpty(mTitle)){
                if(mTitleId > 0){
                    mTitle = getString(mTitleId);
                }
            }

            setTitle(mTitle);
            updateToolbarMenu();
        }
    }

    protected boolean hasToolbar(){
        return true;
    }

    //region Toolbar
    /**
     * Init toolbar menu.
     */
    protected void updateToolbarMenu() {
        if (mToolbar == null) return;
        if(!hasToolbar()){
            mToolbar.setVisibility(View.GONE);
            return;
        }

        if (getMenuResId() > 0) {
            mToolbar.getMenu().clear();
            mToolbar.inflateMenu(getMenuResId());
            mToolbar.setOnMenuItemClickListener(this);
        }else {
            mToolbar.getMenu().clear();
        }
        updateNavigationIcon();
    }

    protected void updateNavigationIcon(){
        if (mToolbar == null) return;
        if(!hasToolbar()){
            return;
        }
        if(getHomeUpIconId() > 0) {

            mToolbar.setNavigationIcon(getHomeUpIconId());
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationClick();
                }
            });
        }else {
            mToolbar.setNavigationIcon(null);
        }
    }

    /**
     * Gets menu res id.
     *
     * @return the menu res id
     */
    protected int getMenuResId() {
        return -1;
    }

    public boolean onMenuItemClick(MenuItem item) {
        return true;
    }

    protected void onNavigationClick(){
        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) getActivity();
            if (activity == null) return;
            activity.onHome();
        }
    }

    /**
     * Gets home up icon id.
     *
     * @return the home up icon id
     */
    protected @DrawableRes
    int getHomeUpIconId() {
        return /*R.drawable.btn_backarrow_white*/ 0;
    }

    /**
     * Sets title.
     *
     * @param resID the res id
     */
    protected void setTitle(int resID) {
        if (mToolbarTitle == null) return;
        mToolbarTitle.setText(resID);
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    protected void setTitle(String title) {
        if (mToolbarTitle == null || title == null) return;
        mToolbarTitle.setText(title);
    }

     /**
     * Gets title.
     *
     * @return the title
     */
    protected String getTitle() {
        return (mToolbarTitle == null) ? "" : mToolbarTitle.getText().toString();
    }
    //endregion


}
