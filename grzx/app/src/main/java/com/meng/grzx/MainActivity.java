package com.meng.grzx;

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
    public TabHost tabHost;
    public final String IP = "123.207.65.93";
    public final int PORT = 9760;
    public Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getActionBar().show();
		
		File f = new File(Environment.getExternalStorageDirectory()+"/Pictures/grzx/group/");
		if(!f.exists()){
			f.mkdirs();
		  }
		File f2 = new File(Environment.getExternalStorageDirectory()+"/Pictures/grzx/user/");
		if(!f2.exists()){
			f2.mkdirs();
		  }
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
									  GroupReply groupReply = new GroupReply();
									  groupReply.groupNum=Long.parseLong(editText.getText().toString());
									  groupReply.reply=true;
									  configJavaBean.mapGroupReply.set(i,groupReply);
									  loadConfigData(gson.toJson(configJavaBean));
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
									  loadConfigData(gson.toJson(configJavaBean));
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
									  loadConfigData(gson.toJson(configJavaBean));
									}
								}).setNegativeButton("取消",null).show();
						  }
					  }).setNegativeButton("取消",null).show();
				}
			});

        listViewGroupDicReply.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> adapterView,View view,final int i,long l){
				  Intent in = new Intent(MainActivity.this,DicEdit.class);
				  in.putExtra("group",configJavaBean.mapGroupDicReply.get(i));
				  startActivity(in);
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

				  final BilibiliUser mapBiliUser = configJavaBean.mapBiliUser.get(p3);
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
							configJavaBean.mapGroupReply.remove(p3);
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
							configJavaBean.mapQQNotReply.remove(p3);
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
							configJavaBean.mapWordNotReply.remove(p3);
							loadConfigData(gson.toJson(configJavaBean));
						  }
					  }).setNegativeButton("取消",null).show();
				  return true;
				}
			});

        listViewGroupRepeater.setOnItemLongClickListener(new OnItemLongClickListener() {

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){

				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.mapGroupRepeater.remove(p3);
							loadConfigData(gson.toJson(configJavaBean));
						  }
					  }).setNegativeButton("取消",null).show();
				  return true;
				}
			});
        listViewGroupDicReply.setOnItemLongClickListener(new OnItemLongClickListener() {

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){

				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.mapGroupDicReply.remove(p3);
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
							configJavaBean.mapBiliUser.remove(p3);
							loadConfigData(gson.toJson(configJavaBean));
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

				  final BilibiliUser mapBiliUser = configJavaBean.mapBiliUser.get(p3);
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
        listViewPersonInfo.setOnItemLongClickListener(new OnItemLongClickListener() {

			  @Override
			  public boolean onItemLongClick(AdapterView<?> p1,View p2,final int p3,long p4){
				  new AlertDialog.Builder(MainActivity.this)
					.setTitle("确定删除吗")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){
							configJavaBean.mapBiliUser.remove(p3);
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
									for(BilibiliUser user : configJavaBean.mapBiliUser){
										if(Long.parseLong(editTextBilibiliId.getText().toString())==user.bid&&
										   Long.parseLong(editTextQQNumber.getText().toString())==user.qq&&
										   editTextName.getText().toString().equals(user.name)&&
										   Integer.parseInt(editTextBilibiliLiveRoom.getText().toString().replace("http://live.bilibili.com/","").replace("?share_source=copy_link",""))==user.bliveRoom){
											return;
										  }
									  }
									BilibiliUser user = new BilibiliUser();
									user.name=editTextName.getText().toString();
									user.qq=Long.parseLong(editTextQQNumber.getText().toString());
									user.bid=Integer.parseInt(editTextBilibiliId.getText().toString().replace("UID:",""));
									user.bliveRoom=Integer.parseInt(editTextBilibiliLiveRoom.getText().toString().replace("http://live.bilibili.com/","").replace("?share_source=copy_link",""));
									configJavaBean.mapBiliUser.add(user);
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
										  for(GroupReply g : configJavaBean.mapGroupReply){
											  if(g.groupNum==Long.parseLong(editText.getText().toString())){
												  return;
												}
											}
										  GroupReply groupReply = new GroupReply();
										  groupReply.groupNum=Long.parseLong(editText.getText().toString());
										  groupReply.reply=true;
										  configJavaBean.mapGroupReply.add(groupReply);
										  break;
										case 1:
										  for(long l : configJavaBean.mapQQNotReply){
											  if(l==Long.parseLong(editText.getText().toString())){
												  return;
												}
											}
										  configJavaBean.mapQQNotReply.add(Long.parseLong(editText.getText().toString()));
										  break;
										case 2:
										  for(String l : configJavaBean.mapWordNotReply){
											  if(l.equals(editText.getText().toString())){
												  return;
												}
											}
										  configJavaBean.mapWordNotReply.add(editText.getText().toString());
										  break;
										case 3:
										  for(String l : configJavaBean.mapGroupRepeater){
											  if(l.equals(editText.getText().toString())){
												  return;
												}
											}
										  configJavaBean.mapGroupRepeater.add(editText.getText().toString());
										  break;
										case 4:
										  for(long l : configJavaBean.mapGroupDicReply){
											  if(l==Long.parseLong(editText.getText().toString())){
												  return;
												}
											}
										  configJavaBean.mapGroupDicReply.add(Long.parseLong(editText.getText().toString()));
										  break;
									  }
									loadConfigData(gson.toJson(configJavaBean));
								  }
							  }).setNegativeButton("取消",null).show();
						}
					}).setNegativeButton("取消",null).show();
			  }
		  }else if(item.getTitle().equals("从服务器获取信息")){
            getJsonString();
		  }else if(item.getTitle().equals("提交修改到服务器")){
            setJsonString(gson.toJson(configJavaBean));
		  }
        return true;
	  }

    private void loadConfigData(String s){
        configJavaBean=gson.fromJson(s,ConfigJavaBean.class);

        listViewGroupReply.setAdapter(new GroupListAdapter(this,configJavaBean.mapGroupReply));

        listViewQQNotReply.setAdapter(new QQNotReplayAdapter(this,configJavaBean.mapQQNotReply));

        listViewWordNotReply.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>(configJavaBean.mapWordNotReply)));

        listViewGroupRepeater.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>(configJavaBean.mapGroupRepeater)));

        ArrayList<String> list5 = new ArrayList<String>();
        for(long l : configJavaBean.mapGroupDicReply){
            list5.add(String.valueOf(l));
		  }
        listViewGroupDicReply.setAdapter(new GroupDicListAdapter(this,configJavaBean.mapGroupDicReply));

        ArrayList<String> list6 = new ArrayList<String>();
        for(BilibiliUser mapBiliUser : configJavaBean.mapBiliUser){
            list6.add(mapBiliUser.name+"  "+mapBiliUser.qq+"  "+mapBiliUser.bid+"  "+mapBiliUser.bliveRoom);
		  }
        listViewPersonInfo.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list6));

	  }

    private void getJsonString(){

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

    private void setJsonString(final String jsonString){

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
			}).start();
	  }

    /* private void loadPersonInfoData() {
	 personInfoJavaBean = gson.fromJson(loadFromSDFile(new File(Environment.getExternalStorageDirectory() + "/grzx.json")), PersonInfoJavaBean.class);
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
	
