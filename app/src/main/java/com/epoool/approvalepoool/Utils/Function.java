package com.epoool.approvalepoool.Utils;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

public class Function {
    public static void getScreenSize(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        Constant.width = point.y;
        Constant.height = point.x;
    }

    public static void snackBarRed(Context context, String str) {
        Snackbar snackbarMake = Snackbar.make(((Activity) context).findViewById(R.id.content), str, Snackbar.LENGTH_LONG);
        View view = snackbarMake.getView();
        view.setBackgroundColor(SupportMenu.CATEGORY_MASK);
        ((TextView) view.findViewById(com.epoool.approvalepoool.R.id.snackbar_text)).setTextColor(-1);
        snackbarMake.show();
    }

    public static void openAct(Context context) {
        ((Activity) context).overridePendingTransition(com.epoool.approvalepoool.R.anim.pull_in_right, com.epoool.approvalepoool.R.anim.push_out_left);
    }

    public static void closeAct(Context context) {
        ((Activity) context).overridePendingTransition(com.epoool.approvalepoool.R.anim.pull_in_left, com.epoool.approvalepoool.R.anim.push_out_right);
    }

    public static void toast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static HashMap<String, String> paramAdderPost(HashMap<String, String> map) {
        map.put("id_username", Constant.idUsername);
        map.put("id_reference", Constant.idReference);
        map.put("token", Constant.token);
        map.put("serial_number", Build.SERIAL);
        return map;
    }

    public static void sendToken(final Context context) {
        try {
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener() { 
                @Override 
                public final void onComplete(Task task) {
                    Function.lambda$sendToken$1(context, task);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ void lambda$sendToken$1(Context context, Task task) {
        if (!task.isSuccessful()) {
            task.getException().printStackTrace();
            return;
        }
        String str = (String) task.getResult();
        Constant.token_fcm = str;
        context.getSharedPreferences("MyPreferences", 0).edit().putString("token_fcm", str).apply();
        final OkHttpHelper okHttpHelper = new OkHttpHelper();
        Runnable runnable = new Runnable() { 
            @Override 
            public final void run() {
                Function.lambda$null$0(okHttpHelper);
            }
        };
        HashMap map = new HashMap();
        map.put("token_fcm", str);
        okHttpHelper.posting(context, Constant.url + "update_token_fcm", paramAdderPost(map), runnable, false, (RelativeLayout) null, (View) null);
    }

    static /* synthetic */ void lambda$null$0(OkHttpHelper okHttpHelper) {
        if (okHttpHelper.code == 1) {
            Log.d("SendToken", okHttpHelper.response);
        }
    }

    public static void getFragment(Context context, Fragment fragment, int i) {
        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(i, fragment).commit();
        Log.d("Function_get_fragment", fragment + "");
    }

    public static void switchView(View view, View view2) {
        view.setVisibility(View.GONE);
        view2.setVisibility(View.VISIBLE);
    }

    public static String toDay(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        try {
            return new SimpleDateFormat("EEEE", new Locale("en", "US")).format(new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "US")).parse(str));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String translateDay(String str) {
        String day = toDay(str);
        return day.equals("Sunday") ? "Minggu" : day.equals("Monday") ? "Senin" : day.equals("Tuesday") ? "Selasa" : day.equals("Wednesday") ? "Rabu" : day.equals("Thursday") ? "Kamis" : day.equals("Friday") ? "Jum'at" : day.equals("Saturday") ? "Sabtu" : "";
    }

    public static String toTanggalBaru(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        try {
            return new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID")).format(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(str));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
