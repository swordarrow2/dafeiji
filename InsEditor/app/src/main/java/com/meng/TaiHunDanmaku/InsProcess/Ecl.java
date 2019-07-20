package com.meng.TaiHunDanmaku.InsProcess;
import java.util.*;

public class Ecl {
	private ArrayList<Sub> subs=new ArrayList<>();

	public Sub sub(String name) {
		Sub s=sub(name, 0);
		return s;
	  }

	public Sub sub(String name, int argLength) {
		Sub s=new Sub(name, argLength);
		subs.add(s);
		return s;
	  } 

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for (Sub s:subs) {
			sb.append(s.toString());	  
		  }    
		return sb.toString();
	  }
  }
