package com.meng.grzxConfig.MaterialDesign.activity;


import android.Manifest;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.meng.grzxConfig.MaterialDesign.R;
import com.meng.grzxConfig.MaterialDesign.adapters.GroupConfigAdapter;
import com.meng.grzxConfig.MaterialDesign.adapters.PersonInfoAdapter;
import com.meng.grzxConfig.MaterialDesign.adapters.QQAccountAdapter;
import com.meng.grzxConfig.MaterialDesign.fragment.AdminFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.BlackGroupFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.BlackQQFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.GroupAutoAllowFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.GroupConfigFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.HomeFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.MasterFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.MenusFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.PersonInfoFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.ProgressFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.QQNotReplyFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.SettingsPreference;
import com.meng.grzxConfig.MaterialDesign.fragment.WordNotReplyFragment;
import com.meng.grzxConfig.MaterialDesign.helpers.NetworkManager;
import com.meng.grzxConfig.MaterialDesign.helpers.SharedPreferenceHelper;
import com.meng.grzxConfig.MaterialDesign.javaBean.ConfigJavaBean;
import com.meng.grzxConfig.MaterialDesign.javaBean.EditorConfig;
import com.meng.grzxConfig.MaterialDesign.javaBean.PersonInfo;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    public static MainActivity instence;
    private DrawerLayout mDrawerLayout;

    public ConfigJavaBean configJavaBean;
    public Gson gson = new Gson();
    public boolean onWifi = false;
    public String mainDic = "";
    public NetworkManager networkManager;

    public GroupConfigAdapter groupConfigAdapter;
    public QQAccountAdapter qqNotReplyAdapter;
    public ArrayAdapter wordNotReplyAdapter;
    public PersonInfoAdapter personInfoAdapter;
    public QQAccountAdapter masterAdapter;
    public QQAccountAdapter adminAdapter;
    public QQAccountAdapter groupAutoAllowAdapter;

    public QQAccountAdapter blackQQAdapter;
    public QQAccountAdapter blackGroupAdapter;

    public GroupConfigFragment groupConfigFragment;
    public QQNotReplyFragment qqNotReplyFragment;
    public WordNotReplyFragment wordNotReplyFragment;
    public PersonInfoFragment personInfoFragment;
    public MasterFragment masterFragment;
    public AdminFragment adminFragment;
    public GroupAutoAllowFragment groupAutoAllowFragment;
    public HomeFragment homeFragment;
    public MenusFragment menusFragment;
    public ProgressFragment progressFragment;
    public BlackQQFragment blackQQFragment;
    public BlackGroupFragment blackGroupFragment;
    public SettingsPreference settingsFragment;
    public ExecutorService threadPool = Executors.newFixedThreadPool(5);
    public EditorConfig editConfig;
    public SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        instence = this;
        SharedPreferenceHelper.Init(this, "preference");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchView = (SearchView) findViewById(R.id.toolbarSearchView);
        setSupportActionBar(toolbar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    personInfoFragment.mListView.setAdapter(personInfoAdapter);
                } else {
                    HashSet<PersonInfo> list = new HashSet<>();
                    for (PersonInfo personInfo : configJavaBean.personInfo) {
                        if (String.valueOf(personInfo.bid).contains(newText) || String.valueOf(personInfo.bliveRoom).contains(newText) || String.valueOf(personInfo.qq).contains(newText) || personInfo.name.contains(newText)) {
                            list.add(personInfo);
                        }
                    }
                    personInfoFragment.mListView.setAdapter(new PersonInfoAdapter(MainActivity.this, list));
                }
                return false;
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        try {
            editConfig = new EditorConfig();
            editConfig.ip = SharedPreferenceHelper.getValue("ip", "0.0.0.0");
            int port = Integer.parseInt(SharedPreferenceHelper.getValue(SharedPreferenceHelper.getBoolean("default", true) ? "num2" : "num1"));
            editConfig.configPort = port;
            editConfig.dicPort = port + 1;
        } catch (Exception e) {
            Toast.makeText(this, "端口错误", Toast.LENGTH_SHORT).show();
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
        CheckBox cb = (CheckBox) ((ViewGroup) navigationView.getHeaderView(0)).getChildAt(0);
        cb.setChecked(SharedPreferenceHelper.getBoolean("default"));
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    try {
                        int port = Integer.parseInt(SharedPreferenceHelper.getValue("num1"));
                        editConfig.configPort = port;
                        editConfig.dicPort = port + 1;
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "端口错误:" + SharedPreferenceHelper.getValue("num2"), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        int port = Integer.parseInt(SharedPreferenceHelper.getValue("num2"));
                        editConfig.configPort = port;
                        editConfig.dicPort = port + 1;
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "端口错误:" + SharedPreferenceHelper.getValue("num1"), Toast.LENGTH_SHORT).show();
                    }
                }
                networkManager.getJsonString();
            }
        });
        //initHomeFragment(false);
        //initProgressFragment(false);
        //initMenuFragment(false);
        initQQFragment(false);
        initWordFragment(false);
        initPersonFragment(false);
        initMasterFragment(false);
        initAdminFragment(false);
        initAllowFragment(false);
        initBlackQQFragment(false);
        initBlackGroupFragment(false);
        initSettingsFragment(false);
        initGroupConfigFragment(true);

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
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        onWifi = wifiNetworkInfo.isConnected();
        mDrawerLayout.openDrawer(GravityCompat.START);
        navigationView.setCheckedItem(R.id.group_config);
        networkManager = new NetworkManager(this);
        networkManager.getJsonString();
    }

    NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            //   Fragment fragment = null;
            //    final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
				  /*        case R.id.home:
				   //	  fragment = new HomeFragment();
				   initHomeFragment(true);
				   break;
				   case R.id.menus:
				   initMenuFragment(true);
				   //	  fragment = new MenusFragment();
				   break;
				   case R.id.progress:
				   initProgressFragment(true);
				   //	  fragment = new ProgressFragment();
				   break;
				 */
                case R.id.group_config:
                    initGroupConfigFragment(true);
                    break;
                case R.id.qq_not_reply:
                    initQQFragment(true);
                    break;
                case R.id.word_not_reply:
                    initWordFragment(true);
                    break;
                case R.id.accounts:
                    initPersonFragment(true);
                    break;
                case R.id.master:
                    initMasterFragment(true);
                    break;
                case R.id.admin:
                    initAdminFragment(true);
                    break;
                case R.id.allow:
                    initAllowFragment(true);
                    break;
                case R.id.blackqq:
                    initBlackQQFragment(true);
                    break;
                case R.id.blackgroup:
                    initBlackGroupFragment(true);
                    break;
                case R.id.settings:
                    initSettingsFragment(true);
                    break;
            }
            //    ft.replace(R.id.fragment, fragment).commit();
            return true;
        }
    };

    public void loadConfigData(String s) {
        configJavaBean = gson.fromJson(s, ConfigJavaBean.class);
		String ss="";
		for(PersonInfo p:configJavaBean.personInfo){
		  if(p.tipIn.size()>0){
			ss+=p.name;
			ss+=" ";
		  }
		}
		Toast.makeText(this,ss,Toast.LENGTH_LONG).show();
        groupConfigAdapter = new GroupConfigAdapter(this, configJavaBean.groupConfigs);
        qqNotReplyAdapter = new QQAccountAdapter(this, configJavaBean.QQNotReply);
        personInfoAdapter = new PersonInfoAdapter(this, configJavaBean.personInfo);
        wordNotReplyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, configJavaBean.wordNotReply);
        masterAdapter = new QQAccountAdapter(this, configJavaBean.masterList);
        adminAdapter = new QQAccountAdapter(this, configJavaBean.adminList);
        groupAutoAllowAdapter = new QQAccountAdapter(this, configJavaBean.groupAutoAllowList);
        blackQQAdapter = new QQAccountAdapter(this, configJavaBean.blackListQQ);
        blackGroupAdapter = new QQAccountAdapter(this, configJavaBean.blackListGroup, true);

        groupConfigFragment.mListView.setAdapter(groupConfigAdapter);
        qqNotReplyFragment.mListView.setAdapter(qqNotReplyAdapter);
        wordNotReplyFragment.mListView.setAdapter(wordNotReplyAdapter);
        personInfoFragment.mListView.setAdapter(personInfoAdapter);
        masterFragment.mListView.setAdapter(masterAdapter);
        adminFragment.mListView.setAdapter(adminAdapter);
        groupAutoAllowFragment.mListView.setAdapter(groupAutoAllowAdapter);
        blackQQFragment.mListView.setAdapter(blackQQAdapter);
        blackGroupFragment.mListView.setAdapter(blackGroupAdapter);
    }

    public void initGroupConfigFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (groupConfigFragment == null) {
            groupConfigFragment = new GroupConfigFragment();
            transactionWelcome.add(R.id.fragment, groupConfigFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(groupConfigFragment);
        }
        transactionWelcome.commit();
    }

    public void initQQFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (qqNotReplyFragment == null) {
            qqNotReplyFragment = new QQNotReplyFragment();
            transactionWelcome.add(R.id.fragment, qqNotReplyFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(qqNotReplyFragment);
        }
        transactionWelcome.commit();
    }

    public void initWordFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (wordNotReplyFragment == null) {
            wordNotReplyFragment = new WordNotReplyFragment();
            transactionWelcome.add(R.id.fragment, wordNotReplyFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(wordNotReplyFragment);
        }
        transactionWelcome.commit();
    }

    public void initPersonFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (personInfoFragment == null) {
            personInfoFragment = new PersonInfoFragment();
            transactionWelcome.add(R.id.fragment, personInfoFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(personInfoFragment);
        }
        transactionWelcome.commit();
    }

    public void initMasterFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (masterFragment == null) {
            masterFragment = new MasterFragment();
            transactionWelcome.add(R.id.fragment, masterFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(masterFragment);
        }
        transactionWelcome.commit();
    }

    public void initAdminFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (adminFragment == null) {
            adminFragment = new AdminFragment();
            transactionWelcome.add(R.id.fragment, adminFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(adminFragment);
        }
        transactionWelcome.commit();
    }

    public void initAllowFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (groupAutoAllowFragment == null) {
            groupAutoAllowFragment = new GroupAutoAllowFragment();
            transactionWelcome.add(R.id.fragment, groupAutoAllowFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(groupAutoAllowFragment);
        }
        transactionWelcome.commit();
    }

    public void initBlackQQFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (blackQQFragment == null) {
            blackQQFragment = new BlackQQFragment();
            transactionWelcome.add(R.id.fragment, blackQQFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(blackQQFragment);
        }
        transactionWelcome.commit();
    }

    public void initBlackGroupFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (blackGroupFragment == null) {
            blackGroupFragment = new BlackGroupFragment();
            transactionWelcome.add(R.id.fragment, blackGroupFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(blackGroupFragment);
        }
        transactionWelcome.commit();
    }

    public void initSettingsFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (settingsFragment == null) {
            settingsFragment = new SettingsPreference();
            transactionWelcome.add(R.id.fragment, settingsFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(settingsFragment);
        }
        transactionWelcome.commit();
    }

    public void initHomeFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            transactionWelcome.add(R.id.fragment, homeFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(homeFragment);
        }
        transactionWelcome.commit();
    }

    public void initMenuFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (menusFragment == null) {
            menusFragment = new MenusFragment();
            transactionWelcome.add(R.id.fragment, menusFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(menusFragment);
        }
        transactionWelcome.commit();
    }

    public void initProgressFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getFragmentManager().beginTransaction();
        if (progressFragment == null) {
            progressFragment = new ProgressFragment();
            transactionWelcome.add(R.id.fragment, progressFragment);
        }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(progressFragment);
        }
        transactionWelcome.commit();
    }

    public void hideFragment(FragmentTransaction transaction) {
        Fragment fs[] = {
                groupConfigFragment,
                qqNotReplyFragment,
                wordNotReplyFragment,
                personInfoFragment,
                masterFragment,
                adminFragment,
                groupAutoAllowFragment,
                homeFragment,
                menusFragment,
                progressFragment,
                blackQQFragment,
                blackGroupFragment,
                settingsFragment
        };
        for (Fragment f : fs) {
            if (f != null) {
                transaction.hide(f);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        } else {
            return false;
        }
        //	return super.onKeyDown(keyCode, event);
    }

}

