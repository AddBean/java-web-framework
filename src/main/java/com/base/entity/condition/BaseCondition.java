package com.base.entity.condition;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/9.
 */
public class BaseCondition {

    private int cIndex;
    private int cOffset;
    private Date cStartTime;
    private Date cEndTime;
    private boolean cDesc;

    public int getcIndex() {
        return cIndex;
    }

    public void setcIndex(int cIndex) {
        this.cIndex = cIndex;
    }

    public int getcOffset() {
        return cOffset;
    }

    public void setcOffset(int cOffset) {
        this.cOffset = cOffset;
    }

    public Date getcStartTime() {
        return cStartTime;
    }

    public void setcStartTime(Date cStartTime) {
        this.cStartTime = cStartTime;
    }

    public Date getcEndTime() {
        return cEndTime;
    }

    public void setcEndTime(Date cEndTime) {
        this.cEndTime = cEndTime;
    }

    public boolean iscDesc() {
        return cDesc;
    }

    public void setcDesc(boolean cDesc) {
        this.cDesc = cDesc;
    }
}
