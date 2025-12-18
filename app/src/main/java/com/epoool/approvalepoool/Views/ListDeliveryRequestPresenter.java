package com.epoool.approvalepoool.Views;

import androidx.core.app.NotificationCompat;

import com.epoool.approvalepoool.Models.PengalihanModel;
import com.epoool.approvalepoool.REST.ApiClient;
import com.epoool.approvalepoool.REST.ApiInterface;
import com.epoool.approvalepoool.Utils.Constant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Field;

import java.util.ArrayList;
import java.util.List;

public class ListDeliveryRequestPresenter {
    private ApiInterface apiInterface = (ApiInterface) ApiClient.getClient().create(ApiInterface.class);
    private ViewListPengalihan view;

    public interface ViewListPengalihan {
        void showPengalihan(List<PengalihanModel> list, int i, String str);
    }

    public ListDeliveryRequestPresenter(ViewListPengalihan viewListPengalihan) {
        this.view = viewListPengalihan;
    }

    public void loadPengalihan(String str, String str2) {
//        id_username, token_fcmNotificationCompat.CATEGORY_STATUS, no_spj, tipe_sub_user
        this.apiInterface.getPengalihan(Constant.idUsername, Constant.token_fcm, str, str2, Constant.tipe_sub_user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PengalihanModel>() { // from class: com.epoool.approvalepoool.Views.ListPengalihanPresenter.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(PengalihanModel pengalihanModel) {
                System.out.println("masuk dlam");
                view.showPengalihan(pengalihanModel.getData(), pengalihanModel.getCode().intValue(), pengalihanModel.getPesan());
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                System.out.println("masuk keluar");
                view.showPengalihan(new ArrayList(), 0, Constant.warningNoConnection);
            }
        });
    }
}
