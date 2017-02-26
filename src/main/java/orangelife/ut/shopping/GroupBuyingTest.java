package orangelife.ut.shopping;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangelife.page.shopping.GroupBuying;

/**
 * 惠民购物测试
 * @author 欢
 * 
 */
public class GroupBuyingTest {

    private static String host = "http://teast.swao.cn";    //定义驱动网址
    private static WebDriver driver;
    private static String shopping_url = "http://teast.swao.cn/#mall";    //惠民购物网址
    
    //用例1：打开惠民购物，查看顶部惠民团购部分，正确显示团购信息：【图片】【现价】【原价】【成团人数】【已成团人数】【商品名】【商品简介】，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase1(String userMobile, String password) {
        Reporter.log("惠民购物测试:打开惠民购物，查看顶部惠民团购部分，正确显示团购信息：【图片】【现价】【原价】【成团人数】【已成团人数】【商品名】【商品简介】，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        List<WebElement> groupList = groupBuying.getGroupBuyingList(driver);
        for(int i=1;i<=groupList.size();i++){
            WebElement totalNum = driver.findElement(By.xpath("//*[@id='mallHome-content']/div[1]/div/div/div["+ i +"]/a/div/div[2]/p[1]"));
            WebElement finishNum = driver.findElement(By.xpath("//*[@id='mallHome-content']/div[1]/div/div/div["+ i +"]/a/div/div[2]/p[1]/span"));
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mallHome-content']/div[1]/div/div/div["+ i +"]/a/div/div[1]/img")).getAttribute("src"));
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mallHome-content']/div[1]/div/div/div["+ i +"]/a/div/div[2]/div/span")).getText());
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mallHome-content']/div[1]/div/div/div["+ i +"]/a/div/div[2]/div/del")).getText());
            Assert.assertNotNull(finishNum.getText());
            Assert.assertNotNull(totalNum.getText().replace(finishNum.getText(), "").trim());
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mallHome-content']/div[1]/div/div/div["+ i +"]/a/div/div[2]/p[2]")).getText());
        }
    }
   
    //用例2：查看顶部惠民团购部分，商品显示在屏幕正中,选择商品跳转至商品详情，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase2(String userMobile, String password) {
        Reporter.log("惠民购物测试:打开惠民购物，查看顶部惠民团购部分，商品显示在屏幕正中,选择商品跳转至商品详情，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        List<WebElement> gorupList = groupBuying.getGroupBuyingList(driver);
        Assert.assertTrue(groupBuying.openGroupBuyInfo(driver, gorupList));
    }
    
    //用例3：点击【查看更多团购商品】跳转至惠民团购列表，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase3(String userMobile, String password) {
        Reporter.log("惠民购物测试:点击【查看更多团购商品】跳转至惠民团购列表，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        groupBuying.openMoreGroupBuyInfo(driver);
        Assert.assertEquals("scroll-container", driver.findElement(By.id("scroll-container")).getAttribute("class"));
    }  

    //用例4：查看惠民团购列表，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase4(String userMobile, String password) {
        Reporter.log("惠民购物测试:查看惠民团购列表，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        groupBuying.openMoreGroupBuyInfo(driver);
        List<WebElement> groupList = groupBuying.getMoreGroupBuyList(driver);
        for(int i=1;i<=groupList.size();i++){
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='scroll-container']/a["+ i +"]/div/div[1]/img")).getAttribute("src"));
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='scroll-container']/a["+ i +"]/div/div[1]/div/div[1]/p/span")));
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='scroll-container']/a["+ i +"]/div/div[1]/div/div[2]/s")));
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='scroll-container']/a["+ i +"]/div/div[1]/div/div[2]/p/span[1]")));
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='scroll-container']/a["+ i +"]/div/div[1]/div/div[2]/p/span[2]")));
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='scroll-container']/a["+ i +"]/div/div[2]/h2")));
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='scroll-container']/a["+ i +"]/div/div[2]/p")));
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='scroll-container']/a["+ i +"]/div/div[1]/div/div[3]")));
        }
    }   
    
    //用例5：从惠民团购列表返回，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase5(String userMobile, String password) {
        Reporter.log("惠民购物测试:从惠民团购列表返回，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        groupBuying.openMoreGroupBuyInfo(driver);
        groupBuying.returnFromMoreGroupBuyInfo(driver);
        Assert.assertEquals(shopping_url, driver.getCurrentUrl());
    }   
    
    //用例6：查看团购商品信息显示，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase6(String userMobile, String password) {
        Reporter.log("惠民购物测试:查看团购商品信息显示，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        groupBuying.openMoreGroupBuyInfo(driver);
        groupBuying.getFirstMoreGroupBuy(driver);
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[1]/div[1]/a/img")).getAttribute("src"));
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[1]/div[3]/div[1]/div[1]/span[1]")));
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[1]/div[3]/div[1]/div[2]/del")));
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[1]/div[3]/div[2]/div[1]")));
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[1]/div[3]/div[1]/div[2]/span")));
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[1]/div[3]/div[2]/div[2]")));
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[1]/div[2]/div[1]")));
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[1]/div[2]/div[2]")));
    }     
    
    //用例7：点击【查看参团详情】,查看参团买家列表信息，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase7(String userMobile, String password) {
        Reporter.log("惠民购物测试:点击【查看参团详情】,查看参团买家列表信息，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        groupBuying.openMoreGroupBuyInfo(driver);
        groupBuying.getFirstMoreGroupBuy(driver);
        List<WebElement> groupBuyers = groupBuying.getGroupBuyerInfo(driver);
        for(int i=1;i<=groupBuyers.size();i++){
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[2]/div[2]/div["+ i +"]/div[1]/span/span")));
            Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[2]/div[2]/div["+ i +"]/div[2]")));
        }
    }    
    
    //用例8：点击【查看参团详情】,查看参团信息显示【成团缺少人数】，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase8(String userMobile, String password) {
        Reporter.log("惠民购物测试:点击【查看参团详情】,查看参团信息显示【成团缺少人数】，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        groupBuying.openMoreGroupBuyInfo(driver);
        groupBuying.getFirstMoreGroupBuy(driver);
        Assert.assertTrue(groupBuying.getLackBuyerNum(driver));
    }   

    //用例9：点击【商品描述】【商品参数】【保障说明】，下方切换显示相应内容，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase9(String userMobile, String password) {
        Reporter.log("惠民购物测试:点击【商品描述】【商品参数】【保障说明】，下方切换显示相应内容，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        groupBuying.openMoreGroupBuyInfo(driver);
        groupBuying.getFirstMoreGroupBuy(driver);
        Assert.assertTrue(groupBuying.getSpecInfo(driver));
    }
    
    //用例10：点击【分享】底部弹出分享渠道，选择渠道弹出链接复制提示，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase10(String userMobile, String password) {
        Reporter.log("惠民购物测试:点击【分享】底部弹出分享渠道，选择渠道，跳转至相应APP/授权/弹出链接复制提示，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        groupBuying.openMoreGroupBuyInfo(driver);
        groupBuying.getFirstMoreGroupBuy(driver);
        Assert.assertTrue(groupBuying.share(driver));
    }  
    
    //用例11：在未登录情况下点击【立刻参团】提示登录，跳转至登录界面，登录成功后，跳转至订单确认界面，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase11(String userMobile, String password) {
        Reporter.log("惠民购物测试:在未登录情况下点击【立刻参团】提示登录，跳转至登录界面，登录成功后，跳转至订单确认界面，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        groupBuying.openMoreGroupBuyInfo(driver);
        groupBuying.getFirstMoreGroupBuy(driver);
        groupBuying.joinGroup(driver, userMobile, password);
        Assert.assertEquals("订单确认", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    } 
    
    //用例12：在已登录情况下点击【立刻参团】提示登录，直接跳转至订单确认界面，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase12(String userMobile, String password) {
        Reporter.log("惠民购物测试:在已登录情况下点击【立刻参团】提示登录，直接跳转至订单确认界面，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.joinGroup2(driver, userMobile, password);
        Assert.assertEquals("订单确认", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    }  
  
    //用例13：在惠民购物页中点击【已结束】【正在抢购】【即将开抢】，下方切换显示相应内容，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase13(String userMobile, String password) {
        Reporter.log("惠民购物测试:在惠民购物页中点击【已结束】【正在抢购】【即将开抢】，下方切换显示相应内容，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.checkGroup(driver));
    }
    
    //用例14：在惠民购物页中查看已结束列表，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase14(String userMobile, String password) {
        Reporter.log("惠民购物测试:在惠民购物页中查看已结束列表，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.clickFinished(driver));
    }    
   
    //用例15：在惠民购物页中查看即将开抢列表，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase15(String userMobile, String password) {
        Reporter.log("惠民购物测试:在惠民购物页中查看即将开抢列表，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.enterBeginGroupInfo(driver));
    }     
   
    //用例16：查看校验即将开抢列表中的各个商品显示信息，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase16(String userMobile, String password) {
        Reporter.log("惠民购物测试:查看校验即将开抢列表中的各个商品显示信息，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.judgeBeginGroupInfo(driver));
    } 

    //用例17：查看校验即将开抢列表中的各个商品的分享，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase17(String userMobile, String password) {
        Reporter.log("惠民购物测试:查看校验即将开抢列表中的各个商品的分享，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.judgeBeginGroupInfoShare(driver));
    }
    
    //用例18：查看校验即将开抢列表中的各个商品对应详情页的规格，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase18(String userMobile, String password) {
        Reporter.log("惠民购物测试:查看校验即将开抢列表中的各个商品对应详情页的规格，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.clickSpec(driver));
    }
    
    //用例19：在即将开抢列表中进入商品详情后，点击【立即抢购】按钮无效，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase19(String userMobile, String password) {
        Reporter.log("惠民购物测试:在即将开抢列表中进入商品详情后，点击【立即抢购】按钮无效，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.clickBuy(driver));
    }    
    
    //用例20：查看正在抢购列表，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase20(String userMobile, String password) {
        Reporter.log("惠民购物测试:查看正在抢购列表，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.enterOnGoingGroupInfo(driver));
    }    
    
    //用例21：查看正在抢购列表的各个商品显示信息，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase21(String userMobile, String password) {
        Reporter.log("惠民购物测试:查看正在抢购列表的各个商品显示信息，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.judgeOnGoingGroupInfo(driver));
    } 
    
    //用例22：查看正在抢购列表的各个商品的分享，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase22(String userMobile, String password) {
        Reporter.log("惠民购物测试:查看正在抢购列表的各个商品的分享，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.judgeOnGoingGroupInfoShare(driver));
    } 
  
    //用例23：查看正在抢购列表的各个商品的规格,未选择规格点击立即抢购，提示请选择规格，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase23(String userMobile, String password) {
        Reporter.log("惠民购物测试:查看正在抢购列表的各个商品的规格,未选择规格点击立即抢购，提示请选择规格，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.clickOnGoingSpec(driver));
    } 
    
    //用例24：查看正在抢购列表的各个商品的规格,选择规格点击立即抢购，提示前往登录，登录后进入订单确认界面，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase24(String userMobile, String password) {
        Reporter.log("惠民购物测试:查看正在抢购列表的各个商品的规格,选择规格点击立即抢购，提示前往登录，登录后进入订单确认界面，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        groupBuying.clickOnGoingSpec2(driver, userMobile, password);
        Assert.assertEquals("订单确认", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    }    
    
    //用例25：查看正在抢购列表的各个商品详情页的数量要与列表页一致，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase25(String userMobile, String password) {
        Reporter.log("惠民购物测试:查看正在抢购列表的各个商品详情页的数量要与列表页一致，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.checkPoductLimitedNum(driver));
    }
    
    //用例26：查看正在抢购列表的各个商品详情页的商品可选数量不得超过库存数，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase26(String userMobile, String password) {
        Reporter.log("惠民购物测试:查看正在抢购列表的各个商品详情页的商品可选数量不得超过库存数，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        Assert.assertTrue(groupBuying.checkPoductMaxNum(driver));
    }   
    
    //用例27：先登录，再查看正在抢购列表的各个商品详情页的商品，未选择规格点击立即抢购，提示请选择规格，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase27(String userMobile, String password) {
        Reporter.log("惠民购物测试:先登录，再查看正在抢购列表的各个商品详情页的商品，未选择规格点击立即抢购，提示请选择规格，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        Assert.assertTrue(groupBuying.groupBuyWithLoginWithOutSpec(driver, userMobile, password));
    }     
    
    //用例28：先登录，再查看正在抢购列表的各个商品详情页的商品，选择规格后点击立即抢购，进入订单确认界面，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase28(String userMobile, String password) {
        Reporter.log("惠民购物测试:先登录，再查看正在抢购列表的各个商品详情页的商品，选择规格后点击立即抢购，进入订单确认界面，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.groupBuyWithLoginWithSpec(driver, userMobile, password);
        Assert.assertEquals("订单确认", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    }

    //用例29：在已登录情况下，点击【商品描述】【商品参数】【保障说明】，下方切换显示相应内容，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase29(String userMobile, String password) {
        Reporter.log("惠民购物测试:在已登录情况下，点击【商品描述】【商品参数】【保障说明】，下方切换显示相应内容，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        Assert.assertTrue(groupBuying.groupBuyInfoWithLogin(driver, userMobile, password));
    }
    
    //用例30：在未登录情况下，进入详情页点击“立即抢购”提示登录，登录后，进入订单页面，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase30(String userMobile, String password) {
        Reporter.log("惠民购物测试:在未登录情况下，进入详情页点击“立即抢购”提示登录，登录后，进入订单页面，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        groupBuying.joinGroupBuyWithOutLogin(driver, userMobile, password);
        Assert.assertEquals("订单确认", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    } 
    
    //用例31：在未登录情况下，进入详情页点击“立即抢购”提示登录，登录后，进入订单页面，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase31(String userMobile, String password) {
        Reporter.log("惠民购物测试:在未登录情况下，进入详情页点击“立即抢购”提示登录，登录后，进入订单页面，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.joinGroupBuyWithLogin(driver, userMobile, password);
        Assert.assertEquals("订单确认", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    } 
        
    //用例32：在登录情况下，点击购物车弹出并显示“我的购物”字样，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase32(String userMobile, String password) {
        Reporter.log("惠民购物测试:在登录情况下，点击购物车弹出并显示“我的购物”字样，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        Assert.assertTrue(groupBuying.clickMyshoppingCart(driver, userMobile, password));
    } 
    
    //用例33：在登录情况下，点击弹出状态下的小车标志,弹出功能按钮，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase33(String userMobile, String password) {
        Reporter.log("惠民购物测试:在登录情况下，点击弹出状态下的小车标志,弹出功能按钮，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.clickCart(driver, userMobile, password);
        Assert.assertEquals("惠民购物", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    } 
    
    //用例34：在未登录情况下，点击小车后后弹出，并显示“我的购物”字样后，而后点击“我的购物”字样，再点击地址管理，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase34(String userMobile, String password) {
        Reporter.log("惠民购物测试:未登录情况下，点击小车后后弹出，并显示“我的购物”字样后，而后点击“我的购物”字样，再点击地址管理，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.preStep(driver, userMobile, password);
        groupBuying.clickCartWithOutLogin(driver, userMobile, password);
        Assert.assertEquals("我的订单", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    }  
    
    //用例35：在已登录情况下，点击小车后后弹出，并显示“我的购物”字样后，而后点击“我的购物”字样，进入相应页面，标签值为验证方式
    @Test(dataProvider="loginData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase35(String userMobile, String password) {
        Reporter.log("惠民购物测试:在已登录情况下，点击小车后后弹出，并显示“我的购物”字样后，而后点击“我的购物”字样，进入相应页面，标签值为验证方式");
        GroupBuying groupBuying = new GroupBuying();
        groupBuying.clickCartWithLogin(driver, userMobile, password);
        Assert.assertEquals("我的订单", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get(host);
        driver.manage().window().setSize(new Dimension(430, 700));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
    }  
    
    @AfterMethod
    public void afterMethod() {
        //关闭打开的浏览器 
        driver.quit();  
    } 

}