package com.meng.stg.bullets;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.player.BaseMyPlane;

public abstract class BaseEnemyBullet extends BaseBullet{
    public abstract Drawable getDrawableJavaBean() ;

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
    public void Update(){
        super.Update();
    }

    @Override
    public void Judge(){
        if(judgeCircle.contains(BaseMyPlane.Instance.Center)){
            Kill();
            BaseMyPlane.Instance.Kill();
        }
    }

    @Override
    public float getRotationDegree(){
        return 0;
    }
}
