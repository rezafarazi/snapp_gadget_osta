package com.gagit.snapp.osta.ostasnappgadget;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgParser;
import com.squareup.picasso.Picasso;


public class home_frag extends Fragment
{



    //Vars Start//
    GridView gridView;
    SwipeRefreshLayout refreshLayout;
    ImageView Header_Image_view;
    RelativeLayout tips_ly;
    LinearLayout is_empty;
    RelativeLayout empty_ly_home_frag;
    //Vars End//








    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }











    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_home_frag, container, false);

        try
        {
            refresh(view);
        }
        catch (Exception Err)
        {

        }

        return view;
    }














    //Get All Compinents Start
    public void refresh(View view) throws Exception
    {

        gridView=(GridView)view.findViewById(R.id.grid_view_home_frag);
        refreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refresh_home_frag);
        tips_ly=(RelativeLayout)view.findViewById(R.id.tips_ly_home_frag_ly);

















        if(user_class.auth_status.trim().equals("false"))
        {
            refreshLayout.setVisibility(View.GONE);
            tips_ly.setVisibility(View.VISIBLE);


            CountDownTimer timer=new CountDownTimer(60000000,10000)
            {
                @Override
                public void onTick(long l)
                {
                    if(user_class.auth_status.trim().equals("true"))
                    {
                        refreshLayout.setVisibility(View.VISIBLE);
                        tips_ly.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFinish()
                {

                }

            }.start();


        }
        else
        {
            //Is Empty Start
            is_empty=(LinearLayout)view.findViewById(R.id.empty_ly_chats_frag);
            empty_ly_home_frag=(RelativeLayout)view.findViewById(R.id.empty_ly_home_frag);
            if(database_class.N_Price_Id.size()>0)
            {
                gridView.setVisibility(View.VISIBLE);
                empty_ly_home_frag.setVisibility(View.GONE);
            }
            else
            {
                gridView.setVisibility(View.GONE);
                empty_ly_home_frag.setVisibility(View.VISIBLE);
            }
            //Is Empty End

        }



        Header_Image_view=(ImageView)view.findViewById(R.id.image_view_header_m_ac);
        Picasso.with(getContext()).load(database_class.Header_URL.trim()).into(Header_Image_view);


        gridView.setAdapter(new adaptor());


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                gridView.removeAllViewsInLayout();
                gridView.setAdapter(new adaptor());
                refreshLayout.setRefreshing(false);
            }
        });






    }
    //Get All Compinents End
















    class adaptor extends BaseAdapter
    {

        @Override
        public int getCount()
        {
//            return database_class.N_Price_Id.size()+1;
            return database_class.N_Price_Id.size();
        }

        @Override
        public Object getItem(int i)
        {
            return null;
        }

        @Override
        public long getItemId(int i)
        {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup)
        {

            try {

//            if(i>=database_class.N_Price_Id.size())
//            {
//                view=getLayoutInflater().inflate(R.layout.load_new_ly,null);
//                return view;
//            }


//            Animation animationUtils=AnimationUtils.loadAnimation(getContext(),R.anim.item_anim);

                view = getLayoutInflater().inflate(R.layout.one_colnum_ly_grid_2, null);


//            view.setAnimation(animationUtils);
//
//            animationUtils.start();


                TextView title = (TextView) view.findViewById(R.id.one_colnum_ly_grid_2_text_view_title);

                TextView date = (TextView) view.findViewById(R.id.one_colnum_ly_grid_2_text_view_date);

                TextView prop = (TextView) view.findViewById(R.id.one_colnum_ly_grid_2_text_view_prop);

                Button select_btn = (Button) view.findViewById(R.id.one_colnum_ly_grid_2_text_view_select_btn);

                final ImageView imageView = (ImageView) view.findViewById(R.id.one_colnum_ly_grid_2_image_view);


//                SvgLoader.pluck().with(getActivity()).setPlaceHolder(R.drawable.wite, R.drawable.wite).load(database_class.N_Price_Img.get(i), imageView);


                title.setText(database_class.N_Price_Name.get(i));

                date.setText(database_class.N_Price_date.get(i));

                prop.setText(" " + database_class.N_Price_Prop.get(i) + " ");


                if (database_class.N_Price_Is_Select.get(i).equals("t")) {
                    select_btn.setBackgroundResource(R.drawable.gray_btn_background);
                    select_btn.setText(R.string.show_ditalies);

                    select_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    database_class.get_prop_by_id(database_class.N_Price_Id.get(i));
                                }
                            }).start();

                            startActivity(new Intent(getContext(), Show_ditales_N_price_ac.class));
                        }
                    });

                } else {
                    select_btn.setBackgroundResource(R.drawable.green_btn_background);
                    select_btn.setText(R.string.send_N_price);

                    select_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    database_class.get_prop_by_id(database_class.N_Price_Id.get(i));
                                }
                            }).start();

                            startActivity(new Intent(getContext(), Show_ditales_N_price_ac.class));
                        }
                    });
                }




                SvgParser svg = new SvgParser(getActivity());
                svg.loadImage(Uri.parse(database_class.N_Price_Img.get(i)), imageView);




                return view;
            }
            catch (Exception Err)
            {
                return view;
            }



        }
    }









}
