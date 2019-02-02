package com.meng.stg.planes;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.meng.stg.BaseGameObject;
import com.meng.stg.helpers.ResourcesManager;

public class JudgeCircleAnimation2 extends BaseGameObject{
    private int stat=0;

    public JudgeCircleAnimation2(){
    }

    @Override
    public void init(){
        super.init();
        image.setDrawable(ResourcesManager.textures.get("slow24"));
    }

    public Image getImage(){
        return image;
    }

    public void update(){
        image.setRotation(stat);
        stat-=2;
    }

    @Override
    public void kill(){
        super.kill();
    }
}
