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


public class my_all_prop_frag extends Fragment
{



    //Vars Start
    GridView gridView;
    SwipeRefreshLayout swipeRefreshLayout;
    RelativeLayout is_empty;
    RelativeLayout tips_ly;
    int Count=0;
    ImageView Header_Image_view;
    //Vars End




    //Fragment Function Start
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {

        View view=inflater.inflate(R.layout.fragment_my_all_prop_frag, container, false);
        try
        {
            refresh(view);
            events();
            auto_refresh();
        }
        catch (Exception Err)
        {

        }

        return view;
    }
    //Fragment Function End












    //Tips
    public void tips(View view)
    {
        tips_ly=(RelativeLayout)view.findViewById(R.id.tips_ly_home_frag_ly);

        if(user_class.auth_status.trim().equals("false"))
        {

            swipeRefreshLayout.setVisibility(View.GONE);
            gridView.setVisibility(View.GONE);
            tips_ly.setVisibility(View.VISIBLE);

            CountDownTimer timer=new CountDownTimer(60000000,10000)
            {
                @Override
                public void onTick(long l)
                {
                    if(user_class.auth_status.trim().equals("true"))
                    {
                        gridView.setVisibility(View.VISIBLE);
                        swipeRefreshLayout.setVisibility(View.VISIBLE);
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
            if(my_all_props_class.id.size()==0)
            {
                swipeRefreshLayout.setVisibility(View.GONE);
                gridView.setVisibility(View.GONE);
                is_empty.setVisibility(View.VISIBLE);
            }
        }


    }
    //Tips















    //Enevts Start
    public void events() throws Exception
    {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                gridView.setAdapter(new my_all_prop_grdi_adaptor());
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }
    //Enevts End








    //Get All Components Start
    public void refresh(View view) throws Exception
    {
        gridView=(GridView)view.findViewById(R.id.grid_view_ly_my_all_prop_frag);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refresh_ly_my_all_prop_frag);
        is_empty=(RelativeLayout) view.findViewById(R.id.empty_ly_my_all_prop_frag);
        Header_Image_view=(ImageView)view.findViewById(R.id.image_view_header_m_ac);

        Picasso.with(getContext()).load(database_class.Header_URL.trim()).into(Header_Image_view);


        gridView.setAdapter(new my_all_prop_grdi_adaptor());

        tips(view);

    }
    //Get All Components End










    //Main Grid Adaptor Start
    class my_all_prop_grdi_adaptor extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return my_all_props_class.id.size();
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
            view=getLayoutInflater().inflate(R.layout.one_colnum_ly_grid_3,null);




            try {


                TextView title = (TextView) view.findViewById(R.id.one_colnum_ly_grid_3_title);
                TextView prop_count = (TextView) view.findViewById(R.id.one_colnum_ly_grid_3_count);
                TextView your_prop = (TextView) view.findViewById(R.id.one_colnum_ly_grid_3_your_prop);
                TextView date = (TextView) view.findViewById(R.id.one_colnum_ly_grid_3_text_view_date);
                TextView is_spicisal = (TextView) view.findViewById(R.id.one_colnum_ly_grid_3_spicial_prop);
                TextView messages = (TextView) view.findViewById(R.id.one_colnum_ly_grid_3_messages);
                Button btn = (Button) view.findViewById(R.id.one_colnum_ly_grid_3_button_ditales);
                ImageView imageView = (ImageView) view.findViewById(R.id.one_colnum_ly_grid_2_image_view);


                title.setText(my_all_props_class.name.get(i));
                prop_count.setText(my_all_props_class.proposal_count.get(i));
                your_prop.setText(my_all_props_class.price.get(i));
                date.setText(my_all_props_class.datecreate.get(i));

                if (my_all_props_class.status.get(i).equals("wait")) {
                    btn.setBackgroundResource(R.drawable.gray_btn_background);
                    btn.setText(R.string.wite_for_prop);
                } else if (my_all_props_class.status.get(i).equals("selected")) {
                    btn.setBackgroundResource(R.drawable.green_btn_background);
                    btn.setText(R.string.done_your_prop);
                } else if (my_all_props_class.status.get(i).equals("cancel")) {
                    btn.setBackgroundResource(R.drawable.punk_btn_background);
                    btn.setText(R.string.price_is_canceled);
                } else if (my_all_props_class.status.get(i).equals("deny")) {
                    btn.setBackgroundResource(R.drawable.orange_btn_background);
                    btn.setText(R.string.an_other_priced);
                }


                if (my_all_props_class.is_special.get(i).equals("t")) {
                    is_spicisal.setVisibility(View.VISIBLE);
                } else {
                    is_spicisal.setVisibility(View.GONE);
                }


                if (my_all_props_class.messages.get(i).equals("0")) {
                    messages.setVisibility(View.GONE);
                } else {
                    messages.setVisibility(View.VISIBLE);
                }






                //Show Icons Start
                SvgParser svg = new SvgParser(getActivity());
                svg.loadImage(Uri.parse(my_all_props_class.picture.get(i)), imageView);
                //Show Icons End









                //btn Click Event Start
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                database_class.get_a_my_prop_by_id(my_all_props_class.id.get(i));
                            }
                        }).start();
                        startActivity(new Intent(getContext(), my_props_one_item_ac.class));
                    }
                });
                //btn Click Event End


            }
            catch (Exception Err)
            {

            }



            return view;
        }
    }
    //Main Grid Adaptor End









    //Auto Refresh Start
    public void auto_refresh() throws Exception
    {

        Count=my_all_props_class.Count;

        CountDownTimer timer=new CountDownTimer(6000000,5000)
        {

            @Override
            public void onTick(long l)
            {


                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        try
                        {
                            database_class.get_all_prices();
                        }
                        catch (Exception Err)
                        {

                        }

                    }
                }).start();


                CountDownTimer timer1=new CountDownTimer(5000,10)
                {
                    @Override
                    public void onTick(long l)
                    {

                        try
                        {
                            if (Count != my_all_props_class.Count)
                            {
                                gridView.setAdapter(new my_all_prop_grdi_adaptor());
                                Count = my_all_props_class.Count;
                                swipeRefreshLayout.setVisibility(View.VISIBLE);
                                is_empty.setVisibility(View.GONE);
                            }
                        }
                        catch (Exception Err)
                        {

                        }

                    }

                    @Override
                    public void onFinish()
                    {

                    }

                }.start();

            }

            @Override
            public void onFinish()
            {

            }

        }.start();
    }
    //Auto Refresh End






}
