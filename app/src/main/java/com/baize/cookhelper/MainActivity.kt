package com.baize.cookhelper

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.baize.cookhelper.test.TestFragment
import com.baize.cookhelper.ui.HomeFragment
import com.baize.cookhelper.ui.MineFragment
import com.baize.cookhelper.utils.getResColor
import com.baize.cookhelper.utils.isDarkTheme
import com.baize.cookhelper.vm.ViewModelFactory
import com.baize.cookhelper.weight.HomeBottomNavigatorBar
import com.baize.cookhelper.weight.MainTabItem
import com.baize.cookhelper.weight.TabClickListener
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

  lateinit var navigatorBar: HomeBottomNavigatorBar

  private var fragments = arrayListOf<Fragment>()
  private var lastFragment : Fragment? = null

  private val mMainViewModel by lazy {
    ViewModelFactory.getMainViewModel(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navigatorBar = findViewById(R.id.navigator_bar)
    navigatorBar.initTabs(arrayListOf(
      MainTabItem(0, "首页", getResColor(R.color.text_color), getResColor(R.color.color_888888), R.drawable.tab_home_s, R.drawable.tab_home),
      MainTabItem(1, "发现", getResColor(R.color.text_color), getResColor(R.color.color_888888), R.drawable.tab_discover_s, R.drawable.tab_discover),
      MainTabItem(2, "消息", getResColor(R.color.text_color), getResColor(R.color.color_888888), R.drawable.tab_mine_s, R.drawable.tab_mine)
    ), 0, object : TabClickListener {
      override fun onTabSelect(tab: MainTabItem) {
        Log.i("baize_", "onTabSelect: ${tab.id}")
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val existFragment = fragmentManager.findFragmentByTag("${tab.tabText}")
        lastFragment?.let { fragmentTransaction.hide(it) } ?:
          fragmentManager.findFragmentByTag(mMainViewModel.lastFragmentTag)?.let { fragmentTransaction.hide(it) }
        if (existFragment == null) {
          val fragment = fragmentGenerator(tab)
          lastFragment = fragment
          mMainViewModel.lastFragmentTag = tab.tabText
          fragmentTransaction.add(R.id.fl_content, fragment, "${tab.tabText}")
        } else {
          lastFragment = existFragment
          mMainViewModel.lastFragmentTag = tab.tabText
          fragmentTransaction.show(existFragment)
        }
        fragmentTransaction.commitNow()
      }

      override fun onTabBeforeSelect(tab: MainTabItem): Boolean {
        return super.onTabBeforeSelect(tab)
      }

      override fun onTabRepeatSelect(tab: MainTabItem) {
        Log.i("baize_", "onTabRepeatSelect: ${tab.id}")
      }

      override fun onTabUnSelect(tab: MainTabItem) {
        Log.i("baize_", "onTabUnSelect: ${tab.id}")
      }
    })
  }

  fun fragmentGenerator(tabItem: MainTabItem): Fragment {
    return when (tabItem.id) {
      0 -> HomeFragment.getInstance("首页")
      1 -> TestFragment.getInstance("发现")
      2 -> MineFragment.getInstance("消息")
      else -> TestFragment.getInstance()
    }
  }

  override fun onBackPressed() {
    super.onBackPressed()
    exitProcess(0)
  }

}