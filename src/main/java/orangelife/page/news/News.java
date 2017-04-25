package orangelife.page.news;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import orangelife.dataProvider.PublicParam;
import orangelife.page.person.ChooseCommunity;
import orangelife.page.person.Login;
import orangelife.util.SeleniumUtil;

/**
 * 新闻页面的操作
 * @author qihuan
 * 
 */
public class News {

    private static Logger log = LoggerFactory.getLogger(News.class);
   
    //前置步骤：用户选择小区，点击惠民引导页后进入首页
    public void preStep(WebDriver driver) {
        log.info("前置步骤：用户选择小区，点击惠民后进入首页");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("home-firstLoginLayer")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='pageFooter']/ul/li[2]/a")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("page-newsList")));
    }
    
    //通用方法：根据传入的参数选择点击哪个选项卡
    public WebElement chooseTab(WebDriver driver, String tab){
        log.info("通用方法：根据传入的参数选择点击哪个选项卡");
        int i;
        WebElement options = driver.findElement(By.className("swiper-wrapper"));
        List<WebElement> optionList = options.findElements(By.tagName("div"));
        for(i=0;i<optionList.size();i++){
            if(tab.equals(optionList.get(i).getText())){
                SeleniumUtil.mouseClick(driver, optionList.get(i));
                break;
            }
        }
        return optionList.get(i);
    }
    
    //执行点击左上方返回按钮回到首页操作
    public void clickFromNews(WebDriver driver) {
        log.info("执行点击左上方返回按钮回到首页操作");
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("header-back")));
    }
    
    
    //执行判断点击选项卡后显示正常操作
    public boolean checkNewsTab(WebDriver driver, String tab) {
        log.info("执行判断点击选项卡后显示正常操作");
        boolean flag = false;
        WebElement option = chooseTab(driver, tab);
        if(option.getAttribute("class").contains("nav-active")){
            flag = true;
        };
        return flag;
    }
    
    //执行点击热点新闻跳转至相应热点新闻详情操作
    public void enterNewsDetail(WebDriver driver) {
        log.info("执行点击热点新闻跳转至相应热点新闻详情操作");
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='channel-3-sContainer']/li[1]")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("page-newsDetail")));
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    //执行在未登录情况下，在热点新闻详情页点击收藏按钮操作
    public void clickCollectionNews(WebDriver driver) {
        log.info("执行在未登录情况下，在热点新闻详情页点击收藏按钮操作");
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("page-msg-collect")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("page-login")));
    }
    
    //执行在未登录情况下，在热点新闻详情页点击收藏按钮后完成登陆操作
    public void toLoginCollectionNews(WebDriver driver, String userMobile, String password) {
        log.info("执行在未登录情况下，在热点新闻详情页点击收藏按钮后完成登陆操作");
        driver.findElement(By.id(PublicParam.loginmobileTxtId)).sendKeys(userMobile);
        driver.findElement(By.id(PublicParam.loginpwdTxtId)).sendKeys(password);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id(PublicParam.loginbtnId)));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("page-newsDetail")));
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    //执行在热点新闻详情点击收藏按钮提示收藏成功
    public boolean collectionNewsWithLogin(WebDriver driver) {
        log.info("执行在未登录情况下，在热点新闻详情页点击收藏按钮后完成登陆操作,之后再次点击收藏按钮提示收藏成功");
        boolean flag = false;
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("page-msg-collect")));
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement collection = driver.findElement(By.xpath("//*[@id='page-msg-collect']/i"));
        if(collection.getAttribute("class").contains("icon-collect_solid")){
            flag = true;
        }
        return flag;
    }
    
    //执行在热点新闻详情点击收藏按钮提示收藏取消
    public boolean cancelCollectionNews(WebDriver driver) {
        log.info("执行在热点新闻详情点击收藏按钮提示收藏取消");
        boolean flag = false;
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("page-msg-collect")));
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement collection = driver.findElement(By.xpath("//*[@id='page-msg-collect']/i"));
        if(!collection.getAttribute("class").contains("icon-collect_solid")){
            flag = true;
        }
        return flag;
    }
    
    //通用：执行先登录、而后可打开新闻详情页操作
    public void enterRegisterWithLogin(WebDriver driver, String userMobile, String password) {
        log.info("执行先登录、而后可打开新闻详情页操作");
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("home-firstLoginLayer")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='pageFooter']/ul/li[2]/a")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("page-newsList")));
    }
    
    //执行点击语音选项卡后显示正常且页面下方有浮动的音频播放标志操作
    public void enterVoiceDetail(WebDriver driver) {
        log.info("执行点击语音选项卡后显示正常且页面下方有浮动的音频播放标志操作");
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("newsList-content")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.xpath("//*[@id='channel-6-sContainer']/li[1]/a")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("page-newsDetail")));
        new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("playAudio")));
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    //点击音频播放按钮
    public WebElement clickVoice(WebDriver driver) {
        log.info("执行点击语音播放按钮,播放音频，播放按钮状态改变操作");
        WebElement playAudio = driver.findElement(By.id("playAudio"));
        SeleniumUtil.mouseClick(driver, playAudio);
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return playAudio;
    }
    
    //执行点击语音播放按钮,播放音频，播放按钮状态改变操作
    public boolean clickVoicePlay(WebDriver driver) {
        log.info("执行点击语音播放按钮,播放音频，播放按钮状态改变操作");
        boolean flag = true;
        WebElement playAudio = clickVoice(driver);
        if(playAudio.getAttribute("class").contains("isPlay")){
            flag = true;
        }
        return flag;
    }
    
    //执行点击语音播放按钮,停止播放音频，播放按钮状态改变操作
    public boolean clickVoicePlayStop(WebDriver driver) {
        log.info("执行点击语音播放按钮,播放音频，播放按钮状态改变操作");
        boolean flag = true;
        WebElement playAudio = clickVoice(driver);  //调用点击播放
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("playAudio")));    //再次点击，停止播放
        if(!playAudio.getAttribute("class").contains("isPlay")){
            flag = true;
        }
        return flag;
    }
    
    
    //执行在音频播放时后退，在进入停止播放操作
    public boolean backToVoiceDetail(WebDriver driver) {
        log.info("执行在音频播放时后退，在进入停止播放操作");
        boolean flag = true;
        clickVoice(driver);  //调用点击播放
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("detail-header-back")));    //后退
        enterVoiceDetail(driver);   //再进入之前的播放页
        if(!driver.findElement(By.id("playAudio")).getAttribute("class").contains("isPlay")){
            flag = true;
        }
        return flag;
    }
    

    
}
