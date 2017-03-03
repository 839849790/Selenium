package orangelife.ut.news;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangelife.page.news.News;

/**
 * 新闻页面的测试
 * @author qihuan
 * 
 */
public class NewsTest {

    private static String host = "http://m.orangelife.com.cn/";    //定义驱动网址
    private static WebDriver driver;
        
    private String home_url = host + "#home";
    private String newsDetail_url = host + "#newsDetail";
    private String login_url = host + "#login";
    private String hot = "热点";
    private String voice = "语音";
   
    //用例2：从APP首页点击下方新闻选项卡切换至新闻界面后，点击左上方返回按钮回到首页，标签值为验证方式
    @Test(priority=2)
    public void testCase2() {
        Reporter.log("新闻页测试:从APP首页点击下方新闻选项卡切换至新闻界面后，点击左上方返回按钮回到首页，标签值为验证方式");
        News news = new News();
        news.preStep(driver);
        news.clickFromNews(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(home_url));
    }
 
    //用例3：进入新闻页后查看热点新闻，列表内容显示正常，列表下拉加载正常，标签值为验证方式
    @Test(priority=3)
    public void testCase3() {
        Reporter.log("新闻页测试:进入新闻页后查看热点新闻，列表内容显示正常，列表下拉加载正常，标签值为验证方式");
        News news = new News();
        news.preStep(driver);
        Assert.assertTrue(news.checkNewsTab(driver, hot));
    }
    
    //用例4：点击热点新闻跳转至相应热点新闻详情且详情显示正常，标签值为验证方式
    @Test(priority=4)
    public void testCase4() {
        Reporter.log("新闻页测试:点击热点新闻跳转至相应热点新闻详情且详情显示正常，标签值为验证方式");
        News news = new News();
        news.preStep(driver);
        news.chooseTab(driver, hot);
        news.enterNewsDetail(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(newsDetail_url));
    } 
    
    //用例7：在未登录情况下，在热点新闻详情页点击收藏按钮提示登录，标签值为验证方式
    @Test(priority=7)
    public void testCase7() {
        Reporter.log("新闻页测试:在未登录情况下，在热点新闻详情页点击收藏按钮提示登录，标签值为验证方式");
        News news = new News();
        news.preStep(driver);
        news.chooseTab(driver, hot);
        news.enterNewsDetail(driver);
        news.clickCollectionNews(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(login_url));
    }
    
    //用例8：在未登录情况下，在热点新闻详情页点击收藏按钮提示登录,登录完成返回详情页，标签值为验证方式
    @Test(priority=8,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase8(String userMobile, String password) {
        Reporter.log("新闻页测试:在未登录情况下，在热点新闻详情页点击收藏按钮提示登录,登录完成返回详情页，标签值为验证方式");
        News news = new News();
        news.preStep(driver);
        news.chooseTab(driver, hot);
        news.enterNewsDetail(driver);
        news.clickCollectionNews(driver);
        news.toLoginCollectionNews(driver, userMobile, password);
        Assert.assertTrue(driver.getCurrentUrl().contains(newsDetail_url));
    }
   
    //用例9：在未登录情况下，在热点新闻详情页点击收藏按钮提示登录,登录完成返回详情页,之后再次点击收藏按钮提示收藏成功，标签值为验证方式
    @Test(priority=9,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase9(String userMobile, String password) {
        Reporter.log("新闻页测试:在未登录情况下，在热点新闻详情页点击收藏按钮提示登录,登录完成返回详情页,之后再次点击收藏按钮提示收藏成功，标签值为验证方式");
        News news = new News();
        news.preStep(driver);
        news.chooseTab(driver, hot);
        news.enterNewsDetail(driver);
        news.clickCollectionNews(driver);
        news.toLoginCollectionNews(driver, userMobile, password);
        Assert.assertTrue(news.collectionNewsWithLogin(driver));
    }

    //用例10：在已登录、未收藏情况下，打开详情页，点击右上角收藏按钮提示收藏成功，标签值为验证方式
    @Test(priority=11,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase10(String userMobile, String password) {
        Reporter.log("新闻页测试:在已登录、未收藏情况下，打开详情页，点击右上角收藏按钮提示收藏成功，标签值为验证方式");
        News news = new News();
        news.enterRegisterWithLogin(driver, userMobile, password);
        news.chooseTab(driver, hot);
        news.enterNewsDetail(driver);
        Assert.assertTrue(news.collectionNewsWithLogin(driver));
    }
    
    //用例11：在已登录、未收藏情况下，打开详情页，点击右上角收藏按钮提示收藏成功后，再点击按钮提示“取消收藏成功”，标签值为验证方式
    @Test(priority=10,dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase11(String userMobile, String password) {
        Reporter.log("新闻页测试:在已登录、未收藏情况下，打开详情页，点击右上角收藏按钮提示收藏成功后，再点击按钮提示“取消收藏成功”，标签值为验证方式");
        News news = new News();
        news.enterRegisterWithLogin(driver, userMobile, password);
        news.chooseTab(driver, hot);
        news.enterNewsDetail(driver);
        Assert.assertTrue(news.cancelCollectionNews(driver));
    } 
    
    //用例14：进入语音新闻页后查看语音新闻详情，查看新闻详情显示正常，页面下方有浮动的音频播放标志，标签值为验证方式
    @Test(priority=14)
    public void testCase14() {
        Reporter.log("新闻页测试:进入语音新闻页后查看语音新闻详情，查看新闻详情显示正常，页面下方有浮动的音频播放标志，标签值为验证方式");
        News news = new News();
        news.preStep(driver);
        news.chooseTab(driver, voice);
        news.enterVoiceDetail(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains(newsDetail_url));
    }

    //用例15：点击语音播放按钮,播放音频，播放按钮状态改变，标签值为验证方式
    @Test(priority=15)
    public void testCase15() {
        Reporter.log("新闻页测试:点击语音播放按钮,播放音频，播放按钮状态改变，标签值为验证方式");
        News news = new News();
        news.preStep(driver);
        news.chooseTab(driver, voice);
        news.enterVoiceDetail(driver);
        Assert.assertTrue(news.clickVoicePlay(driver));
    } 
    
    //用例16：在音频播放时点击语音播放按钮,停止播放，播放按钮状态改变，标签值为验证方式
    @Test(priority=16)
    public void testCase16() {
        Reporter.log("新闻页测试:在音频播放时点击语音播放按钮,停止播放，播放按钮状态改变，标签值为验证方式");
        News news = new News();
        news.preStep(driver);
        news.chooseTab(driver, voice);
        news.enterVoiceDetail(driver);
        Assert.assertTrue(news.clickVoicePlayStop(driver));
    } 
    
    //用例17：在音频播放时后退，在进入停止播放，标签值为验证方式
    @Test(priority=17)
    public void testCase17() {
        Reporter.log("新闻页测试:在音频播放时后退，在进入停止播放，标签值为验证方式");
        News news = new News();
        news.preStep(driver);
        news.chooseTab(driver, voice);
        news.enterVoiceDetail(driver);
        Assert.assertTrue(news.backToVoiceDetail(driver));
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
