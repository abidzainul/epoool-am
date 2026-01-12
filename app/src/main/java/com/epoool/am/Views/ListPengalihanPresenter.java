package com.epoool.am.Views;

import com.epoool.am.Models.PengalihanModel;
import com.epoool.am.REST.ApiClient;
import com.epoool.am.REST.ApiInterface;
import com.epoool.am.Utils.Constant;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class ListPengalihanPresenter {
    private ApiInterface apiInterface = (ApiInterface) ApiClient.getClient().create(ApiInterface.class);
    private ViewListPengalihan view;

    public interface ViewListPengalihan {
        void showPengalihan(List<PengalihanModel> list, int i, String str);
    }

    public ListPengalihanPresenter(ViewListPengalihan viewListPengalihan) {
        this.view = viewListPengalihan;
    }

    public void loadPengalihan(String str, String str2) {
        //id_username, token_fcmNotificationCompat.CATEGORY_STATUS, no_spj, tipe_sub_user
        this.apiInterface.getPengalihan(Constant.idUsername, Constant.token_fcm, str, str2, Constant.tipe_sub_user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PengalihanModel>() { 
            @Override 
            public void onComplete() {
            }

            @Override 
            public void onSubscribe(Disposable disposable) {
            }

            @Override 
            public void onNext(PengalihanModel pengalihanModel) {
                System.out.println("masuk dlam");
                view.showPengalihan(pengalihanModel.getData(), pengalihanModel.getCode().intValue(), pengalihanModel.getPesan());
            }

            @Override 
            public void onError(Throwable th) {
                System.out.println("masuk keluar");
                view.showPengalihan(new ArrayList(), 0, Constant.warningNoConnection);
            }
        });
    }
}
