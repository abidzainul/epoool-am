package com.epoool.approvalepoool.Models;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiverModel {

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("contact")
    @Expose
    private String contact;

    @SerializedName(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)
    @Expose
    private ReceiverModel data;

    /* renamed from: id, reason: collision with root package name */
    @SerializedName("id")
    @Expose
    private String f5id;

    @SerializedName("id_kota")
    @Expose
    private String idKota;

    @SerializedName("id_provinsi")
    @Expose
    private String idProvinsi;

    @SerializedName("kode_distributor")
    @Expose
    private String kodeDistributor;

    @SerializedName("kode_receiver")
    @Expose
    private String kodeReceiver;

    @SerializedName("lat")
    @Expose
    private String lat;

    @SerializedName("lng")
    @Expose
    private String lng;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("nama_distributor")
    @Expose
    private String namaDistributor;

    @SerializedName("nama_kota")
    @Expose
    private String namaKota;

    @SerializedName("nama_provinsi")
    @Expose
    private String namaProvinsi;

    @SerializedName("pesan")
    @Expose
    private String pesan;

    public String getId() {
        return this.f5id;
    }

    public void setId(String str) {
        this.f5id = str;
    }

    public String getKodeReceiver() {
        return this.kodeReceiver;
    }

    public void setKodeReceiver(String str) {
        this.kodeReceiver = str;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String str) {
        this.nama = str;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String str) {
        this.alamat = str;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String str) {
        this.contact = str;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String str) {
        this.lat = str;
    }

    public String getLng() {
        return this.lng;
    }

    public void setLng(String str) {
        this.lng = str;
    }

    public String getKodeDistributor() {
        return this.kodeDistributor;
    }

    public void setKodeDistributor(String str) {
        this.kodeDistributor = str;
    }

    public String getNamaDistributor() {
        return this.namaDistributor;
    }

    public void setNamaDistributor(String str) {
        this.namaDistributor = str;
    }

    public String getIdKota() {
        return this.idKota;
    }

    public void setIdKota(String str) {
        this.idKota = str;
    }

    public String getNamaKota() {
        return this.namaKota;
    }

    public void setNamaKota(String str) {
        this.namaKota = str;
    }

    public String getIdProvinsi() {
        return this.idProvinsi;
    }

    public void setIdProvinsi(String str) {
        this.idProvinsi = str;
    }

    public String getNamaProvinsi() {
        return this.namaProvinsi;
    }

    public void setNamaProvinsi(String str) {
        this.namaProvinsi = str;
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

    public ReceiverModel getData() {
        return this.data;
    }

    public void setData(ReceiverModel receiverModel) {
        this.data = receiverModel;
    }
}
