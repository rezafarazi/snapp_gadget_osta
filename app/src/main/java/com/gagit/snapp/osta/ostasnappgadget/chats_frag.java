package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class chats_frag extends Fragment
{



    //All Vars Start
    SwipeRefreshLayout refreshLayout;
    RelativeLayout tips_ly;
    GridView chat_list;
    RelativeLayout is_empty;
    int Count=0;
    int Count_tik=0;
    int Count_message=0;
    ImageView Header_Image_view;
    //All Vars End



    //Default Ftagment Start
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_chats_frag, container, false);
        try
        {
            refresh(view);
            events();

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
                is_empty=(RelativeLayout)view.findViewById(R.id.empty_ly_chats_frag);
                if(chat_class.chat_list_ucid.size()>0)
                {
                    refreshLayout.setVisibility(View.VISIBLE);
                    is_empty.setVisibility(View.GONE);
                }
                else
                {
                    refreshLayout.setVisibility(View.GONE);
                    is_empty.setVisibility(View.VISIBLE);
                }
                //Is Empty End

            }


        }
        catch (Exception Err)
        {

        }
        return view;
    }
    //Default Ftagment End





    //Get All Components Start
    public void refresh(View view) throws Exception
    {
        refreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refresh_ly_chats_frag);
        chat_list=(GridView) view.findViewById(R.id.list_view_chats_frag);



        Header_Image_view=(ImageView)view.findViewById(R.id.image_view_header_m_ac);

        Picasso.with(getContext()).load(database_class.Header_URL.trim()).into(Header_Image_view);


        start_my_services();
        chat_list.setAdapter(new chat_adaptor());

    }
    //Get All Components End








    //All Events Start
    public void events() throws Exception
    {
        chat_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l)
            {

                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        chat_class.get_chat(chat_class.chat_list_ucid.get(i),chat_class.chat_list_orderid.get(i));
                    }
                }).start();

                startActivity(new Intent(getContext(),chat_ac.class));
            }
        });



        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                chat_list.setAdapter(new chat_adaptor());
                refreshLayout.setRefreshing(false);
            }
        });



    }
    //All Events End










    //Get Chat Adaptor Start
    class chat_adaptor extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return chat_class.chat_list_ucid.size();
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
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            view=getLayoutInflater().inflate(R.layout.one_chat_colnum_ly,null);

            try
            {

                //Get Compoennt Start//
                TextView title = (TextView) view.findViewById(R.id.one_chat_colnum_ly_title);
                TextView message_count = (TextView) view.findViewById(R.id.one_colnum_ly_grid_3_messages);
                TextView text = (TextView) view.findViewById(R.id.one_chat_colnum_ly_text);
                TextView time = (TextView) view.findViewById(R.id.one_chat_colnum_ly_time);
                ImageView tick = (ImageView) view.findViewById(R.id.one_chat_colnum_ly_see);
                //Get Compoennt End//


                //Set Value To Components Start
                title.setText(getString(R.string.priceser_counter) + " " + chat_class.chat_list_ucid.get(i));
                text.setText(chat_class.chat_list_text.get(i));
                message_count.setText(chat_class.chat_list_unread.get(i));
                time.setText(chat_class.chat_list_time.get(i));
                //Set Value To Components End


                if (chat_class.chat_list_tick.equals("1")) {
                    tick.setImageResource(R.drawable.see_2_icon);
                } else {
                    tick.setImageResource(R.drawable.single_see_icon);
                }


                if (chat_class.chat_list_unread.get(i).equals("0")) {
                    message_count.setVisibility(View.GONE);
                } else {
                    message_count.setVisibility(View.VISIBLE);
                }
            }
            catch (Exception Err)
            {

            }

            return view;
        }
    }
    //Get Chat Adaptor End













    //refresh Messages Services Start
    public void start_my_services() throws Exception
    {


        CountDownTimer timer=new CountDownTimer(1000000,5000)
        {

            @Override
            public void onTick(long l)
            {

                Count=chat_class.Counter;
                Count_tik=chat_class.Counter_tik;
                Count_message=chat_class.Counter_message;


                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        new database_class(user_class.mobile,user_class.SHA);
                        chat_class.get_chat_list();
                    }
                }).start();


                CountDownTimer timer1=new CountDownTimer(4500,500)
                {
                    @Override
                    public void onTick(long l)
                    {
                        try
                        {

//                            if(Count!=chat_class.Counter&&Count_tik!=chat_class.Counter_tik&&Count_message!=chat_class.Counter_message)
                            if(Count!=chat_class.Counter)
                            {
                                chat_list.setAdapter(new chat_adaptor());

                                if(chat_class.chat_list_ucid.size()>0)
                                {
                                    refreshLayout.setVisibility(View.VISIBLE);
                                    is_empty.setVisibility(View.GONE);
                                }
                                else
                                {
                                    refreshLayout.setVisibility(View.GONE);
                                    is_empty.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                        catch (Exception Err)
                        {
                            Log.i("Err",Err.getMessage());
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
    //refresh Messages Services End








}
