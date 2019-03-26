package com.meng.stg.stage;

import com.badlogic.gdx.math.*;
import com.meng.stg.boss.plane.*;
import com.meng.stg.task.*;
import com.meng.stg.ui.*;

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
  
