package com.meng.stg.backup.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.MainScreen;

import static com.meng.stg.MainScreen.enemys;

/**
 * Created by Administrator on 2019/1/25.
 */

public abstract class BaseMyPlaneBullet extends BaseBullet{

    public abstract Drawable getDrawable();

    public void Init(Vector2 center,Vector2 velocity){
        super.Init();
        Size.set(20,80);
        Drawer.setSize(Size.x,Size.y);
        Center.set(center);
        Velocity.set(velocity);
        Drawer.setPosition(center.x,center.y,Align.center);
        judgeCircle=new Circle(Center,Drawer.getHeight()/3*2); //中心、半径
        MainScreen.MainGroup.addActor(Drawer);
    }

    public void Judge(){
        try{
            for(int i=0;i<32;i++){
                if(!(enemys[i]==null)){
                    if(((Circle)getCollisionArea()).overlaps(((Circle)enemys[i].getJudgeCircle()))){
                        Kill();
                        enemys[i].hit();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
