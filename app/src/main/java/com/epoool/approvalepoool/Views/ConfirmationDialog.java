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

public class ConfirmationDialog extends Dialog {

    private final Context context;
    private String title;
    private String message;
    private String confirmText;
    private String cancelText;
    private DialogType dialogType;
    private OnConfirmListener onConfirmListener;
    private OnCancelListener onCancelListener;

    public enum DialogType {
        ERROR,
        SUCCESS,
        DEFAULT
    }

    public interface OnConfirmListener {
        void onConfirm();
    }

    public interface OnCancelListener {
        void onCancel();
    }

    public ConfirmationDialog(Context context) {
        super(context);
        this.context = context;
        this.confirmText = "Confirm";
        this.cancelText = "Cancel";
        this.dialogType = DialogType.DEFAULT;
        this.title = "Confirmation";
        this.message = "Are you sure?";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirmation);

        if (getWindow() != null) {
            getWindow().setLayout((int) (context.getResources().getDisplayMetrics().widthPixels * 0.85),
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        ImageView ivIcon = findViewById(R.id.iv_icon);
        FrameLayout iconContainer = findViewById(R.id.icon_container);
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvMessage = findViewById(R.id.tv_message);
        Button btnConfirm = findViewById(R.id.btn_confirm);
        Button btnCancel = findViewById(R.id.btn_cancel);

        tvTitle.setText(title);
        tvMessage.setText(message);
        btnConfirm.setText(confirmText);
        btnCancel.setText(cancelText);

        switch (dialogType) {
            case ERROR:
                iconContainer.setBackgroundResource(R.drawable.ic_background_error);
                ivIcon.setImageResource(R.drawable.ic_error_24);
                btnConfirm.setBackgroundResource(R.drawable.round_red_button);
                btnConfirm.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                break;
            case SUCCESS:
                iconContainer.setBackgroundResource(R.drawable.ic_background_success);
                ivIcon.setImageResource(R.drawable.ic_check_normal_dark);
                btnConfirm.setBackgroundResource(R.drawable.round_green_button);
                btnConfirm.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                break;
            case DEFAULT:
            default:
                iconContainer.setBackgroundResource(R.drawable.ic_background_default);
                ivIcon.setImageResource(R.drawable.ic_info_24);
                btnConfirm.setBackgroundResource(R.drawable.round_blue_button);
                btnConfirm.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                break;
        }

        btnConfirm.setOnClickListener(v -> {
            if (onConfirmListener != null) {
                onConfirmListener.onConfirm();
            }
            dismiss();
        });

        btnCancel.setOnClickListener(v -> {
            if (onCancelListener != null) {
                onCancelListener.onCancel();
            }
            dismiss();
        });

        setCanceledOnTouchOutside(false);
    }

    public ConfirmationDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public ConfirmationDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public ConfirmationDialog setConfirmText(String confirmText) {
        this.confirmText = confirmText;
        return this;
    }

    public ConfirmationDialog setCancelText(String cancelText) {
        this.cancelText = cancelText;
        return this;
    }

    public ConfirmationDialog setDialogType(DialogType dialogType) {
        this.dialogType = dialogType;
        return this;
    }

    public ConfirmationDialog setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
        return this;
    }

    public ConfirmationDialog setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }
}

