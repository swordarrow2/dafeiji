package com.meng.dexReader;

import android.content.*;
import android.content.res.*;
import android.widget.*;

import java.io.*;

import org.xmlpull.v1.*;

public class xmlParser {
    Context c;
    int i = 0;
    static String xmlPath = "/storage/emulated/0/mFramework.xml";
    boolean parsered = false;

    public xmlParser(Context context) {
        c = context;
    }

    public int getXmlLength() {
        run();
        return i;
    }

    public boolean isParsedSeccuss() {
        return parsered;
    }

    public void startParse() {
        parsered = false;
        Thread t = new parseXml();
        t.start();
    }

    class parseXml extends Thread {
        @Override
        public void run() {
            i = 0;
            try {
                File xmlFile = new File(xmlPath);
                InputStream is = new FileInputStream(xmlFile);
                XmlPullParserFactory fac = XmlPullParserFactory.newInstance();
                XmlPullParser xrp = fac.newPullParser();
                xrp.setInput(is, "utf-8");
                while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                    if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                        String tagName = xrp.getName();
                        if (tagName.equals("path")) {
                            MainActivity.dexPaths[i] = xrp.getAttributeValue(0);
                            i++;
                        }
                    }
                    xrp.next();
                }
            } catch (Exception e) {
                Toast.makeText(c, e.toString(), Toast.LENGTH_SHORT).show();
            }
            parsered = true;
        }
    }

    public void run() {
        try {
            File xmlFile = new File(xmlPath);
            if (!xmlFile.exists()){
                fileTools.saveTextFile("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<paths>\n</paths>",xmlPath);
            }
            InputStream is = new FileInputStream(xmlFile);
            XmlPullParserFactory fac = XmlPullParserFactory.newInstance();
            XmlPullParser xrp = fac.newPullParser();
            xrp.setInput(is, "utf-8");
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    if (xrp.getName().equals("path")) {
                        i++;
                    }
                }
                xrp.next();
            }
        } catch (Exception e) {
            Toast.makeText(c, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

}

	
