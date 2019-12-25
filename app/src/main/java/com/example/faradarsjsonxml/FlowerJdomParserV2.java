package com.example.faradarsjsonxml;

import android.content.Context;

import org.jdom2.Element;

import java.io.InputStream;

public class FlowerJdomParserV2 extends XmlJdomParser<Flower>{
    InputStream input;
    public FlowerJdomParserV2(InputStream input){
        this.input=input;
    }

    @Override
    public InputStream getInput() {
        return input;
    }

    @Override
    public String getObjectsNodesKey() {
        return "product";
    }

    @Override
    public Flower getObjectFromNode(Element node) {
        Flower flower=new Flower();
        flower.setPhoto(node.getChildText("photo"));
        flower.setPrice(Double.valueOf(node.getChildText("price")));
        flower.setInstructions(node.getChildText("instructions"));
        flower.setName(node.getChildText("name"));
        flower.setCategory(node.getChildText("category"));
        flower.setProductId(Long.valueOf(node.getChildText("productId")));
        return flower;
    }
}
