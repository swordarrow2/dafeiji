package com.meng.grzxv2;

import android.widget.*;
import com.meng.grzxv2.*;
import java.io.*;
import java.net.*;

public class NetworkManager{

	public final String IP = "123.207.65.93";
	public final int PORT = 9760;
	private MainActivity a;

	public NetworkManager(MainActivity m){
		a=m;
	  }

	public void send(NetworkType n,String text){

	  }
	  

	public void getJsonString(){
if(true){return;}
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
					  a. runOnUiThread(new Runnable() {

							@Override
							public void run(){
								//	Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
								a.	loadConfigData(result);
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
		if(true){return;}
		new Thread(new Runnable() {
			  @Override
			  public void run(){
				  try{
					  Socket client = new Socket(IP,PORT);
					  OutputStream out = client.getOutputStream();
					  DataOutputStream dos = new DataOutputStream(out);
					  dos.writeUTF("write"+a.gson.toJson(a.configJavaBean));
					  InputStream in = client.getInputStream();
					  DataInputStream dis = new DataInputStream(in);
					  final String result = dis.readUTF();
					  a.  runOnUiThread(new Runnable() {

							@Override
							public void run(){
								Toast.makeText(a,result.equals("ok")? "成功" :"失败",Toast.LENGTH_SHORT).show();
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





  }
