package com.maliblo.fincam;

public class HelperClass {

    public static String getTimeStamp(){
        return String.valueOf(System.currentTimeMillis()/1000);
    }
    public static Boolean processValue(String str,int length){
        if(str.matches(".*\\d.*")){
            // contains a number
            if(str.replaceAll("[\\D]", "").length()== length)
                return true;
            else
                return false;
        } else{
            // does not contain a number
            return false;
        }
    }
}
