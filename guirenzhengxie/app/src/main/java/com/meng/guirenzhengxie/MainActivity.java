package com.meng.guirenzhengxie;

import Decoder.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class MainActivity extends Activity{
    public ListView listViewGroupReply, listViewQQNotReply, listViewWordNotReply,
	listViewGroupRepeater, listViewGroupDicReply, listViewPersonInfo;
    public EditText editTextName, editTextQQNumber, editTextBilibiliId, editTextBilibiliLiveRoom;
    public ConfigJavaBean configJavaBean;
    public ActionBar actionBar;
    public String msg = "";
    public TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        actionBar=getActionBar();
        actionBar.show();

        listViewGroupReply=(ListView) findViewById(R.id.mainListView_GroupReply);
        listViewQQNotReply=(ListView) findViewById(R.id.mainListView_QQNotReply);
        listViewWordNotReply=(ListView) findViewById(R.id.mainListView_WordNotReply);
        listViewGroupRepeater=(ListView) findViewById(R.id.mainListView_GroupRepeater);
        listViewGroupDicReply=(ListView) findViewById(R.id.mainListView_GroupDicReply);
        listViewPersonInfo=(ListView) findViewById(R.id.mainListView_PersonInfo);

        tabHost=(TabHost) findViewById(R.id.pixiv_download_main_tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("回复的群").setContent(R.id.mainListView_GroupReply));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("不回复的QQ").setContent(R.id.mainListView_QQNotReply));
        tabHost.addTab(tabHost.newTabSpec("three").setIndicator("不回复的字").setContent(R.id.mainListView_WordNotReply));
        tabHost.addTab(tabHost.newTabSpec("four").setIndicator("复读机").setContent(R.id.mainListView_GroupRepeater));
        tabHost.addTab(tabHost.newTabSpec("five").setIndicator("群组词库").setContent(R.id.mainListView_GroupDicReply));
        tabHost.addTab(tabHost.newTabSpec("six").setIndicator("账号").setContent(R.id.mainListView_PersonInfo));

		getJsonString();
		
		listViewGroupReply.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> adapterView,View view,final int i,long l){
				  final EditText editText = new EditText(MainActivity.this);
				  editText.setText(String.valueOf(configJavaBean.mapGroupReply.get(i)));
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
									  ConfigJavaBean.GroupReply groupReply= new ConfigJavaBean().new GroupReply();
									  groupReply.groupNum=Long.parseLong(editText.getText().toString());
									  groupReply.reply=true;
									  configJavaBean.mapGroupReply.set(i,groupReply);
									  setJsonString(new Gson().toJson(configJavaBean));
									}
								}).setNegativeButton("取消",null).show();
						  }
					  }).setNegativeButton("取消",null).show();
				}
			});
		listViewQQNotReply.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> adapterView,View view,final int i,long l){
				  final EditText editText = new EditText(MainActivity.this);
				  editText.setText(String.valueOf(configJavaBean.mapQQNotReply.get(i)));
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
									  configJavaBean.mapQQNotReply.set(i,Long.parseLong(editText.getText().toString()));
									  setJsonString(new Gson().toJson(configJavaBean));
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
				  editText.setText(String.valueOf(configJavaBean.mapWordNotReply.get(i)));
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
									  configJavaBean.mapWordNotReply.set(i,editText.getText().toString());
									  setJsonString(new Gson().toJson(configJavaBean));
									}
								}).setNegativeButton("取消",null).show();
						  }
					  }).setNegativeButton("取消",null).show();
				}
			});

		listViewGroupRepeater.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> adapterView,View view,final int i,long l){
				  final EditText editText = new EditText(MainActivity.this);
				  editText.setText(String.valueOf(configJavaBean.mapGroupRepeater.get(i)));
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
									  configJavaBean.mapGroupRepeater.set(i,editText.getText().toString());
									  setJsonString(new Gson().toJson(configJavaBean));
									}
								}).setNegativeButton("取消",null).show();
						  }
					  }).setNegativeButton("取消",null).show();
				}
			});

		listViewGroupDicReply.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> adapterView,View view,final int i,long l){
				  final EditText editText = new EditText(MainActivity.this);
				  editText.setText(String.valueOf(configJavaBean.mapGroupDicReply.get(i)));
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
									  configJavaBean.mapGroupDicReply.set(i,Long.parseLong(editText.getText().toString()));
									  setJsonString(new Gson().toJson(configJavaBean));
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

				  final ConfigJavaBean.BilibiliUser mapBiliUser = configJavaBean.mapBiliUser.get(p3);
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
									  setJsonString(new Gson().toJson(configJavaBean));
									}
								}).setNegativeButton("取消",null).show();
						  }
					  }).setNegativeButton("取消",null).show();
				}
			});
		listViewGroupReply.setOnItemLongClickListener(new OnItemLongClickListener(){

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){

				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.mapGroupReply.remove(p3);
							setJsonString(new Gson().toJson(configJavaBean));
						  }
					  }).setNegativeButton("取消",null).show();
				  return true;
				}
			});
		listViewQQNotReply.setOnItemLongClickListener(new OnItemLongClickListener(){

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){

				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.mapQQNotReply.remove(p3);
							setJsonString(new Gson().toJson(configJavaBean));
						  }
					  }).setNegativeButton("取消",null).show();
				  return true;
				}
			});
		listViewWordNotReply.setOnItemLongClickListener(new OnItemLongClickListener(){

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){

				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.mapWordNotReply.remove(p3);
							setJsonString(new Gson().toJson(configJavaBean));
						  }
					  }).setNegativeButton("取消",null).show();
				  return true;
				}
			});

		listViewGroupRepeater.setOnItemLongClickListener(new OnItemLongClickListener(){

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){

				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.mapGroupRepeater.remove(p3);
							setJsonString(new Gson().toJson(configJavaBean));
						  }
					  }).setNegativeButton("取消",null).show();
				  return true;
				}
			});
		listViewGroupDicReply.setOnItemLongClickListener(new OnItemLongClickListener(){

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){

				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.mapGroupDicReply.remove(p3);
							setJsonString(new Gson().toJson(configJavaBean));
						  }
					  }).setNegativeButton("取消",null).show();
				  return true;
				}
			});

		listViewPersonInfo.setOnItemLongClickListener(new OnItemLongClickListener(){

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){	  
				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.mapBiliUser.remove(p3);
							setJsonString(new Gson().toJson(configJavaBean));
						  }
					  }).setNegativeButton("取消",null).show();
				  return true;	
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

				  final ConfigJavaBean.BilibiliUser mapBiliUser = configJavaBean.mapBiliUser.get(p3);
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
									  setJsonString(new Gson().toJson(configJavaBean));
									}
								}).setNegativeButton("取消",null).show();
						  }
					  }).setNegativeButton("取消",null).show();
				}
			});
		listViewPersonInfo.setOnItemLongClickListener(new OnItemLongClickListener(){

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){	  
				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.mapBiliUser.remove(p3);
							setJsonString(new Gson().toJson(configJavaBean));
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
        add.setIcon(android.R.drawable.ic_menu_add);
        return true;
	  }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getTitle().equals("添加")){
            if(tabHost.getCurrentTabTag().equals("six")){
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
									ConfigJavaBean.BilibiliUser user = (new ConfigJavaBean()).new BilibiliUser();
									user.name=editTextName.getText().toString();
									user.qq=Long.parseLong(editTextQQNumber.getText().toString());
									user.bid=Integer.parseInt(editTextBilibiliId.getText().toString().replace("UID:",""));
									user.bliveRoom=Integer.parseInt(editTextBilibiliLiveRoom.getText().toString().replace("http://live.bilibili.com/","").replace("?share_source=copy_link",""));
									configJavaBean.mapBiliUser.add(user);
									setJsonString(new Gson().toJson(configJavaBean));
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
                                            ConfigJavaBean.GroupReply groupReply=new ConfigJavaBean().new GroupReply();
                                            groupReply.groupNum=Long.parseLong(editText.getText().toString());
                                            groupReply.reply=true;
                                            configJavaBean.mapGroupReply.add(groupReply);
										  break;
										case 1:
										  configJavaBean.mapQQNotReply.add(Long.parseLong(editText.getText().toString()));
										  break;
										case 2:
										  configJavaBean.mapWordNotReply.add(editText.getText().toString());
										  break;
										case 3:
										  configJavaBean.mapGroupRepeater.add(editText.getText().toString());
										  break;
										case 4:
										  configJavaBean.mapGroupDicReply.add(Long.parseLong(editText.getText().toString()));
										  break;
									  }
									setJsonString(new Gson().toJson(configJavaBean));
								  }
							  }).setNegativeButton("取消",null).show();
						}
					}).setNegativeButton("取消",null).show();
			  }
		  }
        return true;
	  }

    private void loadConfigData(String s){
        configJavaBean=new Gson().fromJson(s,ConfigJavaBean.class);

        ArrayList<String> list = new ArrayList<String>();
        for (ConfigJavaBean.GroupReply groupReply : configJavaBean.mapGroupReply) {
            list.add(String.valueOf(groupReply.groupNum));
        }
        listViewGroupReply.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));

        ArrayList<String> list2 = new ArrayList<String>();
        for(long l : configJavaBean.mapQQNotReply){
            list2.add(String.valueOf(l));
		  }
        listViewQQNotReply.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list2));

        ArrayList<String> list3 = new ArrayList<String>();
        for(String l : configJavaBean.mapWordNotReply){
            list3.add(l);
		  }
        listViewWordNotReply.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list3));

        ArrayList<String> list4 = new ArrayList<String>();
        for(String l : configJavaBean.mapGroupRepeater){
            list4.add(l);
		  }
        listViewGroupRepeater.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list4));

        ArrayList<String> list5 = new ArrayList<String>();
        for(long l : configJavaBean.mapGroupDicReply){
            list5.add(String.valueOf(l));
		  }
        listViewGroupDicReply.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list5));

        ArrayList<String> list6 = new ArrayList<String>();
        for(ConfigJavaBean.BilibiliUser mapBiliUser : configJavaBean.mapBiliUser){
            list6.add(mapBiliUser.name+"  "+mapBiliUser.qq+"  "+mapBiliUser.bid+"  "+mapBiliUser.bliveRoom);
		  }
        listViewPersonInfo.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list6));

	  }

    private void getJsonString(){

        new Thread(new Runnable() {
			  @Override
			  public void run(){
				  Socket socket;
				  try{
					  socket=new Socket("123.207.65.93",9961);
					  int bufferSize = 1024;
					  char[] buffer = new char[bufferSize];
					  StringBuilder out = new StringBuilder();
					  Reader in = new InputStreamReader(socket.getInputStream());
					  int rsz = 0;
					  while((rsz=in.read(buffer,0,buffer.length))>0){
						  out.append(buffer,0,rsz);
						}
					  msg=out.toString();
					  runOnUiThread(new Runnable() {
							@Override
							public void run(){
								BASE64Decoder d = new BASE64Decoder();
								try{
									loadConfigData(new String(d.decodeBuffer(msg),"utf-8"));
								  }catch(IOException e){
									e.printStackTrace();
								  }
							  }
						  });
					  socket.close();
					}catch(Exception e){
					  e.printStackTrace();
					}
				}
			}).start();
	  }

    private void setJsonString(final String jsonString){

        new Thread(new Runnable() {
			  @Override
			  public void run(){
				  Socket socket;
				  try{
					  socket=new Socket("123.207.65.93",9760);
					  OutputStream outputStream = socket.getOutputStream();
					  BASE64Encoder encoder = new BASE64Encoder();
					  outputStream.write(encoder.encodeBuffer(jsonString.getBytes()).getBytes());
					  socket.close();
					  Thread.sleep(500);
					  runOnUiThread(new Runnable() {
							@Override
							public void run(){
								getJsonString();
							  }
						  });
					}catch(Exception e){
					  e.printStackTrace();
					}
				}
			}).start();
	  }

    /* private void loadPersonInfoData() {
	 personInfoJavaBean = new Gson().fromJson(loadFromSDFile(new File(Environment.getExternalStorageDirectory() + "/grzx.json")), PersonInfoJavaBean.class);
	 ArrayList<String> list = new ArrayList<String>();
	 for (PersonInfoJavaBean.MapBiliUser mapBiliUser : personInfoJavaBean.mapBiliUser) {
	 list.add(mapBiliUser.name + "  " + mapBiliUser.qq + "  " + mapBiliUser.bid + "  " + mapBiliUser.bliveRoom);
	 }
	 listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
     }
	 private String loadFromSDFile(File f) {
	 String result = null;
	 try {
	 int length = (int) f.length();
	 byte[] buff = new byte[length];
	 FileInputStream fin = new FileInputStream(f);
	 fin.read(buff);

	 fin.close();
	 result = new String(buff, "UTF-8");
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 return result;
     }

     public void saveTxt(String str) {
	 try {
	 FileWriter fw = new FileWriter(Environment.getExternalStorageDirectory() + "/grzx.json");//SD卡中的路径
	 fw.flush();
	 fw.write(str);
	 fw.close();
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
     }
	 */
  }
	
