package com.meng.grzxConfig.MaterialDesign.javaBean;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonInfo implements Serializable {
    public String name = "";
    public long qq = 0;
    public int bid = 0;
    public int bliveRoom = 0;
    public boolean autoTip = false;
    public ArrayList<Long> tipIn = new ArrayList<>();
}
