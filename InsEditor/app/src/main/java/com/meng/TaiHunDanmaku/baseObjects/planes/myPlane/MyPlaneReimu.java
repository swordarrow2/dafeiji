package com.meng.TaiHunDanmaku.baseObjects.planes.myPlane;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.baseObjects.planes.*;
import com.meng.TaiHunDanmaku.baseObjects.planes.subPlane.*;
import com.meng.TaiHunDanmaku.ui.*;

public class MyPlaneReimu extends BaseMyPlane {

    @Override
    public void shoot() {
        if (existTime % 3 == 1) {
            Vector2 vel = new Vector2(0, 47);
            ObjectPools.reimuShootPool.obtain().init(new Vector2(objectCenter.x + 8, objectCenter.y + 32), vel);
            ObjectPools.reimuShootPool.obtain().init(new Vector2(objectCenter.x - 8, objectCenter.y + 32), vel);
        }
    }

    @Override
    public void init(GameMain gameMain) {
        super.init(gameMain);
    
        animationManager = new AnimationManager(this, 5);
                                    subPlane4 = new SubPlaneReimuB();
                    subPlane4.init(this, 4);            
                    subPlane3 = new SubPlaneReimuB();
                    subPlane3.init(this, 3);               
                    subPlane2 = new SubPlaneReimuB();
                    subPlane2.init(this, 2);
                    subPlane1 = new SubPlaneReimuB();
                    subPlane1.init(this, 1); 
    }

    @Override
    public void kill() {
        FightScreen.instence.gameMain.miss++;
    }

    @Override
    public void update() {
        super.update();
                   subPlane4.update();           
                subPlane3.update();           
                subPlane2.update();        
                subPlane1.update();
    }

}
