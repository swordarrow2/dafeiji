package com.meng.TaiHunDanmaku.bigFace.item;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.TaiHunDanmaku.bigFace.BaseBigFace;
import com.meng.TaiHunDanmaku.helpers.ResourcesManager;
import com.meng.TaiHunDanmaku.ui.MainScreen;

public class BigFace extends BaseBigFace{

    public void init(Vector2 center,FaceCharacter fc){
        super.init();
        objectCenter.set(center);
        image.setPosition(center.x,center.y,Align.center);
        faceCharacter=fc;
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        image.setDrawable(getDrawable());
        MainScreen.mainGroup.addActor(image);
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
                    drawable=ResourcesManager.textures.get("junkoface");
                    break;
            }
        }
        return drawable;
    }
}
