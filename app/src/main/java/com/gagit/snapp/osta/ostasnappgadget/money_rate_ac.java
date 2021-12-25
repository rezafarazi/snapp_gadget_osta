package com.gagit.snapp.osta.ostasnappgadget;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.gagit.snapp.osta.ostasnappgadget.R;

public class money_rate_ac extends AppCompatActivity
{



    //Vars Start
    SwipeRefreshLayout refreshLayout;
    GridView gridView;
    //Vars End



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_rate_ac);


        try
        {
            refresh();
            events();
        }
        catch (Exception Err)
        {

        }

    }







    //On Back Arrow Pressed Start
    public void on_back_arrow_money_rate_ac(View v)
    {
        onBackPressed();
    }
    //On Back Arrow Pressed End










    //Evens Start
    public void events() throws Exception
    {
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
    //Evens End











    //Get All Components Start
    public void refresh() throws Exception
    {
        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh_panle_money_rate_ac);
        gridView=(GridView)findViewById(R.id.money_rate_gridview_money_rate);

        gridView.setAdapter(new adaptor());

    }
    //Get All Components End











    //Grid View Adaptor Start
    class adaptor extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return 10;
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
            view=getLayoutInflater().inflate(R.layout.one_colnum_ly_grid_1,null);

            try
            {

            }
            catch (Exception Err)
            {

            }

            return view;
        }
    }
    //Grid View Adaptor End








}
