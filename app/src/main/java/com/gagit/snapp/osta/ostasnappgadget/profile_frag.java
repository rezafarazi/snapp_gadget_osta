package com.gagit.snapp.osta.ostasnappgadget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class profile_frag extends Fragment
{


    //Vars Start
    ImageView Header_Image_view;
    Button price_btn;
    //Vars End








    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }














    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_profile_frag, container, false);

        try
        {
            refresh(view);
        }
        catch (Exception Err)
        {

        }

        return view;
    }












    //Events Start
    public void events() throws Exception
    {
    }
    //Events End














    //Get All Components Start
    public void refresh(View view) throws Exception
    {

        price_btn=(Button)view.findViewById(R.id.eshtrak_price_button_profile_frag);

        if(user_class.auth_status.trim().equals("false"))
        {
            price_btn.setVisibility(View.GONE);
        }

        Header(view);
        events();
    }
    //Get All Components End










    //Header Start
    public void Header(View view)
    {
        try
        {
            Header_Image_view = (ImageView) view.findViewById(R.id.image_view_header_m_ac);
            Picasso.with(getContext()).load(database_class.Header_URL.trim()).into(Header_Image_view);
        }
        catch (Exception Err)
        {

        }

    }
    //Header End










}
