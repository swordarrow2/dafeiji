package com.meng.grzxv2;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import c.c.myapplication.*;
import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class DicEdit extends Activity {
    public final String IP = "123.207.65.93";
    public final int PORT = 9999;
    public long groupNum;
    public ListView mainListview;
    public JsonObject jsonObject;
    public Gson gson = new Gson();
    public JsonParser parser = new JsonParser();
    public AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        groupNum = intent.getLongExtra("group", 0l);
        if (groupNum == 0) {
            finish();
        }
        setContentView(R.layout.dic_edit_main);
        getActionBar().show();
        mainListview = (ListView) findViewById(R.id.dic_editListView);
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
        getJsonString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
  /*      MenuItem add = menu.add(0, 0, 0, "添加");
        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        add.setIcon(R.drawable.ic_menu_add);
        MenuItem add2 = menu.add(1, 1, 1, "从服务器获取信息");
        add2.setIcon(R.drawable.stat_sys_download_anim0);
        add2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        MenuItem add3 = menu.add(2, 2, 2, "提交修改到服务器");
        add3.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        add3.setIcon(R.drawable.stat_sys_upload_anim0);
  */      return true;
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
                            jsonObject.add(editText.getText().toString(), new JsonArray());
                            loadConfigData(gson.toJson(jsonObject));
                        }
                    }).setNegativeButton("取消", null).show();
        } else if (item.getTitle().equals("从服务器获取信息")) {
            getJsonString();
        } else if (item.getTitle().equals("提交修改到服务器")) {
            setJsonString();
        }
        return true;
    }


    private void getJsonString() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket client = new Socket(IP, PORT);
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
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void setJsonString() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket client = new Socket(IP, PORT);
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
                        }
                    });
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void loadConfigData(String jsonString) {

        jsonObject = parser.parse(jsonString).getAsJsonObject();
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
                                array.add(editText.getText().toString());
                                loadConfigData(gson.toJson(jsonObject));
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });

        dicListview.setAdapter(new ArrayAdapter<String>(DicEdit.this, android.R.layout.simple_list_item_1, arrayList));
        dicListview.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
			  dialog.dismiss();
                new AlertDialog.Builder(DicEdit.this)
                        .setTitle("删除")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {
                                array.remove(position);
                                loadConfigData(gson.toJson(jsonObject));
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
