package com.prueba.AmigoInvisible.utility;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Utility {
    public  static String cap(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String capitalize(String s){
        if(s.contains(" ")){
            return Arrays.stream(s.split(" ")).map(Utility::cap).collect(Collectors.joining(" "));
        }else{
            return cap(s);
        }
    }

}
