package com.meng.grzxConfig.MaterialDesign.activity;

import android.Manifest;
import android.content.*;
import android.content.pm.PackageManager;
import android.net.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import com.google.gson.*;
import com.meng.grzxConfig.MaterialDesign.R;
import com.meng.grzxConfig.MaterialDesign.fragment.GroupConfigFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.HomeFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.MenusFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.PersonInfoFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.ProgressFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.QQNotReplyFragment;
import com.meng.grzxConfig.MaterialDesign.fragment.WordNotReplyFragment;
import com.meng.grzxConfig.MaterialDesign.helpers.NetworkManager;
import com.meng.grzxConfig.MaterialDesign.adapters.*;
import com.meng.grzxConfig.MaterialDesign.javaBean.*;
import java.io.*;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

	public static MainActivity instence;
    private DrawerLayout mDrawerLayout;

	public ConfigJavaBean configJavaBean;
    public Gson gson = new Gson();
    public boolean onWifi = false;
    public String mainDic = "";
    public NetworkManager networkManager;
    public GroupConfigAdapter groupConfigAdapter;
    public QQNotReplyAdapter qqNotReplyAdapter;
    public ArrayAdapter wordNotReplyAdapter;
    public PersonInfoAdapter personInfoAdapter;

	public GroupConfigFragment groupConfigFragment;
	public QQNotReplyFragment qqNotReplyFragment;
	public WordNotReplyFragment wordNotReplyFragment;
	public PersonInfoFragment personInfoFragment;
	public HomeFragment homeFragment;
	public MenusFragment menusFragment;
	public ProgressFragment progressFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
		instence = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                new AlertDialog.Builder(this)
                        .setTitle("权限申请")
                        .setMessage("本软件需要存储权限用于部分数据存储")
                        .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 321);
                            }
                        }).setCancelable(false).show();
            }
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
		  this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);

		//initHomeFragment(false);
		initQQFragment(false);
		initWordFragment(false);
		initPersonFragment(false);
		//initProgressFragment(false);
		//initMenuFragment(false);
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

        navigationView.setCheckedItem(R.id.group_config);
	  }

    NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
			mDrawerLayout.closeDrawer(GravityCompat.END);
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
		*/		case R.id.group_config:
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
			  }

			//    ft.replace(R.id.fragment, fragment).commit();
            return true;
		  }
	  };

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
		  } else {
            super.onBackPressed();
		  }
	  }

	public void loadConfigData(String s) {
        configJavaBean = gson.fromJson(s, ConfigJavaBean.class);
        groupConfigAdapter = new GroupConfigAdapter(this, configJavaBean.groupConfigs);
        qqNotReplyAdapter = new QQNotReplyAdapter(this, configJavaBean.QQNotReply);
        personInfoAdapter = new PersonInfoAdapter(this, configJavaBean.personInfo);
        wordNotReplyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, configJavaBean.wordNotReply);
        groupConfigFragment.mListView.setAdapter(groupConfigAdapter);
        qqNotReplyFragment.mListView.setAdapter(qqNotReplyAdapter);
        wordNotReplyFragment.mListView.setAdapter(wordNotReplyAdapter);
        personInfoFragment.mListView.setAdapter(personInfoAdapter);
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

