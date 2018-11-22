package com.example.administrator.kotlintest

abstract class A {
    var x = 5

 abstract   fun hello()
}

interface B {
    var y: Int

    fun hello()
}

interface C
interface D
abstract class E
class Z(override var y: Int) : A(),B,C {
    override fun hello() {

    }


}