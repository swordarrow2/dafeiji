package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.*;
import com.meng.TaiHunDanmaku.baseObjects.planes.enemyPlane.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.*;

public class spell5 extends BaseSpellCard {

    public spell5() {
    }

    public void init(BaseBossPlane b) {
        boss = b;
        waitFrameSpell = 120;
        spellName = "台符「纯粹的台混天堂」";
        shooters = new BulletShooter[]{
                new BulletShooter().init()
                        .setEnemyPlane(boss)
                        .setShooterCenter(boss.objectCenter)
                        .setBulletColor(BulletColor.red)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(28)
                        .setBulletWaysDegree(12.8571429f)
                        .setBulletVelocity(new Vector2(0, -2))
                        .setShooterCenterRandomRange(48, 48),
                new BulletShooter().init()
                        .setEnemyPlane(boss)
                        .setShooterCenter(boss.objectCenter)
                        .setShootCenterOffset(new Vector2(-120, -30))
                        .setBulletColor(BulletColor.purple)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(10)
                        .setBulletWaysDegree(36)
                        .setBulletRandomDegreeRange(360)
                        .setBulletVelocity(new Vector2(0, -0.7f))
                        .setShooterCenterRandomRange(64, 64),
                new BulletShooter().init()
                        .setEnemyPlane(boss)
                        .setShooterCenter(boss.objectCenter)
                        .setShootCenterOffset(new Vector2(120, -30))
                        .setBulletColor(BulletColor.purple)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(10)
                        .setBulletWaysDegree(36)
                        .setBulletRandomDegreeRange(360)
                        .setBulletVelocity(new Vector2(0, -0.7f))
                        .setShooterCenterRandomRange(64, 64),
                new BulletShooter().init()
                        .setEnemyPlane(boss)
                        .setShooterCenter(boss.objectCenter)
                        .setShootCenterOffset(new Vector2(-160, 0))
                        .setBulletColor(BulletColor.blue)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(52)
                        .setBulletWaysDegree(6.92307692f)
                        .setBulletRandomDegreeRange(360)
                        .setBulletVelocity(new Vector2(0, -2.5f))
                        .setShooterCenterRandomRange(64, 64),
                new BulletShooter().init()
                        .setEnemyPlane(boss)
                        .setShooterCenter(boss.objectCenter)
                        .setShootCenterOffset(new Vector2(160, 0))
                        .setBulletColor(BulletColor.blue)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(52)
                        .setBulletWaysDegree(6.92307692f)
                        .setBulletRandomDegreeRange(360)
                        .setBulletVelocity(new Vector2(0, -2.5f))
                        .setShooterCenterRandomRange(64, 64),
                new BulletShooter().init()
                        .setEnemyPlane(boss)
                        .setShooterCenter(boss.objectCenter)
                        .setBulletColor(BulletColor.yellow_dark)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(24)
                        .setBulletWaysDegree(15)
                        .setBulletVelocity(new Vector2(0, -4))
        };
    }

    @Override
    public void update() {
        super.update();
        boss.moveTo(193, 350);
        if (waitFrameSpell-- > 0) return;
        
        if (boss.existTime % 20 == 0) {
            shooters[0].shoot();
        }
        if (boss.hp > 5500) return;
        if (boss.existTime % 30 == 0) {
            shooters[1].shoot();
            shooters[2].shoot();
        }
        if (boss.hp > 3500) return;
        if (boss.existTime % 60 == 0) {
            shooters[3].shoot();
            shooters[4].shoot();
        }
        if (boss.hp > 1200) return;
        if (boss.existTime % 10 == 0) {
            shooters[5].shoot();
        }
    }
}
