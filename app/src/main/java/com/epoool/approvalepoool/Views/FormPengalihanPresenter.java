package com.epoool.approvalepoool.Views;

import com.epoool.approvalepoool.Models.AlasanModel;
import com.epoool.approvalepoool.Models.DeliveryOrderModel;
import com.epoool.approvalepoool.Models.InsertUpdateModel;
import com.epoool.approvalepoool.Models.ReceiverModel;
import com.epoool.approvalepoool.REST.ApiClient;
import com.epoool.approvalepoool.REST.ApiInterface;
import com.epoool.approvalepoool.Utils.Constant;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class FormPengalihanPresenter {
    private ApiInterface apiInterface = (ApiInterface) ApiClient.getClient().create(ApiInterface.class);
    private ViewFormPengalihan view;

    public interface ViewFormPengalihan {
        void afterInsert(int i, String str);

        void showAlasan(List<AlasanModel> list, int i, String str);

        void showDataReceiver(ReceiverModel receiverModel, int i, String str);

        void showDataResi(DeliveryOrderModel deliveryOrderModel, int i, String str);
    }

    public FormPengalihanPresenter(ViewFormPengalihan viewFormPengalihan) {
        this.view = viewFormPengalihan;
    }

    public void loadDataResi(String str) {
        this.apiInterface.getDObyResi(Constant.idUsername, Constant.token_fcm, str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DeliveryOrderModel>() { // from class: com.epoool.approvalepoool.Views.FormPengalihanPresenter.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(DeliveryOrderModel deliveryOrderModel) {
                FormPengalihanPresenter.this.view.showDataResi(deliveryOrderModel.getData(), deliveryOrderModel.getCode().intValue(), deliveryOrderModel.getPesan());
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                FormPengalihanPresenter.this.view.showDataResi(new DeliveryOrderModel(), 0, Constant.warningNoConnection);
            }
        });
    }

    public void loadDataReceiver(String str) {
        this.apiInterface.getReceiver(Constant.idUsername, Constant.token_fcm, str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ReceiverModel>() { // from class: com.epoool.approvalepoool.Views.FormPengalihanPresenter.2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(ReceiverModel receiverModel) {
                FormPengalihanPresenter.this.view.showDataReceiver(receiverModel.getData(), receiverModel.getCode().intValue(), receiverModel.getPesan());
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                FormPengalihanPresenter.this.view.showDataReceiver(new ReceiverModel(), 0, Constant.warningNoConnection);
            }
        });
    }

    public void sendPengalihan(String str, String str2, String str3) {
        this.apiInterface.insertPengalihan(Constant.idUsername, Constant.token_fcm, str, str2, str3).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<InsertUpdateModel>() { // from class: com.epoool.approvalepoool.Views.FormPengalihanPresenter.3
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(InsertUpdateModel insertUpdateModel) {
                FormPengalihanPresenter.this.view.afterInsert(insertUpdateModel.getCode().intValue(), insertUpdateModel.getPesan());
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                FormPengalihanPresenter.this.view.afterInsert(0, Constant.warningNoConnection);
            }
        });
    }

    public void loadAlasan() {
        this.apiInterface.getAlasan(Constant.token_fcm, Constant.idReference, "1").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AlasanModel>() { // from class: com.epoool.approvalepoool.Views.FormPengalihanPresenter.4
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(AlasanModel alasanModel) {
                FormPengalihanPresenter.this.view.showAlasan(alasanModel.getData(), alasanModel.getCode().intValue(), alasanModel.getPesan());
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                FormPengalihanPresenter.this.view.showAlasan(new ArrayList(), 0, Constant.warningNoConnection);
            }
        });
    }
}
