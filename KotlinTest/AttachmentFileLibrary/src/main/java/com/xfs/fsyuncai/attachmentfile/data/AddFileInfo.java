package com.xfs.fsyuncai.attachmentfile.data;

import java.io.Serializable;

public class AddFileInfo implements Serializable {

    private String name;
    private String size;
    private String path;

    public AddFileInfo(String name, String size, String path) {
        this.name = name;
        this.size = size;
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
