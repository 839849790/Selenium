package orangelife.util;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * 工具类
 * @author 欢
 * 
 */
public class SeleniumUtil {

    //判断元素是否存在
    public static boolean isElementExist(WebDriver driver, By locator) {
        boolean status = false;  
        try{
            driver.findElement(locator);
            status = true;    
        }catch(Exception e){
            status = false;
        }
        return status;  
    }
    
    //判断元素的子元素否存在
    public static boolean isSonElementExist(WebElement webElement, By locator) {
        boolean status = false;  
        try{
            webElement.findElement(locator);
            status = true;    
        }catch(Exception e){
            status = false;
        }
        return status;  
    }
    
    //模拟鼠标点击事件
    public static void mouseClick(WebDriver driver, WebElement webElement) {
        Actions virtualBtn = new Actions(driver);  
        virtualBtn.moveToElement(webElement).perform();
        virtualBtn.click(webElement).perform();
    }


    /**
    * 上传文件，需要点击弹出上传照片的窗口才行
    *
    * @parambrower 使用的浏览器名称
    * @paramfile 需要上传的文件及文件名
    */
    public static void handleUpload(String browser, File file) {
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        String filePath= file.getAbsolutePath();
        String executeFile= "E:\\upfile.exe"; //定义了upfile.exe文件的路径
        String cmd= "\""+ executeFile+ "\""+ " "+ "\""+ browser+ "\""+ " "+ "\""+ filePath+ "\"";
        System.out.println("cmd==="+cmd);
        try{
            Process p= Runtime.getRuntime().exec(cmd);
            p.waitFor();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    //设置休眠时间和循环次数
    public static void setSleepTime(WebDriver driver, By locator, Long second, int times) {     
        for(int i=0; i<times; i++){
            if(isElementExist(driver, locator)){
                break;
            }else{
               try {
                   Thread.sleep(second);
                   i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
