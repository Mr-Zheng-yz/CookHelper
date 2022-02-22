package com.baize.cookhelper.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baize.cookhelper.MainViewModel
import com.baize.cookhelper.R
import com.baize.cookhelper.base.BaseFragment
import com.baize.cookhelper.databinding.FragmentHomeBinding
import com.baize.cookhelper.network.data.ArticleBean
import com.baize.cookhelper.test.ListAdapter
import com.baize.cookhelper.test.getRandomImageUrl
import com.baize.cookhelper.vm.ViewModelFactory

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
  companion object {
    fun getInstance(text: String = "测试") = HomeFragment().apply {
      val bundle = Bundle()
      bundle.putString("text", text)
      arguments = bundle
    }
  }

  private lateinit var mAdapter: ListAdapter

  private val viewModel: HomeViewModel by lazy {
    ViewModelProvider(this).get(HomeViewModel::class.java)
  }

  override fun initView(containerView: View) {
    mAdapter = ListAdapter()
    binding.rvTest.apply {
      val gridLayoutManager = GridLayoutManager(context, 2)
      gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int) = if (position == 0) 2 else 1
      }
      layoutManager = gridLayoutManager
      adapter = mAdapter
    }

    viewModel.mArticleListData.observe(this, {
      mAdapter.clear()
      val datas = it.map { Pair(getRandomImageUrl(), it.title) }
      mAdapter.addDataList(datas)
      mAdapter.notifyDataSetChanged()
    })
  }

  override fun loadData() {
//    repeat(20) {
//      mAdapter.addData(getRandomImageUrl() to "哈哈${it}")
//    }
//    mAdapter.notifyDataSetChanged()
    viewModel.getHomeList()
  }

}
