package com.meng.TaiHunDanmaku.InsProcess;
import java.util.*;
import java.lang.reflect.*;

public class Ins600 extends Ins {
	private float x=0;
	private float y=0;
	private int color=0;
	private int form=0;

	private int dan;
	private final String lineEnd=";\n";
	private StringBuilder stringBuilder=new StringBuilder();

	public Ins600(Sub sub, int i) {
		super(sub, i);
		dan = i;
	  }

	public Ins600 _600() {
		stringBuilder.append(String.format("ins_600(%d)", dan));
		stringBuilder.append(lineEnd);
		return this;
	  }	  

	public Ins600 _601() {
		stringBuilder.append(String.format("ins_601(%d)", dan));
		stringBuilder.append(lineEnd);
		return this;
	  }

	public Ins600 _602(int form, int color) {
		stringBuilder.append(String.format("ins_602(%d,%d,%d)", dan, form, color));
		stringBuilder.append(lineEnd);
		this.form = form;
		this.color = color;
		return this;
	  }

	public Ins600 _603(float offsetX, float offsetY) {
		stringBuilder.append(String.format("ins_603(%d,%f,%f)", dan, offsetX, offsetY));
		stringBuilder.append(lineEnd);
		x += offsetX;
		y += offsetY;
		return this;
	  }




	public Ins600 x(float x) {
		this.x = x;
		return this;
	  }

	public Ins600 y(float y) {
		this.y = y;
		return this;
	  }

	public Ins600 formAndColor(int form, int color) {
		this.form = form;
		this.color = color;
		return this;
	  }
  }
