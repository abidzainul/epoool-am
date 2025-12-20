package com.epoool.approvalepoool.Views;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.InputType;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.epoool.approvalepoool.Adapters.AdapterDeliveryRequest;
import com.epoool.approvalepoool.Models.DeliveryRequest;
import com.epoool.approvalepoool.Models.SalesOrder;
import com.epoool.approvalepoool.R;
import com.epoool.approvalepoool.Views.dialog.DialogConfirm;
import com.epoool.approvalepoool.Views.dialog.DialogInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DeliveryRequestActivity extends AppCompatActivity implements DeliveryRequestPresenter.ViewDeliveryRequest {
    SalesOrder dataSo;
    DeliveryRequestPresenter presenter;
    private RecyclerView recyclerView;
    private SwipyRefreshLayout srl;
    private TextView tvKosong;
    private FloatingActionButton fabAdd;
    Gson gson = new GsonBuilder().create();
    final String TAG = "DeliveryRequestActivity";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_delivery_request);
        getSupportActionBar().setTitle("Delivery Request");

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.srl = (SwipyRefreshLayout) findViewById(R.id.srl_temp);
        this.tvKosong = (TextView) findViewById(R.id.tv_kosong);
        this.fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);

        String soJson = getIntent().getStringExtra("so_data");
        Log.d(TAG, "onCreate: dataSo=" + soJson);
        this.dataSo = gson.fromJson(soJson, SalesOrder.class);

        this.srl.setRefreshing(true);
        this.presenter = new DeliveryRequestPresenter(this);
        loadData();

        this.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDeliveryDialog();
            }
        });

        this.srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                loadData();
            }
        });

    }

    private void showAddDeliveryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DeliveryRequestActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_delivery_req, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        Button btnSave = dialogView.findViewById(R.id.btn_save);
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);

        // find form fields (assumes these IDs exist in dialog_add_delivery_req.xml)
        EditText etSendDate = dialogView.findViewById(R.id.etSendDate);
        EditText etQtyTruck = dialogView.findViewById(R.id.etQtyTruck);
        EditText etQty = dialogView.findViewById(R.id.etQty);
        EditText etCatatan = dialogView.findViewById(R.id.etNote);

        etQtyTruck.setInputType(InputType.TYPE_CLASS_NUMBER);
        etQty.setInputType(InputType.TYPE_CLASS_NUMBER);
        etCatatan.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);

        etSendDate.setInputType(InputType.TYPE_NULL);
        etSendDate.setFocusable(false);
        final Calendar calendar = Calendar.getInstance();
        etSendDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH);
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dp = new DatePickerDialog(DeliveryRequestActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selected = Calendar.getInstance();
                        selected.set(year, month, dayOfMonth);
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        etSendDate.setText(fmt.format(selected.getTime()));
                    }
                }, y, m, d);
                dp.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sendDate = etSendDate.getText() != null ? etSendDate.getText().toString().trim() : "";
                String qtyTruck = etQtyTruck.getText() != null ? etQtyTruck.getText().toString().trim() : "";
                String qty = etQty.getText() != null ? etQty.getText().toString().trim() : "";
                String catatan = etCatatan.getText() != null ? etCatatan.getText().toString().trim() : "";

                if (sendDate.isEmpty()) {
                    Toast.makeText(DeliveryRequestActivity.this, "Please select send date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (qtyTruck.isEmpty()) {
                    Toast.makeText(DeliveryRequestActivity.this, "Please enter qtyTruck", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (qty.isEmpty()) {
                    Toast.makeText(DeliveryRequestActivity.this, "Please enter qty", Toast.LENGTH_SHORT).show();
                    return;
                }

                String noSo = dataSo.getNoSo();
                String lineSo = dataSo.getLine();
                presenter.saveData(noSo, lineSo, qty, sendDate, catatan);

                Toast.makeText(DeliveryRequestActivity.this, "Saving...", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void loadData() {
        String noSo = dataSo.getNoSo();
        String lineSo = dataSo.getLine();
        this.presenter.loadData(noSo, lineSo);
    }

    @Override
    public void showDeliveryRequest(List<DeliveryRequest> list, int i, String str) {
        this.srl.setRefreshing(false);
        if (i != 1 || list.size() == 0) {
            this.tvKosong.setVisibility(View.VISIBLE);
        } else {
            this.tvKosong.setVisibility(View.GONE);
        }
        this.recyclerView.setAdapter(new AdapterDeliveryRequest(new ArrayList<>(list), new AdapterDeliveryRequest.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, DeliveryRequest data) {
                new DialogInfo(DeliveryRequestActivity.this)
                        .setTitle("Success!")
                        .setMessage("Data saved successfully")
                        .setDialogType(DialogInfo.DialogType.SUCCESS)
                        .setButtonText("OKE")
                        .setOnOKListener(() -> {
                            // Action after OK clicked
                        })
                        .show();
            }

            @Override
            public void onDeleteClick(View v, int position, DeliveryRequest data) {
                new DialogConfirm(DeliveryRequestActivity.this)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure you want to delete this item?")
                        .setDialogType(DialogConfirm.DialogType.ERROR)
                        .setConfirmText("Delete")
                        .setCancelText("Cancel")
                        .setOnConfirmListener(() -> {

                        })
                        .setOnCancelListener(() -> {

                        })
                        .show();
            }
        }));
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void afterRequest(int i, String str) {
        if (i == 1) {
            if (str == null) {
                str = "Your delivery request has been submitted successfully!";
            }
            new DialogConfirm(DeliveryRequestActivity.this)
                    .setTitle("Success")
                    .setMessage(str)
                    .setDialogType(DialogConfirm.DialogType.SUCCESS)
                    .setConfirmText("Done")
                    .setOnConfirmListener(this::finish)
                    .show();
        } else {
            new DialogConfirm(DeliveryRequestActivity.this)
                    .setTitle("Error")
                    .setMessage(str)
                    .setDialogType(DialogConfirm.DialogType.ERROR)
                    .setConfirmText("Retry")
                    .setCancelText("Dismiss")
                    .setOnConfirmListener(() -> {

                    })
                    .show();
        }
    }
}
