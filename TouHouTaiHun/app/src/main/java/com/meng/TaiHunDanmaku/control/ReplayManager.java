package com.meng.TaiHunDanmaku.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.RandomXS128;
import com.meng.TaiHunDanmaku.baseObjects.planes.myPlane.BaseMyPlane;
import com.meng.TaiHunDanmaku.helpers.ObjectPools;
import com.meng.TaiHunDanmaku.ui.GameMain;

import java.util.ArrayList;

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
            GameMain.equipment = playInfo[0];
            GameMain.difficultFlag = Integer.parseInt(playInfo[1]);
            GameMain.playerFlag = Integer.parseInt(playInfo[2]);
            GameMain.stageFlag = Integer.parseInt(playInfo[3]);
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
            rep.add(new Float[]{0f, 0f, 0f, 0f});
        }
    }

    public static void appendData(String s) {
        if (onReplay) return;
        repInfo.append(s);
    }

    public static void update(int gameTime) {
        if (onReplay) {
            BaseMyPlane.instance.objectCenter.x = rep.get(gameTime)[0];
            BaseMyPlane.instance.objectCenter.y = rep.get(gameTime)[1];
            BaseMyPlane.instance.slow = rep.get(gameTime)[2] == 1f;
            BaseMyPlane.instance.onBomb = rep.get(gameTime)[3] == 1f;
        } else {
            appendData(
                    BaseMyPlane.instance.objectCenter.x +
                            " " + BaseMyPlane.instance.objectCenter.y +
                            " " + (BaseMyPlane.instance.slow ? 1 : 0) +
                            " " + (BaseMyPlane.instance.onBomb ? 1 : 0) +
                            "\n");
        }
    }

    public static void saveRepaly() {
        if (onReplay) return;
        file.writeBytes(repInfo.toString().getBytes(), false);
    }
}
