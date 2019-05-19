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
import com.meng.grzxConfig.MaterialDesign.helpers.NetworkType;
import com.meng.grzxConfig.MaterialDesign.javaBean.*;

import java.util.*;

import android.support.v4.app.Fragment;

import com.meng.grzxConfig.MaterialDesign.activity.MainActivity;

import c.c.myapplication.R;

public class PersonInfoFragment extends Fragment {

    public ListView mListView;
    private FloatingActionButton mFab;
    private int mPreviousVisibleItem;

    public EditText editTextName, editTextQQNumber, editTextBilibiliId, editTextBilibiliLiveRoom;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.list);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
                                                ((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
                                                MainActivity.instence.configJavaBean.personInfo.set(MainActivity.instence.findPosition(personInfo), personInfo);
                                                MainActivity.instence.networkManager.send(NetworkType.setPersonInfo, MainActivity.instence.findPosition(personInfo) + " " + MainActivity.instence.gson.toJson(personInfo));
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
                                MainActivity.instence.configJavaBean.personInfo.remove(MainActivity.instence.findPosition(personInfo));
                                MainActivity.instence.personInfoAdapter.notifyDataSetChanged();
                                MainActivity.instence.networkManager.send(NetworkType.removePersonInfo, String.valueOf(MainActivity.instence.findPosition(personInfo)));
                            }
                        }).setNegativeButton("取消", null).show();
                return true;
            }
        });

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
                                                ((BaseAdapter) mListView.getAdapter()).notifyDataSetChanged();
                                                MainActivity.instence.networkManager.send(NetworkType.addPersonInfo, MainActivity.instence.gson.toJson(user));
                                            }
                                        }).setNegativeButton("取消", null).show();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
        mFab.hide(false);
        mFab.show(true);
        mFab.setShowAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.show_from_bottom));
        mFab.setHideAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.hide_to_bottom));

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > mPreviousVisibleItem) {
                    mFab.hide(true);
                } else if (firstVisibleItem < mPreviousVisibleItem) {
                    mFab.show(true);
                }
                mPreviousVisibleItem = firstVisibleItem;
            }
        });
    }
}
