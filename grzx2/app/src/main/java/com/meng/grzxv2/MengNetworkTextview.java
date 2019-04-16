package com.meng.grzxv2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.meng.grzxv2.R;


public class MengNetworkTextview extends LinearLayout {
    private Context context;

    private TextView textViewTitle;
    private TextView textViewSummry;
    private ProgressBar progressBar;

    public MengNetworkTextview(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.meng_network_textview, this);
        textViewTitle = (TextView) findViewById(R.id.meng_network_TextView);
        textViewSummry = (TextView) findViewById(R.id.meng_network_TextView2);
        progressBar = (ProgressBar) findViewById(R.id.meng_network_ProgressBar);

        textViewSummry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", ((Button) v).getText().toString());
                clipboardManager.setPrimaryClip(clipData);
            }
        });
    }

    public void setTitle(String s) {
        textViewTitle.setText(s);
    }

    public String getTitle() {
        return textViewTitle.getText().toString();
    }

    public void setSummry(final String s) {
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewSummry.setText(s);
                progressBar.setVisibility(GONE);
                MengNetworkTextview.this.setVisibility(VISIBLE);
            }
        });
    }

    public String getSummry() {
        return textViewSummry.getText().toString();
    }

}
