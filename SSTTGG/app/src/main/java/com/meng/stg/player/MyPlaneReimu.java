package com.meng.stg.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.meng.stg.bullets.ReimuBomb;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.Pools;

import java.util.HashMap;

public class MyPlaneReimu extends BaseMyPlane{

    private HashMap<String,Drawable> playerAnim=new HashMap<String,Drawable>(30);
    private Drawable d=null;

    @Override
    public Drawable getDrawable(){
        return playerAnim.get("anim0");
    }

    @Override
    public Drawable getStayAnim(){
        switch(animTime%32){
            case 1:
                d=playerAnim.get("anim0");
                break;
            case 5:
                d=playerAnim.get("anim1");
                break;
            case 9:
                d=playerAnim.get("anim2");
                break;
            case 13:
                d=playerAnim.get("anim3");
                break;
            case 17:
                d=playerAnim.get("anim4");
                break;
            case 21:
                d=playerAnim.get("anim5");
                break;
            case 25:
                d=playerAnim.get("anim6");
                break;
            case 29:
                d=playerAnim.get("anim7");
                break;
        }
        return d;
    }

    public Drawable getLeftMoveAnim(){
        switch(animTime%32){
            case 1:
                d=playerAnim.get("anim8");
                break;
            case 5:
                d=playerAnim.get("anim9");
                break;
            case 9:
                d=playerAnim.get("anim10");
                break;
            case 13:
                d=playerAnim.get("anim11");
                break;
            case 17:
                d=playerAnim.get("anim12");
                break;
            case 21:
                d=playerAnim.get("anim13");
                break;
            case 25:
                d=playerAnim.get("anim14");
                break;
            case 29:
                d=playerAnim.get("anim15");
                break;
        }
        return d;
    }

    public Drawable getRightMoveAnim(){
        switch(animTime%32){
            case 1:
                d=playerAnim.get("anim16");
                break;
            case 5:
                d=playerAnim.get("anim17");
                break;
            case 9:
                d=playerAnim.get("anim18");
                break;
            case 13:
                d=playerAnim.get("anim19");
                break;
            case 17:
                d=playerAnim.get("anim20");
                break;
            case 21:
                d=playerAnim.get("anim21");
                break;
            case 25:
                d=playerAnim.get("anim22");
                break;
            case 29:
                d=playerAnim.get("anim23");
                break;
        }
        return d;
    }

    @Override
    public void Init(){
        super.Init();
        Texture walkSheet=new Texture(Gdx.files.internal("textures/player/pl00.png"));
        int FRAME_COLS=8;
        int FRAME_ROWS=3;
        TextureRegion[][] tmp=TextureRegion.split(walkSheet,walkSheet.getWidth()/FRAME_COLS,walkSheet.getHeight()/FRAME_ROWS);
        int index=0;
        for(int i=0;i<FRAME_ROWS;i++){
            for(int j=0;j<FRAME_COLS;j++){
                TextureRegionDrawable drawable=new TextureRegionDrawable(tmp[i][j]);
                playerAnim.put("anim"+index,drawable);
                index++;
            }
        }
        bombTime=Data.ReimuBombTime;
    }

    @Override
    public void Kill(){
        super.Kill();
        Drawer.remove();
        Pools.ImagePool.free(Drawer);
        // if(!MainScreen.gameOver)
        new MyPlaneReimu().Init();
    }

    @Override
    public void bomb(){
        Vector2 vel=new Vector2(0,30);

        if(bombTime%16==0){
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x,0),vel);
        }
        if(bombTime%16==4){
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x-20,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x+20,0),vel);
        }
        if(bombTime%16==8){
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x-20,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x+20,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x-40,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x+40,0),vel);
        }
        if(bombTime%16==12){
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x-20,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x+20,0),vel);
        }
    }
}
