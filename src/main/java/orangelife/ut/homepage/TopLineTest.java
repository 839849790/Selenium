package orangelife.ut.homepage;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangelife.page.homepage.TopLine;

/**
 * 首页头条、商城、和惠民购物、团购测试
 * @author qihuan
 * 
 */
public class TopLineTest {

    private static String host = "http://m.orangelife.com.cn/";    //定义驱动网址
    private static WebDriver driver;
        
    private String home_url = host + "#home";
    private String login_url = host + "#login";
    private String activityDetail = host + "#newsDetail";
    private String mall_url = host + "#mall";
    private String mallGroupBuy_url = host + "#mallGroupBuy";

    //用例2：点击进入头条详情，标签值为验证方式
    @Test
    public void testCase2() {
        Reporter.log("首页头条测试:点击进入头条详情，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.preStep(driver);
        topLine.clickTopLine(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(activityDetail));
    }

    //用例3：进入头条详情页后，点击返回按钮返回首页，标签值为验证方式
    @Test
    public void testCase3() {
        Reporter.log("首页头条测试:进入头条详情页后，点击返回按钮返回首页，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.preStep(driver);
        topLine.clickTopLine(driver);
        topLine.clickHeaderBack(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(home_url));
    }
    
    //用例4：进入首页后，查看时商城选项栏，标签值为验证方式
    @Test
    public void testCase4() {
        Reporter.log("首页商城模块测试:进入首页后，查看时商城选项栏，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.preStep(driver);
        Assert.assertTrue(topLine.checkMall(driver));
    }
    
    //用例5：进入首页后，点击商城选项栏中的灰色图标：操作无效果，标签值为验证方式
    @Test
    public void testCase5() {
        Reporter.log("首页商城模块测试:进入首页后，点击商城选项栏中的灰色图标：操作无效果，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.preStep(driver);
        Assert.assertTrue(topLine.clickGrayMall(driver));
    }
    
    //用例6：进入首页后，在未登录状况下点击商城选项栏中的填色图标【京东】，提示登录，标签值为验证方式
    @Test
    public void testCase6() {
        Reporter.log("首页商城模块测试:在未登录状况下点击商城选项栏中的填色图标【京东】，提示登录，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.preStep(driver);
        Assert.assertTrue(topLine.clickBrightMallWithOutLogin(driver));
    }
    
    //用例7：进入首页后，在未登录状况下点击商城选项栏中的填色图标【京东】提示登录后，取消登录返回首页，标签值为验证方式
    @Test
    public void testCase7() {
        Reporter.log("首页商城模块测试:在未登录状况下点击商城选项栏中的填色图标【京东】提示登录后，取消登录返回首页，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.preStep(driver);
        topLine.cancelLoginMallWithOutLogin(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(home_url));
    }
    
    //用例8：进入首页后，在未登录状况下点击商城选项栏中的填色图标【京东】提示登录后，点击确认登录后进入登录页面，标签值为验证方式
    @Test
    public void testCase8() {
        Reporter.log("首页商城模块测试:在未登录状况下点击商城选项栏中的填色图标【京东】提示登录后，点击确认登录后进入登录页面，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.preStep(driver);
        topLine.confirmLoginMallWithOutLogin(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(login_url));
    }
    
    //用例9：在未登录状况下点击商城选项栏中的填色图标【京东】提示登录后，点击确认登录后进入登录页面,输入登录信息登录成功跳转至商城主页，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase9(String userMobile, String password) {
        Reporter.log("首页商城模块测试:在未登录状况下点击商城选项栏中的填色图标【京东】提示登录后，点击确认登录后进入登录页面,输入登录信息登录成功跳转至商城主页，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.preStep(driver);
        Assert.assertTrue(topLine.enterMallWithLogin(driver, userMobile, password));
    }
    
    //用例10：在已登录状况下点击商城选项栏中的填色图标【京东】,直接跳转至相应商城首页，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase10(String userMobile, String password) {
        Reporter.log("首页商城模块测试:在未登录状况下点击商城选项栏中的填色图标【京东】,直接跳转至相应商城首页，标签值为验证方式");
        TopLine topLine = new TopLine();
        Assert.assertTrue(topLine.checkRegisterWithLogin(driver, userMobile, password));
    }

    //用例12：在已登录后进入商城页击左上方房子样式按钮返回商城首页，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase12(String userMobile, String password) {
        Reporter.log("首页商城模块测试:在已登录后进入商城页击左上方房子样式按钮返回商城首页，标签值为验证方式");
        TopLine topLine = new TopLine();
        Assert.assertTrue(topLine.clickHouse(driver, userMobile, password));
    }
    
    //用例13：在已登录后进入商城页点击左上方“×”按钮返回APP首页，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase13(String userMobile, String password) {
        Reporter.log("首页商城模块测试:在已登录后进入商城页点击左上方“×”按钮返回APP首页，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.clickFork(driver, userMobile, password);
        Assert.assertTrue(driver.getCurrentUrl().contains(home_url));
    }

    //用例14：在首页点击惠民购物部分，跳转至惠民购物主页，标签值为验证方式
    @Test
    public void testCase14() {
        Reporter.log("首页惠民购物模块测试:在首页点击惠民购物部分，跳转至惠民购物主页，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.preStep(driver);
        topLine.clickHuiminShopping(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(mall_url));
    }
    
    //用例15：在首页进入惠民购物主页后点击返回按钮返回首页，标签值为验证方式
    @Test
    public void testCase15() {
        Reporter.log("首页惠民购物模块测试:在首页进入惠民购物主页后点击返回按钮返回首页，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.preStep(driver);
        topLine.clickBackFromHuiminShopping(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(home_url));
    }

    //用例16：在首页点击惠民团购部分，跳转至惠民团购商品列表页面，标签值为验证方式
    @Test
    public void testCase16() {
        Reporter.log("首页惠民团购模块测试:在首页点击惠民团购部分，跳转至惠民团购商品列表页面，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.preStep(driver);
        topLine.clickHuiminGroup(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(mallGroupBuy_url));
    }
    
    //用例17：从首页进入惠民团购商品列表页面后点击返回按钮返回首页，标签值为验证方式
    @Test
    public void testCase17() {
        Reporter.log("首页惠民团购模块测试:从首页进入惠民团购商品列表页面后点击返回按钮返回首页，标签值为验证方式");
        TopLine topLine = new TopLine();
        topLine.preStep(driver);
        topLine.clickBackFromHuiminGroup(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(home_url));
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
