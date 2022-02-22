package com.baize.cookhelper.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baize.cookhelper.network.Api
import com.baize.cookhelper.network.ApiException
import com.baize.cookhelper.network.apiCall
import com.baize.cookhelper.network.data.ArticleBean
import kotlinx.coroutines.launch

/**
 *  author : baize
 *  date : 2022/2/22 2:56 下午
 *  description :
 */
class HomeViewModel : ViewModel() {

  val mArticleListData = MutableLiveData<List<ArticleBean>>()

  fun getHomeList() {
    viewModelScope.launch {
      val result = apiCall { Api.getWanAndroidApi().getArticleList(0) }
      if (result.errorCode == ApiException.CODE_SUCCESS && result.data != null) {
        mArticleListData.postValue(result.data?.datas ?: arrayListOf())
      } else {
        Log.e("network", "errCode:${result.errorCode} errMsg:${result.errorMsg}")
      }
    }
  }

}
