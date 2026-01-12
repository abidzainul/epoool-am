package com.epoool.am.Views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.epoool.am.Adapters.AdapterDialogList;
import com.epoool.am.Models.AlasanModel;
import com.epoool.am.Models.DeliveryOrderModel;
import com.epoool.am.Models.ReceiverModel;
import com.epoool.am.R;
import com.epoool.am.Utils.Constant;
import com.epoool.am.Utils.Function;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;

public class FormPengalihanFragment extends Fragment implements FormPengalihanPresenter.ViewFormPengalihan {
    String alasanString;
    private Button btnCek1;
    private Button btnCek2;
    private Button btnSubmit;
    private Context context;
    private EditText etNoBooking;
    private EditText etNoShipto;
    private EditText et_alasan;
    private String idAlasan;
    private String id_receiver;
    private ProgressDialog mProgressDialog;
    private FormPengalihanPresenter presenter;
    private String resi;
    private TextInputLayout tilNoBooking;
    private TextInputLayout tilNoShipto;
    private TextView tvAlamatReceiver;
    private TextView tvButton;
    private TextView tvJumlah;
    private TextView tvKotaShiptoAwal;
    private TextView tvMuatan;
    private TextView tvNamaReceiver;
    private TextView tvNoRef;
    private TextView tvNorefAwal;
    private TextView tvShiptoAwal;

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_form_pengalihan, viewGroup, false);
        getActivity().setTitle("Pengajuan Pengalihan");
        this.tvMuatan = (TextView) viewInflate.findViewById(R.id.tv_muatan);
        this.tvJumlah = (TextView) viewInflate.findViewById(R.id.tv_jumlah);
        this.tvShiptoAwal = (TextView) viewInflate.findViewById(R.id.tv_shipto_awal);
        this.tvNamaReceiver = (TextView) viewInflate.findViewById(R.id.tv_nama_receiver);
        this.tvNoRef = (TextView) viewInflate.findViewById(R.id.tv_no_ref);
        this.tvAlamatReceiver = (TextView) viewInflate.findViewById(R.id.tv_alamat_receiver);
        this.tvNorefAwal = (TextView) viewInflate.findViewById(R.id.tv_noref_awal);
        this.tvKotaShiptoAwal = (TextView) viewInflate.findViewById(R.id.tv_kota_shipto_awal);
        this.tvButton = (TextView) viewInflate.findViewById(R.id.tv_button);
        this.etNoBooking = (EditText) viewInflate.findViewById(R.id.et_no_booking);
        this.etNoShipto = (EditText) viewInflate.findViewById(R.id.et_no_shipto);
        this.et_alasan = (EditText) viewInflate.findViewById(R.id.et_alasan);
        this.btnCek1 = (Button) viewInflate.findViewById(R.id.btn_cek1);
        this.btnCek2 = (Button) viewInflate.findViewById(R.id.btn_cek2);
        this.btnSubmit = (Button) viewInflate.findViewById(R.id.btn_submit);
        this.tilNoShipto = (TextInputLayout) viewInflate.findViewById(R.id.til_no_shipto);
        this.tilNoBooking = (TextInputLayout) viewInflate.findViewById(R.id.til_no_booking);
        this.context = getActivity();
        this.presenter = new FormPengalihanPresenter(this);
        this.btnCek1.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (!etNoBooking.getText().toString().equals("")) {
                    presenter.loadDataResi(etNoBooking.getText().toString());
                } else {
                    Toast.makeText(context, "Isi kolom No. Resi dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        this.btnCek2.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (!etNoShipto.getText().toString().equals("")) {
                    presenter.loadDataReceiver(etNoShipto.getText().toString());
                } else {
                    Toast.makeText(context, "Isi kolom No. Receiver dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        this.etNoShipto.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra(FirebaseAnalytics.Event.SEARCH, etNoShipto.getText().toString());
                intent.putExtra("tipe", 1);
                startActivityForResult(intent, 1);
                Function.openAct(context);
            }
        });
        this.etNoBooking.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra(FirebaseAnalytics.Event.SEARCH, etNoBooking.getText().toString());
                intent.putExtra("tipe", 2);
                startActivityForResult(intent, 2);
                Function.openAct(context);
            }
        });
        this.tvButton.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                showProgressDialog();
                presenter.loadAlasan();
            }
        });
        this.btnSubmit.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (resi == null || id_receiver == null || idAlasan == null) {
                    if (resi == null) {
                        Toast.makeText(context, "Isi kolom No. SPJ dahulu", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (id_receiver == null) {
                        Toast.makeText(context, "Isi kolom No. Receiver dahulu", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(context, "Isi alasan dahulu", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                FormPengalihanFragment formPengalihanFragment = FormPengalihanFragment.this;
                formPengalihanFragment.showDialog(formPengalihanFragment.context, "Yakin ingin dialihkan?", tvShiptoAwal.getText().toString(), tvNorefAwal.getText().toString(), tvKotaShiptoAwal.getText().toString(), tvNamaReceiver.getText().toString(), tvNoRef.getText().toString(), tvAlamatReceiver.getText().toString(), "YA", "TIDAK");
            }
        });
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_simpulan);
        dialog.setCancelable(true);
        dialog.setTitle(str);
        dialog.show();
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_whole);
        TextView textView = (TextView) dialog.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) dialog.findViewById(R.id.tv_tujuan_awal);
        TextView textView3 = (TextView) dialog.findViewById(R.id.tv_noref_awal);
        TextView textView4 = (TextView) dialog.findViewById(R.id.tv_kota_awal);
        TextView textView5 = (TextView) dialog.findViewById(R.id.tv_pengalihan);
        TextView textView6 = (TextView) dialog.findViewById(R.id.tv_noref);
        TextView textView7 = (TextView) dialog.findViewById(R.id.tv_alamat);
        Button button = (Button) dialog.findViewById(R.id.btn_ok);
        Button button2 = (Button) dialog.findViewById(R.id.btn_cancel);
        linearLayout.getLayoutParams().width = (Constant.height * 8) / 10;
        textView.setText(str);
        textView2.setText(str2);
        textView3.setText(str3);
        textView4.setText(str4);
        textView5.setText(str5);
        textView6.setText(str6);
        textView7.setText(str7);
        button.setText(str8);
        button2.setText(str9);
        button.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
                presenter.sendPengalihan(resi, id_receiver, idAlasan);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public AlertDialog getDialogList(AdapterDialogList adapterDialogList, String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        View viewInflate = getLayoutInflater().inflate(R.layout.dialog_list, (ViewGroup) null);
        builder.setView(viewInflate);
        TextView textView = (TextView) viewInflate.findViewById(R.id.title_dialog);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.recycler_dialog);
        textView.setText(str);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapterDialogList);
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialogCreate.show();
        return alertDialogCreate;
    }

    @Override 
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 1) {
                showProgressDialog();
                this.etNoShipto.setText(intent.getStringExtra(FirebaseAnalytics.Event.SEARCH));
                if (!this.etNoShipto.getText().toString().equals("")) {
                    this.presenter.loadDataReceiver(this.etNoShipto.getText().toString());
                    return;
                } else {
                    Toast.makeText(this.context, "Isi kolom No. Receiver dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            if (i == 2) {
                this.etNoBooking.setText(intent.getStringExtra(FirebaseAnalytics.Event.SEARCH));
                if (!this.etNoBooking.getText().toString().equals("")) {
                    this.presenter.loadDataResi(this.etNoBooking.getText().toString());
                } else {
                    Toast.makeText(this.context, "Isi kolom No. Resi dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override 
    public void showDataResi(DeliveryOrderModel deliveryOrderModel, int i, String str) {
        hideProgressDialog();
        if (i == 1) {
            this.tvMuatan.setText(deliveryOrderModel.getNamaJenisMuatan());
            this.tvJumlah.setText(deliveryOrderModel.getTotalReal() + " " + deliveryOrderModel.getSatuan());
            this.tvShiptoAwal.setText(deliveryOrderModel.getNamaReceiver());
            this.tvNorefAwal.setText(deliveryOrderModel.getKodeReceiver());
            this.tvKotaShiptoAwal.setText(deliveryOrderModel.getNamaKota());
            this.resi = this.etNoBooking.getText().toString();
            return;
        }
        Toast.makeText(this.context, str, Toast.LENGTH_SHORT).show();
    }

    @Override 
    public void showDataReceiver(ReceiverModel receiverModel, int i, String str) {
        hideProgressDialog();
        if (i == 1) {
            if (receiverModel.getLat() != null && receiverModel.getLng() != null && !receiverModel.getLat().isEmpty() && !receiverModel.getLng().isEmpty() && !receiverModel.getLat().equals("0") && !receiverModel.getLng().equals("0")) {
                this.tvNamaReceiver.setText(receiverModel.getNama());
                this.tvNoRef.setText(receiverModel.getKodeReceiver());
                this.tvAlamatReceiver.setText(receiverModel.getAlamat());
                this.id_receiver = receiverModel.getId();
                return;
            }
            Toast.makeText(this.context, "Koordinat receiver kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this.context, str, Toast.LENGTH_SHORT).show();
    }

    @Override 
    public void afterInsert(int i, String str) {
        hideProgressDialog();
        if (i == 1) {
            Toast.makeText(this.context, str, Toast.LENGTH_SHORT).show();
            this.tvMuatan.setText("-");
            this.tvJumlah.setText("-");
            this.tvShiptoAwal.setText("-");
            this.tvNorefAwal.setText("-");
            this.tvKotaShiptoAwal.setText("-");
            this.etNoBooking.setText("");
            this.resi = null;
            this.tvNamaReceiver.setText("Receiver");
            this.tvNoRef.setText("-");
            this.tvAlamatReceiver.setText("-");
            this.etNoShipto.setText("");
            this.id_receiver = null;
            this.alasanString = null;
            this.idAlasan = null;
            this.et_alasan.setText("");
            this.tvButton.setText("Pilih Alasan");
            this.tvButton.setTextColor(getResources().getColor(R.color.grey));
            return;
        }
        Toast.makeText(this.context, str, Toast.LENGTH_SHORT).show();
    }

    @Override 
    public void showAlasan(List<AlasanModel> list, int i, String str) {
        hideProgressDialog();
        if (i == 1) {
            AdapterDialogList adapterDialogList = new AdapterDialogList(list, this.context);
            final AlertDialog dialogList = getDialogList(adapterDialogList, "Pilih Alasan");
            adapterDialogList.setOnItemClickListener(new AdapterDialogList.OnListClickListener() { 
                @Override 
                public void onClicked(String str2, String str3) {
                    alasanString = str2;
                    tvButton.setText("Alasan: " + alasanString);
                    tvButton.setTextColor(getResources().getColor(R.color.bpblack));
                    idAlasan = str3;
                    dialogList.dismiss();
                }
            });
            return;
        }
        Toast.makeText(this.context, "Master data alasan kosong", Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog() {
        if (this.mProgressDialog == null) {
            ProgressDialog progressDialog = new ProgressDialog(this.context);
            this.mProgressDialog = progressDialog;
            progressDialog.setMessage("Please Wait...");
            this.mProgressDialog.setIndeterminate(false);
            this.mProgressDialog.setCancelable(false);
        }
        this.mProgressDialog.show();
    }

    public void hideProgressDialog() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        this.mProgressDialog.dismiss();
    }
}
