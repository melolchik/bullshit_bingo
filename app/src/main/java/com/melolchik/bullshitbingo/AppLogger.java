package com.melolchik.bullshitbingo;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Olga Melekhina on 02.06.2016.
 */
public class AppLogger {
    /**
     * The constant TAG.
     */
    public static final String TAG = "BINGO";
    public static final String TAG_FCM = "BINGO";


    /**
     * Log.
     *
     * @param text the text
     */
    public  static void log(String text){
        Log.d(TAG,"" + text);
    }

    public  static void log_fcm(String text){
         Log.d(TAG_FCM,"" + text);
    }


}
