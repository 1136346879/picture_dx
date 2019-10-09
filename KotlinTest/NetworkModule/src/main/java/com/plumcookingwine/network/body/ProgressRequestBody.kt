package com.plumcookingwine.network.body

import com.plumcookingwine.network.callback.UploadProgressListener
import java.io.IOException

import io.reactivex.disposables.Disposable
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import okio.BufferedSink
import okio.ForwardingSink
import okio.Okio
import okio.Sink

/**
 * 上传进度
 */
open class ProgressRequestBody(//实际起作用的RequestBody
    private val delegate: RequestBody, //进度回调接口
    private val progressListener:  UploadProgressListener
) : RequestBody() {
    private var countingSink: CountingSink? = null

    override fun contentType(): MediaType? {
        return delegate.contentType()
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        countingSink = CountingSink(sink)
        //将CountingSink转化为BufferedSink供writeTo()使用
        val bufferedSink = Okio.buffer(countingSink!!)
        delegate.writeTo(bufferedSink)
        bufferedSink.flush()
    }

    protected inner class CountingSink(delegate: Sink) : ForwardingSink(delegate) {
        private var byteWritten: Long = 0

        /**
         * 上传时调用该方法,在其中调用回调函数将上传进度暴露出去,该方法提供了缓冲区的自己大小
         * @param source
         * @param byteCount
         * @throws IOException
         */
        @Throws(IOException::class)
        override fun write(source: Buffer, byteCount: Long) {
            super.write(source, byteCount)
            byteWritten += byteCount
            progressListener.onProgress(byteWritten, contentLength())
        }
    }

    /**
     * 返回文件总的字节大小
     * 如果文件大小获取失败则返回-1
     * @return
     */
    override fun contentLength(): Long {
        return try {
            delegate.contentLength()
        } catch (e: IOException) {
            -1
        }

    }
}