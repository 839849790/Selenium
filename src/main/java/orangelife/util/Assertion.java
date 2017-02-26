package orangelife.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
 /**
  * 断言类：
  * 被调用判断
  * @author 欢
  * 
  */
 public class Assertion {
     
     private static Logger log = LoggerFactory.getLogger(Assertion.class);
     public static boolean flag=true;    
     public static List<Error> errors=new ArrayList<Error>();
     
     public static void begin(){
         flag=true;
     }
     
     public static void end(){
         Assert.assertTrue(flag);
     }
     
     public static void verifyEquals(Object actual, Object expected){
         try{
             Assert.assertEquals(actual, expected);
         }catch(Error e){
             errors.add(e);
             flag = false;
         }
     }
     
     public static void verifyEquals(Object actual, Object expected, String message){
         try{
             Assert.assertEquals(actual, expected, message);
             log.info("方法验证成功!");
         }catch(Error e){
             errors.add(e);
             flag = false;
             log.info("方法验证失败!");
         }
     }
 
 }


