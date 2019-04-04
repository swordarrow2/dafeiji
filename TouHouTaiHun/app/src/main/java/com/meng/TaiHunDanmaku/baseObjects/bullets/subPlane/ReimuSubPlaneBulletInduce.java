package com.meng.TaiHunDanmaku.baseObjects.bullets.subPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.baseObjects.planes.enemyPlane.*;
import com.meng.TaiHunDanmaku.baseObjects.planes.myPlane.*;
import com.meng.TaiHunDanmaku.ui.*;

import static com.meng.TaiHunDanmaku.ui.FightScreen.enemys;

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
		if(judgeCircle.x<-50||judgeCircle.x>4400
		   ||judgeCircle.y<-50||judgeCircle.y>510){         
			killByOutOfScreen();
		  }else{		
			judge();
		  }

        nearestEnemyPosition.set(noEnemy);
        for(BaseEnemyPlane bep : FightScreen.enemys){
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
	  
	public void judge(){
        try{
            for(int i=0;i<32;i++){
                if(enemys[i]!=null){
                    if(((Circle)getCollisionArea()).overlaps(((Circle)enemys[i].getJudgeCircle()))){
                        enemys[i].hit(BaseMyPlane.instance.slow?36.5f:30.5f);
                        killByOutOfScreen();
					  }
				  }
			  }
		  }catch(Exception e){
            e.printStackTrace();
		  }
	  }
    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
	  }

	@Override
	public void killByOutOfScreen(){
		super.killByOutOfScreen();
        ObjectPools.reimuSubPlaneBulletInducePool.free(this);
	  }

  }
