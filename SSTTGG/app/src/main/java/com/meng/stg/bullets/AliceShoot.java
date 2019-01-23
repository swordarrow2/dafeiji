package com.meng.stg.bullets;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.*;
import com.meng.stg.bullets.*;
import com.meng.stg.helpers.*;

import static com.meng.stg.MainScreen.enemys;

public class AliceShoot extends Projectile {
   public static Pool<AliceShoot> Pool = new Pool<AliceShoot>() {
        @Override
        protected AliceShoot newObject() {
            return new AliceShoot();
        }
    };

    static Drawable drawable;
    public Vector2 Size = new Vector2();

    public void Init(Vector2 center, Vector2 velocity) {
        super.Init();
        this.isEnemyBullet=false;
        Size.set(24, 48);
        Drawer.setSize(Size.x, Size.y);
        Center.set(center);
        Velocity.set(velocity);
        Drawer.setPosition(center.x, center.y, Align.center);

        judgeCircle = new Circle(Center, Drawer.getWidth() / 2); //中心、半径
        MainScreen.MainGroup.addActor(Drawer);
    }

    @Override
    public void Update() {
        super.Update();
        Drawer.toBack();
        judgeCircle.setPosition(Center);
    }
    public AliceShoot createBullet(Vector2 center, Vector2 velocity){
        Pool.obtain(). Init(center,velocity);
        return this;
    }
    @Override
    protected Shape2D getCollisionArea() {
        return judgeCircle;
    }

    @Override
    protected Drawable getDrawable() {
        if (drawable == null) {
            //   if (bullet.color==bullet.colorRed) {
            drawable = Resources.ProjDrawables.get("AliceShoot");
            //   }
        }
        return drawable;
    }

    public void Judge() {
        try {
            for(int i=0;i<32;i++){
                if(!(enemys[i]==null)) {
                    if (((Circle)getCollisionArea()).overlaps(((Circle)enemys[i].getJudgeCircle()))) {
                        Kill();
                        enemys[i].hit();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
