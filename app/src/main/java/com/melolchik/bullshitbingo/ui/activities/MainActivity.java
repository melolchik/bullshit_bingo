package com.melolchik.bullshitbingo.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.melolchik.bullshitbingo.R;
import com.melolchik.bullshitbingo.ui.fragments.FirstListFragment;

/**
 * The type Main activity.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            showFragment(FirstListFragment.createInstance(), true, false);
        }
    }
}
