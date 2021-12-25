package com.gagit.snapp.osta.ostasnappgadget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class sharj_card_ac extends AppCompatActivity
{



    //Vars Start
    EditText Value;
    Button btn;
    GridView gridView;
    public static String Item_Clicked="";
    ArrayList<CheckBox> checkBoxes=new ArrayList<>();
    //Vars End



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharj_card_ac);



        try {

            Value = (EditText) findViewById(R.id.value_edittext_sharj_ac);
            btn = (Button) findViewById(R.id.done_button_sharj_card_ac);
            gridView = findViewById(R.id.price_grid_view_sharj_card_ac);
            gridView.setAdapter(new adaptor());


            Value.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (Value.getText().toString().trim().equals("")) {
                        btn.setEnabled(false);
                    } else {
                        btn.setEnabled(true);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


//
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
//            {
//                Toast.makeText(sharj_card_ac.this, "Salam", Toast.LENGTH_SHORT).show();
////                Value.setText((database_class.Price_card_price.get(i).equals(""))?"0":"");
////                Item_Clicked=database_class.Price_card_id.get(i);
//            }
//        });
//


        }
        catch (Exception Err)
        {

        }





    }







    //On Click Arrow Back Pressed Start
    public void on_back_arrow_sharj_card_ac(View v)
    {
        onBackPressed();
    }
    //On Click Arrow Back Pressed End










    //On Click Value Buttons Event Start
    public void onclick_value_button_sharj_card_ac(View v)
    {
//        switch (v.getId())
//        {
//            case R.id.button_value_20_sharj_ac:
//                Value.setText("20000");
//                break;
//            case R.id.button_value_50_sharj_ac:
//                Value.setText("50000");
//                break;
//            case R.id.button_value_100_sharj_ac:
//                Value.setText("100000");
//                break;
//        }
    }
    //On Click Value Buttons Event End










    //On Click Done Button Start
    public void onclick_done_button_sharj_card_ac(View v)
    {
        startActivity(new Intent(getApplicationContext(),sharj_card_web_view_ac.class));
    }
    //On Click Done Button End










    //Adaptor Start
    class adaptor extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return database_class.Price_card_id.size();
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

            view=getLayoutInflater().inflate(R.layout.price_grid_ly,null);



            try
            {


                TextView textView1, textView2, textView3;

                textView1 = (TextView) view.findViewById(R.id.days_lable_price_grid_ly);
                textView2 = (TextView) view.findViewById(R.id.count_lable_price_grid_ly);
                textView3 = (TextView) view.findViewById(R.id.money_lable_price_grid_ly);
                final CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_price_grid_ly);


                textView1.setText("تعداد روز : " + database_class.Price_card_days.get(i));
                textView2.setText("تعداد سرویس : " + database_class.Price_card_count.get(i));
                textView3.setText("قیمت : " + database_class.Price_card_price.get(i));


                final int t = i;


                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Value.setText(database_class.Price_card_price.get(t));
                        Item_Clicked = database_class.Price_card_id.get(t);
                        startActivity(new Intent(getApplicationContext(), sharj_card_web_view_ac.class));
                    }
                });


//            checkBoxes.add(checkBox);


//            checkBox.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View view)
//                {
//
////                    clear_check(t);
//
//                }
//            });

            }
            catch (Exception Err)
            {

            }


            return view;
        }





//        public void clear_check(int not_checked)
//        {
//
//
//            for(int a=0;a<checkBoxes.size();a++)
//            {
//                if(a==not_checked)
//                {
//                    Toast.makeText(sharj_card_ac.this, "Salam", Toast.LENGTH_SHORT).show();
//                    checkBoxes.get(a).setChecked(true);
//                    checkBoxes.get(a).setSelected(true);
//                }
//                else
//                {
//                    checkBoxes.get(a).setChecked(false);
//                    checkBoxes.get(a).setSelected(false);
//                }
//            }
//        }




    }
    //Adaptor End









}
