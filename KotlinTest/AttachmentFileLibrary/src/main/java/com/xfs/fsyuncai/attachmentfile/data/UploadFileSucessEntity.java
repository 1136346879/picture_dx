package com.xfs.fsyuncai.attachmentfile.data;


import java.io.Serializable;

public class UploadFileSucessEntity implements Serializable {

    /**
     * fullPath : https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/common/20190523100422954355.jpg
     * fileName : 20190322143736.jpg
     * fileSize : 490.95KB
     * errorInfo : 图片上传成功...
     * errorCode : 0
     * reletivePath : common/20190523100422954355.jpg
     */
    private String fullPath;
    private String fileName;
    private String fileSize;
    private String errorInfo;
    private String errorCode;
    private String reletivePath;
    private String originPath;
    private String time;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOriginPath() {
        return originPath;
    }

    public void setOriginPath(String originPath) {
        this.originPath = originPath;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getReletivePath() {
        return reletivePath;
    }

    public void setReletivePath(String reletivePath) {
        this.reletivePath = reletivePath;
    }
}
