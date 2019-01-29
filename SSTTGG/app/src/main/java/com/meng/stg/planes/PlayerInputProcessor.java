package com.meng.stg.planes;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.meng.stg.ui.MainActivity;
import com.meng.stg.ui.MainScreen;

import static com.meng.stg.planes.myPlane.BaseMyPlane.Instance;

/**
 * Plane Ctrl
 */

public class PlayerInputProcessor extends InputAdapter{
    private Vector2 vct2_downPosPlayer=new Vector2();
    private Vector2 vct2_downPosStage=new Vector2();
    private Vector2 vct2_tmp1=new Vector2();
    private int missX=0;
    private int missY=0;
    public static int touchX=0;
    public static int touchY=0;
    private int tx=MainActivity.screenWidth/10;
    private int ty=MainActivity.screenHeight/8;


    @Override
    public boolean touchDown(int screenX,int screenY,int pointer,int button){

        
        if(pointer==0){
            vct2_downPosStage=MainScreen.Stage.screenToStageCoordinates
                    (vct2_downPosStage.set(screenX,screenY));
            vct2_downPosPlayer.set(Instance.objectCenter);
        }
        return super.touchDown(screenX,screenY,pointer,button);
    }

    @Override
    public boolean touchUp(int screenX,int screenY,int pointer,int button){
        return super.touchUp(screenX,screenY,pointer,button);
    }

    @Override
    public boolean touchDragged(int screenX,int screenY,int pointer){
        touchX=screenX;
        touchY=screenY;


            if(pointer==0){
                vct2_tmp1=MainScreen.Stage.screenToStageCoordinates(vct2_tmp1.set(screenX,screenY));
                Instance.objectCenter.set(vct2_downPosPlayer).add(vct2_tmp1.sub(vct2_downPosStage));
            }

        return super.touchDragged(screenX,screenY,pointer);
    }

}