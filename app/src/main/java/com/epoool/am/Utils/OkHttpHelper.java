package com.epoool.am.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.epoool.am.BuildConfig;
import com.epoool.am.REST.ApiClient;

import java.io.File;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class OkHttpHelper {

    private OkHttpClient client;
    private Request request;
    public int code = 100;
    public String pesan;
    public String response;
    public JSONObject obj;
    public PBar pBar;
    public SharedPreferences sp;

    class exeingGet extends AsyncTask{

        private Context context;
        private String url;
        private Runnable run;

        public exeingGet(Context context, String url, Runnable run){
            this.context = context;
            this.url = url;
            this.run = run;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            obj = null;
            try {
                request = new Request.Builder()
//                        .addHeader("AUTH-KEY", SC.decryptString(sp.getString("header","")))
//                        .addHeader("Content-Type","application/json")
//                        .addHeader("aplikasi",Constant.aplikasi)
                        .url(url)
                        .get()
                        .build();

                OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS);

                if (BuildConfig.DEBUG || ApiClient.DEV) {
                    try {
                        TrustManager[] trustAllCerts = new TrustManager[]{
                                new X509TrustManager() {
                                    @Override
                                    public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                                    @Override
                                    public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                                    @Override
                                    public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[]{}; }
                                }
                        };
                        SSLContext sslContext = SSLContext.getInstance("SSL");
                        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                        clientBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                                .hostnameVerifier((hostname, session) -> true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                client = clientBuilder.build();

                Response res = client.newCall(request).execute();
                response = res.body().string();

                Log.d("okhttp","url: "+url+" get\n"+
                        "header: "+request.headers()+"\n"+
                        "response: "+response);

                obj = new JSONObject(response);
                code = obj.getInt("code");
                pesan = obj.getString("pesan");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

//            dialog.dismiss();

            if(pBar != null){
                pBar.hide();
            }

//            try{
//                run.run();
//            }catch (Exception e){
//                e.printStackTrace();
//            }

            if(obj != null){
                run.run();
            }else{
                run.run();
            }
        }
    }

    class exeingGetNoPb extends AsyncTask{

        private Context context;
        private String url;
        private Runnable run;

        public exeingGetNoPb(Context context, String url, Runnable run){
            this.context = context;
            this.url = url;
            this.run = run;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            obj = null;
            try {
                request = new Request.Builder()
//                        .addHeader("AUTH-KEY",SC.decryptString(sp.getString("header","")))
//                        .addHeader("Content-Type","application/json")
//                        .addHeader("aplikasi",Constant.aplikasi)
                        .url(url)
                        .get()
                        .build();

                OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS);

                if (BuildConfig.DEBUG || ApiClient.DEV) {
                    try {
                        TrustManager[] trustAllCerts = new TrustManager[]{
                                new X509TrustManager() {
                                    @Override
                                    public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                                    @Override
                                    public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                                    @Override
                                    public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[]{}; }
                                }
                        };
                        SSLContext sslContext = SSLContext.getInstance("SSL");
                        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                        clientBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                                .hostnameVerifier((hostname, session) -> true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                client = clientBuilder.build();

                Response res = client.newCall(request).execute();
                response = res.body().string();

                Log.d("okhttp","url: "+url+"\n"+
                        "header: "+request.headers()+
                        "response: "+response);

                obj = new JSONObject(response);
                code = obj.getInt("code");
                pesan = obj.getString("pesan");

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

//            dialog.dismiss();

            if(obj != null){
                run.run();
            }else{
                run.run();
            }
        }
    }

    class exeingPost extends AsyncTask{

        private Context context;
        private String url;
        private FormBody.Builder requestBody;
        private Runnable run;

        public exeingPost(Context context, String url, FormBody.Builder requestBody, Runnable run){
            this.context = context;
            this.url = url;
            this.requestBody = requestBody;
            this.run = run;
        }

        @Override
        protected Object doInBackground(Object[] params) {

            obj = null;
            try {
                request = new Request.Builder()
//                        .addHeader("AUTH-KEY", SC.decryptString(sp.getString("header","")))
//                        .addHeader("Content-Type","application/json")
//                        .addHeader("aplikasi",Constant.aplikasi)
                        .url(url)
                        .post(requestBody.build())
                        .build();

                OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS);

                if (BuildConfig.DEBUG || ApiClient.DEV) {
                    try {
                        TrustManager[] trustAllCerts = new TrustManager[]{
                                new X509TrustManager() {
                                    @Override
                                    public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                                    @Override
                                    public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                                    @Override
                                    public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[]{}; }
                                }
                        };
                        SSLContext sslContext = SSLContext.getInstance("SSL");
                        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                        clientBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                                .hostnameVerifier((hostname, session) -> true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                client = clientBuilder.build();

                Response res = client.newCall(request).execute();
                response = res.body().string();

                Log.d("okhttp","url: "+url+" post\n"+
                        "body: "+bodyToString(request)+"\n"+
                        "header: "+request.headers()+"\n"+
                        "response: "+response);

                obj = new JSONObject(response);
                code = obj.getInt("code");
                pesan = obj.getString("pesan");

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if(pBar != null){
                pBar.hide();
            }

            if(obj != null){
                run.run();
            }else{
                run.run();
            }
        }
    }

    class exeingPut extends AsyncTask{

        private Context context;
        private String url;
        private FormBody.Builder requestBody;
        private Runnable run;

        public exeingPut(Context context, String url, FormBody.Builder requestBody, Runnable run){
            this.context = context;
            this.url = url;
            this.requestBody = requestBody;
            this.run = run;
        }

        @Override
        protected Object doInBackground(Object[] params) {

            obj = null;
            try {
                request = new Request.Builder()
//                        .addHeader("AUTH-KEY",SC.decryptString(sp.getString("header","")))
//                        .addHeader("Content-Type","application/json")
//                        .addHeader("aplikasi",Constant.aplikasi)
                        .url(url)
                        .put(requestBody.build())
                        .build();
                Log.d("cek_login","cek: "+request.headers());

                OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS);

                if (BuildConfig.DEBUG || ApiClient.DEV) {
                    try {
                        TrustManager[] trustAllCerts = new TrustManager[]{
                                new X509TrustManager() {
                                    @Override
                                    public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                                    @Override
                                    public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                                    @Override
                                    public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[]{}; }
                                }
                        };
                        SSLContext sslContext = SSLContext.getInstance("SSL");
                        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                        clientBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                                .hostnameVerifier((hostname, session) -> true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                client = clientBuilder.build();

                Response res = client.newCall(request).execute();
                response = res.body().string();

                Log.d("okhttp","put url: "+url+"\n"+
                        "param: "+bodyToString(request)+"\n"+
                        "header: "+request.headers()+
                        "response: "+response);

                obj = new JSONObject(response);
                code = obj.getInt("code");
                pesan = obj.getString("pesan");

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

//            dialog.dismiss();

            if(pBar != null){
                pBar.hide();
            }

            if(obj != null){
                run.run();
            }else{
                run.run();
            }
        }
    }

    class exeingDelete extends AsyncTask{

        private Context context;
        private String url;
        private Runnable run;

        public exeingDelete(Context context, String url, Runnable run){
            this.context = context;
            this.url = url;
            this.run = run;
        }

        @Override
        protected Object doInBackground(Object[] params) {

            obj = null;
            try {
                request = new Request.Builder()
//                        .addHeader("AUTH-KEY",SC.decryptString(sp.getString("header","")))
//                        .addHeader("Content-Type","application/json")
//                        .addHeader("aplikasi",Constant.aplikasi)
                        .url(url)
                        .delete()
                        .build();
                Log.d("cek_login","cek: "+request.headers());

                OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS);

                if (BuildConfig.DEBUG || ApiClient.DEV) {
                    try {
                        TrustManager[] trustAllCerts = new TrustManager[]{
                                new X509TrustManager() {
                                    @Override
                                    public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                                    @Override
                                    public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                                    @Override
                                    public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[]{}; }
                                }
                        };
                        SSLContext sslContext = SSLContext.getInstance("SSL");
                        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                        clientBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                                .hostnameVerifier((hostname, session) -> true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                client = clientBuilder.build();

                Response res = client.newCall(request).execute();
                response = res.body().string();

                Log.d("okhttp","url: "+url+"\n"+
                        "param: "+bodyToString(request)+"\n"+
                        "header: "+request.headers()+
                        "response: "+response);

                obj = new JSONObject(response);
                code = obj.getInt("code");
                pesan = obj.getString("pesan");

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if(pBar != null){
                pBar.hide();
            }

            if(obj != null){
                run.run();
            }else{
                Toast.makeText(context, "Kesalahan Jaringan, Coba Lagi",Toast.LENGTH_LONG).show();
            }
        }
    }

    class exeingMultiPart extends AsyncTask{

        private Context context;
        private String url;
        private MultipartBody.Builder requestBody;
        private Runnable run;

        public exeingMultiPart(Context context, String url,
                               MultipartBody.Builder requestBody, Runnable run){
            this.context = context;
            this.url = url;
            this.requestBody = requestBody;
            this.run = run;
        }

        @Override
        protected Object doInBackground(Object[] params) {

            obj = null;

            try {

                request = new Request.Builder()
//                        .addHeader("AUTH-KEY",SC.decryptString(sp.getString("header","")))
//                        .addHeader("Content-Type","application/json")
//                        .addHeader("aplikasi",Constant.aplikasi)
                        .url(url)
                        .post(requestBody.build())
                        .build();

                OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS);

                if (BuildConfig.DEBUG || ApiClient.DEV) {
                    try {
                        TrustManager[] trustAllCerts = new TrustManager[]{
                                new X509TrustManager() {
                                    @Override
                                    public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                                    @Override
                                    public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                                    @Override
                                    public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[]{}; }
                                }
                        };
                        SSLContext sslContext = SSLContext.getInstance("SSL");
                        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                        clientBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                                .hostnameVerifier((hostname, session) -> true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                client = clientBuilder.build();

                Response res = client.newCall(request).execute();
                response = res.body().string();

                Log.d("okhttp","url: "+url+"\n"+
//                        "mime: "+MediaType.parse(getMimeType(context,Uri.fromFile(new File(filePath))))+"\n"+
//                        "filepath: "+filePath+"\n"+
//                        "filename: "+filename+"\n"+
//                        "paramfile: "+paramFile+"\n"+
                        "body: "+bodyToString(request)+" multipart\n"+
                        "header: "+request.headers()+
                        "response: "+response);

                obj = new JSONObject(response);
                code = obj.getInt("code");
                pesan = obj.getString("pesan");

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if(pBar != null){
                pBar.hide();
            }

            if(obj != null){
                run.run();
            }else{
                Toast.makeText(context, "Kesalahan Jaringan, Coba Lagi",Toast.LENGTH_LONG).show();
            }
        }
    }

//    public String getMimeType(Context context,Uri uri) {
//        String mimeType = null;
//        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
//            ContentResolver cr = context.getContentResolver();
//            mimeType = cr.getType(uri);
//        } else {
//            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
//                    .toString());
//            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
//                    fileExtension.toLowerCase());
//        }
//        return mimeType;
//    }


    private String bodyToString(final Request request){

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    public void multiPart(Context context, String url, HashMap<String,String> params,
                          HashMap<String,String> paramsImage, Runnable run,
                          boolean proBar, RelativeLayout rl, View view){
        if(proBar){
            pBar = new PBar(context,rl,view);
            pBar.show();
        }else{
            pBar = null;
        }

        MultipartBody.Builder multipartBody = new MultipartBody.Builder();
        multipartBody.setType(MultipartBody.FORM);

        for ( Map.Entry<String, String> entry : params.entrySet() ) {
            multipartBody.addFormDataPart(entry.getKey(), entry.getValue());
        }

        for ( Map.Entry<String, String> entry : paramsImage.entrySet() ) {
            multipartBody.addFormDataPart(entry.getKey(), entry.getValue());

            String filename = entry.getValue().substring(entry.getValue().lastIndexOf(File.separator)+1);
            String extension = filename.substring(filename.lastIndexOf(".")+1);
            multipartBody.addFormDataPart(
                    entry.getKey(),
                    filename,
                    RequestBody.create(
                            MediaType.parse(MimeTypeUtil.getType(extension))
                            , new File(entry.getValue())));
        }



//        SC.init(context);
        sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        new exeingMultiPart(context, url,multipartBody, run).execute();
    }

    public void posting(Context context, String url, FormBody.Builder requestBody, Runnable run,
                        boolean proBar, RelativeLayout rl, View view){
//        if(proBar){
//            pBar = new PBar(context,rl,view);
//            pBar.show();
//        }else{
//            pBar = null;
//        }
//        SC.init(context);
        sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        new exeingPost(context, url, requestBody,run).execute();
    }

    public void posting(Context context, String url, HashMap<String,String> params, Runnable run,
                        boolean proBar, RelativeLayout rl, View view){
        Log.d("cek_param","url: "+url+", param: "+params);
        if(proBar){
            pBar = new PBar(context,rl,view);
            pBar.show();
        }else{
            pBar = null;
        }

//        initiateDialogBar(context);
//        dialog.show();

        FormBody.Builder builder = new FormBody.Builder();

        for ( Map.Entry<String, String> entry : params.entrySet() ) {
            builder.add( entry.getKey(), entry.getValue() );
        }
//        SC.init(context);
        sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        new exeingPost(context, url, builder,run).execute();
    }

    public void postTwoRunnables(Context context, String url, HashMap<String,String> params,
                                 Runnable run,Runnable runNegative,
                                 boolean proBar, RelativeLayout rl, View view){
        if(proBar){
            pBar = new PBar(context,rl,view);
            pBar.show();
        }else{
            pBar = null;
        }

//        initiateDialogBar(context);
//        dialog.show();

        FormBody.Builder builder = new FormBody.Builder();

        for ( Map.Entry<String, String> entry : params.entrySet() ) {
            builder.add( entry.getKey(), entry.getValue() );
        }
//        SC.init(context);
        sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        new exeingPost(context, url, builder,run).execute();
    }

    public void putting(Context context, String url, HashMap<String,String> params, Runnable run,
                        boolean proBar, RelativeLayout rl, View view){
        if(proBar){
            pBar = new PBar(context,rl,view);
            pBar.show();
        }else{
            pBar = null;
        }

//        initiateDialogBar(context);
//        dialog.show();

        FormBody.Builder builder = new FormBody.Builder();

        for ( Map.Entry<String, String> entry : params.entrySet() ) {
            builder.add( entry.getKey(), entry.getValue() );
        }
//        SC.init(context);
        sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        new exeingPut(context, url, builder,run).execute();
    }

    public void deleting(Context context, String url, Runnable run,
                         boolean proBar, RelativeLayout rl, View view){
        if(proBar){
            pBar = new PBar(context,rl,view);
            pBar.show();
        }else{
            pBar = null;
        }

//        SC.init(context);
        sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        new exeingDelete(context, url, run).execute();
    }

    public void getting(Context context, String url, Runnable run, boolean proBar,
                        RelativeLayout rl, View view){
        if(proBar){
            pBar = new PBar(context,rl,view);
            pBar.show();
        }else{
            pBar = null;
        }

//        initiateDialogBar(context);
//        dialog.show();
//        SC.init(context);
        sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        new exeingGet(context, url, run).execute();
    }

    public void gettingNoPb(Context context, String url, Runnable run){

//        initiateDialogBar(context);
//        dialog.show();
//        SC.init(context);
        sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        new exeingGetNoPb(context, url, run).execute();
    }

    public void cancelAll(){
        if(client != null){
            Call call = client.newCall(request);
            call.cancel();
        }
    }

//    private void initiateDialogBar(Context context){
//        dialog = new AlertDialog.Builder(context)
//                .setView(R.layout.dialog_progress_bar)
//                .setCancelable(false)
//                .create();
//    }
}
