package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Sms_panle_ac extends AppCompatActivity
{



    //Vars Start
    EditText Phone_Number;
    public static String Phone_number;
    public static String Key="";
    //Vars End





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_panle);

        try
        {
            refresh();
        }
        catch (Exception Err)
        {

        }

    }








    //On Click on Done Button in Sms 1 Ly Button Start
    public void onclick_on_done_Sms_ac_done_btn(View v)
    {

        try
        {

            if(check())
            {
                Generate_Password_Key();
                Phone_number=Phone_Number.getText().toString().trim();
                startActivity(new Intent(getApplicationContext(),Sms2_ac.class));
                overridePendingTransition(R.anim.activity_anim,R.anim.activity_anim);
            }

        }
        catch (Exception Err)
        {

        }

    }
    //On Click on Done Button in Sms 1 Ly Button End






    //Get Check is Phone Number Function Start
    public boolean check() throws Exception
    {
        char []chars=Phone_Number.getText().toString().toCharArray();

        if(chars.length==11)
        {
            if(chars[0]=='0'&&chars[1]=='9')
            {
                Phone_number=Phone_Number.getText().toString().trim();
                return true;
            }
        }

        return false;
    }
    //Get Check is Phone Number Function End










    //Create Key Start
    public void Generate_Password_Key() throws Exception
    {

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                database_class db=new database_class(Phone_Number.getText().toString().trim());

            }

        }).start();

    }
    //Create Key End












    //Get All Components Start
    public void refresh() throws Exception
    {
        Phone_Number=(EditText)findViewById(R.id.Enter_Your_Phone_Number_Text_Box);
    }
    //Get All Components End










    //On Back Pressed Start
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
    //On Back Pressed End




}
