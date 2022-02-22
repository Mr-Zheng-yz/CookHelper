package com.baize.cookhelper.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
  println("hello kotlin")

//  networkTest()
  calculate()
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

fun calculate() {
  while (true) {
    val input = readLine() ?: continue
    if (input == "exit") return
    val inputList = input.split(" ")
    val result = calculate(inputList)
    if (result == null) {
      println("格式错误")
    } else {
      println("$input = $result")
    }

  }
}

fun calculate(list: List<String>): Int? {
  if (list.size != 3) return null
  val left = list[0].toInt()
  val operation = Operation.valueOf(list[1])
  val right = list[2].toInt()
  return when(operation) {
    Operation.ADD -> left + right
    Operation.MINUS -> left - right
    Operation.MULTI -> left * right
    Operation.DIVI -> left / right
  }
}

enum class Operation(val value: String){
  ADD("+"),
  MINUS("-"),
  MULTI("*"),
  DIVI("/")
}