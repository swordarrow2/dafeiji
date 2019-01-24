package com.meng.stg.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.MainScreen;
import com.meng.stg.helpers.Resources;

import static com.meng.stg.MainScreen.enemys;

public class AliceShoot extends BaseBullet{
    public static Pool<AliceShoot> Pool=new Pool<AliceShoot>(){
        @Override
        protected AliceShoot newObject(){
            return new AliceShoot();
        }
    };

    public void Init(Vector2 center,Vector2 velocity){
        super.Init();
        this.isEnemyBullet=false;
        Size.set(24,48);
        Drawer.setSize(Size.x,Size.y);
        Center.set(center);
        Velocity.set(velocity);
        Drawer.setPosition(center.x,center.y,Align.center);
        judgeCircle=new Circle(Center,Drawer.getWidth()/2); //中心、半径
        MainScreen.MainGroup.addActor(Drawer);
    }

    // @Override
    //  public void Update(){
    //     super.Update();
    //     Drawer.toBack();
    // }

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=Resources.Textures.get("AliceShoot");
        }
        return drawable;
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
