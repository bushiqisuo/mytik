package com.example.mytik.util;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class XmlUtil {
    private static final String TAG = "XmlUtil";

    private static final String FILE_NAME = "test_v.xml";

    public void readXml() {
        XmlPullParser xmlPullParser = Xml.newPullParser();
        InputStream is = null;
        try {
            is = ContextHolder.getContext().getAssets().open(FILE_NAME);
            xmlPullParser.setInput(is, StandardCharsets.UTF_8.name());
            int type = xmlPullParser.getEventType();
            while (type !=  XmlPullParser.END_DOCUMENT) {
                //不是结尾tag
                switch (type) {
                    case XmlPullParser.START_DOCUMENT:
                        //初始化
                        break;
                    case XmlPullParser.START_TAG:
                        break;
                }
            }

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

    }

}
