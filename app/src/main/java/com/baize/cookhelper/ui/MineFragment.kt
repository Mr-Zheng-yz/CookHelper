package com.baize.cookhelper.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.preference.PreferenceFragmentCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baize.cookhelper.R
import com.baize.cookhelper.base.BaseFragment
import com.baize.cookhelper.test.ListAdapter
import com.baize.cookhelper.test.getRandomImageUrl
import com.baize.cookhelper.ui.setting.SettingsActivity

class MineFragment : BaseFragment() {
  companion object {
    fun getInstance(text: String = "测试") = MineFragment().apply {
      val bundle = Bundle()
      bundle.putString("text", text)
      arguments = bundle
    }
  }

  override fun getLayoutResId() = R.layout.fragment_mine

  override fun initView(containerView: View) {
    containerView.findViewById<Button>(R.id.btn_setting).setOnClickListener {
      context?.startActivity(Intent(context, SettingsActivity::class.java))
    }
  }

  override fun loadData() {
    super.loadData()
  }

}
