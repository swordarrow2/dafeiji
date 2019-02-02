package com.meng.stg.bullets.subPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.bullets.BaseMyBullet;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.helpers.TextureNameManager;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;
import com.meng.stg.ui.MainScreen;

public class ReimuSubPlaneBulletInduce extends BaseMyBullet{

    private Vector2 nearestEnemyPosition=new Vector2(0,1);
    private final Vector2 targetOnEnemy=new Vector2(0,2);
    private final Vector2 noEnemy=new Vector2(100000,100000);

    @Override
    public Drawable getDrawable(){
        return ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletInduce);
    }

    @Override
    public void update(){
        image.setRotation(getRotationDegree());
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);    
        judgeCircle.setPosition(objectCenter);
		if(judgeCircle.x<-5||judgeCircle.x>390
		   ||judgeCircle.y<-5||judgeCircle.y>460){         
			kill();
		  }else{		
			judge();
		  }

        nearestEnemyPosition.set(noEnemy);
        for(BaseEnemyPlane bep : MainScreen.enemys){
            if(bep==null){
                continue;
            }
            if(bep.getLocation().cpy().sub(objectCenter).len2()<nearestEnemyPosition.cpy().sub(objectCenter).len2()){
                nearestEnemyPosition.set(bep.getLocation());
            }
        }
        if(nearestEnemyPosition.x==noEnemy.x&&nearestEnemyPosition.y==noEnemy.y){
            objectCenter.add(velocity);
        }else{
            objectCenter.add(targetOnEnemy).add(nearestEnemyPosition.sub(objectCenter).nor().scl(8));
        }
    }

    @Override
    public float getRotationDegree(){
        return nearestEnemyPosition.x==noEnemy.x&&nearestEnemyPosition.y==noEnemy.y?velocity.angle():nearestEnemyPosition.angle();
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
    }

}
