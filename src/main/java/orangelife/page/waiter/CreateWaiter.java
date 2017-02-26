package orangelife.page.waiter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.util.Strings;

import orangelife.page.ChooseCommunity;
import orangelife.page.Login;
import orangelife.util.SeleniumUtil;

/**
 * 惠民服务员测试
 * @author qihuan
 * 
 */
public class CreateWaiter {

    private static Logger log = LoggerFactory.getLogger(CreateWaiter.class);
    
    //前置步骤：用户选择小区后登录、进入惠民服务员申请页面
    public void preStep(WebDriver driver, String userMobile, String password) {
        log.info("前置步骤：用户选择小区、登录、然后进入惠民服务员申请页面");
        WaiterList waiter = new WaiterList();
        waiter.applyWaiter(driver, userMobile, password);
    }
    
    //上传头像
    public void upLoadHeadImg(WebDriver driver, String userMobile, String password) {
        log.info("执行上传头像操作!");
        WebElement headImageGroup = driver.findElement(By.className("headImg-upload"));
        //headImageGroup.findElement(By.tagName("i")).click();
        WebElement icon = headImageGroup.findElement(By.tagName("i"));
        if("iconfont icon-photo".equals(icon.getAttribute("class"))){
            icon.click();
        }
        SeleniumUtil.handleUpload("chrome", new File("E:\\selenium\\waiter\\headImg.jpg"));
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='headImage-group']/div[2]/div[1]/img")));
    }
    
    //录入姓名
    public void inputName(WebDriver driver, String userMobile, String password, String name) {
        log.info("执行录入姓名操作!");
        driver.findElement(By.xpath("//*[@id='name-group']/div[2]/input")).sendKeys(name);
    }
    
    //选择性别
    public boolean chooseSex(WebDriver driver, String userMobile, String password) {
        log.info("执行选择性别操作!");
        boolean flag = false;
        int j = 0;
        String radio_active = "radio-item radio-active";  
        List sex = new ArrayList();
        WebElement sex_man = driver.findElement(By.xpath("//*[@id='sex-group']/div[2]/span[1]"));
        WebElement sex_woman = driver.findElement(By.xpath("//*[@id='sex-group']/div[2]/span[2]"));
        if(radio_active.equals(sex_man.getAttribute("class"))){
            sex_woman.click();
        }else{
            sex_man.click();
        }
        sex.add(sex_man.getAttribute("class"));
        sex.add(sex_woman.getAttribute("class"));
        for(int i=0;i<sex.size();i++){
            if(radio_active.equals(sex.get(i))){
                j++;
            }
        }
        if(j==1){
            flag = true;
        }
        return flag;
    }
    
    //获取验证码
    public boolean getValCode(WebDriver driver, String userMobile, String password) {
        log.info("执行获取验证码操作!");
        boolean flag = false;
        driver.findElement(By.xpath("//*[@id='phone-group']/div[2]/input")).sendKeys(userMobile);
        WebElement vCode = driver.findElement(By.className("getVCode"));
        vCode.click();
        for(long i = System.currentTimeMillis(); i < System.currentTimeMillis() + 60; i++){
            if(vCode.getText().contains("秒后发送")){
                flag = true;
                break;
            }
        }   
        return flag;
    }
    
    //服务项可选
    public boolean chooseServiceItems(WebDriver driver, String userMobile, String password) {
        log.info("执行选择服务项操作!");
        boolean flag = false;
        String checkbox_item = "checkbox-item checkbox-active";
        int num = 0;
        WebElement parent = driver.findElement(By.xpath("//*[@id='skill-group']/div[2]"));
        List<WebElement> list = parent.findElements(By.tagName("span"));
        for(int i=0; i<list.size();i++){
            list.get(i).click();
            if(checkbox_item.equals(list.get(i).getAttribute("class"))){
                num++;
            }   
        }
        if(num==list.size()){
            flag = true;
        }
        return flag;
    }
    
    //服务时间选择
    public boolean chooseServiceTime(WebDriver driver, String userMobile, String password) {
        log.info("执行选择服务时间操作!");
        boolean flag = true;
        List<String> time = new ArrayList<String>();
        driver.findElement(By.id("dateRange-group")).click();
        WebElement parent = driver.findElement(By.className("timeRange-day"));
        parent.findElements(By.tagName("li")).get(0).click();
        time.add(parent.findElements(By.tagName("li")).get(0).getAttribute("data-name"));
        WebElement timeItem = driver.findElement(By.className("time-item"));
        List<WebElement> list = timeItem.findElements(By.tagName("li"));
        for(int i=0;i<list.size();i++){
            list.get(i).click();
            time.add(list.get(i).getAttribute("data-name"));
        }
        driver.findElement(By.className("timeRange-complete")).click();
        WebElement time_val = driver.findElement(By.xpath("//*[@id='dateRange-group']/div[1]/div/div[2]"));
        for(int i=0;i<time.size();i++){
            if(!time_val.getText().contains(time.get(i))){
                flag = false;
            }
        }
        return flag;
    }
    
    //输入地址
    public void checkAddress(WebDriver driver, String userMobile, String password, String serviceAdress) {
        log.info("执行输入地址操作!");
        driver.findElement(By.xpath("//*[@id='address-list']/div/div[1]/div[2]/input")).sendKeys(serviceAdress);
    }
    
    //新增服务地址
    public void increaseAddress(WebDriver driver, String userMobile, String password, String serviceAdress){
        driver.findElement(By.id("undefined")).click();
        driver.findElement(By.id("undefined")).click();
        driver.findElement(By.xpath("//*[@id='address-list']/div[1]/div[1]/div[2]/input")).sendKeys(serviceAdress);
        driver.findElement(By.xpath("//*[@id='address-list']/div[2]/div[1]/div[2]/input")).sendKeys(serviceAdress);
        driver.findElement(By.xpath("//*[@id='address-list']/div[3]/div[1]/div[2]/input")).sendKeys(serviceAdress);
        driver.findElement(By.xpath("//*[@id='address-list']/div[3]/div[2]/div[1]/a")).click();
    }
    //执行新增服务地址操作
    public boolean inServiceAdd(WebDriver driver, String userMobile, String password, String serviceAdress) {
        log.info("执行新增服务地址操作!");
        boolean flag = false;
        String activeAddress  = "address-item  active";
        int active_Num = 0;
        increaseAddress(driver, userMobile, password, serviceAdress);
        List<WebElement> list = driver.findElement(By.id("address-list")).findElements(By.tagName("div"));
        for(int i=0;i<list.size();i++){
            if(activeAddress.equals(list.get(i).getAttribute("class"))){
                active_Num++;
            }
        }
        if(active_Num==1){
            flag = true;
        }
        return flag;
    }
    
    //多个服务地址的删除(接新增服务地址)
    public boolean delServiceAdd1(WebDriver driver, String userMobile, String password, String serviceAdress) {
        log.info("执行多个服务地址的删除操作!");
        boolean flag = false;
        String activeAddress  = "address-item active";
        String inactiveAddress  = "address-item";
        int active_Num = 0;
        int inactive_Num = 0;
        increaseAddress(driver, userMobile, password, serviceAdress);
        driver.findElement(By.xpath("//*[@id='address-list']/div[3]/div[2]/div[2]/a")).click();
        List<WebElement> list = driver.findElement(By.id("address-list")).findElements(By.tagName("div"));
        for(int i=0;i<list.size();i++){
            if(activeAddress.equals(list.get(i).getAttribute("class"))){
                active_Num++;
            }
            if(inactiveAddress.equals(list.get(i).getAttribute("class"))){
                inactive_Num++;
            }
        }
        if(active_Num==1 && inactive_Num==1 ){
            flag = true;
        }
        return flag;
    }
    
    //单个服务地址的删除(接新增服务地址)
    public boolean delServiceAdd2(WebDriver driver, String userMobile, String password, String serviceAdress) {
        log.info("执行单个服务地址的删除操作!");
        boolean flag = false;
        increaseAddress(driver, userMobile, password, serviceAdress);
        driver.findElement(By.xpath("//*[@id='address-list']/div[3]/div[2]/div[2]/a")).click();
        driver.findElement(By.xpath("//*[@id='address-list']/div[2]/div[2]/div[2]/a")).click();
        if(SeleniumUtil.isElementExist(driver, By.xpath("//*[@id='address-list']/div[1]/div[2]/div[2]/a"))){
            driver.findElement(By.xpath("//*[@id='address-list']/div/div[2]/div[2]/a")).click();
            if(Strings.isNullOrEmpty(driver.findElement(By.xpath("//*[@id='address-list']/div/div[1]/div[2]/input")).getAttribute("value"))){
                flag = true;
            }
        }else{
            driver.findElement(By.xpath("//*[@id='address-list']/div[1]/div[2]/div[2]/a")).click();
            if(Strings.isNullOrEmpty(driver.findElement(By.xpath("//*[@id='address-list']/div[1]/div[1]/div[2]/input")).getAttribute("value"))){
                flag = true;
            }
        }
        return flag;
    }
    
    //检查新增地址按钮
    public void checkIncreaseBtn(WebDriver driver, String userMobile, String password, String serviceAdress) {
        log.info("执行单个服务地址的删除操作!");
        increaseAddress(driver, userMobile, password, serviceAdress);
        driver.findElement(By.xpath("//*[@id='address-list']/div[3]/div[2]/div[2]/a")).click();
    }
    
    //检查介绍码信息
    public void checkrecommendCode(WebDriver driver, String userMobile, String password, String recommendCode) {
        log.info("执行检查介绍码信息操作!");
        driver.findElement(By.xpath("//*[@id='recommendCode-group']/div[2]/input")).sendKeys(recommendCode);
    }
    
    //检查身份证号码信息
    public void checkIdNumber(WebDriver driver, String userMobile, String password, String idNumber) {
        log.info("执行检查身份证号码信息操作!");
        driver.findElement(By.xpath("//*[@id='idNumber-group']/div[2]/input")).sendKeys(idNumber);
    }
    
    //确认默认提现方式
    public boolean defaultCashWay(WebDriver driver, String userMobile, String password) {
        boolean flag = false;
        log.info("执行确认默认提现方式操作!");
        String defaultWay = "radio-item radio-active";
        List<WebElement> list = driver.findElement(By.xpath("//*[@id='cashWay-group']/div[1]/div[2]")).findElements(By.tagName("span"));
        for(int i=0;i<list.size();i++){
            if(defaultWay.equals(list.get(i).getAttribute("class")) && "alipay".equals(list.get(i).getAttribute("data-code"))){
                flag = true;
                break;
            }
        }
        return flag;
    }
    
    //选择提现方式下方显示相应的提现信息文本框
    public boolean getCashWay(WebDriver driver, String userMobile, String password) {
        boolean flag = false;
        log.info("执行选择提现方式操作!");
        List<WebElement> list = driver.findElement(By.xpath("//*[@id='cashWay-group']/div[1]/div[2]")).findElements(By.tagName("span"));
        for(int i=0;i<list.size();i++){
            list.get(i).click();
            if("alipay".equals(list.get(i).getAttribute("data-code"))){
                if(SeleniumUtil.isElementExist(driver, By.id("cashName-group")) && SeleniumUtil.isElementExist(driver, By.id("cashAccount-group"))){
                    flag = true;
                    continue;
                } 
            }
            if("bank".equals(list.get(i).getAttribute("data-code"))){
                if(SeleniumUtil.isElementExist(driver, By.id("cashName-group")) && SeleniumUtil.isElementExist(driver, By.id("cashAccount-group"))){
                    flag = true;
                    continue;
                } 
            }
        }
        return flag;
    }
    
    //确认提现方式只可单选
    public boolean confirmCashWay(WebDriver driver, String userMobile, String password) {
        log.info("执行确认提现方式只可单选操作!");
        boolean flag = false;
        String checkedWay = "radio-item radio-active";
        int checkedNum = 0;
        List<WebElement> list = driver.findElement(By.xpath("//*[@id='cashWay-group']/div[1]/div[2]")).findElements(By.tagName("span"));
        for(int i=0;i<list.size();i++){
            list.get(i).click();
        }
        for(int i=0;i<list.size();i++){
            if(checkedWay.equals(list.get(i).getAttribute("class"))){
                checkedNum++;
            }
        }
        if(checkedNum==1){
            flag = true;
        }  
        return flag;
    }
    
    //填写备注信息
    public void getRemark(WebDriver driver, String userMobile, String password, String remark) {
        log.info("执行填写备注信息操作!");
        driver.findElement(By.xpath("//*[@id='remark-group']/textarea")).sendKeys(remark);
    }
    
    //打开惠民服务协议页面
    public void openAgreement(WebDriver driver, String userMobile, String password){
        log.info("执行打开惠民服务协议页面操作!");
        driver.findElement(By.xpath("//*[@id='waiter-applyWaiter']/div/div[2]/div[4]/a")).click();
    }

    //打开惠民服务协议页面后返回至申请页
    public void returnApplyPage(WebDriver driver, String userMobile, String password){
        log.info("执行打开惠民服务协议页面操作!");
        openAgreement(driver, userMobile, password);
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("header-back")));
        driver.findElement(By.id("header-back")).click();
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("waiter-applyWaiter")));
    }
    
    //勾选惠民服务协议
    public boolean checkAgreement(WebDriver driver, String userMobile, String password){
        log.info("执行勾选惠民服务协议操作!");
        boolean flag = false;
        String activeAgreement = "agreement agreement-active";
        String inactiveAgreement = "agreement";
        WebElement agreement = driver.findElement(By.xpath("//*[@id='waiter-applyWaiter']/div/div[2]/div[4]"));
        WebElement agreementBtn = driver.findElement(By.xpath("//*[@id='waiter-applyWaiter']/div/div[2]/div[4]/p"));
        if(activeAgreement.equals(agreement.getAttribute("class"))){
            agreementBtn.click();
            if(inactiveAgreement.equals(agreement.getAttribute("class"))){
                agreementBtn.click();
                if(activeAgreement.equals(agreement.getAttribute("class"))){
                    flag = true;
                }
            }
        }else{
            agreementBtn.click();
            if(activeAgreement.equals(agreement.getAttribute("class"))){
                flag = true;
            }
        }
        return flag;
    }
    
    //未输入信息点击提交按钮
    public void submitWithoutInput(WebDriver driver, String userMobile, String password){
        log.info("执行未输入信息点击提交按钮操作!");
        driver.findElement(By.id("btn-complete")).click();
    }
    
    //输入信息后点击提交按钮
    public void submitWithInput(WebDriver driver, String userMobile, String password, String name){
        log.info("执行输入信息后点击提交按钮操作!");
        inputName(driver, userMobile, password, name);
        driver.findElement(By.id("btn-complete")).click();
    }
    
    //上传身份照信息
    public void upIdImg(WebDriver driver, String userMobile, String password) {
        log.info("执行上传身份照信息操作!");
        
        WebElement idImageFront = driver.findElement(By.xpath("//*[@id='idImageUpload-group']/div[2]/div[1]/div/p[1]/span"));
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        idImageFront.findElement(By.tagName("i")).click();
        SeleniumUtil.handleUpload("chrome", new File("E:\\selenium\\waiter\\idImageFront.jpg"));
        
        WebElement idImageBack = driver.findElement(By.xpath("//*[@id='idImageUpload-group']/div[2]/div[2]/div/p[1]/span"));
        idImageBack.findElement(By.tagName("i")).click();
        SeleniumUtil.handleUpload("chrome", new File("E:\\selenium\\waiter\\idImageBack.jpg"));
        
        WebElement personIdImage = driver.findElement(By.xpath("//*[@id='idImageUpload-group']/div[2]/div[3]/div/p[1]/span"));
        personIdImage.findElement(By.tagName("i")).click();
        SeleniumUtil.handleUpload("chrome", new File("E:\\selenium\\waiter\\personIdImage.jpg"));
        
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='idImageUpload-group']/div[2]/div[1]/div/p[1]/span/img")));
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='idImageUpload-group']/div[2]/div[2]/div/p[1]/span/img")));
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='idImageUpload-group']/div[2]/div[3]/div/p[1]/span/img")));
    }
    
    //用户成功注册惠民服务员，等待审核
    public void registerWaiter(WebDriver driver, String userMobile, String password, String name, String serviceAdress, String idNumber, String remark, String cashAccount) {
        log.info("执行上传身份照信息操作!");
        upLoadHeadImg(driver, userMobile, password);
        inputName(driver, userMobile, password, name);
        chooseSex(driver, userMobile,  password);
        getValCode(driver, userMobile, password);
        try {
            Thread.sleep(20000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chooseServiceItems(driver, userMobile, password);
        chooseServiceTime(driver, userMobile, password);
        checkAddress(driver, userMobile, password, serviceAdress);
        checkIdNumber(driver, userMobile, password, idNumber);
        upIdImg(driver, userMobile, password);
        //confirmCashWay(driver, userMobile, password); //微信支付待处理
        chooseAliPay(driver, name, cashAccount);
        getRemark(driver, userMobile, password, remark);
        driver.findElement(By.id("btn-complete")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("to-waiter")));
    }
    
    public void chooseAliPay(WebDriver driver, String name, String cashAccount){
        WebElement alipay = driver.findElement(By.xpath("//*[@id='cashWay-group']/div[1]/div[2]/div[1]/span"));
        alipay.click();
        if("支付宝提现".equals(alipay.getText())){
            driver.findElement(By.xpath("//*[@id='cashName-group']/div[2]/input")).sendKeys(name);
            driver.findElement(By.xpath("//*[@id='cashAccount-group']/div[2]/input")).sendKeys(cashAccount);;
        }
    }
    
    //查看审核状态:未通过
    public boolean checkStaus(WebDriver driver){
        boolean flag = false;
        WebElement status = driver.findElement(By.id("to-waiter"));
        
        //审核中
        if("正在审核中……".equals(status.getText())){
            flag = true;
        }else if("查看详情".equals(status.getText())){
            //审核未通过
            WebElement stauts = null;
            String msgReport = null;
            if(SeleniumUtil.isElementExist(driver, By.xpath("//*[@id='waiterList']/div[2]/div[1]/p[1]/span"))){
                stauts = driver.findElement(By.xpath("//*[@id='waiterList']/div[2]/div[1]/p[1]/span"));
                msgReport = stauts.getText().substring(9, stauts.getText().length());
                if(!Strings.isNullOrEmpty(msgReport)){
                    flag = true;
  
                }
            }
            if(SeleniumUtil.isElementExist(driver, By.xpath("//*[@id='waiterIntro']/div[2]/div[1]/p[1]/span"))){
                stauts = driver.findElement(By.xpath("//*[@id='waiterIntro']/div[2]/div[1]/p[1]/span"));
                msgReport = stauts.getText().substring(9, stauts.getText().length());
                if(!Strings.isNullOrEmpty(msgReport)){
                    flag = true;
                }
            }
        }else if("开始惠民服务".equals(status.getText())){
            //审核通过,首次登录
            status.click();
            if("培训中心".equals(driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText())){
                flag = true;
            }
        }else{
            //审核通过,非首次登录
            if("我的惠民服务".equals(status.getText())){
                status.click();
                new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("waiter-home")));
                if("惠民服务".equals(driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText())){
                    flag = true;
                }
            }  
        }
        return flag;
    }
    
    public void personWaiter(WebDriver driver, String userMobile, String password){
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        driver.findElement(By.xpath("//*[@id='pageFooter']/ul/li[4]/a")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-User")));
        driver.findElement(By.xpath("//*[@id='page-User']/div/div[2]/a/div")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-login")));
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        driver.findElement(By.xpath("//*[@id='page-User']/div/div[2]/a/div")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("waiterIntro")));
    }

}
