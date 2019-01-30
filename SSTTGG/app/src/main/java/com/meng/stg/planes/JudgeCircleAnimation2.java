package com.meng.stg.planes;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.meng.stg.ResourcesManager;

public class JudgeCircleAnimation2{
    private int stat=0;
    private Image judgeAnim;

    public JudgeCircleAnimation2(){
        judgeAnim=new Image(ResourcesManager.textures.get("slow24"));
    }

    public Image getImage(){
        return judgeAnim;
    }

    public void update(){
        judgeAnim.setRotation(stat);
        stat-=2;
    }
}
