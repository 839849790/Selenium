package orangelife.ut.homepage;

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

import orangelife.page.homepage.CityService;

/**
 * 首页城市服务测试
 * @author qihuan
 * 
 */
public class CityServiceTest {

    private static String host = "http://m.orangelife.com.cn/";    //定义驱动网址
    private static WebDriver driver;
    
    private String home_url = host + "#home";
    private String liveliHood_url = host + "#iframe/http%3A%2F%2Fs.orangelife.com.cn%2Fapp%2Fcomplaint_ns%2Findex.html/民生投诉/home";
    private String searchBus_url = host + "#iframe/http%3A%2F%2Fxxbs.sh.gov.cn%3A8080%2Fweixinpage%2Findex.html/公交查询/home";
    private String scenicSpot_url = host + "#iframe/http%3A%2F%2Flyjs.eastday.com%2Flyj%2FSearchSpots%2FSpots.html/景区客流/home";
    private String accumulationFund_url = host + "#iframe/http%3A%2F%2Fm.shgjj.com%2Fgjjwx%2Fweixin%2Faccount_personal/公积金/home";
    private String vegetablePrice_url = host + "#iframe/http%3A%2F%2Fwx.shdrc.gov.cn%2Ffgw%2Findex.php%3Fm%3DHome%26c%3DScjg%26a%3Dindex/菜价查询/home";
    private String lostAndFound_url = host + "#iframe/http%3A%2F%2Fwapapp.eastday.com%2Fswzl%2Findex.html/失物招领/home";
    private String squareDance_url = host + "#squareDance";
    private String moreNavInfo_url = host + "#moreNavInfo";
    private String invoice_url = host + "#iframe/https%3A%2F%2Fwww.tax.sh.gov.cn%2Fwsbs%2FWSBSptFpCx_loginsNewl_zfb_sj.jsp/发票查询/moreNavInfo";
    private String template_url = host + "#iframe/http%3A%2F%2Fsh.eastday.com%2Feastday%2Fshnews%2Fpdzt%2F24-Solar-Terms%2Findex_K24661.html/节气食经/moreNavInfo";
    private String marriedQuery_url = host + "#iframe/http%3A%2F%2Fapp3.shmzj.gov.cn%2FmarryWap%2Fshio%2Findex.jsp/结婚预约/moreNavInfo";
    private String creditCard_url = host + "#iframe/http%3A%2F%2Fwww.huishuaka.com%2F5%2Fcoop%2Findex.html%3FADTAG%3Ddfw-1%26ichannelid%3D282%26rnd%3D1/信用卡办理/moreNavInfo";

    //用例1：进入智橙生活首页，点击民生投诉icon后正常进入民生投诉页面，标签值为验证方式
    @Test
    public void testCase1() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击民生投诉后正常进入民生投诉页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 1);
        Assert.assertEquals("民生投诉", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2/span")).getText());
    }
    
    //用例2：进入民生投诉页面点击曝光台后，点击返回/主按钮后，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase2() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击民生投诉后正常进入民生投诉页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 1);
        cityService.livelihoodLighthouse(driver);
        cityService.clickHomeBtn(driver);
        Assert.assertEquals(liveliHood_url, driver.getCurrentUrl());
    }
    
    //用例3：正常进入民生投诉页面后点击返回按钮，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase3() {
        Reporter.log("首页城市服务测试:正常进入民生投诉页面后点击返回按钮，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 1);
        cityService.returnToHomePage(driver);
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }  
    
    //用例4：进入智橙生活首页，点击公交查询icon后正常进入公交查询页面，标签值为验证方式
    @Test
    public void testCase4() {
        Reporter.log("首页城市服务测试:正常进入民生投诉页面后点击返回按钮，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 2);
        Assert.assertEquals("公交查询", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2/span")).getText());
    }
    
    //用例5：进入智橙生活首页，点击公交查询icon后正常进入公交查询页面，标签值为验证方式
    @Test(dataProvider="busSearchData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase5(String busLine) {
        Reporter.log("首页城市服务测试:正常进入民生投诉页面后点击返回按钮，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 2);
        cityService.searchBus(driver, busLine);
        cityService.clickHomeBtn(driver);
        Assert.assertEquals(searchBus_url, driver.getCurrentUrl());
    }    
    
    //用例6：正常进入公交查询页面后点击返回按钮，正常返回上级页面，标签值为验证方式
    @Test(dataProvider="busSearchData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase6(String busLine) {
        Reporter.log("首页城市服务测试:正常进入公交查询页面后点击返回按钮，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 2);
        cityService.returnToHomePage(driver);
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }
        
    //用例7：进入智橙生活首页，点击景区客流icon后正常进入景区客流页面，标签值为验证方式
    @Test
    public void testCase7() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击景区客流icon后正常进入景区客流页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 3);
        Assert.assertEquals("景区客流", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2/span")).getText());
    }
    
    //用例8：进入景区客流页面点击第一个景点后，点击返回/主按钮后，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase8() {
        Reporter.log("首页城市服务测试:进入景区客流页面点击第一个景点后，点击返回/主按钮后，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 3);
        cityService.clickScenicSpot(driver);
        cityService.clickHomeBtn(driver);
        Assert.assertEquals(scenicSpot_url, driver.getCurrentUrl());
    }
    
    //用例9：正常进入景区客流页面后点击返回按钮，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase9() {
        Reporter.log("首页城市服务测试:正常进入景区客流页面后点击返回按钮，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 3);
        cityService.returnToHomePage(driver);
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }
    
    //用例10：进入智橙生活首页，点击公积金icon后正常进入公积金页面，标签值为验证方式
    @Test
    public void testCase10() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击公积金icon后正常进入公积金页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 4);
        Assert.assertEquals("公积金", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2/span")).getText());
    }
    
    //用例11：进入公积金页面点击注册按钮后，点击返回/主按钮后，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase11() {
        Reporter.log("首页城市服务测试:进入公积金页面点击注册按钮后，点击返回/主按钮后，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 4);
        cityService.clickForgotPsd(driver);
        cityService.clickHomeBtn(driver);
        Assert.assertEquals(accumulationFund_url, driver.getCurrentUrl());
    }
    
    //用例12：正常进入公积金页面后点击返回按钮，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase12() {
        Reporter.log("首页城市服务测试:正常进入公积金页面后点击返回按钮，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 4);
        cityService.returnToHomePage(driver);
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }
  
    //用例13：进入智橙生活首页，点击菜价查询icon后正常进入菜价查询页面，标签值为验证方式
    @Test
    public void testCase13() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击菜价查询icon后正常进入菜价查询页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 5);
        Assert.assertEquals("菜价查询", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2/span")).getText());
    }
    
    //用例14：进入菜价查询页面点击注册按钮后，点击返回/主按钮后，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase14() {
        Reporter.log("首页城市服务测试:进入菜价查询页面点击注册按钮后，点击返回/主按钮后，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 5);
        cityService.clickTodayPrice(driver);
        cityService.clickHomeBtn(driver);
        Assert.assertEquals(vegetablePrice_url, driver.getCurrentUrl());
    }
    
    //用例15：正常进入菜价查询页面后点击返回按钮，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase15() {
        Reporter.log("首页城市服务测试:正常进入菜价查询页面后点击返回按钮，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 5);
        cityService.returnToHomePage(driver);
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }
    
    //用例16：进入智橙生活首页，点击失物招领icon后正常进入失物招领页面，标签值为验证方式
    @Test
    public void testCase16() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击失物招领icon后正常进入失物招领页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 6);
        Assert.assertEquals("失物招领", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2/span")).getText());
    }
    
    //用例17：进入失物招领页面点击注册按钮后，点击返回/主按钮后，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase17() {
        Reporter.log("首页城市服务测试:进入失物招领页面点击注册按钮后，点击返回/主按钮后，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 6);
        cityService.clickHelpBtn(driver);
        cityService.clickHomeBtn(driver);
        Assert.assertEquals(lostAndFound_url, driver.getCurrentUrl());
    }
    
    //用例18：正常进入失物招领页面后点击返回按钮，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase18() {
        Reporter.log("首页城市服务测试:正常进入失物招领页面后点击返回按钮，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterCityServiceDetail(driver, 6);
        cityService.returnToHomePage(driver);
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }
    
    //用例19：进入智橙生活首页，点击广场舞icon后正常进入广场舞页面，标签值为验证方式
    @Test
    public void testCase19() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击广场舞icon后正常进入广场舞页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterSquareDance(driver, 7);
        Assert.assertEquals("广场舞", driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText());
    }
    
    //用例20：进入广场舞页面点击收藏按钮后，点击返回/主按钮后，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase20() {
        Reporter.log("首页城市服务测试:进入广场舞页面点击注册按钮后，点击返回/主按钮后，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterSquareDance(driver, 7);
        cityService.clickCollectBtn(driver);
        Assert.assertEquals(squareDance_url, driver.getCurrentUrl());
    }
    
    //用例21：正常进入广场舞页面后点击返回按钮，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase21() {
        Reporter.log("首页城市服务测试:正常进入广场舞页面后点击返回按钮，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterSquareDance(driver, 7);
        cityService.returnFromSquareDance(driver);
        Assert.assertEquals(home_url, driver.getCurrentUrl());
    }
    
    //用例22：进入智橙生活首页，点击全部后点击地铁查询icon后正常进入地铁查询页面，标签值为验证方式
    @Test
    public void testCase22() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击全部后点击地铁查询icon后正常进入地铁查询页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 9);
        Assert.assertEquals("地铁查询", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2")).getText());
    }
    
    //用例24：进入智橙生活首页，点击全部后点击地铁查询icon后正常进入地铁查询页面，而后点击返回按钮正常返回上级页面，标签值为验证方式
    @Test
    public void testCase24() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击全部后点击地铁查询icon后正常进入地铁查询页面，而后点击返回按钮正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 9);
        cityService.returnToHomePage2(driver);
        Assert.assertEquals(moreNavInfo_url, driver.getCurrentUrl());
    } 
    
    //用例25：进入智橙生活首页，点击全部后点击发票查询icon后正常进入发票查询页面，标签值为验证方式
    @Test
    public void testCase25() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击全部后点击发票查询icon后正常进入发票查询页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 10);
        Assert.assertEquals("发票查询", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2")).getText());
    }
    
    //用例26：进入击发票查询页面输入数据后点击查询，而后点击返回/主按钮后，正常返回上级页面，标签值为验证方式
    @Test(dataProvider="invoiceSearchData1",dataProviderClass=orangelife.dataProvider.DataProviderMethod.class)
    public void testCase26(String invoiceNo, String fphm, String revenueRegisterId) {
        Reporter.log("首页城市服务测试:进入击发票查询页面输入数据后点击查询，而后点击返回/主按钮后，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 10);
        cityService.enterInvoice(driver, invoiceNo, fphm, revenueRegisterId);
        cityService.returnToHomePage3(driver);
        Assert.assertEquals(invoice_url, driver.getCurrentUrl());
    }
    
    //用例27：进入智橙生活首页，点击全部后点击发票查询icon后正常进入发票查询页面，而后点击返回按钮正常返回上级页面，标签值为验证方式
    @Test
    public void testCase27() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击全部后点击发票查询icon后正常进入发票查询页面，而后点击返回按钮正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 10);
        cityService.returnToHomePage2(driver);
        Assert.assertEquals(moreNavInfo_url, driver.getCurrentUrl());
    }    
    
    //用例28：进入智橙生活首页，点击全部后点击节气食经icon后正常进入节气食经页面，标签值为验证方式
    @Test
    public void testCase28() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击全部后点击节气食经icon后正常进入节气食经页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 11);
        Assert.assertEquals("节气食经", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2")).getText());
    }
    
    //用例29：进入节气食经后点击“春分”，而后再点击home键，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase29() {
        Reporter.log("首页城市服务测试:进入节气食经后点击“春分”，而后再点击home键，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 11);
        cityService.enterTemplate(driver);
        cityService.returnToHomePage3(driver);
        Assert.assertEquals(template_url, driver.getCurrentUrl());
    }
    
    //用例30：进入智橙生活首页，点击全部后点击节气食经icon后正常进入节气食经页面，而后点击返回按钮正常返回上级页面，标签值为验证方式
    @Test
    public void testCase30() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击全部后点击节气食经icon后正常进入节气食经页面，而后点击返回按钮正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 11);
        cityService.returnToHomePage2(driver);
        Assert.assertEquals(moreNavInfo_url, driver.getCurrentUrl());
    } 
    
    //用例31：进入智橙生活首页，点击全部后点击结婚预约icon后正常进入结婚预约页面，标签值为验证方式
    @Test
    public void testCase31() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击全部后点击结婚预约icon后正常进入结婚预约页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 12);
        Assert.assertEquals("结婚预约", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2/span")).getText());
    }
    
    //用例32：进入击结婚预约后点击“预约查询”，而后再点击home键，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase32() {
        Reporter.log("首页城市服务测试:进入击结婚预约后点击“预约查询”，而后再点击home键，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 12);
        cityService.enterQuery(driver);
        cityService.returnToHomePage3(driver);
        Assert.assertEquals(marriedQuery_url, driver.getCurrentUrl());
    }
    
    //用例33：进入智橙生活首页，点击全部后点击结婚预约icon后正常进入结婚预约页面，而后点击返回按钮正常返回上级页面，标签值为验证方式
    @Test
    public void testCase33() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击全部后点击结婚预约icon后正常进入结婚预约页面，而后点击返回按钮正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 12);
        cityService.returnToHomePage2(driver);
        Assert.assertEquals(moreNavInfo_url, driver.getCurrentUrl());
    } 
 
    //用例34：进入智橙生活首页，点击全部后点击个税查询icon后正常进入个税查询页面，标签值为验证方式
    @Test
    public void testCase34() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击全部后点击个税查询icon后正常进入个税查询页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 13);
        Assert.assertEquals("个税查询", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2/span")).getText());
    }
    
    //用例36：进入智橙生活首页，点击全部后点击个税查询icon后正常进入个税查询页面，而后点击返回按钮正常返回上级页面，标签值为验证方式
    @Test
    public void testCase36() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击全部后点击个税查询icon后正常进入个税查询页面，而后点击返回按钮正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 13);
        cityService.returnToHomePage2(driver);
        Assert.assertEquals(moreNavInfo_url, driver.getCurrentUrl());
    }    
    
    //用例37：进入智橙生活首页，点击全部后点击信用卡办理icon后正常进入信用卡办理页面，标签值为验证方式
    @Test
    public void testCase37() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击全部后点击信用卡办理icon后正常进入信用卡办理页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 14);
        Assert.assertEquals("信用卡办理", driver.findElement(By.xpath("//*[@id='iframeHeader']/h2/span")).getText());
    }
    
    //用例38：进入信用卡办理后点击“进度查询”，而后再点击home键，正常返回上级页面，标签值为验证方式
    @Test
    public void testCase38() {
        Reporter.log("首页城市服务测试:进入信用卡办理后点击“进度查询”，而后再点击home键，正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 14);
        cityService.enterProgress(driver);
        cityService.returnToHomePage3(driver);
        Assert.assertEquals(creditCard_url, driver.getCurrentUrl());
    }
    
    //用例39：进入智橙生活首页，点击全部后点击信用卡办理icon后正常进入信用卡办理页面，而后点击返回按钮正常返回上级页面，标签值为验证方式
    @Test
    public void testCase39() {
        Reporter.log("首页城市服务测试:进入智橙生活首页，点击全部后点击信用卡办理icon后正常进入信用卡办理页面，而后点击返回按钮正常返回上级页面，标签值为验证方式");
        CityService cityService = new CityService();
        cityService.preStep(driver);
        cityService.enterAll(driver, 8);
        cityService.enterAllOfDetail(driver, 14);
        cityService.returnToHomePage2(driver);
        Assert.assertEquals(moreNavInfo_url, driver.getCurrentUrl());
    }
    
    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get(host);
        driver.manage().window().setSize(new Dimension(430, 700));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
    }  
    
    @AfterMethod
    public void afterMethod() {
        //关闭打开的浏览器 
        driver.quit();  
    } 

}
