package com.epoool.am.Utils.drawer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import androidx.exifinterface.media.ExifInterface;
import com.epoool.am.R;
import com.epoool.am.Utils.Constant;
import com.epoool.am.Utils.Function;
import com.epoool.am.Views.FormPengalihanFragment;
import com.epoool.am.Views.HistoryPengalihanFragment;
import com.epoool.am.Views.ListSalesOrderFragment;
import com.epoool.am.Views.ListPengalihanFragment;
import com.epoool.am.Views.MainActivity;
import java.util.ArrayList;

public class AdapterListViewDrawer extends BaseAdapter {
    private Context context;
    private ArrayList<String> list;

    @Override 
    public Object getItem(int i) {
        return null;
    }

    @Override 
    public long getItemId(int i) {
        return 0L;
    }

    public AdapterListViewDrawer(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.list = arrayList;
    }

    @Override 
    public int getCount() {
        return this.list.size();
    }

    @Override 
    public View getView(int i, View view, ViewGroup viewGroup) {
        String str = this.list.get(i);
        if (str.equals("0")) {
            view = ((Activity) this.context).getLayoutInflater().inflate(R.layout.item_drawer_direct, viewGroup, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_logout_drawer);
            TextView textView = (TextView) view.findViewById(R.id.tv_logout_drawer);
            imageView.setImageResource(R.drawable.drawer_check);
            textView.setText("APPROVAL PENGALIHAN");
            view.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public void onClick(View view2) {
                    MainActivity.closeDrawer();
                    Function.getFragment(AdapterListViewDrawer.this.context, new ListPengalihanFragment(), Constant.holderFragment);
                }
            });
        } else if (str.equals("7")) {
            view = ((Activity) this.context).getLayoutInflater().inflate(R.layout.item_drawer_direct, viewGroup, false);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.iv_logout_drawer);
            TextView textView2 = (TextView) view.findViewById(R.id.tv_logout_drawer);
            imageView2.setImageResource(R.drawable.drawer_input);
            textView2.setText("PENGAJUAN PENGALIHAN");
            view.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public void onClick(View view2) {
                    MainActivity.closeDrawer();
                    Function.getFragment(AdapterListViewDrawer.this.context, new FormPengalihanFragment(), Constant.holderFragment);
                }
            });
        } else if (str.equals("6")) {
            view = ((Activity) this.context).getLayoutInflater().inflate(R.layout.item_drawer_direct, viewGroup, false);
            ImageView imageView3 = (ImageView) view.findViewById(R.id.iv_logout_drawer);
            TextView textView3 = (TextView) view.findViewById(R.id.tv_logout_drawer);
            imageView3.setImageResource(R.drawable.drawer_history);
            textView3.setText("RIWAYAT PENGALIHAN");
            view.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public void onClick(View view2) {
                    Function.getFragment(AdapterListViewDrawer.this.context, new HistoryPengalihanFragment(), Constant.holderFragment);
                    MainActivity.mDrawerLayout.closeDrawer(GravityCompat.START);
                }
            });
        } else if (str.equals("9")) {
            view = ((Activity) this.context).getLayoutInflater().inflate(R.layout.item_drawer_direct, viewGroup, false);
            ImageView imageView3 = (ImageView) view.findViewById(R.id.iv_logout_drawer);
            TextView textView3 = (TextView) view.findViewById(R.id.tv_logout_drawer);
            imageView3.setImageResource(R.drawable.muatan);
            imageView3.setColorFilter(Color.argb(250, 250, 250, 250));
            textView3.setText("DELIVERY REQUEST");
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    Function.getFragment(AdapterListViewDrawer.this.context, new ListSalesOrderFragment(), Constant.holderFragment);
                    MainActivity.mDrawerLayout.closeDrawer(GravityCompat.START);
                }
            });
        } else if (str.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
            final SharedPreferences sharedPreferences = this.context.getSharedPreferences("MyPreferences", 0);
            View viewInflate = ((Activity) this.context).getLayoutInflater().inflate(R.layout.item_drawer_direct, viewGroup, false);
            TextView textView4 = (TextView) viewInflate.findViewById(R.id.tv_logout_drawer);
            ((ImageView) viewInflate.findViewById(R.id.iv_logout_drawer)).setImageResource(R.drawable.drawer_logout);
            textView4.setText("LOGOUT");
            viewInflate.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public void onClick(View view2) {
                    MainActivity.closeDrawer();
                    MainActivity.logOut(sharedPreferences);
                }
            });
            view = viewInflate;
        }
        if (str.equals("1")) {
            view = ((Activity) this.context).getLayoutInflater().inflate(R.layout.header_drawer, viewGroup, false);
            TextView textView5 = (TextView) view.findViewById(R.id.tv_emblem);
            if (Constant.tipe_sub_user.equals("5")) {
                textView5.setText("Senior Manager");
            } else {
                textView5.setText("Manager");
            }
        }
        return view;
    }
}
