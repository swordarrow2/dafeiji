package com.meng.TaiHunDanmaku.ui;
import android.app.*;
import android.os.*;
import android.widget.*;
import com.InsProcess.*;

public class MainActivity2 extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		
		String eclStr=new zunRunner().toString();

		TextView tv=new TextView(this);
		tv.setText(eclStr);
		setContentView(tv);
	  }

  }
