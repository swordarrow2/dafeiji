package com.meng.stg.player;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.*;
import com.meng.stg.bullets.*;
import com.meng.stg.helpers.*;
import java.util.*;

public class MyPlaneReimu extends MyPlane{

    protected int time = 0;
    public static class ReimuEntity extends BaseMyPlane{
        public HashMap<String, Drawable> playerAnim = new HashMap<String, Drawable>(30);
        TextureRegion[][] tmp;
        Texture walkSheet;
        int FRAME_COLS = 8;
        int FRAME_ROWS = 3;
        float playerLastX = 270;
        private Drawable d=null;
        @Override
        protected Drawable getDrawable(){
            //return Resources.NPCDrawables.get("enemy2");
            switch(animTime%32){
                case 1:// case 2:case 3:case 4:
				  d=playerAnim.get("anim0");
				  break;
                case 5://case 6:case 7:case 8:
				  d=playerAnim.get("anim1");
				  break;
                case 9://case 10:case 11:case 12:
				  d=playerAnim.get("anim2");
				  break;
                case 13://case 14:case 15:case 16:
				  d=playerAnim.get("anim3");
				  break;
                case 17://case 18:case 19:case 20:
				  d=playerAnim.get("anim4");
				  break;
                case 21://case 22:case 23:case 24:
				  d=playerAnim.get("anim5");
				  break;
                case 25://case 26:case 27:case 28:
				  d=playerAnim.get("anim6");
				  break;
                case 29://case 30:case 31:case 0:
				  d=playerAnim.get("anim7");
				  break;
				  //  default:
				  //      return playerAnim.get("anim7");
			  }
			return d;
		  }

        protected Drawable getLeftMoveAnim(){
            switch(animTime%32){
                case 1: case 2:case 3:case 4:
				  return playerAnim.get("anim8");
                case 5:case 6:case 7:case 8:
				  return playerAnim.get("anim9");
                case 9:case 10:case 11:case 12:
				  return playerAnim.get("anim10");
                case 13:case 14:case 15:case 16:
				  return playerAnim.get("anim11");
                case 17:case 18:case 19:case 20:
				  return playerAnim.get("anim12");
                case 21:case 22:case 23:case 24:
				  return playerAnim.get("anim13");
                case 25:case 26:case 27:case 28:
				  return playerAnim.get("anim14");
                case 29:case 30:case 31:case 0:
				  return playerAnim.get("anim15");
                default:
				  return playerAnim.get("anim15");
			  }
		  }

        protected Drawable getRightMoveAnim(){
            switch(animTime%32){
                case 1: case 2:case 3:case 4:
				  return playerAnim.get("anim16");
                case 5:case 6:case 7:case 8:
				  return playerAnim.get("anim17");
                case 9:case 10:case 11:case 12:
				  return playerAnim.get("anim18");
                case 13:case 14:case 15:case 16:
				  return playerAnim.get("anim19");
                case 17:case 18:case 19:case 20:
				  return playerAnim.get("anim20");
                case 21:case 22:case 23:case 24:
				  return playerAnim.get("anim21");
                case 25:case 26:case 27:case 28:
				  return playerAnim.get("anim22");
                case 29:case 30:case 31:case 0:
				  return playerAnim.get("anim23");
                default:
				  return playerAnim.get("anim23");
			  }
		  }

        public void Init(){

            super.Init();
            walkSheet=new Texture(Gdx.files.internal("textures/player/Reimu.png"));
            tmp=TextureRegion.split(walkSheet,walkSheet.getWidth()/FRAME_COLS,walkSheet.getHeight()/FRAME_ROWS);
            int index = 0;
            for(int i = 0; i<FRAME_ROWS; i++){
                for(int j = 0; j<FRAME_COLS; j++){
                    TextureRegionDrawable drawable = new TextureRegionDrawable(tmp[i][j]);
                    playerAnim.put("anim"+index,drawable);
                    index++;
				  }
			  }

            //	walkAnimation = new Animation(0.025f, walkFrames);

            if(MainScreen.gameOver)return;
            Drawer.setSize(54,85);
            bombTime=Data.ReimuBombTime;
            unmatchedTime=1;// Data.ReimuUnmatchedTime;
            onUnmatched=true;

		  }


        @Override
        public void Update(){

            if(!MainScreen.gameOver){
				Drawer.toBack();
				animTime++;

				if(MyPlane.Instance.Center.x>playerLastX){
					playerLastX=MyPlane.Instance.Center.x;
					Drawer.setDrawable(getRightMoveAnim());

				  }else if(MyPlane.Instance.Center.x<playerLastX){
					playerLastX=MyPlane.Instance.Center.x;
					Drawer.setDrawable(getLeftMoveAnim());
				  }else{
					playerLastX=MyPlane.Instance.Center.x;
					Drawer.setDrawable(getDrawable());
				  }


				this.Center.set(MyPlane.Instance.Center);
				Center.add(Velocity);
				Drawer.setPosition(Center.x,Center.y,Align.center);
				ExistTime++;
			  }}
	  }

    public static ReimuEntity rm;

    @Override
    public void Init(){
        rm=new ReimuEntity();
        rm.Init();
        super.Init();
	  }

    @Override
    public void Update(){
        if(!MainScreen.gameOver){
			super.Update();
			rm.Update();
			time++;
			if(time%2==1){
				Vector2 vel = new Vector2(0,60);
				//AliceShoot.Pool.obtain().Init(Center, vel);
				AliceShoot sb = new AliceShoot();
				sb.createBullet(Center,vel);
			  }

			if(bombTime==0){
				onBomb=false;
				bombTime=Data.ReimuBombTime;
			  }

			if(unmatchedTime==0){
				onUnmatched=false;
				unmatchedTime=Data.ReimuUnmatchedTime;
			  }
		  }}

    @Override
    public void bomb(){
        Vector2 vel = new Vector2(0,30);

        if(bombTime%16==0){
            ReimuBomb sb = new ReimuBomb();
            sb.createBullet(new Vector2(Center.x,0),vel);
		  }
        if(bombTime%16==4){
            ReimuBomb sb2 = new ReimuBomb();
            sb2.createBullet(new Vector2(Center.x-20,0),vel);
            ReimuBomb sb3 = new ReimuBomb();
            sb3.createBullet(new Vector2(Center.x+20,0),vel);
		  }
        if(bombTime%16==8){
            ReimuBomb sb4 = new ReimuBomb();
            sb4.createBullet(new Vector2(Center.x-40,0),vel);
            ReimuBomb sb5 = new ReimuBomb();
            sb5.createBullet(new Vector2(Center.x+40,0),vel);
		  }
        if(bombTime%16==12){
            ReimuBomb sb6 = new ReimuBomb();
            sb6.createBullet(new Vector2(Center.x-20,0),vel);
            ReimuBomb sb7 = new ReimuBomb();
            sb7.createBullet(new Vector2(Center.x+20,0),vel);
		  }
	  }
  }
