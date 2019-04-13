package com.meng.dexProvider;

import android.content.*;
import android.view.*;

public interface todointerface{  
	public ViewGroup init(Context context);
	public void main();
	public String getDexTitle();
	public void onDexResume();
	public void onDexPause();
}  
