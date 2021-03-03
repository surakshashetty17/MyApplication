package com.example.myapplication.Common;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constants
{

    //Email Validation pattern
    public static final String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

    //Mobile validation pattern
    public static final String mobregEx = "^(\\+91[\\-\\s]?)?[0]?(91)?[0123456789]\\d{9}$";

    public static final String nameEx="[A-Za-z]";

    public static boolean isOnline(Context applicationContext) {

        ConnectivityManager cm = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        else {
            return false;
        }

    }

}
