package com.meng.TaiHunDanmaku.InsProcess;
import java.util.*;

public class Sub {

	private ArrayList<Ins> ins=new ArrayList<>();
	private int danmakuNo=0;
	private String subName;
	private int argLength=0;

	Sub(String name, int argLength) {
		subName = name;
		this.argLength = argLength;
	  }

	public Ins600 ins600() {
		Ins600 i=new Ins600(this, danmakuNo);
		ins.add(i);
		++danmakuNo;
		return i;
	  }

	public VarIns varIns() {
		VarIns v=new VarIns(this);
		ins.add(v);
		return v;
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

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for (Ins i:ins) {
			sb.append(i.toString());
		  }
		String arg;
		switch (argLength) {
			case 0:
			  arg = "";
			  break;
			case 1:
			  arg = "A";
			  break;
			case 2:
			  arg = "A B";
			  break;
			default:
			  arg = "";
			  break;
		  }
		return String.format("sub %s(%s) {\n%s}\n", subName, arg, sb.toString());
	  }
  }
