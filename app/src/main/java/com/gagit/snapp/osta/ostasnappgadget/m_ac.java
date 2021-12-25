package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class m_ac extends AppCompatActivity
{


    //Vars Start
    BottomNavigationView menu;
    RelativeLayout home_ly,new_job_ly,done_job_ly,store_ly,profile_ly,chat_ly;
    TextView textView_1_header,textView_2_header;
    String Phone_number;
    String SHA;
    TextView textView_my_prop;
    TextView textView_my_prop_count;
    TextView textView_date_create;
    TextView textView_all_props;
    TextView textView_scisial_props;
    TextView textView_my_props;
    GridView notifications;
    ImageView profile_image_view;
    TextView phone_number;
    TextView name_and_family_text_view;
    Boolean profile_is_showed=false;
    //Vars End




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_ac);

        try
        {
            refresh();
            events();
        }
        catch (Exception Err)
        {

        }

    }









    //Events Function Start
    public void events() throws Exception
    {
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {

                try
                {

                    clear_stage();

                    switch (item.getItemId()) {
                        case R.id.home_item_menu_ly:
                            home_ly.setVisibility(View.VISIBLE);
                            break;
//                        case R.id.done_job_item_menu_ly:
//                            done_job_ly.setVisibility(View.VISIBLE);
//                            break;
                        case R.id.my_all_props_item_menu_ly:
                            new_job_ly.setVisibility(View.VISIBLE);
                            break;
//                        case R.id.store_item_menu_ly:
//                            store_ly.setVisibility(View.VISIBLE);
//                            break;
                        case R.id.profile_item_menu_ly:
                            profile_ly.setVisibility(View.VISIBLE);
                            break;
                        case R.id.chats_item_menu_ly:
                            chat_ly.setVisibility(View.VISIBLE);
                            break;
                    }
                }
                catch (Exception Err)
                {

                }

                return true;
            }
        });
    }
    //Events Function End

















    //Clear Stage Start
    public void clear_stage() throws Exception
    {
        home_ly.setVisibility(View.GONE);
        done_job_ly.setVisibility(View.GONE);
        chat_ly.setVisibility(View.GONE);
        new_job_ly.setVisibility(View.GONE);
        store_ly.setVisibility(View.GONE);
        profile_ly.setVisibility(View.GONE);
    }
    //Clear Stage End

















    //On Click Profile Start
    public void onclick_profile_profile_frag(View v)
    {
        startActivity(new Intent(getApplicationContext(),profile_ac.class));
    }
    //On Click Profile End















    //on Click Money Rate Start
    public void onclick_money_rate_profile_frag(View v)
    {
        startActivity(new Intent(getApplicationContext(),money_rate_ac.class));
    }
    //on Click Money Rate End















    //on Click new Sharj Start
    public void onclick_new_sharj_profile_frag(View v)
    {
        startActivity(new Intent(getApplicationContext(),sharj_card_ac.class));
    }
    //on Click new Sharj End



















    //On Click Back Button Start
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
    //On Click Back Button End















    //Check App Version Start
    public void get_config_app()
    {
        try
        {

            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionCode+"";
            if(!version.equals(database_class.Site_APP_Version))
            {


                startActivity(new Intent(getApplicationContext(),must_update.class));

//
//                AlertDialog.Builder alert=new AlertDialog.Builder(m_ac.this);
//                alert.setMessage(R.string.update);
//                alert.setNegativeButton(R.string.update_btn, new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i)
//                    {
//                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(database_class.Site_APP_Link)));
//                    }
//                });
//
//                alert.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i)
//                    {
//
//                    }
//                });
//
//
//                alert.show();

            }


        }
        catch (Exception e)
        {

        }


    }
    //Check App Version End











    //Get Profile Start
    public void get_profile_frag() throws Exception
    {
        textView_my_prop=(TextView)findViewById(R.id.eshtrak_total_text_view_profile_frag);
        textView_my_prop_count=(TextView)findViewById(R.id.eshtrak_all_propos_text_view_profile_frag);
        textView_date_create=(TextView)findViewById(R.id.datecreate_text_view_profile_frag);
        textView_all_props=(TextView)findViewById(R.id.my_all_props_count_text_view_profile_frag);
        textView_scisial_props=(TextView)findViewById(R.id.my_spicial_props_count_text_view_profile_frag);
        textView_my_props=(TextView)findViewById(R.id.my_my_props_count_text_view_profile_frag);
        notifications=(GridView)findViewById(R.id.notifications_grid_profile_frag);
        profile_image_view=(ImageView)findViewById(R.id.profile_image_view_profile_frag);
        phone_number=(TextView)findViewById(R.id.phone_number_textview_profile_frag);
        name_and_family_text_view=(TextView)findViewById(R.id.name_and_family_textview_profile_frag);

        CountDownTimer timer=new CountDownTimer(20000,1000)
        {
            @Override
            public void onTick(long l)
            {

                try
                {

                    if(!user_class.mobile.equals("")&&!profile_is_showed)
                    {
                        textView_my_prop.setText(user_class.subscription_title);
                        textView_my_prop_count.setText(user_class.subscription_count);
                        textView_date_create.setText(user_class.Work_history);
                        textView_all_props.setText(user_class.count_all_proposal);
                        textView_scisial_props.setText(user_class.count_special_proposal);
                        textView_my_props.setText(user_class.count_nospecial_proposal);
                        name_and_family_text_view.setText(user_class.fname+" "+user_class.lname);
                        phone_number.setText(user_class.mobile);
                        notifications.setAdapter(new notifications());
                        Picasso.with(getApplicationContext()).load(user_class.picture.trim()).into(profile_image_view);


                        //Change Grid View Size Start
                        ViewGroup.LayoutParams layoutParams1 = notifications.getLayoutParams();
                        layoutParams1.height =user_class.notification_text.size()*60;
                        notifications.setLayoutParams(layoutParams1);
                        //Change Grid View Size End


                        profile_is_showed=true;
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











    //Notifications Adaptor Start
    class notifications extends BaseAdapter
    {

        @Override
        public int getCount() {
            return user_class.notification_text.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            view=getLayoutInflater().inflate(R.layout.one_colnum_ly_grid_notification,null);

            try
            {

                TextView textView_counter = (TextView) view.findViewById(R.id.Counter_text_view_one_colnum_grid_ly_notification);
                TextView textView_text = (TextView) view.findViewById(R.id.text_text_view_one_colnum_grid_ly_notification);

                textView_counter.setText((i + 1) + " - ");
                textView_text.setText(user_class.notification_text.get(i));
            }
            catch (Exception Err)
            {

            }

            return view;
        }
    }
    //Notifications Adaptor End












    //Call To Backup Start
    public void onclick_cell_to_backup(View v)
    {
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+database_class.Back_up_Phone_Number));
        startActivity(intent);
    }
    //Call To Backup End















    //Get All Components Start
    public void refresh() throws Exception
    {
        menu=(BottomNavigationView)findViewById(R.id.bottom_navigation_view_m_ac);

        home_ly=(RelativeLayout)findViewById(R.id.home_ly_m_ac);
        new_job_ly=(RelativeLayout)findViewById(R.id.new_job_ly_m_ac);
        done_job_ly=(RelativeLayout)findViewById(R.id.done_job_ly_m_ac);
        store_ly=(RelativeLayout)findViewById(R.id.store_ly_m_ac);
        profile_ly=(RelativeLayout)findViewById(R.id.profile_ly_m_ac);
        chat_ly=(RelativeLayout)findViewById(R.id.chats_ly_m_ac);



        get_profile_frag();
        get_config_app();




        new Thread(new Runnable()
        {
            @Override
            public void run()
            {

                SharedPreferences sharedPreferences = getSharedPreferences("Snapp_gagit_Services_profile", MODE_PRIVATE);

                Phone_number = sharedPreferences.getString("phone", "");
                SHA = sharedPreferences.getString("SHA", "");
                new database_class(Phone_number, SHA);

            }
        }).start();







//        ------------------------------------Header Props Show Start
//        textView_1_header=(TextView)findViewById(R.id.text_view_1_header_m_ac);
//        textView_2_header=(TextView)findViewById(R.id.text_view_2_header_m_ac);
//
//
//        textView_1_header.setText(user_class.subscription_title);
//        textView_2_header.setText(user_class.subscription_count);

//        CountDownTimer timer=new CountDownTimer(60000000,5000)
//        {
//
//            @Override
//            public void onTick(long l)
//            {
//
//                new Thread(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        SharedPreferences sharedPreferences = getSharedPreferences("Snapp_gagit_Services_profile", MODE_PRIVATE);
//                        Phone_number = sharedPreferences.getString("phone", "");
//                        SHA = sharedPreferences.getString("SHA", "");
//                        new database_class(Phone_number, SHA);
//                    }
//                }).start();
//
//
//                CountDownTimer timer1=new CountDownTimer(4800,100)
//                {
//
//                    @Override
//                    public void onTick(long l)
//                    {
//
//                        if(!textView_2_header.getText().equals(user_class.subscription_count))
//                        {
//                            textView_1_header.setText(user_class.subscription_title);
//                            textView_2_header.setText(user_class.subscription_count);
//                        }
//
//                    }
//
//                    @Override
//                    public void onFinish()
//                    {
//
//                        if(!textView_2_header.getText().equals(user_class.subscription_count))
//                        {
//                            textView_1_header.setText(user_class.subscription_title);
//                            textView_2_header.setText(user_class.subscription_count);
//                        }
//
//                    }
//
//                }.start();
//
//            }
//
//            @Override
//            public void onFinish()
//            {
//
//            }
//
//        };
//        ------------------------------------Header Props Show End



    }
    //Get All Components End











    //on Clikc Items Start
    public void onclick_items_profile_frag(View view)
    {
        switch (view.getId())
        {
            case R.id.show_profile_item_profile_frag:
                Profile_web_viewes_ac.click_item=3;
                break;
            case R.id.my_jobs_profile_frag:
                Profile_web_viewes_ac.click_item=2;
                break;
        }

        startActivity(new Intent(getApplicationContext(),Profile_web_viewes_ac.class));
    }
    //on Clikc Items End














    //On Click Exit From Account Start
    public void onclick_exit_from_account(View v)
    {
        SharedPreferences.Editor editor=getSharedPreferences("Snapp_gagit_Services_profile",MODE_PRIVATE).edit();

        editor.putString("phone", "");
        editor.putString("SHA", "");

        editor.apply();

        editor.commit();

        startActivity(new Intent(getApplicationContext(),Sms_panle_ac.class));
    }
    //On Click Exit From Account End














    //on Pulse Start

//    @Override
//    protected void onRestart()
//    {
//        startActivity(new Intent(getApplicationContext(),logo_ac.class));
//        super.onRestart();
//    }

    //on Pulse End












}
