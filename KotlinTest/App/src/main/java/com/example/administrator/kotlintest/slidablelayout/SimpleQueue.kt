package com.example.administrator.kotlintest.slidablelayout

/**
 * Created by 张宇 on 2019/5/6.
 * E-mail: zhangyu4@yy.com
 * YY: 909017428
 */
interface SimpleQueue<Element> : List<Element> {

    fun moveToNext()

    fun moveToPrev()

    fun next(): Element?

    fun current(): Element?

    fun prev(): Element?
}