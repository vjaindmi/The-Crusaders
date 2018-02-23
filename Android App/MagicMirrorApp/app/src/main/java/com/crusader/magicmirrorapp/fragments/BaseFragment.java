package com.crusader.magicmirrorapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.crusader.magicmirrorapp.activities.MainContentActivity;

/**
 * Created by Sarthak on 23-Feb-18.
 */

public class BaseFragment extends Fragment implements TextWatcher, View.OnClickListener {
    private boolean isPostSavedInstance = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPostSavedInstance = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        isPostSavedInstance = false;
    }

    @Override
    public void onDestroyView() {
        System.gc();
        super.onDestroyView();
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {

    }

    protected void sendMessageToActivity(int what) {
        try {
            Message message = Message.obtain();
            message.what = what;
            ((MainContentActivity) getActivity()).getActivityHandler().sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnCallBackFromThread(String returncallback,String ErrorMessage){

    }
}
