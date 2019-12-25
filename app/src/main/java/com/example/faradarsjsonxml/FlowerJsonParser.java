package com.example.faradarsjsonxml;

import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FlowerJsonParser {

    public static List<Flower>parseJson(InputStream input){
        String content=Utils.convertInputStreamToString(input);
        return parseJson(content);
    }

    public static List<Flower>parseJson(String jsonString){
        List<Flower> flowerList=new ArrayList<>();
        try {
            JSONArray json=new JSONArray(jsonString);
            for (int i=0; i<json.length();i++){
                JSONObject jsonObject=json.getJSONObject(i);
                Flower flower=new Flower();
                flower.setName(jsonObject.getString("name"));
                flower.setCategory(jsonObject.getString("category"));
                flower.setProductId(jsonObject.getLong("productId"));
                flower.setInstructions(jsonObject.getString("instructions"));
                flower.setPhoto(jsonObject.getString("photo"));
                flower.setPrice(jsonObject.getDouble("price"));
                flowerList.add(flower);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return flowerList;
    }

}
