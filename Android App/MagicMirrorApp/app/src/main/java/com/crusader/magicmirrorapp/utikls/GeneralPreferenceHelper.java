package com.crusader.magicmirrorapp.utikls;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sarthak on 23-Feb-18.
 */

public class GeneralPreferenceHelper {

    private static GeneralPreferenceHelper mGeneralPreferenceHelper;
    private Context context;
    private SharedPreferences mSharedPreferences;

    public void provideGeneralPreferenceHelper(Context kGe) {
        context = kGe;
        this.mSharedPreferences = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
    }

    public static GeneralPreferenceHelper getInstance() {
        if (mGeneralPreferenceHelper == null){
            mGeneralPreferenceHelper = new GeneralPreferenceHelper();
        }
        return mGeneralPreferenceHelper;
    }

    public void setUserName(String UserName) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("KEY_USER_NAME", UserName);
        editor.commit();
    }

    public String getUserName() {
        return mSharedPreferences.getString("KEY_USER_NAME", "");
    }

    public void setIPAddress(String UserName) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("KEY_IP_ADDRESS", UserName);
        editor.commit();
    }

    public String getIPAddress() {
        return mSharedPreferences.getString("KEY_IP_ADDRESS", "");
    }
}
