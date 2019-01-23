package com.meng.stg.dialogs;

import android.app.*;
import android.content.*;
import com.meng.stg.*;

public class ErrDialog{
	public static void Show(String title,String message){
		new AlertDialog.Builder(MainActivity.Instance)
		  .setTitle(title)
		  .setMessage(message)
		  .setIcon(android.R.drawable.stat_sys_warning)
		  .setPositiveButton("Dismiss",new DialogInterface.OnClickListener() {
			  @Override
			  public void onClick(DialogInterface arg0,int arg1){
				}
			})
		  .show();
	  }
  }
