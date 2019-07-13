package com.meng.grzxConfig.MaterialDesign.adapters;

import android.app.Activity;
import android.content.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;

import com.meng.grzxConfig.MaterialDesign.R;
import com.meng.grzxConfig.MaterialDesign.activity.MainActivity;
import com.meng.grzxConfig.MaterialDesign.helpers.DownloadImageRunnable;
import com.meng.grzxConfig.MaterialDesign.helpers.HeadType;

import java.io.*;
import java.util.*;

public class QQAccountAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Long> qqList;
    private HashSet<Long> qqSet;
    private boolean isGroup = false;

    public QQAccountAdapter(Context context, HashSet<Long> qqSet) {
        this.context = context;
        this.qqSet = qqSet;
        ArrayList<Long> arrayList = new ArrayList<>(qqSet);
        quickSort(arrayList, 0, arrayList.size() - 1);
        qqList = arrayList;
    }

    public QQAccountAdapter(Context context, HashSet<Long> qqSet, boolean isGroup) {
        this.context = context;
        this.qqSet = qqSet;
        this.isGroup = isGroup;
        ArrayList<Long> arrayList = new ArrayList<>(qqSet);
        quickSort(arrayList, 0, arrayList.size() - 1);
        qqList = arrayList;
    }

    private void quickSort(ArrayList<Long> array, int low, int high) {// 传入low=0，high=array.length-1;
        long pivot;
        Long t;
        int p_pos, i;// pivot->位索引;p_pos->轴值。
        if (low < high) {
            p_pos = low;
            pivot = array.get(p_pos);
            for (i = low + 1; i <= high; i++)
                if (array.get(i) < pivot) {
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

    @Override
    public void notifyDataSetChanged() {
        ArrayList<Long> arrayList = new ArrayList<>(qqSet);
        quickSort(arrayList, 0, arrayList.size() - 1);
        qqList = arrayList;
        super.notifyDataSetChanged();
    }

    public int getCount() {
        return qqList.size();
    }

    public Object getItem(int position) {
        return qqList.get(position);
    }

    public long getItemId(int position) {
        return qqList.get(position).hashCode();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = ((Activity) context).getLayoutInflater().inflate(R.layout.list_item_image_text_switch, null);
            holder = new ViewHolder();
            holder.groupNumber = (TextView) convertView.findViewById(R.id.group_reply_list_itemTextView);
            holder.imageView = (ImageView) convertView.findViewById(R.id.group_reply_list_itemImageView);
            holder.replySwitch = (Switch) convertView.findViewById(R.id.group_reply_list_itemSwitch);
            holder.replySwitch.setEnabled(false);
            holder.replySwitch.setChecked(false);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final long qqNotReply = qqList.get(position);
        holder.groupNumber.setText(String.valueOf(qqNotReply));
        File imageFile = new File(MainActivity.instence.mainDic + (isGroup ? "group/" : "user/") + qqNotReply + ".jpg");
        if (imageFile.exists()) {
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        } else {
            if (MainActivity.instence.onWifi) {
                MainActivity.instence.threadPool.execute(new DownloadImageRunnable(context, holder.imageView, qqNotReply, isGroup ? HeadType.QQGroup : HeadType.QQUser));
            } else {
                holder.imageView.setImageResource(R.drawable.stat_sys_download_anim0);
            }
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instence.threadPool.execute(new DownloadImageRunnable(context, holder.imageView, qqNotReply, isGroup ? HeadType.QQGroup : HeadType.QQUser));
            }
        });

        return convertView;
    }

    private final class ViewHolder {
        private ImageView imageView;
        private TextView groupNumber;
        private Switch replySwitch;
    }
}
