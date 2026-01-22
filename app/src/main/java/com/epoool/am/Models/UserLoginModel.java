package com.epoool.am.Models;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserLoginModel {

    @SerializedName("add_by")
    @Expose
    private String addBy;

    @SerializedName("android_imei")
    @Expose
    private String androidImei;

    @SerializedName("android_sn")
    @Expose
    private String androidSn;

    @SerializedName("bahasa")
    @Expose
    private String bahasa;

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)
    @Expose
    private List<UserLoginModel> data;

    @SerializedName("date_add")
    @Expose
    private String dateAdd;

    @SerializedName("deleted")
    @Expose
    private String deleted;

    @SerializedName("edited_by")
    @Expose
    private String editedBy;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("id_parent")
    @Expose
    private String idParent;

    @SerializedName("id_reference")
    @Expose
    private String idReference;

    @SerializedName("id_username")
    @Expose
    private String idUsername;

    @SerializedName("last_edited")
    @Expose
    private String lastEdited;

    @SerializedName("last_role_change")
    @Expose
    private String lastRoleChange;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("no_hp")
    @Expose
    private String noHp;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("pesan")
    @Expose
    private String pesan;

    @SerializedName("status_login")
    @Expose
    private String statusLogin;

    @SerializedName("sub_user")
    @Expose
    private String subUser;

    @SerializedName("tipe")
    @Expose
    private String tipe;

    @SerializedName("tipe_sub_user")
    @Expose
    private String tipeSubUser;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("token_fcm")
    @Expose
    private String tokenFcm;

    @SerializedName("user_token")
    @Expose
    private String userToken;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("versi_foto")
    @Expose
    private String versiFoto;

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

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public UserLoginModel getData() {
        return this.data.get(0);
    }

//    public void setData(UserLoginModel userLoginModel) {
//        this.data = userLoginModel;
//    }

    public String getIdUsername() {
        return this.idUsername;
    }

    public void setIdUsername(String str) {
        this.idUsername = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getNoHp() {
        return this.noHp;
    }

    public void setNoHp(String str) {
        this.noHp = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getTipe() {
        return this.tipe;
    }

    public void setTipe(String str) {
        this.tipe = str;
    }

    public String getIdReference() {
        return this.idReference;
    }

    public void setIdReference(String str) {
        this.idReference = str;
    }

    public String getDeleted() {
        return this.deleted;
    }

    public void setDeleted(String str) {
        this.deleted = str;
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

    public String getTokenFcm() {
        return this.tokenFcm;
    }

    public void setTokenFcm(String str) {
        this.tokenFcm = str;
    }

    public String getStatusLogin() {
        return this.statusLogin;
    }

    public void setStatusLogin(String str) {
        this.statusLogin = str;
    }

    public String getBahasa() {
        return this.bahasa;
    }

    public void setBahasa(String str) {
        this.bahasa = str;
    }

    public String getAndroidImei() {
        return this.androidImei;
    }

    public void setAndroidImei(String str) {
        this.androidImei = str;
    }

    public String getAndroidSn() {
        return this.androidSn;
    }

    public void setAndroidSn(String str) {
        this.androidSn = str;
    }

    public String getSubUser() {
        return this.subUser;
    }

    public void setSubUser(String str) {
        this.subUser = str;
    }

    public String getIdParent() {
        return this.idParent;
    }

    public void setIdParent(String str) {
        this.idParent = str;
    }

    public String getLastRoleChange() {
        return this.lastRoleChange;
    }

    public void setLastRoleChange(String str) {
        this.lastRoleChange = str;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String str) {
        this.foto = str;
    }

    public String getVersiFoto() {
        return this.versiFoto;
    }

    public void setVersiFoto(String str) {
        this.versiFoto = str;
    }

    public String getUserToken() {
        return this.userToken;
    }

    public void setUserToken(String str) {
        this.userToken = str;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String str) {
        this.nama = str;
    }

    public String getTipeSubUser() {
        if(this.tipeSubUser == null){
            return "5";
        }
        return this.tipeSubUser;
    }

    public void setTipeSubUser(String str) {
        this.tipeSubUser = str;
    }
}
