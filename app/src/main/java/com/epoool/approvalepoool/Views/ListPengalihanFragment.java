package com.epoool.approvalepoool.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.epoool.approvalepoool.Adapters.AdapterPengalihan;
import com.epoool.approvalepoool.Models.PengalihanModel;
import com.epoool.approvalepoool.R;
import com.epoool.approvalepoool.Utils.Constant;
import com.epoool.approvalepoool.Utils.Function;
import com.epoool.approvalepoool.Utils.GsonConverter;
import com.epoool.approvalepoool.Views.ListPengalihanPresenter;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import java.util.List;

/* loaded from: classes.dex */
public class ListPengalihanFragment extends Fragment implements ListPengalihanPresenter.ViewListPengalihan {
    private CardView cardCari;
    Context context;
    private EditText editSearch;
    ListPengalihanPresenter presenter;
    private RecyclerView rcPengalihan;
    String search = "";
    private SwipyRefreshLayout srl;
    private TextView tvKosong;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_list_pengalihan, viewGroup, false);
        this.rcPengalihan = (RecyclerView) viewInflate.findViewById(R.id.rc_pengalihan);
        this.srl = (SwipyRefreshLayout) viewInflate.findViewById(R.id.srl_temp);
        this.tvKosong = (TextView) viewInflate.findViewById(R.id.tv_kosong);
        this.cardCari = (CardView) viewInflate.findViewById(R.id.card_cari);
        this.editSearch = (EditText) viewInflate.findViewById(R.id.edit_search);
        this.srl.setRefreshing(true);
        getActivity().setTitle("Approval Pengalihan");
        this.context = getActivity();
        this.cardCari.setVisibility(View.VISIBLE);
        this.presenter = new ListPengalihanPresenter(this);
        if (Constant.tipe_sub_user.equals("5")) {
            this.presenter.loadPengalihan("6", this.search);
        } else {
            this.presenter.loadPengalihan("0", this.search);
        }
        this.srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() { // from class: com.epoool.approvalepoool.Views.ListPengalihanFragment.1
            @Override // com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout.OnRefreshListener
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                if (Constant.tipe_sub_user.equals("5")) {
                    presenter.loadPengalihan("6", search);
                } else {
                    presenter.loadPengalihan("0", search);
                }
            }
        });
        this.editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.epoool.approvalepoool.Views.ListPengalihanFragment.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 3) {
                    srl.setRefreshing(true);
                    search = textView.getText().toString();
                    if (Constant.tipe_sub_user.equals("5")) {
                        presenter.loadPengalihan("6", search);
                    } else {
                        presenter.loadPengalihan("0", search);
                    }
                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
                }
                return false;
            }
        });
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.srl.setRefreshing(true);
        if (Constant.tipe_sub_user.equals("5")) {
            this.presenter.loadPengalihan("6", this.search);
        } else {
            this.presenter.loadPengalihan("0", this.search);
        }
    }

    @Override // com.epoool.approvalepoool.Views.ListPengalihanPresenter.ViewListPengalihan
    public void showPengalihan(final List<PengalihanModel> list, int i, String str) {
        this.srl.setRefreshing(false);
        if (i != 1 || list.size() == 0) {
            this.tvKosong.setVisibility(View.VISIBLE);
        } else {
            this.tvKosong.setVisibility(View.GONE);
        }
        this.rcPengalihan.setAdapter(new AdapterPengalihan(list, this.context, new AdapterPengalihan.Listener() { // from class: com.epoool.approvalepoool.Views.ListPengalihanFragment.3
            @Override // com.epoool.approvalepoool.Adapters.AdapterPengalihan.Listener
            public void onItemClick(int i2) {
                String jsonString = new GsonConverter().toJsonString(list.get(i2));
                Intent intent = new Intent(context, (Class<?>) DetailPengalihanActivity.class);
                intent.putExtra("pengalihan_string", jsonString);
                context.startActivity(intent);
                Function.openAct(context);
            }
        }));
        this.rcPengalihan.setNestedScrollingEnabled(false);
        this.rcPengalihan.setLayoutManager(new LinearLayoutManager(this.context));
    }
}
