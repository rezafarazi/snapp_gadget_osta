package com.gagit.snapp.osta.ostasnappgadget;


import java.util.Random;


public class security
{

    public String code()
    {
        Random rand = new Random();
        int[] arr = new int[501];
        int x = 0;
        for(int i=500;i<=1000;i++)
        {
            arr[x] = (i*i*i);
            x++;
        }
        int rnd = rand.nextInt(arr.length-3);
        rnd++;
        String final_t = binary(arr[rnd])+"-"+binary(arr[rnd+1])+"-"+binary(arr[rnd+2]);
        return final_t;
    }


    public static String binary(int number)
    {
        int a;
        String b = "";
        while(number!=0)
        {
            a = number%2;
            b = a+b;
            number = number/2;
        }
        return b;
    }
}