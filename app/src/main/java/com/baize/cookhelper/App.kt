package com.baize.cookhelper

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.baize.cookhelper.utils.DARK_MODEL_CONFIG_KEY
import com.baize.cookhelper.utils.SPUtils

class App : Application() {

  companion object {
    private lateinit var instance: App

    @JvmStatic
    fun getContext() = instance
  }

  override fun onCreate() {
    super.onCreate()
    instance = this

    applyDarkModel()
  }

  private fun applyDarkModel() {
    val targetModel = SPUtils.getInt(DARK_MODEL_CONFIG_KEY, AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)
    Log.i("baize_dark", "应用模式：${targetModel}")
    AppCompatDelegate.setDefaultNightMode(targetModel)
  }

}
