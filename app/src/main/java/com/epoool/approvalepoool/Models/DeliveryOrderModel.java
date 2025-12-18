package com.epoool.approvalepoool.Models;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class DeliveryOrderModel {

    @SerializedName("alamat_receiver")
    @Expose
    private String alamatReceiver;

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("contact_receiver")
    @Expose
    private String contactReceiver;

    @SerializedName(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)
    @Expose
    private DeliveryOrderModel data;

    @SerializedName("id_jenis_muatan")
    @Expose
    private String idJenisMuatan;

    @SerializedName("id_receiver")
    @Expose
    private String idReceiver;

    @SerializedName("kode_receiver")
    @Expose
    private String kodeReceiver;

    @SerializedName("konversi_kg")
    @Expose
    private String konversiKg;

    @SerializedName("lat")
    @Expose
    private String lat;

    @SerializedName("lng")
    @Expose
    private String lng;

    @SerializedName("nama_id")
    @Expose
    private String namaId;

    @SerializedName("nama_jenis_muatan")
    @Expose
    private String namaJenisMuatan;

    @SerializedName("nama_kota")
    @Expose
    private String namaKota;

    @SerializedName("nama_receiver")
    @Expose
    private String namaReceiver;

    @SerializedName("no_referensi")
    @Expose
    private String noReferensi;

    @SerializedName("pesan")
    @Expose
    private String pesan;

    @SerializedName("resi")
    @Expose
    private String resi;

    @SerializedName("satuan")
    @Expose
    private String satuan;

    @SerializedName("status_do")
    @Expose
    private String statusDo;

    @SerializedName("tanggal_mulai_bongkar")
    @Expose
    private Object tanggalMulaiBongkar;

    @SerializedName("total_qty")
    @Expose
    private String totalQty;

    @SerializedName("total_real")
    @Expose
    private String totalReal;

    public String getResi() {
        return this.resi;
    }

    public void setResi(String str) {
        this.resi = str;
    }

    public String getIdReceiver() {
        return this.idReceiver;
    }

    public void setIdReceiver(String str) {
        this.idReceiver = str;
    }

    public Object getTanggalMulaiBongkar() {
        return this.tanggalMulaiBongkar;
    }

    public void setTanggalMulaiBongkar(Object obj) {
        this.tanggalMulaiBongkar = obj;
    }

    public String getKodeReceiver() {
        return this.kodeReceiver;
    }

    public void setKodeReceiver(String str) {
        this.kodeReceiver = str;
    }

    public String getNamaReceiver() {
        return this.namaReceiver;
    }

    public void setNamaReceiver(String str) {
        this.namaReceiver = str;
    }

    public String getAlamatReceiver() {
        return this.alamatReceiver;
    }

    public void setAlamatReceiver(String str) {
        this.alamatReceiver = str;
    }

    public String getContactReceiver() {
        return this.contactReceiver;
    }

    public void setContactReceiver(String str) {
        this.contactReceiver = str;
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

    public String getStatusDo() {
        return this.statusDo;
    }

    public void setStatusDo(String str) {
        this.statusDo = str;
    }

    public String getNamaId() {
        return this.namaId;
    }

    public void setNamaId(String str) {
        this.namaId = str;
    }

    public String getTotalQty() {
        return this.totalQty;
    }

    public void setTotalQty(String str) {
        this.totalQty = str;
    }

    public String getKonversiKg() {
        return this.konversiKg;
    }

    public void setKonversiKg(String str) {
        this.konversiKg = str;
    }

    public String getTotalReal() {
        return this.totalReal;
    }

    public void setTotalReal(String str) {
        this.totalReal = str;
    }

    public String getSatuan() {
        return this.satuan;
    }

    public void setSatuan(String str) {
        this.satuan = str;
    }

    public String getIdJenisMuatan() {
        return this.idJenisMuatan;
    }

    public void setIdJenisMuatan(String str) {
        this.idJenisMuatan = str;
    }

    public String getNamaJenisMuatan() {
        return this.namaJenisMuatan;
    }

    public void setNamaJenisMuatan(String str) {
        this.namaJenisMuatan = str;
    }

    public String getNoReferensi() {
        return this.noReferensi;
    }

    public void setNoReferensi(String str) {
        this.noReferensi = str;
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

    public DeliveryOrderModel getData() {
        return this.data;
    }

    public void setData(DeliveryOrderModel deliveryOrderModel) {
        this.data = deliveryOrderModel;
    }

    public String getNamaKota() {
        return this.namaKota;
    }

    public void setNamaKota(String str) {
        this.namaKota = str;
    }
}
