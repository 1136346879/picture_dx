package com.plumcookingwine.network.func

import com.plumcookingwine.network.helper.NetworkHelper
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * 超时重连
 */
class RetryWhenFunc(
    private val count: Int = 3,
    private val delay: Long = 3000,
    private val increaseDelay: Long = 3000
) : Function<Observable<Throwable>, ObservableSource<*>> {

    override fun apply(t: Observable<Throwable>): ObservableSource<*> {

        return t.zipWith(Observable.range(1, count + 1),
            BiFunction<Throwable, Int, Wrapper> { t1, t2 ->
                Wrapper(t1, t2)
            })
            .flatMap {

                /**
                 * 如果有网络并且连接超时才重试
                 *
                 * 注： 如果没有网络就不会重试哦
                 */
                if (
                    NetworkHelper.instance.hasNetWork() &&
                    (it.throwable is ConnectException ||
                            it.throwable is SocketTimeoutException ||
                            it.throwable is TimeoutException) &&
                    it.index < count + 1
                ) {
                    Observable.timer(
                        delay + (it.index - 1) * increaseDelay,
                        TimeUnit.MILLISECONDS
                    )
                } else {
                    Observable.error<Throwable>(it.throwable)
                }
            }
    }

    private inner class Wrapper(val throwable: Throwable, val index: Int)

}