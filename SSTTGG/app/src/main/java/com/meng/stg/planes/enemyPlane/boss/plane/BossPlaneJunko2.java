package com.meng.stg.planes.enemyPlane.boss.plane;

import com.badlogic.gdx.math.*;
import com.meng.stg.planes.enemyPlane.boss.*;
import com.meng.stg.planes.enemyPlane.boss.danmaku.*;
import com.meng.stg.bullets.*;
import com.meng.stg.move.*;
import com.meng.stg.ui.*;
import com.meng.stg.bullets.enemy.*;

public class BossPlaneJunko2 extends BaseBossPlane{

    private final int[][] junkoAnim=new int[][]{
		  {10,14},
		  {5,9}
	  };

    @Override
    public void update(){
        super.update();
        //  moveManager.update();
        //  am.update();
    //    objectCenter.set(193,350);
	MainScreen.spellMode();
	spellCard.update();
	  }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp,BaseMoveMethod... bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
   //     BaseBullet.killAllBullet();
		targetPosition=center.cpy();
		spellCard=new Junko_7_danmaku();
		spellCard.init(this);
        objectName="chunhu";
        this.everyAnimFrameTime=everyAnimFrameTime;
        animNum=junkoAnim;
        
	  }

	@Override
	public void kill(){
		super.kill();
		BaseEnemyBullet.killAllBullet(BulletKillMode.killWithScorePointAndCollect);
		MainScreen.onSpellCard=false;
		MainScreen.sleep=90;
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
	  }
  }
