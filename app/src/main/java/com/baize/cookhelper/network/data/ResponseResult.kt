package com.baize.cookhelper.network.data

import com.baize.cookhelper.network.ApiException.Companion.CODE_SUCCESS

/**
 *  author : baize
 *  date : 2022/2/22 2:45 下午
 *  description :
 */
data class ResponseResult<T>(
  var errorCode: Int = 0,
  var errorMsg: String? = null,
  var data: HttpBaseListBean<T>? = null
) {

  fun isSuccess() = errorCode == CODE_SUCCESS

  fun isEmpty() = data == null

}

data class HttpBaseListBean<T>(
  var curPage: Int = 0,
  var datas: List<T>? = null,
  var offset: Int = 0,
  var over: Boolean = false,
  var pageCount: Int = 0,
  var size: Int = 0,
  var total: Int = 0
)
