package com.baize.cookhelper.weight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import com.baize.cookhelper.utils.dp

class CircleGroupBg @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

  private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    strokeWidth = 1.dp
    style = Paint.Style.STROKE
    color = Color.parseColor("#4D000000")
    strokeJoin = Paint.Join.ROUND
  }

  private val innerRingPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    strokeWidth = 1.dp
    style = Paint.Style.STROKE
    color = Color.parseColor("#1A000000")
    strokeJoin = Paint.Join.BEVEL
  }

  override fun dispatchDraw(canvas: Canvas?) {
    canvas?.drawArc(0.5f.dp, 0.5f.dp, width.toFloat() - 0.5f.dp, height.toFloat() - 0.5f.dp
      , 337.5f, 45f, false, strokePaint)
    canvas?.drawArc(0.5f.dp, 0.5f.dp, width.toFloat()- 0.5f.dp, height.toFloat() - 0.5f.dp
      , 157.5f, 45f, false, strokePaint)
//    canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), (height / 2).toFloat(), paint)

    canvas?.drawArc(4.5f.dp, 4.5f.dp, width - 2.5f.dp, height - 2.5f.dp
      , 337.5f, 45f, false, innerRingPaint)
    val radius = (height / 2) - 3.5f.dp
    canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, innerRingPaint)


    super.dispatchDraw(canvas)
  }

}