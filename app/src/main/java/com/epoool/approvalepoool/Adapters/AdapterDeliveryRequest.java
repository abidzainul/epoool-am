package com.epoool.approvalepoool.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.epoool.approvalepoool.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterDeliveryRequest extends RecyclerView.Adapter<AdapterDeliveryRequest.ViewHolder>{
    private ArrayList<KompBonus> listdata;
    private final AdapterDeliveryRequest.OnItemClickListener listener;
    private boolean enable = true;

    public AdapterDeliveryRequest(ArrayList<KompBonus> listdata, AdapterDeliveryRequest.OnItemClickListener listener) {
        this.listdata = listdata;
        this.listener = listener;
    }

    @Override
    public AdapterDeliveryRequest.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.rv_komp_bonus, parent, false);
        return new AdapterDeliveryRequest.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterDeliveryRequest.ViewHolder holder, int position) {
        final KompBonus data = listdata.get(position);

        holder.tvTitle.setText(name);
        holder.tvSyaratPosm.setText("-");
        holder.tvSyaratSellin.setText("-");


        if(enable) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idx = holder.getAdapterPosition();
                    boolean checked = listener.onItemClick(v, idx, data);
                    setSelected(idx, checked);
                }
            });
        }
    }

    public List<KompBonus> getList(){
        return listdata;
    }

    public void clear(){
        listdata.clear();
        notifyDataSetChanged();
    }

    public void updateItem(int index, KompBonus newValue){
        listdata.set(index, newValue);
        notifyItemChanged(index);
    }

    public void setListData(ArrayList<KompBonus> listdata){
        this.listdata.clear();
        this.listdata = listdata;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        boolean onItemClick(View v, int position, KompBonus data);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvSyaratPosm, tvSyaratSellin, tvReward, tvNotes;
        public CheckBox checkBox;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            this.tvSyaratPosm = (TextView) itemView.findViewById(R.id.tvSyaratPOSM);
            this.tvSyaratSellin = (TextView) itemView.findViewById(R.id.tvSyaratSellin);
            this.tvReward = (TextView) itemView.findViewById(R.id.tvReward);
            this.checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            this.tvNotes = (TextView) itemView.findViewById(R.id.tvNotes);
        }
    }

}
