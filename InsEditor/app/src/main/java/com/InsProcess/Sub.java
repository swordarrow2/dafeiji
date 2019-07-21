package com.InsProcess;

import java.util.*;

public class Sub {

    private ArrayList<Ins> inses = new ArrayList<>();
    private int danmakuNo = 0;
    private String subName;
    private int argLength = 0;

    Sub(String name, int argLength) {
        subName = name;
        this.argLength = argLength;
    }

    public Ins000 ins000() {
        Ins000 ins000 = new Ins000(this);
        inses.add(ins000);
        return ins000;
    }

    public Ins600 ins600() {
        Ins600 ins600 = new Ins600(this, danmakuNo);
        inses.add(ins600);
        ++danmakuNo;
        return ins600;
    }

    public VarIns varIns() {
        VarIns varIns = new VarIns(this);
        inses.add(varIns);
        return varIns;
    }

    float a;
    float b;
    float c;
    float d;
    float e;
    float f;
    float g;
    float h;
    float i;

    public void addIns(Runnable runnable) {
        runnable.run();
    }

    @Override
    public String toString() {
        StringBuilder sb1 = new StringBuilder();
        for (Ins i : inses) {
            sb1.append(i.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        for (int i = 65; i < 65 + argLength; ++i) {
            sb2.append(" ");
            sb2.append((char) i);
        }
        return String.format("sub %s(%s) {\n%s}\n\n", subName, sb2.toString(), sb1.toString());
    }

}
