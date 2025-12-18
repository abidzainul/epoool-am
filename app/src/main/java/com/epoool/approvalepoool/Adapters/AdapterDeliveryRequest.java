package com.epoool.approvalepoool.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.epoool.approvalepoool.Models.PengalihanModel;
import com.epoool.approvalepoool.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterDeliveryRequest extends RecyclerView.Adapter<AdapterDeliveryRequest.ViewHolder>{
    private ArrayList<PengalihanModel> listdata;
    private final AdapterDeliveryRequest.OnItemClickListener listener;
    private boolean enable = true;

    public AdapterDeliveryRequest(ArrayList<PengalihanModel> listdata, AdapterDeliveryRequest.OnItemClickListener listener) {
        this.listdata = listdata;
        this.listener = listener;
    }

    @Override
    public AdapterDeliveryRequest.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.item_delivery_request, parent, false);
        return new AdapterDeliveryRequest.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterDeliveryRequest.ViewHolder holder, int position) {
        final PengalihanModel data = listdata.get(position);

        holder.tvDistibutor.setText(data.getNamaDistributor());
        holder.tvJenisMuatan.setText(data.getNamaJenisMuatan());
        holder.tvQty.setText(data.getTotalReal());
        holder.tvTanggal.setText(data.getTanggalKirim());
        holder.tvFrom.setText(data.getKotaAsal());
        holder.tvTo.setText(data.getKotaBaru());
        holder.tvAddressFrom.setText(data.getAlamatLama());
        holder.tvAddressTo.setText(data.getAlamatBaru());

        holder.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position, data);
            }
        });

    }

    public List<PengalihanModel> getList(){
        return listdata;
    }

    public void clear(){
        listdata.clear();
        notifyDataSetChanged();
    }

    public void setListData(ArrayList<PengalihanModel> listdata){
        this.listdata.clear();
        this.listdata = listdata;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        boolean onItemClick(View v, int position, PengalihanModel data);
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
