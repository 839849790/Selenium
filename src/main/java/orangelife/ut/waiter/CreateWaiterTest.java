package orangelife.ut.waiter;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangelife.page.waiter.CreateWaiter;
import orangelife.page.waiter.WaiterList;

/**
 * 惠民服务员申请页测试
 * @author qihuan
 * 
 */
public class CreateWaiterTest {

    private static String host = "http://teast.swao.cn";    //定义驱动网址
    private static WebDriver driver;
        
    //用例1：测试惠民服务员申请头像上传，头像链接为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase1(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:测试惠民服务员头像上传，头像链接正常为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        createWaiter.upLoadHeadImg(driver, userMobile, password);
        //校验
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='headImage-group']/div[2]/div[1]/img")).getAttribute("src"));
    }
       
    //用例2：测试惠民服务员申请姓名输入，姓名标签为验证方式
    @Test(dataProvider="waiterData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase2(String userMobile, String password, String name) {
        Reporter.log("惠民服务员申请测试:测试惠民服务员申请姓名输入，姓名标签为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        createWaiter.inputName(driver, userMobile, password, name);
        //校验
        Assert.assertEquals(name, driver.findElement(By.xpath("//*[@id='name-group']/div[2]/input")).getAttribute("value"));
    }  

    //用例3：测试惠民服务员申请性别选择，标签为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase3(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:测试惠民服务员申请性别选择，标签为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.chooseSex(driver, userMobile, password));
    }
    
    //用例4：测试手机号和验证码，标签为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase4(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:测试手机号和验证码，标签为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.getValCode(driver, userMobile, password));
    }

    //用例5：服务项可选，标签为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase5(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:服务项可选，标签为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.chooseServiceItems(driver, userMobile, password));
    }

    //用例6：服务时间选择，标签为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase6(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:服务时间选择，标签为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.chooseServiceTime(driver, userMobile, password));
    }
 
    //用例7：填写服务地址，标签值为验证方式
    @Test(dataProvider="waiterData2",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase7(String userMobile, String password, String serviceAdress) {
        Reporter.log("惠民服务员申请测试:填写服务地址，标签值为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        createWaiter.checkAddress(driver, userMobile, password, serviceAdress);
        //校验
        Assert.assertEquals(serviceAdress, driver.findElement(By.xpath("//*[@id='address-list']/div/div[1]/div[2]/input")).getAttribute("value"));
    }
   
    //用例8：新增服务地址，标签为验证方式
    @Test(dataProvider="waiterData2",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase8(String userMobile, String password, String serviceAdress) {
        Reporter.log("惠民服务员申请测试:新增服务地址，标签为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.inServiceAdd(driver, userMobile, password, serviceAdress));
    }
    
    //用例9：多个服务地址删除，标签为验证方式
    @Test(dataProvider="waiterData2",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase9(String userMobile, String password, String serviceAdress) {
        Reporter.log("惠民服务员申请测试:多个服务地址删除，标签为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.delServiceAdd1(driver, userMobile, password, serviceAdress));
    }
    
    //用例10：只有一个服务地址事点击删除,地址信息清空，标签值为验证方式
    @Test(dataProvider="waiterData2",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase10(String userMobile, String password, String serviceAdress) {
        Reporter.log("惠民服务员申请测试:只有一个服务地址事点击删除,地址信息清空，标签值为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.delServiceAdd2(driver, userMobile, password, serviceAdress));
    }
    
    //TODO
    //用例11：检查删除地址时新增地址按钮是否存在，标签为验证方式
    @Test(dataProvider="waiterData2",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase11(String userMobile, String password, String serviceAdress) {
        Reporter.log("惠民服务员申请测试:检查删除地址时新增地址按钮是否存在，标签为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        createWaiter.checkIncreaseBtn(driver, userMobile, password, serviceAdress);
        //校验
        Assert.assertNotNull(driver.findElement(By.id("undefined")));
    }

    //用例12：填写介绍码，标签值为验证方式
    @Test(dataProvider="waiterData3",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase12(String userMobile, String password, String recommendCode) {
        Reporter.log("惠民服务员申请测试:填写介绍码，标签值为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        createWaiter. checkrecommendCode(driver, userMobile, password, recommendCode);
        //校验
        Assert.assertEquals(recommendCode, driver.findElement(By.xpath("//*[@id='recommendCode-group']/div[2]/input")).getAttribute("value"));
    }
    
    //用例13：填写身份证号，标签值为验证方式
    @Test(dataProvider="waiterData4",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase13(String userMobile, String password, String idNumber) {
        Reporter.log("惠民服务员申请测试:填写身份证号，标签值为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        createWaiter. checkIdNumber(driver, userMobile, password, idNumber);
        //校验
        Assert.assertEquals(idNumber, driver.findElement(By.xpath("//*[@id='idNumber-group']/div[2]/input")).getAttribute("value"));
    }
    
    //用例15：支付宝为默认提现方式，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase15(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:支付宝为默认提现方式，标签值为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.defaultCashWay(driver, userMobile, password));
    }
    
    //用例16：选择提现方式下方显示相应的提现信息文本框，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase16(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:选择提现方式下方显示相应的提现信息文本框，标签值为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.getCashWay(driver, userMobile, password));
    }
    
    //用例17：确认提现方式只可单选，标签为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase17(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:确认提现方式只可单选，标签为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.confirmCashWay(driver, userMobile, password));
    }
   
    //用例18：测试备注信息输入，备注标签值为验证方式
    @Test(dataProvider="waiterData5",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase18(String userMobile, String password, String remark) {
        Reporter.log("惠民服务员申请测试:测试备注信息输入，标签值为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        createWaiter.getRemark(driver, userMobile, password, remark);
        //校验
        Assert.assertEquals(remark, driver.findElement(By.xpath("//*[@id='remark-group']/textarea")).getAttribute("value"));
    }
    
    //用例19：测试点击惠民服务协议，跳转界面显示协议内容，备注标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase19(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:测试点击惠民服务协议，跳转界面显示协议内容，备注标签值为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        createWaiter.openAgreement(driver, userMobile, password);
        //校验
        Assert.assertNotNull(driver.findElement(By.id("page-CMS")));
    }
 
    //用例20：测试点击惠民服务协议进入界面显示协议内容后可后退回到申请页，备注标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase20(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:测试点击惠民服务协议进入界面显示协议内容后可后退回到申请页，备注标签值为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        createWaiter.returnApplyPage(driver, userMobile, password);
        //校验
        Assert.assertEquals("申请", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    }   
    
    //用例21：测试可正常勾选“我已阅读并同意惠民服务协议”，备注标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase21(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:测试可正常勾选“我已阅读并同意惠民服务协议”，备注标签值为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.checkAgreement(driver, userMobile, password));
    }
    
    //用例22：测试填写数据有误时提示数据出错，出错的栏目显示为红色，对应的标签信值息为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase22(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:测试填写数据有误时提示数据出错，出错的栏目显示为红色，点击修改后红色消失，对应的标签信值息为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行:直接点击提交按钮(未录入个人信息)
        createWaiter.preStep(driver, userMobile, password);
        createWaiter.submitWithoutInput(driver, userMobile, password);
        //校验
        Assert.assertEquals("row-flex reg-item row-input errorMsg", driver.findElement(By.id("name-group")).getAttribute("class"));
    }
   
    //用例23：测试填写数据有误时提示数据出错，出错的栏目显示为红色，点击修改后红色消失，对应的标签信值息为验证方式
    @Test(dataProvider="waiterData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase23(String userMobile, String password, String name) {
        Reporter.log("惠民服务员申请测试:测试填写数据有误时提示数据出错，出错的栏目显示为红色，点击修改后红色消失，对应的标签信值息为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行:点击提交按钮(录入个人信息)
        createWaiter.preStep(driver, userMobile, password);
        createWaiter.submitWithInput(driver, userMobile, password, name);
        //校验
        Assert.assertEquals("row-flex reg-item row-input", driver.findElement(By.id("name-group")).getAttribute("class"));
    }
   
    //用例24：测试点击上传个人身份照，标签信值息为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase24(String userMobile, String password) {
        Reporter.log("惠民服务员申请测试:测试点击上传个人身份照，标签信值息为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        createWaiter.upIdImg(driver, userMobile, password);
        //校验
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='idImageUpload-group']/div[2]/div[1]/div/p[1]/span/img")).getAttribute("src"));
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='idImageUpload-group']/div[2]/div[2]/div/p[1]/span/img")).getAttribute("src"));
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='idImageUpload-group']/div[2]/div[3]/div/p[1]/span/img")).getAttribute("src"));
    }

   
    //用例25：测试填写数据无误,点击提交按钮,提示申请成功，标签信值息为验证方式
    @Test(dataProvider="waiterData6",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase25(String userMobile, String password, String name, String serviceAdress, String idNumber, String remark, String cashAccount) {
        Reporter.log("惠民服务员申请测试:测试填写数据无误,点击提交按钮,提示申请成功，标签信值息为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.preStep(driver, userMobile, password);
        createWaiter.registerWaiter(driver, userMobile, password, name, serviceAdress, idNumber, remark, cashAccount);
        //校验
        Assert.assertEquals("正在审核中……", driver.findElement(By.id("to-waiter")).getText());
    }    
    
    //用例26：测试重新登录查看正在审核中,标签信值息为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase26(String userMobile, String password) {
        Reporter.log("惠民服务员申请审核测试:测试重新登录查看正在审核中,标签信值息为验证方式");
        //数据准备
        WaiterList waiter  = new WaiterList();
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        waiter.checkWaiter(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.checkStaus(driver));
    }
    
    //用例27：测试重新登录查看审核未通过时,显示未通过原因，点击可进行修改，标签信值息为验证方式
    @Test(dataProvider="auditData2",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase27(String userMobile, String password) {
        Reporter.log("惠民服务员申请审核测试:测试重新登录查看审核未通过时,显示未通过原因，点击可进行修改，标签信值息为验证方式");
        //数据准备
        WaiterList waiter  = new WaiterList();
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        waiter.checkWaiter(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.checkStaus(driver));
    }
    
    //用例28：测试审核通过的首次登录：显示为开始惠民服务,点击后进培训中心，标签信值息为验证方式
    @Test(dataProvider="auditData3",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase28(String userMobile, String password) {
        Reporter.log("惠民服务员申请审核测试:测试审核通过的首次登录：显示为开始惠民服务,点击后进培训中心，标签信值息为验证方式");
        //数据准备
        WaiterList waiter  = new WaiterList();
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        waiter.checkWaiter(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.checkStaus(driver));
    }
    
    //用例29：测试审核通过的非首次登录：显示为我的惠民服务,点击后进入惠民服务详情，标签信值息为验证方式
    @Test(dataProvider="auditData4",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase29(String userMobile, String password) {
        Reporter.log("惠民服务员申请审核测试:测试审核通过的非首次登录：显示为我的惠民服务,点击后进入惠民服务详情，标签信值息为验证方式");
        //数据准备
        WaiterList waiter  = new WaiterList();
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        waiter.checkWaiter(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.checkStaus(driver));
    }
    
    //用例30：测试从个人中心进入惠民服务查看状态审核中时,不可点击，标签信值息为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase30(String userMobile, String password) {
        Reporter.log("惠民服务员申请审核测试:测试从个人中心进入惠民服务查看状态审核中时,不可点击，标签信值息为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.personWaiter(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.checkStaus(driver));
    }
    
    //用例31：测试从个人中心进入惠民服务查看状态审核未通过,显示未通过原因，点击可进行修改，标签信值息为验证方式
    @Test(dataProvider="auditData2",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase31(String userMobile, String password) {
        Reporter.log("惠民服务员申请审核测试:测试从个人中心进入惠民服务查看状态审核未通过,显示未通过原因，点击可进行修改，标签信值息为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.personWaiter(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.checkStaus(driver));
    }
    
    //用例32：测试从个人中心首次登录进入惠民服务查看状态审核通过：显示为开始惠民服务,点击后进培训中心，标签信值息为验证方式
    @Test(dataProvider="auditData3",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase32(String userMobile, String password) {
        Reporter.log("惠民服务员申请审核测试:测试从个人中心首次登录进入惠民服务查看状态审核通过：显示为开始惠民服务,点击后进培训中心，标签信值息为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.personWaiter(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.checkStaus(driver));
    }
    
    //用例33：测试从个人中心非首次登录进入惠民服务查看状态审核通过：显示为我的惠民服务,点击后进入惠民服务详情，标签信值息为验证方式
    @Test(dataProvider="auditData4",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase33(String userMobile, String password) {
        Reporter.log("惠民服务员申请审核测试:测试从个人中心非首次登录进入惠民服务查看状态审核通过：显示为我的惠民服务,点击后进入惠民服务详情，标签信值息为验证方式");
        //数据准备
        CreateWaiter createWaiter = new CreateWaiter();
        //执行
        createWaiter.personWaiter(driver, userMobile, password);
        //校验
        Assert.assertTrue(createWaiter.checkStaus(driver));
    }
    
    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get(host);
        driver.manage().window().setSize(new Dimension(430, 700));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
    }  
    
    @AfterMethod
    public void afterMethod() {
        //关闭打开的浏览器 
        //driver.quit();  
    }
}
