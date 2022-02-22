package com.baize.cookhelper.network

import com.baize.cookhelper.network.data.ArticleBean
import com.baize.cookhelper.network.data.ResponseResult
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *  author : baize
 *  date : 2022/2/22 2:39 下午
 *  description :
 */
interface WanService {


  @GET("article/list/{page}/json")
  suspend fun getArticleList(@Path("page") page: Int): ResponseResult<ArticleBean>

}