package com.les4elefantastiq.les4elefantcowork.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Math on 06/08/16.
 */
public class SharedPreferencesManager {

    private static final String LINKEDINID = "LINKEDINID";

    public static void setLinkedInId(Context context, String linkedInId) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LINKEDINID, linkedInId);
        editor.commit();
    }

    public static String getLinkedInId(Context context) {
        return getSharedPreferences(context).getString(LINKEDINID, null);
    }


    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
    }
}
