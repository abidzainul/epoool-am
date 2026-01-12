package com.epoool.am.Models;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PengalihanModel {

    @SerializedName("alamat_baru")
    @Expose
    private String alamatBaru;

    @SerializedName("alamat_lama")
    @Expose
    private String alamatLama;

    @SerializedName("alasan")
    @Expose
    private String alasan;

    @SerializedName("approved_by")
    @Expose
    private String approvedBy;

    @SerializedName("approved_date")
    @Expose
    private String approvedDate;

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("created_by")
    @Expose
    private String createdBy;

    @SerializedName(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)
    @Expose
    private List<PengalihanModel> data;

    @SerializedName("id_distributor")
    @Expose
    private String idDistributor;

    @SerializedName("id_distributor_baru")
    @Expose
    private String idDistributorBaru;

    @SerializedName("id_do_main")
    @Expose
    private String idDoMain;

    @SerializedName("id_do_main_new")
    @Expose
    private String idDoMainNew;

    @SerializedName("id_gudang")
    @Expose
    private String idGudang;

    @SerializedName("id_pengalihan")
    @Expose
    private String idPengalihan;

    @SerializedName("id_receiver_baru")
    @Expose
    private String idReceiverBaru;

    @SerializedName("id_receiver_lama")
    @Expose
    private String idReceiverLama;

    @SerializedName("incoterm")
    @Expose
    private String incoterm;

    @SerializedName("kd_distributor")
    @Expose
    private String kdDistributor;

    @SerializedName("kd_distributor_baru")
    @Expose
    private String kdDistributorBaru;

    @SerializedName("kd_gudang")
    @Expose
    private String kdGudang;

    @SerializedName("kota_asal")
    @Expose
    private String kotaAsal;

    @SerializedName("kota_baru")
    @Expose
    private String kotaBaru;

    @SerializedName("nama_distributor")
    @Expose
    private String namaDistributor;

    @SerializedName("nama_distributor_baru")
    @Expose
    private String namaDistributorBaru;

    @SerializedName("nama_gudang")
    @Expose
    private String namaGudang;

    @SerializedName("nama_jenis_muatan")
    @Expose
    private String namaJenisMuatan;

    @SerializedName("nama_originator")
    @Expose
    private String namaOriginator;

    @SerializedName("no_spj")
    @Expose
    private String noSpj;

    @SerializedName("pesan")
    @Expose
    private String pesan;

    @SerializedName("receiver_baru")
    @Expose
    private String receiverBaru;

    @SerializedName("receiver_lama")
    @Expose
    private String receiverLama;

    @SerializedName("resi_baru")
    @Expose
    private String resiBaru;

    @SerializedName("resi_lama")
    @Expose
    private String resiLama;

    @SerializedName("satuan")
    @Expose
    private String satuan;

    @SerializedName("status_approval")
    @Expose
    private String statusApproval;

    @SerializedName("tanggal_kirim")
    @Expose
    private String tanggalKirim;

    @SerializedName("tanggal_pengajuan")
    @Expose
    private String tanggalPengajuan;

    @SerializedName("total_real")
    @Expose
    private String totalReal;

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

    public List<PengalihanModel> getData() {
        return this.data;
    }

    public void setData(List<PengalihanModel> list) {
        this.data = list;
    }

    public String getIdPengalihan() {
        return this.idPengalihan;
    }

    public void setIdPengalihan(String str) {
        this.idPengalihan = str;
    }

    public String getIdDoMain() {
        return this.idDoMain;
    }

    public void setIdDoMain(String str) {
        this.idDoMain = str;
    }

    public String getResiLama() {
        return this.resiLama;
    }

    public void setResiLama(String str) {
        this.resiLama = str;
    }

    public String getIdDoMainNew() {
        return this.idDoMainNew;
    }

    public void setIdDoMainNew(String str) {
        this.idDoMainNew = str;
    }

    public String getResiBaru() {
        return this.resiBaru;
    }

    public void setResiBaru(String str) {
        this.resiBaru = str;
    }

    public String getIdReceiverLama() {
        return this.idReceiverLama;
    }

    public void setIdReceiverLama(String str) {
        this.idReceiverLama = str;
    }

    public String getReceiverLama() {
        return this.receiverLama;
    }

    public void setReceiverLama(String str) {
        this.receiverLama = str;
    }

    public String getKotaAsal() {
        return this.kotaAsal;
    }

    public void setKotaAsal(String str) {
        this.kotaAsal = str;
    }

    public String getIdReceiverBaru() {
        return this.idReceiverBaru;
    }

    public void setIdReceiverBaru(String str) {
        this.idReceiverBaru = str;
    }

    public String getReceiverBaru() {
        return this.receiverBaru;
    }

    public void setReceiverBaru(String str) {
        this.receiverBaru = str;
    }

    public String getKotaBaru() {
        return this.kotaBaru;
    }

    public void setKotaBaru(String str) {
        this.kotaBaru = str;
    }

    public String getAlasan() {
        return this.alasan;
    }

    public void setAlasan(String str) {
        this.alasan = str;
    }

    public String getStatusApproval() {
        return this.statusApproval;
    }

    public void setStatusApproval(String str) {
        this.statusApproval = str;
    }

    public String getApprovedBy() {
        return this.approvedBy;
    }

    public void setApprovedBy(String str) {
        this.approvedBy = str;
    }

    public String getApprovedDate() {
        return this.approvedDate;
    }

    public void setApprovedDate(String str) {
        this.approvedDate = str;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String str) {
        this.createdBy = str;
    }

    public String getTanggalPengajuan() {
        return this.tanggalPengajuan;
    }

    public void setTanggalPengajuan(String str) {
        this.tanggalPengajuan = str;
    }

    public String getAlamatBaru() {
        return this.alamatBaru;
    }

    public void setAlamatBaru(String str) {
        this.alamatBaru = str;
    }

    public String getAlamatLama() {
        return this.alamatLama;
    }

    public void setAlamatLama(String str) {
        this.alamatLama = str;
    }

    public String getNoSpj() {
        return this.noSpj;
    }

    public void setNoSpj(String str) {
        this.noSpj = str;
    }

    public String getIncoterm() {
        return this.incoterm;
    }

    public void setIncoterm(String str) {
        this.incoterm = str;
    }

    public String getIdGudang() {
        return this.idGudang;
    }

    public void setIdGudang(String str) {
        this.idGudang = str;
    }

    public String getNamaGudang() {
        return this.namaGudang;
    }

    public void setNamaGudang(String str) {
        this.namaGudang = str;
    }

    public String getKdGudang() {
        return this.kdGudang;
    }

    public void setKdGudang(String str) {
        this.kdGudang = str;
    }

    public String getIdDistributor() {
        return this.idDistributor;
    }

    public void setIdDistributor(String str) {
        this.idDistributor = str;
    }

    public String getNamaDistributor() {
        return this.namaDistributor;
    }

    public void setNamaDistributor(String str) {
        this.namaDistributor = str;
    }

    public String getKdDistributor() {
        return this.kdDistributor;
    }

    public void setKdDistributor(String str) {
        this.kdDistributor = str;
    }

    public String getNamaOriginator() {
        return this.namaOriginator;
    }

    public void setNamaOriginator(String str) {
        this.namaOriginator = str;
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

    public String getNamaJenisMuatan() {
        return this.namaJenisMuatan;
    }

    public void setNamaJenisMuatan(String str) {
        this.namaJenisMuatan = str;
    }

    public String getIdDistributorBaru() {
        return this.idDistributorBaru;
    }

    public void setIdDistributorBaru(String str) {
        this.idDistributorBaru = str;
    }

    public String getNamaDistributorBaru() {
        return this.namaDistributorBaru;
    }

    public void setNamaDistributorBaru(String str) {
        this.namaDistributorBaru = str;
    }

    public String getKdDistributorBaru() {
        return this.kdDistributorBaru;
    }

    public void setKdDistributorBaru(String str) {
        this.kdDistributorBaru = str;
    }

    public String getTanggalKirim() {
        return this.tanggalKirim;
    }

    public void setTanggalKirim(String str) {
        this.tanggalKirim = str;
    }
}
