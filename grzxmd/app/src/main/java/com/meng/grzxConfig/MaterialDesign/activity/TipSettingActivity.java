package com.meng.grzxConfig.MaterialDesign.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.meng.grzxConfig.MaterialDesign.R;
import com.meng.grzxConfig.MaterialDesign.adapters.QQAccountAdapter;
import com.meng.grzxConfig.MaterialDesign.helpers.NetworkType;
import com.meng.grzxConfig.MaterialDesign.javaBean.PersonInfo;

public class TipSettingActivity extends Activity {
    private PersonInfo personInfo;
    public ListView mainListview;
    private int mPreviousVisibleItem;
    private FloatingActionMenu menuRed;
    private String oldPersonInfo = "";
    private QQAccountAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dic_edit_main);
        Intent intent = this.getIntent();
        personInfo = (PersonInfo) intent.getSerializableExtra("personInfo");
        if (personInfo == null) {
            finish();
        }
        mainListview = (ListView) findViewById(R.id.list);
        adapter = new QQAccountAdapter(this, personInfo.tipIn, true);
        mainListview.setAdapter(adapter);
        FloatingActionButton mFab = (FloatingActionButton) findViewById(R.id.fab1);
        menuRed = (FloatingActionMenu) findViewById(R.id.menu_red);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        oldPersonInfo = new Gson().toJson(personInfo);
        fab2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                menuRed.close(true);
                MainActivity.instence.networkManager.send(NetworkType.setPersonInfo, oldPersonInfo + " " + MainActivity.instence.gson.toJson(personInfo), MainActivity.instence.personInfoAdapter);
            }
        });
        mainListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                new AlertDialog.Builder(TipSettingActivity.this)
                        .setTitle("删除")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {
                                personInfo.tipIn.remove(i);
                                adapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuRed.close(true);
                final EditText editText = new EditText(TipSettingActivity.this);
                new AlertDialog.Builder(TipSettingActivity.this)
                        .setView(editText)
                        .setTitle("添加群")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {
                                String str = editText.getText().toString();
                                personInfo.tipIn.add(Long.parseLong(str.substring(str.lastIndexOf("：") + 1)));
                                adapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
        menuRed.setMenuButtonShowAnimation(AnimationUtils.loadAnimation(this, R.anim.show_from_bottom));
        menuRed.setMenuButtonHideAnimation(AnimationUtils.loadAnimation(this, R.anim.hide_to_bottom));
        menuRed.setClosedOnTouchOutside(true);
        mainListview.setOnScrollListener(new AbsListView.OnScrollListener() {
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
