package com.gagit.snapp.osta.ostasnappgadget;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.util.ArrayList;

public class database_class
{





    //All Vars Start
    public static int refresh_Counter=0;

    public static String API_URL="http://kachi.sorapp.ir/api/";
    public static String Sms_Key;
    public static String SHA="";
    public static ArrayList<String> Price_card_id=new ArrayList<>();
    public static ArrayList<String> Price_card_price=new ArrayList<>();
    public static ArrayList<String> Price_card_days=new ArrayList<>();
    public static ArrayList<String> Price_card_count=new ArrayList<>();

    public static ArrayList<String> N_Price_Id=new ArrayList<>();
    public static ArrayList<String> N_Price_Name=new ArrayList<>();
    public static ArrayList<String> N_Price_Img=new ArrayList<>();
    public static ArrayList<String> N_Price_Prop=new ArrayList<>();
    public static ArrayList<String> N_Price_Is_Select=new ArrayList<>();
    public static ArrayList<String> N_Price_date=new ArrayList<>();

    public static String Sname;
    public static String Site_APP_Version;
    public static String Site_Share_Text;
    public static String Header_URL;
    public static String Site_APP_Link;
    public static String Back_up_Phone_Number;
    //All Vars End





    //Get Sms Start
    public database_class(String phone_Number)
    {
        try
        {



            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httppost=new HttpPost(API_URL+"user_s/to_server/login.php");


            pairs.add(new BasicNameValuePair("security",code));
            pairs.add(new BasicNameValuePair("mobile",phone_Number));

            httppost.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));



            HttpResponse httpResponse=httpClient.execute(httppost);



            HttpEntity httpEntity=httpResponse.getEntity();


            String code_p= EntityUtils.toString(httpEntity);


            Log.i("Err",code_p);


            json_phone_code(code_p);



        }
        catch (Exception Error)
        {
            Log.i("Err2",Error.getMessage());
        }
    }
    //Get Sms Start




    //Get Sms Start
    private void json_phone_code(String json)
    {

        JsonParser jsonParser=new JsonParser();

        Object object=jsonParser.parse(json);

        JsonObject object1=(JsonObject)object;

        String alert= object1.get("alert").getAsString();
        String code= object1.get("code").getAsString();

//        Log.i("Err2","Alert Is "+alert);
//        Log.i("Err2","Code Is "+code);

        if(alert.trim().equals("\"success\"")||alert.trim().equals("success"))
        {
            Sms_Key=code;
            SHA=object1.get("sha_pre").getAsString();
        }


    }
    //Get Sms End






    //Get Start Class Start
    public database_class(String mobile, String SHA)
    {
        try
        {
            Get_User_Data(mobile, SHA);
            if(refresh_Counter==0)
            {
                get_config();
                get_notifications();
                refresh_Counter++;
            }
            Get_Price_List();
            get_my_all_props();
        }
        catch (Exception Err)
        {

        }
    }
    //Get Start Class End








    //Get User Data Start
    public void Get_User_Data(String mobile,String SHA)
    {
        try
        {
            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(API_URL+"user_s/to_client/get_user.php");

            pairs.add(new BasicNameValuePair("security",code));

            pairs.add(new BasicNameValuePair("mobile",SHA));

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            HttpEntity httpEntity=httpResponse.getEntity();

            String code_p= EntityUtils.toString(httpEntity);

            byte []codes=code_p.getBytes("ISO-8859-1");

            String result=new String(codes,"UTF-8");

            new user_class(result,SHA);

            get_my_all_props();

        }
        catch (Exception Error)
        {
//            Log.i("Err2",Error.getMessage());
        }
    }
    //Get User Data End










    //Get My All Props Json Start
    public void get_my_all_props()
    {
        try
        {

            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(API_URL+"user_s/to_client/get_proposal.php");

            pairs.add(new BasicNameValuePair("security",code));

            pairs.add(new BasicNameValuePair("uid",user_class.uid));

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            HttpEntity httpEntity=httpResponse.getEntity();

            String code_p= EntityUtils.toString(httpEntity);

            byte []codes=code_p.getBytes("ISO-8859-1");

            String result=new String(codes,"UTF-8");

//            Log.i("Ers",result);

            new my_all_props_class(result);

        }
        catch (Exception Error)
        {
            Log.i("Err2",Error.getMessage());
        }
    }
    //Get My All Props Json End











    //Get Price List Start
    public void Get_Price_List()
    {
        try
        {

            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(API_URL+"user_s/to_client/subscription.php");

            pairs.add(new BasicNameValuePair("security",code));

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            HttpEntity httpEntity=httpResponse.getEntity();

            String code_p= EntityUtils.toString(httpEntity);

//            Log.i("Err",code_p);

            json_Price_list(code_p);

        }
        catch (Exception Error)
        {
            Log.i("Err2",Error.getMessage());
        }
    }
    //Get Price List End









    //Convert Price Json To Arrya List Start
    public void json_Price_list(String json) throws Exception
    {


        Price_card_id.clear();
        Price_card_price.clear();
        Price_card_days.clear();
        Price_card_count.clear();

        JSONArray jsonArray=new JSONArray(json);

        for(int a=0;a<jsonArray.length();a++)
        {
            Price_card_id.add(jsonArray.getJSONObject(a).getString("id"));
            Price_card_price.add(jsonArray.getJSONObject(a).getString("price"));
            Price_card_days.add(jsonArray.getJSONObject(a).getString("days"));
            Price_card_count.add(jsonArray.getJSONObject(a).getString("count"));
        }


    }
    //Convert Price Json To Arrya List End














    //Get New Start
    public static void get_all_prices()
    {
        try
        {

            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(API_URL+"user_s/to_client/get_order.php");

            pairs.add(new BasicNameValuePair("security",code));

            pairs.add(new BasicNameValuePair("uid",user_class.uid));

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            HttpEntity httpEntity=httpResponse.getEntity();

            String code_p= EntityUtils.toString(httpEntity);

            byte []codes=code_p.getBytes("ISO-8859-1");

            String result=new String(codes,"UTF-8");

            convert_json_to_array_n_price(result);

        }
        catch (Exception Error)
        {
            Log.i("Err2",Error.getMessage());
        }
    }
    //Get New End











    //Convert New Price Json To Array List Start
    private static void convert_json_to_array_n_price(String json) throws Exception
    {

        JSONArray jsonArray=new JSONArray(json);

        N_Price_Id.clear();
        N_Price_Name.clear();
        N_Price_Img.clear();
        N_Price_Prop.clear();
        N_Price_Is_Select.clear();
        N_Price_date.clear();


        for(int a=0;a<jsonArray.length();a++)
        {
            N_Price_Id.add(jsonArray.getJSONObject(a).getString("id"));
            N_Price_Name.add(jsonArray.getJSONObject(a).getString("name"));
            N_Price_Img.add(jsonArray.getJSONObject(a).getString("picture"));
            N_Price_Prop.add(jsonArray.getJSONObject(a).getString("proposal"));
            N_Price_Is_Select.add(jsonArray.getJSONObject(a).getString("is_selected"));
            N_Price_date.add(jsonArray.getJSONObject(a).getString("dateorder"));
        }


    }
    //Convert New Price Json To Array List End















    //Get A Prop Ditales Start
    public static void get_prop_by_id(String id)
    {
        try
        {

            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(API_URL+"user_s/to_client/get_order_detail.php");

            pairs.add(new BasicNameValuePair("security",code));

            pairs.add(new BasicNameValuePair("id",id));

            pairs.add(new BasicNameValuePair("uid",user_class.uid));

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            HttpEntity httpEntity=httpResponse.getEntity();

            String code_p= EntityUtils.toString(httpEntity);

            byte []codes=code_p.getBytes("ISO-8859-1");

            String result=new String(codes,"UTF-8");

//            Log.i("Err",result);

            Props_class.done(result);

        }
        catch (Exception Error)
        {
            Log.i("Err2",Error.getMessage());
        }
    }
    //Get A Prop Ditales End














    //Get a My Prop Ditales Start
    public static void get_a_my_prop_by_id(String id)
    {
        try
        {
            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(API_URL+"user_s/to_client/get_proposal_detail.php");

            pairs.add(new BasicNameValuePair("security",code));

            pairs.add(new BasicNameValuePair("id",id));

            pairs.add(new BasicNameValuePair("uid",user_class.uid));

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            HttpEntity httpEntity=httpResponse.getEntity();

            String code_p= EntityUtils.toString(httpEntity);

            byte []codes=code_p.getBytes("ISO-8859-1");

            String result=new String(codes,"UTF-8");


//            Log.i("Err",result);

            new my_a_prop_class(result);

        }
        catch (Exception Error)
        {
            Log.i("Err2",Error.getMessage());
        }
    }
    //Get a My Prop Ditales End














    //Get Application Config From Json Start
    public static void get_config()
    {
        try
        {

            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(API_URL+"config.php");

            pairs.add(new BasicNameValuePair("security",code));

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            String json= EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

//            Log.i("Err3",json);

            Create_Config_data(json);

        }
        catch (Exception Error)
        {

        }

    }












    public static void Create_Config_data(String Json) throws Exception
    {

//        Log.i("Err",Json);

        JsonParser jsonParser=new JsonParser();

        Object object=(Object)jsonParser.parse(Json);

        JsonObject jsonObject=(JsonObject)object;



        Sname=jsonObject.get("sname").getAsString();

        Back_up_Phone_Number=jsonObject.get("support_telephone").getAsString();

        Site_APP_Version=jsonObject.get("android_s_version").getAsString();

        Site_Share_Text=jsonObject.get("sharetext").getAsString();

        Header_URL=jsonObject.get("logo").getAsString();

//        Log.i("Err3","Conf Done");

//        Header_URL="https://snapp-gadget.ir/data/app_logo/original.png";
    }
    //Get Application Config From Json End











    //Get Notifications From Server Start
    public void get_notifications()
    {
        try
        {
            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(API_URL+"user_s/to_client/notification.php");

            pairs.add(new BasicNameValuePair("security",code));

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            HttpEntity httpEntity=httpResponse.getEntity();

            String code_p= EntityUtils.toString(httpEntity);

            byte []codes=code_p.getBytes("ISO-8859-1");

            String result=new String(codes,"UTF-8");

//            Log.i("Err",result);

            user_class.create_notifications_list(result);
        }
        catch (Exception Error)
        {
            Log.i("Err2",Error.getMessage());
        }
    }
    //Get Notifications From Server End









}
