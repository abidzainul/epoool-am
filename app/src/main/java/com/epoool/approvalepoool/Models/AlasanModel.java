package com.epoool.approvalepoool.Models;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes.dex */
public class AlasanModel {

    @SerializedName("add_by")
    @Expose
    private String addBy;

    @SerializedName("alasan_en")
    @Expose
    private String alasanEn;

    @SerializedName("alasan_id")
    @Expose
    private String alasanId;

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)
    @Expose
    private List<AlasanModel> data = null;

    @SerializedName("date_add")
    @Expose
    private String dateAdd;

    @SerializedName("deleted")
    @Expose
    private String deleted;

    @SerializedName("edited_by")
    @Expose
    private String editedBy;

    /* renamed from: id, reason: collision with root package name */
    @SerializedName("id")
    @Expose
    private String f3id;

    @SerializedName("id_gudang")
    @Expose
    private String idGudang;

    @SerializedName("id_originator")
    @Expose
    private String idOriginator;

    @SerializedName("last_edited")
    @Expose
    private String lastEdited;

    @SerializedName("pesan")
    @Expose
    private String pesan;

    @SerializedName("tipe_user")
    @Expose
    private String tipeUser;

    @SerializedName("urutan")
    @Expose
    private String urutan;

    public String getId() {
        return this.f3id;
    }

    public void setId(String str) {
        this.f3id = str;
    }

    public String getTipeUser() {
        return this.tipeUser;
    }

    public void setTipeUser(String str) {
        this.tipeUser = str;
    }

    public String getAlasanId() {
        return this.alasanId;
    }

    public void setAlasanId(String str) {
        this.alasanId = str;
    }

    public String getAlasanEn() {
        return this.alasanEn;
    }

    public void setAlasanEn(String str) {
        this.alasanEn = str;
    }

    public String getUrutan() {
        return this.urutan;
    }

    public void setUrutan(String str) {
        this.urutan = str;
    }

    public String getDeleted() {
        return this.deleted;
    }

    public void setDeleted(String str) {
        this.deleted = str;
    }

    public String getDateAdd() {
        return this.dateAdd;
    }

    public void setDateAdd(String str) {
        this.dateAdd = str;
    }

    public String getLastEdited() {
        return this.lastEdited;
    }

    public void setLastEdited(String str) {
        this.lastEdited = str;
    }

    public String getAddBy() {
        return this.addBy;
    }

    public void setAddBy(String str) {
        this.addBy = str;
    }

    public String getEditedBy() {
        return this.editedBy;
    }

    public void setEditedBy(String str) {
        this.editedBy = str;
    }

    public String getIdOriginator() {
        return this.idOriginator;
    }

    public void setIdOriginator(String str) {
        this.idOriginator = str;
    }

    public String getIdGudang() {
        return this.idGudang;
    }

    public void setIdGudang(String str) {
        this.idGudang = str;
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

    public List<AlasanModel> getData() {
        return this.data;
    }

    public void setData(List<AlasanModel> list) {
        this.data = list;
    }
}
