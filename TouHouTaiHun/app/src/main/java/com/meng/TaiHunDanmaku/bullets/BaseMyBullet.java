package com.meng.TaiHunDanmaku.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.TaiHunDanmaku.ui.FightScreen;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

import static com.meng.TaiHunDanmaku.ui.FightScreen.enemys;


public abstract class BaseMyBullet extends BaseBullet{

    public static HashSet<BaseMyBullet> instances=new HashSet<BaseMyBullet>();
    public static LinkedBlockingQueue<BaseMyBullet> toDelete=new LinkedBlockingQueue<BaseMyBullet>();
    public static LinkedBlockingQueue<BaseMyBullet> toAdd=new LinkedBlockingQueue<BaseMyBullet>();

    public abstract Drawable getDrawable();

    public void init(Vector2 center,Vector2 velocity){
        super.init();
        toAdd.add(this);
        objectCenter.set(center);
        this.velocity.set(velocity);
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        judgeCircle=new Circle(objectCenter,image.getHeight()/2*3); //中心、半径
        image.setDrawable(getDrawable());
        FightScreen.groupNormal.addActor(image);
    }

    @Override
    public void killByOutOfScreen(){
        super.killByOutOfScreen();
        toDelete.add(this);
        image.remove();
	  }

	@Override
	public void update(){
		objectCenter.add(velocity);
		super.update();
	  }

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
        }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
        }
        for(BaseMyBullet baseBullet : instances){
            baseBullet.update();
        }
    }

    public void judge(){
        try{
            for(int i=0;i<32;i++){
                if(enemys[i]!=null){
                    if(((Circle)getCollisionArea()).overlaps(((Circle)enemys[i].getJudgeCircle()))){
                        killByOutOfScreen();
                        enemys[i].hit(10.5f);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
