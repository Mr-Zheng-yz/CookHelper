package com.baize.cookhelper.utils

import android.content.res.Resources
import android.util.TypedValue

val Float.dp
  get() : Float {
    return TypedValue.applyDimension(
      TypedValue.COMPLEX_UNIT_DIP,
      this,
      Resources.getSystem().displayMetrics
    )
  }

fun Float.dp(): Float {
  return TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
  )
}

val Int.dp
  get() = this.toFloat().dp