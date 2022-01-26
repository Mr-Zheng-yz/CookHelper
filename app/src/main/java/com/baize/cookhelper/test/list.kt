package com.baize.cookhelper.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baize.cookhelper.R
import com.bumptech.glide.Glide

/**
 *  author : baize
 *  date : 2022/1/26 3:19 下午
 *  description : 列表相关测试类
 */
class ListAdapter : RecyclerView.Adapter<CardViewHolder>() {
  private val dataList = arrayListOf<Pair<String, String>>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.test_item_card, null)
    return CardViewHolder(view)
  }

  override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
    holder.bindData(dataList[position], position)
  }

  override fun getItemCount() = dataList.size

  fun addData(data: Pair<String, String>) {
    this.dataList.add(data)
  }

  fun addDataList(dataList: List<Pair<String, String>>) {
    this.dataList.addAll(dataList)
  }

  fun clear() {
    this.dataList.clear()
  }

}

class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  private var imageView: ImageView = itemView.findViewById<ImageView>(R.id.iv_img)
  private val textView: TextView = itemView.findViewById<TextView>(R.id.tv_text)

  fun bindData(data: Pair<String, String>, position: Int) {
    Glide.with(imageView.context).load(data.first).into(imageView)
    textView.setText(data.second)
  }

}