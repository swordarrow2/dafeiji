package com.meng.dexProvider;

import android.app.*;
import android.content.*;
import android.hardware.*;
import android.view.*;
import android.widget.*;

public class todo extends Activity implements todointerface,SensorEventListener{

	Context context;
	private final String DEXTITLE="方向";
	private TextView textView;
	Sensor compass;
	SensorManager sm;
	Notification.Builder builder;
	NotificationManager manager;

	@Override
	public void onSensorChanged(SensorEvent p1){
		textView.setText("方向\nx:"+p1.values[SensorManager.DATA_X]);
		builder=new Notification. Builder(context);
        // new Notification.Builder(this)  
        builder.setContentTitle(context.getString(com.meng.dexReader. R.string.app_name))  
			.setContentText("方向\nx:"+p1.values[SensorManager.DATA_X])
		 .setSmallIcon(R.drawable.ic_launcher); 
		manager.notify(1,builder.build());
		
	}

	@Override
	public void onAccuracyChanged(Sensor p1,int p2){
		// TODO: Implement this method
	}


	@Override
	public ViewGroup init(Context context){
		// TODO: Implement this method
		this.context=context;
		textView=new TextView(context);	
		sm=(SensorManager)context. getSystemService(Context.SENSOR_SERVICE);
		compass=sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);

		manager=(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);  
		  

		LinearLayout ll=new LinearLayout(context);
		ll.addView(textView);

		return ll;
	}

	@Override
	public void main(){
		// TODO: Implement this method
		sm.registerListener(this,compass,SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	public String getDexTitle(){
		return DEXTITLE;
	}

	@Override
	public void onDexPause(){
		// TODO: Implement this method
	//	manager.cancelAll();
	//	sm.unregisterListener(this);
	//	Toast.makeText(context,"监听器已卸载",Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDexResume(){
		// TODO: Implement this method
		sm.registerListener(this,compass,SensorManager.SENSOR_DELAY_UI);
		Toast.makeText(context,"监听器已装载",Toast.LENGTH_LONG).show();

	}

	@Override
	public void onDexFinish(){
		// TODO: Implement this method
		manager.cancelAll();
		sm.unregisterListener(this);
		Toast.makeText(context,"监听器已卸载",Toast.LENGTH_LONG).show();
		
	}




}  
