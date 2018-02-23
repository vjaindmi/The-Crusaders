package com.crusader.magicmirrorapp.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.crusader.magicmirrorapp.R;
import com.crusader.magicmirrorapp.fragments.AskUserAndIPDetailsFragment;

import static com.crusader.magicmirrorapp.utikls.Constants.SHOULD_ADD_TO_BACK_STACK;
import static com.crusader.magicmirrorapp.utikls.Constants.SHOULD_REPLACE;

/**
 * Created by Sarthak on 23-Feb-18.
 */

public class MainContentActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        initUserAndIPDetails();
    }

    private void initUserAndIPDetails() {
        if (!checkPopUpState(AskUserAndIPDetailsFragment.class)) {
            showFragment(new AskUserAndIPDetailsFragment(), SHOULD_REPLACE, SHOULD_ADD_TO_BACK_STACK);
        }
    }

    protected boolean checkPopUpState(Class className) {
        String backStateName = className.getName();
        String fragmentTag = backStateName;

        boolean fragmentPopped = getCurrentFragmentManager().popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped && getCurrentFragmentManager().findFragmentByTag(fragmentTag) == null) {
            return false;
        }
        return true;
    }

    protected void showFragment(Fragment fragment, boolean shouldreplace, boolean shouldAddToBackStack) {

        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentTransaction ft = getCurrentFragmentManager().beginTransaction();
        if (shouldreplace) {
            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.view_content, fragment, fragmentTag);
        } else {
            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).add(R.id.view_content, fragment, fragmentTag);
        }
        if (shouldAddToBackStack) {
            ft.addToBackStack(backStateName);
        }
        ft.commit();
    }

    private FragmentManager getCurrentFragmentManager() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        return mFragmentManager;
    }

    public Handler getActivityHandler() {
        return handler;
    }

    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onMessageFromFragment(msg);

        }
    };

    protected void onMessageFromFragment(Message msg) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragmentManager = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager childFm = getCurrentFragmentManager();
        if (childFm.getBackStackEntryCount() > 1) {
            childFm.popBackStackImmediate();
        } else {
            finish();
        }
    }
}
