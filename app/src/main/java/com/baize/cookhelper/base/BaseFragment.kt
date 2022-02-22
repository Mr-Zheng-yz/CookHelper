package com.baize.cookhelper.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *  author : baize
 *  date : 2022/1/26 3:09 下午
 *  description :
 */
abstract class BaseFragment<T : ViewBinding> : Fragment() {

  protected lateinit var binding: T

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    //通过反射方式，调用ViewBinding的 inflate 方法，需要注意版本兼容性
    val type = javaClass.genericSuperclass as ParameterizedType
    val aClass = type.actualTypeArguments[0] as Class<*>
    val method = aClass.getDeclaredMethod(
      "inflate",
      LayoutInflater::class.java,
      ViewGroup::class.java,
      Boolean::class.java
    )
    binding = method.invoke(null, layoutInflater, container, false) as T
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    loadData()
  }

  open fun initView(containerView: View) {}

  open fun loadData() {}

}