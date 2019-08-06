package com.meng.grzxConfig.MaterialDesign.helpers;

import android.widget.*;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;

import com.meng.grzxConfig.MaterialDesign.activity.MainActivity;

import android.os.*;

import com.meng.grzxConfig.MaterialDesign.javaBean.*;
import com.google.gson.*;

public class NetworkManager {
    private MainActivity mainActivity;

    public NetworkManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void send(NetworkType networkType, String text, BaseAdapter adapter) {
        sendString(networkType.toString() + "." + text, adapter);
    }

    public void getJsonString() {
        MainActivity.instence.threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket client = new Socket(mainActivity.editConfig.ip, mainActivity.editConfig.configPort);
                    OutputStream out = client.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    dos.writeUTF("getFull");
                    InputStream in = client.getInputStream();
                    DataInputStream dis = new DataInputStream(in);
                    final String result = dis.readUTF();
                    mainActivity.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mainActivity.loadConfigData(result);
                        }
                    });
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void sendString(final String string, final BaseAdapter adapter) {
        MainActivity.instence.threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket client = new Socket(mainActivity.editConfig.ip, mainActivity.editConfig.configPort);
                    OutputStream out = client.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    dos.writeUTF(string);
                    InputStream in = client.getInputStream();
                    DataInputStream dis = new DataInputStream(in);
                    final String result = dis.readUTF();
                    mainActivity.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(mainActivity, result.equals("ok") ? "成功" : "失败", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        }
                    });
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
