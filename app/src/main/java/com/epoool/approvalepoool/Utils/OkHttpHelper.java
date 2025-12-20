package com.epoool.approvalepoool.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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

public class OkHttpHelper {
    private OkHttpClient client;
    public int code = 100;
    public JSONObject obj;
    public PBar pBar;
    public String pesan;
    private Request request;
    public String response;
    public SharedPreferences sp;

    class exeingGet extends AsyncTask {
        private Context context;
        private Runnable run;
        private String url;

        public exeingGet(Context context, String str, Runnable runnable) {
            this.context = context;
            this.url = str;
            this.run = runnable;
        }

        @Override 
        protected Object doInBackground(Object[] objArr) {
            OkHttpHelper.this.obj = null;
            try {
                OkHttpHelper.this.request = new Request.Builder().url(this.url).get().build();
                OkHttpHelper.this.client = new OkHttpClient.Builder().connectTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).build();
                OkHttpHelper.this.response = OkHttpHelper.this.client.newCall(OkHttpHelper.this.request).execute().body().string();
                Log.d("okhttp", "url: " + this.url + " get\nheader: " + OkHttpHelper.this.request.headers() + "\nresponse: " + OkHttpHelper.this.response);
                OkHttpHelper.this.obj = new JSONObject(OkHttpHelper.this.response);
                OkHttpHelper okHttpHelper = OkHttpHelper.this;
                okHttpHelper.code = okHttpHelper.obj.getInt("code");
                OkHttpHelper okHttpHelper2 = OkHttpHelper.this;
                okHttpHelper2.pesan = okHttpHelper2.obj.getString("pesan");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return null;
        }

        @Override 
        protected void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            if (OkHttpHelper.this.pBar != null) {
                OkHttpHelper.this.pBar.hide();
            }
            if (OkHttpHelper.this.obj != null) {
                this.run.run();
            } else {
                Toast.makeText(this.context, "Kesalahan Jaringan, Coba Lagi", Toast.LENGTH_LONG).show();
            }
        }
    }

    class exeingGetNoPb extends AsyncTask {
        private Context context;
        private Runnable run;
        private String url;

        public exeingGetNoPb(Context context, String str, Runnable runnable) {
            this.context = context;
            this.url = str;
            this.run = runnable;
        }

        @Override 
        protected Object doInBackground(Object[] objArr) {
            OkHttpHelper.this.obj = null;
            try {
                OkHttpHelper.this.request = new Request.Builder().url(this.url).get().build();
                OkHttpHelper.this.client = new OkHttpClient.Builder().connectTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).build();
                OkHttpHelper.this.response = OkHttpHelper.this.client.newCall(OkHttpHelper.this.request).execute().body().string();
                Log.d("okhttp", "url: " + this.url + "\nheader: " + OkHttpHelper.this.request.headers() + "response: " + OkHttpHelper.this.response);
                OkHttpHelper.this.obj = new JSONObject(OkHttpHelper.this.response);
                OkHttpHelper okHttpHelper = OkHttpHelper.this;
                okHttpHelper.code = okHttpHelper.obj.getInt("code");
                OkHttpHelper okHttpHelper2 = OkHttpHelper.this;
                okHttpHelper2.pesan = okHttpHelper2.obj.getString("pesan");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override 
        protected void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            if (OkHttpHelper.this.obj != null) {
                this.run.run();
            } else {
                Toast.makeText(this.context, "Kesalahan Jaringan, Coba Lagi", Toast.LENGTH_LONG).show();
            }
        }
    }

    class exeingPost extends AsyncTask {
        private Context context;
        private FormBody.Builder requestBody;
        private Runnable run;
        private String url;

        public exeingPost(Context context, String str, FormBody.Builder builder, Runnable runnable) {
            this.context = context;
            this.url = str;
            this.requestBody = builder;
            this.run = runnable;
        }

        @Override 
        protected Object doInBackground(Object[] objArr) {
            OkHttpHelper.this.obj = null;
            try {
                OkHttpHelper.this.request = new Request.Builder().url(this.url).post(this.requestBody.build()).build();
                OkHttpHelper.this.client = new OkHttpClient.Builder().connectTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).build();
                Response responseExecute = OkHttpHelper.this.client.newCall(OkHttpHelper.this.request).execute();
                OkHttpHelper.this.response = responseExecute.body().string();
                StringBuilder sb = new StringBuilder();
                sb.append("url: ");
                sb.append(this.url);
                sb.append(" post\nbody: ");
                OkHttpHelper okHttpHelper = OkHttpHelper.this;
                sb.append(okHttpHelper.bodyToString(okHttpHelper.request));
                sb.append("\nheader: ");
                sb.append(OkHttpHelper.this.request.headers());
                sb.append("\nresponse: ");
                sb.append(OkHttpHelper.this.response);
                Log.d("okhttp", sb.toString());
                OkHttpHelper.this.obj = new JSONObject(OkHttpHelper.this.response);
                OkHttpHelper okHttpHelper2 = OkHttpHelper.this;
                okHttpHelper2.code = okHttpHelper2.obj.getInt("code");
                OkHttpHelper okHttpHelper3 = OkHttpHelper.this;
                okHttpHelper3.pesan = okHttpHelper3.obj.getString("pesan");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override 
        protected void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            if (OkHttpHelper.this.pBar != null) {
                OkHttpHelper.this.pBar.hide();
            }
            if (OkHttpHelper.this.obj != null) {
                this.run.run();
            } else {
                Toast.makeText(this.context, "Kesalahan Jaringan, Coba Lagi", Toast.LENGTH_LONG).show();
            }
        }
    }

    class exeingPut extends AsyncTask {
        private Context context;
        private FormBody.Builder requestBody;
        private Runnable run;
        private String url;

        public exeingPut(Context context, String str, FormBody.Builder builder, Runnable runnable) {
            this.context = context;
            this.url = str;
            this.requestBody = builder;
            this.run = runnable;
        }

        @Override 
        protected Object doInBackground(Object[] objArr) {
            OkHttpHelper.this.obj = null;
            try {
                OkHttpHelper.this.request = new Request.Builder().url(this.url).put(this.requestBody.build()).build();
                Log.d("cek_login", "cek: " + OkHttpHelper.this.request.headers());
                OkHttpHelper.this.client = new OkHttpClient.Builder().connectTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).build();
                OkHttpHelper.this.response = OkHttpHelper.this.client.newCall(OkHttpHelper.this.request).execute().body().string();
                StringBuilder sb = new StringBuilder();
                sb.append("put url: ");
                sb.append(this.url);
                sb.append("\nparam: ");
                OkHttpHelper okHttpHelper = OkHttpHelper.this;
                sb.append(okHttpHelper.bodyToString(okHttpHelper.request));
                sb.append("\nheader: ");
                sb.append(OkHttpHelper.this.request.headers());
                sb.append("response: ");
                sb.append(OkHttpHelper.this.response);
                Log.d("okhttp", sb.toString());
                OkHttpHelper.this.obj = new JSONObject(OkHttpHelper.this.response);
                OkHttpHelper okHttpHelper2 = OkHttpHelper.this;
                okHttpHelper2.code = okHttpHelper2.obj.getInt("code");
                OkHttpHelper okHttpHelper3 = OkHttpHelper.this;
                okHttpHelper3.pesan = okHttpHelper3.obj.getString("pesan");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override 
        protected void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            if (OkHttpHelper.this.pBar != null) {
                OkHttpHelper.this.pBar.hide();
            }
            if (OkHttpHelper.this.obj != null) {
                this.run.run();
            } else {
                Toast.makeText(this.context, "Kesalahan Jaringan, Coba Lagi", Toast.LENGTH_LONG).show();
            }
        }
    }

    class exeingDelete extends AsyncTask {
        private Context context;
        private Runnable run;
        private String url;

        public exeingDelete(Context context, String str, Runnable runnable) {
            this.context = context;
            this.url = str;
            this.run = runnable;
        }

        @Override 
        protected Object doInBackground(Object[] objArr) {
            OkHttpHelper.this.obj = null;
            try {
                OkHttpHelper.this.request = new Request.Builder().url(this.url).delete().build();
                Log.d("cek_login", "cek: " + OkHttpHelper.this.request.headers());
                OkHttpHelper.this.client = new OkHttpClient.Builder().connectTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).build();
                OkHttpHelper.this.response = OkHttpHelper.this.client.newCall(OkHttpHelper.this.request).execute().body().string();
                StringBuilder sb = new StringBuilder();
                sb.append("url: ");
                sb.append(this.url);
                sb.append("\nparam: ");
                OkHttpHelper okHttpHelper = OkHttpHelper.this;
                sb.append(okHttpHelper.bodyToString(okHttpHelper.request));
                sb.append("\nheader: ");
                sb.append(OkHttpHelper.this.request.headers());
                sb.append("response: ");
                sb.append(OkHttpHelper.this.response);
                Log.d("okhttp", sb.toString());
                OkHttpHelper.this.obj = new JSONObject(OkHttpHelper.this.response);
                OkHttpHelper okHttpHelper2 = OkHttpHelper.this;
                okHttpHelper2.code = okHttpHelper2.obj.getInt("code");
                OkHttpHelper okHttpHelper3 = OkHttpHelper.this;
                okHttpHelper3.pesan = okHttpHelper3.obj.getString("pesan");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override 
        protected void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            if (OkHttpHelper.this.pBar != null) {
                OkHttpHelper.this.pBar.hide();
            }
            if (OkHttpHelper.this.obj != null) {
                this.run.run();
            } else {
                Toast.makeText(this.context, "Kesalahan Jaringan, Coba Lagi", Toast.LENGTH_LONG).show();
            }
        }
    }

    class exeingMultiPart extends AsyncTask {
        private Context context;
        private MultipartBody.Builder requestBody;
        private Runnable run;
        private String url;

        public exeingMultiPart(Context context, String str, MultipartBody.Builder builder, Runnable runnable) {
            this.context = context;
            this.url = str;
            this.requestBody = builder;
            this.run = runnable;
        }

        @Override 
        protected Object doInBackground(Object[] objArr) {
            OkHttpHelper.this.obj = null;
            try {
                OkHttpHelper.this.request = new Request.Builder().url(this.url).post(this.requestBody.build()).build();
                OkHttpHelper.this.client = new OkHttpClient.Builder().connectTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).build();
                Response responseExecute = OkHttpHelper.this.client.newCall(OkHttpHelper.this.request).execute();
                OkHttpHelper.this.response = responseExecute.body().string();
                StringBuilder sb = new StringBuilder();
                sb.append("url: ");
                sb.append(this.url);
                sb.append("\nbody: ");
                OkHttpHelper okHttpHelper = OkHttpHelper.this;
                sb.append(okHttpHelper.bodyToString(okHttpHelper.request));
                sb.append(" multipart\nheader: ");
                sb.append(OkHttpHelper.this.request.headers());
                sb.append("response: ");
                sb.append(OkHttpHelper.this.response);
                Log.d("okhttp", sb.toString());
                OkHttpHelper.this.obj = new JSONObject(OkHttpHelper.this.response);
                OkHttpHelper okHttpHelper2 = OkHttpHelper.this;
                okHttpHelper2.code = okHttpHelper2.obj.getInt("code");
                OkHttpHelper okHttpHelper3 = OkHttpHelper.this;
                okHttpHelper3.pesan = okHttpHelper3.obj.getString("pesan");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override 
        protected void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            if (OkHttpHelper.this.pBar != null) {
                OkHttpHelper.this.pBar.hide();
            }
            if (OkHttpHelper.this.obj != null) {
                this.run.run();
            } else {
                Toast.makeText(this.context, "Kesalahan Jaringan, Coba Lagi", Toast.LENGTH_LONG).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String bodyToString(Request request) {
        try {
            Request requestBuild = request.newBuilder().build();
            Buffer buffer = new Buffer();
            requestBuild.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (IOException unused) {
            return "did not work";
        }
    }

    public void multiPart(Context context, String str, HashMap<String, String> map, HashMap<String, String> map2, Runnable runnable, boolean z, RelativeLayout relativeLayout, View view) {
        if (z) {
            PBar pBar = new PBar(context, relativeLayout, view);
            this.pBar = pBar;
            pBar.show();
        } else {
            this.pBar = null;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry2 : map2.entrySet()) {
            builder.addFormDataPart(entry2.getKey(), entry2.getValue());
            String strSubstring = entry2.getValue().substring(entry2.getValue().lastIndexOf(File.separator) + 1);
            builder.addFormDataPart(entry2.getKey(), strSubstring, RequestBody.create(MediaType.parse(MimeTypeUtil.getType(strSubstring.substring(strSubstring.lastIndexOf(".") + 1))), new File(entry2.getValue())));
        }
        this.sp = context.getSharedPreferences("user", 0);
        new exeingMultiPart(context, str, builder, runnable).execute(new Object[0]);
    }

    public void posting(Context context, String str, FormBody.Builder builder, Runnable runnable, boolean z, RelativeLayout relativeLayout, View view) {
        this.sp = context.getSharedPreferences("user", 0);
        new exeingPost(context, str, builder, runnable).execute(new Object[0]);
    }

    public void posting(Context context, String str, HashMap<String, String> map, Runnable runnable, boolean z, RelativeLayout relativeLayout, View view) {
        Log.d("cek_param", "url: " + str + ", param: " + map);
        if (z) {
            PBar pBar = new PBar(context, relativeLayout, view);
            this.pBar = pBar;
            pBar.show();
        } else {
            this.pBar = null;
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        this.sp = context.getSharedPreferences("user", 0);
        new exeingPost(context, str, builder, runnable).execute(new Object[0]);
    }

    public void postTwoRunnables(Context context, String str, HashMap<String, String> map, Runnable runnable, Runnable runnable2, boolean z, RelativeLayout relativeLayout, View view) {
        if (z) {
            PBar pBar = new PBar(context, relativeLayout, view);
            this.pBar = pBar;
            pBar.show();
        } else {
            this.pBar = null;
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        this.sp = context.getSharedPreferences("user", 0);
        new exeingPost(context, str, builder, runnable).execute(new Object[0]);
    }

    public void putting(Context context, String str, HashMap<String, String> map, Runnable runnable, boolean z, RelativeLayout relativeLayout, View view) {
        if (z) {
            PBar pBar = new PBar(context, relativeLayout, view);
            this.pBar = pBar;
            pBar.show();
        } else {
            this.pBar = null;
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        this.sp = context.getSharedPreferences("user", 0);
        new exeingPut(context, str, builder, runnable).execute(new Object[0]);
    }

    public void deleting(Context context, String str, Runnable runnable, boolean z, RelativeLayout relativeLayout, View view) {
        if (z) {
            PBar pBar = new PBar(context, relativeLayout, view);
            this.pBar = pBar;
            pBar.show();
        } else {
            this.pBar = null;
        }
        this.sp = context.getSharedPreferences("user", 0);
        new exeingDelete(context, str, runnable).execute(new Object[0]);
    }

    public void getting(Context context, String str, Runnable runnable, boolean z, RelativeLayout relativeLayout, View view) {
        if (z) {
            PBar pBar = new PBar(context, relativeLayout, view);
            this.pBar = pBar;
            pBar.show();
        } else {
            this.pBar = null;
        }
        this.sp = context.getSharedPreferences("user", 0);
        new exeingGet(context, str, runnable).execute(new Object[0]);
    }

    public void gettingNoPb(Context context, String str, Runnable runnable) {
        this.sp = context.getSharedPreferences("user", 0);
        new exeingGetNoPb(context, str, runnable).execute(new Object[0]);
    }

    public void cancelAll() {
        OkHttpClient okHttpClient = this.client;
        if (okHttpClient != null) {
            okHttpClient.newCall(this.request).cancel();
        }
    }
}
