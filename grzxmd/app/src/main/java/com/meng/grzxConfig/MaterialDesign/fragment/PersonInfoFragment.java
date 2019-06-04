package com.meng.grzxConfig.MaterialDesign.fragment;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.view.inputmethod.*;
import android.widget.*;
import android.widget.AdapterView.*;
import com.github.clans.fab.*;
import com.meng.grzxConfig.MaterialDesign.*;
import com.meng.grzxConfig.MaterialDesign.activity.*;
import com.meng.grzxConfig.MaterialDesign.helpers.*;
import com.meng.grzxConfig.MaterialDesign.javaBean.*;

import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;

public class PersonInfoFragment extends Fragment {

    public ListView mListView;
    private FloatingActionButton mFab;
    private int mPreviousVisibleItem;
	private FloatingActionMenu menuRed;
    public EditText editTextName, editTextQQNumber, editTextBilibiliId, editTextBilibiliLiveRoom;


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
		FloatingActionButton fab2=(FloatingActionButton)view.findViewById(R.id.fab2);
		fab2.setOnClickListener(new OnClickListener(){

			  @Override
			  public void onClick(View p1) {
				  menuRed.hideMenu(true);
				  MainActivity.instence.sv.setVisibility(View.VISIBLE);
				  MainActivity.instence.sv.requestFocus();
				  InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
				  imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//开启或者关闭软键盘
				  imm.showSoftInput(MainActivity.instence.sv, InputMethodManager.SHOW_FORCED);//弹出软键盘时，焦点定位在输入框中
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

				  final PersonInfo personInfo = (PersonInfo) adapterView.getItemAtPosition(position);
				  editTextName.setText(personInfo.name);
				  editTextQQNumber.setText(String.valueOf(personInfo.qq));
				  editTextBilibiliId.setText(String.valueOf(personInfo.bid));
				  editTextBilibiliLiveRoom.setText(String.valueOf(personInfo.bliveRoom));

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
									  personInfo.bid = Integer.parseInt(editTextBilibiliId.getText().toString().replace("UID:", ""));
									  personInfo.bliveRoom = Integer.parseInt(editTextBilibiliLiveRoom.getText().toString().replace("http://live.bilibili.com/", "").replace("?share_source=copy_link", ""));
									  MainActivity.instence.configJavaBean.personInfo.set(MainActivity.instence.findPosition(personInfo), personInfo);
									  MainActivity.instence.networkManager.send(NetworkType.setPersonInfo, MainActivity.instence.findPosition(personInfo) + " " + MainActivity.instence.gson.toJson(personInfo), MainActivity.instence.personInfoAdapter);
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
							int po=MainActivity.instence.findPosition(personInfo);
							MainActivity.instence.configJavaBean.personInfo.remove(po);
							MainActivity.instence.networkManager.send(NetworkType.removePersonInfo, String.valueOf(po), MainActivity.instence.personInfoAdapter);
						  }
					  }).setNegativeButton("取消", null).show();
				  return true;
				}
			});
		menuRed.setClosedOnTouchOutside(true);

        mFab.setOnClickListener(new View.OnClickListener() {

			  @Override
			  public void onClick(View p1) {
				  View view = getActivity().getLayoutInflater().inflate(R.layout.person_info_edit_view, null);
				  editTextName = (EditText) view.findViewById(R.id.edit_viewEditText_name);
				  editTextQQNumber = (EditText) view.findViewById(R.id.edit_viewEditText_qq);
				  editTextBilibiliId = (EditText) view.findViewById(R.id.edit_viewEditText_bid);
				  editTextBilibiliLiveRoom = (EditText) view.findViewById(R.id.edit_viewEditText_b_live_room);

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
										  if (Long.parseLong(editTextBilibiliId.getText().toString().replace("UID:", "")) == user.bid &&
											  Long.parseLong(editTextQQNumber.getText().toString()) == user.qq &&
											  editTextName.getText().toString().equals(user.name) &&
											  Integer.parseInt(editTextBilibiliLiveRoom.getText().toString().replace("http://live.bilibili.com/", "").replace("?share_source=copy_link", "")) == user.bliveRoom) {
											  return;
											}
										}
									  PersonInfo user = new PersonInfo();
									  user.name = editTextName.getText().toString();
									  user.qq = Long.parseLong(editTextQQNumber.getText().toString());
									  user.bid = Integer.parseInt(editTextBilibiliId.getText().toString().replace("UID:", ""));
									  user.bliveRoom = Integer.parseInt(editTextBilibiliLiveRoom.getText().toString().replace("http://live.bilibili.com/", "").replace("?share_source=copy_link", ""));
									  MainActivity.instence.configJavaBean.personInfo.add(user);
									  MainActivity.instence.networkManager.send(NetworkType.addPersonInfo, MainActivity.instence.gson.toJson(user), MainActivity.instence.personInfoAdapter);
									}
								}).setNegativeButton("取消", null).show();
						  }
					  }).setNegativeButton("取消", null).show();
				}
			});

        menuRed.setMenuButtonShowAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.show_from_bottom));
        menuRed.setMenuButtonHideAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.hide_to_bottom));

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
			  @Override
			  public void onScrollStateChanged(AbsListView view, int scrollState) {
				}

			  @Override
			  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				  if (firstVisibleItem > mPreviousVisibleItem) {
					  menuRed.hideMenu(true);
					} else if (firstVisibleItem < mPreviousVisibleItem) {
					  menuRed.showMenu(true);
					}
				  mPreviousVisibleItem = firstVisibleItem;
				}
			});
	  }
  }
