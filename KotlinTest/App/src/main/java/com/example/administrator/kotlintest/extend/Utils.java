package com.example.administrator.kotlintest.extend;

public class Utils {




 private void fund(String[] util){


     if(isEmpty(util)){



     }



 }


 public static boolean isEmpty(String [] util){
     return util.length==0;
 }

 public static boolean isNotEmpty(String [] util){
     return util.length>0;
 }
}
