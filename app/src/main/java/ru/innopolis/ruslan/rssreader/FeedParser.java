package ru.innopolis.ruslan.rssreader;

/**
 * Created by ruslan on 21.07.2017.
 */

import android.content.Context;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FeedParser {

    private Context context;

    public FeedParser(Context context) {
        this.context = context;
    }

    private static final String ns = null;

    public List<Publication> readChannel(XmlPullParser parser) throws IOException, XmlPullParserException {

        List<Publication> items = new ArrayList<>();

        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                if (parser.getName().equalsIgnoreCase("item")) {
                    items.add(readPublication(parser));
                }
            }
            eventType = parser.next();
        }
        return items;
    }

    private Publication readPublication(XmlPullParser parser) throws IOException, XmlPullParserException {

        int eventType = parser.getEventType();

        parser.require(XmlPullParser.START_TAG, ns, "item");

        String title = null, description = null, link = null, pubDate = null, creator = null, category = null;

        while (!(eventType == XmlPullParser.END_TAG && parser.getName().equalsIgnoreCase("item"))) {
            if (eventType != XmlPullParser.START_TAG) {
                eventType = parser.next();
                continue;
            }
            switch (parser.getName()) {
                case "title":
                    title = readTitle(parser);
                    break;
                case "link":
                    link = readLink(parser);
                    break;
                case "description":
                    description = readDescription(parser);
                    break;
                case "pubDate":
                    pubDate = readDate(parser);
                    break;
                case "dc:creator":
                    creator = readCreator(parser);
                    break;
                case "category":
                    category = readCategory(parser);
                    break;
             }
            eventType = parser.next();
        }

        return new Publication(title, link, description, pubDate, creator, category);
    }

    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");

        return title;
    }

    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException{

        parser.require(XmlPullParser.START_TAG, ns, "link");
        String link = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "link");

        return link;
    }

    private String readDescription(XmlPullParser parser) throws IOException, XmlPullParserException{

        parser.require(XmlPullParser.START_TAG, ns, "description");
        String description = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "description");

        return description;
    }

    private String readCreator(XmlPullParser parser) throws IOException, XmlPullParserException{

        parser.require(XmlPullParser.START_TAG, ns, "dc:creator");
        String creator = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "dc:creator");

        return creator;
    }

    private String readCategory(XmlPullParser parser) throws IOException, XmlPullParserException{

        parser.require(XmlPullParser.START_TAG, ns, "category");
        String category = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "category");

        return category;
    }

    private String readDate(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, ns, "pubDate");
        String date = readText(parser);

        try {

            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            Date newDate = format.parse(date);
            format = new SimpleDateFormat("dd.MM.yyyy 'в' HH:mm");
            date = format.format(newDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        parser.require(XmlPullParser.END_TAG, ns, "pubDate");
        return date;
    }




    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {

        String result = "";

        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }

        return result;
    }

}