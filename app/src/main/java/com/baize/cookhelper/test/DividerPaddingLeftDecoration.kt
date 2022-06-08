package com.baize.cookhelper.test

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.baize.cookhelper.utils.dp

class DividerPaddingLeftDecoration : RecyclerView.ItemDecoration() {

  private val marginLeft = 16.dp
  private val marginRight = 16.dp
  private val marginTop = 16.dp
  private val marginBottom = 4.dp
  private val dividerColor = Color.parseColor("#55FF0000")
  private val dividerPaint = Paint().apply {
    color = dividerColor
  }

  override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    for (i in 0 until parent.childCount) {
      val child = parent[i]
      val left = child.left.toFloat()
      val right = child.width.toFloat()
      c.drawRect(left + marginLeft
        , child.top + marginTop
        , right - marginRight
        , child.bottom - marginBottom
        ,dividerPaint)
    }
  }

}