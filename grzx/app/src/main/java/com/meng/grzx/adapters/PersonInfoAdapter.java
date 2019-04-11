package com.meng.grzx.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.meng.grzx.MainActivity;
import com.meng.grzx.R;
import com.meng.grzx.javaBean.BilibiliUser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PersonInfoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BilibiliUser> dicList;
    private File imageFile;

    public PersonInfoAdapter(Context context, ArrayList<BilibiliUser> dicList) {
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

        ViewHolder holder;
        if (convertView == null) {
            convertView = ((MainActivity) context).getLayoutInflater().inflate(R.layout.person_info_list_item, null);
            holder = new ViewHolder();
            holder.imageViewQQHead = (ImageView) convertView.findViewById(R.id.imageView_qqHead);
            holder.imageViewBilibiiliHead = (ImageView) convertView.findViewById(R.id.imageView_bilibiliHead);
            holder.textViewName = (TextView) convertView.findViewById(R.id.textView_name);
            holder.textViewQQNumber = (TextView) convertView.findViewById(R.id.textView_qqnum);
            holder.textViewBilibiliUid = (TextView) convertView.findViewById(R.id.textView_bilibiliUid);
            holder.textViewBilibiliLiveId = (TextView) convertView.findViewById(R.id.textView_bilibiliLiveId);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BilibiliUser dicGroup = dicList.get(position);
        holder.textViewName.setText(String.valueOf(dicGroup));
        imageFile = new File(Environment.getExternalStorageDirectory() + "/Pictures/grzx/group/" + dicGroup + ".jpg");
        if (imageFile.exists()) {
            holder.imageViewQQHead.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        } else {
            new DownloadImageThread(holder.imageViewQQHead, dicGroup).start();
        }
        return convertView;
    }

    //这个内部类主要为了ListView加载的一个性能优化
    //View的findViewById()方法也是比较耗时的，因此需要考虑只调用一次，
    //之后就用View.getTag()方法来获得ViewHolder对象
    //有时候如果没有必要，我们就没有必要这样去做，可以直接在上面定义
    private final class ViewHolder {
        private ImageView imageViewQQHead;
        private ImageView imageViewBilibiiliHead;
        private TextView textViewName;
        private TextView textViewQQNumber;
        private TextView textViewBilibiliUid;
        private TextView textViewBilibiliLiveId;
    }


    class DownloadImageThread extends Thread {
        private ImageView imageView;
        private String imageUrl = "";

        public DownloadImageThread(ImageView imageView, long groupNumber) {
            this.imageView = imageView;
            imageUrl = "http://p.qlogo.cn/gh/" + groupNumber + "/" + groupNumber + "/100/";
        }

        @Override
        public void run() {
            downloadFile(imageUrl);
        }

        private void downloadFile(String url) {
            try {
                URL u = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");
                InputStream is = connection.getInputStream();
                FileOutputStream fos = new FileOutputStream(imageFile);
                byte buf[] = new byte[4096];
                int len = 0;
                while ((len = is.read(buf)) > 0) {
                    fos.write(buf, 0, len);
                }
                is.close();
                connection.disconnect();
                ((MainActivity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
