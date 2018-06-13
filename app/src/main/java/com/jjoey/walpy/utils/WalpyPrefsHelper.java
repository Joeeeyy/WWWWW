package com.jjoey.walpy.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class WalpyPrefsHelper {

    public Context context;
    public SharedPreferences customizePrefs, tabsCountPrefs;
    public SharedPreferences.Editor editor, tabsEditor;

    public static final String CUSTOMIZE_TABS_PREFS = "customizeTabsPrefs";
    public static final String COUNT_TABS_PREFS = "countTabsPrefs";

    public static final String KEY_NATURE = "nature";
    public static final String KEY_SPACE = "space";
    public static final String KEY_SEASONS = "seasons";
    public static final String KEY_ART = "art";
    public static final String KEY_SCIFI = "sci_fi";
    public static final String KEY_MISC = "misc";

    public static final String KEY_COUNT = "count_tabs";

    public WalpyPrefsHelper(Context context) {
        this.context = context;
        customizePrefs = context.getSharedPreferences(CUSTOMIZE_TABS_PREFS, Context.MODE_PRIVATE);
        editor = customizePrefs.edit();

        tabsCountPrefs = context.getSharedPreferences(COUNT_TABS_PREFS, Context.MODE_PRIVATE);
        tabsEditor = tabsCountPrefs.edit();

    }

    public void saveNaturePrefs(String s){
        editor.putString(KEY_NATURE, s).apply();
    }

    public String getKeyNature(){
        return customizePrefs.getString(KEY_NATURE, null);
    }

    public void removeNaturePrefs(){
        editor.remove(KEY_NATURE).apply();
    }

    public void saveSpacePrefs(String s){
        editor.putString(KEY_SPACE, s).apply();
    }

    public String getKeySpace(){
        return customizePrefs.getString(KEY_SPACE, null);
    }

    public void removeSpacePrefs(){
        editor.remove(KEY_SPACE).apply();
    }

    public void saveSeasonsPrefs(String s){
        editor.putString(KEY_SEASONS, s).apply();
    }

    public String getKeySeasons(){
        return customizePrefs.getString(KEY_SEASONS, null);
    }

    public void removeSeasonPrefs(){
        editor.remove(KEY_SEASONS).apply();
    }

    public void saveArtPrefs(String s){
        editor.putString(KEY_ART, s).apply();
    }

    public String getKeyArt(){
        return customizePrefs.getString(KEY_ART, null);
    }

    public void removeArtPrefs(){
        editor.remove(KEY_ART).apply();
    }

    public void saveSci_FiPrefs(String s){
        editor.putString(KEY_SCIFI, s).apply();
    }

    public String getKeyScifi(){
        return customizePrefs.getString(KEY_SCIFI, null);
    }

    public void removeSci_FiPrefs(){
        editor.remove(KEY_SCIFI).apply();
    }

    public void saveMiscPrefs(String s){
        editor.putString(KEY_MISC, s).apply();
    }

    public String getKeyMisc(){
        return customizePrefs.getString(KEY_MISC, null);
    }

    public void removeMiscPrefs(){
        editor.remove(KEY_MISC).apply();
    }

    public void removeAllPrefs(){
        editor.remove(KEY_NATURE).apply();
        editor.remove(KEY_SPACE).apply();
        editor.remove(KEY_SEASONS).apply();
        editor.remove(KEY_ART).apply();
        editor.remove(KEY_SCIFI).apply();
        editor.remove(KEY_MISC).apply();
    }

    public void saveCount(int count) {
        tabsEditor.putInt(KEY_COUNT, count).apply();
    }

    public int getSaveCount(){
        return tabsCountPrefs.getInt(KEY_COUNT, 0);
    }
}
