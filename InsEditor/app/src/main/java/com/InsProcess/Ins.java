package com.InsProcess;

import com.InsProcess.Sub;

public class Ins {
	Sub sub;
	final String lineStart="    ";
	final String lineEnd=";\n";

	StringBuilder stringBuilder=new StringBuilder();

	Ins(Sub sub) {
		this.sub = sub;
	  }

	@Override
	public String toString() {
		return stringBuilder.toString();
	  }

  }
