package com.meng.grzxv2.adapters;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.meng.grzxv2.DownloadImageThread;
import com.meng.grzxv2.HeadType;
import com.meng.grzxv2.MainActivity;
import com.meng.grzxv2.NetworkType;
import com.meng.grzxv2.R;
import com.meng.grzxv2.javaBean.bilibili.BilibiliPersonInfo;
import com.meng.grzxv2.javaBean.PersonInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PersonInfoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PersonInfo> infos;

    public PersonInfoAdapter(Context context, ArrayList<PersonInfo> infos) {
        this.context = context;
        this.infos = infos;
    }

    public int getCount() {
        return infos.size();
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
            convertView = ((MainActivity) context).getLayoutInflater().inflate(R.layout.person_info_list_item, null);
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
        File qqImageFile = new File(MainActivity.mainDic + "user/" + personInfo.qq + ".jpg");
        File bilibiliImageFile = new File(MainActivity.mainDic + "bilibili/" + personInfo.bid + ".jpg");
        if (personInfo.qq == 0) {
            holder.imageViewQQHead.setImageResource(R.drawable.stat_sys_download_anim0);
        } else {
            if (qqImageFile.exists()) {
                holder.imageViewQQHead.setImageBitmap(BitmapFactory.decodeFile(qqImageFile.getAbsolutePath()));
            } else {
                if (MainActivity.onWifi) {
                    new DownloadImageThread(context, holder.imageViewQQHead, personInfo.qq, HeadType.QQUser).start();
                } else {
                    holder.imageViewQQHead.setImageResource(R.drawable.stat_sys_download_anim0);
                    holder.imageViewQQHead.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new DownloadImageThread(context, holder.imageViewQQHead, personInfo.qq, HeadType.QQUser).start();
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
                if (MainActivity.onWifi) {
                    new DownloadImageThread(context, holder.imageViewBilibiiliHead, personInfo.bid, HeadType.BilibiliUser).start();
                } else {
                    holder.imageViewBilibiiliHead.setImageResource(R.drawable.stat_sys_download_anim0);
                    holder.imageViewBilibiiliHead.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new DownloadImageThread(context, holder.imageViewBilibiiliHead, personInfo.bid, HeadType.BilibiliUser).start();
                        }
                    });
                }
            }
        }
        holder.infoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ListView listView = new ListView(context);
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("text", (String) parent.getItemAtPosition(position));
                        clipboardManager.setPrimaryClip(clipData);
                        return true;
                    }
                });
                new AlertDialog.Builder(context)
                        .setTitle("信息")
                        .setView(listView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {
                                listView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, getBilibiliHeadUrl(Long.parseLong(holder.textViewBilibiliUid.getText().toString())).toArrayList()));
                            }
                        }).show();
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

    private BilibiliPersonInfo getBilibiliHeadUrl(long uid) {
        try {
            URL url = new URL("https://api.bilibili.com/x/space/acc/info?mid=" + uid + "&jsonp=jsonp");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            BilibiliPersonInfo bilibiliPersonInfoJavaBean = new Gson().fromJson(stringBuilder.toString(), BilibiliPersonInfo.class);
            return bilibiliPersonInfoJavaBean;
        } catch (Exception e) {
            e.printStackTrace();
            return new BilibiliPersonInfo();
        }
    }
}
