package com.meng.stg.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.AliceShoot;
import com.meng.stg.bullets.ReimuBomb;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.Pools;
import com.meng.stg.helpers.Resources;

public class MyPlaneReimu extends BaseMyPlane{

    private Drawable d=null;

    @Override
    public Drawable getDrawable(){
        return Resources.Textures.get("reimu0");
    }

    @Override
    public Drawable getStayAnim(){
        switch(animTime%32){
            case 1:
                d=Resources.Textures.get("reimu0");
                break;
            case 5:
                d=Resources.Textures.get("reimu1");
                break;
            case 9:
                d=Resources.Textures.get("reimu2");
                break;
            case 13:
                d=Resources.Textures.get("reimu3");
                break;
            case 17:
                d=Resources.Textures.get("reimu4");
                break;
            case 21:
                d=Resources.Textures.get("reimu5");
                break;
            case 25:
                d=Resources.Textures.get("reimu6");
                break;
            case 29:
                d=Resources.Textures.get("reimu7");
                break;
        }
        return d;
    }

    public Drawable getLeftMoveAnim(){
        switch(animTime%32){
            case 1:
                d=Resources.Textures.get("reimu8");
                break;
            case 5:
                d=Resources.Textures.get("reimu9");
                break;
            case 9:
                d=Resources.Textures.get("reimu10");
                break;
            case 13:
                d=Resources.Textures.get("reimu11");
                break;
            case 17:
                d=Resources.Textures.get("reimu12");
                break;
            case 21:
                d=Resources.Textures.get("reimu13");
                break;
            case 25:
                d=Resources.Textures.get("reimu14");
                break;
            case 29:
                d=Resources.Textures.get("reimu15");
                break;
        }
        return d;
    }

    public Drawable getRightMoveAnim(){
        switch(animTime%32){
            case 1:
                d=Resources.Textures.get("reimu16");
                break;
            case 5:
                d=Resources.Textures.get("reimu17");
                break;
            case 9:
                d=Resources.Textures.get("reimu18");
                break;
            case 13:
                d=Resources.Textures.get("reimu19");
                break;
            case 17:
                d=Resources.Textures.get("reimu20");
                break;
            case 21:
                d=Resources.Textures.get("reimu21");
                break;
            case 25:
                d=Resources.Textures.get("reimu22");
                break;
            case 29:
                d=Resources.Textures.get("reimu23");
                break;
        }
        return d;
    }

    @Override
    public void shoot(){

        if(ExistTime%2==1){
               Vector2 vel=new Vector2(0,60);
               AliceShoot.Pool.obtain().Init(Center,vel);
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
                playerAnim.put("reimu"+index,drawable);
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
