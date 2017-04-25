package orangelife.page.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import orangelife.page.person.ChooseCommunity;
import orangelife.util.SeleniumUtil;

/**
 * 首页城市服务操作
 * @author qihuan
 * 
 */
public class CityService {

    private static Logger log = LoggerFactory.getLogger(CityService.class);
    
    //通用前置步骤：用户选择小区后进入首页
    public void preStep(WebDriver driver) {
        log.info("前置步骤：用户选择小区后进入首页");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,50).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
    }
    
    //通用步骤：点击icon进入详情页面
    public void enterCityServiceDetail(WebDriver driver, int i) {
        log.info("点击icon进入详情页面");
        WebElement accumulationFund = driver.findElement(By.xpath("//*[@id='news-container']/div[2]/ul/li["+i+"]"));
        SeleniumUtil.mouseClick(driver, accumulationFund);
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("iframe-iframeThis")));
    }
    
    //通用步骤：进入详情页面后点击返回/主按钮
    public void clickHomeBtn(WebDriver driver){
        driver.switchTo().defaultContent();
        driver.findElement(By.id("iframe-home-back")).click();
        new WebDriverWait(driver,50).until(ExpectedConditions.presenceOfElementLocated(By.id("iframe-iframeThis")));
    }
    
    //通用步骤：点击返回按钮到首页
    public void returnToHomePage(WebDriver driver) {
        driver.findElement(By.id("iframe-page-back")).click();
        new WebDriverWait(driver,50).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
    }
    
    //通用步骤：点击全部icon进入全部城市服务列表页面
    public void enterAll(WebDriver driver, int i) {
        log.info("点击全部icon进入全部城市服务列表页面");
        WebElement all = driver.findElement(By.xpath("//*[@id='news-container']/div[2]/ul/li["+i+"]"));
        SeleniumUtil.mouseClick(driver, all);
        new WebDriverWait(driver,50).until(ExpectedConditions.presenceOfElementLocated(By.id("page-moreNavInfo")));
    }
    
    //通用步骤：在城市服务全部列表页中点击icon进入详情页面
    public void enterAllOfDetail(WebDriver driver, int i) {
        log.info("在城市服务全部列表页中点击icon进入地铁查询详情页面");
        WebElement subWay = driver.findElement(By.xpath("//*[@id='page-moreNavInfo-content']/a["+i+"]"));
        SeleniumUtil.mouseClick(driver, subWay);
        new WebDriverWait(driver,50).until(ExpectedConditions.presenceOfElementLocated(By.id("page-iframe")));
    }
    
    //通用步骤：点击返回到从城市服务列表页
    public void returnToHomePage2(WebDriver driver) {
        driver.findElement(By.id("iframe-page-back")).click();
        new WebDriverWait(driver,50).until(ExpectedConditions.presenceOfElementLocated(By.id("page-moreNavInfo")));
    }
    
    //通用步骤：点击返回到从城市服务列表页，适用于有iframe遮罩层的情况
    public void returnToHomePage3(WebDriver driver) {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("iframe-home-back")).click();
        new WebDriverWait(driver,50).until(ExpectedConditions.presenceOfElementLocated(By.id("iframe-iframeThis")));
    }
    
    /**
     * 民生投诉相关操作
     * @param driver
     */    
    //点击曝光台
    public void livelihoodLighthouse(WebDriver driver) {
        log.info("执行点击曝光台操作");
        driver.switchTo().frame("iframe-iframeThis");
        driver.findElement(By.className("exposure")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("categoryid")));
    }  
    
    /**
     * 公交查询相关操作
     * @param driver
     */
    //点击公交查询
    public void searchBus(WebDriver driver, String busLine) {
        log.info("执行点击公交查询操作");
        driver.switchTo().frame("iframe-iframeThis");
        driver.findElement(By.id("text")).sendKeys(busLine);
        driver.findElement(By.id("btnSerch")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("title")));
    } 
     
    /**
     * 景区客流相关操作
     * @param driver
     */
    //点击第一个景点
    public void clickScenicSpot(WebDriver driver) {
        log.info("执行点击第一个景点操作");
        driver.switchTo().frame("iframe-iframeThis");
        driver.findElement(By.xpath("//*[@id='dataContent']/ul[1]/li[1]/div[1]/div/div/a")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("indexbutton")));
    } 
    
    /**
     * 公积金相关操作
     * @param driver
     */
    //点击忘记密码按钮
    public void clickForgotPsd(WebDriver driver) {
        log.info("执行点击忘记密码按钮操作");
        driver.switchTo().frame("iframe-iframeThis");
        driver.findElement(By.xpath("//*[@id='login_form']/div/div[1]/a[1]/div")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("forgetPass")));
    }

    /**
     * 菜价查询相关操作
     * @param driver
     */
    //点击今日菜价按钮
    public void clickTodayPrice(WebDriver driver) {
        log.info("执行点击今日菜价操作");
        driver.switchTo().frame("iframe-iframeThis");
        driver.findElement(By.xpath("//*[@class='none']/ul/li[1]")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("marketsearch")));
    }    
    
    /**
     * 失物招领相关操作
     * @param driver
     */
    //点击帮助按钮
    public void clickHelpBtn(WebDriver driver) {
        log.info("执行点击帮助操作");
        driver.switchTo().frame("iframe-iframeThis");
        driver.findElement(By.id("js4")).click();
    }
    
    /**
     * 广场舞相关操作：不适用通用步骤
     * @param driver
     */
    //点击icon进入广场舞详情页面
    public void enterSquareDance(WebDriver driver, int i) {
        log.info("点击icon进入广场舞详情页面");
        WebElement accumulationFund = driver.findElement(By.xpath("//*[@id='news-container']/div[2]/ul/li["+i+"]"));
        SeleniumUtil.mouseClick(driver, accumulationFund);
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("page-squareDance")));
    }
    
    //点击收藏按钮
    public void clickCollectBtn(WebDriver driver) {
        log.info("执行收藏操作");
        driver.findElement(By.xpath("//*[@id='to-collectSongs']/i")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("model-confirm")));
        driver.findElement(By.className("confirm-no")).click();
    }     
    
    //点击返回按钮到首页
    public void returnFromSquareDance(WebDriver driver) {
        driver.findElement(By.id("header-back")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
    }
    
    
    /**
     * 发票查询相关操作：不适用通用步骤
     * @param driver
     */
    //发票查询详情页中输入数据后点击提交
    public void enterInvoice(WebDriver driver, String invoiceNo, String fphm, String revenueRegisterId) {
        log.info("执行发票查询详情页中输入数据后点击提交操作");
        driver.switchTo().frame("iframe-iframeThis");
        driver.findElement(By.id("invoiceNo")).sendKeys(invoiceNo);
        driver.findElement(By.id("fphm")).sendKeys(fphm);
        driver.findElement(By.id("revenueRegisterId")).sendKeys(revenueRegisterId);
        driver.findElement(By.xpath("//*[@class='am-ft-center']/input[2]")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("zffcswcx")));
    }
    
    /**
     * 节气食经相关操作：不适用通用步骤
     * @param driver
     */
    //节气食经详情页中选中“春分”点击
    public void enterTemplate(WebDriver driver) {
        log.info("执行节气食经详情页中选中“春分”点击操作");
        driver.switchTo().frame("iframe-iframeThis");
        driver.findElement(By.xpath("//*[@class='fm24']/ul/li[4]")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("list")));
    }
    
    /**
     * 结婚预约相关操作：不适用通用步骤
     * @param driver
     */
    //结婚预约情页中选中“预约查询”点击
    public void enterQuery(WebDriver driver) {
        log.info("执行结婚预约情页中选中“预约查询”点击操作");
        driver.switchTo().frame("iframe-iframeThis");
        driver.findElement(By.className("appoint")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("bookfrom")));
    } 
    
    /**
     * 信用卡办理相关操作：不适用通用步骤
     * @param driver
     */
    //信用卡办理页中选中“进度查询”点击
    public void enterProgress(WebDriver driver) {
        log.info("执行信用卡办理页中选中“进度查询”点击操作");
        driver.switchTo().frame("iframe-iframeThis");
        driver.findElement(By.xpath("//*[@id='schedule']/a[2]")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("listbank")));
    } 
}
