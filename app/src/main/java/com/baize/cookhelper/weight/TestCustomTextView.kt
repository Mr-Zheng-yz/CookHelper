package com.baize.cookhelper.weight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.baize.cookhelper.databinding.TestCustomTextBinding
import com.baize.cookhelper.utils.dp

class TestCustomTextView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

  private val vb = TestCustomTextBinding.inflate(LayoutInflater.from(context), this, true)

  init {
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    setMeasuredDimension(MeasureSpec.makeMeasureSpec(500,MeasureSpec.EXACTLY),
      MeasureSpec.makeMeasureSpec(100, MeasureSpec.EXACTLY))
  }

}