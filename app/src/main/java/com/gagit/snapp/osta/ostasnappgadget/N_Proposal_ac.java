package com.gagit.snapp.osta.ostasnappgadget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import org.apache.http.util.EncodingUtils;

public class N_Proposal_ac extends AppCompatActivity
{


    //All Vars Start
    WebView webView;
    RelativeLayout main_ly;
    //All Vars End



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n__proposal_ac);

        try
        {

            this.setTitle("پیشنهاد جدید");

            webView = (WebView) findViewById(R.id.web_view_n_proposal);
            webView.setWebViewClient(new WebViewClient());

            webView.getSettings().setJavaScriptEnabled(true);

            byte[] post = EncodingUtils.getBytes("security=" + new security().code() + "&uid=" + user_class.uid + "&orderid=" + Props_class.id, "UTF-8");
            webView.postUrl(database_class.API_URL+"/user_s/to_server/submit_proposal.php", post);


            main_ly = (RelativeLayout) findViewById(R.id.n__proposal_ac_main_dialog_ly);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;

            ViewGroup.LayoutParams layoutParams1 = main_ly.getLayoutParams();
            layoutParams1.width = width - 60;
            main_ly.setLayoutParams(layoutParams1);

        }
        catch (Exception Err)
        {

        }


    }







    //on Click Back Arrow in Ac Start
    public void on_back_arrow_n_proposal_ac(View v)
    {
        onBackPressed();
    }
    //on Click Back Arrow in Ac End









    //on Click Close Web Browser And Done Event Start
    public void on_click_done_btn_N_proposal(View v)
    {
        onBackPressed();
    }
    //on Click Close Web Browser And Done Event End







}
