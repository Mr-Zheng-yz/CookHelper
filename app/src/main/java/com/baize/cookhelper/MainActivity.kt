package com.baize.cookhelper

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  lateinit var navigatorBar: HomeBottomNavigatorBar

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navigatorBar = findViewById(R.id.navigator_bar)
    navigatorBar.initTabs(
      arrayListOf(
        MainTabItem("首页", Color.RED, Color.GRAY, R.drawable.tab_home_s, R.drawable.tab_home),
        MainTabItem("发现", Color.RED, Color.GRAY, R.drawable.tab_discover_s, R.drawable.tab_discover)
      )
    )
  }

}