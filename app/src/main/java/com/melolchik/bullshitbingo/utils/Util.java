package com.melolchik.bullshitbingo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.StringRes;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by Olga Melekhina on 01.07.2016.
 */
public class Util {

    public static Context mContext;

    public static void init(Context context){
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }

    public static String notEmptyString(String origin) {
        return origin == null ? "" : origin.trim();
    }

    public static float convertDpToPixel(float dp) {
        if(mContext == null) return 0f;
        Resources resources = mContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public  static boolean isEmailValid(String email){
        boolean valid = true;

        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            valid = false;
        }

        return valid;

    }


    public static boolean isNetworkAvailable() {
        if (mContext == null)
            return false;
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null && activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager
                    = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void showSoftKeyboard(Activity activity) {
        if (activity != null/* && activity.getCurrentFocus() != null*/) {
            InputMethodManager inputMethodManager
                    = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    public static int getScreenWidth() {
        if (mContext == null) return 0;
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        if (mContext == null) return 0;
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getStatusBarHeight() {
        if(mContext == null) return 0;
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getActionBarHeight(Context context) {
       /* TypedValue tv = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);*/
        //return context.getResources().getDimensionPixelSize(R.dimen.height_action_bar);
        return 0;

    }

    public static int getFragmentHeight() {

        return getScreenHeight() - getStatusBarHeight();
    }

    public static String getUniqueId(Context context) {
        /*TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();*/
        final String androidId = Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }
    /**
     * Show error toast.
     *
     * @param message the message
     */
    public static void showErrorToast(String message) {
        if(mContext == null) return;
        Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Show error toast.
     *
     * @param messageResId the message res id
     */
    public static void showErrorToast(@StringRes int messageResId) {
        if(mContext == null) return;
        Toast toast = Toast.makeText(mContext, messageResId, Toast.LENGTH_LONG);
        toast.show();
    }
    protected static void log(String message) {
        //AppLogger.log(Util.class.getSimpleName() + " " + message);
    }

}
