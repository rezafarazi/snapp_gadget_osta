package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.gagit.snapp.osta.ostasnappgadget.R;


public class new_job_ac extends Fragment
{


    //Vars Start
    GridView gridView;
    SwipeRefreshLayout refreshLayout;
    //Vars End








    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_new_job, container, false);

        try
        {
            refresh(view);
        }
        catch (Exception Err)
        {

        }

        return view;
    }








    //Get All Compoenntes Start
    public void refresh(View view) throws Exception
    {
        gridView=(GridView)view.findViewById(R.id.new_job_grid_new_job_frag);
        refreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refresh_new_hob_frag);

        gridView.setAdapter(new adaptor());
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
    //Get All Componentes End










    //Grid Adaptor Start
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
            Animation animationUtils= AnimationUtils.loadAnimation(getContext(),R.anim.item_anim);

            view=getLayoutInflater().inflate(R.layout.one_colnum_ly_grid_1,null);

            view.setAnimation(animationUtils);

            animationUtils.start();

            return view;
        }

    }
    //Grid Adaptor End








}
