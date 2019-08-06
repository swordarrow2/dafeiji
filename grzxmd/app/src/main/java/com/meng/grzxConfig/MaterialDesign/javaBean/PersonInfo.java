package com.meng.grzxConfig.MaterialDesign.javaBean;

import java.util.ArrayList;
import java.util.HashSet;

public class PersonInfo {
    public String name = "";
    public long qq = 0;
    public int bid = 0;
    public int bliveRoom = 0;
    public HashSet<Long> tipIn = new HashSet<>();
    public ArrayList<Boolean> tip = new ArrayList<>();

    public PersonInfo() {
        tip.add(true);//live
        tip.add(true);//video artical
        tip.add(false);//action
    }

    public boolean isTipLive() {
        return tip.get(0);
    }

    public void setTipLive(boolean b) {
        tip.set(0, b);
    }

    public boolean isTipVideo() {
        return tip.get(1);
    }

    public void setTipVideo(boolean b) {
        tip.set(1, b);
    }

    public boolean isTipAction() {
        return tip.get(2);
    }

    public void setTipAction(boolean b) {
        tip.set(2, b);
    }

}
