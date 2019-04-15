package com.meng.grzxv2.javaBean.bilibili;

import java.util.ArrayList;

public class BilibiliPersonInfo {
    public int code = 0;
    public String message = "";
    public Data data = new Data();

    public ArrayList<String> toArrayList(){
        ArrayList<String > list=new ArrayList<>();
        list.add("code:"+code);
        list.add("message:"+message);
        list.addAll(data.toArrayList());
        return list;
    }
}
