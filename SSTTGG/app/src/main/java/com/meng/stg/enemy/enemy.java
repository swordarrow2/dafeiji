package com.meng.stg.enemy;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.MainScreen;
import com.meng.stg.bullets.SimpleRedBullet;
import com.meng.stg.helpers.Resources;
import com.meng.stg.*;

public class enemy extends BaseEnemy {

    private boolean Killed = false;
    private int time = 0;
    private float vx = 0;
    private float vy = 0;

    //public static int missTime=0;
    @Override
    protected Drawable getDrawable() {
        return Resources.Textures.get("enemy");
    }

    protected Shape2D getCollisionArea() {
        return judgeCircle;
    }
public Shape2D getJudgeCircle(){
    return judgeCircle;
}
    public boolean isKilled() {
        return Killed;
    }

    public enemy createEnemy(float x, float y, float vx, float vy) {
        super.Init();
        isEnemy = true;
        Killed = false;
        Drawer.setSize(64, 64);
        Center.set(x, y);
        this.vx = vx;
        this.vy = vy;
        //   Init();
        judgeCircle = new Circle(Center, Drawer.getWidth() / 2); //中心、半径
        MainScreen.MainGroup.addActor(Drawer);
        return this;
    }

    @Override
    public void Init() {
        //  this.isEnemy=true;
        //  Killed=false;

        //	super.Init();

        //	Drawer.setSize(32, 32);
        //	this.Center.set(50, 720);
    }

    @Override
    public void Update() {
		Main.SBatch.begin();
		MainScreen. f.draw(Main.SBatch, "HP:"+hp, Center.x+20, Center.y);
		Main.SBatch.end();
        if (!MainScreen.gameOver) {
            Center.x += vx;
            Center.y += vy;
        }
        time++;
        if (time % 21 == 1) {
            Vector2 vel = new Vector2(0, -5);
            SimpleRedBullet sb = new SimpleRedBullet();
           sb.createBullet(Center, vel);
        }
        super.Update();
    }



    @Override
    public void Kill() {
        Killed = true;
        //	missTime++;
        super.Kill();
    }


}
