package com.meng.grzxv2;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.google.gson.*;
import com.meng.grzxv2.javaBean.bilibili.user.BilibiliPersonInfo;

import java.io.*;
import java.net.*;

public class InfoActivity extends Activity{
	public ProgressBar progressBar;
	public ProgressBar progressBar2;
	private TabHost tabHost;

	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;
	private MengNetworkTextview mnt    ;




	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		if(intent.getStringExtra("qq")==null||intent.getStringExtra("bid")==null){
			finish();
		  }	
		setContentView(R.layout.info_list);

		progressBar=(ProgressBar) findViewById(R.id.info_listProgressBar1);
		progressBar2=(ProgressBar) findViewById(R.id.info_listProgressBar2);

		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );
		mnt    =(MengNetworkTextview)findViewById(R.id.    );




		tabHost=(TabHost) findViewById(R.id.pixiv_download_main_tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("屑站").setContent(R.id.info_listFrameLayout1));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("QQ").setContent(R.id.info_listFrameLayout2));

		getBilibiliUserInfo(intent.getStringExtra("bid"),listview);
	  }
	private void getBilibiliUserInfo(final String uid, final ListView listview){
        new Thread(new Runnable(){

			  @Override
			  public void run(){
				  try{
					  URL url = new URL("https://api.bilibili.com/x/space/acc/info?mid="+uid+"&jsonp=jsonp");
					  HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
					  BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
					  String line;
					  StringBuilder stringBuilder = new StringBuilder();
					  while((line=reader.readLine())!=null){
						  stringBuilder.append(line);
						}
					  final BilibiliPersonInfo bilibiliPersonInfoJavaBean = new Gson().fromJson(stringBuilder.toString(),BilibiliPersonInfo.class);
					  runOnUiThread(new Runnable(){

							@Override
							public void run(){
							  progressBar.setVisibility(View.GONE);
								listview.setAdapter(new ArrayAdapter<String>(InfoActivity.this,android.R.layout.simple_list_item_1,bilibiliPersonInfoJavaBean.toArrayList()));
							  }
						  });
					}catch(Exception e){
					  e.printStackTrace();
					}
				}
			}).start();
	}
  }
