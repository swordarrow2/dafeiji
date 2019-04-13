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

public class GroupRepeaterListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<GroupRepeater> groupRepeaters;

    public GroupRepeaterListAdapter(Context context, ArrayList<GroupRepeater> groupRepeaters) {
        this.context = context;
        this.groupRepeaters = groupRepeaters;
    }

    public int getCount() {
        return groupRepeaters.size();
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
            holder.imageView = (ImageView) convertView.findViewById(R.id.group_reply_list_itemImageView);
            holder.replySwitch = (Switch) convertView.findViewById(R.id.group_reply_list_itemSwitch);
            holder.replySwitch.setTextOff("不复读");
            holder.replySwitch.setTextOn("复读");
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GroupRepeater repeater = groupRepeaters.get(position);

        holder.groupNumber.setText(String.valueOf(repeater.groupNumber));
        holder.replySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                repeater.reply = isChecked;
            }
        });

        holder.replySwitch.setChecked(repeater.reply);

        File imageFile = new File(MainActivity.mainDic + "group/" + repeater.groupNumber + ".jpg");
        if (imageFile.exists()) {
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        } else {
            if (MainActivity.onWifi) {
                new DownloadImageThread(context, holder.imageView, repeater.groupNumber, HeadType.QQGroup).start();
            } else {
                holder.imageView.setImageResource(R.drawable.stat_sys_download_anim0);
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DownloadImageThread(context, holder.imageView, repeater.groupNumber, HeadType.QQGroup).start();
                    }
                });
            }
        }
        return convertView;
    }

    private final class ViewHolder {
        private ImageView imageView;
        private TextView groupNumber;
        private Switch replySwitch;
    }
}
