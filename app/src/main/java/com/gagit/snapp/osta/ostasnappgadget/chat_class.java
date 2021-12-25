package com.gagit.snapp.osta.ostasnappgadget;

import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.util.ArrayList;

public class chat_class
{



    public static ArrayList<String> chat_list_ucid=new ArrayList<>();
    public static ArrayList<String> chat_list_ucid_copy=new ArrayList<>();
    public static ArrayList<String> chat_list_orderid=new ArrayList<>();
    public static ArrayList<String> chat_list_text=new ArrayList<>();
    public static ArrayList<String> chat_list_time=new ArrayList<>();
    public static ArrayList<String> chat_list_tick=new ArrayList<>();
    public static ArrayList<String> chat_list_unread=new ArrayList<>();

    public static int Counter=0;
    public static int Counter_tik=0;
    public static int Counter_message=0;



    public static String ucid="";
    public static String order_id="";
    public static String send_alert="";



    public static ArrayList<String> chat_align=new ArrayList<>();
    public static ArrayList<String> chat_text=new ArrayList<>();
    public static ArrayList<String> chat_time=new ArrayList<>();
    public static ArrayList<String> chat_timestamp=new ArrayList<>();
    public static ArrayList<String> chat_tick=new ArrayList<>();



    public static Boolean refreshing=false;





    //Get Chat List Start
    public static void get_chat_list()
    {
        try
        {

            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(database_class.API_URL+"/user_s/to_client/get_conversation.php");

            pairs.add(new BasicNameValuePair("security",code));

            pairs.add(new BasicNameValuePair("uid",user_class.uid));

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            HttpEntity httpEntity=httpResponse.getEntity();

            String code_p= EntityUtils.toString(httpEntity);

            byte []codes=code_p.getBytes("ISO-8859-1");

            String result=new String(codes,"UTF-8");

//            Log.i("Err",result);

            convert_chat_list_json_to_array_list(result);

        }
        catch (Exception Error)
        {
            Log.i("Err2",Error.getMessage());
        }
    }
    //Get Chat List End








    //Convert Chat List Json To Array Start
    private static void convert_chat_list_json_to_array_list(String Json)
    {

        try
        {
            JSONArray jsonArray=new JSONArray(Json);

            chat_list_ucid_copy.clear();

            for(int a=0;a<chat_list_ucid.size();a++)
            {
                chat_list_ucid_copy.add(chat_list_ucid.get(a));
            }


            chat_list_ucid.clear();
            chat_list_orderid.clear();
            chat_list_text.clear();
            chat_list_time.clear();
            chat_list_tick.clear();
            chat_list_unread.clear();
            Counter=0;


            for(int a=0;a<jsonArray.length();a++)
            {
                chat_list_ucid.add(jsonArray.getJSONObject(a).getString("ucid"));
                chat_list_orderid.add(jsonArray.getJSONObject(a).getString("orderid"));
                chat_list_text.add(jsonArray.getJSONObject(a).getString("text"));
                chat_list_time.add(jsonArray.getJSONObject(a).getString("time"));
                chat_list_tick.add(jsonArray.getJSONObject(a).getString("tick"));
                chat_list_unread.add(jsonArray.getJSONObject(a).getString("unread"));
                Counter+=Integer.parseInt(jsonArray.getJSONObject(a).getString("unread"));
                Counter_tik+=Integer.parseInt(jsonArray.getJSONObject(a).getString("tick"));
                Counter_message++;
            }


            if(chat_list_ucid_copy.size()!=chat_list_ucid.size())
            {
                refreshing=true;
            }

        }
        catch (Exception Err)
        {

        }


    }
    //Convert Chat List Json To Array End










    //Get Chat List Start
    public static void get_chat(String ucid1,String order_id1)
    {
        try
        {

            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(database_class.API_URL+"/user_s/to_client/get_chat.php");

            pairs.add(new BasicNameValuePair("security",code));

            pairs.add(new BasicNameValuePair("uid",user_class.uid));

            ucid=ucid1;

            pairs.add(new BasicNameValuePair("ucid",ucid1));

            order_id=order_id1;

            pairs.add(new BasicNameValuePair("orderid",order_id1));

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            HttpEntity httpEntity=httpResponse.getEntity();

            String code_p= EntityUtils.toString(httpEntity);

            byte []codes=code_p.getBytes("ISO-8859-1");

            String result=new String(codes,"UTF-8");

//            Log.i("Err",result);

            convert_chat_json(result);

        }
        catch (Exception Error)
        {
            Log.i("Err2",Error.getMessage());
        }
    }
    //Get Chat List End













    //Get Chat List Start
    public static void get_chat()
    {
        try
        {

            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(database_class.API_URL+"/user_s/to_client/get_chat.php");

            pairs.add(new BasicNameValuePair("security",code));

            pairs.add(new BasicNameValuePair("uid",user_class.uid));

            ucid=my_a_prop_class.ucid;

            pairs.add(new BasicNameValuePair("ucid",my_a_prop_class.ucid));

            order_id=my_a_prop_class.order_id;

            pairs.add(new BasicNameValuePair("orderid",my_a_prop_class.order_id));

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            HttpEntity httpEntity=httpResponse.getEntity();

            String code_p= EntityUtils.toString(httpEntity);

            byte []codes=code_p.getBytes("ISO-8859-1");

            String result=new String(codes,"UTF-8");

//            Log.i("Err",result);

            convert_chat_json(result);


        }
        catch (Exception Error)
        {
            Log.i("Err2",Error.getMessage());
        }
    }
    //Get Chat List End















    //Get Chat List Start
    public static void get_chatt()
    {
        try
        {

            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(database_class.API_URL+"/user_s/to_client/get_chat.php");

            pairs.add(new BasicNameValuePair("security",code));

            pairs.add(new BasicNameValuePair("uid",user_class.uid));

            pairs.add(new BasicNameValuePair("ucid",ucid));

            pairs.add(new BasicNameValuePair("orderid",order_id));

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            HttpEntity httpEntity=httpResponse.getEntity();

            String code_p= EntityUtils.toString(httpEntity);

            byte []codes=code_p.getBytes("ISO-8859-1");

            String result=new String(codes,"UTF-8");

//            Log.i("Err",result);

            clear_chat_array();

            convert_chat_json(result);


        }
        catch (Exception Error)
        {
            Log.i("Err2",Error.getMessage());
        }
    }
    //Get Chat List End












    //Clear Chat Start
    public static void clear_chat_array()
    {
        chat_align.clear();
        chat_time.clear();
        chat_timestamp.clear();
        chat_tick.clear();
        chat_text.clear();
    }
    //Clear Chat End














    //Convert Chat Json To Text Start
    private static void convert_chat_json(String Json)
    {

        try
        {


            JSONArray jsonArray=new JSONArray(Json);

            for(int a=0;a<jsonArray.length();a++)
            {

                chat_align.add(jsonArray.getJSONObject(a).getString("align"));
                chat_text.add(jsonArray.getJSONObject(a).getString("text"));
                chat_time.add(jsonArray.getJSONObject(a).getString("time"));
                chat_timestamp.add(jsonArray.getJSONObject(a).getString("timestamp"));
                chat_tick.add(jsonArray.getJSONObject(a).getString("tick"));

            }


        }
        catch (Exception Err)
        {
            Log.i("Err",Err.getMessage());
        }
    }
    //Convert Chat Json To Text End

















    //Send Chat List Start
    public static void send_chat(String text)
    {
        try
        {

            ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();

            security security=new security();

            String code=security.code();

            DefaultHttpClient httpClient=new DefaultHttpClient();

            HttpPost httpGet=new HttpPost(database_class.API_URL+"/user_s/to_server/submit_chat.php");

            pairs.add(new BasicNameValuePair("security",code));

            pairs.add(new BasicNameValuePair("uid",user_class.uid));

            pairs.add(new BasicNameValuePair("ucid",ucid));

            pairs.add(new BasicNameValuePair("orderid",order_id));

            pairs.add(new BasicNameValuePair("text",text));

//            Log.i("Err",code+"\n"+user_class.uid+"\n"+ucid+"\n"+order_id+"\n"+text);

            httpGet.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

            HttpResponse httpResponse=httpClient.execute(httpGet);

            HttpEntity httpEntity=httpResponse.getEntity();

            String code_p= EntityUtils.toString(httpEntity);

            byte []codes=code_p.getBytes("ISO-8859-1");

            String result=new String(codes,"UTF-8");

//            Log.i("abc",result);

            convert_json_request(result);


        }
        catch (Exception Error)
        {
            Log.i("abc",Error.getMessage());
        }
    }
    //Send Chat List End

















    //Get Send Request Start
    private static void convert_json_request(String Json)
    {
        try
        {
            JsonParser jsonParser=new JsonParser();

            Object object=jsonParser.parse(Json);

            JsonObject object1=(JsonObject)object;

            send_alert = object1.get("alert").getAsString();

        }
        catch (Exception Err)
        {
//            Log.i("Err",Err.getMessage());
        }
    }
    //Get Send Request End





}
