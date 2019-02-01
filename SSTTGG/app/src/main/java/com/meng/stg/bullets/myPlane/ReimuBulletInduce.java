package com.meng.stg.bullets.myPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.bullets.*;
import com.meng.stg.helpers.*;
import com.meng.stg.planes.enemyPlane.*;
import com.meng.stg.ui.*;

public class ReimuBulletInduce extends BaseMyBullet{
    private Vector2 nearestEnemyPosition=new Vector2(0,1);
	private Vector2 targetOnEnemy=new Vector2(0,2);
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
        image.setRotation(getRotationDegree());
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        drawBox.set(image.getX(),image.getY(),image.getWidth(),image.getHeight());
        judgeCircle.setPosition(objectCenter);
        if(!drawBox.overlaps(MainScreen.fightArea)){
            kill();
		  }else{
            judge();
		  }
        nearestEnemyPosition=getNearestEnemy();
		if(nearestEnemyPosition.equals(new Vector2(100000,100000))){
			objectCenter.add(velocity);
		  }else{	  
			objectCenter.add(targetOnEnemy).add(nearestEnemyPosition.sub(objectCenter).nor().scl(8));
		  }
	  }

    @Override
    public float getRotationDegree(){
        return nearestEnemyPosition.equals(new Vector2(100000,100000))?velocity.angle():nearestEnemyPosition.angle();
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
	  }

    public Vector2 getNearestEnemy(){
        Vector2 vct2_tmp1=new Vector2(100000,100000);
        for(BaseEnemyPlane bep : MainScreen.enemys){
            if(bep==null){
				continue;
			  }
			if(bep.getLocation().cpy().sub(objectCenter).len2()<vct2_tmp1.cpy().sub(objectCenter).len2()){
                vct2_tmp1=bep.getLocation().cpy();
			  }
		  }
        return vct2_tmp1;
	  }
  }
