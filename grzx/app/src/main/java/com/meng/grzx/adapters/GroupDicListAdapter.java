package com.meng.grzx.adapters;

import android.content.*;
import android.view.*;
import android.widget.*;

import java.io.*;
import java.net.*;
import java.util.*;

import android.os.*;
import android.graphics.*;

import com.meng.grzx.DownloadImageThread;
import com.meng.grzx.HeadType;
import com.meng.grzx.MainActivity;
import com.meng.grzx.R;

public class GroupDicListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Long> dicList;

    public GroupDicListAdapter(Context context, ArrayList<Long> dicList) {
        this.context = context;
        this.dicList = dicList;
    }

    public int getCount() {
        return dicList.size();
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
            holder.replySwitch.setEnabled(false);
            holder.replySwitch.setChecked(true);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final long dicGroup = dicList.get(position);
        holder.groupNumber.setText(String.valueOf(dicGroup));
        File imageFile = new File(MainActivity.mainDic + "group/" + dicGroup + ".jpg");
        if (imageFile.exists()) {
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        } else {
            if (MainActivity.onWifi) {
                new DownloadImageThread(context, holder.imageView, dicGroup, HeadType.QQGroup).start();
            } else {
                holder.imageView.setImageResource(R.drawable.stat_sys_download_anim0);
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DownloadImageThread(context, holder.imageView, dicGroup, HeadType.QQGroup).start();
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
