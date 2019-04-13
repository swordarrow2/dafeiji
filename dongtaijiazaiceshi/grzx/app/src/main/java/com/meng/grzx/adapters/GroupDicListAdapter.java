package com.meng.grzx.adapters;

import android.content.*;
import android.view.*;
import android.widget.*;

import java.io.*;
import java.net.*;
import java.util.*;

import android.os.*;
import android.graphics.*;

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

        ViewHolder holder;
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
        long dicGroup = dicList.get(position);
        holder.groupNumber.setText(String.valueOf(dicGroup));
        File imageFile = new File(Environment.getExternalStorageDirectory() + "/Pictures/grzx/group/" + dicGroup + ".jpg");
        if (imageFile.exists()) {
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        } else {
            new DownloadImageThread(holder.imageView, dicGroup).start();
        }
        return convertView;
    }

    //这个内部类主要为了ListView加载的一个性能优化
    //View的findViewById()方法也是比较耗时的，因此需要考虑只调用一次，
    //之后就用View.getTag()方法来获得ViewHolder对象
    //有时候如果没有必要，我们就没有必要这样去做，可以直接在上面定义
    private final class ViewHolder {
        private ImageView imageView;
        private TextView groupNumber;
        private Switch replySwitch;
    }

    class DownloadImageThread extends Thread {
        private ImageView imageView;
        private String imageUrl = "";
        private long groupNumber;
        private File imageFile;

        public DownloadImageThread(ImageView imageView, long groupNumber) {
            this.groupNumber = groupNumber;
            this.imageView = imageView;
            imageUrl = "http://p.qlogo.cn/gh/" + groupNumber + "/" + groupNumber + "/100/";
        }

        @Override
        public void run() {
			if(!((MainActivity) context).onWifi){
			  imageView.setImageResource(R.drawable.stat_sys_download_anim0);
			  return;
			}
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
                        imageView.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/Pictures/grzx/group/" + groupNumber + ".jpg"));
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
