package com.epoool.am.Views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.epoool.am.R;

public class DialogConfirm extends Dialog {

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

    public DialogConfirm(Context context) {
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
                btnConfirm.setBackgroundResource(R.drawable.round_red_button);
                btnConfirm.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                break;
            case SUCCESS:
                btnConfirm.setBackgroundResource(R.drawable.round_green_button);
                btnConfirm.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                break;
            case DEFAULT:
            default:
                btnConfirm.setBackgroundResource(R.drawable.round_primary_button);
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

    public DialogConfirm setTitle(String title) {
        this.title = title;
        return this;
    }

    public DialogConfirm setMessage(String message) {
        this.message = message;
        return this;
    }

    public DialogConfirm setConfirmText(String confirmText) {
        this.confirmText = confirmText;
        return this;
    }

    public DialogConfirm setCancelText(String cancelText) {
        this.cancelText = cancelText;
        return this;
    }

    public DialogConfirm setDialogType(DialogType dialogType) {
        this.dialogType = dialogType;
        return this;
    }

    public DialogConfirm setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
        return this;
    }

    public DialogConfirm setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }
}

