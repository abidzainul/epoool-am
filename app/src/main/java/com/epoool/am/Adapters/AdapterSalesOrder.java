package com.epoool.am.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.epoool.am.Models.SalesOrder;
import com.epoool.am.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterSalesOrder extends RecyclerView.Adapter<AdapterSalesOrder.ViewHolder>{
    private ArrayList<SalesOrder> listdata;
    private final AdapterSalesOrder.OnItemClickListener listener;
    private boolean enable = true;

    public AdapterSalesOrder(ArrayList<SalesOrder> listdata, AdapterSalesOrder.OnItemClickListener listener) {
        this.listdata = listdata;
        this.listener = listener;
    }

    @Override
    public AdapterSalesOrder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.item_sales_order, parent, false);
        return new AdapterSalesOrder.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterSalesOrder.ViewHolder holder, int position) {
        final SalesOrder data = listdata.get(position);

        holder.tvDistibutor.setText(data.getDistributor());
        holder.tvJenisMuatan.setText(data.getMaterial());
        holder.tvQty.setText(data.getTotalReleasedQty());
        holder.tvTanggal.setText(data.getDeliveryDate());
        holder.tvFrom.setText(data.getNamaGudang());
//        holder.tvAddressFrom.setText(data.getAddressShipTo());
        holder.tvTo.setText(data.getNameShipTo());
        holder.tvAddressTo.setText(data.getAddressShipTo());

        holder.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position, data);
            }
        });

    }

    public List<SalesOrder> getList(){
        return listdata;
    }

    public void clear(){
        listdata.clear();
        notifyDataSetChanged();
    }

    public void setListData(ArrayList<SalesOrder> listdata){
        this.listdata.clear();
        this.listdata = listdata;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position, SalesOrder data);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDistibutor, tvJenisMuatan, tvTanggal, tvQty;
        public TextView tvFrom, tvAddressFrom, tvTo, tvAddressTo;
        public Button btnRequest;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tvDistibutor = (TextView) itemView.findViewById(R.id.tv_distibutor);
            this.tvTanggal = (TextView) itemView.findViewById(R.id.tv_tanggal);
            this.tvJenisMuatan = (TextView) itemView.findViewById(R.id.tv_jenis_muatan);
            this.tvQty = (TextView) itemView.findViewById(R.id.tv_qty);
            this.tvFrom = (TextView) itemView.findViewById(R.id.tv_from);
            this.tvAddressFrom = (TextView) itemView.findViewById(R.id.tv_address_from);
            this.tvTo = (TextView) itemView.findViewById(R.id.tv_to);
            this.tvAddressTo = (TextView) itemView.findViewById(R.id.tv_address_to);
            this.btnRequest = (Button) itemView.findViewById(R.id.btnRequest);
        }
    }

}
