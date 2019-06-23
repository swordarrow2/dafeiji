package com.meng.grzxConfig.MaterialDesign.adapters;

import android.content.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;

import com.meng.grzxConfig.MaterialDesign.R;
import com.meng.grzxConfig.MaterialDesign.activity.InfoActivity;
import com.meng.grzxConfig.MaterialDesign.helpers.DownloadImageRunnable;
import com.meng.grzxConfig.MaterialDesign.helpers.HeadType;
import com.meng.grzxConfig.MaterialDesign.javaBean.*;

import java.io.*;
import java.util.*;

import com.meng.grzxConfig.MaterialDesign.activity.MainActivity;

public class PersonInfoAdapter extends BaseAdapter {
    private MainActivity context;
    private ArrayList<PersonInfo> infos;

    public PersonInfoAdapter(MainActivity context, HashSet<PersonInfo> infos) {
        this.context = context;
        ArrayList<PersonInfo> arrayList = new ArrayList<>(infos);
        quickSort(arrayList, 0, arrayList.size() - 1);
        this.infos = arrayList;
    }

    private void quickSort(ArrayList<PersonInfo> array, int low, int high) {// 传入low=0，high=array.length-1;
        long pivot;
        PersonInfo t;
        int p_pos, i;// pivot->位索引;p_pos->轴值。
        if (low < high) {
            p_pos = low;
            pivot = array.get(p_pos).qq;
            for (i = low + 1; i <= high; i++)
                if (array.get(i).qq > pivot) {
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
        return infos.size();
    }

    public Object getItem(int position) {
        return infos.get(position);
    }

    public long getItemId(int position) {
        return infos.get(position).hashCode();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.person_info_list_item, null);
            holder = new ViewHolder();
            holder.imageViewQQHead = (ImageView) convertView.findViewById(R.id.imageView_qqHead);
            holder.imageViewBilibiiliHead = (ImageView) convertView.findViewById(R.id.imageView_bilibiliHead);
            holder.infoImageView = (ImageView) convertView.findViewById(R.id.imageView_info);
            holder.textViewName = (TextView) convertView.findViewById(R.id.textView_name);
            holder.textViewQQNumber = (TextView) convertView.findViewById(R.id.textView_qqnum);
            holder.textViewBilibiliUid = (TextView) convertView.findViewById(R.id.textView_bilibiliUid);
            holder.textViewBilibiliLiveId = (TextView) convertView.findViewById(R.id.textView_bilibiliLiveId);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final PersonInfo personInfo = infos.get(position);
        holder.textViewName.setText(personInfo.name);
        holder.textViewQQNumber.setText(String.valueOf(personInfo.qq));
        holder.textViewBilibiliUid.setText(String.valueOf(personInfo.bid));
        holder.textViewBilibiliLiveId.setText(String.valueOf(personInfo.bliveRoom));
        File qqImageFile = new File(MainActivity.instence.mainDic + "user/" + personInfo.qq + ".jpg");
        File bilibiliImageFile = new File(MainActivity.instence.mainDic + "bilibili/" + personInfo.bid + ".jpg");
        if (personInfo.qq == 0) {
            holder.imageViewQQHead.setImageResource(R.drawable.stat_sys_download_anim0);
        } else {
            if (qqImageFile.exists()) {
                holder.imageViewQQHead.setImageBitmap(BitmapFactory.decodeFile(qqImageFile.getAbsolutePath()));
            } else {
                if (MainActivity.instence.onWifi) {
                    MainActivity.instence.threadPool.execute(new DownloadImageRunnable(context, holder.imageViewQQHead, personInfo.qq, HeadType.QQUser));
                } else {
                    holder.imageViewQQHead.setImageResource(R.drawable.stat_sys_download_anim0);
                    holder.imageViewQQHead.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.instence.threadPool.execute(new DownloadImageRunnable(context, holder.imageViewQQHead, personInfo.qq, HeadType.QQUser));
                        }
                    });
                }
            }
        }
        if (personInfo.bid == 0) {
            holder.imageViewBilibiiliHead.setImageResource(R.drawable.stat_sys_download_anim0);
        } else {
            if (bilibiliImageFile.exists()) {
                holder.imageViewBilibiiliHead.setImageBitmap(BitmapFactory.decodeFile(bilibiliImageFile.getAbsolutePath()));
            } else {
                if (MainActivity.instence.onWifi) {
                    MainActivity.instence.threadPool.execute(new DownloadImageRunnable(context, holder.imageViewBilibiiliHead, personInfo.bid, HeadType.BilibiliUser));
                } else {
                    holder.imageViewBilibiiliHead.setImageResource(R.drawable.stat_sys_download_anim0);
                }
            }
        }
        holder.imageViewBilibiiliHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instence.threadPool.execute(new DownloadImageRunnable(context, holder.imageViewBilibiiliHead, personInfo.bid, HeadType.BilibiliUser));
            }
        });
        holder.infoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("qq", holder.textViewQQNumber.getText().toString());
                intent.putExtra("bid", holder.textViewBilibiliUid.getText().toString());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private final class ViewHolder {
        private ImageView imageViewQQHead;
        private ImageView imageViewBilibiiliHead;
        private ImageView infoImageView;
        private TextView textViewName;
        private TextView textViewQQNumber;
        private TextView textViewBilibiliUid;
        private TextView textViewBilibiliLiveId;
    }
}
