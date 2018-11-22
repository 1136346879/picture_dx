package com.example.administrator.kotlintest.entity.date;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kangf on 2018/7/18.
 */

@Entity
public class AccountData {

    @Id
    private Long id;

    private String username;

    private String password;

    private Long time;

    @Keep
    public AccountData(String username, String password, Long time) {
        this.username = username;
        this.password = password;
        this.time = time;
    }

    @Keep
    public AccountData(Long id, String username, String password, Long time) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.time = time;
    }

    @Generated(hash = 691197240)
    public AccountData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
