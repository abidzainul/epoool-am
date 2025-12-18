package com.epoool.approvalepoool.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class InsertUpdateModel {

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("pesan")
    @Expose
    private String pesan;

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer num) {
        this.code = num;
    }

    public String getPesan() {
        return this.pesan;
    }

    public void setPesan(String str) {
        this.pesan = str;
    }
}
