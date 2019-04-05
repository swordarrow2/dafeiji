package com.meng.TaiHunDanmaku.stage;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.taizhang.body.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;

public class stage1{

    public static void addEnemy(){
        switch(FightScreen.enemyTimeFlag){
            case 30:
			  new BossTaiZhang3().init(new Vector2(193,350),10,2700,new Task[]{ new TaskMoveTo(193,250) });
			  FightScreen.onBoss=true;
			  break;
		  }
	  }
  }
  
