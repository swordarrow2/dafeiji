package com.meng.TaiHunDanmaku.ui;
import android.app.*;
import android.os.*;
import com.meng.TaiHunDanmaku.InsProcess.*;
import android.widget.*;

public class MainActivity2 extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		Ecl ecl=new Ecl();
		Sub s1=ecl.sub("testSub", 2);
		Ins600 ins=s1.ins600();
		ins._600();
		ins._602(2, 5);
		ins._603(64, 64);
		ins._601();
		ins._600();
		ins._602(4, 2);
		ins._603(85, 12);
		ins._601();
		ins._601(1);

		Sub s2=ecl.sub("MainFront");
		VarIns v = s2.varIns().a(64).b(64).c(2).d(5);
		s2.ins600()
		  .	_600()
		  .	posAndImg(64, 64, 2, 5)
		  .	_601()
		  .	_600()
		  .	posAndImg(v.floatA() , v.floatB(), v.intC(), "5")
		  .	_602(4, 2)
		  .	_603(85, 12)
		  .	_601()
		  .	_601(1);

		String eclStr=ecl.toString();

		TextView tv=new TextView(this);
		tv.setText(eclStr);
		setContentView(tv);
	  }

  }
