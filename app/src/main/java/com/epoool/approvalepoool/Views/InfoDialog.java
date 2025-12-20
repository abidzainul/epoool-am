package com.epoool.approvalepoool.Views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.epoool.approvalepoool.R;

public class InfoDialog extends Dialog {

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

    public InfoDialog(Context context) {
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

        ImageView ivIcon = findViewById(R.id.iv_icon);
        FrameLayout iconContainer = findViewById(R.id.icon_container);
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvMessage = findViewById(R.id.tv_message);
        Button btnOK = findViewById(R.id.btn_ok);

        tvTitle.setText(title);
        tvMessage.setText(message);
        btnOK.setText(buttonText);

        // Set icon and colors based on dialog type
        switch (dialogType) {
            case ERROR:
                iconContainer.setBackgroundResource(R.drawable.ic_background_error);
                ivIcon.setImageResource(R.drawable.ic_error_24);
                btnOK.setBackgroundResource(R.drawable.round_red_button);
                btnOK.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                break;
            case SUCCESS:
                iconContainer.setBackgroundResource(R.drawable.ic_background_success);
                ivIcon.setImageResource(R.drawable.ic_check_normal_dark);
                btnOK.setBackgroundResource(R.drawable.round_green_button);
                btnOK.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                break;
            case INFO:
            case DEFAULT:
            default:
                iconContainer.setBackgroundResource(R.drawable.ic_background_default);
                ivIcon.setImageResource(R.drawable.ic_info_24);
                btnOK.setBackgroundResource(R.drawable.round_blue_button);
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
    public InfoDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public InfoDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public InfoDialog setButtonText(String buttonText) {
        this.buttonText = buttonText;
        return this;
    }

    public InfoDialog setDialogType(DialogType dialogType) {
        this.dialogType = dialogType;
        return this;
    }

    public InfoDialog setOnOKListener(OnOKListener onOKListener) {
        this.onOKListener = onOKListener;
        return this;
    }
}

