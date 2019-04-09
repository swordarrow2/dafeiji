package com.meng.grzx;
import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class DicEdit extends Activity{
	public final String IP = "123.207.65.93";// 服务器地址
	public final int PORT = 9961;// 服务器端口号
	public long groupNum;
	public ActionBar actionBar;
	public ListView listview;
	public JsonObject jsonObject;
	public Gson gson=new Gson();
	public JsonParser parser=new JsonParser();

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		groupNum=intent.getLongExtra("group",0l);
		if(groupNum==0){
			finish();
		  }
		setContentView(R.layout.dic_edit_main);
		actionBar=getActionBar();
		actionBar.show();
		listview=(ListView) findViewById(R.id.dic_editListView);
		getJsonString();
	  }
	  
	private void getJsonString(){

        new Thread(new Runnable() {
			  @Override
			  public void run(){
				  try{				  
					  Socket client=new Socket(IP,PORT);				 
					  OutputStream out = client.getOutputStream();					  
					  DataOutputStream dos = new DataOutputStream(out);				  
					  dos.writeUTF("get"+groupNum+".");
					  InputStream in = client.getInputStream();
					  DataInputStream dis = new DataInputStream(in);
					  final String result=dis.readUTF();
					  runOnUiThread(new Runnable(){

							@Override
							public void run(){		
								Toast.makeText(DicEdit.this,result,Toast.LENGTH_SHORT).show();
								loadConfigData(result);							  
							  }
						  });			
						  client.close();
					}catch(UnknownHostException e){
					  e.printStackTrace();
					}catch(IOException e){
					  e.printStackTrace();
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
				  try{				  
					  Socket client=new Socket(IP,PORT);				 
					  OutputStream out = client.getOutputStream();					  
					  DataOutputStream dos = new DataOutputStream(out);				  
					  dos.writeUTF("write"+groupNum+"."+gson.toJson(jsonObject));
					  InputStream in = client.getInputStream();
					  DataInputStream dis = new DataInputStream(in);
					  final String result=dis.readUTF();
					  runOnUiThread(new Runnable(){

							@Override
							public void run(){		
								Toast.makeText(DicEdit.this,result.equals("ok")?"成功":"失败",Toast.LENGTH_SHORT).show();							  
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

	private void loadConfigData(String jsonString){

		jsonObject=parser.parse(jsonString).getAsJsonObject();// 谷歌的GSON对象
		ArrayList<String> list = new ArrayList<String>();
		for(Map.Entry me:jsonObject.entrySet()){
			String s=(String) me.getKey();
			list.add(s.replace("\"",""));
		  }
		listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));
		/*	it = obj.entrySet().iterator();
		 while (it.hasNext()) {// 遍历集合
		 Map.Entry entry = (Map.Entry) it.next();
		 if (Pattern.matches(".*" + (String) entry.getKey() + ".*", msg.replace(" ", "").trim())) { // 使用了正则表达式查找要进行的回复
		 JsonArray array = (JsonArray) entry.getValue(); // 根据词库特点，一个key对应一个数组
		 int arraySize = array.size();
		 if (arraySize != 0) {
		 int k = 0;
		 for (; k < arraySize; k++) {
		 // 读取出来的数据是带有引号的
		 // 将引号去掉并将对象放入Hashmap中
		 replyPool.put(k, Methods.removeCharAtStartAndEnd(array.get(k).toString()));
		 }
		 // 从所有的回答中随机选择一个
		 Autoreply.sendMessage(group, qq,
		 replyPool.get(Autoreply.instence.random.nextInt(2147483647) % k));
		 replyPool.clear();
		 return true;
		 }
		 }
		 }
		 */
	  }
  }
