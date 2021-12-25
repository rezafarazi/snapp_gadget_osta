package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

public class my_props_one_item_ac extends FragmentActivity implements OnMapReadyCallback
{





    //All Vars Start
    private GoogleMap mMap;
    public static Double x=10.0,y=20.0;
    TextView text1,text2,text3,text4,text5,is_scicial,message_counter,Prop_money,Prop_date,Prop_text;
    Button Prop_btn;
    ImageView imageview;
    SwipeRefreshLayout refreshLayout;
    ScrollView scrollView;
    View l1,l2,l3,l4,l5,l6,l7,l8,message_linaer_view;
    GridView questions;
    GridView all_prop;
    Boolean is_show=false;
    int Scroll=0;
    RelativeLayout phone_ly;
    //All Vars End








    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_props_one_item_ac);

        try
        {
            refresh();
            fill();
        }
        catch (Exception Err)
        {

        }
    }







    //Maps Start
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        LatLng sydney = new LatLng(x, y);
        mMap.addMarker(new MarkerOptions().position(sydney).title(Props_class.name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(x,y), 14.0f));
    }
    //Maps End








    //On Click Back Arrow Event Start
    public void on_back_arrow__my_props_one_item_ac(View v)
    {
        onBackPressed();
    }
    //On Click Back Arrow Event End











    //Get To Chats Activityes Start
    public void on_click_go_to_chat_ac(View v)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                chat_class.get_chat();
            }
        }).start();

        startActivity(new Intent(getApplicationContext(),chat_ac.class));
    }
    //Get To Chats Activityes End











    //On Click Back Button Start
    @Override
    public void onBackPressed()
    {
        new my_a_prop_class();
        super.onBackPressed();
    }
    //On Click Back Button End









    //Get All Component Start
    public void refresh() throws Exception
    {
        //All Text Views Start
        text1=(TextView)findViewById(R.id.text1_view_my_props_one_item_ac__n_price_ac);
        text2=(TextView)findViewById(R.id.text2_view_my_props_one_item_ac__n_price_ac);
        text3=(TextView)findViewById(R.id.text3_view_my_props_one_item_ac__n_price_ac);
        text4=(TextView)findViewById(R.id.text4_view_my_props_one_item_ac__n_price_ac);
        text5=(TextView)findViewById(R.id.text5_view_my_props_one_item_ac__n_price_ac);
        is_scicial=(TextView)findViewById(R.id.is_spicial_text_view_my_props_one_item_ac);
        message_counter=(TextView)findViewById(R.id.messages_counter_text_view_my_props_one_item_ac);
        Prop_money=(TextView)findViewById(R.id.money_prop_text_view_my_props_one_item_ac);
        Prop_date=(TextView)findViewById(R.id.date_prop_text_view_my_props_one_item_ac);
        Prop_text=(TextView)findViewById(R.id.text_prop_text_view_my_props_one_item_ac);
        //All Text Views End

        //All Panle Views Start
        l1=(View)findViewById(R.id.panle1_my_props_one_item_ac);
        l2=(View)findViewById(R.id.panle2_my_props_one_item_ac);
        l3=(View)findViewById(R.id.panle3_my_props_one_item_ac);
        l4=(View)findViewById(R.id.panle4_my_props_one_item_ac);
        l5=(View)findViewById(R.id.panle5_my_props_one_item_ac);
        l6=(View)findViewById(R.id.panle6_my_props_one_item_ac);
        l7=(View)findViewById(R.id.panle7_my_props_one_item_ac);
        l8=(View)findViewById(R.id.panle8_my_props_one_item_ac);
        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh_ly_my_props_one_item_ac);
        scrollView=(ScrollView)findViewById(R.id.scroll_view_my_props_one_item_ac);
        scrollView.setVisibility(View.GONE);
        message_linaer_view=(LinearLayout)findViewById(R.id.messages_linear_view_my_props_one_item_ac);
        //All Panle Views End

        //All Button Views Start
        Prop_btn=(Button)findViewById(R.id.props_wite_btn_my_props_one_item_ac);
        //All Button Views End

        //All Image View Views Start
        imageview=(ImageView)findViewById(R.id.image_view_my_props_one_item_ac);
        //All Image View Views End

        //All Grids View Views Start
        questions=(GridView)findViewById(R.id.questions_grid_view_my_props_one_item_ac);
        all_prop=(GridView)findViewById(R.id.all_props_grid_view_my_props_one_item_ac_ac);
        //All Grids View Views End






        //Phone Layout Start
        phone_ly=(RelativeLayout)findViewById(R.id.phone_panle_my_props_one_item_ac);
        //Phone Layout End






        //Events Start
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                refreshLayout.setRefreshing(false);
            }
        });
        //Events End


    }
    //Get All Component End








    //Get Maps Components Start
    public void refresh_Maps() throws Exception
    {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.my_props_one_item_ac_ac_map_frag);
        mapFragment.getMapAsync(this);
    }
    //Get Maps Components End








    //On Click Back Button Start
    public void fill() throws Exception
    {
        refreshLayout.setRefreshing(true);


        CountDownTimer timer=new CountDownTimer(5000,10)
        {
            @Override
            public void onTick(long l)
            {
                try
                {
                    if(!my_a_prop_class.id.equals("")&&is_show==false)
                    {

                        refreshLayout.setRefreshing(false);
                        scrollView.setVisibility(View.VISIBLE);


                        if(my_a_prop_class.is_spicial.equals("t"))
                        {
                            is_scicial.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            is_scicial.setVisibility(View.GONE);
                        }



                        if(my_a_prop_class.status.equals("wait"))
                        {
                            Prop_btn.setBackgroundResource(R.drawable.gray_btn_background2);
                            Prop_btn.setText(R.string.wite_for_prop);
                        }
                        else if(my_a_prop_class.status.equals("selected"))
                        {
                            Prop_btn.setBackgroundResource(R.drawable.green_btn_background2);
                            Prop_btn.setText(R.string.done_your_prop);
                        }
                        else if(my_a_prop_class.status.equals("cancel"))
                        {
                            Prop_btn.setBackgroundResource(R.drawable.punk_btn_background2);
                            Prop_btn.setText(R.string.price_is_canceled);
                        }
                        else if(my_a_prop_class.status.equals("deny"))
                        {
                            Prop_btn.setBackgroundResource(R.drawable.orange_btn_background2);
                            Prop_btn.setText(R.string.an_other_priced);
                        }








                        if(Build.VERSION.SDK_INT<19)
                        {
                            Prop_btn.bringToFront();
                        }









                        if(my_a_prop_class.user_mobile.equals(""))
                        {
                            phone_ly.setVisibility(View.GONE);
                        }
                        else
                        {
                            TextView user_number=(TextView)findViewById(R.id.phone_number_text_view_my_props_one_item_ac);
                            user_number.setText(my_a_prop_class.user_mobile);
                        }









                        if(Integer.parseInt(my_a_prop_class.message_count)>0)
                        {
                            message_counter.setVisibility(View.VISIBLE);
                            message_counter.setText(my_a_prop_class.message_count);
                        }
                        else
                        {
                            message_counter.setVisibility(View.GONE);
                        }



                        text1.setText(my_a_prop_class.name);
                        text2.setText(my_a_prop_class.proposal_count);
                        text3.setText(my_a_prop_class.date_create);
                        text4.setText(my_a_prop_class.date_order);
                        text5.setText(my_a_prop_class.text);
                        Prop_money.setText(my_a_prop_class.my_price);
                        Prop_date.setText(my_a_prop_class.my_datecreate);
                        Prop_text.setText(my_a_prop_class.my_description);




                        String []geo=my_a_prop_class.geo.split("-");
                        y=Double.parseDouble(geo[0]);
                        x=Double.parseDouble(geo[1]);
                        refresh_Maps();




                        questions.setAdapter(new adaptor_questions());
                        all_prop.setAdapter(new adaptor_props());



                        if(my_a_prop_class.Propos_all.size()<=0)
                        {
                                l5.setVisibility(View.GONE);
                        }



                        ViewGroup.LayoutParams layoutParams = questions.getLayoutParams();
                        layoutParams.height = 150 * my_a_prop_class.items.size();
                        questions.setLayoutParams(layoutParams);


                        ViewGroup.LayoutParams layoutParams1 = all_prop.getLayoutParams();
                        layoutParams1.height = 360 * (my_a_prop_class.Propos_all.size() / 2);
                        all_prop.setLayoutParams(layoutParams1);







                        //Show Picture Start
                        SvgLoader svgLoader=new SvgLoader();
                        svgLoader.load(Uri.parse(my_a_prop_class.picture),imageview);
                        //Show Picture End




                        is_show=true;
                    }
                }
                catch (Exception Err)
                {

                }

                if (is_show)
                    Scroll++;

                if(Scroll<20)
                    scrollView.fullScroll(View.FOCUS_UP);

            }

            @Override
            public void onFinish()
            {

                if(is_show==false)
                {
                    refreshLayout.setRefreshing(false);
                    l7.setVisibility(View.VISIBLE);
                }
            }

        }.start();

    }
    //On Click Back Button End












    //on Click Cell To User Start
    public void on_click_cell_to_user_my_a_props_one_item_ac(View v)
    {
        try
        {

            if(!my_a_prop_class.user_mobile.equals(""))
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + my_a_prop_class.user_mobile));
                startActivity(intent);
            }

        }
        catch (Exception Err)
        {

        }
    }
    //on Click Cell To User End













    //Questions Adaptor Start
    class adaptor_questions extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return my_a_prop_class.items.size();
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

            view=getLayoutInflater().inflate(R.layout.n_price_questions_grid_ly,null);

            TextView Text1=(TextView)view.findViewById(R.id.text1_view_n_price_querstions_grid_ly);
            TextView Text2=(TextView)view.findViewById(R.id.text2_view_n_price_querstions_grid_ly);


            try
            {

                JsonParser jsonParser=new JsonParser();

                Object object=jsonParser.parse(my_a_prop_class.items.get(i)+"");

                JsonObject object1=(JsonObject)object;

                Text1.setText(object1.get("question").getAsString()+"");
                Text2.setText(object1.get("answer").getAsString()+"");

            }
            catch (Exception Err)
            {

            }

            return view;
        }


    }
    //Questions Adaptor End










    //All Prop Adaptor Start
    class adaptor_props extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return my_a_prop_class.Propos_all.size();
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
            view=getLayoutInflater().inflate(R.layout.all_prop_list_ly,null);

            try
            {


                JsonParser jsonParser=new JsonParser();
                Object object=jsonParser.parse(my_a_prop_class.Propos_all.get(i)+"");
                JsonObject object1=(JsonObject)object;

                TextView textView1 = (TextView) view.findViewById(R.id.text_view_1_all_prop_grid_ly);
                TextView textView2 = (TextView) view.findViewById(R.id.text_view_2_all_prop_grid_ly);
                TextView textView3 = (TextView) view.findViewById(R.id.text_view_3_all_prop_grid_ly);

                ImageView imageView = (ImageView) view.findViewById(R.id.image_view_all_prop_grid_ly);


                textView1.setText(object1.get("name").getAsString());
                textView2.setText(object1.get("price").getAsString());
                textView3.setText(object1.get("time").getAsString());
//                SvgLoader.pluck().with(Show_ditales_N_price_ac.this).setPlaceHolder(R.drawable.wite, R.drawable.wite).load(object1.get("picture").getAsString(), imageview);

                Picasso.with(getApplicationContext()).load(object1.get("picture").getAsString()).into(imageView);

                LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.prop_all_ly_liner);

                if(object1.get("is_special").getAsString().equals("t"))
                {
                    linearLayout.setBackgroundResource(R.drawable.prop_all_border_background_ly_selected);
                }


            }
            catch (Exception Err)
            {
                Log.i("Err",Err.getMessage());
            }

            return view;
        }
    }
    //All Prop Adaptor End






}
