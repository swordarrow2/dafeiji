package com.meng.stg.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.ReimuShoot;
import com.meng.stg.bullets.ReimuBomb;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.Pools;
import com.meng.stg.helpers.Resources;

public class MyPlaneReimu extends BaseMyPlane{

    private Drawable d=null;
    private String reimu="reimu";

    @Override
    public Drawable getDrawableJavaBean(){
        return Resources.Textures.get(reimu+0);
    }

    @Override
    public Drawable getStayAnim(){
        switch(animTime%32){
            case 1:
                d=Resources.Textures.get(reimu+0);
                break;
            case 5:
                d=Resources.Textures.get(reimu+1);
                break;
            case 9:
                d=Resources.Textures.get(reimu+2);
                break;
            case 13:
                d=Resources.Textures.get(reimu+3);
                break;
            case 17:
                d=Resources.Textures.get(reimu+4);
                break;
            case 21:
                d=Resources.Textures.get(reimu+5);
                break;
            case 25:
                d=Resources.Textures.get(reimu+6);
                break;
            case 29:
                d=Resources.Textures.get(reimu+7);
                break;
        }
        return d;
    }

    public Drawable getLeftMoveAnim(){
        switch(animTime%32){
            case 1:
                d=Resources.Textures.get(reimu+8);
                break;
            case 5:
                d=Resources.Textures.get(reimu+9);
                break;
            case 9:
                d=Resources.Textures.get(reimu+10);
                break;
            case 13:
                d=Resources.Textures.get(reimu+11);
                break;
            case 17:
                d=Resources.Textures.get(reimu+12);
                break;
            case 21:
                d=Resources.Textures.get(reimu+13);
                break;
            case 25:
                d=Resources.Textures.get(reimu+14);
                break;
            case 29:
                d=Resources.Textures.get(reimu+15);
                break;
        }
        return d;
    }

    public Drawable getRightMoveAnim(){
        switch(animTime%32){
            case 1:
                d=Resources.Textures.get(reimu+16);
                break;
            case 5:
                d=Resources.Textures.get(reimu+17);
                break;
            case 9:
                d=Resources.Textures.get(reimu+18);
                break;
            case 13:
                d=Resources.Textures.get(reimu+19);
                break;
            case 17:
                d=Resources.Textures.get(reimu+20);
                break;
            case 21:
                d=Resources.Textures.get(reimu+21);
                break;
            case 25:
                d=Resources.Textures.get(reimu+22);
                break;
            case 29:
                d=Resources.Textures.get(reimu+23);
                break;
        }
        return d;
    }

    @Override
    public void shoot(){
        if(ExistTime%20==1){
            Vector2 vel=new Vector2(0,6);
            ReimuShoot.Pool.obtain().Init(Center,vel);
        }
    }

    @Override
    public void Init(){
        super.Init();
     /*   Texture walkSheet=new Texture(Gdx.files.internal("textures/player/pl00.png"));
        int FRAME_COLS=8;
        int FRAME_ROWS=3;
        TextureRegion[][] tmp=TextureRegion.split(walkSheet,walkSheet.getWidth()/FRAME_COLS,walkSheet.getHeight()/FRAME_ROWS);
        int index=0;
        for(int i=0;i<FRAME_ROWS;i++){
            for(int j=0;j<FRAME_COLS;j++){
                TextureRegionDrawable drawable=new TextureRegionDrawable(tmp[i][j]);
                playerAnim.put(reimu+"+index,drawable);
                index++;
            }
        }*/
        bombTime=Data.ReimuBombTime;
    }

    @Override
    public void Kill(){
        super.Kill();
        Pools.ImagePool.free(Drawer);
        new MyPlaneReimu().Init();
    }

    @Override
    public void bomb(){
        Vector2 vel=new Vector2(0,30);

        if(bombTime%16==0){
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x,0),vel);
        }
        if(bombTime%16==4){
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x-20,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x+20,0),vel);
        }
        if(bombTime%16==8){
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x-40,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x+40,0),vel);
        }
        if(bombTime%16==12){
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x-20,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x+20,0),vel);
        }
    }
}
