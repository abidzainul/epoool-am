package com.epoool.approvalepoool.Utils;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class PBar {
    private ProgressBar progressbar;
    private View view;

    public PBar(Context context, RelativeLayout relativeLayout, View view) {
        this.view = view;
        this.progressbar = new ProgressBar(context, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        int i = (Constant.height * 40) / 100;
        relativeLayout.addView(this.progressbar);
        layoutParams.setMargins(i, 0, i, 0);
        layoutParams.addRule(13);
        this.progressbar.setLayoutParams(layoutParams);
        this.progressbar.getLayoutParams().height = 45;
        this.progressbar.setVisibility(View.GONE);
    }

    public void show() {
        Function.switchView(this.view, this.progressbar);
    }

    public void hide() {
        if (this.progressbar.isShown()) {
            Function.switchView(this.progressbar, this.view);
        }
    }
}
