package com.meng.TaiHunDanmaku.baseObjects.bigFace.item;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.TaiHunDanmaku.baseObjects.bigFace.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.ui.*;

public class BigFace extends BaseBigFace{

    public void init(Vector2 center,FaceCharacter fc){
        super.init();
        objectCenter.set(center);
        image.setPosition(center.x,center.y,Align.center);
        faceCharacter=fc;
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        image.setDrawable(getDrawable());
        FightScreen.instence.groupNormal.addActor(image);
		image.setZIndex(Data.zIndexBigFace);	
    }

    @Override
    public Vector2 getSize(){
        return size;
    }

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            switch(faceCharacter){
                case Junko:
                    drawable=ResourcesManager.textures.get("sjf");
                    break;
            }
        }
        return drawable;
    }
}
