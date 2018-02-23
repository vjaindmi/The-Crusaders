package com.crusader.magicmirrorapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.crusader.magicmirrorapp.R;
import com.crusader.magicmirrorapp.networkcallthreads.UserAndIPDetailsThread;
import com.crusader.magicmirrorapp.utikls.GeneralPreferenceHelper;

import static com.crusader.magicmirrorapp.utikls.Constants.INIT_DETAILS_CONTINUE;

/**
 * Created by Sarthak on 23-Feb-18.
 */

public class AskUserAndIPDetailsFragment extends BaseFragment {

    private TextInputEditText mTxtInptUsrName;
    private TextInputEditText mTxtInptAddress;
    private Button mButtonAccessCode;
    private View mLineView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_review_dialog_fragment, container, false);
        mTxtInptUsrName = (TextInputEditText) view.findViewById(R.id.edit_info_address);
        mTxtInptAddress = (TextInputEditText) view.findViewById(R.id.edit_info_address_line_2);
        mButtonAccessCode = (Button) view.findViewById(R.id.continue_button);
        mLineView = (View) view.findViewById(R.id.line_view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTxtInptUsrName.addTextChangedListener(this);
        mTxtInptAddress.addTextChangedListener(this);

        mTxtInptUsrName.setText(GeneralPreferenceHelper.getInstance().getUserName().toString().trim());
        mTxtInptAddress.setText(GeneralPreferenceHelper.getInstance().getIPAddress().toString().trim());
        mTxtInptUsrName.setSelection(mTxtInptUsrName.getText().length());
        mTxtInptAddress.setSelection(mTxtInptAddress.getText().length());

        mButtonAccessCode.setOnClickListener(this);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        super.afterTextChanged(editable);

        if (!mTxtInptUsrName.getText().toString().trim().isEmpty() && !mTxtInptAddress.getText().toString().trim().isEmpty()) {
            onCheckedChanged(true);
        } else {
            onCheckedChanged(false);
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == mButtonAccessCode.getId() && mLineView.getVisibility() == View.VISIBLE) {
            GeneralPreferenceHelper.getInstance().setUserName(mTxtInptUsrName.getText().toString().trim());
            GeneralPreferenceHelper.getInstance().setIPAddress(mTxtInptAddress.getText().toString().trim());

            UserAndIPDetailsThread userAndIPDetailsThread = new UserAndIPDetailsThread(this,mTxtInptAddress.getText().toString().trim(),mTxtInptUsrName.getText().toString().trim());
            userAndIPDetailsThread.provideReturn();
        }
    }

    public void onCheckedChanged(boolean runcalidation) {
        if (runcalidation) {
            mLineView.setVisibility(View.VISIBLE);
            mButtonAccessCode.setBackgroundResource(R.drawable.blue_button_selector);
        } else {
            mLineView.setVisibility(View.GONE);
            mButtonAccessCode.setBackgroundResource(R.color.gsk_grey);
        }
    }

    @Override
    public void returnCallBackFromThread(String returncallback, String ErrorMessage) {
        super.returnCallBackFromThread(returncallback,ErrorMessage);

        if (returncallback.equalsIgnoreCase("Fail")){
            Toast.makeText(getActivity(),"Error : " + ErrorMessage.trim(), Toast.LENGTH_SHORT).show();
        } else {
            sendMessageToActivity(INIT_DETAILS_CONTINUE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTxtInptUsrName.removeTextChangedListener(this);
        mTxtInptAddress.removeTextChangedListener(this);
        mButtonAccessCode.setOnClickListener(null);
        mTxtInptUsrName = null;
        mTxtInptAddress = null;
        mButtonAccessCode = null;
    }
}
