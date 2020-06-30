package com.meng.stg2.helpers;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.meng.stg2.ui.MainActivity;

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
