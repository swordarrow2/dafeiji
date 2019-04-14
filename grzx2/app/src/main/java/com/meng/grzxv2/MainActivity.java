package com.meng.grzxv2;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;

import com.google.gson.*;
import com.meng.grzxv2.adapters.*;
import com.meng.grzxv2.javaBean.*;

import dalvik.system.*;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;

public class MainActivity extends Activity{
    public ListView listViewGroupReply, listViewQQNotReply, listViewWordNotReply,
	listViewPersonInfo;
    public EditText editTextName, editTextQQNumber, editTextBilibiliId, editTextBilibiliLiveRoom;
    public ConfigJavaBean configJavaBean;
    public TabHost tabHost;
	//  public final String IP = "123.207.65.93";
	//   public final int PORT = 9760;
    public Gson gson = new Gson();
    public static boolean onWifi = false;
    public static String mainDic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getActionBar().show();
        mainDic=Environment.getExternalStorageDirectory()+"/Pictures/grzx/";
        File f = new File(mainDic+"group/");
        if(!f.exists()){
            f.mkdirs();
		  }
        File f2 = new File(mainDic+"user/");
        if(!f2.exists()){
            f2.mkdirs();
		  }
        File f3 = new File(mainDic+"bilibili/");
        if(!f3.exists()){
            f3.mkdirs();
		  }
		/*	DexClassLoader loader = new DexClassLoader(dexPath,
		 dexOutputDir.getAbsolutePath(),
		 null, ClassLoader.getSystemClassLoader().getParent());
		 try{
		 Class clz = loader.loadClass("com.youbo.switchsky.LogUtils");
		 Object instance= clz.newInstance();
		 Method method = clz.getMethod("parse", Context.class);
		 method.invoke(instance,this);
		 }catch(Exception e){}

		 */
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        onWifi=wifiNetworkInfo.isConnected();

        listViewGroupReply=(ListView) findViewById(R.id.mainListView_GroupReply);
        listViewQQNotReply=(ListView) findViewById(R.id.mainListView_QQNotReply);
        listViewWordNotReply=(ListView) findViewById(R.id.mainListView_WordNotReply);
        listViewPersonInfo=(ListView) findViewById(R.id.mainListView_PersonInfo);

        tabHost=(TabHost) findViewById(R.id.pixiv_download_main_tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("回复的群").setContent(R.id.mainListView_GroupReply));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("不回复的QQ").setContent(R.id.mainListView_QQNotReply));
        tabHost.addTab(tabHost.newTabSpec("three").setIndicator("不回复的字").setContent(R.id.mainListView_WordNotReply));
        tabHost.addTab(tabHost.newTabSpec("four").setIndicator("账号").setContent(R.id.mainListView_PersonInfo));
		//     getJsonString();
		loadPersonInfoData();
        listViewGroupReply.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> adapterView,View view,final int i,long l){
				  final GroupConfig g=configJavaBean.groupConfigs.get(i);
				  final LinearLayout v=(LinearLayout) MainActivity.this.getLayoutInflater().inflate(R.layout.group_config,null);
				  final Switch  fudu  =(Switch)v.findViewById(R.id.group_configSwitch_repeat);
				  final	  Switch   setu =(Switch)v.findViewById(R.id.group_configSwitch_setu);
				  final  Switch  pohai  =(Switch)v.findViewById(R.id.group_configSwitch_pohai);
				  final  Switch  dic  =(Switch)v.findViewById(R.id.group_configSwitch_dic);
				  final  Switch bilibili   =(Switch)v.findViewById(R.id.group_configSwitch_bilibili);
				  final  Switch   cuigeng =(Switch)v.findViewById(R.id.group_configSwitch_cuigeng);
				  final  Switch  soutu  =(Switch)v.findViewById(R.id.group_configSwitch_soutu);
				  final  Switch   lianjie =(Switch)v.findViewById(R.id.group_configSwitch_link);
				  final  Switch  suijifeiji  =(Switch)v.findViewById(R.id.group_configSwitch_roll);
				  final	  Switch  erweima  =(Switch)v.findViewById(R.id.group_configSwitch_barcode);
				  final Switch  kuiping  =(Switch)v.findViewById(R.id.group_configSwitch_kuiping);
				  final  Switch  cqma  =(Switch)v.findViewById(R.id.group_configSwitch_cqcode);
				  final  Switch  zan  =(Switch)v.findViewById(R.id.group_configSwitch_zan);
				  final  Switch  moshenfusong  =(Switch)v.findViewById(R.id.group_configSwitch_mishenfusong);
				  fudu.setChecked(g.repeat);
				  setu.setChecked(g.setu);
				  pohai.setChecked(g.pohai);
				  dic.setChecked(g.dic);
				  bilibili.setChecked(g.bilibiliCheck);
				  cuigeng.setChecked(g.cuigeng);
				  soutu.setChecked(g.searchPic);
				  lianjie.setChecked(g.checkLink);
				  suijifeiji.setChecked(g.roll);
				  erweima.setChecked(g.barcode);
				  kuiping.setChecked(g.kuiping);
				  cqma.setChecked(g.cqCode);
				  zan.setChecked(g.zan);
				  moshenfusong.setChecked(g.moshenfusong);

				  new AlertDialog.Builder(MainActivity.this)
					.setView(v)
					.setTitle("编辑")
					.setPositiveButton("确定",new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface p1,int p2){
							g.repeat=fudu.isChecked();
							g.setu=setu.isChecked();
							g.pohai=pohai.isChecked();
							g.dic=dic.isChecked();
							g.bilibiliCheck=bilibili.isChecked();
							g.searchPic=soutu.isChecked();
							g.checkLink=lianjie.isChecked();
							g.roll=suijifeiji.isChecked();
							g.barcode=erweima.isChecked();
							g.kuiping=kuiping.isChecked();
							g.cqCode=cqma.isChecked();
							g.zan=zan.isChecked();
							g.moshenfusong=moshenfusong.isChecked();
							saveTxt(gson.toJson(configJavaBean));
						  }
					  }).show();
				  //			  g.groupNumber=Long.parseLong(editText.getText().toString());
				  //			  loadConfigData(gson.toJson(configJavaBean));

				}
			});
        listViewQQNotReply.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> adapterView,View view,final int i,long l){
				  final EditText editText = new EditText(MainActivity.this);
				  editText.setText(String.valueOf(configJavaBean.QQNotReply.get(i)));
				  new AlertDialog.Builder(MainActivity.this)
					.setView(editText)
					.setTitle("编辑")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							new AlertDialog.Builder(MainActivity.this)
							  .setTitle("确定修改吗")
							  .setPositiveButton("确定",new DialogInterface.OnClickListener() {
								  @Override
								  public void onClick(DialogInterface p11,int p2){
									  configJavaBean.QQNotReply.set(i,Long.parseLong(editText.getText().toString()));
									  loadConfigData(gson.toJson(configJavaBean));
									}
								}).setNegativeButton("取消",null).show();
						  }
					  }).setNegativeButton("取消",null).show();
				}
			});
        listViewWordNotReply.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> adapterView,View view,final int i,long l){
				  final EditText editText = new EditText(MainActivity.this);
				  editText.setText(String.valueOf(configJavaBean.wordNotReply.get(i)));
				  new AlertDialog.Builder(MainActivity.this)
					.setView(editText)
					.setTitle("编辑")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							new AlertDialog.Builder(MainActivity.this)
							  .setTitle("确定修改吗")
							  .setPositiveButton("确定",new DialogInterface.OnClickListener() {
								  @Override
								  public void onClick(DialogInterface p11,int p2){
									  configJavaBean.wordNotReply.set(i,editText.getText().toString());
									  loadConfigData(gson.toJson(configJavaBean));
									}
								}).setNegativeButton("取消",null).show();
						  }
					  }).setNegativeButton("取消",null).show();
				}
			});

        listViewPersonInfo.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(final AdapterView<?> p1,View p2,final int p3,long p4){
				  View view = getLayoutInflater().inflate(R.layout.person_info_edit_view,null);
				  editTextName=(EditText) view.findViewById(R.id.edit_viewEditText_name);
				  editTextQQNumber=(EditText) view.findViewById(R.id.edit_viewEditText_qq);
				  editTextBilibiliId=(EditText) view.findViewById(R.id.edit_viewEditText_bid);
				  editTextBilibiliLiveRoom=(EditText) view.findViewById(R.id.edit_viewEditText_b_live_room);

				  final PersonInfo mapBiliUser = configJavaBean.personInfo.get(p3);
				  editTextName.setText(mapBiliUser.name);
				  editTextQQNumber.setText(String.valueOf(mapBiliUser.qq));
				  editTextBilibiliId.setText(String.valueOf(mapBiliUser.bid));
				  editTextBilibiliLiveRoom.setText(String.valueOf(mapBiliUser.bliveRoom));

				  new AlertDialog.Builder(MainActivity.this)
					.setView(view)
					.setTitle("编辑")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							new AlertDialog.Builder(MainActivity.this)
							  .setTitle("确定修改吗")
							  .setPositiveButton("确定",new DialogInterface.OnClickListener() {
								  @Override
								  public void onClick(DialogInterface p11,int p2){
									  mapBiliUser.name=editTextName.getText().toString();
									  mapBiliUser.qq=Long.parseLong(editTextQQNumber.getText().toString());
									  mapBiliUser.bid=Integer.parseInt(editTextBilibiliId.getText().toString().replace("UID:",""));
									  mapBiliUser.bliveRoom=Integer.parseInt(editTextBilibiliLiveRoom.getText().toString().replace("http://live.bilibili.com/","").replace("?share_source=copy_link",""));
									  loadConfigData(gson.toJson(configJavaBean));
									}
								}).setNegativeButton("取消",null).show();
						  }
					  }).setNegativeButton("取消",null).show();
				}
			});
        listViewGroupReply.setOnItemLongClickListener(new OnItemLongClickListener() {

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){

				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.groupConfigs.remove(p3);
							loadConfigData(gson.toJson(configJavaBean));
						  }
					  }).setNegativeButton("取消",null).show();
				  return true;
				}
			});
        listViewQQNotReply.setOnItemLongClickListener(new OnItemLongClickListener() {

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){

				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.QQNotReply.remove(p3);
							loadConfigData(gson.toJson(configJavaBean));
						  }
					  }).setNegativeButton("取消",null).show();
				  return true;
				}
			});
        listViewWordNotReply.setOnItemLongClickListener(new OnItemLongClickListener() {

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){

				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.wordNotReply.remove(p3);
							loadConfigData(gson.toJson(configJavaBean));
						  }
					  }).setNegativeButton("取消",null).show();
				  return true;
				}
			});

        listViewPersonInfo.setOnItemLongClickListener(new OnItemLongClickListener() {

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){
				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.personInfo.remove(p3);
							loadConfigData(gson.toJson(configJavaBean));
						  }
					  }).setNegativeButton("取消",null).show();
				  return true;
				}
			});
	  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuItem add = menu.add(0,0,0,"添加");
        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        add.setIcon(R.drawable.ic_menu_add);
        MenuItem add2 = menu.add(1,1,1,"从服务器获取信息");
        add2.setIcon(R.drawable.stat_sys_download_anim0);
        add2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        MenuItem add3 = menu.add(2,2,2,"提交修改到服务器");
        add3.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        add3.setIcon(R.drawable.stat_sys_upload_anim0);
        return true;
	  }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getTitle().equals("添加")){
            if(tabHost.getCurrentTabTag().equals("four")){
                View view = getLayoutInflater().inflate(R.layout.person_info_edit_view,null);
                editTextName=(EditText) view.findViewById(R.id.edit_viewEditText_name);
                editTextQQNumber=(EditText) view.findViewById(R.id.edit_viewEditText_qq);
                editTextBilibiliId=(EditText) view.findViewById(R.id.edit_viewEditText_bid);
                editTextBilibiliLiveRoom=(EditText) view.findViewById(R.id.edit_viewEditText_b_live_room);

                new AlertDialog.Builder(MainActivity.this)
				  .setView(view)
				  .setTitle("编辑")
				  .setPositiveButton("确定",new DialogInterface.OnClickListener() {
					  @Override
					  public void onClick(DialogInterface p11,int p2){

						  new AlertDialog.Builder(MainActivity.this)
							.setTitle("确定修改吗")
							.setPositiveButton("确定",new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface p11,int p2){
									for(PersonInfo user : configJavaBean.personInfo){
										if(Long.parseLong(editTextBilibiliId.getText().toString().replace("UID:",""))==user.bid&&
										   Long.parseLong(editTextQQNumber.getText().toString())==user.qq&&
										   editTextName.getText().toString().equals(user.name)&&
										   Integer.parseInt(editTextBilibiliLiveRoom.getText().toString().replace("http://live.bilibili.com/","").replace("?share_source=copy_link",""))==user.bliveRoom){
											return;
										  }
									  }
									PersonInfo user = new PersonInfo();
									user.name=editTextName.getText().toString();
									user.qq=Long.parseLong(editTextQQNumber.getText().toString());
									user.bid=Integer.parseInt(editTextBilibiliId.getText().toString().replace("UID:",""));
									user.bliveRoom=Integer.parseInt(editTextBilibiliLiveRoom.getText().toString().replace("http://live.bilibili.com/","").replace("?share_source=copy_link",""));
									configJavaBean.personInfo.add(user);
									loadConfigData(gson.toJson(configJavaBean));
								  }
							  }).setNegativeButton("取消",null).show();
						}
					}).setNegativeButton("取消",null).show();
			  }else{
                final EditText editText = new EditText(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this)
				  .setView(editText)
				  .setTitle("编辑")
				  .setPositiveButton("确定",new DialogInterface.OnClickListener() {
					  @Override
					  public void onClick(DialogInterface p11,int p2){

						  new AlertDialog.Builder(MainActivity.this)
							.setTitle("确定修改吗")
							.setPositiveButton("确定",new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface p11,int p2){

									switch(tabHost.getCurrentTab()){
										case 0:
										  for(GroupConfig g : configJavaBean.groupConfigs){
											  if(g.groupNumber==Long.parseLong(editText.getText().toString())){
												  return;
												}
											}
										  GroupConfig groupReply = new GroupConfig();
										  groupReply.groupNumber=Long.parseLong(editText.getText().toString());                                                
										  configJavaBean.groupConfigs.add(groupReply);
										  break;
										case 1:
										  for(long l : configJavaBean.QQNotReply){
											  if(l==Long.parseLong(editText.getText().toString())){
												  return;
												}
											}
										  configJavaBean.QQNotReply.add(Long.parseLong(editText.getText().toString()));
										  break;
										case 2:
										  for(String l : configJavaBean.wordNotReply){
											  if(l.equals(editText.getText().toString())){
												  return;
												}
											}
										  configJavaBean.wordNotReply.add(editText.getText().toString());
										  break;                                             
									  }
									loadConfigData(gson.toJson(configJavaBean));
								  }
							  }).setNegativeButton("取消",null).show();
						}
					}).setNegativeButton("取消",null).show();
			  }
		  }else if(item.getTitle().equals("从服务器获取信息")){
			//       getJsonString();
		  }else if(item.getTitle().equals("提交修改到服务器")){
			//       setJsonString(gson.toJson(configJavaBean));
		  }
        return true;
	  }

    private void loadConfigData(String s){
        configJavaBean=gson.fromJson(s,ConfigJavaBean.class);
        listViewGroupReply.setAdapter(new GroupReplyListAdapter(this,configJavaBean.groupConfigs));
        listViewQQNotReply.setAdapter(new QQNotReplyAdapter(this,configJavaBean.QQNotReply));
        listViewWordNotReply.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,new ArrayList<>(configJavaBean.wordNotReply)));
		listViewPersonInfo.setAdapter(new PersonInfoAdapter(this,configJavaBean.personInfo));
	  }
	/*
	 public void getJsonString(){

	 new Thread(new Runnable() {
	 @Override
	 public void run(){
	 try{
	 Socket client = new Socket(IP,PORT);
	 OutputStream out = client.getOutputStream();
	 DataOutputStream dos = new DataOutputStream(out);
	 dos.writeUTF("get");
	 InputStream in = client.getInputStream();
	 DataInputStream dis = new DataInputStream(in);
	 final String result = dis.readUTF();
	 runOnUiThread(new Runnable() {

	 @Override
	 public void run(){
	 //	Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
	 loadConfigData(result);
	 }
	 });
	 client.close();
	 }catch(UnknownHostException e){
	 e.printStackTrace();
	 }catch(IOException e){
	 e.printStackTrace();
	 }catch(Exception e){
	 }
	 }
	 }).start();
	 }

	 public void setJsonString(final String jsonString){

	 new Thread(new Runnable() {
	 @Override
	 public void run(){
	 try{
	 Socket client = new Socket(IP,PORT);
	 OutputStream out = client.getOutputStream();
	 DataOutputStream dos = new DataOutputStream(out);
	 dos.writeUTF("write"+gson.toJson(configJavaBean));
	 InputStream in = client.getInputStream();
	 DataInputStream dis = new DataInputStream(in);
	 final String result = dis.readUTF();
	 runOnUiThread(new Runnable() {

	 @Override
	 public void run(){
	 Toast.makeText(MainActivity.this,result.equals("ok")? "成功" :"失败",Toast.LENGTH_SHORT).show();
	 }
	 });
	 client.close();
	 }catch(UnknownHostException e){
	 e.printStackTrace();
	 }catch(IOException e){
	 e.printStackTrace();
	 }catch(Exception e){
	 }
	 }
	 });
	 }
	 */
	private void loadPersonInfoData(){
		File f=new File(Environment.getExternalStorageDirectory()+"/grzxv2.json");
		if(!f.exists()){
			configJavaBean=new ConfigJavaBean();
			configJavaBean.groupConfigs.add(new GroupConfig());
			saveTxt(gson.toJson(configJavaBean));
		  }
		loadConfigData(loadFromSDFile(f)) ;
	  }
	private String loadFromSDFile(File f){
		String result = null;
		try{
			int length = (int) f.length();
			byte[] buff = new byte[length];
			FileInputStream fin = new FileInputStream(f);
			fin.read(buff);

			fin.close();
			result=new String(buff,"UTF-8");
		  }catch(Exception e){
			e.printStackTrace();
		  }
		return result;
	  }

	public void saveTxt(String str){
		try{
			FileWriter fw = new FileWriter(Environment.getExternalStorageDirectory()+"/grzxv2.json");//SD卡中的路径
			fw.flush();
			fw.write(str);
			fw.close();
		  }catch(Exception e){
			e.printStackTrace();
		  }
	  }

  }
	
