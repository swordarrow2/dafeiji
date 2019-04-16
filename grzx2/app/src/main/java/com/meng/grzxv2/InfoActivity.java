package com.meng.grzxv2;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.google.gson.*;
import com.meng.grzxv2.javaBean.bilibili.*;
import java.io.*;
import java.net.*;

public class InfoActivity extends Activity{
	public ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Intent i=getIntent();
		if(i.getStringExtra("g")==null){
			finish();
		  }
		setContentView(R.layout.info_list);
		listView=(ListView) findViewById(R.id.info_listListView);
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			  @Override
			  public boolean onItemLongClick(AdapterView<?> parent,View view,int position,long id){
				  ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
				  ClipData clipData = ClipData.newPlainText("text",(String) parent.getItemAtPosition(position));
				  clipboardManager.setPrimaryClip(clipData);
				  return true;
				}
			});
		getBilibiliHeadUrl(i.getStringExtra("g"),listView);
	  }
	private void getBilibiliHeadUrl(final String uid,final ListView listview){
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
								listview.setAdapter(new ArrayAdapter<String>(InfoActivity.this,android.R.layout.simple_list_item_1,bilibiliPersonInfoJavaBean.toArrayList()));
							  }
						  });
					}catch(Exception e){
					  e.printStackTrace();
					}
				}
			}).start();
		return ;
	  }
  }
