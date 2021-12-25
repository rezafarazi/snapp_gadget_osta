package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class no_internet_ac extends AppCompatActivity
{



    //Vars Start

    RelativeLayout Main_Relativ_layout_no_internet_connetion;

    //All Components



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        try
        {
            refresh();
        }
        catch (Exception Err)
        {

        }

    }













    //On Back Button Click Start//
    @Override
    public void onBackPressed()
    {
        Intent exit=new Intent(Intent.ACTION_MAIN);
        exit.addCategory(Intent.CATEGORY_HOME);
        startActivity(exit);
    }
    //On Back Button Click End//







    //On Click Retry Button In No Internet Connection Activity Start
    public void onclick_retry_connect_to_Internet(View v)
    {
        try
        {

            if(isNetworkAvailable())
            {
                startActivity(new Intent(getApplicationContext(), logo_ac.class));
            }
            else
            {
                Snackbar nointernet_agin=Snackbar.make(Main_Relativ_layout_no_internet_connetion,R.string.no_internet_connection_text,Snackbar.LENGTH_SHORT);
                nointernet_agin.show();
            }

        }
        catch (Exception Err)
        {

        }

    }
    //On Click Retry Button In No Internet Connection Activity End














    //Check To Connected To InterNet Start//
    public boolean isNetworkAvailable() throws Exception
    {
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    //Check To Connected To InterNet Start//








    //Components ID
    public void refresh() throws Exception
    {
        Main_Relativ_layout_no_internet_connetion=(RelativeLayout)findViewById(R.id.no_internet_connection_main_relativ);
    }
    //Components ID





}
