package com.example.kkangsam_pt03_mission2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;

public class SettingPreferenceFragment extends PreferenceFragment {
    SharedPreferences prefs;

    ListPreference basicNetworkPreference;
    PreferenceScreen apnScreen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preference);

        basicNetworkPreference = (ListPreference)findPreference("basic_network");
        apnScreen = (PreferenceScreen)findPreference("apn");


        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!prefs.getString("basic_network","").equals("")){
            basicNetworkPreference.setSummary(prefs.getString("basic_network","LTE(권장)"));
        }


        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            if(key.equals("basic_network")){
                basicNetworkPreference.setSummary(prefs.getString("basic_network","LTE(권장)"));

            }
        }
    };

}
