package com.epoool.approvalepoool.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.epoool.approvalepoool.Models.SearchModel;
import com.epoool.approvalepoool.R;
import java.util.List;

/* loaded from: classes.dex */
public class AdapterSearchList extends RecyclerView.Adapter<AdapterSearchList.CustomViewHolder> {
    private List<SearchModel> data;
    OnListClickListener itemListener;
    private int tipe;

    public interface OnListClickListener {
        void onClicked(String str, int i);
    }

    public void setOnItemClickListener(OnListClickListener onListClickListener) {
        this.itemListener = onListClickListener;
    }

    public AdapterSearchList(List<SearchModel> list, Context context, int i) {
        this.data = list;
        this.tipe = i;
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

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {
        int i2 = this.tipe;
        if (i2 == 1) {
            customViewHolder.item_list.setText(this.data.get(i).getNoReferensi() + ", " + this.data.get(i).getNama());
        } else if (i2 == 2) {
            customViewHolder.item_list.setText(this.data.get(i).getNoSpj());
        }
        customViewHolder.linear_item.setOnClickListener(new View.OnClickListener() { // from class: com.epoool.approvalepoool.Adapters.AdapterSearchList.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AdapterSearchList.this.tipe == 1) {
                    AdapterSearchList.this.itemListener.onClicked(((SearchModel) AdapterSearchList.this.data.get(i)).getNoReferensi(), i);
                } else if (AdapterSearchList.this.tipe == 2) {
                    AdapterSearchList.this.itemListener.onClicked(((SearchModel) AdapterSearchList.this.data.get(i)).getNoSpj(), i);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }
}
