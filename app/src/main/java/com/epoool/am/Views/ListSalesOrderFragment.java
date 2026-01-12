package com.epoool.am.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import com.epoool.am.Adapters.AdapterSalesOrder;
import com.epoool.am.Models.SalesOrder;
import com.epoool.am.R;
import com.epoool.am.Utils.Function;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ListSalesOrderFragment extends Fragment implements ListSalesOrderPresenter.ViewListSalesOrder {
    Context context;
    private EditText etDateStart;
    private EditText etDateEnd;
    private EditText etPlant;
    ListSalesOrderPresenter presenter;
    private RecyclerView rcSalesOrder;
    private SwipyRefreshLayout srl;
    private TextView tvKosong;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    Gson gson = new GsonBuilder().create();

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_sales_order, viewGroup, false);

        this.rcSalesOrder = viewInflate.findViewById(R.id.rc_pengalihan);
        this.srl = viewInflate.findViewById(R.id.srl_temp);
        this.tvKosong = viewInflate.findViewById(R.id.tv_kosong);
        this.etDateStart = viewInflate.findViewById(R.id.etDateStart);
        this.etDateEnd = viewInflate.findViewById(R.id.etDateEnd);
        this.etPlant = viewInflate.findViewById(R.id.etPlant);
        Button btnSearch = viewInflate.findViewById(R.id.btn_search);

        this.srl.setRefreshing(true);
        if (getActivity() != null) {
            getActivity().setTitle("Delivery Request");
        }
        this.context = getActivity();
        this.presenter = new ListSalesOrderPresenter(this);
        loadData();

        etDateStart.setInputType(InputType.TYPE_NULL);
        etDateStart.setOnClickListener(v -> showDatePickerDialog(etDateStart));

        etDateEnd.setInputType(InputType.TYPE_NULL);
        etDateEnd.setOnClickListener(v -> showDatePickerDialog(etDateEnd));

        btnSearch.setOnClickListener(v -> {
            loadData();
        });

        this.srl.setOnRefreshListener(swipyRefreshLayoutDirection -> {
            loadData();
        });

        return viewInflate;
    }

    private void loadData(){
        String dateStart = etDateStart.getText().toString().trim();
        String dateEnd = etDateEnd.getText().toString().trim();
        String plant = etPlant.getText().toString().trim();
        this.presenter.loadData(plant, dateStart, dateEnd);
    }

    @Override 
    public void onResume() {
        super.onResume();
        this.srl.setRefreshing(true);
        loadData();
    }

    @Override
    public void showSalesOrder(final List<SalesOrder> list, int i, String str) {
        this.srl.setRefreshing(false);
        if (i != 1 || list.isEmpty()) {
            this.tvKosong.setVisibility(View.VISIBLE);
        } else {
            this.tvKosong.setVisibility(View.GONE);
        }
        this.rcSalesOrder.setAdapter(new AdapterSalesOrder(new ArrayList<>(list), (v, position, data) -> {
            Intent intent = new Intent(context, DeliveryRequestActivity.class);
            intent.putExtra("so_data", gson.toJson(data, SalesOrder.class));
            context.startActivity(intent);
            Function.openAct(context);
        }));
        this.rcSalesOrder.setNestedScrollingEnabled(false);
        this.rcSalesOrder.setLayoutManager(new LinearLayoutManager(this.context));
    }

    private void showDatePickerDialog(final EditText editText) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
            (dialog, year, monthOfYear, dayOfMonth) -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                String formattedDate = dateFormat.format(calendar.getTime());
                editText.setText(formattedDate);
            },
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getParentFragmentManager(), "DatePickerDialog");
    }
}
