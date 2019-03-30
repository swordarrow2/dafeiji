package com.meng.guirenzhengxie;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;

import com.google.gson.*;

import java.io.*;
import java.util.*;

public class MainActivity extends Activity {
    public ListView listView;
    public EditText editTextName, editTextQQNumber, editTextBilibiliId, editTextBilibiliLiveRoom;
    public PersonInfoJavaBean personInfoJavaBean;
    public  ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        actionBar = getActionBar();
        actionBar.show();
        listView = (ListView) findViewById(R.id.mainListView);
        loadData();
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> p1, View p2, final int p3, long p4) {
                View view = getLayoutInflater().inflate(R.layout.edit_view, null);
                editTextName = (EditText) view.findViewById(R.id.edit_viewEditText_name);
                editTextQQNumber = (EditText) view.findViewById(R.id.edit_viewEditText_qq);
                editTextBilibiliId = (EditText) view.findViewById(R.id.edit_viewEditText_bid);
                editTextBilibiliLiveRoom = (EditText) view.findViewById(R.id.edit_viewEditText_b_live_room);

                final PersonInfoJavaBean.MapBiliUser mapBiliUser = personInfoJavaBean.mapBiliUser.get(p3);
                editTextName.setText(mapBiliUser.name);
                editTextQQNumber.setText(String.valueOf(mapBiliUser.qq));
                editTextBilibiliId.setText(String.valueOf(mapBiliUser.bid));
                editTextBilibiliLiveRoom.setText(String.valueOf(mapBiliUser.bliveRoom));

                new AlertDialog.Builder(MainActivity.this)
                        .setView(view)
                        .setTitle("编辑")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {

                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("确定修改吗")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface p11, int p2) {
                                                mapBiliUser.name = editTextName.getText().toString();
                                                mapBiliUser.qq = Long.parseLong(editTextQQNumber.getText().toString());
                                                mapBiliUser.bid = Long.parseLong(editTextBilibiliId.getText().toString());
                                                mapBiliUser.bliveRoom = Long.parseLong(editTextBilibiliLiveRoom.getText().toString());
                                                stringTxt(new Gson().toJson(personInfoJavaBean));
                                                loadData();
                                            }
                                        }).setNegativeButton("取消", null).show();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu); 
        MenuItem add = menu.add(0, 0, 0, "添加");
        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        add.setIcon(android.R.drawable.ic_menu_add);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("添加")){
            View view = getLayoutInflater().inflate(R.layout.edit_view, null);
            editTextName = (EditText) view.findViewById(R.id.edit_viewEditText_name);
            editTextQQNumber = (EditText) view.findViewById(R.id.edit_viewEditText_qq);
            editTextBilibiliId = (EditText) view.findViewById(R.id.edit_viewEditText_bid);
            editTextBilibiliLiveRoom = (EditText) view.findViewById(R.id.edit_viewEditText_b_live_room);

            new AlertDialog.Builder(MainActivity.this)
                    .setView(view)
                    .setTitle("编辑")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface p11, int p2) {

                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("确定修改吗")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface p11, int p2) {
                                            PersonInfoJavaBean.MapBiliUser user=personInfoJavaBean.getNewBean();
                                            user.name = editTextName.getText().toString();
                                            user.qq = Long.parseLong(editTextQQNumber.getText().toString());
                                            user.bid = Long.parseLong(editTextBilibiliId.getText().toString());
                                            user.bliveRoom = Long.parseLong(editTextBilibiliLiveRoom.getText().toString());
                                            personInfoJavaBean.mapBiliUser.add(user);
                                            stringTxt(new Gson().toJson(personInfoJavaBean));
                                            loadData();
                                        }
                                    }).setNegativeButton("取消", null).show();
                        }
                    }).setNegativeButton("取消", null).show();
        }
        return true;
    }

    private void loadData() {
        personInfoJavaBean = new Gson().fromJson(loadFromSDFile(new File(Environment.getExternalStorageDirectory() + "/grzx.json")), PersonInfoJavaBean.class);
        ArrayList<String> list = new ArrayList<String>();
        for (PersonInfoJavaBean.MapBiliUser mapBiliUser : personInfoJavaBean.mapBiliUser) {
            list.add(mapBiliUser.name);
        }
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
    }

    private String loadFromSDFile(File f) {
        String result = null;
        try {
            int length = (int) f.length();
            byte[] buff = new byte[length];
            FileInputStream fin = new FileInputStream(f);
            fin.read(buff);

            fin.close();
            result = new String(buff, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void stringTxt(String str) {
        try {
            FileWriter fw = new FileWriter(Environment.getExternalStorageDirectory() + "/grzx.json");//SD卡中的路径
            fw.flush();
            fw.write(str);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
