package com.baize.cookhelper.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.preference.PreferenceFragmentCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.baize.cookhelper.R
import com.baize.cookhelper.base.BaseFragment
import com.baize.cookhelper.databinding.FragmentMineBinding
import com.baize.cookhelper.test.ListAdapter
import com.baize.cookhelper.test.TestFragment
import com.baize.cookhelper.test.getRandomImageUrl
import com.baize.cookhelper.ui.setting.SettingsActivity
import com.google.android.material.tabs.TabLayout
import android.view.View.OnTouchListener

import android.view.ViewGroup
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import com.baize.cookhelper.test.WebTestActivity


class MineFragment : BaseFragment<FragmentMineBinding>() {
  companion object {
    fun getInstance(text: String = "测试") = MineFragment().apply {
      val bundle = Bundle()
      bundle.putString("text", text)
      arguments = bundle
    }
  }

  override fun initView(containerView: View) {
    binding.btnSetting.setOnClickListener {
      context?.startActivity(Intent(context, SettingsActivity::class.java))
    }
    binding.btnTestWeb.setOnClickListener {
      context?.startActivity(Intent(context, WebTestActivity::class.java))
    }

    val mAdapter = ViewPagerAdapter(
      requireFragmentManager(),
      mutableListOf("视频" to TestFragment(), "图片" to TestFragment(), "文件" to TestFragment())
    )
    binding.viewpager.currentItem = 1
    binding.viewpager.postDelayed({ binding.viewpager.currentItem = 2 }, 200)
    binding.viewpager.adapter = mAdapter
    binding.tablayout.setupWithViewPager(binding.viewpager)
//    binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//      override fun onTabSelected(tab: TabLayout.Tab?) {
//        Log.i("haha", "tab selected:")
//        binding.tablayout.postDelayed({
//          if (binding.viewpager.tag == null) binding.viewpager.tag = true
//        }, 600)
//      }
//      override fun onTabUnselected(tab: TabLayout.Tab?) {
//      }
//      override fun onTabReselected(tab: TabLayout.Tab?) {
//      }
//    })
    binding.tablayout.setOnClickListener {
      Log.i("haha", "tablayout 点击了!!!！！！")
    }
    setGesture(binding.tablayout) {
      Log.i("haha", "tablayout 点击了！！！")
    }
    binding.tablayout.getTabAt(0)

    binding.viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
      }

      override fun onPageSelected(position: Int) {
        Log.i("haha", "viewPage Selected:${binding.viewpager.tag} " +
            "${binding.viewpager.tag is Boolean} ${binding.viewpager.tag is String}")
        if (binding.viewpager.tag as? Boolean == true) {
          Log.i("haha", "view page selected:$position")
        }
      }

      override fun onPageScrollStateChanged(state: Int) {
        if (state == ViewPager.SCROLL_STATE_DRAGGING) {
          if (binding.viewpager.tag == null) binding.viewpager.tag = true
        }
//        Log.i("haha", "state:$state")
      }
    })
  }

  private fun setGesture(viewGroup: ViewGroup, listener:()->Unit) {
    val detector = GestureDetector(context, object : SimpleOnGestureListener() {
      override fun onSingleTapConfirmed(e: MotionEvent): Boolean { //单击
        listener.invoke()
        return super.onSingleTapConfirmed(e)
      }
    })
    // onTouch lambda should call View#performClick when a click is detected
    if (viewGroup.hasOnClickListeners()) {
      viewGroup.setOnTouchListener { v, event ->
        Log.i("haha", "event:{${event.actionMasked}}")
        detector.onTouchEvent(event) //一定要返回false，否则会拦截onClick事件
        true
      }
    }
  }

  override fun loadData() {
    super.loadData()
  }

  class ViewPagerAdapter(
    fm: FragmentManager,
    val fragments: MutableList<Pair<String, Fragment>>
  ) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
      return fragments[position].first
    }

    override fun getCount(): Int {
      return fragments.size
    }

    override fun getItem(position: Int): Fragment {
      return fragments[position].second
    }

  }

}
