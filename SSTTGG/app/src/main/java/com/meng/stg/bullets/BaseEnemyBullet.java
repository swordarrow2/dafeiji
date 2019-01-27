package com.meng.stg.bullets;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.myPlane.BaseMyPlane;

public abstract class BaseEnemyBullet extends BaseBullet{
    public abstract Drawable getDrawable() ;
    public static int bulletCount=0;
    @Override
    public void Init(){
        super.Init();
        bulletCount++;
    }

    @Override
    public void Kill(){
        super.Kill();
        bulletCount--;
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
        return 180;
    }
}
