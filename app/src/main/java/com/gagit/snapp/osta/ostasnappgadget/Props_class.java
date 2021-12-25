package com.gagit.snapp.osta.ostasnappgadget;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;

public class Props_class
{


    public static boolean show=false;

    public static String id;
    public static String name;
    public static String picture;
    public static String proposal_count;
    public static String date_create;
    public static String date_order;
    public static String show_btn;
    public static String text;
    public static String geo;
    public static JsonArray Propos_all;
    public static JsonArray items;

    public static String alert_404;
    public static String image_404;
    public static String text_404;




    public Props_class()
    {
        id=name=picture=proposal_count=date_create=date_order=show_btn=text=geo=alert_404=text_404=image_404="";
    }





    public static void done(String Json)
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
                name = object1.get("name").getAsString() + "";
                picture = object1.get("picture").getAsString() + "";
                proposal_count = object1.get("proposal_count").getAsString() + "";
                date_create = object1.get("datecreate").getAsString() + "";
                date_order = object1.get("dateorder").getAsString() + "";
                show_btn = object1.get("show_button").getAsString() + "";
                text = object1.get("text").getAsString() + "";
                geo = object1.get("geo").getAsString() + "-";



                items=object1.getAsJsonArray("item").getAsJsonArray();
                Propos_all=object1.getAsJsonArray("proposal").getAsJsonArray();



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







}
