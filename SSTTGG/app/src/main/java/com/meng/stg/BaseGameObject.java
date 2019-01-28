package com.meng.stg;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.planes.enemyPlane.MoveCtrl;

public abstract class BaseGameObject{

    public Vector2 objectCenter=new Vector2();
    public Circle judgeCircle;
    public Image Drawer=null;
    public int animFlag=0;
    public Drawable drawable=null;
    public MoveCtrl mvc=null;
    public Vector2 velocity=new Vector2();

}
