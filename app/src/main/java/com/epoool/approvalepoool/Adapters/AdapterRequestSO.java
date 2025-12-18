package com.epoool.approvalepoool.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.epoool.approvalepoool.Models.PengalihanModel;
import com.epoool.approvalepoool.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterRequestSO extends RecyclerView.Adapter<AdapterRequestSO.ViewHolder>{
    private ArrayList<PengalihanModel> listdata;
    private final AdapterRequestSO.OnItemClickListener listener;
    private boolean enable = true;

    public AdapterRequestSO(ArrayList<PengalihanModel> listdata, AdapterRequestSO.OnItemClickListener listener) {
        this.listdata = listdata;
        this.listener = listener;
    }

    @Override
    public AdapterRequestSO.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.item_request_so, parent, false);
        return new AdapterRequestSO.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRequestSO.ViewHolder holder, int position) {
        final PengalihanModel data = listdata.get(position);

        holder.noRequest.setText(data.getNamaDistributor());
        holder.qty.setText(data.getNamaJenisMuatan());
        holder.sendDate.setText(data.getTotalReal());
        holder.note.setText(data.getTanggalKirim());
        holder.noResi.setText(data.getKotaBaru());

        if(data.getResiBaru() != null){
            holder.buttonDelete.setVisibility(View.GONE);
            holder.noResi.setVisibility(View.VISIBLE);
        } else {
            holder.buttonDelete.setVisibility(View.VISIBLE);
            holder.noResi.setVisibility(View.GONE);
        }

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
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
        public TextView noRequest, qty, sendDate, note, noResi;
        public Button buttonDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            this.noRequest = (TextView) itemView.findViewById(R.id.noRequest);
            this.qty = (TextView) itemView.findViewById(R.id.qty);
            this.sendDate = (TextView) itemView.findViewById(R.id.sendDate);
            this.note = (TextView) itemView.findViewById(R.id.note);
            this.noResi = (TextView) itemView.findViewById(R.id.noResi);
            this.buttonDelete = (Button) itemView.findViewById(R.id.buttonDelete);
        }
    }

}
