package com.baize.cookhelper.test.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.baize.cookhelper.R

class LifecycleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_lifecycle)

    //添加观察者
    lifecycle.addObserver(ObserverDemo())

//    Handler(Looper.getMainLooper()).postDelayed({ lifecycle.addObserver(ObserverDemo()) },5000)
  }

  fun addObsever(v: View) {
    lifecycle.addObserver(ObserverDemo())
  }
}

// 观察者👀
// 当被观察者（Activity，Fragment）触发生命周期事件时，会调用对应注解修饰的方法（六种Event）
class ObserverDemo : LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
  fun any() {
//    Log.i("baize_lifecycle","Event#ON_ANY")
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun create() {
    Log.i("baize_lifecycle","Event#ON_CREATE")
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_START)
  fun start() {
    Log.i("baize_lifecycle","Event#ON_START")
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun resume() {
    Log.i("baize_lifecycle","Event#ON_RESUME")
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  fun pause() {
    Log.i("baize_lifecycle","Event#ON_PAUSE")
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun stop() {
    Log.i("baize_lifecycle","Event#ON_STOP")
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  fun destory() {
    Log.i("baize_lifecycle","Event#ON_DESTROY")
  }

}


