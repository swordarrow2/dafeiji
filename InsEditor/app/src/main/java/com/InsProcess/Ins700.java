package com.InsProcess;

public class Ins700 extends Ins {
    private float x = 0;
    private float y = 0;
    private int color = 0;
    private int form = 0;

    private int dan;

    public Ins700(Sub sub, int i) {
        super(sub);
        dan = i;
    }

    public Ins700 _600() {
        stringBuilder.append(lineStart);
        stringBuilder.append(String.format("ins_600(%d)", dan));
        stringBuilder.append(lineEnd);
        return this;
    }

    public Ins700 _601() {
        stringBuilder.append(lineStart);
        stringBuilder.append(String.format("ins_601(%d)", dan));
        stringBuilder.append(lineEnd);
        return this;
    }

    public Ins700 _601(int i) {
        stringBuilder.append(lineStart);
        stringBuilder.append(String.format("ins_601(%d)", i));
        stringBuilder.append(lineEnd);
        return this;
    }

    public Ins700 _602(int form, int color) {
        stringBuilder.append(lineStart);
        stringBuilder.append(String.format("ins_602(%d, %d, %d)", dan, form, color));
        stringBuilder.append(lineEnd);
        this.form = form;
        this.color = color;
        return this;
    }

    public Ins700 _602(String form, String color) {
        stringBuilder.append(lineStart);
        stringBuilder.append(String.format("ins_602(%d, %s, %s)", dan, form, color));
        stringBuilder.append(lineEnd);
        //	this.form = form;
        //	this.color = color;
        return this;
    }

    public Ins700 _603(float offsetX, float offsetY) {
        stringBuilder.append(lineStart);
        stringBuilder.append(String.format("ins_603(%d, %ff, %ff)", dan, offsetX, offsetY));
        stringBuilder.append(lineEnd);
        x += offsetX;
        y += offsetY;
        return this;
    }

    public Ins700 _603(String offsetX, String offsetY) {
        stringBuilder.append(lineStart);
        stringBuilder.append(String.format("ins_603(%d, %s, %s)", dan, offsetX, offsetY));
        stringBuilder.append(lineEnd);
//		x += offsetX;
        //	y += offsetY;
        return this;
    }


    public Ins700 posAndImg(String x, String y, String color, String form) {
        _602(color, form)._603(x, y);
        return this;
    }

    public Ins700 posAndImg(float x, float y, int color, int form) {
        _602(color, form)._603(x, y);
        return this;
    }


    public Ins700 x(float x) {
        this.x = x;
        return this;
    }

    public Ins700 y(float y) {
        this.y = y;
        return this;
    }

    public Ins700 formAndColor(int form, int color) {
        this.form = form;
        this.color = color;
        return this;
    }

    private boolean isNumber(Object o) {
        return o instanceof Integer || o instanceof Float;
    }
}
