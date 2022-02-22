package com.baize.cookhelper.test

class Person(val name: String){
  var age: Int = 0
    private set(value) {
      //do something
      field = value
    }

  val isAdult
    get() = age >= 18

  //思考，为什么不能写成：
//   val isAdult = age >= 18

}

interface TestInterface {
  var canWall : Boolean

  //接口成员属性局限性：
  //1.不能赋初始值
  //2.不能重写setter：Property in an interface cannot have a backing field
  //本质上也是类方法（反编译结论）
}

class TestClass() : TestInterface {
  override var canWall: Boolean = false
    get() = field
    set(value) {
      field = value
    }
}
