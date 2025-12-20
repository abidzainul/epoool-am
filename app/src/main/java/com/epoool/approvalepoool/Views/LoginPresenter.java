package com.epoool.approvalepoool.Views;

import com.epoool.approvalepoool.Models.UserLoginModel;
import com.epoool.approvalepoool.REST.ApiClient;
import com.epoool.approvalepoool.REST.ApiInterface;
import com.epoool.approvalepoool.Utils.Constant;
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
        this.apiInterface.login(str, str2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserLoginModel>() { 
            @Override 
            public void onComplete() {
            }

            @Override 
            public void onSubscribe(Disposable disposable) {
            }

            @Override 
            public void onNext(UserLoginModel userLoginModel) {
                view.afterLogin(userLoginModel.getData(), userLoginModel.getCode().intValue(), userLoginModel.getPesan(), userLoginModel.getToken());
            }

            @Override 
            public void onError(Throwable th) {
                view.afterLogin(new UserLoginModel(), 0, Constant.warningNoConnection, "0");
            }
        });
    }
}
