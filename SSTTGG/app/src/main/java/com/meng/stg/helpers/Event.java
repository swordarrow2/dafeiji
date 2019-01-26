package com.meng.stg.helpers;


public class Event{

    public String EventString;
    public float rand;
    public int special;
    public int special2;
    public String condition;
    public String condition2;
    public String result;
    public int contype;
    public int contype2;
    public String opreator;
    public String opreator2;
    public String collector;
    public int change;
    public int changetype;
    public int changevalue;
    public int changename;
    public float res;
    public int times;
    public int time;
    public boolean noloop;

    public void String2EmitterEvent()
    {
        String eventString =  EventString;
        String str1 = eventString.split("：")[0];
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "";
        String str6 = "";
        String str7 = eventString.split("：")[1];
        float num1 = 0.0f;
        int num2 = 0;
        if (eventString.contains("且"))
        {
            str6 = "且";
            str2 = str1.split("且".ToCharArray())[1];
            str1 = str1.split("且".ToCharArray())[0];
        }
        else if (eventString.contains("或"))
        {
            str6 = "或";
            str2 = str1.split("或".ToCharArray())[1];
            str1 = str1.split("或".ToCharArray())[0];
        }
        if (str1.contains(">"))
        {
            str3 = str1.split(">")[0];
            str4 = ">";
            str1 = str1.split(">")[1];
        }
        else if (str1.contains("="))
        {
            str3 = str1.split("=")[0];
            str4 = "=";
            str1 = str1.split("=")[1];
        }
        else if (str1.contains("<"))
        {
            str3 = str1.split("<")[0];
            str4 = "<";
            str1 = str1.split("<")[1];
        }
        if (str2.contains(">"))
        {
            String str8 = str2.split(">")[0];
            str5 = ">";
            str2 = str2.split(">")[1];
        }
        else if (str2.contains("="))
        {
            String str8 = str2.split("=")[0];
            str5 = "=";
            str2 = str2.split("=")[1];
        }
        else if (str2.contains("<"))
        {
            String str8 = str2.split("<")[0];
            str5 = "<";
            str2 = str2.split("<")[1];
        }
        if (eventString.contains("变化到"))
        {
            int num3 = 0;
            String[] strArray = str7.split("变".ToCharArray())[1].split("，".ToCharArray());
            int result = (int) Hash.results[str7.split("变".ToCharArray())[0]];
            String str8 = str7.split("变".ToCharArray())[0];
            if (strArray[0].replace("化到", "").contains("\\+"))
            {
                if (strArray[0].replace("化到", "").split("\\+")[0] == "自身")
                    num2 = 3;
                else if (strArray[0].replace("化到", "").split("\\+")[0] == "自机")
                    num2 = 4;
                else
                    num1 = Float.parseFloat(strArray[0].replace("化到", "").split("\\+")[0]);
            }
            else if (strArray[0].replace("化到", "") == "自身")
                num2 = 3;
            else if (strArray[0].replace("化到", "") == "自机")
                num2 = 4;
            else
                num1 = Float.parseFloat(strArray[0].replace("化到", ""));
            String str9 = strArray[1];
            int num4 = Integer.parseInt(strArray[2].split("帧".ToCharArray())[0]);
            condition = str1;
            this.result = str7;
            condition2 = str2;
            contype = (int) Hash.conditions[str3];
            contype2 = (int) Hash.conditions[str3];
            opreator = str4;
            opreator2 = str5;
            collector = str6;
            change = num3;
            changetype = (int) Hash.type[str9];
            changevalue = result;
            changename = (int) Hash.results[str8];
            res = num1;
            special = num2;
            if (strArray[0].replace("化到", "").contains("\\+"))
                rand = Float.parseFloat(strArray[0].replace("化到", "").split("\\+")[1]);
            times = num4;
            if (!strArray[2].contains("\\("))
                return;
            time = Integer.parseInt(strArray[2].split("\\(")[1].split("\\)")[0]);
        }
        else if (eventString.contains("增加"))
        {
            int num3 = 1;
            String[] strArray = str7.split("增".ToCharArray())[1].split("，".ToCharArray());
            strArray[0] = strArray[0].replace("加", "");
            int result = (int) Hash.results[str7.split("增".ToCharArray())[0]];
            String str8 = str7.split("增".ToCharArray())[0];
            if (strArray[0].contains("\\+"))
            {
                if (strArray[0].split("\\+")[0] == "自身")
                    num2 = 3;
                else if (strArray[0].split("\\+")[0] == "自机")
                    num2 = 4;
                else
                    num1 = Float.parseFloat(strArray[0].split("\\+")[0]);
            }
            else if (strArray[0] == "自身")
                num2 = 3;
            else if (strArray[0] == "自机")
                num2 = 4;
            else
                num1 = Float.parseFloat(strArray[0]);
            String str9 = strArray[1];
            int num4 = Integer.parseInt(strArray[2].split("帧".ToCharArray())[0]);
            condition = str1;
            this.result = str7;
            condition2 = str2;
            contype = (int) Hash.conditions[str3];
            contype2 = (int) Hash.conditions[str3];
            opreator = str4;
            opreator2 = str5;
            collector = str6;
            change = num3;
            changetype = (int) Hash.type[str9];
            changevalue = result;
            changename = (int) Hash.results[str8];
            res = num1;
            special = num2;
            if (strArray[0].contains("\\+"))
                rand = Float.parseFloat(strArray[0].split("\\+")[1]);
            times = num4;
            if (!strArray[2].contains("\\("))
                return;
            time = Integer.parseInt(strArray[2].split("\\(")[1].split("\\)")[0]);
        }
        else if (eventString.contains("减少"))
        {
            int num3 = 2;
            String[] strArray = str7.split("减少".ToCharArray())[2].split("，".ToCharArray());
            int result = (int) Hash.results[str7.split("减少".ToCharArray())[0]];
            String str8 = str7.split("减少".ToCharArray())[0];
            if (strArray[0].contains("\\+"))
            {
                if (strArray[0].split("\\+")[0] == "自身")
                    num2 = 3;
                else if (strArray[0].split("\\+")[0] == "自机")
                    num2 = 4;
                else
                    num1 = Float.parseFloat(strArray[0].split("\\+")[0]);
            }
            else if (strArray[0] == "自身")
                num2 = 3;
            else if (strArray[0] == "自机")
                num2 = 4;
            else
                num1 = Float.parseFloat(strArray[0]);
            String str9 = strArray[1];
            int num4 = Integer.parseInt(strArray[2].split("帧".ToCharArray())[0]);
            condition = str1;
            this.result = str7;
            condition2 = str2;
            contype = (int) Hash.conditions[str3];
            contype2 = (int) Hash.conditions[str3];
            opreator = str4;
            opreator2 = str5;
            collector = str6;
            change = num3;
            changetype = (int) Hash.type[str9];
            changevalue = result;
            changename = (int) Hash.results[str8];
            res = num1;
            special = num2;
            if (strArray[0].contains("\\+"))
                rand = Float.parseFloat(strArray[0].split("\\+")[1]);
            times = num4;
            if (!strArray[2].contains("\\("))
                return;
            time = Integer.parseInt(strArray[2].split("\\(")[1].split("\\)")[0]);
        }
        else if (eventString.contains("恢复"))
        {
            special = 1;
            opreator = str4;
            opreator2 = str5;
            condition = str1;
            condition2 = str2;
            contype = (int) Hash.conditions[str3];
            contype2 = (int) Hash.conditions[str3];
            collector = str6;
        }
        else if (eventString.contains("发射"))
        {
            special = 2;
            opreator = str4;
            opreator2 = str5;
            condition = str1;
            condition2 = str2;
            contype = (int) Hash.conditions[str3];
            contype2 = (int) Hash.conditions[str3];
            collector = str6;
        }
    }

    public void String2BulletEvent()
    {
        String eventString =  EventString;
        String str1 = eventString.split("：")[0];
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "";
        String str6 = "";
        String str7 = eventString.split("：")[1];
        float num1 = 0.0f;
        int num2 = 0;
        if (eventString.contains("且"))
        {
            str6 = "且";
            str2 = str1.split("且".ToCharArray())[1];
            str1 = str1.split("且".ToCharArray())[0];
        }
        else if (eventString.contains("或"))
        {
            str6 = "或";
            str2 = str1.split("或".ToCharArray())[1];
            str1 = str1.split("或".ToCharArray())[0];
        }
        if (str1.contains(">"))
        {
            str3 = str1.split(">")[0];
            str4 = ">";
            str1 = str1.split(">")[1];
        }
        else if (str1.contains("="))
        {
            str3 = str1.split("=")[0];
            str4 = "=";
            str1 = str1.split("=")[1];
        }
        else if (str1.contains("<"))
        {
            str3 = str1.split("<")[0];
            str4 = "<";
            str1 = str1.split("<")[1];
        }
        if (str2.contains(">"))
        {
            String str8 = str2.split(">")[0];
            str5 = ">";
            str2 = str2.split(">")[1];
        }
        else if (str2.contains("="))
        {
            String str8 = str2.split("=")[0];
            str5 = "=";
            str2 = str2.split("=")[1];
        }
        else if (str2.contains("<"))
        {
            String str8 = str2.split("<")[0];
            str5 = "<";
            str2 = str2.split("<")[1];
        }
        if (eventString.contains("变化到"))
        {
            int num3 = 0;
            String[] strArray = str7.split("变".ToCharArray())[1].split("，".ToCharArray());
            int num4 = (int) Hash.results2[str7.split("变".ToCharArray())[0]];
            String str8 = str7.split("变".ToCharArray())[0];
            if (strArray[0].replace("化到", "").contains("\\+"))
            {
                if (strArray[0].replace("化到", "").split("\\+")[0] == "自身")
                    num2 = 3;
                else if (strArray[0].replace("化到", "").split("\\+")[0] == "自机")
                    num2 = 4;
                else
                    num1 = Float.parseFloat(strArray[0].replace("化到", "").split("\\+")[0]);
            }
            else if (strArray[0].replace("化到", "") == "自身")
                num2 = 3;
            else if (strArray[0].replace("化到", "") == "自机")
                num2 = 4;
            else
                num1 = Float.parseFloat(strArray[0].replace("化到", ""));
            String str9 = strArray[1];
            int num5 = Integer.parseInt(strArray[2].split("帧".ToCharArray())[0]);
            condition = str1;
            result = str7;
            condition2 = str2;
            contype = (int) Hash.conditions2[str3];
            contype2 = (int) Hash.conditions2[str3];
            opreator = str4;
            opreator2 = str5;
            collector = str6;
            change = num3;
            changetype = (int) Hash.type[str9];
            changevalue = num4;
            changename = (int) Hash.results2[str8];
            res = num1;
            special = num2;
            if (strArray[0].replace("化到", "").contains("\\+"))
                rand = Float.parseFloat(strArray[0].replace("化到", "").split("\\+")[1]);
            times = num5;
            if (!strArray[2].contains("\\("))
                return;
            time = Integer.parseInt(strArray[2].split("\\(")[1].split("\\)")[0]);
        }
        else if (eventString.contains("增加"))
        {
            int num3 = 1;
            String[] strArray = str7.split("增".ToCharArray())[1].split("，".ToCharArray());
            strArray[0] = strArray[0].replace("加", "");
            int num4 = (int) Hash.results2[str7.split("增".ToCharArray())[0]];
            String str8 = str7.split("增".ToCharArray())[0];
            if (strArray[0].contains("\\+"))
            {
                if (strArray[0].split("\\+")[0] == "自身")
                    num2 = 3;
                else if (strArray[0].split("\\+")[0] == "自机")
                    num2 = 4;
                else
                    num1 = Float.parseFloat(strArray[0].split("\\+")[0]);
            }
            else if (strArray[0] == "自身")
                num2 = 3;
            else if (strArray[0] == "自机")
                num2 = 4;
            else
                num1 = Float.parseFloat(strArray[0]);
            String str9 = strArray[1];
            int num5 = Integer.parseInt(strArray[2].split("帧".ToCharArray())[0]);
            condition = str1;
            result = str7;
            condition2 = str2;
            contype = (int) Hash.conditions2[str3];
            contype2 = (int) Hash.conditions2[str3];
            opreator = str4;
            opreator2 = str5;
            collector = str6;
            change = num3;
            changetype = (int) Hash.type[str9];
            changevalue = num4;
            changename = (int) Hash.results2[str8];
            res = num1;
            special = num2;
            if (strArray[0].contains("\\+"))
                rand = Float.parseFloat(strArray[0].split("\\+")[1]);
            times = num5;
            if (!strArray[2].contains("\\("))
                return;
            time = Integer.parseInt(strArray[2].split("\\(")[1].split("\\)")[0]);
        }
        else if (eventString.contains("减少"))
        {
            int num3 = 2;
            String[] strArray = str7.split("减少".ToCharArray())[2].split("，".ToCharArray());
            int num4 = (int) Hash.results2[str7.split("减少".ToCharArray())[0]];
            String str8 = str7.split("减少".ToCharArray())[0];
            if (strArray[0].contains("\\+"))
            {
                if (strArray[0].split("\\+")[0] == "自身")
                    num2 = 3;
                else if (strArray[0].split("\\+")[0] == "自机")
                    num2 = 4;
                else
                    num1 = Float.parseFloat(strArray[0].split("\\+")[0]);
            }
            else if (strArray[0] == "自身")
                num2 = 3;
            else if (strArray[0] == "自机")
                num2 = 4;
            else
                num1 = Float.parseFloat(strArray[0]);
            String str9 = strArray[1];
            int num5 = Integer.parseInt(strArray[2].split("帧")[0]);
            condition = str1;
            result = str7;
            condition2 = str2;
            contype = (int) Hash.conditions2[str3];
            contype2 = (int) Hash.conditions2[str3];
            opreator = str4;
            opreator2 = str5;
            collector = str6;
            change = num3;
            changetype = (int) Hash.type[str9];
            changevalue = num4;
            changename = (int) Hash.results2[str8];
            res = num1;
            special = num2;
            if (strArray[0].contains("\\+"))
                rand = Float.parseFloat(strArray[0].split("\\+")[1]);
            times = num5;
            if (!strArray[2].contains("\\("))
                return;
            time = Integer.parseInt(strArray[2].split("\\(")[1].split("\\)")[0]);
        }
        else if (eventString.contains("恢复"))
        {
            special = 1;
            opreator = str4;
            opreator2 = str5;
            condition = str1;
            condition2 = str2;
            contype = (int) Hash.conditions2[str3];
            contype2 = (int) Hash.conditions2[str3];
            collector = str6;
        }
        else if (eventString.contains("发射"))
        {
            special = 2;
            opreator = str4;
            opreator2 = str5;
            condition = str1;
            condition2 = str2;
            contype = (int) Hash.conditions2[str3];
            contype2 = (int) Hash.conditions2[str3];
            collector = str6;
        }
    }

    public object Clone()
    {
        return  MemberwiseClone();
    }
}
