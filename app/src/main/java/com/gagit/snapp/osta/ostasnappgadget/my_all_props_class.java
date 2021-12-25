package com.gagit.snapp.osta.ostasnappgadget;

import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;

public class my_all_props_class
{


    public static ArrayList<String> id=new ArrayList<>();
    public static ArrayList<String> is_special=new ArrayList<>();
    public static ArrayList<String> name=new ArrayList<>();
    public static ArrayList<String> picture=new ArrayList<>();
    public static ArrayList<String> price=new ArrayList<>();
    public static ArrayList<String> proposal_count=new ArrayList<>();
    public static ArrayList<String> status=new ArrayList<>();
    public static ArrayList<String> datecreate=new ArrayList<>();
    public static ArrayList<String> messages=new ArrayList<>();
    public static int Count=0;



    public my_all_props_class(String json)
    {

        try
        {

            id.clear();
            is_special.clear();
            name.clear();
            picture.clear();
            price.clear();
            proposal_count.clear();
            status.clear();
            datecreate.clear();
            messages.clear();
            Count=0;


            JSONArray jsonArray = new JSONArray(json);


            for(int a=0;a<jsonArray.length();a++)
            {
                id.add(jsonArray.getJSONObject(a).getString("id")+"");
                is_special.add(jsonArray.getJSONObject(a).getString("is_special")+"");
                name.add(jsonArray.getJSONObject(a).getString("name")+"");
                picture.add(jsonArray.getJSONObject(a).getString("picture")+"");
                price.add(jsonArray.getJSONObject(a).getString("price")+"");
                proposal_count.add(jsonArray.getJSONObject(a).getString("proposal_count")+"");
                status.add(jsonArray.getJSONObject(a).getString("status")+"");
                datecreate.add(jsonArray.getJSONObject(a).getString("datecreate")+"");
                messages.add(jsonArray.getJSONObject(a).getString("messages")+"");
                Count++;
            }

//            Log.i("Err",id.get(0));
        }
        catch (Exception Err)
        {
            Log.i("Err",Err.getMessage());
        }
    }

}
