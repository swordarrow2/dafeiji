package com.meng.stg.bullets.myPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.BaseMyBullet;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;
import com.meng.stg.ui.MainScreen;
import com.badlogic.gdx.utils.*;
import com.meng.stg.helpers.*;

public class ReimuBulletInduce extends BaseMyBullet{
    private Vector2 nearestEnemyPosition=new Vector2(0,1);
    private Vector2 mTarget=Vector2.Zero;
	public static Pool<ReimuBulletInduce> Pool=new Pool<ReimuBulletInduce>(){
        @Override
        protected ReimuBulletInduce newObject(){
            return new ReimuBulletInduce();
		  }
	  };
    @Override
    public Drawable getDrawable(){
        return ResourcesManager.textures.get("reimu31");
    }

    @Override
    public void update(){
        super.update();
		//velocity.set((velocity.x+getNearestEnemy().x)/2,(velocity.y+getNearestEnemy().y)/2);
        nearestEnemyPosition=getNearestEnemy();
        objectCenter.add(velocity).add(nearestEnemyPosition.sub(objectCenter).nor().scl(4));
    }

    @Override
    public float getRotationDegree(){
        return nearestEnemyPosition.angle();
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
    }

    public Vector2 getNearestEnemy(){
        Vector2 vct2_tmp1=new Vector2(1000,1000);
        for(BaseEnemyPlane bep : MainScreen.enemys){
            if(bep==null){
			  break;
			}
			if(bep.getLocation().cpy().sub(objectCenter).len2()<vct2_tmp1.cpy().sub(objectCenter).len2()){
                vct2_tmp1=bep.getLocation().cpy();
			  }
        }
        return vct2_tmp1;
    }
}
