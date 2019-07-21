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
		ScrollView s=new ScrollView(this);
		TextView tv=new TextView(this);
		s.addView(tv);
		tv.setText(eclStr);
		setContentView(s);
	  }

  }
