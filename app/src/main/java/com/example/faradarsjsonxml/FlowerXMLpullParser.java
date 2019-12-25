package com.example.faradarsjsonxml;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FlowerXMLpullParser {

    private Context context;
    private List<Flower> flowerList;
    Flower currentFlower;
    String currentTag;

    public FlowerXMLpullParser(Context context){
        this.context=context;
    }
    public List<Flower> pullXml(){
        flowerList =new ArrayList<>();
        try {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser=factory.newPullParser();
            InputStream input=context.getResources().openRawResource(R.raw.flowers_xml);
            parser.setInput(input , null);
            int evenType = parser.getEventType();
            while (evenType!=XmlPullParser.END_DOCUMENT){
                if(evenType==XmlPullParser.START_TAG){
                    handleStartTag(parser.getName());
                }else if (evenType==XmlPullParser.TEXT){
                    handleTexr(parser.getText());
                }else if(evenType==XmlPullParser.END_TAG){
                    currentTag=null;
                }
                evenType=parser.next();

            }
            input.close();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }


        return flowerList;
    }

    private void handleTexr(String text) {
        if(currentFlower==null || currentTag==null) return;
        switch (currentTag){
            case "productId":
                currentFlower.setProductId(Long.valueOf(text));
                break;
            case "category":
                currentFlower.setCategory(text);
                break;
            case "name":
                currentFlower.setName(text);
                break;
            case "instructions":
                currentFlower.setInstructions(text);
                break;
            case "price":
                currentFlower.setPrice(Double.valueOf(text));
                break;
            case "photo":
                currentFlower.setPhoto(text);
                break;
                default: break;


        }
    }

    private void handleStartTag(String tagName) {
        if("product".equals(tagName)){
            currentFlower=new Flower();
            flowerList.add(currentFlower);
        }else {
            currentTag=tagName;
        }
    }


}
