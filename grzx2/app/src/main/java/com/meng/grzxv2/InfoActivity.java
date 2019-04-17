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
import com.meng.grzxv2.javaBean.bilibili.spaceToLive.*;
import android.graphics.*;

public class InfoActivity extends Activity{
	public ProgressBar progressBar;
	public ProgressBar progressBar2;
	private TabHost tabHost;

	private MengNetworkTextview mntid    ;
	private MengNetworkTextview mntname    ;
	private MengNetworkTextview mntsex    ;
	private MengNetworkTextview mntsign    ;
	private MengNetworkTextview mntlevel    ;
	private MengNetworkTextview mntbirthday    ;
	private MengNetworkTextview mntcoins    ;
	private MengNetworkTextview mntviptype    ;
	private MengNetworkTextview mntvipstatus    ;
private MengNetworkTextview mntlive;
	private MengNetworkTextview mnttitle;
	private MengNetworkTextview mntstatus;
	private MengNetworkTextview mntroomid;
	

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

		mntid=(MengNetworkTextview)findViewById(R.id.info_list_mengNetworkTextview_id);
		mntname=(MengNetworkTextview)findViewById(R.id.info_list_mengNetworkTextview_name);
		mntsex=(MengNetworkTextview)findViewById(R.id. info_list_mengNetworkTextview_sex);
		mntsign=(MengNetworkTextview)findViewById(R.id.info_list_mengNetworkTextview_sign);
		mntlevel=(MengNetworkTextview)findViewById(R.id.info_list_mengNetworkTextview_level);
		mntbirthday=(MengNetworkTextview)findViewById(R.id.info_list_mengNetworkTextview_birthday);
		mntcoins=(MengNetworkTextview)findViewById(R.id.info_list_mengNetworkTextview_coins);
		mntviptype=(MengNetworkTextview)findViewById(R.id.info_list_mengNetworkTextview_viptype);
		mntvipstatus=(MengNetworkTextview)findViewById(R.id.info_list_mengNetworkTextview_vipstatus);
        mntlive=(MengNetworkTextview)findViewById(R.id.info_list_mengNetworkTextview_live);
		mntstatus=(MengNetworkTextview)findViewById(R.id.info_list_mengNetworkTextview_livestatus);	
		mnttitle=(MengNetworkTextview)findViewById(R.id.info_list_mengNetworkTextview_livetitle);
		mntroomid=(MengNetworkTextview)findViewById(R.id.info_list_mengNetworkTextview_roomid);
		
		mntid.setTitle("ID");
		mntname.setTitle("用户名");
		mntsex.setTitle("性别");
		mntsign.setTitle("签名");
		mntlevel.setTitle("等级");
		mntbirthday.setTitle("生日");
		mntcoins.setTitle("硬币数");
		mntviptype.setTitle("vip类型");
		mntvipstatus.setTitle("vip状态");
		mntlive.setTitle("直播url");
		mntstatus.setTitle("状态");
		mnttitle.setTitle("标题");
		mntroomid.setTitle("房间号");
		((ImageView)findViewById(R.id.info_listImageView)).setImageBitmap(BitmapFactory.decodeFile(MainActivity.mainDic + "bilibili/" + intent.getStringExtra("bid") + ".jpg"));
		tabHost=(TabHost) findViewById(R.id.pixiv_download_main_tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("屑站").setContent(R.id.info_listFrameLayout1));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("QQ").setContent(R.id.info_listFrameLayout2));

		getBilibiliUserInfo(intent.getStringExtra("bid"));
	  }
	  
	private void getBilibiliUserInfo(final String uid){
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
								mntid.setSummry(bilibiliPersonInfoJavaBean.data.mid);
								mntname.setSummry(bilibiliPersonInfoJavaBean.data.name);
								mntsex.setSummry(bilibiliPersonInfoJavaBean.data.sex);
								mntsign.setSummry(bilibiliPersonInfoJavaBean.data.sign);
								mntlevel.setSummry(bilibiliPersonInfoJavaBean.data.level);
								mntbirthday.setSummry(bilibiliPersonInfoJavaBean.data.birthday);
								mntcoins.setSummry(bilibiliPersonInfoJavaBean.data.coins);
								mntviptype.setSummry(bilibiliPersonInfoJavaBean.data.vip.type);
								mntvipstatus.setSummry(bilibiliPersonInfoJavaBean.data.vip.status);		
							  }
						  });
						  Thread.sleep(500);
					  new Thread(new Runnable(){

							@Override
							public void run(){
								try{
									URL url = new URL("https://api.live.bilibili.com/room/v1/Room/getRoomInfoOld?mid="+uid);
									HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
									BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
									String line;
									StringBuilder stringBuilder = new StringBuilder();
									while((line=reader.readLine())!=null){
										stringBuilder.append(line);
									  }
									final SpaceToLiveJavaBean sjb = new Gson().fromJson(stringBuilder.toString(),SpaceToLiveJavaBean.class);
									runOnUiThread(new Runnable(){

										  @Override
										  public void run(){
											  mntlive.setSummry(sjb.data.url);
											  mnttitle.setSummry(sjb.data.title);
											  mntstatus.setSummry(sjb.data.liveStatus==1?"正在直播":"未直播");
											  mntroomid.setSummry(sjb.data.roomid);
											}
										});
								  }catch(Exception e){
									e.printStackTrace();
								  }
							  }
						  }).start(); 
					}catch(Exception e){
					  e.printStackTrace();
					}
				}
			}).start();
	  }
  }
