package orangelife.page.person;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import orangelife.dataProvider.PublicParam;
import orangelife.util.SeleniumUtil;

/**
 * 登录操作
 * @author qihuan
 * 
 */
public class Login {
    
    private static Logger log = LoggerFactory.getLogger(Login.class);
    
    //登录测试
    public void testLogin(WebDriver driver, String userMobile, String password) {
        log.info("执行登录操作!");
        login(driver, userMobile, password);
    }

    //进入登录注册页面
    public void login(WebDriver driver, String userMobile, String password) {
        if(SeleniumUtil.isElementExist(driver, By.className("confirm-yes"))){
            driver.findElement(By.className("confirm-yes")).click();
        }
        if(SeleniumUtil.isElementExist(driver, By.id(PublicParam.landingV3GoLoginId))){
            driver.findElement(By.id(PublicParam.landingV3GoLoginId)).click();
        }
        driver.findElement(By.id(PublicParam.loginmobileTxtId)).sendKeys(userMobile);
        driver.findElement(By.id(PublicParam.loginpwdTxtId)).sendKeys(password);
        driver.findElement(By.id(PublicParam.loginbtnId)).click();
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }   
}
