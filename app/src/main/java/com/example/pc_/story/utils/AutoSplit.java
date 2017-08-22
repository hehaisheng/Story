package com.example.pc_.story.utils;

/**
 * Created by pc- on 2017/7/4.
 */
public class AutoSplit {


    public  static String  autoSplit(String recommendData){
        String[]  strings1=recommendData.split("@");
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<strings1.length;i++){
            if(i!=strings1.length-1){
                stringBuilder.append(strings1[i]).append("\n");
            }else{
                stringBuilder.append(strings1[i]);
            }
        }
        return stringBuilder.toString();

    }
}
