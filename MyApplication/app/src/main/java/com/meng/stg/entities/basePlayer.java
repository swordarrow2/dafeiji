package com.meng.stg.entities;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.meng.stg.MainScreen;

public class basePlayer extends BaseObject{
    public static basePlayer Instance;
    PlayerAnimation animation=null;

    @Override
    public Drawable getDrawable(){
        if(animation==null)
            animation=new PlayerAnimation();
        return animation;
    }

    @Override
    public void Init(){
        super.Init();
        Instance=this;
        Drawer.setSize(32,32);
        Center.set(270,80);
    }

    @Override
    public void Update(){
        Center.x=MathUtils.clamp(Center.x,0,MainScreen.Width);
        Center.y=MathUtils.clamp(Center.y,0,MainScreen.Height);
        super.Update();
        Drawer.toFront();
  //      animation.Update();
    }

    @Override
    public void Kill(){
    }

    public static class PlayerInputProcessor extends InputAdapter{
        Vector2 vct2_downPosPlayer=new Vector2();
        Vector2 vct2_downPosStage=new Vector2();
        Vector2 vct2_tmp1=new Vector2();

        @Override
        public boolean touchDown(int screenX,int screenY,int pointer,int button){
            if(pointer==0){
                vct2_downPosStage=MainScreen.Stage.screenToStageCoordinates
                        (vct2_downPosStage.set(screenX,screenY));
                vct2_downPosPlayer.set(basePlayer.Instance.Center);
            }
            return super.touchDown(screenX,screenY,pointer,button);
        }

        @Override
        public boolean touchDragged(int screenX,int screenY,int pointer){
            if(pointer==0){
                vct2_tmp1=MainScreen.Stage.screenToStageCoordinates(vct2_tmp1.set(screenX,screenY));
                basePlayer.Instance.Center.set(vct2_downPosPlayer).add(vct2_tmp1.sub(vct2_downPosStage));
            }
            return super.touchDragged(screenX,screenY,pointer);
        }
    }

    public static class PlayerAnimation extends TextureRegionDrawable implements Disposable{
        TextureRegion[] regions=new TextureRegion[8];
        int stat=0;

        public PlayerAnimation(){
            for(int i=0;i<regions.length;i++){
                Pixmap pixmap=new Pixmap(64,64,Pixmap.Format.RGBA8888);
                pixmap.setColor(0,0,1,1);
                pixmap.fillCircle(32,32,10);
                pixmap.setColor(1,1,1,1);
                pixmap.fillCircle(32,32,5);
                pixmap.setColor(1,1,1,0.5f);
                pixmap.drawCircle(32,32,(int)(i*3f)+7);
                pixmap.setColor(1,1,1,1f);
                pixmap.drawCircle(32,32,(int)(i*3f)+8);
                pixmap.setColor(1,1,1,0.5f);
                pixmap.drawCircle(32,32,(int)(i*3f)+9);
                regions[i]=new TextureRegion(new Texture(pixmap));
                pixmap.dispose();
            }
            setRegion(regions[0]);
        }

        public void Update(){
            stat++;
            setRegion(regions[stat%regions.length]);
        }

        @Override
        public void dispose(){
            for(int i=0;i<regions.length;i++){
                regions[i].getTexture().dispose();
            }
        }
    }
}
