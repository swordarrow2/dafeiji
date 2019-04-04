package com.meng.TaiHunDanmaku.helpers;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.meng.TaiHunDanmaku.ui.MainActivity;

public class ErrDialog{
    public static void show(String title,String message){
        new AlertDialog.Builder(MainActivity.instance)
                .setTitle(title)
                .setMessage(message)
                .setIcon(android.R.drawable.stat_sys_warning)
                .setPositiveButton("Dismiss",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0,int arg1){
                    }
                }).show();
    }
}
