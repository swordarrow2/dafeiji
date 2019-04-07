package com.meng.TaiHunDanmaku.helpers;

import java.io.*;
import java.util.zip.*;

import com.badlogic.gdx.files.*;
import com.badlogic.gdx.*;

public class ZipManager {

    public static void saveReplayFile(String txt, String fileName) throws IOException {
        FileHandle fh = Gdx.files.external(fileName);
        InputStream input = new ByteArrayInputStream(txt.getBytes());
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
    }

    public static String readReplayFile(String fileName) throws IOException {
        byte[] doc = new byte[512];
        ZipInputStream zipis = new ZipInputStream(Gdx.files.external(fileName).read());
        FileHandle tmpFile = Gdx.files.external(fileName + "tmp");
        while (zipis.getNextEntry() != null) {
            FileOutputStream out = (FileOutputStream) tmpFile.write(false);
            int n;
            while ((n = zipis.read(doc, 0, 512)) != -1) {
                out.write(doc, 0, n);
            }
            out.close();
        }
        zipis.close();
        String replayString=tmpFile.readString();
     //   tmpFile.delete();
        return replayString;
    }
}
