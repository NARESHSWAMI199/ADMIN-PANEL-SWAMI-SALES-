package com.sales;


import java.util.Arrays;
import java.util.List;

public class Test {

        public static void main(String args[]) {
        String result = "";
        String name = "Naresh  swami";
        List<String> arr = Arrays.asList(name.split(" "));
        for (int i= arr.size()-1; i >= 0; i-- ){
            //if (arr[i].equals(" ")) continue;
            result += arr.get(i);
        }
         System.out.println(result);
        }

}

