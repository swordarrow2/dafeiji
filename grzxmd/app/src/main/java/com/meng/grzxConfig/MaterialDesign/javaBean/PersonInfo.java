package com.meng.grzxConfig.MaterialDesign.javaBean;

import java.io.Serializable;
import java.util.HashSet;

public class PersonInfo implements Serializable {
    public String name = "";
    public long qq = 0;
    public int bid = 0;
    public int bliveRoom = 0;
    public boolean autoTip = false;
    public HashSet<Long> tipIn = new HashSet<>();
}
