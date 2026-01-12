package com.epoool.am.Models;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchModel {

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)
    @Expose
    private List<SearchModel> data = null;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("no_referensi")
    @Expose
    private String noReferensi;

    @SerializedName("no_spj")
    @Expose
    private String noSpj;

    @SerializedName("pesan")
    @Expose
    private String pesan;

    public String getNoSpj() {
        return this.noSpj;
    }

    public void setNoSpj(String str) {
        this.noSpj = str;
    }

    public String getNoReferensi() {
        return this.noReferensi;
    }

    public void setNoReferensi(String str) {
        this.noReferensi = str;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String str) {
        this.nama = str;
    }

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

    public List<SearchModel> getData() {
        return this.data;
    }

    public void setData(List<SearchModel> list) {
        this.data = list;
    }
}
