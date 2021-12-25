package com.gagit.snapp.osta.ostasnappgadget;

import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class chat_ac extends AppCompatActivity
{



    //Vars Start
    GridView chatview;
    EditText new_message;
    LinearLayout is_empty;
    RelativeLayout chat_ly;
    SwipeRefreshLayout refresh_ly;
    Boolean show_chat=false;
    int timer_inter=2500;
    int Count;
    //Vars End




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chat_ac);
            refresh();
        }
        catch (Exception Err)
        {

        }
    }









    //On Click Back Arrow Chat Activity Start
    public void on_back_arrow_chat_ac(View v)
    {
        onBackPressed();
    }
    //On Click Back Arrow Chat Activity End













    //Get All Components Start
    public void refresh() throws Exception
    {
        chatview=(GridView)findViewById(R.id.chat_view_chat_ac);
        new_message=(EditText)findViewById(R.id.new_message_edit_text_view_chat_ac);
        is_empty=(LinearLayout)findViewById(R.id.empty_ly_chats_ac);
        chat_ly=(RelativeLayout)findViewById(R.id.chats_ly_chat_ac);
        refresh_ly=(SwipeRefreshLayout)findViewById(R.id.refresh_ly_chat_ac);


        refresh_ly.setRefreshing(true);




        refresh_ly.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                refresh_ly.setRefreshing(false);
            }
        });




        CountDownTimer timer=new CountDownTimer(timer_inter,500)
        {
            @Override
            public void onTick(long l)
            {
                try
                {

                    if(!chat_class.chat_align.get(0).equals("")&&!show_chat)
                    {
                        Count=chat_class.chat_align.size();
                        chatview.setAdapter(new chat_adaptor());
                        chat_ly.setVisibility(View.VISIBLE);
                        refresh_ly.setRefreshing(false);
                        show_chat=true;
                    }

                }
                catch (Exception Err)
                {

                }

            }

            @Override
            public void onFinish()
            {
                if(!show_chat)
                {
                    refresh_ly.setRefreshing(false);
                    is_empty.setVisibility(View.VISIBLE);
                    chatview.setVisibility(View.GONE);
                }
            }
        }.start();



        refreshing_timer();

    }
    //Get All Components End













    //Refreshing By Timer Start
    public void refreshing_timer() throws Exception
    {
        try
        {
            CountDownTimer timer = new CountDownTimer(6000000, 5000) {

                @Override
                public void onTick(long l) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            chat_class.get_chatt();
                        }
                    }).start();


                    CountDownTimer timer1 = new CountDownTimer(4500, 500) {
                        @Override
                        public void onTick(long l) {
                            try {

                                if (Count != chat_class.chat_align.size() && chat_class.chat_align.size() != 0)
                                {

                                    Count = chat_class.chat_align.size();
                                    chatview.setAdapter(new chat_adaptor());
                                    is_empty.setVisibility(View.GONE);
                                    chatview.setVisibility(View.VISIBLE);
                                }
                                if(chat_class.chat_align.size()==0&&is_empty.getVisibility()==View.VISIBLE)
                                {
                                    is_empty.setVisibility(View.VISIBLE);
                                    chatview.setVisibility(View.GONE);
                                }


                            } catch (Exception Err) {

                            }
                        }

                        @Override
                        public void onFinish() {

                        }

                    }.start();


                }

                @Override
                public void onFinish() {

                }

            }.start();
        }
        catch (Exception Err)
        {

        }
    }
    //Refreshing By Timer End

















    //Send New Message Start
    public void onclick_send_chat_btn_chat_ac(View v)
    {

        try
        {
            if (!new_message.getText().toString().trim().equals("")) {
                new_message.setEnabled(false);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        chat_class.send_chat(new_message.getText() + "".trim());
                    }
                }).start();

                CountDownTimer timer = new CountDownTimer(3000, 100) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        new_message.setText("");
                        new_message.setEnabled(true);
                    }
                }.start();
            }
        }
        catch (Exception Err)
        {

        }

    }
    //Send New Message End













    //Get Chat Adaptor Start
    class chat_adaptor extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return chat_class.chat_align.size();
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

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            try {
                view = getLayoutInflater().inflate(R.layout.chat_colnum__grid_ly, null);

                RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.main_ly_chat_grid_ly);
                RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.main2_ly_chat_grid_ly);

                TextView chat_text = (TextView) view.findViewById(R.id.chat_item_text_view_chat_colnum_grid_ly);
                TextView date = (TextView) view.findViewById(R.id.chat_item_date_text_view_chat_colnum_grid_ly);

                ImageView see = (ImageView) view.findViewById(R.id.chat_item_see_image_view_chat_colnum_grid_ly);

                if (chat_class.chat_align.get(i).toLowerCase().equals("right")) {
                    relativeLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                    relativeLayout2.setBackgroundResource(R.drawable.green_chat_item_background);
                } else {
                    relativeLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                    relativeLayout2.setBackgroundResource(R.drawable.white_chat_item_background);
                }

                chat_text.setText(chat_class.chat_text.get(i));
                date.setText(chat_class.chat_time.get(i));


                if (chat_class.chat_tick.get(i).equals("1")) {
                    see.setImageResource(R.drawable.single_see_icon);
                } else if (chat_class.chat_tick.get(i).equals("2")) {
                    see.setImageResource(R.drawable.see_2_icon);
                } else {
                    see.setVisibility(View.GONE);
                }


                return view;
            }
            catch (Exception Err)
            {
                return view;
            }
        }
    }
    //Get Chat Adaptor End

















    //on Back Press Event Start
    @Override
    public void onBackPressed()
    {
        chat_class.clear_chat_array();
        super.onBackPressed();
    }
    //on Back Press Event End






}
