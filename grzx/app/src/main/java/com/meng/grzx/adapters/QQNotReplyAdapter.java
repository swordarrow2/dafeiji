package com.meng.grzx.adapters;

import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.meng.grzx.MainActivity;
import com.meng.grzx.R;

import java.io.*;
import java.net.*;
import java.util.*;

public class QQNotReplyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Long> notReplyList;

    public QQNotReplyAdapter(Context context, ArrayList<Long> notReplyList) {
        this.context = context;
        this.notReplyList = notReplyList;
    }

    public int getCount() {
        return notReplyList.size();
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
            holder.replySwitch.setChecked(false);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        long groupReply = notReplyList.get(position);
        holder.groupNumber.setText(String.valueOf(groupReply));
        File imageFile = new File(Environment.getExternalStorageDirectory() + "/Pictures/grzx/user/" + groupReply + ".jpg");
        if (imageFile.exists()) {
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        } else {
            new DownloadImageThread(holder.imageView, groupReply).start();
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
        private File downloadFile;

        public DownloadImageThread(ImageView imageView, long QQnumber) {
            this.imageView = imageView;
            imageUrl = "http://q2.qlogo.cn/headimg_dl?bs=" + QQnumber + "&dst_uin=" + QQnumber + "&dst_uin=" + QQnumber + "&;dst_uin=" + QQnumber + "&spec=100&url_enc=0&referer=bu_interface&term_type=PC";
            downloadFile = new File(Environment.getExternalStorageDirectory() + "/Pictures/grzx/user/" + QQnumber + ".jpg");
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
                FileOutputStream fos = new FileOutputStream(downloadFile);
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
                        imageView.setImageBitmap(BitmapFactory.decodeFile(downloadFile.getAbsolutePath()));
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
