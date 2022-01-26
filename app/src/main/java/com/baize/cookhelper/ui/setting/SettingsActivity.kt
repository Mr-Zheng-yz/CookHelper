package com.baize.cookhelper.ui.setting

import android.os.Bundle
import com.baize.cookhelper.R
import com.baize.cookhelper.base.BaseActivity

/**
 *  author : baize
 *  date : 2022/1/26 5:07 下午
 *  description :
 */
class SettingsActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_setting)

    supportFragmentManager.beginTransaction().replace(R.id.fl_content, SettingFragment()).commitNow()
  }


}