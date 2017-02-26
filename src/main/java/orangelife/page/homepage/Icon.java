package orangelife.page.homepage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import orangelife.page.ChooseCommunity;
import orangelife.page.Login;
import orangelife.util.SeleniumUtil;

/**
 * 首页icon操作
 * @author qihuan
 * 
 */
public class Icon {

    private static Logger log = LoggerFactory.getLogger(Icon.class);
    private String confirmCollection = "menu-icon iconfont icon-collect_solid";
    private String cancelCollection = "menu-icon iconfont icon-collect";
    
    private String health = "//*[@id='home-scrollContainernavPaging']/div[1]/div[2]/a[5]/div[1]/img";
    private String VR = "//*[@id='home-scrollContainernavPaging']/div[1]/div[2]/a[6]/div[1]/img";
    private String point = "//*[@id='home-scrollContainernavPaging']/div[1]/div[2]/a[7]/div[1]/img";
    private String culture = "//*[@id='home-scrollContainernavPaging']/div[1]/div[2]/a[3]/div[1]/img";
    private String register = "//*[@id='home-scrollContainernavPaging']/div[1]/div[3]/a[1]/div[1]/img";
    private String communityService = "//*[@id='home-scrollContainernavPaging']/div[1]/div[2]/a[1]/div[1]/img";

    
    //前置步骤：用户选择小区，点击惠民引导页后进入活动列表首页
    public void preStep(WebDriver driver) {
        log.info("前置步骤：用户选择小区，点击惠民后进入首页");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        driver.findElement(By.xpath("//*[@id='home-scrollContainernavPaging']/div[1]/div[2]/a[2]/div[1]/img")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-publicWelfareActive")));
    }
    
    //用户已登录情况下，进入活动列表页
    public void preStep2(WebDriver driver, String userMobile, String password){
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        driver.findElement(By.xpath("//*[@id='home-scrollContainernavPaging']/div[1]/div[2]/a[2]/div[1]/img")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-publicWelfareActive")));
    }
      
    //随机进入活动详情页
    public void activityDetail(WebDriver driver){
        log.info("执行随机进入活动详情页");
        WebElement activityList = driver.findElement(By.className("page-scroll-container"));
        List<WebElement> onGongIntroList = activityList.findElements(By.className("imgTitleIntro "));
        onGongIntroList.get(1).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-articleDetail")));
    }
    
    //进入活动列表，点击返回正常返回首页
    public void returnHomepage(WebDriver driver){
        log.info("执行进入活动列表，点击返回正常返回首页");
        driver.findElement(By.id("header-back")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
    } 
    
    //判定分享
    public List<WebElement> judgeShare(WebDriver driver){
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.className("popup-share")));
        WebElement shareType = driver.findElement(By.className("share-type"));
        return shareType.findElements(By.tagName("li"));
    }
    
    //点击分享
    public boolean clickShare(WebDriver driver){
        log.info("执行进入活动详情页后点击分享按钮");
        boolean flag = false;
        driver.findElement(By.xpath("//*[@id='page-top-msg-content']/a[3]/i")).click();
        List<WebElement> shareList = judgeShare(driver);
        if(shareList.size() == 4){
            flag = true;
        }
        return flag;
    }
    
/*    //点击字体
    public boolean clickFont(WebDriver driver){
        log.info("执行点击字体");
        boolean flag = false;
        driver.findElement(By.xpath("//*[@id='page-top-msg-content']/a[1]/i")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("change-box")));
        WebElement fontType = driver.findElement(By.xpath("//*[@id='page-articleDetail']/div[3]"));
        int fontSize = Integer.parseInt(fontType.getAttribute("style").substring(11, 13));
        if(fontSize==22){
            driver.findElement(By.xpath("//*[@id='change-box']/div[3]")).click();
            WebElement fontType2 = driver.findElement(By.xpath("//*[@id='page-articleDetail']/div[3]"));
            int fontSize2 = Integer.parseInt(fontType2.getAttribute("style").substring(11, 13));
            if(fontSize>fontSize2){
                flag = true;
            }
        }else{
            driver.findElement(By.xpath("//*[@id='change-box']/div[2]")).click();
            WebElement fontType3 = driver.findElement(By.xpath("//*[@id='page-articleDetail']/div[3]"));
            int fontSize3 = Integer.parseInt(fontType3.getAttribute("style").substring(11, 13));
            if(fontSize<fontSize3){
                flag = true;
            }
        }
        return flag;
    }*/
    
    //用户未登录情况下，点击收藏
    public void clickCollectionWithOutLogin(WebDriver driver){
        log.info("执行进入活动详情页后点击点击收藏按钮");
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement collectionBtn = driver.findElement(By.xpath("//*[@id='page-msg-collect']/i"));
        collectionBtn.click();
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("page-login")));
    }
    
    //用户已登录情况下，点击收藏
    public boolean clickCollectionWithLogin(WebDriver driver){
        log.info("执行进入活动详情页后点击点击收藏按钮");
        boolean flag = false;
        WebElement collectionBtn = driver.findElement(By.xpath("//*[@id='page-msg-collect']/i"));
        SeleniumUtil.mouseClick(driver, collectionBtn);
        if(cancelCollection.equals(collectionBtn.getAttribute("class"))){
            SeleniumUtil.mouseClick(driver, collectionBtn);
        }
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(confirmCollection.equals(collectionBtn.getAttribute("class"))){
            flag = true;
        }
        return flag;
    }  
    
    //用户已登录情况下，取消收藏
    public boolean cancelCollection(WebDriver driver){
        log.info("执行进入活动详情页后点击点击按钮，取消收藏");
        boolean flag = false;
        WebElement collectionBtn = driver.findElement(By.xpath("//*[@id='page-msg-collect']/i"));
        SeleniumUtil.mouseClick(driver, collectionBtn);
        if(confirmCollection.equals(collectionBtn.getAttribute("class"))){
            SeleniumUtil.mouseClick(driver, collectionBtn);
        }
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(cancelCollection.equals(collectionBtn.getAttribute("class"))){
            flag = true;
        }
        return flag;
    } 
    
    /**
     * 执行公共文化相关动作
     * @param driver
     */
    //用户选择小区，点击惠民引导页后进入文化首页
    public void enterCulture(WebDriver driver) {
        log.info("用户选择小区，点击惠民后进入文化首页");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        driver.findElement(By.xpath(culture)).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("iframe-iframeThis")));
    } 
    
    //点击关闭按钮返回到首页
    public void retrunCulture(WebDriver driver) {
        log.info("执行点击关闭按钮返回到首页操作");
        driver.findElement(By.id("iframe-page-back")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
    }
    
    /**
     * 执行挂号相关动作
     * @param driver
     */
    //用户选择小区，点击惠民引导页后进入挂号首页
    public void enterRegister(WebDriver driver) {
        log.info("用户选择小区，点击惠民后进入挂号首页");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath(register)));
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.className("confirm-yes")));
        driver.findElement(By.className("confirm-yes")).click();
        new WebDriverWait(driver,50).until(ExpectedConditions.presenceOfElementLocated(By.id("page-login")));
    }
    
    //先登录，再进入挂号页面
    public void enterRegisterWithLogin(WebDriver driver, String userMobile, String password, String idCard) {
        log.info("先登录，再进入挂号页面");
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath(register)));
        if(SeleniumUtil.isElementExist(driver, By.className("model-confirm"))){
            driver.findElement(By.className("confirm-yes")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("register-idCard")));
            driver.findElement(By.id("register-idCard")).sendKeys(idCard);
            driver.findElement(By.id("register-btn")).click();
        }
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.className("iframe-body")));
    } 
    
    
    /**
     * 执行健康养生相关动作
     * @param driver
     */
    //用户选择小区，点击惠民引导页后进入健康养生首页
    public void enterHealth(WebDriver driver) {
        log.info("执行用户选择小区，点击惠民引导页后进入健康养生首页操作");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath(health)));
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-healthy-home")));
    }   
    
    //进入健康的详情页
    public void enterHealthDetail(WebDriver driver) {
        log.info("执行进入健康的详情页操作");
        driver.findElement(By.xpath("//*[@id='hot-newsNav']/div/div[2]")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-healthy-recommend")));
        driver.findElement(By.xpath("//*[@id='recommend-list']/div[1]")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-healthyDetail")));
    } 
    
    //从进入健康的详情页返回到上一页
    public void returnFromHealthDetail(WebDriver driver) {
        log.info("执行进入健康的详情页操作");
        driver.findElement(By.id("header-back")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-healthy-recommend")));
    }    
    
    //进入健康的详情页后，点击收藏
    public boolean enterCollection(WebDriver driver) {
        log.info("执行进入健康的详情页后，点击收藏操作");
        boolean flag = false;
        if(!SeleniumUtil.isElementExist(driver, By.id("page-msg-collect"))){
            driver.findElement(By.id("page-msg-collect")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.className("confirm-yes")));
            driver.findElement(By.className("confirm-yes")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.className("page-login")));
        }
        return flag;
    }   
    
    //先登录，再进入健康页面
    public void enterCollectionWithLogin(WebDriver driver, String userMobile, String password) {
        log.info("先登录，再进入健康页面");
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath(health)));
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-healthy-home")));
    } 
    
    //进入健康的详情页后，点击分享
    public boolean enterShare(WebDriver driver) {
        log.info("执行进入健康的详情页后，点击分享操作");
        boolean flag = false;
        driver.findElement(By.xpath("//*[@id='page-top-msg-content']/a[2]/i")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.className("popup-share")));
        WebElement shareType = driver.findElement(By.className("popup-share"));
        List<WebElement> shareList = shareType.findElements(By.className("share-box"));
        if(shareList.size()==4){
            flag = true;
        }
        return flag;
    } 
    
    
    /**
     * 执行VR全景相关动作
     * @param driver
     */
    //用户选择小区，点击惠民引导页后进入VR首页
    public void enterVR(WebDriver driver) {
        log.info("执行用户选择小区，点击惠民引导页后进入VR首页操作");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath(VR)));
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("iframe-iframeThis")));
    }
    
    //在【VR全景】页面点击后退键
    public void returnFromVR(WebDriver driver) {
        log.info("执行在【VR全景】页面点击后退键操作");
        driver.findElement(By.id("iframe-page-back")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
    }    
    
    /**
     * 执行网点相关动作
     * @param driver
     */
    //用户选择小区，点击惠民引导页后进入网点首页
    public void enterPoint(WebDriver driver) {
        log.info("执行用户选择小区，点击惠民引导页后进入网点首页操作");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath(point)));
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-NetPoint")));
    }
    
    //在网点展示页面点击后退键
    public void returnFromPoint(WebDriver driver) {
        log.info("执行在网点展示页面点击后退键操作");
        driver.findElement(By.id("header-back")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
    }
    
    //在网点详情页点击按字母排序
    public boolean newPointSort(WebDriver driver) {
        log.info("执行在网点详情页点击按字母排序操作");
        boolean flag = false;
        WebElement netPoint = driver.findElement(By.id("netPoint-change"));
        if("按字母".equals(netPoint.getText())){
            netPoint.click();
            WebElement sort = driver.findElement(By.id("letterContainer"));
            if("display: block;".equals(sort.getAttribute("style"))){
                flag = true;
            }
        }
        return flag;
    }
    
    /**
     * 执行社区服务动作
     * @param driver
     */
    //用户选择小区，点击惠民引导页后社区服务首页
    public void enterCommunityService(WebDriver driver) {
        log.info("执行用户选择小区，点击惠民引导页后社区服务首页操作");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath(communityService)));
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-affairsGuideMore")));
    }
    
    //在社区服务分类中点击一个进入事项列表页
    public void enterCommunityServiceList(WebDriver driver) {
        log.info("执行在社区服务分类中点击一个进入事项列表页操作");
        driver.findElement(By.xpath("//*[@id='page-affairsGuideMore-content']/a[1]")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-affairsList")));
    }
   
    //在社区服务列表页中点击一个进入详情页
    public void enterCommunityServiceDetail(WebDriver driver) {
        log.info("执行在社区服务列表页中点击一个进入详情页操作");
        WebElement affairsType = driver.findElement(By.xpath("//*[@id='page-affairsList']/div[2]/div"));
        List<WebElement> affairsList = affairsType.findElements(By.tagName("a"));
        for(int i=0;i<=affairsList.size();i++){
            if(affairsList.get(i).getAttribute("href").contains("12036")){
                affairsList.get(i).click();
                break;
            }
        }
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-affairsDetail")));
    }
    
    //在事项详情页点击机构
    public void enterCommunityServiceDetailInstitutions(WebDriver driver) {
        log.info("执行在事项详情页点击机构操作");
        driver.findElement(By.xpath("//*[@id='page-top-msg-content']/div/a")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("agency-list")));
    }    
    
    //在事项详情页中切换区域
    public boolean changeArea(WebDriver driver) {
        log.info("执行在事项详情页中切换区域操作");
        boolean flag = false;
        WebElement areaNav = driver.findElement(By.xpath("//*[@id='page-affairsDetail']/div[2]/ul"));
        List<WebElement> areaList = areaNav.findElements(By.tagName("li"));
        if("active".equals(areaList.get(0).getAttribute("class"))){
            areaList.get(1).click();
            if("active".equals(areaList.get(1).getAttribute("class"))){
                flag = true;
            }
        }
        return flag;
    } 
    
    //在生育收养详情页点击后退按钮退回至上一层
    public void returnFromCommunityServiceDetail(WebDriver driver) {
        log.info("执行在生育收养详情页点击后退按钮退回至上一层操作");
        driver.findElement(By.id("header-back")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-affairsList")));
    } 
   
}
