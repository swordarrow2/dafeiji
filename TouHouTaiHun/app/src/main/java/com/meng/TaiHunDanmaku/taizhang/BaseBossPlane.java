package com.meng.TaiHunDanmaku.taizhang;

import com.badlogic.gdx.math.Vector2;
import com.meng.TaiHunDanmaku.taizhang.danmaku.BaseNormalDanmaku;
import com.meng.TaiHunDanmaku.taizhang.danmaku.BaseSpellCard;
import com.meng.TaiHunDanmaku.planes.enemyPlane.BaseEnemyPlane;
import com.meng.TaiHunDanmaku.ui.*;
import com.meng.TaiHunDanmaku.task.*;

public abstract class BaseBossPlane extends BaseEnemyPlane {

    public static BaseBossPlane instence;
    public BaseNormalDanmaku normalDanmaku;
    public BaseSpellCard spellCard;

    @Override
    public abstract Vector2 getSize();

    @Override
    public void shoot() {
    }

    @Override
    public void init(Vector2 center, int everyAnimFrameTime, int hp, Task[] bmm) {
        super.init(center, everyAnimFrameTime, hp, bmm);
        FightScreen.instence.bossMaxHp = hp;
        instence = this;
    }

    @Override
    public void kill() {
        super.kill();
        FightScreen.lasers.clear();
        FightScreen.reflexAndThroughs.clear();
    }

}
