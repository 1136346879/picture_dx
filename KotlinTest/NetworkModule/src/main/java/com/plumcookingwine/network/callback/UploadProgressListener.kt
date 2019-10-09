package com.plumcookingwine.network.callback

interface UploadProgressListener {
    /**
     * 上传进度
     * @param currentBytesCount
     * @param totalBytesCount
     */
    fun onProgress(currentBytesCount: Long, totalBytesCount: Long)
}