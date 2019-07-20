package com.meng.TaiHunDanmaku.InsProcess;

public class VarIns extends Ins {
	VarIns(Sub sub) {
		super(sub);
	  }

	public VarIns a(float a) {
		sub.a = a;
		stringBuilder.append(lineStart);
		stringBuilder.append(String.format("var A = %f", a));
		stringBuilder.append(lineEnd);
		return this;
	  }

	public VarIns a(int a) {
		sub.a = a;
		stringBuilder.append(lineStart);
		stringBuilder.append(String.format("var A = %d", a));
		stringBuilder.append(lineEnd);
		return this;
	  }
	  
	public VarIns a() {
		stringBuilder.append(lineStart);
		stringBuilder.append("var A");
		stringBuilder.append(lineEnd);
		return this;
	  }

	public VarIns b(float b) {
		sub.b = b;
		stringBuilder.append(lineStart);
		stringBuilder.append(String.format("var B = %f", b));
		stringBuilder.append(lineEnd);
		return this;
	  }

	public VarIns b() {
		stringBuilder.append(lineStart);
		stringBuilder.append("var B");
		stringBuilder.append(lineEnd);
		return this;
	  }

	public VarIns c(float c) {
		sub.c = c;
		stringBuilder.append(lineStart);
		stringBuilder.append(String.format("var C = %f", c));
		stringBuilder.append(lineEnd);
		return this;
	  }
	public VarIns c() {
		stringBuilder.append(lineStart);
		stringBuilder.append("var C");
		stringBuilder.append(lineEnd);
		return this;
	  }

	public VarIns d(float d) {
		sub.d = d;
		stringBuilder.append(lineStart);
		stringBuilder.append(String.format("var D = %f", d));
		stringBuilder.append(lineEnd);
		return this;
	  }

	public VarIns d() {
		stringBuilder.append(lineStart);
		stringBuilder.append("var D");
		stringBuilder.append(lineEnd);
		return this;
	  }

	public String floatA() {
		return "_f(A)";
	  }

	public String intA() {
		return "_S(A)";
	  }

	public String floatB() {
		return "_f(B)";
	  }

	public String intB() {
		return "_S(B)";
	  }

	public String floatC() {
		return "_f(C)";
	  }

	public String intC() {
		return "_S(C)";
	  } 

	public String floatD() {
		return "_f(D)";
	  }
	
	public String intD() {
		return "_S(D)";
	  }  

  }
  
