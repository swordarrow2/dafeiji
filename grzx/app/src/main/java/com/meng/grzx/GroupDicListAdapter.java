package com.meng.grzx;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import android.widget.CompoundButton.*;
import com.google.gson.*;
import com.meng.grzx.*;
import java.io.*;
import java.net.*;
import java.util.*;
import android.os.*;
import android.graphics.*;

public class GroupDicListAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<Long> musicInfoBeans;
	private LayoutInflater inflater;
	private ViewHolder holder;

	public GroupDicListAdapter(Context context,ArrayList<Long> musicInfoBeans){
		this.context=context;
		this.musicInfoBeans=musicInfoBeans;
		inflater=LayoutInflater.from(context);
	  }	

	public int getCount(){
		return musicInfoBeans.size();
	  }
	public Object getItem(int position){
		return null;
	  }
	public long getItemId(int position){
		return 0;
	  }

	public View getView(final int position,View convertView,ViewGroup parent){

		if(convertView==null){
			convertView=inflater.inflate(R.layout.group_reply_list_item,null);
			holder=new ViewHolder();
			holder.groupNumber=(TextView) convertView.findViewById(R.id.group_reply_list_itemTextView);		
			holder.imageView=(ImageView)convertView.findViewById(R.id.group_reply_list_itemImageView);		
			holder.replySwitch=(Switch) convertView.findViewById(R.id.group_reply_list_itemSwitch);
			holder.replySwitch.setEnabled(false);
			holder.replySwitch.setChecked(true);
			convertView.setTag(holder);
		  }else{
			holder=(ViewHolder) convertView.getTag();
		  }
		long groupReply=musicInfoBeans.get(position);
		holder.groupNumber.setText(String.valueOf(groupReply));
		new DownloadImageThread(holder.imageView,groupReply).start();
		return convertView;
	  }
	//这个内部类主要为了ListView加载的一个性能优化
	//View的findViewById()方法也是比较耗时的，因此需要考虑只调用一次，
	//之后就用View.getTag()方法来获得ViewHolder对象
	//有时候如果没有必要，我们就没有必要这样去做，可以直接在上面定义
	private final class ViewHolder{
		private ImageView imageView;
		private TextView groupNumber;
		private Switch replySwitch;
	  }
	

	class DownloadImageThread extends Thread{
		private long groupNumber = 0;
		ImageView imagev;
		private String imageUrl = "";

		public DownloadImageThread(ImageView mengp,long pixivId){
			imagev=mengp;
			groupNumber=pixivId;
			imageUrl="http://p.qlogo.cn/gh/"+groupNumber+"/"+groupNumber+"/100/";
		  }

		@Override
		public void run(){
			downloadFile(imageUrl);
		  }

		private void downloadFile(String url){
			try{
				final String expandName = ".jpg";
				final String fileName = String.valueOf(groupNumber);

				final File file = new File(Environment.getExternalStorageDirectory()+"/Pictures/grzx/group/"+fileName+expandName);
				if(!file.exists()){
					URL u = new URL(url);
					HttpURLConnection connection = (HttpURLConnection) u.openConnection();
					connection.setRequestMethod("GET");
					connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");
					InputStream is = connection.getInputStream();
					FileOutputStream fos = new FileOutputStream(file);
					byte buf[] = new byte[4096];
					int len = 0;
					while((len=is.read(buf))>0){
						fos.write(buf,0,len);
					  }			
					is.close();
					connection.disconnect();
				  }
				((MainActivity)context).runOnUiThread(new Runnable(){

					  @Override
					  public void run(){
						  imagev.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
						}
					});	
			  }catch(IOException e){
				e.printStackTrace();
			  }
		  }	
	  }
  }
