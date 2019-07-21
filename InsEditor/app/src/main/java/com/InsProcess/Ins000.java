package com.InsProcess;

public class Ins000 extends Ins {

    Ins000(Sub sub) {
        super(sub);
    }

    // ins_15("BossCard7_at4", _SS 20, _SS 3);
    public Ins000 _11(String name, String... args) {
        stringBuilder.append(lineStart).append("ins_11(\"").append(name).append("\"");
        for (String s : args) {
            stringBuilder.append(", ").append(s);
        }
        stringBuilder.append(")").append(lineEnd);
        return this;
    }

	public Ins000 _15(String name, String... args) {
        stringBuilder.append(lineStart).append("ins_15(\"").append(name).append("\"");
        for (String s : args) {
            stringBuilder.append(", ").append(s);
		  }
        stringBuilder.append(")").append(lineEnd);
        return this;
	  }
	
	
    public Ins000 diffSwitch(int e, int n, int h, int l, int o) {
        stringBuilder.append("!E").append("\n  ").append(e).append(";\n!N")
                .append("\n  ").append(n).append(";\n!H")
                .append("\n  ").append(h).append(";\n!L")
                .append("\n  ").append(l).append(";\n!O")
                .append("\n  ").append(o).append(";\n!*\n");
        return this;
    }

    public String getStack(int i) {
        return "[" + i + "]";
    }

    public String transferInt(String name) {
        return "_SS " + name;
    }

    public String transferFloat(String name) {
        return "_ff " + name;
    }

    public String transferIntToFloat(String name) {
        return "_fS " + name;
    }

    public String transferFloatToInt(String name) {
        return "_Sf " + name;
    }

}
