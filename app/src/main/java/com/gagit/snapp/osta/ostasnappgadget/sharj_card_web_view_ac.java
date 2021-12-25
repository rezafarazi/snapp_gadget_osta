package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.apache.http.util.EncodingUtils;

public class sharj_card_web_view_ac extends AppCompatActivity
{





    WebView webView;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharj_card_web_view_ac);


        try
        {

            webView = (WebView) findViewById(R.id.web_view_shrj_card_web_view_ac);
            webView.setWebViewClient(new WebViewClient());

            webView.getSettings().setJavaScriptEnabled(true);

            byte[] post = EncodingUtils.getBytes("subscription=" + sharj_card_ac.Item_Clicked + "&uid=" + user_class.uid, "UTF-8");
            webView.postUrl(database_class.API_URL+"/user_s/to_server/subscription_pay.php", post);

        }
        catch (Exception Err)
        {

        }

    }





    //on Click Arrow Back Btn Start
    public void on_back_arrow_sharj_card_web_view_ac(View v)
    {
        onBackPressed();
    }
    //on Click Arrow Back Btn End






    //On Click End Pay Start
    public void onclick_end_pay_event_sharj_card_view_ac(View v)
    {

        try
        {
            startActivity(new Intent(getApplicationContext(), m_ac.class));
        }
        catch (Exception Err)
        {
            Toast.makeText(this, Err.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    //On Click End Pay End





}
