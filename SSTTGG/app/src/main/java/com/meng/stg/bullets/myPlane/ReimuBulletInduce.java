package com.meng.stg.bullets.myPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.BaseMyBullet;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;
import com.meng.stg.ui.MainScreen;

public class ReimuBulletInduce extends BaseMyBullet{
    private Vector2 nearestEnemyPosition=Vector2.Zero;
    private Vector2 mTarget=Vector2.Zero;

    @Override
    public Drawable getDrawable(){
        return null;
    }

    @Override
    public void update(){
        super.update();
        nearestEnemyPosition=getNearestEnemy();
        objectCenter.add(nearestEnemyPosition.sub(objectCenter).nor().scl(12));
    }

    @Override
    public float getRotationDegree(){
        return velocity.angle()+90;
    }

    @Override
    public Vector2 getSize(){
        return null;
    }

    public Vector2 getNearestEnemy(){
        Vector2 vct2_tmp1=new Vector2(1000,1000);
        for(BaseEnemyPlane bep : MainScreen.enemys){
            if(bep.getLocation().sub(objectCenter).len2()<vct2_tmp1.sub(objectCenter).len2()){
                vct2_tmp1=bep.getLocation();
            }
        }
        return vct2_tmp1;
    }
}
