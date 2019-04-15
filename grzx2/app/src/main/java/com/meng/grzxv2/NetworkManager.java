package com.meng.grzxv2;

import android.widget.*;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;

public class NetworkManager {

    public final String IP = "123.207.65.93";
    public final int PORT = 9760;
    private MainActivity mainActivity;
    private Gson gson = new Gson();

    public NetworkManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void send(NetworkType networkType, String text) {
        sendString(networkType.toString() + "." + text);
    }

    public void getJsonString() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket client = new Socket(IP, PORT);
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
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                }
            }
        }).start();
    }

    private void sendString(final String string) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket client = new Socket(IP, PORT);
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
