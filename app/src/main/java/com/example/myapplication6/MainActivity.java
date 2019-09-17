package com.example.myapplication6;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);
        parseXML();


    }

    private void parseXML() {
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open("data/All_in_One_GEN007_2015-07-06_11-31-03-74.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

            processParsing(parser);
        } catch (XmlPullParserException e) {

        } catch (IOException e) {

        }
    }

    private void processParsing(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<Rta> rta1 = new ArrayList<>();
        int eventType = parser.getEventType();
        Rta rta = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName= null ;

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();

                    if ("All_in_One_GEN007".equals(eltName)) {
                        rta = new Rta();
                        rta1.add(rta);
                    } else if (rta != null) {
                        if ("starttime".equals(eltName)) {
                            rta.starttime = parser.nextText();
                        } else if ("deviceid".equals(eltName)) {
                            rta.deviceid = parser.nextText();
                        } else if ("subscriberid".equals(eltName)) {
                            rta.subscriberid = parser.nextText();
                        } else if ("simid".equals(eltName)) {
                            rta.simid = parser.nextText();
                        } else if ("random_cal1".equals(eltName)) {
                            rta.random_cal1 = parser.nextText();
                        } else if ("random_cal2".equals(eltName)) {
                            rta.random_cal2 = parser.nextText();
                        } else if ("instanceID".equals(eltName)) {
                            rta.instanceID = parser.nextText();
                        } else if ("instanceName".equals(eltName)) {
                            rta.instanceName = parser.nextText();
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }
        printXml(rta1);
    }


    private void printXml ( ArrayList<Rta> rta1) {
        StringBuilder builder = new StringBuilder();

        for ( Rta rta : rta1) {
            builder.append(rta.starttime).append("\n").
                    append(rta.deviceid).append("\n").
                    append(rta.subscriberid).append("\n").
                    append(rta.simid).append("\n").
                    append(rta.random_cal1).append("\n").
                    append(rta.random_cal2).append("\n").
                    append(rta.instanceName).append("\n").
                    append(rta.instanceID).append("\n");
        }
        txt.setText(builder.toString());
    }
}
