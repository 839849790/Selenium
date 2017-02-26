package orangelife.page.waiter;

import java.util.List;
import java.util.Random;

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
 * 惠民服务员测试
 * @author qihuan
 * 
 */
public class WaiterList {

    private static Logger log = LoggerFactory.getLogger(WaiterList.class);
    
    //惠民服务员测试&附近服务员列表测试&介绍信息测试
    public void testWaiter(WebDriver driver) {
        log.info("执行惠民服务员测试操作!");
        //新用户首次进入惠民服务页面
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        chooseCommunity.toWaiter(driver);
    }
    
    //后退按钮测试
    public void testBack(WebDriver driver) {
        log.info("执行惠民服务员页面后退测试操作!");
        testWaiter(driver);
        driver.findElement(By.id(PublicParam.headerbackBtn)).click();
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id(PublicParam.homeNowCommunityTxtId)));
    }
    
    //惠民服务员"顶部介绍图"元素测试
    public String testWaiterImg(WebDriver driver) {
        log.info("执行惠民服务员页面元素显示测试操作!");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        chooseCommunity.toWaiter(driver);
        WebElement img = driver.findElement(By.xpath("//*[@id='waiterList']/div[1]/div[1]/img"));
        return img.getAttribute("src");
    }
    
    //惠民服务员"附近服务员列表"元素测试
    public void nearbyWaiter(WebDriver driver) {
        log.info("执行惠民服务员页面元素显示测试操作!");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        chooseCommunity.toWaiter(driver);
        List<WebElement> elements = driver.findElements(By.className("waiter-single"));
        int random = new Random().nextInt(elements.size())+1;
        driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div[" +random+ "]/div[1]/img")).click();
    }
    
    //惠民服务员"惠民员申请通道"元素测试
    public void applyChannel(WebDriver driver) {
        log.info("执行惠民服务员页面元素显示测试操作!");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        chooseCommunity.toWaiter(driver);
        if(SeleniumUtil.isElementExist(driver, By.id("to-waiter"))){
            driver.findElement(By.id("to-waiter")).click();
        }
    }
    
    //点击惠民员列表弹出登录测试
    public void clickWaiter(WebDriver driver) {
        log.info("执行点击惠民员列表弹出登录测试操作!");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        chooseCommunity.toWaiter(driver);
        if(SeleniumUtil.isElementExist(driver, By.xpath("//*[@id='nearby-waiter']/div[2]/div[1]"))){
            driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div[1]/div[1]")).click();
        }  
    }    
    
    //惠民服务员登录测试
    public void checkWaiter(WebDriver driver, String userMobile, String password) {
        log.info("执行登录后执行惠民服务员登录测试操作!");
        applyChannel(driver);
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
    }
    
    //切换默认惠民服务员，查看默认服务员变更
    public boolean switchDefaultWaiter(WebDriver driver, String userMobile, String password) {
        boolean flag = false;
        log.info("执行切换默认惠民服务员，查看默认服务员变更测试操作!");
        String checkedWaiterId = switchWaiter(driver, userMobile, password);
        cilckBannerWaiter(driver);
        String newdefaultWaiterId = driver.findElement(By.xpath("//*[@id='default-waiter']/div[2]/div")).getAttribute("data-id");
        if(checkedWaiterId.equals(newdefaultWaiterId)){
            flag = true;
        }
        return flag;
    }
    
    //切换默认惠民服务员，查看服务员不在列表中
    public boolean switchDefaultWaiter2(WebDriver driver, String userMobile, String password) {
        boolean flag = true;
        log.info("执行切换默认惠民服务员，查看服务员不在列表中测试操作!");
        String checkedWaiterId = switchWaiter(driver, userMobile, password);
        cilckBannerWaiter(driver);
        List<WebElement> elements = driver.findElements(By.className("waiter-single"));
        
        for(int i=1; i<elements.size(); i++){
            String nearbyWaiterId = driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div["+i+"]")).getAttribute("data-id");
            if(checkedWaiterId.equals(nearbyWaiterId)){
                flag = false;
            }
        }
        return flag;
    }
    
    //选择附近服务员切换到默认惠民服务员
    public String switchWaiter(WebDriver driver, String userMobile, String password) {
        String checkedWaiterId = "";
        log.info("执行登录后切换默认惠民服务员测试操作!");
        applyChannel(driver);
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        List<WebElement> elements = driver.findElements(By.className("waiter-single"));
        int random = new Random().nextInt(elements.size())+1;
        WebElement defaultWaiter = driver.findElement(By.xpath("//*[@id='nearby-waiter']/div[2]/div["+random+"]"));
        checkedWaiterId = defaultWaiter.getAttribute("data-id");
        defaultWaiter.click();
        if(SeleniumUtil.isElementExist(driver, By.className("confirm-yes"))){
            driver.findElement(By.className("confirm-yes")).click();
        }
        return checkedWaiterId;
    }
    
    //申请成为惠民服务员
    public void applyWaiter(WebDriver driver, String userMobile, String password){
        checkWaiter(driver, userMobile, password);
        if(SeleniumUtil.isElementExist(driver, By.id("to-waiter"))){
            driver.findElement(By.id("to-waiter")).click();
            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("waiter-applyWaiter")));
        }
    }
    
    //惠民服务员申请页后退
    public void backWaiter(WebDriver driver, String userMobile, String password){
        applyWaiter(driver, userMobile, password);
        if(SeleniumUtil.isElementExist(driver, By.id(PublicParam.headerbackBtn))){
            driver.findElement(By.id(PublicParam.headerbackBtn)).click();
            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("waiterList")));
        }
    }
    
    //从首页轮播图进入惠民服务员页面
    public void cilckBannerWaiter(WebDriver driver){
       WebElement element = driver.findElement(By.xpath("//*[@id='home-scrollContainer']/div[3]/div[1]/div[3]"));
       for(int i=0;i<6;i++){
           if(element.getAttribute("class").equals("swiper-slide swiper-slide-active")){
               element.click();
               new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("waiterList")));
               break;
           }else{
               try {
                   Thread.sleep(5000l);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }  
           }
       }
    }
    
    //从惠民服务员页面退回到首页，再通过轮播图进入惠民服务员页面
    public void entryWaiterList(WebDriver driver){
       testBack(driver);
       cilckBannerWaiter(driver);
    }
    
    //未登录情况下从个人中心点击我的惠民 进入登录页面
    public void entryMyWaiterWithNotLogin(WebDriver driver){
        testBack(driver);
        driver.findElement(By.xpath("//*[@id='pageFooter']/ul/li[4]/a")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='page-User']/div/div[2]/a/div")));
        driver.findElement(By.xpath("//*[@id='page-User']/div/div[2]/a/div/div[1]/span")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id(PublicParam.loginmobileTxtId)));
    }
    
    //已登录情况下从个人中心点击我的惠民 进入登录页面
    public void entryMyWaiterWithLogin(WebDriver driver, String userMobile, String password){
        entryMyWaiterWithNotLogin(driver);
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        driver.findElement(By.xpath("//*[@id='page-User']/div/div[2]/a/div/div[1]/span")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("waiterIntro")));
    }
    
    //已登录情况下从个人中心点击我的惠民 进入登录页面再申请惠民服务
    public void entryMyWaiterWithLoginApplayWaiter(WebDriver driver, String userMobile, String password){
        entryMyWaiterWithLogin(driver, userMobile, password);
        driver.findElement(By.id("to-waiter")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("waiter-applyWaiter")));
    }
    
    //已登录情况下从个人中心点击我的惠民 进入惠民注册页面后退至惠民服务页
    public void entryMyWaiterWithLoginGoBack(WebDriver driver, String userMobile, String password){
        entryMyWaiterWithLoginApplayWaiter(driver, userMobile, password);
        driver.findElement(By.id(PublicParam.headerbackBtn)).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("waiterIntro")));
    }
    
    //切换用户地址查看默认服务员
    public boolean updateLoc(WebDriver driver, String userMobile, String password){
        boolean flag = false;
        checkWaiter(driver, userMobile, password);
        String defaultWaitername = driver.findElement(By.xpath("//*[@id='default-waiter']/div[2]/div/div[2]/div[1]/div[1]/span")).getText();
        driver.findElement(By.id(PublicParam.headerbackBtn)).click();
        ChooseCommunity com = new ChooseCommunity();
        com.secChooseCommunity(driver);
        cilckBannerWaiter(driver);
        String newDefaultWaitername = driver.findElement(By.xpath("//*[@id='default-waiter']/div[2]/div/div[2]/div[1]/div[1]/span")).getText();
        if(defaultWaitername.equals(newDefaultWaitername)){
            flag = true;
        }
        return flag;
    }
    
    //切换用户地址查看附近服务员列表
    public void updateLoc2(WebDriver driver, String userMobile, String password){
        checkWaiter(driver, userMobile, password);
        driver.findElement(By.id(PublicParam.headerbackBtn)).click();
        ChooseCommunity com = new ChooseCommunity();
        com.secChooseCommunity(driver);
        cilckBannerWaiter(driver);

    }
}
