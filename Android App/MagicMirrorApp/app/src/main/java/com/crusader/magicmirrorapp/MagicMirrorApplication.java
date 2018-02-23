package com.crusader.magicmirrorapp;

import android.app.Application;

import com.crusader.magicmirrorapp.utikls.GeneralPreferenceHelper;

/**
 * Created by Sarthak on 23-Feb-18.
 */

public class MagicMirrorApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setupGeneralPreferenceHelper();
    }

    private void setupGeneralPreferenceHelper(){
        GeneralPreferenceHelper.getInstance().provideGeneralPreferenceHelper(this);
    }
}