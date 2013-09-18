package com.favorite.utils;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class SaxParseProjectService extends DefaultHandler {

    public FavoriteList getFavoriteList() {
        return favoriteList;
    }

    public class FavoriteList {
        public List<String> favoritePaths = new LinkedList<String>();
        public HashMap<String, String> favoriteEnviron = new LinkedHashMap<String, String>();
    }

    private FavoriteList favoriteList = new FavoriteList();

    private String nowTagName = "";
    private String nowTagAttributeName = "";

    private boolean hasList;

    public FavoriteList parse(InputStream xmlStream) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxParseProjectService handler = new SaxParseProjectService();
        parser.parse(xmlStream, handler);

        return handler.getFavoriteList();
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if ("list".equals(qName)) {
            hasList = true;
        }

        if ("option".equals(qName)) {
            if (hasList) {
                if (nowTagAttributeName.equals("favoriteEnviron")) {
                    String key = attributes.getValue("name");
                    String value = attributes.getValue("value");
                    favoriteList.favoriteEnviron.put(key, value);
                } else if (nowTagAttributeName.equals("favoritePaths")) {
                    String value = attributes.getValue("value");
                    favoriteList.favoritePaths.add(value);
                }
            } else {
                nowTagAttributeName = attributes.getValue("name");
            }
        }
        nowTagName = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("list".equals(qName)) {
            hasList = false;
        }
    }

}