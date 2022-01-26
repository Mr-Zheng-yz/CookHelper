package com.baize.cookhelper.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.baize.cookhelper.MainViewModel
import com.baize.cookhelper.test.jetpack.ParamsViewModel

/**
 *  author : baize
 *  date : 2022/1/6 11:37 上午
 *  description :
 */
object ViewModelFactory {

  fun getMainViewModel(viewModelStoreOwner: ViewModelStoreOwner): MainViewModel {
    return ViewModelProvider(viewModelStoreOwner).get(MainViewModel::class.java)
  }

  fun getParamsViewModel(viewModelStoreOwner: ViewModelStoreOwner, testParams: Int): ParamsViewModel {
    return ViewModelProvider(viewModelStoreOwner, ParamsViewModelFactory(testParams)).get(ParamsViewModel::class.java)
    //return ParamsViewModelFactory(testParams).create(ParamsViewModel::class.java) //错误示范
  }

}

class ParamsViewModelFactory(private val testParams: Int) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return ParamsViewModel(testParams) as T
  }
}