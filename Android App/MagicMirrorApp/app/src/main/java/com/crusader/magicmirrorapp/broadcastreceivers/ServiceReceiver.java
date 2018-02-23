package com.crusader.magicmirrorapp.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.crusader.magicmirrorapp.networkcallthreads.IncomingCallsThread;
import com.crusader.magicmirrorapp.utikls.GeneralPreferenceHelper;

import java.util.Calendar;

/**
 * Created by Sarthak on 23-Feb-18.
 */

public class ServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        // Get the current Phone State
        boolean ring = true;
        boolean callReceived = false;


        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if(state==null){
            return;
        }

        Bundle bundle = intent.getExtras();
        String number= bundle.getString("incoming_number");
        Calendar calendar=Calendar.getInstance();
        long currentTimeStamp=calendar.getTimeInMillis();
        // If phone state "Rininging"
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {
            ring =true;
            // Get the Caller's Phone Number

        }



        // If incoming call is received
        if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
        {
            callReceived=true;
        }


        // If phone is Idle
        if (state.equals(TelephonyManager.EXTRA_STATE_IDLE))
        {
            // If phone was ringing(ring=true) and not received(callReceived=false) , then it is a missed call
            if(ring==true&&callReceived==false)
            {
                IncomingCallsThread incomingCallsThread = new IncomingCallsThread(number,GeneralPreferenceHelper.getInstance().getUserName());
                incomingCallsThread.provideReturn();
                //workingWithFunctions();
                ring=false;
            }
            callReceived=false;
        }
    }
}
