package com.bootcamp.login;

import android.util.Log;

/**
 * Created by alvaromunoz
 * Date 2019-11-22.
 */
public class Utils {

    public static void Debug(Class clase, String data){
        System.out.println(clase.getName() + ": " + data);
        Log.w(clase.getName(), data);
    }
}
