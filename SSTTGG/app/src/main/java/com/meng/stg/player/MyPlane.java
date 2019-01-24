package com.meng.stg.player;

import com.badlogic.gdx.math.*;
import com.meng.stg.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Disposable;
import com.meng.stg.bullets.Projectile;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.Pools;

public class MyPlane extends BaseMyPlane {
    public static MyPlane Instance;
    public PlayerAnimation animation = null;
    public static boolean missed = false;
    public static boolean b=false;
    @Override
    protected Drawable getDrawable() {
        if (animation == null)
            animation = new PlayerAnimation();
        return animation;
    }

    @Override
    public void Init() {
        super.Init();

        if (!MainScreen.gameOver) {
            Instance = this;
            Drawer.setSize(32, 32);
            Center.set(270, 80);
        }
    }

    @Override
    public void Update() {
        if (!MainScreen.gameOver) {
            Center.x = x = MathUtils.clamp(Center.x, 0, MainScreen.Width);
            Center.y = y = MathUtils.clamp(Center.y, 0, MainScreen.Height);
            super.Update();
            Drawer.toFront();
            animation.Update();
        }
    }
    @Override
    public void Kill() {
            if (!onUnmatched) {
                MainScreen.gameOver=true;
                missTime++;
                missed=true;
                b=false;
                //Pools.ImagePool.free(Drawer);
                switch (MainScreen.playerFlag) {
                    case Data.playerFlagReimu:
                        Drawer.remove();
                        MyPlaneReimu.rm.Drawer.remove();
                        Pools.ImagePool.free(Drawer);
                        Pools.ImagePool.free(MyPlaneReimu.rm.Drawer);
                       // if(!MainScreen.gameOver)
                        new MyPlaneReimu().Init();
                        break;
                    case Data.playerFlagAlice:
                        Drawer.remove();
                        MyPlaneAlice.Alice.Drawer.remove();
                        Pools.ImagePool.free(Drawer);
                        Pools.ImagePool.free(MyPlaneAlice.Alice.Drawer);
                     //   if(!MainScreen.gameOver)
                        new MyPlaneAlice().Init();
                        break;
                }
                //Drawer.remove();
                //将Drawer从舞台上撤下并且将其回归到Pool中
                //Pools.ImagePool.free(Drawer);

            }

    }
    public void bomb(){

    }


    public static class PlayerInputProcessor extends InputAdapter {
        Vector2 vct2_downPosPlayer = new Vector2();
        Vector2 vct2_downPosStage = new Vector2();
        Vector2 vct2_tmp1 = new Vector2();
        int missX;
        int missY;
        public static int touchX;
        public static int touchY;
        int tx=MainActivity.screenWidth/10;
        int ty=MainActivity.screenHeight/8;

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {

            if(MainScreen.gameOver){
                System.exit(0);
            }
            if((screenX<tx)&&(screenY>(MainActivity.screenHeight-ty))){
                Instance.onBomb=true;
            }
            if (pointer == 0) {
                if(animTime>30){
                    animTime=0;
                }
                    vct2_downPosStage = MainScreen.Stage.screenToStageCoordinates
                            (vct2_downPosStage.set(screenX, screenY));
                    vct2_downPosPlayer.set(MyPlane.Instance.Center);
            }
            return super.touchDown(screenX, screenY, pointer, button);
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            missed=false;
            return super.touchUp(screenX, screenY, pointer, button);
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            touchX=screenX;
            touchY=screenY;

            if (!missed) {
        //        if(screenX<100&&screenY<100){
         //           Instance.onBomb=true;
         //       }
                if (pointer == 0) {
                    vct2_tmp1 = MainScreen.Stage.screenToStageCoordinates(vct2_tmp1.set(screenX, screenY));
                    MyPlane.Instance.Center.set(vct2_downPosPlayer).add(vct2_tmp1.sub(vct2_downPosStage));
                }

            } else {
                if (!b) {
                    missX = screenX - 270;
                    missY = screenY + 80;
                    b=true;
                }
                if (pointer == 0) {
                    vct2_tmp1 = MainScreen.Stage.screenToStageCoordinates(vct2_tmp1.set(screenX, screenY));
                    MyPlane.Instance.Center.set(new Vector2(screenX-missX,-(screenY-missY)));
                }
            }

            return super.touchDragged(screenX, screenY, pointer);
        }
    }

    public static class PlayerAnimation extends TextureRegionDrawable implements Disposable {
        TextureRegion[] regions = new TextureRegion[8];
        int stat = 0;

        public PlayerAnimation() {
            for (int i = 0; i < regions.length; i++) {
                Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
                pixmap.setColor(0, 0, 1, 1);
                pixmap.fillCircle(32, 32, 10);
                pixmap.setColor(1, 1, 1, 1);
                pixmap.fillCircle(32, 32, 5);
                pixmap.setColor(1, 1, 1, 0.5f);
                pixmap.drawCircle(32, 32, (int) (i * 3f) + 7);
                pixmap.setColor(1, 1, 1, 1f);
                pixmap.drawCircle(32, 32, (int) (i * 3f) + 8);
                pixmap.setColor(1, 1, 1, 0.5f);
                pixmap.drawCircle(32, 32, (int) (i * 3f) + 9);
                regions[i] = new TextureRegion(new Texture(pixmap));
                pixmap.dispose();
            }
            setRegion(regions[0]);
        }

        public void Update() {
            stat++;
            setRegion(regions[stat % regions.length]);
        }

        @Override
        public void dispose() {
            for (int i = 0; i < regions.length; i++) {
                regions[i].getTexture().dispose();
            }
        }
    }
}
