package com.epoool.approvalepoool.REST;

import com.epoool.approvalepoool.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/* loaded from: classes.dex */
public class ApiClient {
    public static boolean DEV = true;
    public static String baseUrl;
    public static String url;
    public static String urlImageKendala;
    public static String urlImageOriginator;
    private static Retrofit retrofit = null;

    static {
//        baseUrl = BuildConfig.DEBUG ? "http://10.0.2.2/" : "https://app.epoool.id/";
        baseUrl = BuildConfig.DEBUG ? "https://dev.epoool.id/" : "https://app.epoool.id/";
        url = baseUrl + "index.php/mobile/api_android_driver/";
        urlImageOriginator = baseUrl + "berkas/foto_user/originator/";
        urlImageKendala = baseUrl + "berkas/foto_claim/img_problem/";
    }

    public static Retrofit getClient() {
        Gson gsonCreate = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (DEV) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1L, TimeUnit.MINUTES)
                .readTimeout(30L, TimeUnit.SECONDS)
                .writeTimeout(30L, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        if (retrofit != null) {
            return retrofit;
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gsonCreate))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
