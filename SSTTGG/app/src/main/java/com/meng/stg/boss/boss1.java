package com.meng.stg.boss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.meng.stg.MainScreen;
import com.meng.stg.bullets.SimpleRedBullet;
import com.meng.stg.enemy.BaseEnemy;
import com.meng.stg.helpers.Resources;

import java.util.HashMap;

public class boss1 extends BaseEnemy {

    private boolean Killed = false;
    private int time = 0;
    private float vx = 0;
    private float vy = 0;
    private boolean xx = false;
    private boolean yy = false;
    public HashMap<String, Drawable> bossAnim = new HashMap<String, Drawable>(15);
    TextureRegion[][] tmp;
    Texture walkSheet;
    int FRAME_COLS =5;
    int FRAME_ROWS = 3;
    Drawable d=null;
    //public static int missTime=0;
    @Override
    protected Drawable getDrawable() {
        //return Resources.NPCDrawables.get("enemy2");

        switch (animTime % 48) {
           // case 3: //case 2:case 3:case 4:
           //    d= bossAnim.get("anim0");
           //     break;
            case 15://case 6:case 7:case 8:
                d= bossAnim.get("anim1");
                break;
         //   case 27://case 10:case 11:case 12:
          //      d= bossAnim.get("anim2");
          //      break;
            case 39://case 14:case 15:case 0:
                d= bossAnim.get("anim3");
                break;
          //  default:
           //    d= bossAnim.get("anim7");
        }
        return d;
    }

    protected Drawable getRightMoveAnim() {
        switch (animTime % 48) {
            // case 3: //case 2:case 3:case 4:
            //    d= bossAnim.get("anim0");
            //     break;
            case 15://case 6:case 7:case 8:
                d= bossAnim.get("anim6");
                break;
            //   case 27://case 10:case 11:case 12:
            //      d= bossAnim.get("anim2");
            //      break;
            case 39://case 14:case 15:case 0:
                d= bossAnim.get("anim9");
                break;
            //  default:
            //    d= bossAnim.get("anim7");
        }
        return d;

    }

    protected Drawable getLeftMoveAnim() {
        switch (animTime % 48) {
            // case 3: //case 2:case 3:case 4:
            //    d= bossAnim.get("anim0");
            //     break;
            case 15://case 6:case 7:case 8:
                d= bossAnim.get("anim14");
                break;
            //   case 27://case 10:case 11:case 12:
            //      d= bossAnim.get("anim2");
            //      break;
            case 39://case 14:case 15:case 0:
                d= bossAnim.get("anim11");
                break;
            //  default:
            //    d= bossAnim.get("anim7");
        }
        return d;

    }

    protected Shape2D getCollisionArea() {
        return judgeCircle;
    }
    public Circle getJudgeCircle(){
        return judgeCircle;
    }
    public boolean isKilled() {
        return Killed;
    }

    public boss1 createEnemy(float x, float y, float vx, float vy) {
        super.Init();
        enemyLastX=x;
        isEnemy = true;
        Killed = false;
        walkSheet = new Texture(Gdx.files.internal("textures/boss/boss1.png"));
        tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                TextureRegionDrawable drawable = new TextureRegionDrawable(tmp[i][j]);
                bossAnim.put("anim" + index, drawable);
                index++;
            }
        }
        Drawer.setSize(128, 128);
        Center.set(x, y);
        this.vx = vx;
        this.vy = vy;
        //   Init();
        hp = 200;
        judgeCircle = new Circle(Center, 32); //中心、半径
        MainScreen.MainGroup.addActor(Drawer);
        return this;
    }
    public boss1 createEnemy(float x, float y, float vx, float vy,int hp){
        super.Init();
        enemyLastX=x;
        isEnemy = true;
        Killed = false;
        walkSheet = new Texture(Gdx.files.internal("textures/boss/boss1.png"));
        tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                TextureRegionDrawable drawable = new TextureRegionDrawable(tmp[i][j]);
                bossAnim.put("anim" + index, drawable);
                index++;
            }
        }
        Drawer.setSize(128, 128);
        Center.set(x, y);
        this.vx = vx;
        this.vy = vy;
        //   Init();
        this.hp=hp;
        judgeCircle = new Circle(Center, 32); //中心、半径
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
if(!MainScreen.gameOver) {
    if (Center.x > 500) {
        xx = true;
    } else if (Center.x < 50) {
        xx = false;
    }
    if (xx) {
        Center.x += vx;
    } else {
        Center.x -= vx;
    }

    if (Center.y > 700) {
        yy = true;
    } else if (Center.y < 400) {
        yy = false;
    }
    if (yy) {
        Center.y += vy;
    } else {
        Center.y -= vy;
    }

}
    animTime++;
        if (Center.x > enemyLastX) {
            enemyLastX= Center.x;
            Drawer.setDrawable(getRightMoveAnim());

        } else if (Center.x < enemyLastX) {
            enemyLastX = Center.x;
            Drawer.setDrawable(getLeftMoveAnim());
        } else {
            enemyLastX = Center.x;
            Drawer.setDrawable(getDrawable());
        }
        time++;
        //   if (time % 21 == 1) {
        //       Vector2 vel = new Vector2(0, -5);
        //      SimpleRedBullet sb = new SimpleRedBullet();
        //     sb.createBullet(Center, vel);
        //  }

if(!MainScreen.gameOver) {
    if (time % 30 == 1) {
        int randVal = MathUtils.random(0, 360);
        Vector2 vel = new Vector2(3, 0);
        vel.rotate(randVal);
        for (int i = 0; i < 12; i++) {
            SimpleRedBullet sb = new SimpleRedBullet();
            sb.createBullet(Center, vel);
            vel.rotate(30);
        }
    }
}
        super.Update();
    }


    @Override
    public void Kill() {
            Killed = true;
            MainScreen.onBoss = false;
            int randVal = MathUtils.random(0, 360);
            Vector2 vel = new Vector2(15, 0);
            vel.rotate(randVal);
            for (int i = 0; i < 24; i++) {
                SimpleRedBullet sb = new SimpleRedBullet();
                sb.createBullet(Center, vel);
                vel.rotate(15);
            }
            //	missTime++;
            super.Kill();

    }

}
