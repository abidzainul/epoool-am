package com.epoool.approvalepoool.Views;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;
import com.epoool.approvalepoool.Models.PengalihanModel;
import com.epoool.approvalepoool.R;
import com.epoool.approvalepoool.Utils.Constant;
import com.epoool.approvalepoool.Utils.Function;
import com.epoool.approvalepoool.Utils.GsonConverter;
import com.epoool.approvalepoool.Views.DetailPengalihanPresenter;

/* loaded from: classes.dex */
public class DetailPengalihanActivity extends AppCompatActivity implements DetailPengalihanPresenter.ViewDetailPengalihan {
    private Button btnSetuju;
    private Button btnTolak;
    private Context context;
    private LinearLayout llButtonBar;
    private PengalihanModel pengalihan;
    private DetailPengalihanPresenter presenter;
    private TextView tvAlamatAwal;
    private TextView tvAlamatTujuan;
    private TextView tvAlasan;
    private TextView tvDist;
    private TextView tvDistAwal;
    private TextView tvDistBaru;
    private TextView tvJenisMuatanAtas;
    private TextView tvJumlahAtas;
    private TextView tvNoBooking;
    private TextView tvNoShipto;
    private TextView tvNoShiptoAwal;
    private TextView tvOrigin;
    private TextView tvStatus;
    private TextView tvTanggalSpj;
    private TextView tvTglPengajuan;
    private TextView tvTujuanAwal;
    private TextView tvTujuanBaru;
    private TextView tv_qty;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail_pengalihan);
        this.tvNoBooking = (TextView) findViewById(R.id.tv_no_booking);
        this.tvTglPengajuan = (TextView) findViewById(R.id.tv_tanggal_pengajuan);
        this.tvTujuanBaru = (TextView) findViewById(R.id.tv_tujuan_baru);
        this.tvNoShipto = (TextView) findViewById(R.id.tv_no_shipto);
        this.tvAlamatTujuan = (TextView) findViewById(R.id.tv_alamat_tujuan);
        this.tvTujuanAwal = (TextView) findViewById(R.id.tv_tujuan_awal);
        this.tvNoShiptoAwal = (TextView) findViewById(R.id.tv_no_shipto_awal);
        this.tvAlamatAwal = (TextView) findViewById(R.id.tv_alamat_awal);
        this.tvAlasan = (TextView) findViewById(R.id.tv_alasan);
        this.tvStatus = (TextView) findViewById(R.id.tv_status);
        this.tvOrigin = (TextView) findViewById(R.id.tv_origin);
        this.btnTolak = (Button) findViewById(R.id.btn_tolak);
        this.btnSetuju = (Button) findViewById(R.id.btn_setuju);
        this.llButtonBar = (LinearLayout) findViewById(R.id.ll_button_bar);
        this.tvJumlahAtas = (TextView) findViewById(R.id.tv_jumlah_atas);
        this.tvJenisMuatanAtas = (TextView) findViewById(R.id.tv_jenis_muatan_atas);
        this.tv_qty = (TextView) findViewById(R.id.tv_qty);
        this.tvDist = (TextView) findViewById(R.id.tv_dist);
        this.tvDistAwal = (TextView) findViewById(R.id.tv_dist_awal);
        this.tvDistBaru = (TextView) findViewById(R.id.tv_dist_baru);
        this.tvTanggalSpj = (TextView) findViewById(R.id.tv_tanggal_spj);
        this.context = this;
        this.pengalihan = new GsonConverter<PengalihanModel>() { // from class: com.epoool.approvalepoool.Views.DetailPengalihanActivity.1
        }.toJsonObject(getIntent().getStringExtra("pengalihan_string"));
        setData();
        this.presenter = new DetailPengalihanPresenter(this);
        this.btnTolak.setOnClickListener(new View.OnClickListener() { // from class: com.epoool.approvalepoool.Views.DetailPengalihanActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                presenter.updateStatus(pengalihan.getIdPengalihan(), ExifInterface.GPS_MEASUREMENT_2D);
            }
        });
        this.btnSetuju.setOnClickListener(new View.OnClickListener() { // from class: com.epoool.approvalepoool.Views.DetailPengalihanActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (pengalihan.getIncoterm().equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    presenter.updateStatus(pengalihan.getIdPengalihan(), ExifInterface.GPS_MEASUREMENT_3D);
                    return;
                }
                if (!pengalihan.getIncoterm().equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    presenter.updateStatus(pengalihan.getIdPengalihan(), "1");
                } else if (Constant.tipe_sub_user.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    presenter.updateStatus(pengalihan.getIdPengalihan(), "6");
                } else {
                    presenter.updateStatus(pengalihan.getIdPengalihan(), "1");
                }
            }
        });
    }

    void setData() {
        if (this.pengalihan.getStatusApproval().equals("0") || this.pengalihan.getStatusApproval().equals("6")) {
            this.tvStatus.setVisibility(View.GONE);
            this.llButtonBar.setVisibility(View.VISIBLE);
        } else {
            this.tvStatus.setVisibility(View.VISIBLE);
            this.llButtonBar.setVisibility(View.GONE);
            String statusApproval = this.pengalihan.getStatusApproval();
            statusApproval.hashCode();
            if (statusApproval.equals("1")) {
                this.tvStatus.setText("APPROVED");
                this.tvStatus.setBackgroundColor(getResources().getColor(R.color.green_approved));
            } else if (statusApproval.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                this.tvStatus.setText("REJECTED");
                this.tvStatus.setBackgroundColor(getResources().getColor(R.color.bpRed));
            } else {
                this.tvStatus.setText("");
            }
        }
        String strTranslateDay = Function.translateDay(this.pengalihan.getTanggalPengajuan());
        String strTranslateDay2 = Function.translateDay(this.pengalihan.getTanggalKirim());
        this.tvTglPengajuan.setText(strTranslateDay + ", " + Function.toTanggalBaru(this.pengalihan.getTanggalPengajuan()));
        this.tvTanggalSpj.setText(strTranslateDay2 + ", " + Function.toTanggalBaru(this.pengalihan.getTanggalKirim()));
        this.tvNoBooking.setText("No. SPJ: " + this.pengalihan.getNoSpj());
        this.tvTujuanBaru.setText(this.pengalihan.getReceiverBaru());
        this.tvNoShipto.setText("No. Ref: " + this.pengalihan.getIdReceiverBaru());
        this.tvAlamatTujuan.setText(this.pengalihan.getAlamatBaru());
        this.tvTujuanAwal.setText(this.pengalihan.getReceiverLama());
        this.tvNoShiptoAwal.setText("No. Ref: " + this.pengalihan.getIdReceiverLama());
        this.tvAlamatAwal.setText(this.pengalihan.getAlamatLama());
        this.tvAlasan.setText(this.pengalihan.getAlasan());
        this.tvOrigin.setText(this.pengalihan.getNamaOriginator());
        this.tvJenisMuatanAtas.setText(this.pengalihan.getNamaJenisMuatan());
        this.tv_qty.setText(this.pengalihan.getTotalReal() + " " + this.pengalihan.getSatuan());
        this.tvDist.setText(this.pengalihan.getNamaDistributor());
        this.tvDistAwal.setText(this.pengalihan.getNamaDistributor() + " (" + this.pengalihan.getKdDistributor() + ")");
        this.tvDistBaru.setText(this.pengalihan.getNamaDistributorBaru() + " (" + this.pengalihan.getKdDistributorBaru() + ")");
    }

    @Override // com.epoool.approvalepoool.Views.DetailPengalihanPresenter.ViewDetailPengalihan
    public void afterApproved(int i, String str) {
        if (i == 1) {
            Toast.makeText(this.context, str, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this.context, str, Toast.LENGTH_SHORT).show();
        }
    }
}
