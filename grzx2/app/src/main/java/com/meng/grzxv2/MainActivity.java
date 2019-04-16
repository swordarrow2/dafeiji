package com.meng.grzxv2;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;

import com.google.gson.*;
import com.meng.grzxv2.adapters.*;
import com.meng.grzxv2.javaBean.*;

import java.io.*;
import java.util.*;

import android.widget.SearchView.*;

public class MainActivity extends Activity {
    public ListView listViewGroupReply, listViewQQNotReply, listViewWordNotReply,
            listViewPersonInfo;
    public EditText editTextName, editTextQQNumber, editTextBilibiliId, editTextBilibiliLiveRoom;
    public ConfigJavaBean configJavaBean;
    public TabHost tabHost;
    public Gson gson = new Gson();
    public static boolean onWifi = false;
    public static String mainDic = "";
    private SearchView searchView;
    public NetworkManager networkManager;
    private GroupReplyListAdapter groupReplyListAdapter;
    private QQNotReplyAdapter qqNotReplyAdapter;
    private ArrayAdapter wordNotReplyAdapter;
    private PersonInfoAdapter personInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getActionBar().show();
        networkManager = new NetworkManager(this);
        searchView = new SearchView(this);
        searchView.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                switch (tabHost.getCurrentTab()) {
                    case 0:
                        if (newText.equals("")) {
                            listViewGroupReply.setAdapter(groupReplyListAdapter);
                        } else {
                            ArrayList<GroupConfig> list = new ArrayList<>();
                            for (GroupConfig p : configJavaBean.groupConfigs) {
                                String bid = String.valueOf(p.groupNumber);
                                if (bid.contains(newText)) {
                                    list.add(p);
                                }
                            }
                            listViewGroupReply.setAdapter(new GroupReplyListAdapter(MainActivity.this, list));
                        }
                        break;
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
                            listViewPersonInfo.setAdapter(new PersonInfoAdapter(MainActivity.this, list));
                        }
                        break;
                }
                return false;
            }
        });
        mainDic = Environment.getExternalStorageDirectory() + "/Pictures/grzx/";
        File f = new File(mainDic + "group/");
        if (!f.exists()) {
            f.mkdirs();
        }
        File f2 = new File(mainDic + "user/");
        if (!f2.exists()) {
            f2.mkdirs();
        }
        File f3 = new File(mainDic + "bilibili/");
        if (!f3.exists()) {
            f3.mkdirs();
        }
        File f4 = new File(mainDic + ".nomedia");
        if (!f4.exists()) {
            try {
                f4.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

        listViewGroupReply = (ListView) findViewById(R.id.mainListView_GroupReply);
        listViewQQNotReply = (ListView) findViewById(R.id.mainListView_QQNotReply);
        listViewWordNotReply = (ListView) findViewById(R.id.mainListView_WordNotReply);
        listViewPersonInfo = (ListView) findViewById(R.id.mainListView_PersonInfo);

        tabHost = (TabHost) findViewById(R.id.pixiv_download_main_tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("回复的群").setContent(R.id.mainListView_GroupReply));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("不回复的QQ").setContent(R.id.mainListView_QQNotReply));
        tabHost.addTab(tabHost.newTabSpec("three").setIndicator("不回复的字").setContent(R.id.mainListView_WordNotReply));
        tabHost.addTab(tabHost.newTabSpec("four").setIndicator("账号").setContent(R.id.mainListView_PersonInfo));
        networkManager.getJsonString();
        listViewGroupReply.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final GroupConfig groupConfig = (GroupConfig) adapterView.getItemAtPosition(position);
                final View v = MainActivity.this.getLayoutInflater().inflate(R.layout.group_config, null);
                final EditText et = (EditText) v.findViewById(R.id.group_configTextView_groupNumber);
                et.setText(String.valueOf(groupConfig.groupNumber));
                final Switch repeat = (Switch) v.findViewById(R.id.group_configSwitch_repeat);
                final Switch setu = (Switch) v.findViewById(R.id.group_configSwitch_setu);
                final Switch pohai = (Switch) v.findViewById(R.id.group_configSwitch_pohai);
                final Switch dic = (Switch) v.findViewById(R.id.group_configSwitch_dic);
                final Switch bilibili = (Switch) v.findViewById(R.id.group_configSwitch_bilibili);
                final Switch cuigeng = (Switch) v.findViewById(R.id.group_configSwitch_cuigeng);
                final Switch searchPicture = (Switch) v.findViewById(R.id.group_configSwitch_soutu);
                final Switch checkLink = (Switch) v.findViewById(R.id.group_configSwitch_link);
                final Switch roll = (Switch) v.findViewById(R.id.group_configSwitch_roll);
                final Switch barcode = (Switch) v.findViewById(R.id.group_configSwitch_barcode);
                final Switch kuiping = (Switch) v.findViewById(R.id.group_configSwitch_kuiping);
                final Switch cqma = (Switch) v.findViewById(R.id.group_configSwitch_cqcode);
                final Switch zan = (Switch) v.findViewById(R.id.group_configSwitch_zan);
                final Switch moshenfusong = (Switch) v.findViewById(R.id.group_configSwitch_mishenfusong);
                repeat.setChecked(groupConfig.isRepeat());
                setu.setChecked(groupConfig.isSetu());
                pohai.setChecked(groupConfig.isPohai());
                dic.setChecked(groupConfig.isDic());
                bilibili.setChecked(groupConfig.isBilibiliCheck());
                cuigeng.setChecked(groupConfig.isCuigeng());
                searchPicture.setChecked(groupConfig.isSearchPic());
                checkLink.setChecked(groupConfig.isCheckLink());
                roll.setChecked(groupConfig.isRoll());
                barcode.setChecked(groupConfig.isBarcode());
                kuiping.setChecked(groupConfig.isKuiping());
                cqma.setChecked(groupConfig.isCqCode());
                zan.setChecked(groupConfig.isZan());
                moshenfusong.setChecked(groupConfig.isMoshenfusong());

                new AlertDialog.Builder(MainActivity.this)
                        .setView(v)
                        .setTitle("编辑")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface p1, int p2) {
                                groupConfig.groupNumber = Long.parseLong(et.getText().toString());
                                groupConfig.setRepeat(repeat.isChecked());
                                groupConfig.setSetu(setu.isChecked());
                                groupConfig.setPohai(pohai.isChecked());
                                groupConfig.setDic(dic.isChecked());
                                groupConfig.setBilibiliCheck(bilibili.isChecked());
                                groupConfig.setCuigeng(cuigeng.isChecked());
                                groupConfig.setSearchPic(searchPicture.isChecked());
                                groupConfig.setCheckLink(checkLink.isChecked());
                                groupConfig.setRoll(roll.isChecked());
                                groupConfig.setBarcode(barcode.isChecked());
                                groupConfig.setKuiping(kuiping.isChecked());
                                groupConfig.setCqCode(cqma.isChecked());
                                groupConfig.setZan(zan.isChecked());
                                groupConfig.setMoshenfusong(moshenfusong.isChecked());
                                configJavaBean.groupConfigs.set(findPosition(groupConfig), groupConfig);
                                networkManager.send(NetworkType.setGroup, findPosition(groupConfig) + " " + gson.toJson(groupConfig));
                            }
                        }).show();
            }
        });
        listViewQQNotReply.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int position, long l) {
                final EditText editText = new EditText(MainActivity.this);
                final long number = (long) adapterView.getItemAtPosition(position);
                editText.setText(String.valueOf(number));
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
                                                configJavaBean.QQNotReply.set(findPosition(number), Long.parseLong(editText.getText().toString()));
                                                ((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
                                                networkManager.send(NetworkType.setNotReplyUser, findPosition(number) + " " + editText.getText().toString());
                                            }
                                        }).setNegativeButton("取消", null).show();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
        listViewWordNotReply.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int position, long l) {
                final EditText editText = new EditText(MainActivity.this);
                editText.setText((String) adapterView.getItemAtPosition(position));
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
                                                configJavaBean.wordNotReply.set(findPosition((String) adapterView.getItemAtPosition(position)), editText.getText().toString());
                                                ((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
                                                networkManager.send(NetworkType.setNotReplyWord, findPosition((String) adapterView.getItemAtPosition(position)) + " " + editText.getText().toString());
                                            }
                                        }).setNegativeButton("取消", null).show();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });

        listViewPersonInfo.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View p2, final int position, long p4) {
                View view = getLayoutInflater().inflate(R.layout.person_info_edit_view, null);
                editTextName = (EditText) view.findViewById(R.id.edit_viewEditText_name);
                editTextQQNumber = (EditText) view.findViewById(R.id.edit_viewEditText_qq);
                editTextBilibiliId = (EditText) view.findViewById(R.id.edit_viewEditText_bid);
                editTextBilibiliLiveRoom = (EditText) view.findViewById(R.id.edit_viewEditText_b_live_room);

                final PersonInfo personInfo = (PersonInfo) adapterView.getItemAtPosition(position);
                editTextName.setText(personInfo.name);
                editTextQQNumber.setText(String.valueOf(personInfo.qq));
                editTextBilibiliId.setText(String.valueOf(personInfo.bid));
                editTextBilibiliLiveRoom.setText(String.valueOf(personInfo.bliveRoom));

                new AlertDialog.Builder(MainActivity.this)
                        .setView(view)
                        .setTitle("编辑")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("确定修改吗")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int which) {
                                                personInfo.name = editTextName.getText().toString();
                                                personInfo.qq = Long.parseLong(editTextQQNumber.getText().toString());
                                                personInfo.bid = Integer.parseInt(editTextBilibiliId.getText().toString().replace("UID:", ""));
                                                personInfo.bliveRoom = Integer.parseInt(editTextBilibiliLiveRoom.getText().toString().replace("http://live.bilibili.com/", "").replace("?share_source=copy_link", ""));
                                                ((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
                                                configJavaBean.personInfo.set(findPosition(personInfo), personInfo);
                                                networkManager.send(NetworkType.setPersonInfo, findPosition(personInfo) + " " + gson.toJson(personInfo));
                                            }
                                        }).setNegativeButton("取消", null).show();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
        listViewGroupReply.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long id) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("确定删除吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                GroupConfig groupConfig = (GroupConfig) adapterView.getItemAtPosition(position);
                                configJavaBean.groupConfigs.remove(findPosition(groupConfig));
                                adapterView.removeViewAt(position);
                                ((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
                                networkManager.send(NetworkType.removeGroup, String.valueOf(findPosition(groupConfig)));
                            }
                        }).setNegativeButton("取消", null).show();
                return true;
            }
        });
        listViewQQNotReply.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long id) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("确定删除吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                long qq = adapterView.getItemIdAtPosition(position);
                                configJavaBean.QQNotReply.remove(findPosition(qq));
                                adapterView.removeViewAt(position);
                                ((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
                                networkManager.send(NetworkType.removeNotReplyUser, String.valueOf(findPosition(qq)));
                            }
                        }).setNegativeButton("取消", null).show();
                return true;
            }
        });
        listViewWordNotReply.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long id) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("确定删除吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                String string = (String) adapterView.getItemAtPosition(position);
                                configJavaBean.wordNotReply.remove(findPosition(position));
                                adapterView.removeViewAt(position);
                                ((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
                                networkManager.send(NetworkType.removeNotReplyWord, String.valueOf(findPosition(string)));
                            }
                        }).setNegativeButton("取消", null).show();
                return true;
            }
        });

        listViewPersonInfo.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("确定删除吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which2) {
                                PersonInfo personInfo = (PersonInfo) adapterView.getItemAtPosition(position);
                                configJavaBean.personInfo.remove(findPosition(personInfo));
                                adapterView.removeViewAt(position);
                                ((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
                                networkManager.send(NetworkType.removePersonInfo, String.valueOf(findPosition(personInfo)));
                            }
                        }).setNegativeButton("取消", null).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem add3 = menu.add(0, 0, 0, "提交修改到服务器");
        add3.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        add3.setIcon(R.drawable.stat_sys_upload_anim0);
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
                View view = getLayoutInflater().inflate(R.layout.person_info_edit_view, null);
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
                                                for (PersonInfo user : configJavaBean.personInfo) {
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
                                                configJavaBean.personInfo.add(user);
                                                ((BaseAdapter) listViewPersonInfo.getAdapter()).notifyDataSetChanged();
                                                networkManager.send(NetworkType.addPersonInfo, gson.toJson(user));
                                            }
                                        }).setNegativeButton("取消", null).show();
                            }
                        }).setNegativeButton("取消", null).show();
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
                                                    case 0:
                                                        long inputLong = Long.parseLong(editText.getText().toString());
                                                        for (GroupConfig groupConfig : configJavaBean.groupConfigs) {
                                                            if (groupConfig.groupNumber == inputLong) {
                                                                return;
                                                            }
                                                        }
                                                        GroupConfig groupConfig = new GroupConfig();
                                                        groupConfig.groupNumber = inputLong;
                                                        configJavaBean.groupConfigs.add(groupConfig);
                                                        ((BaseAdapter) listViewGroupReply.getAdapter()).notifyDataSetChanged();
                                                        networkManager.send(NetworkType.addGroup, gson.toJson(groupConfig));
                                                        break;
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
        groupReplyListAdapter = new GroupReplyListAdapter(this, configJavaBean.groupConfigs);
        qqNotReplyAdapter = new QQNotReplyAdapter(this, configJavaBean.QQNotReply);
        personInfoAdapter = new PersonInfoAdapter(this, configJavaBean.personInfo);
        wordNotReplyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, configJavaBean.wordNotReply);
        listViewGroupReply.setAdapter(groupReplyListAdapter);
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
	
