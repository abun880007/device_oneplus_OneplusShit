/*
 * Copyright (C) 2015 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oneplus.shit.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.provider.Settings;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.preference.TwoStatePreference;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.util.Log;

import com.oneplus.shit.settings.R;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

    public class ShitPanelSettings extends PreferenceActivity {

    public static final String KEY_VIBSTRENGTH = "vib_strength";

    /*public static final String KEY_TAPTOWAKE_SWITCH = "taptowake";*/

    public static final String KEY_SRGB_SWITCH = "srgb";
    public static final String KEY_HBM_SWITCH = "hbm";
    public static final String KEY_DCI_SWITCH = "dci";
    public static final String KEY_DCDIM_SWITCH = "dcdim";
    public static final String KEY_NIGHT_SWITCH = "night";
    public static final String KEY_ONEPLUS_SWITCH = "oneplus";
    
    private SharedPreferences mPrefs;
    private VibratorStrengthPreference mVibratorStrength;
    private TwoStatePreference mHBMModeSwitch;
    private TwoStatePreference mDCDimSwitch;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        addPreferencesFromResource(R.xml.shit_panel);

        ListView lv = getListView();
        lv.setDivider(new ColorDrawable(Color.TRANSPARENT));
        lv.setDividerHeight(0);

        mVibratorStrength = (VibratorStrengthPreference) findPreference(KEY_VIBSTRENGTH);
        if (mVibratorStrength != null) {
            mVibratorStrength.setEnabled(VibratorStrengthPreference.isSupported());
        }

        /*mTapToWakeSwitch = (TwoStatePreference) findPreference(KEY_TAPTOWAKE_SWITCH);
        mTapToWakeSwitch.setOnPreferenceChangeListener(new TapToWakeSwitch());*/

        mHBMModeSwitch = (TwoStatePreference) findPreference(KEY_HBM_SWITCH);
        mHBMModeSwitch.setOnPreferenceChangeListener(new HBMModeSwitch());

        mDCDimSwitch = (TwoStatePreference) findPreference(KEY_DCDIM_SWITCH);
        mDCDimSwitch.setOnPreferenceChangeListener(new DCDimSwitch());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
