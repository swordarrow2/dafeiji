package com.meng.dexProvider;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class todo extends Activity implements todointerface{
	Context context;
	private TextView tv;
	private Button btn;
	private final String DEXTITLE="启动QQ";

	
	@Override
	public void onDexResume(){
		// TODO: Implement this method
		 showToast();
	}

	@Override
	public void onDexPause(){
		// TODO: Implement this method
	//	btn.setText("pause");
		showToast2();
	}

	
	@Override
	public ViewGroup init(Context context){
		// TODO: Implement this method
		this.context=context;
		tv=new TextView(context);
		btn=new Button(context);
		btn.setText("点击启动QQ");
		btn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1){
					// TODO: Implement this method
		//			showToast();
					tv.setText("正在启动");
					btn.setText("正在启动");
					launchQQ();
				}
			});
		LinearLayout ll=new LinearLayout(context);
		ll.addView(btn);
		
		return ll;
	}
	
	@Override
	public void main(){
		// TODO: Implement this method
	//	tv.setText("test");
	}

	@Override
	public String getDexTitle(){
		// TODO: Implement this method
		return DEXTITLE;
	}

	public int showToast(){ 
        Toast.makeText(context,"openqq resume",Toast.LENGTH_LONG).show();  
        return 100;  
    }  
	public int showToast2(){ 
        Toast.makeText(context,"openqq pause",Toast.LENGTH_LONG).show();  
        return 100;  
    }  
	
	private void launchQQ(){
		//ComponentName componetName = new ComponentName("com.downloading.main.baiduyundownload","com.downloading.main.baiduyundownload.home.D");	
		ComponentName componetName = new ComponentName("com.tencent.mobileqq","com.tencent.mobileqq.activity.SplashActivity");	
		try{
			Intent intent = new Intent();
			intent.setComponent(componetName);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		}catch(Exception e){
			tv.setText(e.toString());
		}
	}
	



}  
