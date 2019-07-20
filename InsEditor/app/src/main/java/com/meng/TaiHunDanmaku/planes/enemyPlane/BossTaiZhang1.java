package com.meng.TaiHunDanmaku.planes.enemyPlane;

import com.meng.TaiHunDanmaku.danmaku.taizhang.normal1;
import com.meng.TaiHunDanmaku.danmaku.taizhang.spell5;
import com.meng.TaiHunDanmaku.task.Task;

public class BossTaiZhang1 extends BaseBossPlane {

    private final int[][] junkoAnim=new int[][]{
            {10,14},
            {5,8}
    };

    @Override
    public void update() {
        super.update();
        //  moveManager.update();
        //  am.update();
        //    objectCenter.set(193,350);
        //		if (hp > 4000) {
        //		normalDanmaku.update();
        //	  } else {
        spellCard.update();
        //	  }
    }

    @Override
    public void init(Vector2 center, int everyAnimFrameTime, int hp, Task[] bmm) {
        super.init(center, everyAnimFrameTime, hp, bmm);
        //   BulletRemover.killAllBullet();
        objectName = "chunhu";
        targetPosition = center.cpy();
        this.everyAnimFrameTime = everyAnimFrameTime;
        animNum = junkoAnim;
        normalDanmaku = new normal1();
        normalDanmaku.init(this);
        spellCard = new spell5();
        spellCard.init(this);

    }

    @Override
    public void kill() {
        super.kill();
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(96, 128);
    }
}
