package com.meng.TaiHunDanmaku.ui;

import android.os.Bundle;
import android.view.KeyEvent;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.meng.TaiHunDanmaku.control.ReplayManager;

public class MainActivity extends AndroidApplication {
    public static MainActivity instance;
    GameMain gameMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        gameMain = new GameMain(this);
        initialize(gameMain, new AndroidApplicationConfiguration());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (gameMain.screenFlag) {
                case 1:
                    gameMain.setMainMenuScreen();
                    return true;
                case 2:
                    gameMain.setSelectDiffScreen();
                    return true;
                case 3:
                    if(ReplayManager.onReplay){
                        gameMain.setMainMenuScreen();
                        return true;
                    }
                    if (gameMain.difficultFlag.equals("Extra")) {
                        gameMain.setMainMenuScreen();
                    } else {
                        gameMain.setSelectCharScreen();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
