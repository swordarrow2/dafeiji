package com.meng.TaiHunDanmaku.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.RandomXS128;
import com.meng.TaiHunDanmaku.baseObjects.planes.myPlane.BaseMyPlane;
import com.meng.TaiHunDanmaku.helpers.ObjectPools;
import com.meng.TaiHunDanmaku.ui.FightScreen;
import com.meng.TaiHunDanmaku.ui.GameMain;

import java.util.ArrayList;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ReplayManager {
    public static boolean onReplay = false;
    private static StringBuilder repInfo = new StringBuilder();
    private static ArrayList<float[]> rep = new ArrayList<float[]>();

    public ReplayManager() {

    }

    public static void init(GameMain gameMain, long seed) {

        ReplayManager.onReplay = gameMain.onReplay;
        if (onReplay) {
            rep = new ArrayList<float[]>(131071);
            String allString = "";
            try {
                byte[] doc = new byte[512];
                ZipInputStream zipis = new ZipInputStream(Gdx.files.external(gameMain.replayFileName).read());
                FileHandle tmpFile = Gdx.files.external(gameMain.replayFileName + ".txt");
                while (zipis.getNextEntry() != null) {
                    FileOutputStream out = (FileOutputStream) tmpFile.write(false);
                    int n;
                    while ((n = zipis.read(doc, 0, 512)) != -1) {
                        out.write(doc, 0, n);
                    }
                    out.close();
                }
                zipis.close();
                allString = tmpFile.readString();
                tmpFile.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] playInfo = allString.substring(0, allString.indexOf("\n")).split("\\s");
            gameMain.charaFlag = playInfo[0];
            gameMain.equipmentFlag = playInfo[1];
            gameMain.difficultFlag = playInfo[2];
            gameMain.stageFlag = playInfo[3];
            gameMain.power = Integer.parseInt(playInfo[4]);
            ObjectPools.randomPool = new RandomXS128(Long.parseLong(playInfo[5]));
            String[] splitedAllFrameInfo = allString.substring(allString.indexOf("\n") + 1).split("\n");
            String[] frameInfo;
            for (String s : splitedAllFrameInfo) {
                if (!s.equals("end")) {
                    frameInfo = s.split("\\s");
                    rep.add(new float[]{
                            Float.parseFloat(frameInfo[0]),
                            Float.parseFloat(frameInfo[1]),
                            Float.parseFloat(frameInfo[2]),
                            Float.parseFloat(frameInfo[3]),
                            Float.parseFloat(frameInfo[4])
                    });
                }
            }
            rep.add(new float[]{0f, 0f, 0f, 0f, 0f});
            rep.add(new float[]{0f, 0f, 0f, 0f, 0f});
        } else {
            ObjectPools.randomPool = new RandomXS128(seed);
            appendData(
                    gameMain.charaFlag + " " +
                            gameMain.equipmentFlag + " " +
                            gameMain.difficultFlag + " " +
                            gameMain.stageFlag + " " +
                            gameMain.power + " " +
                            seed + "\n");
        }
    }

    public static void appendData(String s) {
        if (onReplay) return;
        repInfo.append(s);
    }

    public static void update(int gameTime) {
        if (onReplay) {
            float[] eachFrameInfo = rep.get(gameTime);
            BaseMyPlane.instance.objectCenter.x = eachFrameInfo[0];
            BaseMyPlane.instance.objectCenter.y = eachFrameInfo[1];
            BaseMyPlane.instance.slow = eachFrameInfo[2] == 1f;
            BaseMyPlane.instance.onBomb = eachFrameInfo[3] == 1f;
            FightScreen.instence.replayFPS = (int) eachFrameInfo[4];
        } else {
            appendData(
                    BaseMyPlane.instance.objectCenter.x +
                            " " + BaseMyPlane.instance.objectCenter.y +
                            " " + (BaseMyPlane.instance.slow ? 1 : 0) +
                            " " + (BaseMyPlane.instance.onBomb ? 1 : 0) +
                            " " + FightScreen.instence.nowFps +
                            "\n");
        }
    }

    public static void saveRepaly() {
        if (onReplay) return;
        repInfo.append("end");
        try {
            FileHandle fh = Gdx.files.external(FightScreen.instence.gameMain.replayFileName);
            InputStream input = new ByteArrayInputStream(repInfo.toString().getBytes());
            ZipOutputStream zipOut = new ZipOutputStream(fh.write(false));
            zipOut.putNextEntry(new ZipEntry("bin.txt"));
            zipOut.setComment("FaFaFa");  // 设置注释
            int temp = 0;
            while ((temp = input.read()) != -1) {
                zipOut.write(temp);
            }
            input.close();
            zipOut.flush();
            zipOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
