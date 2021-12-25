package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class Show_ditales_N_price_ac extends FragmentActivity implements OnMapReadyCallback {


    //All Vars Start
    private GoogleMap mMap;
    TextView text1,text2,text3,text4,text5;
    Button Prop_btn;
    ImageView imageview;
    SwipeRefreshLayout refreshLayout;
    ScrollView scrollView;
    Boolean refreshed=false;
    Boolean []scroll1={false,false,false,false,false};
    public double x = 0, y = 0;
    LinearLayout l1,l2,l3,l4,l5;
    RelativeLayout l6,l7;
    GridView questions;
    GridView all_prop;
    int Scroll=0;
    //All Vars End







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ditales__n_price_ac);

        try
        {
            refresh();
        }
        catch (Exception Err)
        {

        }

    }







    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(x, y);
        mMap.addMarker(new MarkerOptions().position(sydney).title(Props_class.name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(x,y), 14.0f));

    }








    //On Click Back Arrow Button Start
    public void on_back_arrow__show_ditales_n_proposal_ac(View v)
    {
        onBackPressed();
    }
    //On Click Back Arrow Button End











    //Get All Components Start
    public void refresh() throws Exception
    {
        text1=(TextView)findViewById(R.id.text1_view_show_ditales__n_price_ac);
        text2=(TextView)findViewById(R.id.text_view2_show_ditales__n_price_ac);
        text3=(TextView)findViewById(R.id.text3_view_show_ditales__n_price_ac);
        text4=(TextView)findViewById(R.id.text4_view_show_ditales__n_price_ac);
        text5=(TextView)findViewById(R.id.text5_view_show_ditales__n_price_ac);


        l1=(LinearLayout)findViewById(R.id.panle1_show_ditales);
        l2=(LinearLayout)findViewById(R.id.panle2_show_ditales);
        l3=(LinearLayout)findViewById(R.id.panle3_show_ditales);
        l4=(LinearLayout)findViewById(R.id.panle4_show_ditales);
        l5=(LinearLayout)findViewById(R.id.panle5_show_ditales);
        l6=(RelativeLayout)findViewById(R.id.panle6_show_ditales);
        l7=(RelativeLayout)findViewById(R.id.panle7_show_ditales);


        all_prop=(GridView)findViewById(R.id.all_props_grid_view_show_ditales_n_price_ac);


        questions=(GridView)findViewById(R.id.questions_grid_view_show_ditales__n_price_ac);

        imageview=(ImageView)findViewById(R.id.image_view_show_ditales__n_price_ac);

        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh_ly_show_ditales_n_price);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                refreshLayout.setRefreshing(false);
            }
        });
        refreshLayout.setRefreshing(true);

        scrollView=(ScrollView)findViewById(R.id.scroll_view_show_ditales_n_price);
        scrollView.setVisibility(View.GONE);

        Prop_btn=(Button)findViewById(R.id.btn_prop_show_ditales__n_price_ac);



        CountDownTimer timer=new CountDownTimer(5000,10)
        {

            @Override
            public void onTick(long l)
            {

                try
                {
                    if(!Props_class.id.equals("")&&!refreshed)
                    {

                        refreshLayout.setRefreshing(false);
                        scrollView.setVisibility(View.VISIBLE);


                        ViewGroup.LayoutParams layoutParams=questions.getLayoutParams();
                        layoutParams.height=150* Props_class.items.size();
                        questions.setLayoutParams(layoutParams);



                        text1.setText(Props_class.name);
                        text2.setText(Props_class.proposal_count);
                        text3.setText(Props_class.date_create);
                        text4.setText(Props_class.date_order);
                        text5.setText(Props_class.text);

                        String []lang= Props_class.geo.split("-");
                        y=Double.parseDouble(lang[0]);
                        x=Double.parseDouble(lang[1]);

                        refresh_Maps();


                        if(Props_class.show_btn.equals("f"))
                        {
                            Prop_btn.setVisibility(View.GONE);
                        }

                        questions.setAdapter(new adaptor_questions());
                        all_prop.setAdapter(new adaptor_props());



                        ViewGroup.LayoutParams layoutParams1=all_prop.getLayoutParams();
                        layoutParams1.height=360*(Props_class.Propos_all.size()/2);
                        all_prop.setLayoutParams(layoutParams1);



                        if(Props_class.Propos_all.size()<=0)
                        {
                            l5.setVisibility(View.GONE);
                        }




                        try
                        {

                            SvgLoader.pluck().with(Show_ditales_N_price_ac.this).setPlaceHolder(R.drawable.witt22, R.drawable.witt22).load(Props_class.picture+"", imageview);
                        }
                        catch (Exception Err)
                        {
                            Log.i("Err",Err.getMessage());
                        }



                        refreshed=true;


                    }


                }
                catch (Exception Err)
                {

                }

                if (refreshed)
                    Scroll++;

                if(Scroll<20)
                    scrollView.fullScroll(View.FOCUS_UP);




            }

            @Override
            public void onFinish()
            {

                try
                {
                    if (!Props_class.alert_404.equals("") && !refreshed)
                    {
                        View[] linearLayout = {l1,l2, l3, l4, l5,l6};
                        for (int a = 0; a < linearLayout.length; a++)
                            linearLayout[a].setVisibility(View.GONE);
                        l7.setVisibility(View.VISIBLE);

                        scrollView.setVisibility(View.VISIBLE);

                        refreshLayout.setRefreshing(false);
                        refreshed = true;
                        ImageView imageViews = (ImageView) findViewById(R.id.image_view_404_show_ditales_n_price_ac);
                        SvgLoader.pluck().with(Show_ditales_N_price_ac.this).setPlaceHolder(R.drawable.witt22, R.drawable.witt22).load(Props_class.image_404, imageViews);
//                        Picasso.get().load(Props_class.image_404).into(imageView);


                        TextView textView = (TextView) findViewById(R.id.text_view_404_show_ditales_n_price_ac);
                        textView.setText(Props_class.text_404);


                        Scroll++;

                    }
                    else
                    {
                    }
                }
                catch (Exception Err)
                {
                }

            }

        }.start();

    }
    //Get All Components End











    //Get Maps Components Start
    public void refresh_Maps()
    {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.show_ditales_n_price_ac_map_frag);
        mapFragment.getMapAsync(this);
    }
    //Get Maps Components End










    //On Back Pressed Start
    @Override
    public void onBackPressed()
    {
        new Props_class();
        super.onBackPressed();
    }
    //On Back Pressed End










    //Questions Adaptor Start
    class adaptor_questions extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return Props_class.items.size();
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

                Object object=jsonParser.parse(Props_class.items.get(i)+"");

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
            return Props_class.Propos_all.size();
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
                Object object=jsonParser.parse(Props_class.Propos_all.get(i)+"");
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










    //On Button Send Order New Start
    public void onclick_send_new_prop(View v)
    {
        startActivity(new Intent(getApplicationContext(),N_Proposal_ac.class));
    }
    //On Button Send Order New End







}
