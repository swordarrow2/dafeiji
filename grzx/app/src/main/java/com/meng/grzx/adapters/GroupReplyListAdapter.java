package com.meng.grzx.adapters;

import android.content.*;
import android.view.*;
import android.widget.*;
import android.widget.CompoundButton.*;

import java.io.*;
import java.net.*;
import java.util.*;

import android.os.*;
import android.graphics.*;

import com.meng.grzx.javaBean.GroupReply;
import com.meng.grzx.MainActivity;
import com.meng.grzx.R;

public class GroupReplyListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<GroupReply> groupReplies;
    private File imageFile;

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

        ViewHolder holder;
        if (convertView == null) {
            convertView = ((MainActivity) context).getLayoutInflater().inflate(R.layout.list_item_image_text_switch, null);
            holder = new ViewHolder();
            holder.groupNumber = (TextView) convertView.findViewById(R.id.group_reply_list_itemTextView);
            holder.replySwitch = (Switch) convertView.findViewById(R.id.group_reply_list_itemSwitch);
            holder.imageView = (ImageView) convertView.findViewById(R.id.group_reply_list_itemImageView);
            holder.replySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton p1, boolean p2) {
                    groupReplies.get(position).reply = p2;
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GroupReply groupReply = groupReplies.get(position);
        holder.groupNumber.setText(String.valueOf(groupReply.groupNum));
        holder.replySwitch.setChecked(groupReply.reply);
        imageFile = new File(Environment.getExternalStorageDirectory() + "/Pictures/grzx/group/" + groupReply + ".jpg");
        if (imageFile.exists()) {
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        } else {
            new DownloadImageThread(holder.imageView, groupReply.groupNum).start();
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
                if (is != null) {
                    FileOutputStream fos = new FileOutputStream(imageFile);
                    byte buf[] = new byte[4096];
                    int len = 0;
                    while ((len = is.read(buf)) > 0) {
                        fos.write(buf, 0, len);
                    }
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
