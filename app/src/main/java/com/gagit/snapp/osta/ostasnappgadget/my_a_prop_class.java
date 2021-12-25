package com.gagit.snapp.osta.ostasnappgadget;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class my_a_prop_class
{



    public static String id;
    public static String name;
    public static String picture;
    public static String proposal_count;
    public static String date_create;
    public static String date_order;
    public static String text;
    public static String geo;
    public static JsonArray Propos_all;
    public static JsonArray items;
    public static String message_count;
    public static String is_spicial;
    public static String status;
    public static String my_price;
    public static String my_datecreate;
    public static String my_description;
    public static String ucid;
    public static String order_id;
    public static String user_mobile="";


    public static String alert_404;
    public static String image_404;
    public static String text_404;


    public my_a_prop_class(String Json)
    {

//        Log.i("Err",Json);
        JsonParser jsonParser = new JsonParser();
        Object object = jsonParser.parse(Json);
        JsonObject object1 = (JsonObject) object;
        try
        {

            if(Json.indexOf("404")<0)
            {

                id = object1.get("id").getAsString() + "";
                ucid = object1.get("ucid").getAsString() + "";
                order_id = object1.get("orderid").getAsString() + "";
                name = object1.get("name").getAsString() + "";
                picture = object1.get("picture").getAsString() + "";
                proposal_count = object1.get("proposal_count").getAsString() + "";
                date_create = object1.get("datecreate").getAsString() + "";
                date_order = object1.get("dateorder").getAsString() + "";
                text = object1.get("text").getAsString() + "";
                geo = object1.get("geo").getAsString() + "-";
                message_count=object1.get("messages").getAsString()+"";
                is_spicial=object1.get("is_special").getAsString()+"";
                status=object1.get("status").getAsString()+"";
                my_price=object1.get("my_price").getAsString()+"";
                my_datecreate=object1.get("my_datecreate").getAsString()+"";
                my_description=object1.get("my_description").getAsString()+"";
                items=object1.getAsJsonArray("item").getAsJsonArray();
                Propos_all=object1.getAsJsonArray("proposal").getAsJsonArray();


                try
                {
                    user_mobile = object1.get("user_mobile").getAsString() + "";
                }
                catch (Exception Err)
                {

                }


            }
            else
            {
                alert_404 = object1.get("alert").getAsString() + "";
                image_404 = object1.get("image").getAsString() + "";
                text_404 = object1.get("text").getAsString() + "";
            }


        }
        catch (Exception Err)
        {
            Log.i("Err", Err.getMessage());
        }



    }



    public my_a_prop_class()
    {
        id=name=picture=proposal_count=date_create=date_order=text=geo=alert_404=text_404=image_404=message_count=is_spicial=status=my_price=my_datecreate=my_description=user_mobile="";
    }








}
