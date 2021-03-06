package com.baize.cookhelper.ui.setting

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.baize.cookhelper.R
import com.baize.cookhelper.base.BaseFragment
import com.baize.cookhelper.databinding.FragmentSettingBinding
import com.baize.cookhelper.utils.DARK_MODEL_CONFIG_KEY
import com.baize.cookhelper.utils.SPUtils
import com.baize.cookhelper.utils.isDarkTheme
import com.google.android.material.switchmaterial.SwitchMaterial

/**
 *  author : baize
 *  date : 2022/1/26 4:13 下午
 *  description :
 */
class SettingFragment : BaseFragment<FragmentSettingBinding>() {

  lateinit var switchDark: SwitchCompat
  lateinit var switchDarkFollowSystem: SwitchMaterial

  private val darkSwitchChangeListener : CompoundButton.OnCheckedChangeListener by lazy {
    CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
      when (buttonView) {
        binding.switchDark -> {
          Log.i("baize_dark","设置为：${if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO}")
          SPUtils.putInt(DARK_MODEL_CONFIG_KEY, if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
          AppCompatDelegate.setDefaultNightMode(if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
//          switchDarkFollowSystem.setOnCheckedChangeListener(null)
//          switchDarkFollowSystem.isChecked = false
//          switchDarkFollowSystem.setOnCheckedChangeListener(darkSwitchChangeListener)
        }
        binding.switchDarkFollowSystem -> {
          if (isChecked) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
              Log.i("baize_dark","MODE_NIGHT_FOLLOW_SYSTEM:跟随系统")
              SPUtils.putInt(DARK_MODEL_CONFIG_KEY,AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            } else {
              Log.i("baize_dark","MODE_NIGHT_AUTO_BATTERY:省电模式设置")
              SPUtils.putInt(DARK_MODEL_CONFIG_KEY,AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            }
          } else {
            val targetModel = if (isDarkTheme(requireContext())) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            Log.i("baize_dark","当前：${if (isDarkTheme(requireContext())) "深色" else "浅色"}" +
                " 设置为：${targetModel}")
            SPUtils.putInt(DARK_MODEL_CONFIG_KEY,targetModel)
            AppCompatDelegate.setDefaultNightMode(targetModel)
          }
        }
      }
    }
  }

  override fun initView(containerView: View) {
    Log.i("baize_dark", "当前状态：${AppCompatDelegate.getDefaultNightMode()}")
    binding.switchDark.isChecked = isDarkTheme(requireContext())
    binding.switchDark.setOnCheckedChangeListener(darkSwitchChangeListener)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      binding.switchDarkFollowSystem.text = "跟随系统"
      binding.switchDarkFollowSystem.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM ||
          AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED
    } else {
      binding.switchDarkFollowSystem.text = "由省电模式设置"
      binding.switchDarkFollowSystem.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
    }
    binding.switchDarkFollowSystem.setOnCheckedChangeListener(darkSwitchChangeListener)
  }

}