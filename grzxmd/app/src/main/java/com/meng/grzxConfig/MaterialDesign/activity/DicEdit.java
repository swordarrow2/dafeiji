package com.meng.grzxConfig.MaterialDesign.activity;


import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.AdapterView.*;

import com.github.clans.fab.*;
import com.google.gson.*;
import com.meng.grzxConfig.MaterialDesign.*;

import java.io.*;
import java.net.*;
import java.util.*;

import android.app.AlertDialog;

public class DicEdit extends AppCompatActivity {
    public long groupNum = -1;
    public JsonObject jsonObject;
    public Gson gson = new Gson();
    public JsonParser parser = new JsonParser();
    public ListView mainListview;
    public AlertDialog dialog;
    private FloatingActionButton mFab;
    private int mPreviousVisibleItem;
    private FloatingActionMenu menuRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dic_edit_main);
        groupNum = getIntent().getLongExtra("num", -1);
        if (groupNum == -1) {
            finish();
        }
        mainListview = (ListView) findViewById(R.id.list);
        mFab = (FloatingActionButton) findViewById(R.id.fab1);
        menuRed = (FloatingActionMenu) findViewById(R.id.menu_red);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                menuRed.close(true);
                setJsonString();
            }
        });
        mainListview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                View dicEditView = getLayoutInflater().inflate(R.layout.dic_edit_group, null);
                initDicEditView(dicEditView, adapterView, view, i, l);
                dialog = new AlertDialog.Builder(DicEdit.this).setView(dicEditView).show();
            }
        });
        mainListview.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(DicEdit.this)
                        .setTitle("删除")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {
                                jsonObject.keySet().remove(parent.getItemAtPosition(position));
                                loadConfigData(gson.toJson(jsonObject));

                            }
                        }).setNegativeButton("取消", null).show();

                return true;
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuRed.close(true);
                final EditText editText = new EditText(DicEdit.this);
                new AlertDialog.Builder(DicEdit.this)
                        .setView(editText)
                        .setTitle("添加词条")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {
                                if (editText.getText().toString().length() < 3) {
                                    Toast.makeText(DicEdit.this, "谢绝过短关键字", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (editText.getText().toString().contains("椰叶") | editText.getText().toString().contains("人？")) {
                                    Toast.makeText(DicEdit.this, "谢绝膜人", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                jsonObject.add(editText.getText().toString(), new JsonArray());
                                loadConfigData(gson.toJson(jsonObject));
                                setJsonString();
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
        getJsonString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("添加")) {
            final EditText editText = new EditText(DicEdit.this);
            new AlertDialog.Builder(DicEdit.this)
                    .setView(editText)
                    .setTitle("添加词条")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface p11, int p2) {
                            if (editText.getText().toString().length() < 3) {
                                Toast.makeText(DicEdit.this, "谢绝过短关键字", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (editText.getText().toString().contains("椰叶") | editText.getText().toString().contains("人？")) {
                                Toast.makeText(DicEdit.this, "谢绝膜人", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            jsonObject.add(editText.getText().toString(), new JsonArray());
                            loadConfigData(gson.toJson(jsonObject));
                            setJsonString();
                        }
                    }).setNegativeButton("取消", null).show();
        } else if (item.getTitle().equals("从服务器获取信息")) {
            getJsonString();
        } else if (item.getTitle().equals("提交修改到服务器")) {
            setJsonString();
        }
        return true;
    }

    @Override
    protected void onResume() {
        getJsonString();
        super.onResume();
    }

    private void getJsonString() {
        MainActivity.instence.threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket client = new Socket(MainActivity.instence.editConfig.ip, MainActivity.instence.editConfig.dicPort);
                    OutputStream out = client.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    dos.writeUTF("get" + groupNum + ".");
                    InputStream in = client.getInputStream();
                    DataInputStream dis = new DataInputStream(in);
                    final String result = dis.readUTF();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            loadConfigData(result);
                        }
                    });
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setJsonString() {
        MainActivity.instence.threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket client = new Socket(MainActivity.instence.editConfig.ip, MainActivity.instence.editConfig.dicPort);
                    OutputStream out = client.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    dos.writeUTF("write" + groupNum + "." + gson.toJson(jsonObject));
                    InputStream in = client.getInputStream();
                    DataInputStream dis = new DataInputStream(in);
                    final String result = dis.readUTF();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(DicEdit.this, result.equals("ok") ? "成功" : "失败", Toast.LENGTH_SHORT).show();
                            getJsonString();
                        }
                    });
                    client.close();
                } catch (Exception e) {
                }
            }
        });
    }

    private void loadConfigData(String jsonString) {

        jsonObject = parser.parse(jsonString).getAsJsonObject();// 谷歌的GSON对象
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry me : jsonObject.entrySet()) {
            String s = (String) me.getKey();
            list.add(s.replace("\"", ""));
        }
        mainListview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
    }

    private void initDicEditView(View dicEditView, AdapterView<?> adapterView, View view, final int i, long l) {
        ListView dicListview = (ListView) dicEditView.findViewById(R.id.dic_edit_group_listview);
        final JsonArray array = jsonObject.getAsJsonArray((String) adapterView.getItemAtPosition(i));
        ArrayList<String> arrayList = new ArrayList<String>();
        for (JsonElement s : array) arrayList.add(s.getAsString());

        ((Button) dicEditView.findViewById(R.id.dic_edit_group_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                final EditText editText = new EditText(DicEdit.this);
                new AlertDialog.Builder(DicEdit.this)
                        .setView(editText)
                        .setTitle("添加词条")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {
                                if (editText.getText().toString().contains("椰叶") | editText.getText().toString().contains("人？")) {
                                    Toast.makeText(DicEdit.this, "谢绝膜人", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                array.add(editText.getText().toString());
                                loadConfigData(gson.toJson(jsonObject));
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });

        dicListview.setAdapter(new ArrayAdapter<String>(DicEdit.this, android.R.layout.simple_list_item_1, arrayList));
        dicListview.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(DicEdit.this)
                        .setTitle("删除")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {
                                array.remove(position);
                                loadConfigData(gson.toJson(jsonObject));
                                ((BaseAdapter) parent.getAdapter()).notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消", null).show();
                return true;
            }
        });

        dicListview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                dialog.dismiss();
                final EditText editText = new EditText(DicEdit.this);
                editText.setText(array.get(position).getAsString());
                new AlertDialog.Builder(DicEdit.this)
                        .setView(editText)
                        .setTitle("修改词条")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {
                                new AlertDialog.Builder(DicEdit.this)
                                        .setTitle("确定修改吗")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface p11, int p2) {
                                                array.remove(position);
                                                array.add(editText.getText().toString());
                                                loadConfigData(gson.toJson(jsonObject));
                                            }
                                        }).setNegativeButton("取消", null).show();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
    }
}

