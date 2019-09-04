package com.meng.grzxConfig.MaterialDesign.fragment;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.AdapterView.*;

import com.github.clans.fab.*;
import com.google.gson.Gson;
import com.meng.grzxConfig.MaterialDesign.*;
import com.meng.grzxConfig.MaterialDesign.activity.*;
import com.meng.grzxConfig.MaterialDesign.helpers.*;
import com.meng.grzxConfig.MaterialDesign.javaBean.*;
import com.meng.grzxConfig.MaterialDesign.javaBean.bilibili.spaceToLive.SpaceToLiveJavaBean;

import android.app.Fragment;
import android.view.View.OnClickListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PersonInfoFragment extends Fragment {

    public ListView mListView;
    private FloatingActionButton mFab;
    private FloatingActionMenu menuRed;
    public EditText editTextName, editTextQQNumber, editTextBilibiliId, editTextBilibiliLiveRoom, etTipInGroup;
    public CheckBox cbLive, cbVideo, cbAction;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.person_info_fragment, container, false);
	  }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.list);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab1);
        menuRed = (FloatingActionMenu) view.findViewById(R.id.menu_red);
        FloatingActionButton fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab2.setOnClickListener(new OnClickListener() {

			  @Override
			  public void onClick(View p1) {
				  menuRed.close(true);
				  MainActivity.instence.searchView.setVisibility(View.VISIBLE);
				  MainActivity.instence.searchView.requestFocus();
				}
			});
        mListView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(final AdapterView<?> adapterView, View p2, final int position, long p4) {
				  View view = getActivity().getLayoutInflater().inflate(R.layout.person_info_edit_view, null);
				  editTextName = (EditText) view.findViewById(R.id.edit_viewEditText_name);
				  editTextQQNumber = (EditText) view.findViewById(R.id.edit_viewEditText_qq);
				  editTextBilibiliId = (EditText) view.findViewById(R.id.edit_viewEditText_bid);
				  editTextBilibiliLiveRoom = (EditText) view.findViewById(R.id.edit_viewEditText_b_live_room);
				  etTipInGroup = (EditText) view.findViewById(R.id.edit_viewEditText_tipInGroup);
				  cbLive = (CheckBox) view.findViewById(R.id.edit_viewCheckbox_live);
				  cbVideo = (CheckBox) view.findViewById(R.id.edit_viewCheckbox_video);
				  cbAction = (CheckBox) view.findViewById(R.id.edit_viewCheckbox_action);

				  final PersonInfo personInfo = (PersonInfo) adapterView.getItemAtPosition(position);
				  editTextName.setText(personInfo.name);
				  editTextQQNumber.setText(String.valueOf(personInfo.qq));
				  editTextBilibiliId.setText(String.valueOf(personInfo.bid));
				  editTextBilibiliLiveRoom.setText(String.valueOf(personInfo.bliveRoom));
				  cbLive.setChecked(personInfo.isTipLive());
				  cbVideo.setChecked(personInfo.isTipVideo());
				  cbAction.setChecked(personInfo.isTipAction());
				  if (personInfo.tipIn.size() > 0) {
					  StringBuilder stringBuilder = new StringBuilder();
					  for (long group : personInfo.tipIn) {
						  stringBuilder.append(group).append(",");
						}
					  String str = stringBuilder.toString();
					  etTipInGroup.setText(str.substring(0, str.length() - 1));
					}
				  final String oldPersonInfo = MainActivity.instence.gson.toJson(personInfo);
				  new AlertDialog.Builder(getActivity())
					.setView(view)
					.setTitle("编辑")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int which) {
							new AlertDialog.Builder(getActivity())
							  .setTitle("确定修改吗")
							  .setPositiveButton("确定", new DialogInterface.OnClickListener() {
								  @Override
								  public void onClick(DialogInterface dialogInterface, int which) {
									  personInfo.name = editTextName.getText().toString();
									  personInfo.qq = Long.parseLong(editTextQQNumber.getText().toString());
									  personInfo.bid = Integer.parseInt(getUId(editTextBilibiliId.getText().toString()));
									  personInfo.bliveRoom = Integer.parseInt(getLiveId(editTextBilibiliLiveRoom.getText().toString()));
									  personInfo.setTipLive(cbLive.isChecked());
									  personInfo.setTipVideo(cbVideo.isChecked());
									  personInfo.setTipAction(cbAction.isChecked());
									  personInfo.tipIn.clear();
									  if (!etTipInGroup.getText().toString().equals("")) {
										  String[] strs = etTipInGroup.getText().toString().split(",");
										  for (int i = 0, strsLength = strs.length; i < strsLength; i++) {
											  String s = strs[i];
											  personInfo.tipIn.add(Long.parseLong(s));
											}
										}
									  MainActivity.instence.networkManager.send(NetworkType.setPersonInfo, oldPersonInfo + " " + MainActivity.instence.gson.toJson(personInfo), MainActivity.instence.personInfoAdapter);
									}
								}).setNegativeButton("取消", null).show();
						  }
					  }).setNegativeButton("取消", null).show();
				}
			});

        mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			  @Override
			  public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long id) {
				  new AlertDialog.Builder(getActivity())
					.setTitle("确定删除吗")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int which2) {
							PersonInfo personInfo = (PersonInfo) adapterView.getItemAtPosition(position);
							MainActivity.instence.configJavaBean.personInfo.remove(personInfo);
							MainActivity.instence.networkManager.send(NetworkType.removePersonInfo, MainActivity.instence.gson.toJson(personInfo), MainActivity.instence.personInfoAdapter);
						  }
					  }).setNegativeButton("取消", null).show();
				  return true;
				}
			});
        menuRed.setClosedOnTouchOutside(true);

        mFab.setOnClickListener(new View.OnClickListener() {

			  @Override
			  public void onClick(View p1) {
				  menuRed.close(true);
				  View view = getActivity().getLayoutInflater().inflate(R.layout.person_info_edit_view, null);
				  editTextName = (EditText) view.findViewById(R.id.edit_viewEditText_name);
				  editTextQQNumber = (EditText) view.findViewById(R.id.edit_viewEditText_qq);
				  editTextBilibiliId = (EditText) view.findViewById(R.id.edit_viewEditText_bid);
				  editTextBilibiliLiveRoom = (EditText) view.findViewById(R.id.edit_viewEditText_b_live_room);
				  cbLive = (CheckBox) view.findViewById(R.id.edit_viewCheckbox_live);
				  cbVideo = (CheckBox) view.findViewById(R.id.edit_viewCheckbox_video);
				  cbAction = (CheckBox) view.findViewById(R.id.edit_viewCheckbox_action);
				  etTipInGroup = (EditText) view.findViewById(R.id.edit_viewEditText_tipInGroup);

				  new AlertDialog.Builder(getActivity())
					.setView(view)
					.setTitle("编辑")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11, int p2) {

							new AlertDialog.Builder(getActivity())
							  .setTitle("确定添加吗")
							  .setPositiveButton("确定", new DialogInterface.OnClickListener() {
								  @Override
								  public void onClick(DialogInterface p11, int p2) {
									  for (PersonInfo user : MainActivity.instence.configJavaBean.personInfo) {
										  if (Long.parseLong(getUId(editTextBilibiliId.getText().toString())) == user.bid &&
											  Long.parseLong(editTextQQNumber.getText().toString()) == user.qq &&
											  editTextName.getText().toString().equals(user.name) &&
											  Integer.parseInt(getLiveId(editTextBilibiliLiveRoom.getText().toString())) == user.bliveRoom) {
											  return;
											}
										}

									  MainActivity.instence.threadPool.execute(new Runnable() {
											@Override
											public void run() {
												final PersonInfo user = new PersonInfo();
												user.name = editTextName.getText().toString();
												user.qq = Long.parseLong(editTextQQNumber.getText().toString());
												user.bid = Integer.parseInt(getUId(editTextBilibiliId.getText().toString()));
												user.bliveRoom = Integer.parseInt(getLiveId(editTextBilibiliLiveRoom.getText().toString()));
												user.setTipLive(cbLive.isChecked());
												user.setTipVideo(cbVideo.isChecked());
												user.setTipAction(cbAction.isChecked());
												String[] strs = etTipInGroup.getText().toString().split(",");
												for (int i = 0, strsLength = strs.length; i < strsLength; i++) {
													String s = strs[i];
													if(s.equals("")){
													  break;
													}
													user.tipIn.add(Long.parseLong(s));
												  }
												MainActivity.instence.configJavaBean.personInfo.add(user);
												MainActivity.instence.networkManager.send(NetworkType.addPersonInfo, MainActivity.instence.gson.toJson(user), MainActivity.instence.personInfoAdapter);
											  }
										  });
									}
								}).setNegativeButton("取消", null).show();
						  }
					  }).setNegativeButton("取消", null).show();
				}
			});

        menuRed.setMenuButtonShowAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.show_from_bottom));
        menuRed.setMenuButtonHideAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.hide_to_bottom));
        menuRed.setClosedOnTouchOutside(true);
	  }
	  private String getLiveId(String url){
		  if(url.startsWith("live.bilibili.com/")){
			  StringBuilder stringBuilder = new StringBuilder();
			  for (int i = url.indexOf("live.bilibili.com/") + 18; i < url.length(); ++i) {
				  if (url.charAt(i) >= 48 && url.charAt(i) <= 57) {
					  stringBuilder.append(url.charAt(i));
					} else {
					  break;
					}
				}
			  return stringBuilder.toString();
		  }else{
			return url;
		  }
	  }
	private String getUId(String url){
	  if(url.startsWith("UID:")){
		return url.substring(4);
		} else {
		  return url;
		}
	  }
	  
    public String readCode(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            InputStream in = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
			  }
            return sb.toString();
		  } catch (Exception e) {
            return e.toString();
		  }
	  }
  }
