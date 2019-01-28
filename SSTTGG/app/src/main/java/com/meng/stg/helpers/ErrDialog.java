package com.meng.stg.helpers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.meng.stg.MainActivity;

public class ErrDialog
{
	public static void Show(String title, String message)
	{
		new AlertDialog.Builder(MainActivity.Instance)
		.setTitle(title)
		.setMessage(message)
		.setIcon(android.R.drawable.stat_sys_warning)
		.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			}
		})
		.show();
	}
}
