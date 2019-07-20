package com.meng.TaiHunDanmaku.ui;

import android.os.Bundle;
import android.view.KeyEvent;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

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
}
