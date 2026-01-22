package com.epoool.am.Views;

import com.epoool.am.Models.UserLoginModel;
import com.epoool.am.REST.ApiClient;
import com.epoool.am.REST.ApiInterface;
import com.epoool.am.Utils.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter {
    private ApiInterface apiInterface = (ApiInterface) ApiClient.getClient().create(ApiInterface.class);
    private ViewLogin view;

    public interface ViewLogin {
        void afterLogin(UserLoginModel userLoginModel, int i, String str, String str2);
    }

    public LoginPresenter(ViewLogin viewLogin) {
        this.view = viewLogin;
    }

    public void doLogin(String str, String str2) {
        String versi = "6";
        String os = "android";
        String sn = "unknown";
        String key = "4YtOFWfBqVcwRVIwuRsD";
        this.apiInterface.login(str, str2, versi, os, sn, key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserLoginModel>() {
            @Override 
            public void onComplete() {
            }

            @Override 
            public void onSubscribe(Disposable disposable) {
            }

            @Override 
            public void onNext(UserLoginModel userLoginModel) {
                Gson gson = new GsonBuilder().create();
                System.out.println("UserLogin: "+gson.toJson(userLoginModel));
                view.afterLogin(userLoginModel.getData(), userLoginModel.getCode().intValue(), userLoginModel.getPesan(), userLoginModel.getToken());
            }

            @Override 
            public void onError(Throwable th) {
                view.afterLogin(new UserLoginModel(), 0, Constant.warningNoConnection, "0");
            }
        });
    }
}
