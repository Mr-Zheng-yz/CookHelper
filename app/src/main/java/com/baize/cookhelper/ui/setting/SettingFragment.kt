package com.baize.cookhelper.ui.setting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.CheckBoxPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.baize.cookhelper.R
import com.baize.cookhelper.utils.isDarkTheme

/**
 *  author : baize
 *  date : 2022/1/26 4:13 下午
 *  description :
 */
class SettingFragment : PreferenceFragmentCompat() {

  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    setPreferencesFromResource(R.xml.preferences, rootKey)
    when (AppCompatDelegate.getDefaultNightMode()) {
      AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> {
        Log.i("baize_", "跟随系统...")
      }
      AppCompatDelegate.MODE_NIGHT_YES -> {
        Log.i("baize_", "黑暗模式...")
      }
      AppCompatDelegate.MODE_NIGHT_NO -> {
        Log.i("baize_", "白天模式...")
      }
      AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY -> {
        Log.i("baize_", "低电量...")
      }
      AppCompatDelegate.MODE_NIGHT_UNSPECIFIED -> {
        Log.i("baize_", "未指定...")
      }
    }
    val isDarkModel = isDarkTheme(requireContext())
    Log.i("baize_", "是否是深色模式？：${isDarkModel}")

    val darkModelSwitch: CheckBoxPreference? = findPreference("dark_model_switch")
    val darkFollowSystem: SwitchPreferenceCompat? = findPreference("dark_follow_system")
    darkFollowSystem?.setDefaultValue(false)
    darkFollowSystem?.setOnPreferenceChangeListener { preference, newValue ->
      if (newValue is Boolean && newValue == true) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
      }
      return@setOnPreferenceChangeListener true
    }

    darkModelSwitch?.setDefaultValue(isDarkModel)
    darkModelSwitch?.setOnPreferenceChangeListener { preference, newValue ->
      Log.i("baize_", "newValue:${newValue}")
      if (newValue is Boolean && newValue != isDarkTheme(requireContext())) {
        AppCompatDelegate.setDefaultNightMode(if (newValue) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
      }
      return@setOnPreferenceChangeListener true
    }
  }

}