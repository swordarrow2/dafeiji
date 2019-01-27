package com.meng.stg.myPlane;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;

public class JudgeCircleAnimation extends TextureRegionDrawable implements Disposable{
    private TextureRegion[] regions=new TextureRegion[8];
    private int stat=0;

    public JudgeCircleAnimation(){
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
        for(TextureRegion region : regions){
            region.getTexture().dispose();
        }
    }
}