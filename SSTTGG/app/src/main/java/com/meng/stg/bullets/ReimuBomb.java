package com.meng.stg.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.MainScreen;
import com.meng.stg.helpers.Resources;
import com.meng.stg.player.Player;

import static com.meng.stg.MainScreen.enemys;

public class ReimuBomb extends Projectile {
 //   public static Pool<AliceShoot> Pool = new Pool<AliceShoot>() {
  //      @Override
  //      protected AliceShoot newObject() {
   //         return new AliceShoot();
   //     }
   // };

    static Drawable drawable;
    public Vector2 Size = new Vector2();

    public void Init(Vector2 center, Vector2 velocity) {
        super.Init();
        this.isEnemyBullet=false;
        Size.set(20, 80);
        Drawer.setSize(Size.x, Size.y);
        Center.set(center);
        Velocity.set(velocity);
        Drawer.setPosition(center.x, center.y, Align.center);

        judgeCircle = new Circle(Center, Drawer.getHeight() / 3*2); //中心、半径
        MainScreen.MainGroup.addActor(Drawer);
    }

    @Override
    public void Update() {
        super.Update();
        judgeCircle.setPosition(Center);
    }
    public ReimuBomb createBullet(Vector2 center, Vector2 velocity){
        Init(center,velocity);
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
            drawable = Resources.ProjDrawables.get("ReimuBomb");
            //   }
        }
        return drawable;
    }
private boolean ifJudgeCircleMeet(Shape2D s1,Shape2D s2){
    Circle c1=(Circle)s1;
    Circle c2=(Circle)s2;
    float circle1x=c1.x;
    float circle1y=c1.y;
    float circle1r=c1.radius;
    float circle2x=c2.x;
    float circle2y=c2.y;
    float circle2r=c2.radius;

    if((Math.pow((circle2x-circle1x),2)+Math.pow((circle2y-circle1y),2))>(circle1r+circle2r)){
return false;
    }else {
        return true;
    }


}
    public void Judge() {
        try {
            for(int i=0;i<32;i++){
                if(!(enemys[i]==null)) {
                    if (((Circle)getCollisionArea()).overlaps(((Circle)enemys[i].getJudgeCircle()))) {
                        enemys[i].hit();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
