package orangelife.ut.waiter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangelife.page.waiter.WaiterList;
import orangelife.util.SeleniumUtil;

/**
 * 惠民服务页面测试
 * @author qihuan
 * 
 */
public class WaiterTest {

    private static String host = "http://teast.swao.cn/";    //定义驱动网址
    private static WebDriver driver;
    
    private String waiter_url = host + "#waiterList";
    private String home_url = host + "#home";
    private String login_url = host + "#login";
    private String person_waiter_url = host + "#waiterIntro/unServant";
    private String apply_url = host + "#waiterApply";
    
    private String waiter_img = "https://s.orangelife.com.cn/img/default/recommend-waiter.jpg";
    private String toLogin = "立即登录";
    
    //用例1：测试新用户首次进入选择小区页，选择小区后跳转到惠民服务员页面，url是否正确为验证方式
    @Test
    public void testCase1() {
        Reporter.log("惠民服务员测试:测试新用户首次进入选择小区页，选择小区后跳转到惠民服务员页面，url是否正确为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.testWaiter(driver);
        //校验
        Assert.assertEquals(waiter_url, driver.getCurrentUrl());
    }    
            
    //用例2：测试新用户首次进入惠民服务员页面,点击后退按钮退回到首页，url是否正确为验证方式
    @Test
    public void testCase2() {
        Reporter.log("惠民服务员测试:测试新用户首次进入惠民服务员页面,点击后退按钮退回到首页，url是否正确为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.testBack(driver);
        //校验
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }
       
    //用例3：测试新用户首次进入惠民服务员页面后，顶部介绍图显示正常，图片链接是否正确为验证方式
    @Test
    public void testCase3() {
        Reporter.log("惠民服务员测试:测试新用户首次进入惠民服务员页面后，顶部介绍图显示正常，图片链接是否正确为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行,校验
        Assert.assertEquals(waiter_img, waiter.testWaiterImg(driver));
    }
    
    //用例4：测试新用户首次进入惠民服务员页面后，附近服务员列表显示正常，附近服务员列表数据是否为空为验证方式
    @Test
    public void testCase4() {
        Reporter.log("惠民服务员测试:测试新用户首次进入惠民服务员页面后，附近服务员列表显示正常，附近服务员列表数据是否为空为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.testWaiter(driver);
        //校验
        Assert.assertNotNull(driver.findElement(By.id("nearby-waiter")).getText());
    }
    
    //用例5：测试新用户首次进入惠民服务员页面后，惠民员申请通道显示正常，是否弹出登录对话框为验证方式
    @Test
    public void testCase5() {
        Reporter.log("惠民服务员测试:测试新用户首次进入惠民服务员页面后，惠民员申请通道显示正常，是否弹出登录对话框为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.applyChannel(driver);
        //校验
        Assert.assertEquals(toLogin, driver.findElement(By.className("confirm-yes")).getText());
    }
    
    //用例6：测试惠民服务员头像是否存在，头像链接正常为验证方式
    @Test
    public void testCase6() {
        Reporter.log("惠民服务员测试:测试惠民服务员头像是否存在，头像链接正常为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.testWaiter(driver);
        List<WebElement> elements = driver.findElements(By.className("waiter-single"));
        for(int i=1; i<=elements.size(); i++){
            //校验
            Assert.assertNotEquals("", driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div[" +i+ "]/div[1]/img")).getAttribute("src"));
        }
    }
     
    //用例7：测试惠民服务员姓名是否存在，姓名标签有值为验证方式
    @Test
    public void testCase7() {
        Reporter.log("惠民服务员测试:测试惠民服务员姓名是否存在，标签有值为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.testWaiter(driver);
        List<WebElement> elements = driver.findElements(By.className("waiter-single"));
        for(int i=1; i<=elements.size(); i++){
            //校验
            Assert.assertNotEquals("", driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div[" +i+ "]/div[2]/div[1]/div[1]/span")).getText());
        }
    }
        
    //用例8：测试惠民服务员信息性别图标存在，图片标签存在为验证方式
    @Test
    public void testCase8() {
        Reporter.log("惠民服务员测试:测试惠民服务员信息性别图标存在，图片标签存在为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.testWaiter(driver);
        List<WebElement> elements = driver.findElements(By.className("waiter-single"));
        for(int i=1; i<=elements.size(); i++){
            //校验
            Assert.assertNotEquals("", driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div["+i+"]/div[2]/div[1]/div[1]/i")).getAttribute("class"));
        }
    }
 
    //用例9：测试惠民服务员服务项信息存在，标签有值为验证方式
    @Test
    public void testCase9() {
        Reporter.log("惠民服务员测试:测试惠民服务员服务项信息存在，标签有值为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.testWaiter(driver);
        List<WebElement> elements = driver.findElements(By.className("waiter-single"));
        for(int i=1; i<=elements.size(); i++){
            //校验
            Assert.assertNotEquals("", driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div[" +i+ "]/div[2]/div[2]/span[1]/span[1]")).getText());
        }
    }
    
    //用例10：测试惠民服务员地址信息存在，地址标签内容存在为验证方式
    @Test
    public void testCase10() {
        Reporter.log("惠民服务员测试:测试惠民服务员地址信息存在，地址标签内容存在为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.testWaiter(driver);
        List<WebElement> elements = driver.findElements(By.className("waiter-single"));
        for(int i=1; i<=elements.size(); i++){
            //校验
            Assert.assertNotEquals("", driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div[" +i+ "]/div[2]/div[2]/span[1]/span[1]")).getText());
        }
    }
    
    //用例11：测试惠民服务员按照距离排序，排序正常为验证方式
    @Test
    public void testCase11() {
        Reporter.log("惠民服务员测试:测试惠民服务员按照距离排序，排序正常为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.testWaiter(driver);
        List<WebElement> elements = driver.findElements(By.className("waiter-single"));
        String distanceOne = driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div[1]/div[2]/div[1]/div[2]")).getText();
        for(int i=2; i<=elements.size(); i++){
            String distanceTwo = driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div["+i+"]/div[2]/div[1]/div[2]")).getText();
            //校验
            Assert.assertTrue(Integer.parseInt(distanceOne.substring(0, distanceOne.length()-1)) <= Integer.parseInt(distanceTwo.substring(0, distanceTwo.length()-1)));
            distanceOne = distanceTwo;
        }
    }
 
    //用例12：测试点击惠民员列表弹出登录，标签是否存在为验证方式
    @Test
    public void testCase12() {
        Reporter.log("惠民服务员测试:测试点击惠民员列表弹出登录，标签是否存在为验证方式录");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.clickWaiter(driver);
        //校验
        Assert.assertEquals(toLogin, driver.findElement(By.className("confirm-yes")).getText());
    }

    //用例13：测试用户登录后可查看"当前默认绑定服务员"，标签是否存在为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase13(String userMobile, String password) {
        Reporter.log("惠民服务员测试:测试用户登录后可查看'当前默认绑定服务员'，标签是否存在为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.checkWaiter(driver, userMobile, password);
        //校验
        Assert.assertNotNull(driver.findElement(By.id("default-waiter")));
    }
 
    //用例14：测试用户登录后选择附近惠民员列表中的任一惠民服务员并确认切换，默认服务员变更，用户名data-id标签相同为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase14(String userMobile, String password) {
        Reporter.log("惠民服务员测试:测试用户登录后选择附近惠民员列表中的任一惠民服务员并确认切换，默认服务员变更，用户名data-id标签相同为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行,校验
        Assert.assertTrue(waiter.switchDefaultWaiter(driver, userMobile, password));
    }
  
    //用例15：测试用户登录后点击申请成为惠民服务员，页面跳转到申请注册填写界面，标签存在为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase15(String userMobile, String password) {
        Reporter.log("惠民服务员测试:测试用户登录后点击申请成为惠民服务员，页面跳转到申请注册填写界面，标签存在为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.applyWaiter(driver, userMobile, password);
        //校验
        Assert.assertNotNull(driver.findElement(By.id("waiter-applyWaiter")));
    }
    
    //用例16：测试用户登录后点击申请成为惠民服务员，页面跳转后可后退至惠民服务员页面，url是否正确为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase16(String userMobile, String password) {
        Reporter.log("惠民服务员测试:测试用户登录后点击申请成为惠民服务员，页面跳转后可后退至惠民服务员页面，url是否正确为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.backWaiter(driver, userMobile, password);
        //校验
        Assert.assertEquals(waiter_url, driver.getCurrentUrl());
    }
   
    //用例17：测试用户未登录时无默认服务员，对应标签是否存在为验证方式
    @Test
    public void testCase17() {
        Reporter.log("惠民服务员测试:测试用户未登录时无默认服务员，对应标签是否存在为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.testWaiter(driver);
        //校验
        Assert.assertTrue(!SeleniumUtil.isElementExist(driver, By.id("default-waiter")));
    }

    //用例18：测试用户未登录时点击附近服务员提示登录，对应标签存在为验证方式
    @Test
    public void testCase18() {
        Reporter.log("惠民服务员测试:测试用户未登录时点击附近服务员提示登录，对应标签存在为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.nearbyWaiter(driver);
        //校验
        Assert.assertNotNull(driver.findElement(By.className("confirm-yes")));
    }
    
    //用例19：测试首页轮播可点击进入惠民服务员页面，url为验证方式
    @Test
    public void testCase19() {
        Reporter.log("惠民服务员测试:测试首页轮播可点击进入惠民服务员页面，url为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.entryWaiterList(driver);
        //校验
        Assert.assertEquals(waiter_url, driver.getCurrentUrl());
    }

    //用例20：测试在未登录情况下，点击我的'个人中心-惠民'进入登录注册页面，url为验证方式
    @Test
    public void testCase20() {
        Reporter.log("惠民服务员测试:测试在未登录情况下，点击我的'个人中心-惠民'进入登录注册页面，url为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.entryMyWaiterWithNotLogin(driver);
        //校验
        Assert.assertEquals(login_url, driver.getCurrentUrl());
    }
   
    //用例21：测试在已登录情况下，点击我的'个人中心-惠民'进入登录页面，url为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase21(String userMobile, String password) {
        Reporter.log("惠民服务员测试:测试在已登录情况下，点击我的'个人中心-惠民'进入惠民服务员页面，url为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.entryMyWaiterWithLogin(driver, userMobile, password);
        //校验
        Assert.assertEquals(person_waiter_url, driver.getCurrentUrl());
    }
    
    //用例22：测试在已登录情况下，点击我的'个人中心-惠民' 进入登录页面再申请惠民服务，url为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase22(String userMobile, String password) {
        Reporter.log("惠民服务员测试:测试在已登录情况下，点击我的'个人中心-惠民'进入惠民服务员页面,点击申请进入惠民服务员申请页面，url为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.entryMyWaiterWithLoginApplayWaiter(driver, userMobile, password);
        //校验
        Assert.assertEquals(apply_url, driver.getCurrentUrl());
    }

    //用例23：测试在已登录情况下，点击我的'个人中心-惠民'进入惠民注册页面后退至惠民服务页，url为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase23(String userMobile, String password) {
        Reporter.log("惠民服务员测试:测试在已登录情况下，点击我的'个人中心-惠民'进入惠民服务员页面,点击进入申请页面后退至惠民服务页面，url为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.entryMyWaiterWithLoginGoBack(driver, userMobile, password);
        //校验
        Assert.assertEquals(person_waiter_url, driver.getCurrentUrl());
    }
    
    //用例24：测试修改定位后，默认的惠民服务员不变，附近的服务员变更，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase24(String userMobile, String password) {
        Reporter.log("惠民服务员测试:测试修改定位后，默认的惠民服务员不变，附近的服务员变更，标签值为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行,校验
        Assert.assertTrue(waiter.updateLoc(driver, userMobile, password));
    }
   
    //用例25：测试修改定位后，测试惠民服务员按照距离排序，排序正常为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase25(String userMobile, String password) {
        Reporter.log("惠民服务员测试:测试修改定位后，默认的惠民服务员不变，附近的服务员变更，排序正常为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        waiter.updateLoc2(driver, userMobile, password);
        List<WebElement> elements = driver.findElements(By.className("waiter-single"));
        String distanceOne = driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div[1]/div[2]/div[1]/div[2]")).getText();
        for(int i=2; i<elements.size(); i++){
            String distanceTwo = driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div["+i+"]/div[2]/div[1]/div[2]")).getText();
            //校验
            Assert.assertTrue(Integer.parseInt(distanceOne.substring(0, distanceOne.length()-1)) <= Integer.parseInt(distanceTwo.substring(0, distanceTwo.length()-1)));
            distanceOne = distanceTwo;
        }
    }   

    //用例26：测试用户登录后选择附近惠民员列表中的任一惠民服务员并确认切换后，此服务员不在附近列表中，用户名data-id标签为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase26(String userMobile, String password) {
        Reporter.log("惠民服务员测试:测试用户登录后选择附近惠民员列表中的任一惠民服务员并确认切换后，此服务员不在附近列表中，用户名data-id标签为验证方式");
        //数据准备
        WaiterList waiter = new WaiterList();
        //执行
        boolean result = waiter.switchDefaultWaiter2(driver, userMobile, password);
        //校验
        Assert.assertTrue(result);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get(host);
        driver.manage().window().setSize(new Dimension(430, 700));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
    }  
    
    @AfterMethod
    public void afterMethod() {
        //关闭打开的浏览器 
        driver.quit();
    } 
}
