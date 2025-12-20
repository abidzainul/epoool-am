package com.epoool.approvalepoool.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.epoool.approvalepoool.Models.UserLoginModel;
import com.epoool.approvalepoool.R;
import com.epoool.approvalepoool.REST.ApiClient;
import com.epoool.approvalepoool.Utils.Constant;
import com.epoool.approvalepoool.Utils.Function;
import com.epoool.approvalepoool.Utils.GsonConverter;
import com.epoool.approvalepoool.Views.LoginPresenter;
import java.util.UUID;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.ViewLogin {
    Button btnLogin;
    private Context context;
    EditText etPassword;
    EditText etUsername;
    private LoginPresenter presenter;
    RelativeLayout rlWhole;
    TextView tvFordev;
    TextView tvVersion;

    @Override 
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        this.etUsername = (EditText) findViewById(R.id.et_username_login);
        this.etPassword = (EditText) findViewById(R.id.et_password_login);
        this.btnLogin = (Button) findViewById(R.id.btn_login);
        this.rlWhole = (RelativeLayout) findViewById(R.id.rl_whole);
        this.tvVersion = (TextView) findViewById(R.id.tv_version);
        this.tvFordev = (TextView) findViewById(R.id.tv_fordev);
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            this.tvVersion.setText(packageInfo.versionName + " (" + packageInfo.versionCode + ")");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (ApiClient.DEV) {
            this.tvFordev.setVisibility(View.VISIBLE);
        } else {
            this.tvFordev.setVisibility(View.GONE);
        }
        getSupportActionBar().hide();
        this.context = this;
        Function.getScreenSize(this);
        this.etUsername.getBackground().mutate().setColorFilter(-1, PorterDuff.Mode.SRC_ATOP);
        this.etPassword.getBackground().mutate().setColorFilter(-1, PorterDuff.Mode.SRC_ATOP);
        cekLogin();
        this.presenter = new LoginPresenter(this);
        this.btnLogin.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
                    Function.snackBarRed(context, "Mohon lengkapi semua field di atas");
                } else {
                    presenter.doLogin(etUsername.getText().toString(), etPassword.getText().toString());
                }
            }
        });
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, "android.permission.READ_PHONE_STATE") != 0) {
            return;
        }
        Constant.IMEI = telephonyManager.getDeviceId() == null ? UUID.randomUUID().toString() : telephonyManager.getDeviceId();
    }

    void cekLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", 0);
        if (isLogin(sharedPreferences)) {
            Constant.idUsername = sharedPreferences.getString("id_username", "");
            Constant.idReference = sharedPreferences.getString("id_reference", "");
            Constant.tipe = sharedPreferences.getString("tipe", "");
            Constant.token_fcm = sharedPreferences.getString("token_fcm", "");
            Constant.tipe_sub_user = sharedPreferences.getString("tipe_sub_user", "");
            Constant.token = sharedPreferences.getString("token", "");
            UserLoginModel jsonObject = new GsonConverter<UserLoginModel>() { 
            }.toJsonObject(sharedPreferences.getString("user_model_string", "{}"));
            Constant.modelUser = new UserLoginModel();
            Constant.modelUser.setUsername(jsonObject.getUsername());
            Constant.modelUser.setEmail(jsonObject.getEmail());
            Constant.modelUser.setNoHp(jsonObject.getNoHp());
            startActivity(new Intent(this, MainActivity.class));
            finish();
            Function.openAct(this);
        }
    }

    boolean isLogin(SharedPreferences sharedPreferences) {
        return !sharedPreferences.getString("token_fcm", "").equals("");
    }

    @Override 
    public void onBackPressed() {
        super.onBackPressed();
        Function.closeAct(this);
    }

    @Override 
    public void afterLogin(UserLoginModel userLoginModel, int i, String str, String str2) {
        if (i == 1) {
            Constant.idUsername = userLoginModel.getIdUsername();
            Constant.idReference = userLoginModel.getIdReference();
            Constant.tipe = userLoginModel.getTipe();
            Constant.token_fcm = userLoginModel.getTokenFcm();
            Constant.tipe_sub_user = userLoginModel.getTipeSubUser();
            Constant.token = str2;
            Constant.modelUser = new UserLoginModel();
            Constant.modelUser.setUsername(userLoginModel.getUsername());
            Constant.modelUser.setEmail(userLoginModel.getEmail());
            Constant.modelUser.setNoHp(userLoginModel.getNoHp());
            String jsonString = new GsonConverter().toJsonString(Constant.modelUser);
            SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", 0);
            sharedPreferences.edit().putString("token_fcm", userLoginModel.getTokenFcm()).apply();
            sharedPreferences.edit().putString("tipe_sub_user", userLoginModel.getTipeSubUser()).apply();
            sharedPreferences.edit().putString("token", str2).apply();
            sharedPreferences.edit().putString("tipe", userLoginModel.getTipe()).apply();
            sharedPreferences.edit().putString("id_reference", userLoginModel.getIdReference()).apply();
            sharedPreferences.edit().putString("id_username", userLoginModel.getIdUsername()).apply();
            sharedPreferences.edit().putString("user_model_string", jsonString).apply();
            Function.sendToken(this);
            startActivity(new Intent(this, MainActivity.class));
            finish();
            Function.openAct(this);
            return;
        }
        this.etPassword.setText("");
        Function.toast(this, str);
    }
}
