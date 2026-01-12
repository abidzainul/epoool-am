package com.epoool.am.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.epoool.am.R;

public class DialogHelper {
    public static void showWithDoubleButton(Context context, String str, String str2, String str3, String str4, final Runnable runnable) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_biru);
        dialog.setCancelable(true);
        dialog.setTitle(str);
        dialog.show();
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_whole);
        TextView textView = (TextView) dialog.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) dialog.findViewById(R.id.tv_dialog);
        Button button = (Button) dialog.findViewById(R.id.btn_ok);
        Button button2 = (Button) dialog.findViewById(R.id.btn_cancel);
        linearLayout.getLayoutParams().width = (Constant.height * 8) / 10;
        textView.setText(str);
        textView2.setText(str2);
        button.setText(str3);
        button2.setText(str4);
        button.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
                runnable.run();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public static void showWithDoubleButtonNotCancellable(Context context, String str, String str2, String str3, String str4, final Runnable runnable, final Runnable runnable2) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_biru);
        dialog.setCancelable(false);
        dialog.setTitle(str);
        dialog.show();
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_whole);
        TextView textView = (TextView) dialog.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) dialog.findViewById(R.id.tv_dialog);
        Button button = (Button) dialog.findViewById(R.id.btn_ok);
        Button button2 = (Button) dialog.findViewById(R.id.btn_cancel);
        linearLayout.getLayoutParams().width = (Constant.height * 8) / 10;
        textView.setText(str);
        textView2.setText(str2);
        button.setText(str3);
        button2.setText(str4);
        button.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
                runnable.run();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
                runnable2.run();
            }
        });
    }

    public static void showNotCancellable(Context context, String str, String str2, String str3, String str4, final Runnable runnable) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_biru);
        dialog.setCancelable(false);
        dialog.setTitle(str);
        dialog.show();
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_whole);
        TextView textView = (TextView) dialog.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) dialog.findViewById(R.id.tv_dialog);
        Button button = (Button) dialog.findViewById(R.id.btn_ok);
        Button button2 = (Button) dialog.findViewById(R.id.btn_cancel);
        linearLayout.getLayoutParams().width = (Constant.height * 8) / 10;
        textView.setText(str);
        textView2.setText(str2);
        button.setText(str3);
        button2.setText(str4);
        button.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
                runnable.run();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public static void showOneButton(Context context, String str, String str2, String str3, final Runnable runnable) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_biru);
        dialog.setCancelable(true);
        dialog.setTitle(str);
        dialog.show();
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_whole);
        TextView textView = (TextView) dialog.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) dialog.findViewById(R.id.tv_dialog);
        Button button = (Button) dialog.findViewById(R.id.btn_ok);
        Button button2 = (Button) dialog.findViewById(R.id.btn_cancel);
        linearLayout.getLayoutParams().width = (Constant.height * 8) / 10;
        textView.setText(str);
        textView2.setText(str2);
        button.setText(str3);
        button2.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
    }

    public static void showOneButtonNotCancellable(Context context, String str, String str2, String str3, final Runnable runnable) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_biru);
        dialog.setCancelable(false);
        dialog.setTitle(str);
        dialog.show();
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_whole);
        TextView textView = (TextView) dialog.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) dialog.findViewById(R.id.tv_dialog);
        Button button = (Button) dialog.findViewById(R.id.btn_ok);
        Button button2 = (Button) dialog.findViewById(R.id.btn_cancel);
        linearLayout.getLayoutParams().width = (Constant.height * 8) / 10;
        textView.setText(str);
        textView2.setText(str2);
        button.setText(str3);
        button2.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
                runnable.run();
            }
        });
    }

    public static void showOneButtonAlternate(Context context, String str, String str2, String str3, final Runnable runnable) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_alternate);
        dialog.show();
        TextView textView = (TextView) dialog.findViewById(R.id.tv_title_dialog);
        TextView textView2 = (TextView) dialog.findViewById(R.id.tv_dialog);
        Button button = (Button) dialog.findViewById(R.id.btn_dialog);
        ((LinearLayout) dialog.findViewById(R.id.ll_whole)).getLayoutParams().width = (Constant.height * 90) / 100;
        textView.setText(str);
        textView2.setText(str2);
        button.setText(str3);
        button.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                dialog.dismiss();
                runnable.run();
            }
        });
    }
}
