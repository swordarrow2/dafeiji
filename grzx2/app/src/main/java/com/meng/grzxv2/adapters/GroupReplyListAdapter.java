package com.meng.grzxv2.adapters;

import android.content.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;
import android.widget.CompoundButton.*;
import com.meng.grzxv2.*;
import com.meng.grzxv2.javaBean.*;
import java.io.*;
import java.util.*;

public class GroupReplyListAdapter extends BaseAdapter{
    private MainActivity context;
    private ArrayList<GroupConfig> groupReplies;

    public GroupReplyListAdapter(MainActivity context,ArrayList<GroupConfig> groupReplies){
        this.context=context;
        this.groupReplies=groupReplies;
	  }

    public int getCount(){
        return groupReplies.size();
	  }

    public Object getItem(int position){
        return null;
	  }

    public long getItemId(int position){
        return 0;
	  }

    public View getView(final int position,View convertView,ViewGroup parent){

        final ViewHolder holder;
        if(convertView==null){
            convertView=context.getLayoutInflater().inflate(R.layout.list_item_image_text_switch,null);
            holder=new ViewHolder();
            holder.groupNumber=(TextView) convertView.findViewById(R.id.group_reply_list_itemTextView);
            holder.replySwitch=(Switch) convertView.findViewById(R.id.group_reply_list_itemSwitch);
            holder.imageView=(ImageView) convertView.findViewById(R.id.group_reply_list_itemImageView);
            convertView.setTag(holder);
		  }else{
            holder=(ViewHolder) convertView.getTag();
		  }
        final GroupConfig groupReply = groupReplies.get(position);
        holder.groupNumber.setText(String.valueOf(groupReply.groupNumber));
        holder.replySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			  @Override
			  public void onCheckedChanged(CompoundButton p1,boolean p2){
				  if(groupReply.reply!=p2){
					  groupReply.reply=p2;
					  context.networkManager.send(NetworkType.setGroup,position+" "+context.gson.toJson(groupReply));
					}
				}
			});

        holder.replySwitch.setChecked(groupReply.reply);

        File imageFile = new File(MainActivity.mainDic+"group/"+groupReply.groupNumber+".jpg");
        if(imageFile.exists()){
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
		  }else{
            if(MainActivity.onWifi){
                new DownloadImageThread(context,holder.imageView,groupReply.groupNumber,HeadType.QQGroup).start();
			  }else{
                holder.imageView.setImageResource(R.drawable.stat_sys_download_anim0);
                holder.imageView.setOnClickListener(new View.OnClickListener() {
					  @Override
					  public void onClick(View v){
						  new DownloadImageThread(context,holder.imageView,groupReply.groupNumber,HeadType.QQGroup).start();
						}
					});
			  }
		  }
        return convertView;
	  }

    private class ViewHolder{
        private ImageView imageView;
        private TextView groupNumber;
        private Switch replySwitch;
	  }
  }
