package com.example.faradarsjsonxml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FlowerAdapter extends ArrayAdapter<Flower> {

    private Context context;
    private List<Flower> flowerList;
    public FlowerAdapter(Context context, List<Flower>flowerList) {
        super(context, R.layout.flower_list_item,flowerList);
        this.context=context;
        this.flowerList=flowerList;
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context)
                    .inflate(R.layout.flower_list_item,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.fill(position);
        return convertView;
    }
    public class ViewHolder{
        ImageView flowerImage;
        TextView flowerName, flowerPrice, flowerCategory;

        public ViewHolder(View view){
            flowerName=(TextView)view.findViewById(R.id.flower_name);
            flowerPrice=(TextView)view.findViewById(R.id.flower_price);;
            flowerCategory=(TextView)view.findViewById(R.id.flower_category); ;
            flowerImage=(ImageView)view.findViewById(R.id.flower_image);
        }
        public void fill(int position){
            Flower flower =flowerList.get(position);
            flowerName.setText(flower.getName());
            flowerPrice.setText(String.valueOf(flower.getPrice()));
            flowerCategory.setText(flower.getCategory());
            //load image
            String photoName=flower.getPhoto();
            if(photoName.contains(".")){
                photoName=photoName.substring(0,photoName.lastIndexOf('.'));
            }
            int imgResId=context.getResources().getIdentifier(
                    photoName,"drawable",context.getApplicationContext().getPackageName());
            flowerImage.setImageResource(imgResId);
        }

    }
}
