package com.meng.dexReader;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.meng.dexProvider.*;
import dalvik.system.*;
import java.io.*;
import android.widget.TabHost.*;

public class MainActivity extends Activity{
    final String className="com.meng.dexProvider.todo";
    LinearLayout ll;
    int childCount = 0;

    public static String[] dexPaths;
	todointerface[] todoInterface;
	boolean[] excutedPause;

	public int todoFlag=0;
    String[] dexTitle;
    TabHost tabHost = null;
    TabWidget tabWidget = null;
    FrameLayout fl = null;
    LinearLayout mainView = null;
    xmlParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll=(LinearLayout) findViewById(R.id.main_linearlayout);// inflater.inflate(R.layout.activity_main,null);
        childCount=ll.getChildCount();
        ((Button) ll.findViewById(R.id.mainButton)).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1){
					// TODO: Implement this method
					reInit();
				}
			});
        init();      
		tabHost.setOnTabChangedListener(new OnTabChangeListener(){

				@Override
				public void onTabChanged(String p1){
					excutedPause[tabHost.getCurrentTab()]=false;
					excuteTabPause();
				}
			});	
    }


    private void init(){
		todoFlag=0;
        parser=new xmlParser(this);
        int lengthOfXml=parser.getXmlLength();
        dexPaths=new String[lengthOfXml];
        dexTitle=new String[lengthOfXml];
		excutedPause=new boolean[lengthOfXml];
		for(int i=0;i<lengthOfXml;i++){
			excutedPause[i]=false;
		}
		todoInterface=new todointerface[lengthOfXml];
        parser.startParse();
        tabHost=new TabHost(this,null);
        LinearLayout.LayoutParams lp_tabHost=new LinearLayout.LayoutParams(-1,-1);
        tabHost.setLayoutParams(lp_tabHost);
        tabWidget=new TabWidget(this);
        tabWidget.setId(android.R.id.tabs);
        tabWidget.setLayoutParams(new LinearLayout.LayoutParams(-1,-2));
        fl=new FrameLayout(this);
        fl.setId(android.R.id.tabcontent);
        fl.setLayoutParams(new LinearLayout.LayoutParams(-1,-2));
        mainView=new LinearLayout(this);
        LinearLayout.LayoutParams lp_mainView=new LinearLayout.LayoutParams(-1,-1);
        mainView.setLayoutParams(lp_mainView);
        mainView.setOrientation(LinearLayout.VERTICAL);
        HorizontalScrollView sl=new HorizontalScrollView(this);
        sl.addView(tabWidget);
        mainView.addView(sl);
        mainView.addView(fl);
        tabHost.addView(mainView);
        tabHost.setup();
        ll.addView(tabHost);
		Object[] objtmp;
		for(int i=0;i<dexPaths.length;i++){
			try{
                objtmp=runDex(dexPaths[i]);
                LinearLayout ll=(LinearLayout)objtmp[0];
                dexTitle[i]=(String)objtmp[1];
                ll.setId(i);
                fl.addView(ll);
                tabHost.addTab(tabHost.newTabSpec("tag"+i).setIndicator(dexTitle[i]).setContent(i));	
			}catch(Exception e){
				Toast.makeText(this,"hhh"+e.toString(),Toast.LENGTH_LONG).show();
			}
		}
    }

    private void reInit(){
        ll.removeViewAt(childCount);
		init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.action_reinit){
            reInit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Object[] runDex(String path) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        LinearLayout ll = null;
        String title="";
        File dexOutputDir = getDir("dex1",0);
		DexClassLoader loader = new DexClassLoader(path,dexOutputDir.getAbsolutePath(),
												   null,getClassLoader());
		Class clz = loader.loadClass(className);
		todointerface impl = (todointerface) clz.newInstance();
		ll=(LinearLayout) impl.init(this);
		impl.main();
		todoInterface[todoFlag]=impl;
		todoFlag++;
		title=impl.getDexTitle();
        return new Object[]{ll,title};
    }

	@Override
	protected void onPause(){
		// TODO: Implement this method
		super.onPause();
		todoInterface[tabHost.getCurrentTab()].onDexPause();

	}

	@Override
	protected void onDestroy(){
		// TODO: Implement this method
		for(int i=0;i<todoInterface.length;i++){
			try{
				todoInterface[i].onDexPause();
			}catch(Exception e){
				Toast.makeText(this,"第"+i+"个出现异常"+e.toString(),Toast.LENGTH_LONG).show();
			}
		}
		super.onDestroy();
	}


	@Override
	protected void onResume(){
		// TODO: Implement this method
		super.onResume();
		excuteTabPause();
	}

	/*
	 @Override
	 protected void onPause(){
	 for(int i=0;i<todoInterface.length;i++){
	 try{
	 todoInterface[i].onDexPause();
	 }catch(Exception e){
	 Toast.makeText(this,todoInterface[i].getDexTitle()+"出现异常"+e.toString(),Toast.LENGTH_LONG).show();
	 }
	 }
	 super.onPause();
	 }

	 @Override
	 protected void onResume(){
	 for(int i=0;i<todoInterface.length;i++){
	 try{
	 todoInterface[i].onDexResume();
	 }catch(Exception e){
	 Toast.makeText(this,todoInterface[i].getDexTitle()+"出现异常"+e.toString(),Toast.LENGTH_LONG).show();
	 }
	 }
	 super.onResume();
	 }
	 */


	private void excuteTabPause(){
		for(int i=0;i<todoInterface.length;i++){
			if(i==tabHost.getCurrentTab()){
				try{
					todoInterface[i].onDexResume();
				}catch(Exception e){
					Toast.makeText(this,"第"+i+"个出现异常"+e.toString(),Toast.LENGTH_LONG).show();
				}
			}else{
				if(excutedPause[i]==false){
					try{
						todoInterface[i].onDexPause();
					}catch(Exception e){
						Toast.makeText(this,"第"+i+"个出现异常"+e.toString(),Toast.LENGTH_LONG).show();
					}
					excutedPause[i]=true;
				}
			}
		}
	}




}
