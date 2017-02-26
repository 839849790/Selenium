package orangelife.ut;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangelife.page.ChooseCommunity;

/**
 * 选择小区测试
 * @author qihuan
 * 
 */
public class ChooseCommunityTest {

    private static String host = "http://m.orangelife.com.cn/";    //定义驱动网址
    private static WebDriver driver;
    
    private String home_url = host + "#home";
    
    //用例1：首次登录随机选择小区，登录成功，url正确为验证方式
    @Test
    public void testCase1() {
        Reporter.log("选择小区测试:随机选择小区");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }
    
    @BeforeClass
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get(host);
        driver.manage().window().setSize(new Dimension(430, 700));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
    }
    
    @AfterClass
    public void afterMethod() {
        //关闭打开的浏览器 
        driver.quit();  
    }
}
