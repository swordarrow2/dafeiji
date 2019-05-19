package com.meng.grzxv2;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.widget.SearchView.*;
import c.c.myapplication.*;
import com.google.gson.*;
import com.meng.grzxv2.adapters.*;
import com.meng.grzxv2.javaBean.*;
import java.io.*;
import java.util.*;

public class MainActivity extends Activity {
    public ListView  listViewQQNotReply, listViewWordNotReply,
            listViewPersonInfo;
    public EditText editTextName, editTextQQNumber, editTextBilibiliId, editTextBilibiliLiveRoom;
    public ConfigJavaBean configJavaBean;
    public TabHost tabHost;
    public Gson gson = new Gson();
    public static boolean onWifi = false;
    public static String mainDic = "";
    private SearchView searchView;
    public NetworkManager networkManager;
    private QQNotReplyAdapter qqNotReplyAdapter;
    private ArrayAdapter wordNotReplyAdapter;
    private PersonInfoAdapter personInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.main);
        getActionBar().show();
     //   networkManager = new NetworkManager(this);
        searchView = new SearchView(this);
        searchView.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                switch (tabHost.getCurrentTab()) {
                
                    case 1:
                        if (newText.equals("")) {
                            listViewQQNotReply.setAdapter(qqNotReplyAdapter);
                        } else {
                            ArrayList<Long> list = new ArrayList<>();
                            for (long p : configJavaBean.QQNotReply) {
                                String bid = String.valueOf(p);
                                if (bid.contains(newText)) {
                                    list.add(p);
                                }
                            }
                            listViewQQNotReply.setAdapter(new QQNotReplyAdapter(MainActivity.this, list));
                        }
                        break;
                    case 2:
                        if (newText.equals("")) {
                            listViewWordNotReply.setAdapter(wordNotReplyAdapter);
                        } else {
                            ArrayList<String> list = new ArrayList<>();
                            for (String p : configJavaBean.wordNotReply) {
                                if (p.contains(newText)) {
                                    list.add(p);
                                }
                            }
                            listViewWordNotReply.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list));
                        }
                        break;
                    case 3:
                        if (newText.equals("")) {
                            listViewPersonInfo.setAdapter(personInfoAdapter);
                        } else {
                            ArrayList<PersonInfo> list = new ArrayList<>();
                            for (PersonInfo personInfo : configJavaBean.personInfo) {
                                if (String.valueOf(personInfo.bid).contains(newText) ||
                                        String.valueOf(personInfo.bliveRoom).contains(newText) ||
                                        String.valueOf(personInfo.qq).contains(newText) ||
                                        personInfo.name.contains(newText)) {
                                    list.add(personInfo);
                                }
                            }
                    //        listViewPersonInfo.setAdapter(new PersonInfoAdapter(MainActivity.this, list));
                        }
                        break;
                }
                return false;
            }
        });
        
		/*	DexClassLoader loader = new DexClassLoader(dexPath,
		 dexOutputDir.getAbsolutePath(),
		 null, ClassLoader.getSystemClassLoader().getParent());
		 try{
		 Class clz = loader.loadClass("com.youbo.switchsky.LogUtils");
		 Object instance= clz.newInstance();
		 Method method = clz.getMethod("parse", Context.class);
		 method.invoke(instance,this);
		 }catch(Exception e){}

		 */
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        onWifi = wifiNetworkInfo.isConnected();

      //  listViewQQNotReply = (ListView) findViewById(R.id.mainListView_QQNotReply);
      //  listViewWordNotReply = (ListView) findViewById(R.id.mainListView_WordNotReply);
      //  listViewPersonInfo = (ListView) findViewById(R.id.mainListView_PersonInfo);

        tabHost = (TabHost) findViewById(R.id.pixiv_download_main_tabhost);
        tabHost.setup();
        
     //   tabHost.addTab(tabHost.newTabSpec("two").setIndicator("不回复的QQ").setContent(R.id.mainListView_QQNotReply));
     //   tabHost.addTab(tabHost.newTabSpec("three").setIndicator("不回复的字").setContent(R.id.mainListView_WordNotReply));
    //    tabHost.addTab(tabHost.newTabSpec("four").setIndicator("账号").setContent(R.id.mainListView_PersonInfo));
        networkManager.getJsonString();
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem add3 = menu.add(0, 0, 0, "提交修改到服务器");
        add3.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    //    add3.setIcon(R.drawable.stat_sys_upload_anim0);
        add3.setActionView(searchView);
        MenuItem add = menu.add(1, 1, 1, "添加");
        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        add.setIcon(R.drawable.ic_menu_add);
        MenuItem add2 = menu.add(2, 2, 2, "从服务器获取信息");
        add2.setIcon(R.drawable.stat_sys_download_anim0);
        add2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("添加")) {
            if (tabHost.getCurrentTab() == 3) {
                
            } else {
                final EditText editText = new EditText(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this)
                        .setView(editText)
                        .setTitle("编辑")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {

                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("确定修改吗")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface p11, int p2) {
                                                switch (tabHost.getCurrentTab()) {
                                                    case 1:
                                                        Long userInput = Long.parseLong(editText.getText().toString());
                                                        for (long l : configJavaBean.QQNotReply) {
                                                            if (l == userInput) {
                                                                return;
                                                            }
                                                        }
                                                        configJavaBean.QQNotReply.add(userInput);
                                                        ((BaseAdapter) listViewQQNotReply.getAdapter()).notifyDataSetChanged();
                                                        networkManager.send(NetworkType.addNotReplyUser, String.valueOf(userInput));
                                                        break;
                                                    case 2:
                                                        String input = editText.getText().toString();
                                                        for (String s : configJavaBean.wordNotReply) {
                                                            if (s.equals(input)) {
                                                                return;
                                                            }
                                                        }
                                                        configJavaBean.wordNotReply.add(input);
                                                        ((BaseAdapter) listViewWordNotReply.getAdapter()).notifyDataSetChanged();
                                                        networkManager.send(NetworkType.addNotReplyWord, input);
                                                        break;
                                                }
                                            }
                                        }).setNegativeButton("取消", null).show();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        } else if (item.getTitle().equals("从服务器获取信息")) {
            networkManager.getJsonString();
        }
        return true;
    }

    public void loadConfigData(String s) {
        configJavaBean = gson.fromJson(s, ConfigJavaBean.class);
       
        qqNotReplyAdapter = new QQNotReplyAdapter(this, configJavaBean.QQNotReply);
//        personInfoAdapter = new PersonInfoAdapter(this, configJavaBean.personInfo);
        wordNotReplyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, configJavaBean.wordNotReply);
        
        listViewQQNotReply.setAdapter(qqNotReplyAdapter);
        listViewWordNotReply.setAdapter(wordNotReplyAdapter);
        listViewPersonInfo.setAdapter(personInfoAdapter);
    }


    /*
     private void loadPersonInfoData() {
     File f = new File(Environment.getExternalStorageDirectory() + "/grzxv2.json");
     if (!f.exists()) {
     configJavaBean = new ConfigJavaBean();
     configJavaBean.groupConfigs.add(new GroupConfig());
     saveTxt(gson.toJson(configJavaBean));
     }
     loadConfigData(loadFromSDFile(f));
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

     public void saveTxt(String str) {
     try {
     FileWriter fw = new FileWriter(Environment.getExternalStorageDirectory() + "/grzxv2.json");//SD卡中的路径
     fw.flush();
     fw.write(str);
     fw.close();
     } catch (Exception e) {
     e.printStackTrace();
     }
     }*/
    public int findPosition(Object javabean) {
        if (javabean instanceof GroupConfig) {
            for (int i = 0; i < configJavaBean.groupConfigs.size(); ++i) {
                if (configJavaBean.groupConfigs.get(i).equals(javabean)) {
                    return i;
                }
            }
        } else if (javabean instanceof Long) {
            for (int i = 0; i < configJavaBean.QQNotReply.size(); ++i) {
                if (configJavaBean.QQNotReply.get(i).equals(javabean)) {
                    return i;
                }
            }
        } else if (javabean instanceof String) {
            for (int i = 0; i < configJavaBean.wordNotReply.size(); ++i) {
                if (configJavaBean.wordNotReply.get(i).equals(javabean)) {
                    return i;
                }
            }
        } else if (javabean instanceof PersonInfo) {
            for (int i = 0; i < configJavaBean.personInfo.size(); ++i) {
                if (configJavaBean.personInfo.get(i).equals(javabean)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
	
