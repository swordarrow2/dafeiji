package com.meng.stg.bullets;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.player.BaseMyPlane;

/**
 * Created by Administrator on 2019/1/25.
 */

public abstract class BaseEnemyBullet extends BaseBullet{
    public abstract Drawable getDrawable() ;

    @Override
    public void Init(){
        super.Init();
        bullet.bulletCount++;
    }

    @Override
    public void Kill(){
        super.Kill();
        bullet.bulletCount--;
    }

    @Override
    public void Judge(){
        if(judgeCircle.contains(BaseMyPlane.Instance.Center)){
            Kill();
            BaseMyPlane.Instance.Kill();
        }
    }
}
