package com.meng.stg.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.MainScreen;

import static com.meng.stg.MainScreen.enemys;


public abstract class BaseMyPlaneBullet extends BaseBullet{

    public abstract Drawable getDrawable();

    public void Init(Vector2 center,Vector2 velocity){
        super.Init();
        Center.set(center);
        Velocity.set(velocity);
        Drawer.setPosition(Center.x,Center.y,Align.center);
		
        judgeCircle=new Circle(Center,Drawer.getWidth()/3*2); //中心、半径
		
        MainScreen.MainGroup.addActor(Drawer);
    }

    @Override
    public void Update(){
        super.Update();
		Drawer.setPosition(Center.x,Center.y,Align.center);
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
