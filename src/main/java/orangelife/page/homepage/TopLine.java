package orangelife.page.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import orangelife.dataProvider.PublicParam;
import orangelife.page.ChooseCommunity;
import orangelife.page.Login;
import orangelife.util.SeleniumUtil;

/**
 * 首页头条、商城、和惠民购物、团购操作
 * @author qihuan
 * 
 */
public class TopLine {

    private static Logger log = LoggerFactory.getLogger(TopLine.class);
    private String mallTitle = "商城";
   
    //前置步骤：用户选择小区，点击惠民引导页后进入首页
    public void preStep(WebDriver driver) {
        log.info("前置步骤：用户选择小区，点击惠民后进入首页");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("home-firstLoginLayer")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
    }
    
    //点击进入头条详情
    public void clickTopLine(WebDriver driver){
        log.info("执行进入头条详情操作");
        WebElement topLine = driver.findElement(By.id("hot-newsNav"));
        WebElement activity = topLine.findElement(By.xpath("//*[@id='hot-newsNav']/div/div[2]/div/div/div[2]/a"));
        SeleniumUtil.mouseClick(driver, activity);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("page-newsDetail")));
    }
   
    //进入头条详情页后，点击返回按钮返回首页
    public void clickHeaderBack(WebDriver driver){
        log.info("执行进入头条详情页后，点击返回按钮返回首页操作");
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("detail-header-back")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
    }
    
    //进入首页后查看时商城选项栏
    public boolean checkMall(WebDriver driver){
        log.info("执行进入首页后查看时商城选项栏操作");
        return SeleniumUtil.isElementExist(driver, By.id("mall-list"));
    }
    
    //进入首页后，执行点击商城选项栏中的灰色图标操作
    public boolean clickGrayMall(WebDriver driver){
        log.info("执行点击商城选项栏中的灰色图标操作");
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='mall-list']/div[2]/div/div[3]/a/img")));
        return !SeleniumUtil.isElementExist(driver, By.className("confirm-content"));
    }

    //进入首页后，执行点击商城选项栏中的填色图标【京东】操作
    public boolean clickBrightMallWithOutLogin(WebDriver driver){
        log.info("执行点击商城选项栏中的填色图标【京东】操作");
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='mall-list']/div[2]/div/div[1]/a/img")));
        return SeleniumUtil.isElementExist(driver, By.className("confirm-content"));
    }
    
    //进入首页后，执行进入商城选项栏中的填色图标【京东】提示登录后，点击取消登录返回首页操作
    public void cancelLoginMallWithOutLogin(WebDriver driver){
        log.info("执行进入商城选项栏中的填色图标【京东】提示登录后，点击取消登录返回首页操作");
        clickBrightMallWithOutLogin(driver);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.className("confirm-no")));
    }
    
    //进入首页后，执行进入商城选项栏中的填色图标【京东】提示登录后，点击确认登录后进入登录页面操作
    public void confirmLoginMallWithOutLogin(WebDriver driver){
        log.info("执行进入商城选项栏中的填色图标【京东】提示登录后，点击确认登录后进入登录页面操作");
        clickBrightMallWithOutLogin(driver);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.className("confirm-yes")));
    }
    
    //进入首页后，执行进入商城选项栏中的填色图标【京东】提示登录后，点击确认登录后进入登录页面，输入登录信息登录成功跳转至商城主页操作
    public boolean enterMallWithLogin(WebDriver driver, String userMobile, String password){
        log.info("执行进入商城选项栏中的填色图标【京东】提示登录后，点击确认登录后进入登录页面，输入登录信息登录成功跳转至商城主页操作");
        boolean flag = false;
        confirmLoginMallWithOutLogin(driver);
        driver.findElement(By.id(PublicParam.loginmobileTxtId)).sendKeys(userMobile);
        driver.findElement(By.id(PublicParam.loginpwdTxtId)).sendKeys(password);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id(PublicParam.loginbtnId)));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("iframe-container")));
        if(driver.findElement(By.xpath("//*[@id='iframeHeader']/h2")).getText().contains(mallTitle)){
            flag = true;
        };
        return flag;
    }
    
    //通用：先登录再进入首页,再进入商城页面
    public void enterRegisterWithLogin(WebDriver driver, String userMobile, String password) {
        log.info("先登录再进入首页,再进入商城页面");
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("home-firstLoginLayer")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='mall-list']/div[2]/div/div[1]/a/img")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("iframe-container")));
    } 
    
    //登陆后进入商城检测
    public boolean checkRegisterWithLogin(WebDriver driver, String userMobile, String password) {
        log.info("检测先登录再进入首页,再进入商城页面");
        boolean flag = false;
        enterRegisterWithLogin(driver, userMobile, password);
        if(driver.findElement(By.xpath("//*[@id='iframeHeader']/h2")).getText().contains(mallTitle)){
            flag = true;
        };
        return flag;
    }
    
    //在已登录后进入商城页击左上方房子样式按钮返回商城首页
    public boolean clickHouse(WebDriver driver, String userMobile, String password) {
        log.info("在已登录后进入商城页击左上方房子样式按钮返回商城首页");
        boolean flag = false;
        enterRegisterWithLogin(driver, userMobile, password);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("iframe-home-back")));
        if(driver.findElement(By.xpath("//*[@id='iframeHeader']/h2")).getText().contains(mallTitle)){
            flag = true;
        };
        return flag;
    }
    
    //在已登录后进入商城页击左上方“×”按钮返回商城首页
    public void clickFork(WebDriver driver, String userMobile, String password) {
        log.info("在已登录后进入商城页击左上方“×”按钮返回商城首页");
        enterRegisterWithLogin(driver, userMobile, password);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("iframe-page-back")));
    }
    
    //在首页点击惠民购物部分，跳转至惠民购物主页
    public void clickHuiminShopping(WebDriver driver) {
        log.info("在首页点击惠民购物部分，跳转至惠民购物主页");
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='goTo-mall']/div/div[1]/a/img")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
    }
    
    //在首页进入惠民购物主页后点击返回按钮返回首页
    public void clickBackFromHuiminShopping(WebDriver driver) {
        log.info("在首页点击惠民购物部分，跳转至惠民购物主页");
        clickHuiminShopping(driver);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("header-back")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
    }
    
    //在首页点击惠民团购部分，跳转至惠民团购商品列表页面
    public void clickHuiminGroup(WebDriver driver) {
        log.info("在首页点击惠民团购部分，跳转至惠民团购商品列表页面");
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='goTo-mall']/div/div[2]/a/img")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-groupBuy")));
    }
    
    //从首页进入惠民团购商品列表页面后点击返回按钮返回首页
    public void clickBackFromHuiminGroup(WebDriver driver) {
        log.info("从首页进入惠民团购商品列表页面后点击返回按钮返回首页");
        clickHuiminGroup(driver);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("header-back")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
    }
    
}
