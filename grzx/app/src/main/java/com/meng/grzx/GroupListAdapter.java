package com.meng.grzx;

import android.content.*;
import android.view.*;
import android.widget.*;

import java.util.*;
import android.widget.CompoundButton.*;

public class GroupListAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<GroupReply> musicInfoBeans;
	private LayoutInflater inflater;
	private ViewHolder holder;

	public GroupListAdapter(Context context,ArrayList<GroupReply> musicInfoBeans){
		this.context=context;
		this.musicInfoBeans=musicInfoBeans;
		inflater=LayoutInflater.from(context);
	  }	

	public int getCount(){
		return musicInfoBeans.size();
	  }
	public Object getItem(int position){
		return null;
	  }
	public long getItemId(int position){
		return 0;
	  }

	public View getView(final int position,View convertView,ViewGroup parent){

		if(convertView==null){
			convertView=inflater.inflate(R.layout.group_reply_list_item,null);
			holder=new ViewHolder();
			holder.groupNumber=(TextView) convertView.findViewById(R.id.group_reply_list_itemTextView);
			holder.replySwitch=(Switch) convertView.findViewById(R.id.group_reply_list_itemSwitch);
			holder.replySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				  @Override
				  public void onCheckedChanged(CompoundButton p1,boolean p2){
					  musicInfoBeans.get(position).reply=p2;
					}
				});
			convertView.setTag(holder);
		  }else{
			holder=(ViewHolder) convertView.getTag();
		  }
		GroupReply groupReply=musicInfoBeans.get(position);
		holder.groupNumber.setText(String.valueOf(groupReply.groupNum));
		holder.replySwitch.setChecked(groupReply.reply);
		return convertView;
	  }
	//这个内部类主要为了ListView加载的一个性能优化
	//View的findViewById()方法也是比较耗时的，因此需要考虑只调用一次，
	//之后就用View.getTag()方法来获得ViewHolder对象
	//有时候如果没有必要，我们就没有必要这样去做，可以直接在上面定义
	private final class ViewHolder{
		private TextView groupNumber;
		private Switch replySwitch;
	  }
  }
