package com.meng.stg2.characters;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.meng.stg2.characters.player.BaseMyPlane;
import com.meng.stg2.ui.MainScreen;

import static com.meng.stg2.characters.player.BaseMyPlane.instance;
import com.meng.stg2.characters.player.*;

public class PlayerInputProcessor extends InputAdapter{
    private Vector2 vct2_downPosPlayer=new Vector2();
    private Vector2 vct2_downPosStage=new Vector2();
    private Vector2 vct2_tmp1=new Vector2();

    @Override
    public boolean touchDown(int screenX,int screenY,int pointer,int button){
        if(pointer==0){
            vct2_downPosStage=MainScreen.stage.screenToStageCoordinates(vct2_downPosStage.set(screenX,screenY));
            vct2_downPosPlayer.set(instance.position);
        }
        if(pointer==1){
            BaseMyPlane.instance.slow=true;
        }
        if(pointer==2){
            BaseMyPlane.instance.onBomb=true;
        }
        if(pointer==3){
           MainScreen.instence.restart();
            // BaseMyPlane.instance.power++;
           // ((MyPlaneReimu)BaseMyPlane.instance).onPowerInc();
        }
        return super.touchDown(screenX,screenY,pointer,button);
    }

    @Override
    public boolean touchUp(int screenX,int screenY,int pointer,int button){
        if(pointer==1){
            BaseMyPlane.instance.slow=false;
        }
        return super.touchUp(screenX,screenY,pointer,button);
    }

    @Override
    public boolean touchDragged(int screenX,int screenY,int pointer){
        if(pointer==0){
            vct2_tmp1=MainScreen.stage.screenToStageCoordinates(vct2_tmp1.set(screenX,screenY));
            instance.position.set(vct2_downPosPlayer).add(vct2_tmp1.sub(vct2_downPosStage));
        }
        return super.touchDragged(screenX,screenY,pointer);
    }

}
