package com.meng.stg.planes;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.meng.stg.helpers.ResourcesManager;

public class JudgeCircleAnimation{

    private int stat=0;
    private Image judgeAnim;

    public JudgeCircleAnimation(){
        judgeAnim=new Image(ResourcesManager.textures.get("slow23"));
    }

    public Image getImage(){
        return judgeAnim;
    }

    public void update(){
        judgeAnim.setRotation(stat);
        stat+=2;
    }
}
