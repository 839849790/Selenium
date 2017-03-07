package orangelife.ut.person;

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
import orangelife.page.person.Person;

/**
 * 个人页相关内容测试
 * @author qihuan
 * 
 */
public class PersonTest {

    private static String host = "http://m.orangelife.com.cn/";    //定义驱动网址
    private static WebDriver driver;
    
    private String home_url = host + "#home";
    private String userInfo_url = host + "#userInfo";
    private String myCommunity = "我的小区";
    private String myCollection = "我的收藏";
    private String myShopping = "我的购物";
    private String myHuimin = "我的惠民";
    private String myMsg = "消息中心";
   
    //用例1：设置按钮测试：在未登录情况下，进入首页后点击“我的”，而后点击设置按钮，弹出登录提醒框，标签正确为验证方式
    @Test(priority=1,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase1(String userMobile, String password) {
        Reporter.log("设置按钮测试：未登录情况下，进入首页后点击“我的”，而后点击设置按钮，弹出登录提醒框，标签正确为验证方式");
        Person person = new Person();
        person.preStep(driver);
        Assert.assertTrue(person.checkSetWithoutLogin(driver));
    }
  
    //用例2：设置按钮测试：在已登录情况下，进入首页后点击“我的”，而后点击设置按钮，进入设置页面，标签正确为验证方式
    @Test(priority=2,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase2(String userMobile, String password) {
        Reporter.log("设置按钮测试：设置按钮测试：在已登录情况下，进入首页后点击“我的”，而后点击设置按钮，进入设置页面，标签正确为验证方式");
        Person person = new Person();
        person.preStepWithLogin(driver, userMobile, password);
        Assert.assertTrue(person.checkSetWithLogin(driver));
    }
   
    //用例3：设置按钮测试：在已登录情况下进入设置页面，点击消息到达提醒按钮，可以开启/关闭消息到达，标签正确为验证方式
    @Test(priority=3,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase3(String userMobile, String password) {
        Reporter.log("设置按钮测试：在已登录情况下进入设置页面，点击消息到达提醒按钮，可以开启/关闭消息到达，标签正确为验证方式");
        Person person = new Person();
        person.preStepWithLogin(driver, userMobile, password);
        person.clickSet(driver);
        Assert.assertTrue(person.clickMsgSet(driver));
    }
   
    //用例4：设置按钮测试：在已登录情况下进入设置页面，点击“正文字号”按钮，弹出调整字号大小提示框，标签正确为验证方式
    @Test(priority=4,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase4(String userMobile, String password) {
        Reporter.log("设置按钮测试：在已登录情况下进入设置页面，点击”正文字号”按钮，弹出调整字号大小提示框，标签正确为验证方式");
        Person person = new Person();
        person.preStepWithLogin(driver, userMobile, password);
        person.clickSet(driver);
        Assert.assertTrue(person.clickFontSet(driver));
    }

    //用例5：设置按钮测试：在已登录情况下进入设置页面，点击“意见反馈”按钮，弹出意见反馈输入框，标签正确为验证方式
    @Test(priority=5,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase5(String userMobile, String password) {
        Reporter.log("设置按钮测试：在已登录情况下进入设置页面，点击“意见反馈”按钮，弹出意见反馈输入框，标签正确为验证方式");
        Person person = new Person();
        person.preStepWithLogin(driver, userMobile, password);
        person.clickSet(driver);
        Assert.assertTrue(person.clickFeedbackSet(driver));
    }
   
    //用例7：个人信息测试：在未登录情况下，进入首页后点击“我的”，而后点击头像，弹出登录提醒框，标签正确为验证方式
    @Test(priority=7)
    public void testCase7() {
        Reporter.log("个人信息测试：在未登录情况下，进入首页后点击“我的”，而后点击头像，弹出登录提醒框，标签正确为验证方式");
        Person person = new Person();
        person.preStep(driver);
        Assert.assertTrue(person.checkHeadImgWithoutLogin(driver));
    }
    
    //用例8：个人信息测试：在已登录情况下，进入首页后点击“我的”，而后点击头像，进入个人信息页面，标签正确为验证方式
    @Test(priority=8,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase8(String userMobile, String password) {
        Reporter.log("个人信息测试：在已登录情况下，进入首页后点击“我的”，而后点击头像，进入个人信息页面，标签正确为验证方式");
        Person person = new Person();
        person.preStepWithLogin(driver, userMobile, password);
        Assert.assertTrue(person.checkHeadImgWithLogin(driver));
    }
    
    //用例9：个人信息测试：未定位登录验证:进入定位页面重新定位后，点击登录，输入登录数据，登录成功，跳转到首页，标签正确为验证方式
    @Test(priority=9,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase9(String userMobile, String password) {
        Reporter.log("个人信息测试：进入定位页面重新定位后，点击登录，输入登录数据，登录成功，跳转到首页，标签正确为验证方式");
        Login person = new Login();
        person.testLogin(driver, userMobile, password);
        Assert.assertTrue(driver.getCurrentUrl().equals(home_url));
    }
    
    //用例10：个人信息测试：已定位登录验证:进入首页-我的-点击个人中心，弹出按钮点击立即登录，点击登录输入登录数据，登录成功，跳转到首页，标签正确为验证方式
    @Test(priority=10,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase10(String userMobile, String password) {
        Reporter.log("个人信息测试：进入定位页面重新定位后，点击登录，输入登录数据，登录成功，跳转到首页，标签正确为验证方式");
        Person person = new Person();
        person.preStep(driver);
        person.clickHeadImgToLogin(driver, userMobile, password);
        Assert.assertTrue(driver.getCurrentUrl().equals(userInfo_url));
    }
    
    //用例13：个人信息测试：已登录验证:进入首页-我的-点击头像-修改，弹出修改提示框，标签正确为验证方式
    @Test(priority=13,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase13(String userMobile, String password) {
        Reporter.log("个人信息测试：已登录验证:进入首页-我的-点击头像-修改，弹出修改提示框，标签正确为验证方式");
        Person person = new Person();
        person.preStepWithLogin(driver, userMobile, password);
        Assert.assertTrue(person.changeHeadImg(driver));
    }
   
    //用例14：个人信息测试：已登录验证:进入首页-我的-点击头像-点击修改密码，跳转到重置密码页面，标签正确为验证方式
    @Test(priority=14,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase14(String userMobile, String password) {
        Reporter.log("个人信息测试：已登录验证:进入首页-我的-点击头像-点击修改密码，跳转到重置密码页面，标签正确为验证方式");
        Person person = new Person();
        person.preStepWithLogin(driver, userMobile, password);
        Assert.assertTrue(person.changePsd(driver));
    }
    
    //用例15：我的小区测试：已登录验证:进入首页-我的-点击我的小区，跳转到我的小区页面，标签正确为验证方式
    @Test(priority=15,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase15(String userMobile, String password) {
        Reporter.log("我的小区测试：已登录验证:进入首页-我的-点击我的小区，跳转到我的小区页面，标签正确为验证方式");
        Person person = new Person();
        person.preStepWithLogin(driver, userMobile, password);
        Assert.assertTrue(person.myCommunity(driver, myCommunity));
    }
  
    //用例16：我的收藏测试：已登录验证:进入首页-我的-点击我的收藏，跳转到我的收藏页面，标签正确为验证方式
    @Test(priority=16,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase16(String userMobile, String password) {
        Reporter.log("我的收藏测试：已登录验证:进入首页-我的-点击我的收藏，跳转到我的收藏页面，标签正确为验证方式");
        Person person = new Person();
        person.preStepWithLogin(driver, userMobile, password);
        Assert.assertTrue(person.myCollection(driver, myCollection));
    }
    
    //用例17：我的购物测试：已登录验证:进入首页-我的-点击我的购物，跳转到我的购物页面，标签正确为验证方式
    @Test(priority=17,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase17(String userMobile, String password) {
        Reporter.log("我的购物测试：已登录验证:进入首页-我的-点击我的购物，跳转到我的购物页面，标签正确为验证方式");
        Person person = new Person();
        person.preStepWithLogin(driver, userMobile, password);
        Assert.assertTrue(person.myShopping(driver, myShopping));
    }
   
    //用例18：我的惠民测试：已登录验证:进入首页-我的-点击我的惠民，跳转到我的惠民页面，标签正确为验证方式
    @Test(priority=18,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase18(String userMobile, String password) {
        Reporter.log("我的惠民测试：已登录验证:进入首页-我的-点击我的惠民，跳转到我的惠民页面，标签正确为验证方式");
        Person person = new Person();
        person.preStepWithLogin(driver, userMobile, password);
        Assert.assertTrue(person.myHuimin(driver, myHuimin));
    }
   
    //用例19：消息中心测试：已登录验证:进入首页-我的-点击消息中心，跳转到消息中心页面，标签正确为验证方式
    @Test(priority=19,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase19(String userMobile, String password) {
        Reporter.log("消息中心测试：已登录验证:进入首页-我的-点击消息中心，跳转到消息中心页面，标签正确为验证方式");
        Person person = new Person();
        person.preStepWithLogin(driver, userMobile, password);
        Assert.assertTrue(person.myMsg(driver, myMsg));
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
