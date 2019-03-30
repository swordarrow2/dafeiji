package com.meng.guirenzhengxie;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import com.google.gson.*;
import java.io.*;
import java.util.*;

public class MainActivity extends Activity{
	public ListView listView;
	public View v;
	public EditText etname, etqq,etbid,etlive;
	public javabean jb;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		listView=(ListView)findViewById(R.id.mainListView);
		loadData();
		listView.setOnItemClickListener(new OnItemClickListener(){

			  @Override
			  public void onItemClick(final AdapterView<?> p1,View p2,final int p3,long p4){
				  v=getLayoutInflater().inflate(R.layout.edit_view,null);
				  etname=(EditText) v.findViewById(R.id.edit_viewEditText_name);
				  etqq=(EditText) v.findViewById(R.id.edit_viewEditText_qq);
				  etbid=(EditText) v.findViewById(R.id.edit_viewEditText_bid);
				  etlive=(EditText) v.findViewById(R.id.edit_viewEditText_b_live_room);

				  javabean.MapBiliUser jmb=jb.mapBiliUser.get(p3);
				  etname.setText(jmb.name);
				  etqq.setText(""+jmb.qq);
				  etbid.setText(""+jmb.bid);
				  etlive.setText(""+jmb.bliveRoom);

				  new AlertDialog.Builder(MainActivity.this)
					.setView(v)
					.setTitle("编辑")
					.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11,int p2){

							new AlertDialog.Builder(MainActivity.this)
							  .setTitle("确定修改吗")
							  .setPositiveButton("确定",new DialogInterface.OnClickListener() {
								  @Override
								  public void onClick(DialogInterface p11,int p2){
									  jb.mapBiliUser.get(p3).name=etname.getText().toString();
									  stringTxt(new Gson().toJson(jb));
									  loadData();
									}
								}).setNegativeButton("取消",null).show();
						  }
					  }).setNegativeButton("取消",null).show();
				}
			});


	  }

	private void loadData(){
		jb=new Gson().fromJson(loadFromSDFile(new File(Environment.getExternalStorageDirectory()+"/grzx.json")),javabean.class);
		ArrayList<String> list=new ArrayList<String>();
		for(javabean.MapBiliUser h:jb.mapBiliUser){
			list.add(h.name);
		  }
		listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));  
	  }

	private String loadFromSDFile(File f){
        String result=null;
        try{
            int length=(int)f.length();
            byte[] buff=new byte[length];
            FileInputStream fin=new FileInputStream(f);
            fin.read(buff);
            fin.close();
            result=new String(buff,"UTF-8");
		  }catch(Exception e){
            e.printStackTrace();
		  }
        return result;
	  }

	public void stringTxt(String str){
		try{
			FileWriter fw = new FileWriter(Environment.getExternalStorageDirectory()+"/grzx.json");//SD卡中的路径
			fw.flush();
			fw.write(str);
			fw.close();
		  }catch(Exception e){
			e.printStackTrace();
		  }
	  }

  }
