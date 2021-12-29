package com.baize.cookhelper

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  lateinit var navigatorBar: HomeBottomNavigatorBar

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navigatorBar = findViewById(R.id.navigator_bar)
    navigatorBar.initTabs(
      arrayListOf(
        MainTabItem(0,"首页", Color.RED, Color.GRAY, R.drawable.tab_home_s, R.drawable.tab_home),
        MainTabItem(1,"发现", Color.RED, Color.GRAY, R.drawable.tab_discover_s, R.drawable.tab_discover),
        MainTabItem(2,"消息", Color.RED, Color.GRAY, R.drawable.tab_discover_s, R.drawable.tab_discover)
      )
    )
    navigatorBar.setTabListener(object : TabClickListener {
      override fun onTabSelect(tab: MainTabItem) {
        Log.i("baize_","onTabSelect: ${tab.id}")
      }

      override fun onTabBeforeSelect(tab: MainTabItem): Boolean {
        Log.i("baize_","onTabBeforeSelect: ${tab.id}")
        if (tab.id == 2) {
          return false
        }
        return super.onTabBeforeSelect(tab)
      }

      override fun onTabRepeatSelect(tab: MainTabItem) {
        Log.i("baize_","onTabRepeatSelect: ${tab.id}")
      }

      override fun onTabUnSelect(tab: MainTabItem) {
        Log.i("baize_","onTabUnSelect: ${tab.id}")
      }
    })
  }

}