package com.meng.grzxConfig.MaterialDesign.activity;

import android.*;
import android.content.*;
import android.content.pm.*;
import android.net.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import android.widget.SearchView.*;

import com.google.gson.*;
import com.meng.grzxConfig.MaterialDesign.adapters.*;
import com.meng.grzxConfig.MaterialDesign.fragment.*;
import com.meng.grzxConfig.MaterialDesign.helpers.*;
import com.meng.grzxConfig.MaterialDesign.javaBean.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;

import com.meng.grzxConfig.MaterialDesign.R;

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
    public ExecutorService threadPool = Executors.newFixedThreadPool(5);
    public EditorConfig editConfig;
    public SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        instence = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        sv = (SearchView) findViewById(R.id.toolbarSearchView);
        setSupportActionBar(toolbar);
        sv.setOnQueryTextListener(new OnQueryTextListener() {

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
                        if (String.valueOf(personInfo.bid).contains(newText) ||
                                String.valueOf(personInfo.bliveRoom).contains(newText) ||
                                String.valueOf(personInfo.qq).contains(newText) ||
                                personInfo.name.contains(newText)) {
                            list.add(personInfo);
                        }
                    }
                    personInfoFragment.mListView.setAdapter(new PersonInfoAdapter(MainActivity.this, list));
                }
                return false;
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        try {
            editConfig = new Gson().fromJson(readFileToString(), EditorConfig.class);
        } catch (Exception e) {
            System.exit(0);
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
        //initHomeFragment(false);
        //initProgressFragment(false);
        //initMenuFragment(false);
        initQQFragment(false);
        initWordFragment(false);
        initPersonFragment(false);
        initMasterFragment(false);
        initAdminFragment(false);
        initAllowFragment(false);
        initGroupConfigFragment(true);
        networkManager = new NetworkManager(this);
        networkManager.getJsonString();

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
            }

            //    ft.replace(R.id.fragment, fragment).commit();
            return true;
        }
    };

    public void loadConfigData(String s) {
        configJavaBean = gson.fromJson(s, ConfigJavaBean.class);
        groupConfigAdapter = new GroupConfigAdapter(this, configJavaBean.groupConfigs);
        qqNotReplyAdapter = new QQAccountAdapter(this, configJavaBean.QQNotReply);
        personInfoAdapter = new PersonInfoAdapter(this, configJavaBean.personInfo);
        wordNotReplyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(configJavaBean.wordNotReply));
        masterAdapter = new QQAccountAdapter(this, configJavaBean.masterList);
        adminAdapter = new QQAccountAdapter(this, configJavaBean.adminList);
        groupAutoAllowAdapter = new QQAccountAdapter(this, configJavaBean.groupAutoAllowList);

        groupConfigFragment.mListView.setAdapter(groupConfigAdapter);
        qqNotReplyFragment.mListView.setAdapter(qqNotReplyAdapter);
        wordNotReplyFragment.mListView.setAdapter(wordNotReplyAdapter);
        personInfoFragment.mListView.setAdapter(personInfoAdapter);
        masterFragment.mListView.setAdapter(masterAdapter);
        adminFragment.mListView.setAdapter(adminAdapter);
        groupAutoAllowFragment.mListView.setAdapter(groupAutoAllowAdapter);
    }

    public void initGroupConfigFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
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
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
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
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
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
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
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
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
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
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
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
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
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

    public void initHomeFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
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
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
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
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
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
                progressFragment
        };
        for (Fragment f : fs) {
            if (f != null) {
                transaction.hide(f);
            }
        }
    }

    public String readFileToString() throws IOException {
        File file = new File(Environment.getExternalStorageDirectory() + "/grzxEditConfig.json");
        if (!file.exists()) {
            System.exit(0);
            file.createNewFile();
        }
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        FileInputStream in = new FileInputStream(file);
        in.read(filecontent);
        in.close();
        return new String(filecontent, "UTF-8");
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

