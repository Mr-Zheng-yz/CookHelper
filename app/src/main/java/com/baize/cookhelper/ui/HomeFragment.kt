package com.baize.cookhelper.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baize.cookhelper.R
import com.baize.cookhelper.base.BaseFragment
import com.baize.cookhelper.test.ListAdapter
import com.baize.cookhelper.test.getRandomImageUrl

class HomeFragment : BaseFragment() {
  companion object {
    fun getInstance(text: String = "测试") = HomeFragment().apply {
      val bundle = Bundle()
      bundle.putString("text", text)
      arguments = bundle
    }
  }

  private lateinit var rvTest: RecyclerView
  private lateinit var mAdapter: ListAdapter

  override fun getLayoutResId() = R.layout.fragment_home

  override fun initView(containerView: View) {
    rvTest = containerView.findViewById(R.id.rv_test)
    mAdapter = ListAdapter()
    rvTest.apply {
      layoutManager = GridLayoutManager(context, 2)
      adapter = mAdapter
    }
  }

  override fun loadData() {
    repeat(20) {
      mAdapter.addData(getRandomImageUrl() to "哈哈${it}")
    }
    mAdapter.notifyDataSetChanged()
  }

}
