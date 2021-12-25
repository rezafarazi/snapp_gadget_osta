package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class must_update extends AppCompatActivity
{

    TextView your_update,new_update;
    ImageView Header_Image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_must_update);
        Header_Image_view=(ImageView)findViewById(R.id.image_view_header_m_ac);
        Picasso.with(getApplicationContext()).load(database_class.Header_URL.trim()).into(Header_Image_view);

        your_update=(TextView)findViewById(R.id.your_update_must_update);
        new_update=(TextView)findViewById(R.id.countinu_update_must_update);

        try
        {
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionCode+"";
            your_update.setText(version);
            new_update.setText(database_class.Site_APP_Version);
        }
        catch (Exception Err)
        {

        }

    }






    public void onclick_update_btn(View v)
    {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.snapp-gadget.ir/app")));
    }







    @Override
    public void onBackPressed()
    {

    }





}
