package com.epoool.approvalepoool.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.epoool.approvalepoool.Adapters.AdapterSearchList;
import com.epoool.approvalepoool.Models.SearchModel;
import com.epoool.approvalepoool.R;
import com.epoool.approvalepoool.Views.SearchPresenter;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchPresenter.ViewListSearch {
    Context context;
    EditText etSearch;
    private Handler handler;
    SearchPresenter presenter;
    RecyclerView rcList;
    private Runnable runnableSearch = null;
    TextView tvKosong;

    @Override 
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search);
        this.etSearch = (EditText) findViewById(R.id.et_search);
        this.rcList = (RecyclerView) findViewById(R.id.rc_list);
        this.tvKosong = (TextView) findViewById(R.id.tv_kosong);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        if (getIntent().getIntExtra("tipe", 0) == 1) {
            getSupportActionBar().setTitle("No. Receiver");
        } else if (getIntent().getIntExtra("tipe", 0) == 2) {
            getSupportActionBar().setTitle("No. SPJ");
        }
        this.context = this;
        this.etSearch.requestFocus();
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(this.etSearch, 1);
        this.presenter = new SearchPresenter(this);
        if (getIntent().getStringExtra(FirebaseAnalytics.Event.SEARCH) != null && !getIntent().getStringExtra(FirebaseAnalytics.Event.SEARCH).equals("")) {
            this.etSearch.setText(getIntent().getStringExtra(FirebaseAnalytics.Event.SEARCH));
            this.etSearch.setSelection(getIntent().getStringExtra(FirebaseAnalytics.Event.SEARCH).length());
            if (getIntent().getIntExtra("tipe", 0) == 1) {
                this.presenter.loadSearchReceiver(this.etSearch.getText().toString());
            } else if (getIntent().getIntExtra("tipe", 0) == 2) {
                this.presenter.loadSearchSPJ(this.etSearch.getText().toString());
            }
        }
        this.handler = new Handler();
        this.etSearch.addTextChangedListener(new TextWatcher() { 
            @Override 
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override 
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override 
            public void afterTextChanged(final Editable editable) {
                if (editable.toString().length() >= 3) {
                    if (runnableSearch != null) {
                        handler.removeCallbacks(runnableSearch);
                        runnableSearch = null;
                    }
                    runnableSearch = new Runnable() { 
                        @Override 
                        public void run() {
                            if (getIntent().getIntExtra("tipe", 0) == 1) {
                                presenter.loadSearchReceiver(editable.toString());
                            } else if (getIntent().getIntExtra("tipe", 0) == 2) {
                                presenter.loadSearchSPJ(editable.toString());
                            }
                        }
                    };
                    handler.postDelayed(runnableSearch, 500L);
                    return;
                }
                if (editable.toString().length() == 0) {
                    tvKosong.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override 
    public void showSearch(List<SearchModel> list, int i, String str) {
        if (i == 1) {
            this.tvKosong.setVisibility(View.GONE);
            AdapterSearchList adapterSearchList = new AdapterSearchList(list, this.context, getIntent().getIntExtra("tipe", 0));
            this.rcList.setHasFixedSize(true);
            this.rcList.setNestedScrollingEnabled(false);
            this.rcList.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.VERTICAL, false));
            this.rcList.setAdapter(adapterSearchList);
            adapterSearchList.setOnItemClickListener(new AdapterSearchList.OnListClickListener() { 
                @Override 
                public void onClicked(String str2, int i2) {
                    Intent intent = new Intent();
                    intent.putExtra(FirebaseAnalytics.Event.SEARCH, str2);
                    setResult(-1, intent);
                    finish();
                }
            });
            return;
        }
        this.tvKosong.setVisibility(View.VISIBLE);
    }
}
