package com.gagit.snapp.osta.ostasnappgadget;

import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.util.ArrayList;

public class user_class
{


    public static String profile_status;
    public static String uid;
    public static String mobile;
    public static String SHA;
    public static String money;
    public static String fname;
    public static String lname;
    public static String datecreate;
    public static String national_code;
    public static String identity_card;
    public static String picture;
    public static String subscription_title;
    public static String subscription_count;
    public static String count_all_proposal;
    public static String count_special_proposal;
    public static String count_nospecial_proposal;
    public static String Work_history;
    public static String auth_status;

    public static Boolean alert=true;

    public static ArrayList<String> notification_text=new ArrayList<>();



    public user_class(String json,String SHA)
    {

        try
        {

            JsonParser jsonParser=new JsonParser();
            Object object=jsonParser.parse(json);
            JsonObject object1=(JsonObject)object;






            try
            {
                alert= (object1.get("alert").getAsString().equals("404"))?false:true;
            }
            catch (Exception Err)
            {

            }





            this.SHA=SHA;
            profile_status= object1.get("profile_status").getAsString();
            uid= object1.get("uid").getAsString();
            mobile= object1.get("mobile").getAsString();
            fname= object1.get("fname").getAsString();
            lname= object1.get("lname").getAsString();
            picture= object1.get("picture").getAsString();
            subscription_title= object1.get("subscription_title").getAsString();
            subscription_count= object1.get("subscription_count").getAsString();
            Work_history=object1.get("work_history").getAsString();
            count_all_proposal= object1.get("count_all_proposal").getAsString();
            count_special_proposal= object1.get("count_special_proposal").getAsString();
            count_nospecial_proposal= object1.get("count_nospecial_proposal").getAsString();
            auth_status=object1.get("auth_status").getAsString();


//            national_code= object1.get("national_code").getAsString();
//            identity_card= object1.get("identity_card").getAsString();
//            money= object1.get("money").getAsString();
//            datecreate= object1.get("datecreate").getAsString();

        }
        catch (Exception Err)
        {

        }




    }




    public static void create_notifications_list(String json)
    {

        try
        {
            JSONArray jsonArray=new JSONArray(json);

            for(int a=0;a<jsonArray.length();a++)
            {
                notification_text.add(jsonArray.getJSONObject(a).getString("text"));
            }

        }
        catch (Exception Err)
        {

        }

    }






}
