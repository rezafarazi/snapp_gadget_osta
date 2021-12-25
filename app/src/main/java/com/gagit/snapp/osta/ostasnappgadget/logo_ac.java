package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class logo_ac extends AppCompatActivity
{





    //Vars Start
    public static String Phone_number;
    public static String SHA;
    Boolean activity_opened=false;
    Boolean n_user=false;
    Boolean sign=false;
    Boolean Not_Internet=false;
    int Counter=0;
    boolean loged=false;
    int not_sign_counter=0;
    //Vars End









    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        try
        {
            timer();
        }
        catch (Exception Err)
        {

        }

    }










    //Timer Function Start
    public void timer() throws Exception
    {

        CountDownTimer timer=new CountDownTimer(60000,10)
        {
            @Override
            public void onTick(long l)
            {

                    if (isNetworkAvailable() && !activity_opened)
                    {

                        try
                        {

                            if (sign_check())
                            {


                                try
                                {


                                    if (!activity_opened && !user_class.uid.equals("") && !database_class.Header_URL.equals(""))
                                    {


                                        if (user_class.profile_status.trim().equals("false"))
                                        {
                                            Toast.makeText(logo_ac.this, R.string.wite, Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), Access_is_false_ac.class));
                                        }
                                        else
                                        {
                                            startActivity(new Intent(getApplicationContext(), m_ac.class));
                                        }

                                        activity_opened = true;

                                    }
                                    else if (!user_class.alert)
                                    {
                                        startActivity(new Intent(getApplicationContext(), Sms_panle_ac.class));
                                        activity_opened = true;
                                    }


                                }
                                catch (Exception Err)
                                {

                                }

                            }
                            else if (not_sign_counter >= 50)
                            {
                                if (!n_user)
                                {
                                    startActivity(new Intent(getApplicationContext(), Sms_panle_ac.class));
                                    n_user = true;
                                }

                            }
                            else
                            {
                                not_sign_counter++;
                            }
                        }
                        catch (Exception Err)
                        {

                        }
                    }
                    else if (!Not_Internet && !activity_opened)
                    {
                        Not_Internet = true;
                        startActivity(new Intent(getApplicationContext(), no_internet_ac.class));
                    }


                }



            @Override
            public void onFinish()
            {

            }
        }.start();
    }
    //Timer Function End















    //Check Sign To App Start
    public boolean sign_check() throws Exception
    {
        if(Counter==0)
        {
            sign = get_profile();
            Counter++;
        }

        return sign;
    }
    //Check Sign To App End
















    //Save Profile Start
    public boolean get_profile() throws Exception
    {


            SharedPreferences sharedPreferences = getSharedPreferences("Snapp_gagit_Services_profile", MODE_PRIVATE);

            Phone_number = sharedPreferences.getString("phone", "");
            SHA = sharedPreferences.getString("SHA", "");

            if (!Phone_number.equals("") && !SHA.equals(""))
            {
                log();
                return true;
            }
            else
            {
                return false;
            }



    }
    //Save Profile End





















    //Log Start
    public void log()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Snapp_gagit_Services_profile", MODE_PRIVATE);

        Phone_number = sharedPreferences.getString("phone", "");
        SHA = sharedPreferences.getString("SHA", "");

//        if (!Phone_number.equals("") && !SHA.equals("")&&!loged)
        if (!Phone_number.equals("") && !SHA.equals(""))
        {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        new database_class(Phone_number, SHA);
                        get_chat_list();
                        database_class.get_all_prices();
                    } catch (Exception Err) {}

                }
            }).start();

            loged=true;

        }
    }
    //Log End
























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
















    //Check To Connected To InterNet Start//
    public boolean isNetworkAvailable()
    {
        try
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        catch (Exception Err)
        {
            return true;
        }
    }
    //Check To Connected To InterNet Start//




}
