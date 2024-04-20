package com.atguigu.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
        String ls = "1 2 3 4 5";
        List<String> listString2 = getListString2(ls);
        System.out.println(listString2);

    }

    public static List<String> getListString2(String suffExpression) {
        String[] split = suffExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }
        return list;


    }
}
