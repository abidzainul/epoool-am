package com.epoool.approvalepoool.Utils;

import android.content.Intent;
import com.epoool.approvalepoool.Models.UserLoginModel;
import com.epoool.approvalepoool.R;
import com.epoool.approvalepoool.REST.ApiClient;
import com.epoool.approvalepoool.Utils.drawer.AdapterListViewDrawer;

public class Constant {
    public static String IMEI;
    public static AdapterListViewDrawer adapterListViewDrawer;
    public static String baseUrl;
    public static int height;
    public static int holderFragment;
    public static String idOriginator;
    public static String idReference;
    public static String idUsername;
    public static Intent intentQueue;
    public static Intent intentService;
    public static String key;
    public static UserLoginModel modelUser;
    public static String newUrl;
    public static String os;
    public static String tipe;
    public static String tipe_sub_user;
    public static String token;
    public static String token_fcm;
    public static String url;
    public static String urlImageOriginator;
    public static String warningNoConnection;
    public static int width;

    static {
        baseUrl = ApiClient.DEV ? "http://10.0.2.2/" : "https://app.epoool.id/";
        url = baseUrl + "index.php/mobile/pengalihan/api_pengalihan/";
        newUrl = baseUrl + "index.php/mobile/baru_15042020/api_driver/";
        urlImageOriginator = baseUrl + "berkas/foto_user/originator/";
        os = "android";
        key = "4YtOFWfBqVcwRVIwuRsD";
        warningNoConnection = "Gagal, coba beberapa saat lagi";
        holderFragment = R.id.content_frame;
    }
}
