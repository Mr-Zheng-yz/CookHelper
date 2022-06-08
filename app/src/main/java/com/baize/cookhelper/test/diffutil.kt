package com.baize.cookhelper.test

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.baize.cookhelper.network.data.TArticle

//abstract class Callback {
//  //旧数据集长度
//  abstract fun getOldListSize(): Int
//
//  //新数据集长度
//  abstract fun getNewListSize(): Int
//
//  //是否是同一个Item
//  abstract fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
//
//  //内容是否相同
//  abstract fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
//
//  //同一个Item，内容不同，获取变化的部分
//  abstract fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any?
//}

class TArticleDiffCallback(val oldList: ArrayList<TArticle>, val newList: ArrayList<TArticle>)
  : DiffUtil.Callback() {

  override fun getOldListSize(): Int = oldList.size

  override fun getNewListSize(): Int = newList.size

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition].id == newList[newItemPosition].id
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition].id == newList[newItemPosition].id
        && oldList[oldItemPosition].imageUrl == newList[newItemPosition].imageUrl
        && oldList[oldItemPosition].title == newList[newItemPosition].title
  }

  //不实现，复用整个Item
  override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
    val bundle = Bundle()
    if (oldList[oldItemPosition].id != newList[newItemPosition].id) {
      bundle.putInt("id", newList[newItemPosition].id)
    }
    if (oldList[oldItemPosition].imageUrl != newList[newItemPosition].imageUrl) {
      bundle.putString("imageUrl", newList[newItemPosition].imageUrl)
    }
    if (oldList[oldItemPosition].title != newList[newItemPosition].title) {
      bundle.putString("imageUrl", newList[newItemPosition].title)
    }
    return bundle
  }
}
