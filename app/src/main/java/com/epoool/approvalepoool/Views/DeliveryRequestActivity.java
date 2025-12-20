package com.epoool.approvalepoool.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.epoool.approvalepoool.Adapters.AdapterDeliveryRequest;
import com.epoool.approvalepoool.Models.DeliveryRequest;
import com.epoool.approvalepoool.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

public class DeliveryRequestActivity extends AppCompatActivity implements DeliveryRequestPresenter.ViewDeliveryRequest {
    DeliveryRequestPresenter presenter;
    private RecyclerView recyclerView;
    private SwipyRefreshLayout srl;
    private TextView tvKosong;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_delivery_request);
        getSupportActionBar().setTitle("Delivery Request");

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.srl = (SwipyRefreshLayout) findViewById(R.id.srl_temp);
        this.tvKosong = (TextView) findViewById(R.id.tv_kosong);
        this.fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);

        this.srl.setRefreshing(true);
        this.presenter = new DeliveryRequestPresenter(this);
        loadData();

        this.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DeliveryRequestActivity.this, "Add new delivery request", Toast.LENGTH_SHORT).show();
                // TODO: Add logic to create new delivery request
            }
        });

        this.srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                loadData();
            }
        });

    }

    private void loadData() {
        this.presenter.loadData("", "");
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

            }

            @Override
            public void onDeleteClick(View v, int position, DeliveryRequest data) {

            }
        }));
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void afterRequest(int i, String str) {
        if (i == 1) {
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        }
    }
}
