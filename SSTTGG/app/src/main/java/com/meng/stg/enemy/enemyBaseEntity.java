package com.meng.stg.enemy;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.MainScreen;
import com.meng.stg.helpers.Pools;
import com.meng.stg.player.Player;

public abstract class enemyBaseEntity {
    public boolean isEnemy = true;
    public float enemyLastX;
    public int animTime=0;
    public Vector2 Velocity = new Vector2();
    public Image Drawer = null;
    protected Rectangle drawBox = new Rectangle();
    public Circle judgeCircle;
    public int delay = 0;
    public Vector2 Center = new Vector2();
    public int hp=10;
    public void Init() {
        //获取一个Image

        Drawer = Pools.ImagePool.obtain();
        Drawable drawable = getDrawable();
        //设置材质
        Drawer.setDrawable(drawable);
        //加入舞台中



    }
    public int getHp(){
        return hp;
    }
public void hit(){

    if (!MainScreen.gameOver) {
    hp--;
    if(hp<1){
        Kill();
    }}
}
public Vector2 getLocation(){
    return Center;
}
    public void Kill() {
        Drawer.remove();
        judgeCircle=null;
        //将Drawer从舞台上撤下并且将其回归到Pool中
        Pools.ImagePool.free(Drawer);
    }

    public void Update() {
        //更新位置
        //    Center.add(Velocity);
            Drawer.setPosition(Center.x, Center.y, Align.center);
        judgeCircle.setPosition(Center.x, Center.y);
        drawBox.set(Drawer.getX(), Drawer.getY(), Drawer.getWidth(), Drawer.getHeight());
        if (!drawBox.overlaps(MainScreen.FightArea)) {
            Kill();
        } else {
            Judge();
        }

    }
    public Shape2D getJudgeCircle(){
        return judgeCircle;
    }
    protected abstract Shape2D getCollisionArea();

    public void Judge() {
        if (getCollisionArea().contains(Player.Instance.Center)) {
            hit();
            Player.Instance.Kill();
        }
    }

    protected abstract Drawable getDrawable();

    public boolean isKilled() {
        return true;
    }
}
