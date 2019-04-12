package com.meng.grzx.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.meng.grzx.MainActivity;
import com.meng.grzx.R;
import com.meng.grzx.javaBean.bilibili.BilibiliPersonInfo;
import com.meng.grzx.javaBean.PersonInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PersonInfoAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<PersonInfo> infos;

    public PersonInfoAdapter(Context context,ArrayList<PersonInfo> infos){
        this.context=context;
        this.infos=infos;
	  }

    public int getCount(){
        return infos.size();
	  }

    public Object getItem(int position){
        return null;
	  }

    public long getItemId(int position){
        return 0;
	  }

    public View getView(final int position,View convertView,ViewGroup parent){

        ViewHolder holder;
        if(convertView==null){
            convertView=((MainActivity) context).getLayoutInflater().inflate(R.layout.person_info_list_item,null);
            holder=new ViewHolder();
            holder.imageViewQQHead=(ImageView) convertView.findViewById(R.id.imageView_qqHead);
            holder.imageViewBilibiiliHead=(ImageView) convertView.findViewById(R.id.imageView_bilibiliHead);
            holder.textViewName=(TextView) convertView.findViewById(R.id.textView_name);
            holder.textViewQQNumber=(TextView) convertView.findViewById(R.id.textView_qqnum);
            holder.textViewBilibiliUid=(TextView) convertView.findViewById(R.id.textView_bilibiliUid);
            holder.textViewBilibiliLiveId=(TextView) convertView.findViewById(R.id.textView_bilibiliLiveId);
            convertView.setTag(holder);
		  }else{
            holder=(ViewHolder) convertView.getTag();
		  }
        PersonInfo personInfo = infos.get(position);
        holder.textViewName.setText(personInfo.name);
        holder.textViewQQNumber.setText(String.valueOf(personInfo.qq));
        holder.textViewBilibiliUid.setText(String.valueOf(personInfo.bid));
        holder.textViewBilibiliLiveId.setText(String.valueOf(personInfo.bliveRoom));
        File qqImageFile = new File(Environment.getExternalStorageDirectory()+"/Pictures/grzx/user/"+personInfo.qq+".jpg");
        File bilibiliImageFile = new File(Environment.getExternalStorageDirectory()+"/Pictures/grzx/bilibili/"+personInfo.bid+".jpg");
		if(personInfo.qq!=0){
			if(qqImageFile.exists()){
				holder.imageViewQQHead.setImageBitmap(BitmapFactory.decodeFile(qqImageFile.getAbsolutePath()));
			  }else{
				new DownloadImageThread(holder.imageViewQQHead,personInfo.qq,true).start();
			  }
		  }else{
			holder.imageViewQQHead.setImageResource(R.drawable.stat_sys_download_anim0);
		  }
        if(personInfo.bid!=0){
            if(bilibiliImageFile.exists()){
                holder.imageViewBilibiiliHead.setImageBitmap(BitmapFactory.decodeFile(bilibiliImageFile.getAbsolutePath()));
			  }else{
                new DownloadImageThread(holder.imageViewBilibiiliHead,personInfo.bid,false).start();
			  }
		  }else{
			holder.imageViewBilibiiliHead.setImageResource(R.drawable.stat_sys_download_anim0);
		  }
        return convertView;
	  }

    //这个内部类主要为了ListView加载的一个性能优化
    //View的findViewById()方法也是比较耗时的，因此需要考虑只调用一次，
    //之后就用View.getTag()方法来获得ViewHolder对象
    //有时候如果没有必要，我们就没有必要这样去做，可以直接在上面定义
    private final class ViewHolder{
        private ImageView imageViewQQHead;
        private ImageView imageViewBilibiiliHead;
        private TextView textViewName;
        private TextView textViewQQNumber;
        private TextView textViewBilibiliUid;
        private TextView textViewBilibiliLiveId;
	  }


    class DownloadImageThread extends Thread{
        private ImageView imageView;
        private boolean isQQ = false;
        private long id = 0;
        private File imageFile;

        public DownloadImageThread(ImageView imageView,long id,boolean isQQ){
            this.imageView=imageView;
            this.isQQ=isQQ;
            this.id=id;
            if(isQQ){
                imageFile=new File(Environment.getExternalStorageDirectory()+"/Pictures/grzx/user/"+id+".jpg");
			  }else{
                imageFile=new File(Environment.getExternalStorageDirectory()+"/Pictures/grzx/bilibili/"+id+".jpg");
			  }
		  }

        @Override
        public void run(){
            if(isQQ){
                downloadFile("http://q2.qlogo.cn/headimg_dl?bs="+id+"&dst_uin="+id+"&dst_uin="+id+"&;dst_uin="+id+"&spec=100&url_enc=0&referer=bu_interface&term_type=PC");
			  }else{
                downloadFile(getBilibiliHeadUrl(id));
			  }
		  }

        private String getBilibiliHeadUrl(long uid){
            try{
                URL url = new URL("https://api.bilibili.com/x/space/acc/info?mid="+uid+"&jsonp=jsonp");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while((line=reader.readLine())!=null){
                    stringBuilder.append(line);
				  }
                BilibiliPersonInfo bilibiliPersonInfoJavaBean = new Gson().fromJson(stringBuilder.toString(),BilibiliPersonInfo.class);
                return bilibiliPersonInfoJavaBean.data.face;
			  }catch(Exception e){
                e.printStackTrace();
                return "";
			  }
		  }

        private void downloadFile(String url){
            try{
                URL u = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");
                InputStream is = connection.getInputStream();
                FileOutputStream fos = new FileOutputStream(imageFile);
                byte buf[] = new byte[4096];
                int len = 0;
                while((len=is.read(buf))>0){
                    fos.write(buf,0,len);
				  }
                is.close();
                connection.disconnect();
                ((MainActivity) context).runOnUiThread(new Runnable() {

					  @Override
					  public void run(){
						  imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
						}
					});
			  }catch(IOException e){
                e.printStackTrace();
			  }
		  }
	  }
  }
