package com.crusader.magicmirrorapp.networkcallthreads;

import android.os.Handler;
import android.util.Log;

import com.crusader.magicmirrorapp.fragments.BaseFragment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sarthak on 23-Feb-18.
 */

public class UserAndIPDetailsThread {

    private BaseFragment mBaseFragment;
    private String mIpAddress;
    private String mUserName;
    private String murlrequest = "http://172.20.10.10:8080/remote?action=NOTIFICATION&notification=SHOW_ALERT&payload={%22title%22:%22Connected%22,%22message%22:%22Hi%20Sarthak%20....%22}";
//    private String mpayload = "{%22title%22:%22Alert%22,%22message%22:%22Hi%20Sarthak%20....%22}";
    private String mHideUrlrequet = "http://172.20.10.10:8080/remote?action=NOTIFICATION&notification=HIDE_ALERT";
    public UserAndIPDetailsThread(BaseFragment mBaseFragment, String mIpAddress, String mUserName) {
        this.mBaseFragment = mBaseFragment;
        this.mIpAddress = mIpAddress;
        this.mUserName = mUserName;
    }

    public void provideReturn(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                murlrequest = murlrequest.replace("Connected", "Connected");
                murlrequest = murlrequest.replace("Sarthak", mUserName.toString());
                try {
                    threadPart(murlrequest);
                } catch (final Exception e) {
                    e.printStackTrace();
                    mBaseFragment.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mBaseFragment.returnCallBackFromThread("fail",e.getMessage());
                        }
                    });
                }
            }
        }).start();
    }

    public void hideReturn(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    threadPart(mHideUrlrequet);
                } catch (final Exception e) {
                    e.printStackTrace();
                    mBaseFragment.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mBaseFragment.returnCallBackFromThread("fail",e.getMessage());
                        }
                    });
                }
            }
        }).start();
    }

    public void threadPart(String sUrl) throws Exception {
        URL url = new URL(sUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(false);
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.connect();
        int responseStatusCode = connection.getResponseCode();
        BufferedReader rd = null;
        if( responseStatusCode != HttpURLConnection.HTTP_OK ) {
            Log.d("UserAndIPDetailsThread", "NOT OK");
            rd = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            //Get more informations about the problem
        } else {
            Log.d("UserAndIPDetailsThread", "ITS OK");
            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }

        String content = "",line;

        while ((line = rd.readLine()) != null) {
            content += line + "\n";
        }
        Log.d("UserAndIPDetailsThread", content.toString());
        final String finalContent = content;
        mBaseFragment.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBaseFragment.returnCallBackFromThread( finalContent.toString(),"");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideReturn();
                    }
                },6000);
            }
        });
    }
}