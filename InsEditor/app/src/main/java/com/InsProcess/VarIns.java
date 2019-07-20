package com.InsProcess;

public class VarIns extends Ins {
    VarIns(Sub sub) {
        super(sub);
    }

    public VarIns assiFloat(String varName, float value) {
        stringBuilder.append(lineStart).append("%").append(varName).append(" = ")
                .append(value).append("f").append(lineEnd);
        return this;
    }

    public VarIns assiInt(String varName, int value) {
        stringBuilder.append(lineStart).append("$").append(varName).append(" = ").append(value).append(lineEnd);
        return this;
    }

    public VarIns argCount(int count) {
        if (count < 1) {
            return this;
        }
        stringBuilder.append(lineStart);
        stringBuilder.append("Var");
        for (int i = 65; i < 65 + count; ++i) {
            stringBuilder.append(" ");
            stringBuilder.append((char) i);
        }
        stringBuilder.append(lineEnd);
        return this;
    }
}
  
