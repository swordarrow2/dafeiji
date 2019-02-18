package com.meng.stg.ui;

import cn.s3bit.th902.*;
import cn.s3bit.th902.gamecontents.*;
import cn.s3bit.th902.gamecontents.components.player.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;

public class PlayerInputProcessor extends InputAdapter{
    private Vector2 vct2_downPosPlayer=new Vector2();
    private Vector2 vct2_downPosStage=new Vector2();
    private Vector2 vct2_tmp1=new Vector2();

    @Override
    public boolean touchDown(int screenX,int screenY,int pointer,int button){
        if(pointer==0){
            vct2_downPosStage=GameMain.instance.activeStage.screenToStageCoordinates(vct2_downPosStage.set(screenX,screenY));
			vct2_downPosPlayer.set(JudgingSystem.playerJudge);
		  }
        if(pointer==1){
            Player.slow=true;
		  }
        if(pointer==2){
            Player.onBomb=true;
		  }
        if(pointer==3){
			//	MainScreen.instence.restart();
            // BaseMyPlane.instance.power++;
			// ((MyPlaneReimu)BaseMyPlane.instance).onPowerInc();
		  }
        return super.touchDown(screenX,screenY,pointer,button);
	  }

    @Override
    public boolean touchUp(int screenX,int screenY,int pointer,int button){
        if(pointer==1){
            Player.slow=false;
		  }
        return super.touchUp(screenX,screenY,pointer,button);
	  }

    @Override
    public boolean touchDragged(int screenX,int screenY,int pointer){
        if(pointer==0){
            vct2_tmp1=GameMain.instance.activeStage.screenToStageCoordinates(vct2_tmp1.set(screenX,screenY));
            Player.transform.position.set(vct2_downPosPlayer).add(vct2_tmp1.sub(vct2_downPosStage));
			Player.transform.position.x = MathUtils.clamp(Player.transform.position.x, 30, 540);
			Player.transform.position.y = MathUtils.clamp(Player.transform.position.y, 48, 680);
			
		  }
        return super.touchDragged(screenX,screenY,pointer);
	  }

  }

