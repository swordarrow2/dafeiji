package com.meng.stg.boss.danmaku;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.boss.BaseBossPlane;
import com.meng.stg.boss.BaseSpellCard;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.bullets.enemy.BulletShooter;

public class Spell_PurelyBulletHell extends BaseSpellCard{

    public Spell_PurelyBulletHell(){
    }

    public void init(BaseBossPlane b){
        boss=b;
        shooters=new BulletShooter[]{
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletCenter(boss.objectCenter)
                        .setBulletColor(BulletColor.red)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setWays(24)
                        .setWaysDegree(15)
                        .setInterval(30)
                        .setRandomCenter(64,64),
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletCenter(boss.objectCenter)
                        .setOffset(new Vector2(-120,0))
                        .setBulletColor(BulletColor.purple)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setWays(12)
                        .setWaysDegree(30)
                        .setInterval(60)
                        .setRandomDegree(360)
                        .setRandomCenter(64,64),
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletCenter(boss.objectCenter)
                        .setOffset(new Vector2(120,0))
                        .setBulletColor(BulletColor.purple)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setWays(12)
                        .setWaysDegree(30)
                        .setInterval(60)
                        .setRandomDegree(360)
                        .setRandomCenter(64,64),
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletCenter(boss.objectCenter)
                        .setOffset(new Vector2(-160,0))
                        .setBulletColor(BulletColor.blue)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setWays(40)
                        .setWaysDegree(9)
                        .setInterval(40)
                        .setRandomDegree(360)
                        .setRandomCenter(64,64),
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletCenter(boss.objectCenter)
                        .setOffset(new Vector2(160,0))
                        .setBulletColor(BulletColor.blue)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setWays(40)
                        .setWaysDegree(9)
                        .setInterval(40)
                        .setRandomDegree(360)
                        .setRandomCenter(64,64),
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletCenter(boss.objectCenter)
                        .setBulletColor(BulletColor.yellow_dark)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setWays(36)
                        .setWaysDegree(10)
                        .setInterval(8)
        };
        partFrame=new int[]{
                1000,1000,1000,1000,1000,1000
        };
        partHp=new float[]{
                7000,5000,5000,3000,3000,1000
        };
    }
}
