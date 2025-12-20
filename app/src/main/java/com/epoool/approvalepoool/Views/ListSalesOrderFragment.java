package com.epoool.approvalepoool.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.epoool.approvalepoool.Adapters.AdapterSalesOrder;
import com.epoool.approvalepoool.Models.SalesOrder;
import com.epoool.approvalepoool.R;
import com.epoool.approvalepoool.Utils.Function;
import com.epoool.approvalepoool.Utils.GsonConverter;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

public class ListSalesOrderFragment extends Fragment implements ListSalesOrderPresenter.ViewListSalesOrder {
    private CardView cardCari;
    Context context;
    private EditText editSearch;
    ListSalesOrderPresenter presenter;
    private RecyclerView rcSalesOrder;
    String search = "";
    private SwipyRefreshLayout srl;
    private TextView tvKosong;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_sales_order, viewGroup, false);

        this.rcSalesOrder = (RecyclerView) viewInflate.findViewById(R.id.rc_pengalihan);
        this.srl = (SwipyRefreshLayout) viewInflate.findViewById(R.id.srl_temp);
        this.tvKosong = (TextView) viewInflate.findViewById(R.id.tv_kosong);

        this.srl.setRefreshing(true);
        getActivity().setTitle("Delivery Request");
        this.context = getActivity();
        this.presenter = new ListSalesOrderPresenter(this);
        loadData();

        this.srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() { 
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                loadData();
            }
        });

        return viewInflate;
    }

    private void loadData(){
        this.presenter.loadData("", "", "");
//        if (Constant.tipe_sub_user.equals("5")) {
//            this.presenter.loadData("6", this.search);
//        } else {
//            this.presenter.loadData("0", this.search);
//        }
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
        if (i != 1 || list.size() == 0) {
            this.tvKosong.setVisibility(View.VISIBLE);
        } else {
            this.tvKosong.setVisibility(View.GONE);
        }
        this.rcSalesOrder.setAdapter(new AdapterSalesOrder(new ArrayList<>(list), new AdapterSalesOrder.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, SalesOrder data) {
                String jsonString = new GsonConverter().toJsonString(data);
                Intent intent = new Intent(context, DeliveryRequestActivity.class);
                intent.putExtra("so", jsonString);
                context.startActivity(intent);
                Function.openAct(context);
            }
        }));
        this.rcSalesOrder.setNestedScrollingEnabled(false);
        this.rcSalesOrder.setLayoutManager(new LinearLayoutManager(this.context));
    }
}
