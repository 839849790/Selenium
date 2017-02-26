package orangelife.ut;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangelife.page.Login;

/**
 * 登录测试
 * @author qihuan
 * 
 */
public class LoginTest {

    private static String host = "http://m.orangelife.com.cn/";    //定义驱动网址
    private static WebDriver driver;
    
    private String home_url = host + "#home";
    private String login_url = host + "#login/_DL__DL_%23home_DL__DL__DL_";
    
    //用例1：正确用户名、密码，登录成功，url正确为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase1(String userMobile, String password) {
        Reporter.log("登录测试:正确用户名、密码");
        Login testLogin = new Login();
        testLogin.testLogin(driver, userMobile, password);
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }
    
    //用例2：正确用户名、错误密码，登录失败，url正确为验证方式
    @Test(dataProvider="loginData2",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase2(String userMobile, String password) {
        Reporter.log("登录测试：正确用户名、错误密码");
        Login testLogin = new Login();
        testLogin.testLogin(driver, userMobile, password);
        Assert.assertEquals(login_url, driver.getCurrentUrl());
    }
    
    //用例3：用户名错误、密码正确，登录失败，url正确为验证方式
    @Test(dataProvider="loginData3",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase3(String userMobile, String password) {
        Reporter.log("登录测试：用户名错误、密码正确");
        Login testLogin = new Login();
        testLogin.testLogin(driver, userMobile, password);
        Assert.assertEquals(login_url, driver.getCurrentUrl());
    }
    
    //用例4：用户名错误、密码错误，登录失败，url正确为验证方式
    @Test(dataProvider="loginData4",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase4(String userMobile, String password) {
        Reporter.log("登录测试：用户名错误、密码错误");
        Login testLogin = new Login();
        testLogin.testLogin(driver, userMobile, password);
        Assert.assertEquals(login_url, driver.getCurrentUrl());
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get(host);
        driver.manage().window().setSize(new Dimension(430, 700));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
    }  
    
    @AfterMethod
    public void afterMethod() {
        //关闭打开的浏览器 
        driver.quit();  
    } 

}
