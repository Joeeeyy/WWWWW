package com.jjoey.walpy.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class WalpyPrefs {

    public Context context;
    public SharedPreferences countPrefs;
    public SharedPreferences.Editor editor;

    public static final String COUNT_CHECKED = "Count_Prefs";
    public static final String KEY_CHECKED = "key_checked";

    public WalpyPrefs(Context context) {
        this.context = context;
        countPrefs = context.getSharedPreferences(COUNT_CHECKED, Context.MODE_PRIVATE);
        editor = countPrefs.edit();
    }

    public void setCountChecked(int count){
        editor.putInt(KEY_CHECKED, count).commit();
    }

    public int getCountChecked(){
        return countPrefs.getInt(KEY_CHECKED, 0);
    }

    public void inValidateCount(){
        editor.remove(KEY_CHECKED).commit();
    }

}
