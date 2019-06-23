package com.meng.grzxConfig.MaterialDesign.adapters;

import android.graphics.*;
import android.view.*;
import android.widget.*;
import android.widget.CompoundButton.*;

import com.meng.grzxConfig.MaterialDesign.R;
import com.meng.grzxConfig.MaterialDesign.helpers.DownloadImageRunnable;
import com.meng.grzxConfig.MaterialDesign.helpers.HeadType;
import com.meng.grzxConfig.MaterialDesign.helpers.NetworkType;
import com.meng.grzxConfig.MaterialDesign.javaBean.*;

import java.io.*;
import java.util.*;

import com.meng.grzxConfig.MaterialDesign.activity.MainActivity;

import android.app.*;

public class GroupConfigAdapter extends BaseAdapter {
    private Activity context;
    private ArrayList<GroupConfig> groupReplies;
    // private HashMap<Long, Bitmap> hashMap = new HashMap<>();

    public GroupConfigAdapter(Activity context, HashSet<GroupConfig> groupReplies) {
        this.context = context;
        ArrayList<GroupConfig> arrayList = new ArrayList<>(groupReplies);
        quickSort(arrayList, 0, arrayList.size() - 1);
        this.groupReplies = arrayList;
    }

    private void quickSort(ArrayList<GroupConfig> array, int low, int high) {// 传入low=0，high=array.length-1;
        long pivot;
        GroupConfig t;
        int p_pos, i;// pivot->位索引;p_pos->轴值。
        if (low < high) {
            p_pos = low;
            pivot = array.get(p_pos).groupNumber;
            for (i = low + 1; i <= high; i++)
                if (array.get(i).groupNumber > pivot) {
                    p_pos++;
                    t = array.get(p_pos);
                    array.set(p_pos, array.get(i));
                    array.set(i, t);
                }
            t = array.get(low);
            array.set(low, array.get(p_pos));
            array.set(p_pos, t);
            // 分而治之
            quickSort(array, low, p_pos - 1);// 排序左半部分
            quickSort(array, p_pos + 1, high);// 排序右半部分
        }
    }

    public int getCount() {
        return groupReplies.size();
    }

    public Object getItem(int position) {
        return groupReplies.get(position);
    }

    public long getItemId(int position) {
        return groupReplies.get(position).hashCode();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.list_item_image_text_switch, null);
            holder = new ViewHolder();
            holder.groupNumber = (TextView) convertView.findViewById(R.id.group_reply_list_itemTextView);
            holder.replySwitch = (Switch) convertView.findViewById(R.id.group_reply_list_itemSwitch);
            holder.imageView = (ImageView) convertView.findViewById(R.id.group_reply_list_itemImageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GroupConfig groupReply = groupReplies.get(position);
        holder.groupNumber.setText(String.valueOf(groupReply.groupNumber));
        holder.replySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton p1, boolean p2) {
                if (groupReply.reply != p2) {
                    groupReply.reply = p2;
                    ((MainActivity) context).networkManager.send(NetworkType.setGroup, MainActivity.instence.gson.toJson(groupReply), GroupConfigAdapter.this);
                }
            }
        });

        holder.replySwitch.setChecked(groupReply.reply);

        //    if (hashMap.get(groupReply.groupNumber) == null) {
        File imageFile = new File(MainActivity.instence.mainDic + "group/" + groupReply.groupNumber + ".jpg");
        if (imageFile.exists()) {
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        } else {
            if (MainActivity.instence.onWifi) {
                MainActivity.instence.threadPool.execute(new DownloadImageRunnable(context, holder.imageView, groupReply.groupNumber, HeadType.QQGroup));
            } else {
                holder.imageView.setImageResource(R.drawable.stat_sys_download_anim0);
            }
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instence.threadPool.execute(new DownloadImageRunnable(context, holder.imageView, groupReply.groupNumber, HeadType.QQGroup));
            }
        });
        //     }
        //      holder.imageView.setImageBitmap(hashMap.get(groupReply.groupNumber));
        return convertView;
    }

    private class ViewHolder {
        private ImageView imageView;
        private TextView groupNumber;
        private Switch replySwitch;
    }
}
