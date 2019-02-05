package com.meng.stg.boss.plane;

import com.badlogic.gdx.math.*;
import com.meng.stg.boss.*;
import com.meng.stg.boss.danmaku.*;
import com.meng.stg.bullets.*;
import com.meng.stg.move.*;
import com.meng.stg.ui.*;
import com.meng.stg.item.item.*;

public class BossPlaneJunko extends BaseBossPlane{

    private final int[][] junkoAnim=new int[][]{
		  {10,14},
		  {5,9}
	  };

    @Override
    public void update(){
        super.update();
        //  moveManager.update();
        //  am.update();
        objectCenter.set(193,350);
	  }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp,BaseMoveMethod... bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
        BaseBullet.killAllBullet();
        objectName="chunhu";
        this.everyAnimFrameTime=everyAnimFrameTime;
        animNum=junkoAnim;
        spellCard=new Junko_1_danmaku();
        spellCard.init(this);
	  }

	@Override
	public void kill(){
		super.kill();
		MainScreen.sleep=90;
		MainScreen.normalMode();
		BaseEnemyBullet.killAllBullet();
		EnemyItem.create(objectCenter.cpy(),ItemType.power);
		new BossPlaneJunko2().init(objectCenter,10,7000,new MoveMethodStraight(1,1,new Vector2(0,0.0001f)));
	  }


    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
	  }


  }
