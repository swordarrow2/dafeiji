package com.meng.grzx.adapters;

import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.google.gson.*;
import com.meng.grzx.*;
import com.meng.grzx.javaBean.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class GroupRepeaterListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<GroupRepeater> groupRepeaters;

    public GroupRepeaterListAdapter(Context context,ArrayList<GroupRepeater> groupRepeaters){
        this.context=context;
        this.groupRepeaters=groupRepeaters;
	  }

    public int getCount(){
        return groupRepeaters.size();
	  }

    public Object getItem(int position){
        return null;
	  }

    public long getItemId(int position){
        return 0;
	  }

    public View getView(final int position,View convertView,ViewGroup parent){

        ViewHolder holder;
        if(convertView==null){
            convertView=((MainActivity) context).getLayoutInflater().inflate(R.layout.list_item_image_text_switch,null);
            holder=new ViewHolder();
            holder.groupNumber=(TextView) convertView.findViewById(R.id.group_reply_list_itemTextView);
            holder.imageView=(ImageView) convertView.findViewById(R.id.group_reply_list_itemImageView);
            holder.replySwitch=(Switch) convertView.findViewById(R.id.group_reply_list_itemSwitch);
            holder.replySwitch.setTextOff("不复读");
            holder.replySwitch.setTextOn("复读");			
            convertView.setTag(holder);
		  }else{
            holder=(ViewHolder) convertView.getTag();
		  }
        final GroupRepeater repeater = groupRepeaters.get(position);
		holder.replySwitch.setChecked(repeater.reply);
        holder.groupNumber.setText(String.valueOf(repeater.groupNumber));
        holder.replySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			  @Override
			  public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
				  repeater.reply=isChecked;
				  ((MainActivity)context).setJsonString(new Gson().toJson(((MainActivity)context).configJavaBean));
				}
			});
        File imageFile = new File(Environment.getExternalStorageDirectory()+"/Pictures/grzx/group/"+repeater.groupNumber+".jpg");
        if(imageFile.exists()){
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
		  }else{
            new DownloadImageThread(holder.imageView,repeater.groupNumber).start();
		  }
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
        private ImageView imageView;
        private String imageUrl = "";
        private File imageFile;

        public DownloadImageThread(ImageView imageView,long groupNumber){
            this.imageView=imageView;
            imageUrl="http://p.qlogo.cn/gh/"+groupNumber+"/"+groupNumber+"/100/";
            imageFile=new File(Environment.getExternalStorageDirectory()+"/Pictures/grzx/group/"+groupNumber+".jpg");
		  }

        @Override
        public void run(){
            downloadFile(imageUrl);
		  }

        private void downloadFile(String url){
            try{
                URL u = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");
                InputStream is = connection.getInputStream();
                FileOutputStream fos = new FileOutputStream(imageFile);
                byte buf[] = new byte[4096];
                int len = 0;
                while((len=is.read(buf))>0){
                    fos.write(buf,0,len);
				  }
                is.close();
                connection.disconnect();
                ((MainActivity) context).runOnUiThread(new Runnable() {

					  @Override
					  public void run(){
						  imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
						}
					});
			  }catch(IOException e){
                e.printStackTrace();
			  }
		  }
	  }
  }
