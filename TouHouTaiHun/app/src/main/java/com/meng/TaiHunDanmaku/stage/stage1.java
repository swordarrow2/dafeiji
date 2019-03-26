package com.meng.TaiHunDanmaku.stage;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.boss.plane.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;

public class stage1{

    public static void addEnemy(){
        switch(MainScreen.gameTime){
            case 30:
			  new BossPlaneJunko().init(new Vector2(193,350),10,20000,new Task[]{ new TaskMove(193,250) });
			  MainScreen.onBoss=true;
			  break;
		  }
	  }
  }
  
