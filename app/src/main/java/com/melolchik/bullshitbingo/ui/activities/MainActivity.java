package com.melolchik.bullshitbingo.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.ui.fragments.FirstListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(FirstListFragment.createInstance(),true,false);
    }
}
