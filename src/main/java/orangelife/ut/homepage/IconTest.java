package orangelife.ut.homepage;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangelife.page.homepage.Icon;

/**
 * 首页icon测试
 * @author qihuan
 * 
 */
public class IconTest {

    private static String host = "http://m.orangelife.com.cn/";    //定义驱动网址
    private static WebDriver driver;
    
    private static String activityDetail = host + "#articleDetail";    //活动详情页
    private String home_url = host + "#home";
    private String login_url = host + "#login/_DL__DL__DL__DL__DL_";
    private String register_url = host + "#iframe/http%3A%2F%2Fwww.91985.com";
    private String health_url = host + "#healthy";

    //用例2：点击一条活动信息，正常进入详情页查看活动内容，标签值为验证方式
    @Test
    public void testCase2() {
        Reporter.log("首页icon测试:验证社区活动是否正常展示内容，标签值为验证方式");
        Icon icon = new Icon();
        icon.preStep(driver);
        icon.activityDetail(driver);
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains(activityDetail));
    }
    
    //用例3：进入活动列表，点击返回正常返回首页，标签值为验证方式
    @Test
    public void testCase3() {
        Reporter.log("首页icon测试:进入活动列表，点击返回正常返回首页，标签值为验证方式");
        Icon icon = new Icon();
        icon.preStep(driver);
        icon.returnHomepage(driver);
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }   
    
    //用例4：点击一条活动查看详情后，点击右上角分享，弹出分享图标，标签值为验证方式
    @Test
    public void testCase4() {
        Reporter.log("首页icon测试:点击任一一条活动查看详情后，点击右上角分享，弹出分享图标，标签值为验证方式");
        Icon icon = new Icon();
        icon.preStep(driver);
        icon.activityDetail(driver);
        Assert.assertTrue(icon.clickShare(driver));
    }
   
    //用例6：用户未登录情况下，点击一条活动查看详情后，点击右上角字体收藏按钮进入登录页，标签值为验证方式
    @Test
    public void testCase6() {
        Reporter.log("首页icon测试:用户未登录情况下，点击一条活动查看详情后，点击右上角收藏按钮进入登录页，标签值为验证方式");
        Icon icon = new Icon();
        icon.preStep(driver);
        icon.activityDetail(driver);
        icon.clickCollectionWithOutLogin(driver);
        Assert.assertEquals(login_url, driver.getCurrentUrl());
    }   

    //用例11：点击公共文化可正常进入公共文化页面，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase11(String userMobile, String password) {
        Reporter.log("首页icon测试:点击公共文化可正常进入公共文化页面，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterCulture(driver);
        Assert.assertEquals("公共文化", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2/span")).getText());
    }

    //用例12：在公共文化页面点击关闭按钮退回前一页，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase12(String userMobile, String password) {
        Reporter.log("首页icon测试:在公共文化页面点击关闭按钮退回前一页，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterCulture(driver);
        icon.retrunCulture(driver);
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }
   
    //用例13：在用户未登录情况下，点击挂号，弹出提出登录框，要求用户登录，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase13(String userMobile, String password) {
        Reporter.log("首页icon测试:在用户未登录情况下，点击挂号，弹出提出登录框，要求用户登录，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterRegister(driver);
        System.out.println(driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
        Assert.assertEquals("登录", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    }    
        
    //用例14：在用户已登录情况下，点击挂号，正常展示可提供预约挂号的医院列表，标签值为验证方式
    @Test(dataProvider="loginData5",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase14(String userMobile, String password, String idCard) {
        Reporter.log("首页icon测试:在用户未登录情况下，点击挂号，弹出提出登录框，要求用户登录，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterRegisterWithLogin(driver, userMobile, password, idCard);
        Assert.assertTrue(driver.getCurrentUrl().contains(register_url));
    }    
        
    //用例15：点击健康，正常进入健康页面，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase15(String userMobile, String password) {
        Reporter.log("首页icon测试:点击健康，正常进入健康页面，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterHealth(driver);
        Assert.assertEquals(health_url, driver.getCurrentUrl());
    }
        
    //用例16：进入健康页后，点击一条“健康养生”信息，正常进入详情页，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase16(String userMobile, String password) {
        Reporter.log("首页icon测试:进入健康页后，点击一条“健康养生”信息，正常进入详情页，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterHealth(driver);
        icon.enterHealthDetail(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("#healthyDetail"));
    }
    
    //用例17：进入健康详情页后，点击后退按钮，正常返回上一页，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase17(String userMobile, String password) {
        Reporter.log("首页icon测试:进入健康详情页后，点击后退按钮，正常返回上一页，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterHealth(driver);
        icon.enterHealthDetail(driver);
        icon.returnFromHealthDetail(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("#healthyRecommend"));
    }    
            
    //用例18：在未登录情况下，在健康养生详情页点击右上角收藏按钮，弹出提示，请用户先登录，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase18(String userMobile, String password) {
        Reporter.log("首页icon测试:在未登录情况下，在健康养生详情页点击右上角收藏按钮，弹出提示，请用户先登录，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterHealth(driver);
        icon.enterHealthDetail(driver);
        Assert.assertTrue(icon.enterCollection(driver));
    } 
 
    //用例19：在已登录情况下，在健康养生详情页点击右上角收藏按钮，收藏成功，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase19(String userMobile, String password) {
        Reporter.log("首页icon测试:在已登录情况下，在健康养生详情页点击右上角收藏按钮，收藏成功，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterCollectionWithLogin(driver, userMobile, password);
        icon.enterHealthDetail(driver);
        Assert.assertTrue(icon.enterCollection(driver));
    }    
   
    //用例20：在健康养生详情页点击右上角分享按钮，从下方弹出各社交网站图标，点击后可前往分享，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase20(String userMobile, String password) {
        Reporter.log("首页icon测试:在健康养生详情页点击右上角分享按钮，从下方弹出各社交网站图标，点击后可前往分享，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterHealth(driver);
        icon.enterHealthDetail(driver);
        Assert.assertTrue(icon.enterShare(driver));
    }
        
    //用例24：首页点击【VR全景】,正常进入全景展示页面，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase24(String userMobile, String password) {
        Reporter.log("首页icon测试:首页点击【VR全景】,正常进入全景展示页面，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterVR(driver);
        Assert.assertEquals("VR全景", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2/span")).getText());
    }    
        
    //用例25：在【VR全景】页面点击后退键,正常返回首页，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase25(String userMobile, String password) {
        Reporter.log("首页icon测试:在【VR全景】页面点击后退键,正常返回首页，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterVR(driver);
        icon.returnFromVR(driver);
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }  
       
    //用例26：点击首页【网点】,正常进入网点展示页面，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase26(String userMobile, String password) {
        Reporter.log("首页icon测试:点击首页【网点】,正常进入网点展示页面，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterPoint(driver);
        Assert.assertEquals("网点", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    }   

    //用例27：在网点展示页面点击后退键，正常返回首页，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase27(String userMobile, String password) {
        Reporter.log("首页icon测试:在网点展示页面点击后退键，正常返回首页，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterPoint(driver);
        icon.returnFromPoint(driver);
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }    
    
    //用例28：在网点详情页点击按字母排序，网点则重新排序展示，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase28(String userMobile, String password) {
        Reporter.log("首页icon测试:在网点详情页点击按字母排序，网点则重新排序展示，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterPoint(driver);
        Assert.assertTrue(icon.newPointSort(driver));
    } 

    //用例29：当前处在智橙生活首页点击【社区服务】,正常显示社区服务列表项，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase29(String userMobile, String password) {
        Reporter.log("首页icon测试:当前处在智橙生活首页点击【社区服务】,正常显示社区服务列表项，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterCommunityService(driver);
        Assert.assertEquals("社区服务", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    }
    
    //用例30：在社区服务列表页中点击任一事项分类（如生育收养）,正常进入对应事项列表页，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase30(String userMobile, String password) {
        Reporter.log("首页icon测试:在社区服务列表页中点击任一事项分类（如生育收养）,正常进入对应事项列表页，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterCommunityService(driver);
        icon.enterCommunityServiceList(driver);
        Assert.assertEquals("生育收养", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    }    
    
    //用例31：在事项列表页中点击列表中的事项（如申领独生子女父母奖励）,能正常进入事项详情页面，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase31(String userMobile, String password) {
        Reporter.log("首页icon测试:在事项列表页中点击列表中的事项（如申领独生子女父母奖励）,能正常进入事项详情页面，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterCommunityService(driver);
        icon.enterCommunityServiceList(driver);
        icon.enterCommunityServiceDetail(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("#affairsDetail"));
    }
    
    //用例32：在事项详情页点击机构，正常显示服务机构内容，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase32(String userMobile, String password) {
        Reporter.log("首页icon测试:在事项详情页点击机构，正常显示服务机构内容，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterCommunityService(driver);
        icon.enterCommunityServiceList(driver);
        icon.enterCommunityServiceDetail(driver);
        icon.enterCommunityServiceDetailInstitutions(driver);
        Assert.assertEquals("服务机构", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    }    
    
    //用例33：在事项详情页点击区域，正常切换至新选择区域，显示内容做相应的匹配，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase33(String userMobile, String password) {
        Reporter.log("首页icon测试:在事项详情页点击区域，正常切换至新选择区域，显示内容做相应的匹配，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterCommunityService(driver);
        icon.enterCommunityServiceList(driver);
        icon.enterCommunityServiceDetail(driver);
        Assert.assertTrue(icon.changeArea(driver));
    }
    
    //用例34：在生育收养详情页点击后退按钮,正常退回至上一层，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase34(String userMobile, String password) {
        Reporter.log("首页icon测试:在生育收养详情页点击后退按钮,正常退回至上一层，标签值为验证方式");
        Icon icon = new Icon();
        icon.enterCommunityService(driver);
        icon.enterCommunityServiceList(driver);
        icon.enterCommunityServiceDetail(driver);
        icon.returnFromCommunityServiceDetail(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("#affairsList"));
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
