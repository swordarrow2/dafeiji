package com.meng.grzxConfig.MaterialDesign.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.meng.grzxConfig.MaterialDesign.R;
import com.meng.grzxConfig.MaterialDesign.activity.MainActivity;
import com.meng.grzxConfig.MaterialDesign.helpers.NetworkType;

public class GroupAutoAllowFragment extends Fragment {

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
                                                MainActivity.instence.configJavaBean.groupAutoAllowList.remove(number);
                                                MainActivity.instence.configJavaBean.groupAutoAllowList.add(Long.parseLong(editText.getText().toString()));
                                                MainActivity.instence.networkManager.send(NetworkType.setGroupAllow, number + " " + editText.getText().toString(),MainActivity.instence.groupAutoAllowAdapter);
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
                                long qq = (long) adapterView.getItemAtPosition(position);
                                MainActivity.instence.configJavaBean.groupAutoAllowList.remove(qq);
                                MainActivity.instence.networkManager.send(NetworkType.removeGroupAllow, String.valueOf(qq),MainActivity.instence.groupAutoAllowAdapter);
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
                                                for (long l : MainActivity.instence.configJavaBean.groupAutoAllowList) {
                                                    if (l == userInput) {
                                                        return;
                                                    }
                                                }
                                                MainActivity.instence.configJavaBean.groupAutoAllowList.add(userInput);
                                                MainActivity.instence.networkManager.send(NetworkType.addGroupAllow, String.valueOf(userInput),MainActivity.instence.groupAutoAllowAdapter);

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
