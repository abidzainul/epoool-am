package com.epoool.approvalepoool.Views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.epoool.approvalepoool.R;

public class DialogInfo extends Dialog {

    private final Context context;
    private String title;
    private String message;
    private String buttonText;
    private DialogType dialogType;
    private OnOKListener onOKListener;

    public enum DialogType {
        ERROR,
        SUCCESS,
        DEFAULT,
        INFO
    }

    public interface OnOKListener {
        void onOK();
    }

    public DialogInfo(Context context) {
        super(context);
        this.context = context;
        this.buttonText = "OKE";
        this.dialogType = DialogType.INFO;
        this.title = "Information";
        this.message = "This is an information message";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_info);

        if (getWindow() != null) {
            getWindow().setLayout((int) (context.getResources().getDisplayMetrics().widthPixels * 0.85),
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvMessage = findViewById(R.id.tv_message);
        Button btnOK = findViewById(R.id.btn_ok);

        tvTitle.setText(title);
        tvMessage.setText(message);
        btnOK.setText(buttonText);

        // Set icon and colors based on dialog type
        switch (dialogType) {
            case ERROR:
                btnOK.setBackgroundResource(R.drawable.round_red_button);
                btnOK.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                break;
            case SUCCESS:
                btnOK.setBackgroundResource(R.drawable.round_green_button);
                btnOK.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                break;
            case INFO:
            case DEFAULT:
            default:
                btnOK.setBackgroundResource(R.drawable.round_primary_button);
                btnOK.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                break;
        }

        btnOK.setOnClickListener(v -> {
            if (onOKListener != null) {
                onOKListener.onOK();
            }
            dismiss();
        });

        setCanceledOnTouchOutside(false);
    }

    // Builder methods for fluent API
    public DialogInfo setTitle(String title) {
        this.title = title;
        return this;
    }

    public DialogInfo setMessage(String message) {
        this.message = message;
        return this;
    }

    public DialogInfo setButtonText(String buttonText) {
        this.buttonText = buttonText;
        return this;
    }

    public DialogInfo setDialogType(DialogType dialogType) {
        this.dialogType = dialogType;
        return this;
    }

    public DialogInfo setOnOKListener(OnOKListener onOKListener) {
        this.onOKListener = onOKListener;
        return this;
    }
}

