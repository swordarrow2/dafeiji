package com.meng.guirenzhengxie;

import java.util.ArrayList;

public class ConfigJavaBean {
    public ArrayList<Long> mapGroupReply = new ArrayList<>();
    public ArrayList<Long> mapQQNotReply = new ArrayList<>();
    public ArrayList<String> mapWordNotReply = new ArrayList<>();
    public ArrayList<String> mapGroupRepeater = new ArrayList<>();
    public ArrayList<Long> mapGroupDicReply = new ArrayList<>();
    public ArrayList<BilibiliUser> mapBiliUser = new ArrayList<>();

    public class BilibiliUser {
        public String name = "";
        public long qq = 0;
        public int bid = 0;
        public int bliveRoom = 0;
        public boolean autoTip = false;
    }
}
