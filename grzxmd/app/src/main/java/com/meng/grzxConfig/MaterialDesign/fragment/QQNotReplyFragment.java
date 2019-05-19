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

import java.util.*;

import android.support.v4.app.Fragment;

import com.meng.grzxConfig.MaterialDesign.activity.MainActivity;

import c.c.myapplication.R;

public class QQNotReplyFragment extends Fragment {

    public ListView mListView;
    private FloatingActionButton mFab;
    private int mPreviousVisibleItem;

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
            public void onItemClick(final AdapterView<?> adapterView, View view, final int position, long l) {
                final EditText editText = new EditText(getActivity());
                final long number = (long) adapterView.getItemAtPosition(position);
                editText.setText(String.valueOf(number));
                new AlertDialog.Builder(getActivity())
                        .setView(editText)
                        .setTitle("编辑")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("确定修改吗")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface p11, int p2) {
                                                MainActivity.instence.configJavaBean.QQNotReply.set(MainActivity.instence.findPosition(number), Long.parseLong(editText.getText().toString()));
                                                ((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
                                                MainActivity.instence.networkManager.send(NetworkType.setNotReplyUser, MainActivity.instence.findPosition(number) + " " + editText.getText().toString());
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
                            public void onClick(DialogInterface dialogInterface, int which) {
                                long qq = adapterView.getItemIdAtPosition(position);
                                MainActivity.instence.configJavaBean.QQNotReply.remove(MainActivity.instence.findPosition(qq));
                                MainActivity.instence.qqNotReplyAdapter.notifyDataSetChanged();
                                MainActivity.instence.networkManager.send(NetworkType.removeNotReplyUser, String.valueOf(MainActivity.instence.findPosition(qq)));
                            }
                        }).setNegativeButton("取消", null).show();
                return true;
            }
        });


        mFab.hide(false);
        mFab.show(true);
        mFab.setShowAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.show_from_bottom));
        mFab.setHideAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.hide_to_bottom));
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = new EditText(getActivity());
                new AlertDialog.Builder(getActivity())
                        .setView(editText)
                        .setTitle("编辑")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {

                                new AlertDialog.Builder(getActivity())
                                        .setTitle("确定添加吗")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface p11, int p2) {
                                                Long userInput = Long.parseLong(editText.getText().toString());
                                                for (long l : MainActivity.instence.configJavaBean.QQNotReply) {
                                                    if (l == userInput) {
                                                        return;
                                                    }
                                                }
                                                MainActivity.instence.configJavaBean.QQNotReply.add(userInput);
                                                MainActivity.instence.qqNotReplyAdapter.notifyDataSetChanged();
                                                MainActivity.instence.networkManager.send(NetworkType.addNotReplyUser, String.valueOf(userInput));

                                            }
                                        }).setNegativeButton("取消", null).show();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });

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
