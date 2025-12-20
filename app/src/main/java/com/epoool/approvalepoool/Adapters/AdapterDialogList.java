package com.epoool.approvalepoool.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.epoool.approvalepoool.Models.AlasanModel;
import com.epoool.approvalepoool.R;
import java.util.List;

public class AdapterDialogList extends RecyclerView.Adapter<AdapterDialogList.CustomViewHolder> {
    private List<AlasanModel> data;
    OnListClickListener itemListener;

    public interface OnListClickListener {
        void onClicked(String str, String str2);
    }

    public void setOnItemClickListener(OnListClickListener onListClickListener) {
        this.itemListener = onListClickListener;
    }

    public AdapterDialogList(List<AlasanModel> list, Context context) {
        this.data = list;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView item_list;
        LinearLayout linear_item;

        public CustomViewHolder(View view) {
            super(view);
            this.linear_item = (LinearLayout) view.findViewById(R.id.linear_item);
            this.item_list = (TextView) view.findViewById(R.id.item_list);
        }
    }

    @Override 
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        customViewHolder.item_list.setText(this.data.get(i).getAlasanId());
        customViewHolder.linear_item.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                itemListener.onClicked(((AlasanModel) data.get(i)).getAlasanId(), ((AlasanModel) data.get(i)).getId());
            }
        });
    }

    @Override 
    public int getItemCount() {
        return this.data.size();
    }
}
