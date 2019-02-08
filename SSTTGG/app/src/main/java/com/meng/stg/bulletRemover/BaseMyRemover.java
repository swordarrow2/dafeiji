package com.meng.stg.bulletRemover;


import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.ui.MainScreen;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

import static com.meng.stg.ui.MainScreen.enemys;

public abstract class BaseMyRemover extends BaseRemover{

    public static HashSet<BaseMyRemover> instances=new HashSet<BaseMyRemover>();
    public static LinkedBlockingQueue<BaseMyRemover> toDelete=new LinkedBlockingQueue<BaseMyRemover>();
    public static LinkedBlockingQueue<BaseMyRemover> toAdd=new LinkedBlockingQueue<BaseMyRemover>();

    public abstract Drawable getDrawable();

    public void init(Vector2 center,Vector2 velocity){
        super.init();
        toAdd.add(this);
        objectCenter.set(center);
        this.velocity.set(velocity);
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        judgeCircle=new Circle(objectCenter,image.getHeight()/2*3); //中心、半径
        image.setDrawable(getDrawable());
        MainScreen.mainGroup.addActor(image);
    }

    @Override
    public void kill(){
        super.kill();
        toDelete.add(this);
        image.remove();
    }

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
        }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
        }
        for(BaseMyRemover baseBullet : instances){
            baseBullet.update();
        }
    }

    public void judge(){
        try{
            for(int i=0;i<32;i++){
                if(enemys[i]!=null){
                    if(((Circle)getCollisionArea()).overlaps(((Circle)enemys[i].getJudgeCircle()))){
                        kill();
                        enemys[i].hit(10.5f);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
