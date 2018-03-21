package com.besieged.ktreader.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/21.
 */

public class CommonUtils {

    private static Toast mToast;

    public static void ShowTips(Context context, String tips) {
        if (mToast == null) {
            mToast = Toast.makeText(context,tips,Toast.LENGTH_SHORT);
        } else {
            mToast.setText(tips);
        }
        mToast.show();
    }
}
