package com.baize.cookhelper.test

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baize.cookhelper.R
import com.baize.cookhelper.network.data.TArticle
import com.bumptech.glide.Glide

/**
 *  author : baize
 *  date : 2022/1/26 3:19 下午
 *  description : 列表相关测试类
 */
class ListAdapter : RecyclerView.Adapter<CardViewHolder>() {
  private var dataList = arrayListOf<TArticle>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.test_item_card, null)
    return CardViewHolder(view)
  }

  override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
    holder.bindData(dataList[position], position)
  }

  override fun onBindViewHolder(holder: CardViewHolder, position: Int, payloads: MutableList<Any>) {
    if (payloads.isEmpty()) {
      onBindViewHolder(holder, position)
    } else {
      // item 的增量更新
      val payload = payloads[0] as Bundle
      payload.keySet().forEach { key ->
        when (key) {
          "imageUrl" -> Glide.with(holder.imageView.context).load(payload.get(key)).into(holder.imageView)
          "title" -> holder.textView.text = payload.getString("title")
        }
      }
    }
  }

  override fun getItemCount() = dataList.size

  //DiffUtil
  fun swapData(newList: ArrayList<TArticle>, diff: Boolean) {
    Log.i("baize_", "oldList:$dataList")
    Log.i("baize_", "newList:${newList}")
    if (diff) {
      //detectMoves:考虑item移动的情况，会将算法复杂度提升到O(n^2)
      val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(TArticleDiffCallback(dataList, newList), false)
      this.dataList = newList
      diffResult.dispatchUpdatesTo(this)
    } else {
      dataList = newList
      notifyDataSetChanged()
    }
  }

  fun addData(data: TArticle) {
    this.dataList.add(data)
  }

  fun addDataList(dataList: List<TArticle>) {
    this.dataList.addAll(dataList)
  }

  fun clear() {
    this.dataList.clear()
  }

  val preVisibleViews = mutableListOf<RecyclerView.ViewHolder>()

  override fun onViewAttachedToWindow(holder: CardViewHolder) {
    super.onViewAttachedToWindow(holder)
    val rect = Rect()
//    val isVisible = holder.itemView.getGlobalVisibleRect(rect)
//    if (isVisible) {
//      Log.i("haha", "isVisible ${holder.adapterPosition}" )
//    }
    for (i in preVisibleViews.size - 1 downTo 0) {
      preVisibleViews[i].run {
        val isVisible = itemView.getGlobalVisibleRect(rect)
            && rect.height() > holder.itemView.height / 3  //露出3分之一才上报
        if (isVisible) {
          Log.i("baize_", "上报:${adapterPosition}")
          preVisibleViews.removeAt(i)
        }
      }
    }
    holder.itemView.post {
      val isVisible = holder.itemView.getGlobalVisibleRect(rect)
          && rect.height() > holder.itemView.height / 3  //露出3分之一才上报
      if (isVisible) {
        Log.i("baize_", "上报:${holder.adapterPosition}")
      } else {
        preVisibleViews.add(holder)
      }
//      Log.i("haha", "rectHeight:${rect.height()} viewHeight:${holder.itemView.height / 3}")
      Log.i("haha", "onViewAttachedToWindow ${holder.adapterPosition} ${holder.itemView} ${isVisible}" )
    }
  }

  override fun onViewDetachedFromWindow(holder: CardViewHolder) {
    super.onViewDetachedFromWindow(holder)
    Log.i("haha", "onViewDetachedFromWindow ${holder.adapterPosition}" )
    preVisibleViews.remove(holder)
  }

}

class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  var imageView: ImageView = itemView.findViewById<ImageView>(R.id.iv_img)
  val textView: TextView = itemView.findViewById<TextView>(R.id.tv_text)

  fun bindData(data: TArticle, position: Int) {
    Glide.with(imageView.context).load(data.imageUrl).into(imageView)
    textView.setText(data.title)
  }

}