package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.badlogic.gdx.math.Vector2;
import com.meng.TaiHunDanmaku.taizhang.BaseBossPlane;
import com.meng.TaiHunDanmaku.taizhang.danmaku.BaseSpellCard;
import com.meng.TaiHunDanmaku.bullets.BaseEnemyBullet;
import com.meng.TaiHunDanmaku.bullets.enemy.BulletColor;
import com.meng.TaiHunDanmaku.bullets.enemy.BulletForm;
import com.meng.TaiHunDanmaku.bullets.enemy.BulletKillMode;
import com.meng.TaiHunDanmaku.bullets.enemy.BulletShooter;

public class spell7 extends BaseSpellCard{

    public spell7(){
    }

    public void init(BaseBossPlane b){
        boss=b;
        waitFrameSpell=120;
        spellName="纯符「纯粹的弹幕地狱」";
        shooters=new BulletShooter[]{
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletShooterCenter(boss.objectCenter)
                        .setBulletColor(BulletColor.green)//red
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(28)
                        .setBulletWaysDegree(12.8571429f)
                        .setBulletVelocity(new Vector2(0,-2))
                        .setShooterCenterRandomRange(48,48),
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletShooterCenter(boss.objectCenter)
                        .setShootCenterOffset(new Vector2(-120,-30))
			.setBulletColor(BulletColor.green)//purple
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(10)
                        .setBulletWaysDegree(36)
                        .setRandomDegreeRange(360)
                        .setBulletVelocity(new Vector2(0,-0.7f))
                        .setShooterCenterRandomRange(64,64),
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletShooterCenter(boss.objectCenter)
                        .setShootCenterOffset(new Vector2(120,-30))
			.setBulletColor(BulletColor.green)//purple
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(10)
                        .setBulletWaysDegree(36)
                        .setRandomDegreeRange(360)
                        .setBulletVelocity(new Vector2(0,-0.7f))
                        .setShooterCenterRandomRange(64,64),
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletShooterCenter(boss.objectCenter)
                        .setShootCenterOffset(new Vector2(-160,0))
			.setBulletColor(BulletColor.green)//blue
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(52)
                        .setBulletWaysDegree(6.92307692f)
                        .setRandomDegreeRange(360)
                        .setBulletVelocity(new Vector2(0,-2.5f))
                        .setShooterCenterRandomRange(64,64),
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletShooterCenter(boss.objectCenter)
                        .setShootCenterOffset(new Vector2(160,0))
			.setBulletColor(BulletColor.green)//blue
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(52)
                        .setBulletWaysDegree(6.92307692f)
                        .setRandomDegreeRange(360)
                        .setBulletVelocity(new Vector2(0,-2.5f))
                        .setShooterCenterRandomRange(64,64),
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletShooterCenter(boss.objectCenter)
			.setBulletColor(BulletColor.green)//yellow_dark
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(24)
                        .setBulletWaysDegree(15)
                        .setBulletVelocity(new Vector2(0,-4))
        };
    }

    @Override
    public void update(){
        super.update();
        boss.moveTo(193,350);
        if(waitFrameSpell-->0) return;
        if(boss.hp<3540&&boss.hp>3500){
            BaseEnemyBullet.killAllBullet(BulletKillMode.killWithNothing);
        }
        if(boss.existTime%20==0){
            shooters[0].shoot();
        }
        if(boss.hp>5500) return;
        if(boss.existTime%30==0){
            shooters[1].shoot();
            shooters[2].shoot();
        }
        if(boss.hp>3500) return;
        if(boss.existTime%60==0){
            shooters[3].shoot();
            shooters[4].shoot();
        }
        if(boss.hp>1200) return;
        if(boss.existTime%10==0){
            shooters[5].shoot();
        }
    }
}
