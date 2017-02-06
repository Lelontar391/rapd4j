package com.rapd4j.util;
import java.math.BigDecimal;

/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，<br>
 * 这个工具类提供精确的浮点数运算，包括加减乘除和四舍五入。<br>
 * @author Lelonta
 *
 */
public class Calculate {
	
      //默认除法运算精度
      private static final int DEF_DIV_SCALE = 2;
     
      //这个类不能实例化
      public Calculate() {
      }
      
      /**
       * 将数字进行格式化，将“.7”转换成“0.7”的形式
       * @param str1 输入值
       * @return 格式化后的字符串表示的数字，返回值为String
       */
      public static String FormatZeroString(String str1){
          if(str1.indexOf(".")!=-1){
            if(str1.indexOf(".")==0){
              str1 = "0"+str1;
            }
          }
          return str1;
      }
      
      /**
       * 将数字进行格式化，将“.7”转换成“0.7”的形式
       * @param str1 输入值
       * @return 格式化后的字符串表示的数字，返回值为double
       */
      public static double FormatZeroDouble(String str1){
          if(str1.indexOf(".")!=-1){
            if(str1.indexOf(".")==0){
              str1 = "0"+str1;
            }
          }
          return Double.valueOf(str1).doubleValue();
      }
      
      /**
       * 提供精确的加法运算
       * @param str1 被加数
       * @param str2 加数
       * @return 两个参数的和，返回值为String
       */
      public static String AddString(String str1,String str2){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          return FormatZeroString(b1.add(b2).toString());
      }
      
      /**
       * 提供精确的加法运算
       * @param str1 被加数
       * @param str2 加数
       * @return 两个参数的和，返回值为double
       */
      public static double AddDouble(String str1,String str2){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          //return b1.add(b2).doubleValue();
          return FormatZeroDouble(b1.add(b2).toString());
      }
      
      /**
       * 提供精确的减法运算
       * @param str1 被减数
       * @param str2 减数
       * @return 两个参数的差，返回值为String
       */
      public static String SubString(String str1,String str2){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          return FormatZeroString(b1.subtract(b2).toString());
      }
      
      /**
       * 提供精确的减法运算
       * @param str1 被减数
       * @param str2 减数
       * @return 两个参数的差，返回值为double
       */
      public static double SubDouble(String str1,String str2){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          //return b1.subtract(b2).doubleValue();
          return FormatZeroDouble(b1.subtract(b2).toString());
      }
      
      /**
       * 提供（相对）传入的参数与0的比较
       * 专门用于的与“0”比较的判断语句
       * @param str1 输入值
       * @return 两个参数的差，返回值为String
       */
      public static String SubZeroString(String str1){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal("0");
          return FormatZeroString(b1.subtract(b2).toString());
      }
      
      /**
       * 提供（相对）传入的参数与0的比较
       * 专门用于的与“0”比较的判断语句
       * @param str1 输入值
       * @return 两个参数的差，返回值为double
       */
      public static double SubZeroDouble(String str1){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal("0");
          //return b1.subtract(b2).doubleValue();
          return FormatZeroDouble(b1.subtract(b2).toString());
      }
      
      /**
       * 提供精确的乘法运算
       * @param str1 被乘数
       * @param str2 乘数
       * @return 两个参数的积，返回值为String
       */
      public static String MulString(String str1,String str2){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          return FormatZeroString(b1.multiply(b2).toString());
      }
      
      /**
       * 提供精确的乘法运算
       * @param str1 被乘数
       * @param str2 乘数
       * @return 两个参数的积，返回值为double
       */
      public static double MulDouble(String str1,String str2){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          return FormatZeroDouble(b1.multiply(b2).toString());
      }
      
      /**
       * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
       * 小数点以后2位，以后的数字四舍五入
       * @param str1 被除数
       * @param str2 除数
       * @return 两个参数的商，返回值为String
       */
      public static String DivString(String str1,String str2){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          return FormatZeroString(b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).toString());
      }
      
      /**
       * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
       * 小数点以后2位，以后的数字四舍五入
       * @param str1 被除数
       * @param str2 除数
       * @return 两个参数的商，返回值为double
       */
      public static double DivDouble(String str1,String str2){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          return FormatZeroDouble(b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).toString());
      }
      
      /**
       * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
       * 小数点以后2位，以后的数字四舍五入
       * @param str1 被除数
       * @param str2 除数
       * @return 两个参数的商，返回值为String,并去掉结尾的多余的0
       */
      public static String DivZeroString(String str1,String str2){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          String str3 = b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).toString();
          //去掉结尾的多余的0
          if(str3.indexOf(".")!=-1){
               while((str3.length()-1)==str3.lastIndexOf("0")){
                        str3 = str3.substring(0,str3.lastIndexOf("0"));
                 }
                 if((str3.length()-1)==str3.lastIndexOf("."))
                  str3 = str3.substring(0,str3.length()-1);
           }
          return FormatZeroString(str3);
      }
      
      /**
       * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
       * 小数点以后2位，以后的数字四舍五入
       * @param str1 被除数
       * @param str2 除数
       * @return 两个参数的商，返回值为double,并去掉结尾的多余的0
       */
      public static double DivZeroDouble(String str1,String str2){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          String str3 = b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).toString();
          //去掉结尾的多余的0
          if(str3.indexOf(".")!=-1){
               while((str3.length()-1)==str3.lastIndexOf("0")){
                        str3 = str3.substring(0,str3.lastIndexOf("0"));
                 }
                 if((str3.length()-1)==str3.lastIndexOf("."))
                  str3 = str3.substring(0,str3.length()-1);
           }
          return FormatZeroDouble(str3);
      }
      
      /**
       * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
       * 定精度，以后的数字四舍五入
       * @param str1 被除数
       * @param str2 除数
       * @param scale 表示表示需要精确到小数点以后几位
       * @return 两个参数的商，返回值为String
       */
      public static String DivScaleString(String str1,String str2,int scale){
          if(scale<0){
              throw new IllegalArgumentException(
                  "The scale must be a positive integer or zero");
          }
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          return FormatZeroString(b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).toString());
      }
      
      /**
       * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
       * 定精度，以后的数字四舍五入
       * @param str1 被除数
       * @param str2 除数
       * @param scale 表示表示需要精确到小数点以后几位
       * @return 两个参数的商，返回值为String,并去掉结尾多余的0
       */
      public static String DivScaleZeroString(String str1,String str2,int scale){
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          String str3 = b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).toString();
          //去掉结尾的多余的0
          if(str3.indexOf(".")!=-1){
               while((str3.length()-1)==str3.lastIndexOf("0")){
                        str3 = str3.substring(0,str3.lastIndexOf("0"));
                 }
                if((str3.length()-1)==str3.lastIndexOf("."))
                  str3 = str3.substring(0,str3.length()-1);
           }
          return FormatZeroString(str3);
      }
      
      /**
       * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
       * 定精度，以后的数字四舍五入
       * @param str1 被除数
       * @param str2 除数
       * @param scale 表示表示需要精确到小数点以后几位
       * @return 两个参数的商，返回值为double
       */
      public static double DivScaleDouble(String str1,String str2,int scale){
          if(scale<0){
              throw new IllegalArgumentException(
                  "The scale must be a positive integer or zero");
          }
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          return FormatZeroDouble(b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).toString());
      }
      
      /**
       * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
       * 定精度，以后的数字四舍五入
       * @param str1 被除数
       * @param str2 除数
       * @param scale 表示表示需要精确到小数点以后几位
       * @return 两个参数的商，返回值为double,并去掉结尾多余的0
       */
      public static double DivScaleZeroDouble(String str1,String str2,int scale){
          if(scale<0){
              throw new IllegalArgumentException(
                  "The scale must be a positive integer or zero");
          }
          BigDecimal b1 = new BigDecimal(str1);
          BigDecimal b2 = new BigDecimal(str2);
          String str3 = b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).toString();
          //去掉结尾的多余的0
          if(str3.indexOf(".")!=-1){
               while((str3.length()-1)==str3.lastIndexOf("0")){
                        str3 = str3.substring(0,str3.lastIndexOf("0"));
                 }
                 if((str3.length()-1)==str3.lastIndexOf("."))
                  str3 = str3.substring(0,str3.length()-1);
           }
          return FormatZeroDouble(str3);
      }
      
      /**
       * 提供精确的小数位四舍五入处理
       * @param str 需要四舍五入的数字
       * @param scale 小数点后保留几位
       * @return 四舍五入后的结果，返回值为String
       */
      public static String RoundString(String str,int scale){
          if(scale<0){
              throw new IllegalArgumentException(
                  "The scale must be a positive integer or zero");
          }
          BigDecimal b = new BigDecimal(str);
          BigDecimal one = new BigDecimal("1");
          return FormatZeroString(b.divide(one,scale,BigDecimal.ROUND_HALF_UP).toString());
      }
      
      /**
       * 提供精确的小数位四舍五入处理
       * @param str 需要四舍五入的数字
       * @param scale 小数点后保留几位
       * @return 四舍五入后的结果，返回值为double
       */
      public static double RoundDouble(String str,int scale){
          if(scale<0){
              throw new IllegalArgumentException(
                  "The scale must be a positive integer or zero");
          }
          BigDecimal b = new BigDecimal(str);
          BigDecimal one = new BigDecimal("1");
          return FormatZeroDouble(b.divide(one,scale,BigDecimal.ROUND_HALF_UP).toString());
      }
      
      /**
       * 去掉结尾多余的0
       * @param str3
       * @return
       */
      public static String delSuperZero(String str3){

          if(str3.indexOf(".")!=-1){
               while((str3.length()-1)==str3.lastIndexOf("0")){
                        str3 = str3.substring(0,str3.lastIndexOf("0"));
                 }
                 if((str3.length()-1)==str3.lastIndexOf("."))
                  str3 = str3.substring(0,str3.length()-1);
           }
          
          return str3;
          
      }

}