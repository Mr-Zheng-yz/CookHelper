package com.baize.cookhelper.ui

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.baize.cookhelper.base.BaseFragment
import com.baize.cookhelper.databinding.FragmentHomeBinding
import com.baize.cookhelper.network.data.TArticle
import com.baize.cookhelper.test.DividerPaddingLeftDecoration
import com.baize.cookhelper.test.ListAdapter
import com.baize.cookhelper.test.getRandomImageUrl

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
//      val gridLayoutManager = GridLayoutManager(context, 2)
//      gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//        override fun getSpanSize(position: Int) = if (position == 0) 2 else 1
//      }
//      layoutManager = gridLayoutManager
      layoutManager = LinearLayoutManager(context)
      adapter = mAdapter
      addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
      addItemDecoration(DividerPaddingLeftDecoration())
    }

    var ids = 0

    viewModel.mArticleListData.observe(this) {
      val datas = it.map {
        TArticle(ids, getRandomImageUrl(), it.title).also {
          ids++
        }
      }
      ids = 0
      mAdapter.swapData(datas as ArrayList<TArticle>, true)
    }

    binding.flTest.setOnClickListener {
      Toast.makeText(context, "请求接口", Toast.LENGTH_SHORT).show()
      viewModel.getHomeList()
    }
  }

  override fun loadData() {
//    repeat(20) {
//      mAdapter.addData(getRandomImageUrl() to "哈哈${it}")
//    }
//    mAdapter.notifyDataSetChanged()
    viewModel.getHomeList()
  }

  fun emitVisibleItems() {
    val manager = binding.rvTest.layoutManager
    if (manager is GridLayoutManager) {
      val firstPosition = manager.findFirstVisibleItemPosition()
      val lastPosition = manager.findLastVisibleItemPosition()
      val visibleRange = mutableListOf<Int>()
      for (i in firstPosition..lastPosition) {
        val view = manager.findViewByPosition(i) ?: continue
        val rect = Rect()
        Log.i("baize_", "view：$view ${view.getGlobalVisibleRect(rect)}")
        val isVisible = view.getGlobalVisibleRect(rect)
        if (isVisible) {
          visibleRange.add(i)
        }
      }
      Log.i("baize_", "曝光：$visibleRange")
    }
  }

}
