package com.gagit.snapp.osta.ostasnappgadget;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.gagit.snapp.osta.ostasnappgadget.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class profile_ac extends AppCompatActivity
{






    //All Vars Start
    RelativeLayout wite_ly;
    ScrollView context_ly;
    Boolean is_show=false;
    ArrayList<TextView> all_text_viewes=new ArrayList<>();
    ImageView profile;
    //All Vars End




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_ac);

        try
        {
            refresh();
            fitch();
        }
        catch (Exception Err)
        {

        }

    }










    //On Click Arrow Back Icon Profile Ac Start
    public void on_back_arrow_profile_ac(View v)
    {
        onBackPressed();
    }
    //On Click Arrow Back Icon Profile Ac End















    //Get All Compoent Start
    public void refresh() throws Exception
    {
        wite_ly=(RelativeLayout)findViewById(R.id.wite_ly_profile_ac);
        context_ly=(ScrollView)findViewById(R.id.context_ly_profile_ac);

        profile=(ImageView)findViewById(R.id.profile_imageview_profile_ac);

        all_text_viewes.add((TextView)findViewById(R.id.name_and_family_textview_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.city_textview_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.price_count_textview_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.time_month_textview_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.name_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.family_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.is_single_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.national_code_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.birth_day_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.phone_number_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.end_licence_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.telephone_number_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.job_telephone_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.city_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.tax_code_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.address_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.count_all_jobs_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.taxasos_text_view_profile_ac));
        all_text_viewes.add((TextView)findViewById(R.id.about_taxasos_text_view_profile_ac));



    }
    //Get All Compoent End












    //Fitch Data From Server Start
    public void fitch() throws Exception
    {
        CountDownTimer timer=new CountDownTimer(6000,1000)
        {
            @Override
            public void onTick(long l)
            {

            }

            @Override
            public void onFinish()
            {

                try
                {

                    if (!is_show)
                    {
                        wite_ly.setVisibility(View.GONE);
                        context_ly.setVisibility(View.VISIBLE);
                        is_show = true;
                    }

                }
                catch (Exception Err)
                {

                }

            }
        }.start();

    }
    //Fitch Data From Server End







}
