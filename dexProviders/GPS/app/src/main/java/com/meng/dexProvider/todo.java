package com.meng.dexProvider;

import android.app.*;
import android.content.*;
import android.location.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class todo extends Activity implements todointerface{
	Context context;
	private final String DEXTITLE="GPS";
	private Location currentLocation;
	private TextView textView;
	LocationManager locationManager;
	

	@Override
	public ViewGroup init(Context context){
		// TODO: Implement this method
		this.context=context;
		textView=new TextView(context);	
		locationManager = (LocationManager)context.getSystemService(LOCATION_SERVICE);
		// 创建一个Criteria对象
		Criteria criteria = new Criteria();
		// 设置粗略精确度
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		// 设置是否需要返回海拔信息
		criteria.setAltitudeRequired(true);
		// 设置是否需要返回方位信息
		criteria.setBearingRequired(true);
		// 设置是否允许付费服务
		criteria.setCostAllowed(true);
		// 设置电量消耗等级
		criteria.setPowerRequirement(Criteria.POWER_HIGH);
		// 设置是否需要返回速度信息
		criteria.setSpeedRequired(true);
		// 根据设置的Criteria对象，获取最符合此标准的provider对象
		String currentProvider = locationManager.getBestProvider(criteria, true);
		// 根据当前provider对象获取最后一次位置信息
		currentLocation = locationManager.getLastKnownLocation(currentProvider);
		getLocationInfo(currentLocation);
		LocationListener locationListener = new LocationListener() {
    		// LocationProvider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
			public void onStatusChanged(String provider, int status, Bundle extras) {
			}
			//LocationProvider被enable时触发此函数，比如GPS被打开
			public void onProviderEnabled(String provider) {
				textView.setText("GPS已打开,正在确定位置");
			}
			//LocationProvider被disable时触发此函数，比如GPS被关闭
			public void onProviderDisabled(String provider) {
				textView.setText("GPS被停用");
			}
			//当坐标改变时触发此函数；如果LocationProvider传进相同的坐标，它就不会被触发  
			public void onLocationChanged(Location location) {
				getLocationInfo(location);
			}
		};
		// 注册监听器 locationListener   
		//第 2 、 3个参数可以控制接收GPS消息的频度以节省电力。第 2个参数为毫秒， 表示调用 listener的周期，
		//第 3个参数为米 ,表示位置移动指定距离后就调用 locationListener  
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
		LinearLayout ll=new LinearLayout(context);
		ll.addView(textView);
		
		return ll;
	}
	
	@Override
	public void main(){
		// TODO: Implement this method
		
	}

	@Override
	public String getDexTitle(){
		// TODO: Implement this method
		return DEXTITLE;
	}

	
	private void getLocationInfo(Location location){
		if(location!=null){
			//获得经度
			double lag = location.getLatitude();
			//获得纬度
			double lng = location.getLongitude();
			//获得速度
			double speed = location.getSpeed();
			//获得精度

			double dd=0;
			if(location.hasAltitude()){
				dd=location.getAltitude();
			}

			float acc = 0;
			if(location.hasAccuracy()){
				acc = location.getAccuracy();
			}
			double height = 0;
			if(location.hasAltitude())
				height = location.getAltitude();
			float bear = 0;
			if(location.hasBearing())
				bear = location.getBearing();
			textView.setText("经度是："+lag+"\n"+"纬度是："+lng+"\n速度是："+speed+"\n精度是："+acc
							 +"\n高度是："+height+"\n风向是："+bear+dd);
		}else{
			textView.setText("没有获取到信息");
		}
	}

	@Override
	public void onDexPause(){
		// TODO: Implement this method
	}

	@Override
	public void onDexResume(){
		// TODO: Implement this method
	}

	
}  
