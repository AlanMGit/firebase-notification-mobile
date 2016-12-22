package com.firebasenotificationmobile.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Alan on 15/12/2016.
 */
public class Utils {

    public static void setToast(Context context, String mensagem) {

        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }

}
