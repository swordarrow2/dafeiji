package com.meng.stg;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.move.MoveManager;

/*
base game object
 */
public abstract class BaseGameObject {

    public Vector2 objectCenter=new Vector2();
    public Circle judgeCircle;
    public Image image=null;
    public int animFlag=0;
    public Drawable drawable=null;
    public MoveManager moveManager=null;
    public Vector2 velocity=new Vector2();
    public int existTime=0;
    public Vector2 size=new Vector2();

    public void update(){
        ++existTime;
    }
}
