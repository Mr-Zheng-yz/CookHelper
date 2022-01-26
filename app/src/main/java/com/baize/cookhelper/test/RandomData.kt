package com.baize.cookhelper.test

import kotlin.random.Random

/**
 *  author : baize
 *  date : 2022/1/26 3:44 下午
 *  description : 随即数据
 */
val imageUrls = arrayOf(
  "http://m.imeitou.com/uploads/allimg/2021012217/bsifanqt1ne.jpg",
  "http://m.imeitou.com/uploads/allimg/2021012217/itoza3rxax5.jpg",
  "http://m.imeitou.com/uploads/allimg/2021012217/gosay2r20uf.jpg",
  "http://m.imeitou.com/uploads/allimg/2021012217/zvgahtwjxf2.jpg",
  "http://m.imeitou.com/uploads/allimg/2021012217/spykduc004z.jpg",
  "http://m.imeitou.com/uploads/allimg/2021012217/vrgijcfvdo2.jpg",
  "http://m.imeitou.com/uploads/allimg/2021012217/kbd3uunersq.jpg",
  "http://m.imeitou.com/uploads/allimg/2021012217/awono2ihoqx.jpg",
)

//随机网图
fun getRandomImageUrl() : String {
  return imageUrls[(imageUrls.indices).random()]
}