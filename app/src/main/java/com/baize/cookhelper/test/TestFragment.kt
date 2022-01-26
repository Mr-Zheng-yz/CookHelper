package com.baize.cookhelper.test

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.baize.cookhelper.R

class TestFragment : Fragment() {

  companion object {

    fun getInstance(text: String = "测试") = TestFragment().apply {
      val bundle = Bundle()
      bundle.putString("text", text)
      arguments = bundle
    }
  }

  lateinit var textView: TextView

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    textView = TextView(context)
    textView.gravity = Gravity.CENTER
    textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_color))
    textView.setBackgroundResource(R.drawable.almanac_yi)
    return textView
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    textView.text = arguments?.getString("text") ?: "测试"
  }

}