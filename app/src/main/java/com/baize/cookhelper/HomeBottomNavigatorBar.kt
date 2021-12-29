package com.baize.cookhelper

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/**
 * 自定义首页底部导航栏
 */
class HomeBottomNavigatorBar @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs), View.OnClickListener {

  private lateinit var tabItemList: List<MainTabItem>
  var tabClickListener : TabClickListener? = null

  fun initTabs(tabs: List<MainTabItem>, default: Int = 0) {
    orientation = LinearLayout.HORIZONTAL
    tabItemList = tabs
    for (i in tabs.indices) {
      val tabView = MainTabItemView(context).apply { initItemTab(tabs[i], default == i) }
      tabView.setOnClickListener(this)
      addView(tabView, LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f))
    }
  }

  fun setTabListener() {
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
  }

  override fun onClick(v: View) {
    v.isSelected = !v.isSelected
  }

}

/**
 * tab点击回调
 */
interface TabClickListener{
  fun onTabSelect()
  fun onTabBeforeSelect() {}
  fun onTabRepeatSelect() {}
  fun onTabUnSelect() {}
}

/**
 * 首页导航栏tab
 */
class MainTabItemView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

  lateinit var tabView: View
  lateinit var tabImage: ImageView
  lateinit var tabText: TextView

  fun initItemTab(tab: MainTabItem, selected: Boolean = false) {
    tabView = LayoutInflater.from(context).inflate(R.layout.view_main_tab_item, this)
    tabImage = tabView.findViewById(R.id.iv_tab_image)
    tabImage.background = generateSelectorDrawable(tab.selectImgResId, tab.unSelectImgResId)
    tabText = tabView.findViewById(R.id.iv_tab_text)
    tabText.setTextColor(generateColorStateList(tab.selectTexColor, tab.unSelectTextColor))
    tabText.text = tab.tabText
    setSelect(selected)
  }

  fun setSelect(selected: Boolean) {
    this.isSelected = selected
    tabImage.isSelected = selected
    tabText.isSelected = selected
  }

  private fun generateSelectorDrawable(selectImgResId: Int, unSelectImgId: Int): StateListDrawable {
    val imageStateDrawable = StateListDrawable()
    imageStateDrawable.addState(
      intArrayOf(-android.R.attr.state_selected), context.getDrawable(selectImgResId)
    )
    imageStateDrawable.addState(
      intArrayOf(android.R.attr.state_selected), context.getDrawable(unSelectImgId)
    )
    return imageStateDrawable
  }

  private fun generateColorStateList(selectColor: Int, unSelectColor: Int): ColorStateList {
    val colors = intArrayOf(selectColor, unSelectColor)
    val states = Array(2) { index ->
      return@Array when (index) {
        0 -> intArrayOf(android.R.attr.state_selected)
        1 -> intArrayOf(-android.R.attr.state_selected)
        else -> null
      }
    }
    return ColorStateList(states, colors)
  }

}


/**
 * 首页底部tab菜单封装
 */
data class MainTabItem(
  var tabText: String,
  var selectTexColor: Int,
  var unSelectTextColor: Int,
  var selectImgResId: Int,
  var unSelectImgResId: Int
) {
  var selectImgUrl: String? = null
  var unSelectImgUrl: String? = null
}
