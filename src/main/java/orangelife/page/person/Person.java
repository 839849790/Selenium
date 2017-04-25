package orangelife.page.person;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import orangelife.util.SeleniumUtil;

/**
 * 个人页相关内容操作
 * @author qihuan
 * 
 */
public class Person {
    
    private static Logger log = LoggerFactory.getLogger(Person.class);
    
    //前置步骤1：用户选择小区，点击惠民引导页后进入首页,在进入“我的”页面
    public void preStep(WebDriver driver) {
        log.info("前置步骤1：用户选择小区，点击惠民后进入首页");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("home-firstLoginLayer")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='pageFooter']/ul/li[4]/a")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("page-User")));
    }
    
    //前置步骤2：执行先登录、而后进入“我的”页面操作
    public void preStepWithLogin(WebDriver driver, String userMobile, String password) {
        log.info("前置步骤2：执行先登录、而后进入“我的”页面操作");
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("home-firstLoginLayer")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='pageFooter']/ul/li[4]/a")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("page-User")));
    }
    
    //通用步骤：执行点击“设置”按钮操作
    public void clickSet(WebDriver driver) {
        log.info("通用步骤：执行点击“设置”按钮操作");
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("userSetting")));
    }
    
    //判断未登录情况下的点击“设置”按钮操作
    public boolean checkSetWithoutLogin(WebDriver driver) {
        log.info("判断未登录情况下的点击“设置”按钮操作");
        clickSet(driver);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.className("layermcont")));
        return SeleniumUtil.isElementExist(driver, By.className("model-confirm"));
    }
        
    //判断已登陆后的点击“设置”按钮操作
    public boolean checkSetWithLogin(WebDriver driver) {
        log.info("判断已登陆后的点击“设置”按钮操作");
        clickSet(driver);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("pageBodyContainer")));
        return SeleniumUtil.isElementExist(driver, By.id("page-setting"));
    }
    
    //执行点击“消息到达提醒”按钮操作
    public boolean clickMsgSet(WebDriver driver) {
        log.info("执行点击“消息到达提醒”按钮操作");
        boolean flag = false; 
        WebElement msgSwitch = driver.findElement(By.id("msg-switch"));
        String className = msgSwitch.getAttribute("class");
        SeleniumUtil.mouseClick(driver, msgSwitch);
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!className.equals(msgSwitch.getAttribute("class"))){
            flag = true;
        }
        return flag;
    }
    
    //执行点击“正文字号”按钮操作
    public boolean clickFontSet(WebDriver driver) {
        log.info("执行点击“正文字号”按钮操作");
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("changeFont")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("pageBodyContainer")));
        return SeleniumUtil.isElementExist(driver, By.id("page-changeFont"));
    }
    
    //执行点击“意见反馈”按钮操作
    public boolean clickFeedbackSet(WebDriver driver) {
        log.info("执行点击“意见反馈”按钮操作");
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("feedback")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.className("feedback-group")));
        return SeleniumUtil.isElementExist(driver, By.id("feedback-send"));
    }
    
    //通用步骤：执行点击头像操作
    public void clickHeadImg(WebDriver driver) {
        log.info("通用步骤：执行点击头像操作");
        SeleniumUtil.mouseClick(driver, driver.findElement(By.className("head-img")));
    }
    
    //判断未登录情况下的点击头像操作
    public boolean checkHeadImgWithoutLogin(WebDriver driver) {
        log.info("判断未登录情况下的点击头像操作");
        clickHeadImg(driver);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.className("layermcont")));
        return SeleniumUtil.isElementExist(driver, By.className("model-confirm"));
    }
    
    //判断已登录情况下的点击头像操作
    public boolean checkHeadImgWithLogin(WebDriver driver) {
        log.info("判断已登录情况下的点击头像操作");
        clickHeadImg(driver);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("pageBodyContainer")));
        return SeleniumUtil.isElementExist(driver, By.id("page-UserInfo"));
    }
    
    //执行点击头像登录操作
    public void clickHeadImgToLogin(WebDriver driver, String userMobile, String password) {
        log.info("执行点击头像登录操作");
        Login login = new Login();
        clickHeadImg(driver);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.className("layermcont")));
        login.testLogin(driver, userMobile, password);
    }
    
    //执行判断已登录情况下的点击头像,而后点击名称操作
    public boolean changeHeadImg(WebDriver driver) {
        log.info("执行判断已登录情况下的点击头像,而后点击名称操作");
        clickHeadImg(driver);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("pageBodyContainer")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='nickName']/div")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.className("layermcont")));
        return SeleniumUtil.isElementExist(driver, By.id("page-UserInfo"));
    }
    
    //执行判断已登录情况下的点击头像,而后点击修改密码操作
    public boolean changePsd(WebDriver driver) {
        log.info("执行判断已登录情况下的点击头像,而后点击修改密码操作");
        clickHeadImg(driver);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("pageBodyContainer")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='page-UserInfo']/div[2]/div[2]/a/div")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("page-resetPwd")));
        return SeleniumUtil.isElementExist(driver, By.id("resetPwd-btn"));
    }
    
    
    //通用方法：在我的页面查找对应的栏目
    public void clickMenu(WebDriver driver, String menuName){
        for(int i=1;i<=5;i++){
            String path = "//*[@id='page-User']/div/div[1]/a["+i+"]/div/div[1]/span";
            WebElement menu = driver.findElement(By.xpath(path));
            if(menu.getText().equals(menuName)){
                SeleniumUtil.mouseClick(driver, menu);
                break;
            }
        }
    }
    
    //执行判断已登录情况下的点击我的小区操作
    public boolean myCommunity(WebDriver driver, String menuName) {
        log.info("执行判断已登录情况下的点击我的小区操作");
        clickMenu(driver, menuName);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("page-mineStreet")));
        return SeleniumUtil.isElementExist(driver, By.id("myAddress"));
    }
    
    //执行判断已登录情况下的点击我的收藏操作
    public boolean myCollection(WebDriver driver, String menuName) {
        log.info("执行判断已登录情况下的点击我的收藏操作");
        clickMenu(driver, menuName);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("page-affairs-collect")));
        return SeleniumUtil.isElementExist(driver, By.id("tabNavs"));
    }
    
    //执行判断已登录情况下的点击我的购物操作
    public boolean myShopping(WebDriver driver, String menuName) {
        log.info("执行判断已登录情况下的点击我的购物操作");
        clickMenu(driver, menuName);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("pageBodyContainer")));
        return SeleniumUtil.isElementExist(driver, By.id("mall-usersShopping"));
    }
    
    //通用方法：Menu2在我的页面查找对应的栏目
    public void clickMenu2(WebDriver driver, String menuName){
        for(int i=1;i<=1;i++){
            String path = "//*[@id='page-User']/div/div[2]/a["+i+"]/div/div[1]/span";
            WebElement menu = driver.findElement(By.xpath(path));
            if(menu.getText().equals(menuName)){
                SeleniumUtil.mouseClick(driver, menu);
                break;
            }
        }
    }
    
    //执行判断已登录情况下的点击我的购物操作
    public boolean myHuimin(WebDriver driver, String menuName) {
        log.info("执行判断已登录情况下的点击我的购物操作");
        clickMenu2(driver, menuName);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("pageBodyContainer")));
        return SeleniumUtil.isElementExist(driver, By.id("waiterIntro"));
    }
    
    
    //通用方法：Menu3在我的页面查找对应的栏目
    public void clickMenu3(WebDriver driver, String menuName){
        for(int i=1;i<=2;i++){
            String path = "//*[@id='page-User']/div/div[3]/a["+i+"]/div/div[1]/span";
            WebElement menu = driver.findElement(By.xpath(path));
            if(menu.getText().equals(menuName)){
                SeleniumUtil.mouseClick(driver, menu);
                break;
            }
        }
    }
    
    //执行判断已登录情况下的点击消息中心操作
    public boolean myMsg(WebDriver driver, String menuName) {
        log.info("执行判断已登录情况下的点击消息中心操作");
        clickMenu3(driver, menuName);
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("pageBodyContainer")));
        return SeleniumUtil.isElementExist(driver, By.id("page-MessageCenter"));
    }
    
}
