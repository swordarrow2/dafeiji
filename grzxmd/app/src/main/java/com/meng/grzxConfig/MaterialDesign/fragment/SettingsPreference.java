package com.meng.grzxConfig.MaterialDesign.fragment;

import android.os.*;
import android.preference.*;

import com.meng.grzxConfig.MaterialDesign.R;

public class SettingsPreference extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName("preference");
        addPreferencesFromResource(R.xml.preference);
    }
}
