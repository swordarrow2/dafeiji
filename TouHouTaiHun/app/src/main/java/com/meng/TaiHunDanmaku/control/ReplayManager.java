package com.meng.TaiHunDanmaku.control;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.RandomXS128;
import com.meng.TaiHunDanmaku.helpers.ObjectPools;
import com.meng.TaiHunDanmaku.ui.FightScreen;

import java.util.ArrayList;
import java.util.Arrays;

public class ReplayManager {
    public static boolean onReplay = false;
    private static StringBuilder repInfo = new StringBuilder();
    private static ArrayList<Float[]> rep = new ArrayList<Float[]>();
    private static FileHandle file;

    public ReplayManager() {

    }

    public static void init(String fileName, boolean onReplay) {
        file = Gdx.files.external(fileName);
        if (onReplay) {
            ReplayManager.onReplay = true;
            rep = new ArrayList<Float[]>(131071);
            String allString = file.readString();
            String[] playInfo = allString.substring(0, allString.indexOf("\n")).split("\\s");
            FightScreen.pl = playInfo[0];
            FightScreen.difficultFlag = Integer.parseInt(playInfo[1]);
            FightScreen.playerFlag = Integer.parseInt(playInfo[2]);
            FightScreen.stageFlag = Integer.parseInt(playInfo[3]);
            ObjectPools.randomPool = new RandomXS128(Long.parseLong(playInfo[4]));
            String[] splitedAllFrameInfo = allString.substring(allString.indexOf("\n") + 1).split("\n");
            String[] frameInfo;
            for (String s : splitedAllFrameInfo) {
                frameInfo = s.split("\\s");
                rep.add(new Float[]{
                        Float.parseFloat(frameInfo[0]),
                        Float.parseFloat(frameInfo[1]),
                        Float.parseFloat(frameInfo[2]),
                        Float.parseFloat(frameInfo[3])
                });
            }
        }
    }

    public static void appendData(String s) {
        if (onReplay) return;
        repInfo.append(s);
    }

    public static Float[] getData(int gameTime) {
        return rep.get(gameTime);
    }

    public static void saveRepaly() {
        if (onReplay) return;
        file.writeBytes(repInfo.toString().getBytes(), false);
    }
}
