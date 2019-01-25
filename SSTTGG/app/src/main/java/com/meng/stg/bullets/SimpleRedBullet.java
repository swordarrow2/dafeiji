package com.meng.stg.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.MainScreen;
import com.meng.stg.helpers.Resources;

public class SimpleRedBullet extends BaseEnemyBullet{

    public static Pool<SimpleRedBullet> Pool=new Pool<SimpleRedBullet>(){
        @Override
        protected SimpleRedBullet newObject(){
            return new SimpleRedBullet();
        }
    };

    public void Init(Vector2 center,Vector2 velocity){
        drawable=Resources.Textures.get("SimpleRedBullet");
        super.Init();
        Size.set(24,24);
        Drawer.setSize(Size.x,Size.y);
        Center.set(center);
        Velocity.set(velocity);
        Drawer.setPosition(center.x,center.y,Align.center);
        judgeCircle=new Circle(Center,Drawer.getWidth()/2); //中心、半径
        MainScreen.MainGroup.addActor(Drawer);
    }

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=Resources.Textures.get("SimpleRedBullet");
        }
        return drawable;
    }

}
