package com.baize.cookhelper.utils

import android.content.Context
import android.content.res.Configuration

/**
 *  author : baize
 *  date : 2021/12/30 4:27 下午
 *  description : 通用工具🔧
 */

fun isDarkTheme(context: Context): Boolean {
  val flag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
  return flag == Configuration.UI_MODE_NIGHT_YES
}
