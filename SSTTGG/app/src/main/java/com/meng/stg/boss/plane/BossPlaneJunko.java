package com.meng.stg.boss.plane;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.boss.BaseBossPlane;
import com.meng.stg.boss.danmaku.Junko.normal1;
import com.meng.stg.boss.danmaku.Junko.spell1;
import com.meng.stg.boss.partAgent;
import com.meng.stg.item.BaseItem;
import com.meng.stg.item.ItemType;
import com.meng.stg.ui.MainScreen;

public class BossPlaneJunko extends BaseBossPlane{

    private final int[][] junkoAnim=new int[][]{
            {10,14},
            {5,8}
    };

    @Override
    public void update(){
        super.update();
        //  moveManager.update();
        //  am.update();
        //    objectCenter.set(193,350);
        if(hp>4000){
            MainScreen.normalMode();
            normalDanmaku.update();
        }else{
            MainScreen.spellMode();
            spellCard.update();
        }
    }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp){
        super.init(center,everyAnimFrameTime,hp);
        //   BulletRemover.killAllBullet();
        objectName="chunhu";
        targetPosition=center.cpy();
        this.everyAnimFrameTime=everyAnimFrameTime;
        animNum=junkoAnim;
        normalDanmaku=new normal1();
        normalDanmaku.init(this);
        spellCard=new spell1();
        spellCard.init(this);
        MainScreen.instence.layoutManager.nextPart.add(new partAgent(4000));
    }

    @Override
    public void kill(){
        super.kill();
        //	MainScreen.sleep=90;
        MainScreen.normalMode();
        BaseItem.create(objectCenter.cpy(),ItemType.power);
        new BossPlaneJunko2().init(objectCenter,10,7000);
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
    }

}
