package orangelife.page.person;

import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import orangelife.dataProvider.PublicParam;
import orangelife.util.SeleniumUtil;

/**
 * 首页测试-小区选择
 * @author qihuan
 * 
 */
public class ChooseCommunity {
    
    private static Logger log = LoggerFactory.getLogger(ChooseCommunity.class);
    
    //测试选择附近小区(首次)
    public void firChooseCommunity(WebDriver driver){
        log.info("执行选择小区操作!");
        if(SeleniumUtil.isElementExist(driver, By.className("confirm-yes"))){
            Alert confirm = driver.switchTo().alert();
            confirm.accept();
        }
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id("nearlyAddress")));
        String element = "//*[@id='nearlyAddress']/div[2]/div[" +String.valueOf(new Random().nextInt(5)+1)+ "]";
        WebElement community = driver.findElement(By.xpath(element));
        SeleniumUtil.mouseClick(driver, community);                     //模拟鼠标点击操作
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("home-toWaiter")));
    }
    
    //自首页选择小区
    public void secChooseCommunity(WebDriver driver){
        if(SeleniumUtil.isElementExist(driver, By.id(PublicParam.headerbackBtn))){
            driver.findElement(By.id(PublicParam.headerbackBtn)).click();
            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id(PublicParam.homeNowCommunityTxtId)));
        }
        if(SeleniumUtil.isElementExist(driver, By.id(PublicParam.homeNowCommunityTxtId))){
            driver.findElement(By.id(PublicParam.homeNowCommunityTxtId)).click();
            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("nearlyAddress")));
        }        
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("nearlyAddress")));
        String element = "//*[@id='nearlyAddress']/div[2]/div[" +String.valueOf(new Random().nextInt(5)+1)+ "]";
        WebElement community = driver.findElement(By.xpath(element));
        SeleniumUtil.mouseClick(driver, community);                     //模拟鼠标点击操作
        if(SeleniumUtil.isElementExist(driver, By.className("confirm-yes"))){
            driver.findElement(By.className("confirm-yes")).click();
        } 
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id(PublicParam.homeNowCommunityTxtId)));
    }   
    
    public void toWaiter(WebDriver driver){
        //新添加
        driver.findElement(By.id("home-toWaiter")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("waiterList")));
    }

}
