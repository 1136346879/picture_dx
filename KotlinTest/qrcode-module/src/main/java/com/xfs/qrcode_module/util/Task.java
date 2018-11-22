package com.xfs.qrcode_module.util;


import android.renderscript.RenderScript;

/**
 * Created by hexun on 2017/10/12.
 * @author ZHANG PEIYUAN
 */

public abstract class Task implements Runnable, Comparable<Task> {

    private Priority priority = Priority.DEFAULT;
    private int sequence;

    public Task() {}

    public Task(Priority priority) {
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }



    @Override
    public int compareTo(Task it) {
        final Priority current = this.getPriority();
        final Priority another = it.getPriority();
        return current == another ? this.getSequence() - it.getSequence() :
                another.ordinal() - current.ordinal();
    }
}
