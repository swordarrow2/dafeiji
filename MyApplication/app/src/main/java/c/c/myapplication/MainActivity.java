package c.c.myapplication;

import android.content.*;
import android.net.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.google.gson.*;
import com.meng.grzxv2.*;
import com.meng.grzxv2.adapters.*;
import com.meng.grzxv2.javaBean.*;
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
    public GroupReplyListAdapter groupReplyListAdapter;
    public QQNotReplyAdapter qqNotReplyAdapter;
    public ArrayAdapter wordNotReplyAdapter;
    public PersonInfoAdapter personInfoAdapter;

	public GroupConfigFragment gcf;
	public QQNotReplyFragment qnf;
	public WordNotReplyFragment wnf;
	public PersonInfoFragment pif;
	public HomeFragment hf;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
		instence = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
		  this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);

		NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view2);
        navigationView2.setNavigationItemSelectedListener(navigationItemSelectedListener);
		initHomeFragment(false);
		initQQFragment(false);
		initWordFragment(false);
		initPersonFragment(false);
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
                case R.id.home:
				  //	  fragment = new HomeFragment();
				  initHomeFragment(true);
				  break;
                case R.id.menus:
				  //	  fragment = new MenusFragment();
				  break;
                case R.id.progress:
				  //	  fragment = new ProgressFragment();
				  break;
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
        groupReplyListAdapter = new GroupReplyListAdapter(this, configJavaBean.groupConfigs);
        qqNotReplyAdapter = new QQNotReplyAdapter(this, configJavaBean.QQNotReply);
        personInfoAdapter = new PersonInfoAdapter(this, configJavaBean.personInfo);
        wordNotReplyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, configJavaBean.wordNotReply);
        gcf.mListView.setAdapter(groupReplyListAdapter);
        qnf.mListView.setAdapter(qqNotReplyAdapter);
        wnf.mListView.setAdapter(wordNotReplyAdapter);
        pif.mListView.setAdapter(personInfoAdapter);
	  }

	public void initGroupConfigFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
        if (gcf == null) {
            gcf = new GroupConfigFragment();
            transactionWelcome.add(R.id.fragment, gcf);
		  }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(gcf);
		  }
        transactionWelcome.commit();
	  }

	public void initQQFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
        if (qnf == null) {
            qnf = new QQNotReplyFragment();
            transactionWelcome.add(R.id.fragment, qnf);
		  }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(qnf);
		  }
        transactionWelcome.commit();
	  }

	public void initWordFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
        if (wnf == null) {
            wnf = new WordNotReplyFragment();
            transactionWelcome.add(R.id.fragment, wnf);
		  }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(wnf);
		  }
        transactionWelcome.commit();
	  }

	public void initPersonFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
        if (pif == null) {
            pif = new PersonInfoFragment();
            transactionWelcome.add(R.id.fragment, pif);
		  }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(pif);
		  }
        transactionWelcome.commit();
	  }

	public void initHomeFragment(boolean showNow) {
        FragmentTransaction transactionWelcome = getSupportFragmentManager().beginTransaction();
        if (hf == null) {
            hf = new HomeFragment();
            transactionWelcome.add(R.id.fragment, hf);
		  }
        hideFragment(transactionWelcome);
        if (showNow) {
            transactionWelcome.show(hf);
		  }
        transactionWelcome.commit();
	  } 

	public void hideFragment(FragmentTransaction transaction) {
        Fragment fs[] = {
			gcf,
			qnf,
			wnf,
			pif,
			hf
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

