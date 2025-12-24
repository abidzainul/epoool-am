package com.epoool.approvalepoool.Views;

import com.epoool.approvalepoool.Models.InsertUpdateModel;
import com.epoool.approvalepoool.Models.DeliveryRequest;
import com.epoool.approvalepoool.Models.DeliveryRequestRes;
import com.epoool.approvalepoool.REST.ApiClient;
import com.epoool.approvalepoool.REST.ApiInterface;
import com.epoool.approvalepoool.Utils.Constant;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Field;

import java.util.ArrayList;
import java.util.List;

public class DeliveryRequestPresenter {
    private ApiInterface apiInterface = (ApiInterface) ApiClient.getClient().create(ApiInterface.class);
    private ViewDeliveryRequest view;

    public interface ViewDeliveryRequest {
        void showDeliveryRequest(List<DeliveryRequest> list, int i, String str);
        void afterRequest(int i, String str);
        void afterDelete(int i, String str);
    }

    public DeliveryRequestPresenter(ViewDeliveryRequest view) {
        this.view = view;
    }

    public void loadData(String noSo, String lineSo) {

        System.out.println("token: " + Constant.token_fcm);
        this.apiInterface.getDeliveryRequest(Constant.token_fcm, noSo, lineSo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeliveryRequestRes>() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onNext(DeliveryRequestRes res) {
                        System.out.println("onNext");
                        view.showDeliveryRequest(res.getData(), res.getCode(), res.getPesan());
                    }

                    @Override
                    public void onError(Throwable th) {
                        System.out.println("onError");
                        view.showDeliveryRequest(new ArrayList<>(), 0, Constant.warningNoConnection);
                    }
                });
    }

    public void saveData(String noSo, String lineSo, String qty, String dateSend, String note) {
        this.apiInterface.saveDeliveryRequest(Constant.token_fcm, noSo, lineSo,
                        qty, dateSend, note)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InsertUpdateModel>() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onNext(InsertUpdateModel insertUpdateModel) {
                view.afterRequest(insertUpdateModel.getCode().intValue(), insertUpdateModel.getPesan());
            }

            @Override
            public void onError(Throwable th) {
                view.afterRequest(0, th.getMessage());
            }
        });
    }

    public void deleteData(String id) {
        this.apiInterface.deleteDeliveryRequest(Constant.token_fcm, id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InsertUpdateModel>() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onNext(InsertUpdateModel insertUpdateModel) {
                        view.afterDelete(insertUpdateModel.getCode().intValue(), insertUpdateModel.getPesan());
                    }

                    @Override
                    public void onError(Throwable th) {
                        view.afterDelete(0, Constant.warningNoConnection);
                    }
                });
    }
    
}
