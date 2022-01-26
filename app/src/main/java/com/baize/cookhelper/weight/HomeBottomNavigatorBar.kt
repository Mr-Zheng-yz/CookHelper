package com.baize.cookhelper.weight

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.baize.cookhelper.R

/**
 * 自定义首页底部导航栏
 * 1.支持菜单条目灵活配置
 * 2.支持多种回调，适用各种情景
 * TODO 3.支持网络图标
 * TODO 4.支持切换动画
 * TODO 5.支持提示红点
 */
class HomeBottomNavigatorBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs), View.OnClickListener {

    private lateinit var tabItemList: List<MainTabItem>
    private var currentSelectIndex: Int = -1
    var tabClickListener: TabClickListener? = null

    init {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }

    fun initTabs(tabs: List<MainTabItem>, default: Int = 0, tabClickListener: TabClickListener) {
        this.tabClickListener = tabClickListener
        this.currentSelectIndex = if (default in tabs.indices) default else -1
        this.tabItemList = tabs
        for (i in tabs.indices) {
            val tabView = MainTabItemView(context).apply {
                initItemTab(tabs[i], default == i)
                setOnClickListener(this@HomeBottomNavigatorBar::onClick)
                if (default == i) tabClickListener.onTabSelect(tabs[i])
            }
            addView(tabView, LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f))
        }
    }

    override fun onClick(tabView: View) {
        if (tabView is MainTabItemView) {
            val index = tabItemList.indexOf(tabView.tabItem)
            if (index in tabItemList.indices) {
                val targetView = getChildAt(index) as MainTabItemView
                val lastTabView = getChildAt(currentSelectIndex) as MainTabItemView
                if (index == currentSelectIndex) {
                    tabClickListener?.onTabRepeatSelect(targetView.tabItem)
                } else {
                    if (tabClickListener?.onTabBeforeSelect(targetView.tabItem) != false) {
                        lastTabView.isSelected = false
                        tabClickListener?.onTabUnSelect(lastTabView.tabItem)
                        targetView.setSelect(true)
                        tabClickListener?.onTabSelect(targetView.tabItem)
                        currentSelectIndex = index
                    }
                }
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}

/**
 * tab点击回调
 */
interface TabClickListener {
    fun onTabSelect(tab: MainTabItem)
    fun onTabBeforeSelect(tab: MainTabItem): Boolean {
        return true
    }

    fun onTabRepeatSelect(tab: MainTabItem) {}
    fun onTabUnSelect(tab: MainTabItem) {}
}

/**
 * 首页导航栏tab
 */
class MainTabItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private lateinit var tabView: View
    private lateinit var tabImage: ImageView
    private lateinit var tabText: TextView
    lateinit var tabItem: MainTabItem

    fun initItemTab(tab: MainTabItem, selected: Boolean = false) {
        tabItem = tab
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

    private fun generateSelectorDrawable(
        selectImgResId: Int,
        unSelectImgId: Int
    ): StateListDrawable {
        val imageStateDrawable = StateListDrawable()
        imageStateDrawable.addState(
            intArrayOf(android.R.attr.state_selected), context.getDrawable(selectImgResId)
        )
        imageStateDrawable.addState(
            intArrayOf(-android.R.attr.state_selected), context.getDrawable(unSelectImgId)
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
    var id: Int,
    var tabText: String,
    var selectTexColor: Int,
    var unSelectTextColor: Int,
    var selectImgResId: Int,
    var unSelectImgResId: Int
) {
    //TODO 支持网络图片
    var selectImgUrl: String? = null
    var unSelectImgUrl: String? = null
}
