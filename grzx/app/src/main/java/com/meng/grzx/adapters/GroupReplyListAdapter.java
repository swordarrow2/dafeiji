package com.meng.grzx.adapters;

import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.CompoundButton.*;

import com.google.gson.*;
import com.meng.grzx.*;
import com.meng.grzx.javaBean.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class GroupReplyListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<GroupReply> groupReplies;

    public GroupReplyListAdapter(Context context, ArrayList<GroupReply> groupReplies) {
        this.context = context;
        this.groupReplies = groupReplies;
    }

    public int getCount() {
        return groupReplies.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = ((MainActivity) context).getLayoutInflater().inflate(R.layout.list_item_image_text_switch, null);
            holder = new ViewHolder();
            holder.groupNumber = (TextView) convertView.findViewById(R.id.group_reply_list_itemTextView);
            holder.replySwitch = (Switch) convertView.findViewById(R.id.group_reply_list_itemSwitch);
            holder.imageView = (ImageView) convertView.findViewById(R.id.group_reply_list_itemImageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GroupReply groupReply = groupReplies.get(position);
        holder.groupNumber.setText(String.valueOf(groupReply.groupNum));

        holder.replySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton p1, boolean p2) {
                groupReply.reply = p2;
            }
        });

        holder.replySwitch.setChecked(groupReply.reply);

        File imageFile = new File(MainActivity.mainDic + "group/" + groupReply.groupNum + ".jpg");
        if (imageFile.exists()) {
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        } else {
            if (MainActivity.onWifi) {
                new DownloadImageThread(context, holder.imageView, groupReply.groupNum, HeadType.QQGroup).start();
            } else {
                holder.imageView.setImageResource(R.drawable.stat_sys_download_anim0);
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DownloadImageThread(context, holder.imageView, groupReply.groupNum, HeadType.QQGroup).start();
                    }
                });
            }
        }
        return convertView;
    }

    private class ViewHolder {
        private ImageView imageView;
        private TextView groupNumber;
        private Switch replySwitch;
    }
}
