package com.example.administrator.kotlintest.entity.date;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by HaoBoy on 2018/8/22.
 */
@Entity
public class AreaSelectedHistoryData {

    @Id
    private Long id;

    private int cityId;

    private int wareHouserId;

    private String code;

    private String name;

    private Long insertTime;

    @Keep
    public AreaSelectedHistoryData(int cityId, int wareHouserId, String code, String name, Long insertTime) {
        this.cityId = cityId;
        this.wareHouserId = wareHouserId;
        this.code = code;
        this.name = name;
        this.insertTime = insertTime;
    }

    @Generated(hash = 398167524)
    public AreaSelectedHistoryData() {
    }

    @Generated(hash = 914602157)
    public AreaSelectedHistoryData(Long id, int cityId, int wareHouserId, String code, String name,
            Long insertTime) {
        this.id = id;
        this.cityId = cityId;
        this.wareHouserId = wareHouserId;
        this.code = code;
        this.name = name;
        this.insertTime = insertTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getWareHouserId() {
        return wareHouserId;
    }

    public void setWareHouserId(int wareHouserId) {
        this.wareHouserId = wareHouserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getinsertTime() {
        return insertTime;
    }

    public void setinsertTime(Long insertTime) {
        this.insertTime = insertTime;
    }

    public Long getInsertTime() {
        return this.insertTime;
    }

    public void setInsertTime(Long insertTime) {
        this.insertTime = insertTime;
    }
}
