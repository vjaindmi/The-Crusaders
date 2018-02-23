package com.crusader.magicmirrorapp.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.crusader.magicmirrorapp.networkcallthreads.IncomingMessageThread;
import com.crusader.magicmirrorapp.utikls.GeneralPreferenceHelper;

/**
 * Created by Sarthak on 23-Feb-18.
 */

public class SmsListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();

                        IncomingMessageThread incomingMessageThread = new IncomingMessageThread(msg_from, GeneralPreferenceHelper.getInstance().getUserName(),msgBody);
                        incomingMessageThread.provideReturn();
                    }
                }catch(Exception e){
//                            Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }
}
