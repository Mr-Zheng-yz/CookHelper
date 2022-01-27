package com.baize.cookhelper.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.baize.cookhelper.App

/**
 *  author : baize
 *  date : 2022/1/27 3:58 下午
 *  description :
 */
object SPUtils {

  val sp: SharedPreferences by lazy {
    App.getContext().getSharedPreferences("setting", Context.MODE_PRIVATE)
  }

  fun putInt(key: String, value: Int) = sp.edit { putInt(key, value) }

  fun getInt(key: String, defaultValue: Int = 0) = sp.getInt(key, defaultValue)

}