package com.meng.stg.boss.plane;

import com.badlogic.gdx.math.*;
import com.meng.stg.boss.*;
import com.meng.stg.boss.danmaku.Junko.*;
import com.meng.stg.bullets.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.move.*;
import com.meng.stg.ui.*;

public class BossPlaneJunko2 extends BaseBossPlane{

    private final int[][] junkoAnim=new int[][]{
		  {10,14},
		  {5,9}
	  };
    int wf=200;

    @Override
    public void update(){
        super.update();
        if(wf-->0) return;
        MainScreen.spellMode();
        spellCard.update();
	  }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp,BaseMoveMethod... bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
        targetPosition=center.cpy();
        spellCard=new spell7();
        spellCard.init(this);
        objectName="chunhu";
        this.everyAnimFrameTime=everyAnimFrameTime;
        animNum=junkoAnim;
		MainScreen.instence.nextPart=1200;
	  }

    @Override
    public void hit(float bulletDamage){
        if(wf>0) return;
        super.hit(bulletDamage);
	  }

    @Override
    public void kill(){
        super.kill();
        BaseEnemyBullet.killAllBullet(BulletKillMode.KillOnBossLastDeath);
        MainScreen.onSpellCard=false;
        MainScreen.sleep=75;
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
	  }
  }
