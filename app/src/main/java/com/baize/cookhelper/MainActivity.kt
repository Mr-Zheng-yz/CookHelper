package com.baize.cookhelper

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.baize.cookhelper.test.TestFragment
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

  lateinit var navigatorBar: HomeBottomNavigatorBar

  private var fragments = arrayListOf<Fragment>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navigatorBar = findViewById(R.id.navigator_bar)
    navigatorBar.initTabs(arrayListOf(
      MainTabItem(0, "首页", Color.RED, Color.GRAY, R.drawable.tab_home_s, R.drawable.tab_home),
      MainTabItem(
        1,
        "发现",
        Color.RED,
        Color.GRAY,
        R.drawable.tab_discover_s,
        R.drawable.tab_discover
      ),
      MainTabItem(
        2,
        "消息",
        Color.RED,
        Color.GRAY,
        R.drawable.tab_discover_s,
        R.drawable.tab_discover
      )
    ), 0, object : TabClickListener {
      override fun onTabSelect(tab: MainTabItem) {
        Log.i("baize_", "onTabSelect: ${tab.id}")
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val existFragment = fragmentManager.findFragmentByTag("${tab.id}")
        fragments.forEach { fragmentTransaction.hide(it) }
        if (existFragment == null) {
          val fragment = fragmentGenerator(tab)
          fragments.add(fragment)
          fragmentTransaction.add(R.id.fl_content, fragment, "${tab.id}")
        } else {
          fragmentTransaction.show(existFragment)
        }
        fragmentTransaction.commit()
        fragmentManager.beginTransaction()
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
      0 -> TestFragment.getInstance("首页")
      1 -> TestFragment.getInstance("发现")
      2 -> TestFragment.getInstance("我的")
      else -> TestFragment.getInstance()
    }
  }

  override fun onBackPressed() {
    super.onBackPressed()
    exitProcess(0)
  }

}