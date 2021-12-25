package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sms2_ac extends AppCompatActivity
{


    //Var s
    EditText done_code;
    TextView help_Text_view;
    int counter_invalde=0;
    //Var s




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms2_ac);

        try
        {
            refresh();
            events();
        }
        catch (Exception Err)
        {

        }

    }





    ///ON Back Buttton
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_anim,R.anim.activity_anim);
    }
    ///ON Back Buttton












    //On Click Done Button Start
    public void onclick_done_btn_check_digits(View v)
    {

        try
        {

            if (done_code.getText().toString().equals(database_class.Sms_Key))
            {
                start_main_ac_and_save();
            }
            else
            {
                if (counter_invalde <= 3)
                {
                    Toast.makeText(this, R.string.Password_invalde, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), logo_ac.class));
                }
                counter_invalde++;
            }
        }
        catch (Exception Err)
        {

        }

    }
    //On Click Done Button End












    //Events On Start AC Time Start
    public void events() throws Exception
    {
        done_code.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent)
            {

                try
                {

                    if (done_code.getText().toString().equals(database_class.Sms_Key))
                    {
                        start_main_ac_and_save();
                    }

                }
                catch (Exception Err)
                {

                }


                return false;
            }
        });
    }
    //Events On Start AC Time End

















    //Save And Start Android App Start
    public void start_main_ac_and_save() throws Exception
    {
        save_profile();
        startActivity(new Intent(getApplicationContext(), white_for_frist_start_ac.class));
    }
    //Save And Start Android App End












    //Save Profile Start
    public void save_profile() throws Exception
    {
        SharedPreferences.Editor editor=getSharedPreferences("Snapp_gagit_Services_profile",MODE_PRIVATE).edit();

        editor.putString("phone", Sms_panle_ac.Phone_number);
        editor.putString("SHA", database_class.SHA);

        editor.apply();

        editor.commit();

        Toast.makeText(this, "خوش آمدید", Toast.LENGTH_SHORT).show();

    }
    //Save Profile End
















    //Change Phone Number On Click Event Start
    public void onclick_change_Phone_number(View v)
    {
        startActivity(new Intent(getApplicationContext(), Sms_panle_ac.class));
        overridePendingTransition(R.anim.activity_anim,R.anim.activity_anim);
    }
    //Change Phone Number On Click Event End


















    //Get All Components On Screen Satrt
    public void refresh() throws Exception
    {
        done_code=(EditText)findViewById(R.id.Enter_virify_code);
        help_Text_view=(TextView)findViewById(R.id.text_help_sms2_ac);


        help_Text_view.setText(help_Text_view.getText().toString()+"\n"+ Sms_panle_ac.Phone_number);
    }
    //Get All Components On Screen End









}
