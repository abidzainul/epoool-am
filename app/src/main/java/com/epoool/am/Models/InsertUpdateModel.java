package com.epoool.am.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertUpdateModel {

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName(value = "pesan", alternate = {"message"})
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
