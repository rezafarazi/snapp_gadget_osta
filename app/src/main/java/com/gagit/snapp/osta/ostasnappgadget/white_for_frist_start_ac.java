package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class white_for_frist_start_ac extends AppCompatActivity
{


    //Vars Start
    String Phone_number;
    String SHA;
    Boolean activity=false;
    //Vars End



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_for_frist_start);

        SharedPreferences sharedPreferences = getSharedPreferences("Snapp_gagit_Services_profile", MODE_PRIVATE);

        Phone_number = sharedPreferences.getString("phone", "");
        SHA = sharedPreferences.getString("SHA", "");


        new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        new database_class(Phone_number, SHA);
                        get_chat_list();
                        database_class.get_all_prices();
                    }
                    catch (Exception Err)
                    {

                    }

                }
        }).start();







        CountDownTimer timer=new CountDownTimer(60000,10)
        {
            @Override
            public void onTick(long l)
            {


                try
                {


                    if(!activity && !user_class.uid.equals("")&&!database_class.Header_URL.equals(""))
                    {
                        startActivity(new Intent(getApplicationContext(),m_ac.class));
                        activity=true;
                    }

                }
                catch (Exception Err)
                {
                    Log.i("Err_s",Err.getMessage());
                }


            }

            @Override
            public void onFinish()
            {

            }
        }.start();



    }






    //Get Chat List Start
    public static void get_chat_list() throws Exception
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    chat_class.get_chat_list();
                }
                catch (Exception Err)
                {

                }

            }
        }).start();
    }
    //Get Chat List End



}
