package com.baize.cookhelper.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
  println("hello kotlin")

  networkTest()
}

fun networkTest() {
  runBlocking {
    val url = "https://www.baidu.com"
    val client = OkHttpClient.Builder()
      .build()
    val request = Request.Builder()
      .url(url)
      .build()
    withContext(Dispatchers.IO){
      try {
        val response = client.newCall(request).execute()
        println("result:${response.code} ${response.body?.string()}")
      } catch (e: Exception) {
        println("e:${e.message}")
      }
    }
  }

}