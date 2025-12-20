package com.epoool.approvalepoool.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.epoool.approvalepoool.Models.DeliveryRequest;
import com.epoool.approvalepoool.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterDeliveryRequest extends RecyclerView.Adapter<AdapterDeliveryRequest.ViewHolder>{
    private ArrayList<DeliveryRequest> listdata;
    private final AdapterDeliveryRequest.OnItemClickListener listener;
    private boolean enable = true;

    public AdapterDeliveryRequest(ArrayList<DeliveryRequest> listdata, AdapterDeliveryRequest.OnItemClickListener listener) {
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
        final DeliveryRequest data = listdata.get(position);

        holder.noRequest.setText("No. " + data.getDeliveryRequestNo());
        holder.qty.setText("Qty: " + data.getQty());
        holder.sendDate.setText(data.getDateAdd());
        holder.noResi.setText("Resi: "+data.getNoSo());

        if(!data.getNote().isEmpty()){
            holder.note.setText("Catatan: " + data.getNote());
        }

//        if(data.getNoSo() != null){
//            holder.buttonDelete.setVisibility(View.GONE);
//            holder.noResi.setVisibility(View.VISIBLE);
//        } else {
//            holder.buttonDelete.setVisibility(View.VISIBLE);
//            holder.noResi.setVisibility(View.GONE);
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position, data);
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClick(v, position, data);
            }
        });

    }

    public List<DeliveryRequest> getList(){
        return listdata;
    }

    public void clear(){
        listdata.clear();
        notifyDataSetChanged();
    }

    public void setListData(ArrayList<DeliveryRequest> listdata){
        this.listdata.clear();
        this.listdata = listdata;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position, DeliveryRequest data);
        void onDeleteClick(View v, int position, DeliveryRequest data);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView noRequest, qty, sendDate, note, noResi;
        public ImageButton buttonDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            this.noRequest = (TextView) itemView.findViewById(R.id.noRequest);
            this.qty = (TextView) itemView.findViewById(R.id.qty);
            this.sendDate = (TextView) itemView.findViewById(R.id.sendDate);
            this.note = (TextView) itemView.findViewById(R.id.note);
            this.noResi = (TextView) itemView.findViewById(R.id.noResi);
            this.buttonDelete = (ImageButton) itemView.findViewById(R.id.buttonDelete);
        }
    }

}
