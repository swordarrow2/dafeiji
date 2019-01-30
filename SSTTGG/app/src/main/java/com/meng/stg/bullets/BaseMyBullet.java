package com.meng.stg.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.bullets.BaseBullet;
import com.meng.stg.ui.MainScreen;

import static com.meng.stg.ui.MainScreen.enemys;


public abstract class BaseMyBullet extends BaseBullet{

    public abstract Drawable getDrawable();

    public void Init(Vector2 center,Vector2 velocity){
        super.Init();
        objectCenter.set(center);
        this.velocity.set(velocity);
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        judgeCircle=new Circle(objectCenter,image.getWidth()/3*2); //中心、半径
        MainScreen.mainGroup.addActor(image);
    }

    public void judge(){
        try{
            for(int i=0;i<32;i++){
                if(!(enemys[i]==null)){
                    if(((Circle)getCollisionArea()).overlaps(((Circle)enemys[i].getJudgeCircle()))){
                        kill();
                        enemys[i].hit();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
