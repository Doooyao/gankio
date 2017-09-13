package com.example.administrator.mygankio.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tdfz on 2017/9/6.
 */

public class StringUtils {
    public List<String> getTitleFromContent(String htmlSource){
        List<String> list = new ArrayList<String>();
        String s = "";
        //Pattern pa = Pattern.compile("<title>.*?</title>", Pattern.CANON_EQ);也可以
        Pattern pa = Pattern.compile("\"title\": \".\"");//源码中标题正则表达式
        Matcher ma = pa.matcher(htmlSource);
        while (ma.find())//寻找符合el的字串
        {
            s = ma.group();
        }
        String[] parts = s.split("/");
        for(int i=0,j=parts.length;i<j;i++){
            list.add(parts[i]);
        }
        return list;
    }

}
