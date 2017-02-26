package orangelife.page.shopping;

import java.util.ArrayList;
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
 * 惠民服务员测试
 * @author 欢
 * 
 */
public class GroupBuying {

    private static Logger log = LoggerFactory.getLogger(GroupBuying.class);
    private String showEleClass = "swiper-slide swiper-slide-next";
    
    //前置步骤：用户选择小区，点击惠民后进入惠民购物页面
    public void preStep(WebDriver driver, String userMobile, String password) {
        log.info("前置步骤：用户选择小区，点击惠民后进入惠民购物页面");
        ChooseCommunity chooseCommunity = new ChooseCommunity();
        chooseCommunity.firChooseCommunity(driver);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        driver.findElement(By.xpath("//*[@id='pageFooter']/ul/li[3]/a")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
    }
    
    //查看顶部惠民团购
    public List<WebElement> getGroupBuyingList(WebDriver driver) {
        log.info("执行查看顶部惠民团购操作!");
        List<WebElement> groupBuyList = new ArrayList<WebElement>();
        WebElement group = driver.findElement(By.xpath("//*[@id='mallHome-content']/div[1]/div/div"));
        List<WebElement> groupList = group.findElements(By.tagName("div"));
        for(int i=0;i<groupList.size();i++){
            if(groupList.get(i).getAttribute("class").indexOf("swiper-slide") != -1){
                groupBuyList.add(groupList.get(i));
            }
        }
        return groupBuyList;
    }
    
    //打开当前显示的团购信息
    public boolean openGroupBuyInfo(WebDriver driver, List gorupList){
        log.info("执行打开当前显示的团购信息操作!");
        boolean flag = false;
        for(int i=1;i<=gorupList.size();i++){
            WebElement showEle = driver.findElement(By.xpath("//*[@id='mallHome-content']/div[1]/div/div/div["+i+"]"));
            String name = driver.findElement(By.xpath("//*[@id='mallHome-content']/div[1]/div/div/div["+ i +"]/a/div/div[2]/p[2]")).getText();
            if(showEleClass.equals(showEle.getAttribute("class"))){
                showEle.click();
                new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-groupBuyDetail")));
                String title = driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[1]/div[2]/div[1]")).getText();
                if(name.equals(title)){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    
    //点击【查看更多团购商品】
    public void openMoreGroupBuyInfo(WebDriver driver){
        log.info("执行点击【查看更多团购商品】操作!");
        driver.findElement(By.xpath("//*[@id='mallHome-content']/a/div/div[2]")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-groupBuy")));
    }
    
    //查看团购列表
    public List<WebElement> getMoreGroupBuyList(WebDriver driver){
        log.info("执行查看团购列表操作!");
        WebElement group = driver.findElement(By.id("scroll-container"));
        return group.findElements(By.tagName("a"));
    }
    
    //从【查看更多团购商品】返回
    public void returnFromMoreGroupBuyInfo(WebDriver driver){
        log.info("执行从【查看更多团购商品】返回操作!");
        driver.findElement(By.id("header-back")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
    }
    
    //获取第一项团购详情
    public void getFirstMoreGroupBuy(WebDriver driver){
        log.info("执行获取第一项团购详情操作!");
        driver.findElement(By.xpath("//*[@id='scroll-container']/a[1]/div/div[1]/img")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-groupBuyDetail")));
    }
    
    //查看参团买家列表信息
    public List<WebElement> getGroupBuyerInfo(WebDriver driver){
        log.info("执行查看参团买家列表信息操作!");
        List<WebElement> groupBuyerList = new ArrayList<WebElement>();
        WebElement groupBuyDetail = driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[2]/div[2]"));
        List<WebElement> groupList = groupBuyDetail.findElements(By.tagName("div"));
        for(int i=0;i<groupList.size();i++){
            if("row-flex  row-text".equals(groupList.get(i).getAttribute("class"))){
                groupBuyerList.add(groupList.get(i));
            }
        }
        return groupBuyerList;
    }
    
    //查看参团信息显示【成团缺少人数】
    public boolean getLackBuyerNum(WebDriver driver){
        log.info("执行查看参团信息显示【成团缺少人数】操作!");
        boolean flag = false;
        if(SeleniumUtil.isElementExist(driver, By.xpath("//*[@id='mall-groupBuyDetail']/div[1]/div[2]/div[3]"))){
            flag = true;
        }
        return flag;
    }
    
    //点击【商品描述】【商品参数】【保障说明】
    public boolean getSpecInfo(WebDriver driver){
        log.info("执行点击【商品描述】【商品参数】【保障说明】操作!");
        boolean flag = false;
        WebElement btn = driver.findElement(By.xpath("//*[@id='goods-tabId']/div[1]/ul/li[1]"));
        SeleniumUtil.mouseClick(driver, btn);
        if("active".equals(btn.getAttribute("class"))){
            flag = true;
        }
        return flag;
    }
    
    //分享判定
    public List<WebElement> judgeShare(WebDriver driver){
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.className("popup-share")));
        WebElement shareType = driver.findElement(By.className("share-type"));
        return shareType.findElements(By.tagName("li"));
    }
    
    //点击分享
    public boolean share(WebDriver driver){
        log.info("执行点击【分享】操作!");
        boolean flag = false;
        driver.findElement(By.xpath("//*[@id='mall-groupBuyDetail']/div[2]/div[1]/a")).click();
        List<WebElement> shareList = judgeShare(driver);
        if(shareList.size() == 4){
            flag = true;
        }
        return flag;
    }
    
    //在未登录情况下点击立刻参团
    public void joinGroup(WebDriver driver, String userMobile, String password){
        log.info("执行在未登录情况下点击【立刻参团】操作!");
        driver.findElement(By.id("goodsDetail-mBuy")).click();
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-createOrder")));
    }
    
    //在已登录情况下进入团购页面
    public void enterGroup(WebDriver driver, String userMobile, String password){
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        driver.findElement(By.id("home-firstLoginLayer")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("homeNowCommunity")));
        driver.findElement(By.xpath("//*[@id='pageFooter']/ul/li[3]/a")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
    }
    
    //在已登录情况下点击立刻参团
    public void joinGroup2(WebDriver driver, String userMobile, String password){
        log.info("执行在已登录情况下点击【立刻参团】操作!");
        enterGroup(driver, userMobile, password);
        openMoreGroupBuyInfo(driver);
        getFirstMoreGroupBuy(driver);
        driver.findElement(By.id("goodsDetail-mBuy")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-createOrder")));
    }
    
    //点击【已结束】【正在抢购】【即将开抢】
    public boolean checkGroup(WebDriver driver){
        log.info("执行点击【已结束】【正在抢购】【即将开抢】操作!");
        boolean flag = false;
        WebElement groupType = driver.findElement(By.xpath("//*[@id='navContainer']/div[2]/ul/li[2]"));
        SeleniumUtil.mouseClick(driver, groupType);
        if(groupType.getAttribute("class").contains("active")){
            flag = true;
        }
        return flag;
    }
    
    //在惠民购物页中查看已结束列表，标签值为验证方式
    public boolean clickFinished(WebDriver driver){
        log.info("执行在惠民购物页中查看已结束列表操作!");
        boolean flag = false;
        int num = 0;
        driver.findElement(By.xpath("//*[@id='navContainer']/div[2]/ul/li[1]")).click();
        WebElement finishedGroup = driver.findElement(By.id("limitList-container"));
        List<WebElement> finishedGroupList = finishedGroup.findElements(By.tagName("a"));
        for(int i=1;i<=finishedGroupList.size();i++){
            WebElement finishedGroupBtn = driver.findElement(By.xpath("//*[@id='limitList-container']/a["+i+"]/div/div[2]/div/div[2]/div"));
            if(finishedGroupBtn.getAttribute("class").contains("goodsBtn-gray")){
                finishedGroupBtn.click();
                new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
                num++;
            }
        }
        if(num == finishedGroupList.size()){
            flag = true;
        }
        return flag;
    }
    
    //进入即将开抢页面
    public List<WebElement> enterBegin(WebDriver driver){
        driver.findElement(By.xpath("//*[@id='navContainer']/div[2]/ul/li[3]")).click();
        WebElement beginGroup = driver.findElement(By.id("limitList-container"));
        return beginGroup.findElements(By.tagName("a"));
    }
    
    //在惠民购物页中查看即将开抢列表
    public boolean enterBeginGroupInfo(WebDriver driver){
        log.info("执行查看正在抢购列表操作!");
        List<WebElement> beginList = enterBegin(driver);
        return clickGroupInfo(driver, beginList);
    }
    
    //判定即将开抢页面的内容
    public boolean judgeBeginGroupInfo(WebDriver driver){
        log.info("执行判定即将开抢页面的内容操作!");
        List<WebElement> beginList = enterBegin(driver);
        return judgeGroupInfo(driver, beginList);
    }
    
    //查看即将开抢列表中的各个商品的分享功能
    public boolean judgeBeginGroupInfoShare(WebDriver driver){
        log.info("执行查看即将开抢列表中的各个商品的分享功能操作!");
        List<WebElement> beginList = enterBegin(driver);
        return judgeGroupInfoShare(driver, beginList);
    }
    
    //查看即将开抢列表中的各个商品对应详情页的规格
    public boolean clickSpec(WebDriver driver){
        log.info("执行查看即将开抢列表中的各个商品对应详情页的规格操作!");
        boolean flag = false;
        int num = 0;
        List<WebElement> beginGroupList = enterBegin(driver);
        for(int i=1;i<=beginGroupList.size();i++){
            driver.findElement(By.xpath("//*[@id='limitList-container']/a["+i+"]/div/div[2]/h3")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
            SeleniumUtil.mouseClick(driver, driver.findElement(By.id("select-spec")));
            if("产品详情".equals(driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText())){
                num++;
            }
            driver.findElement(By.id("header-back")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
        }
        if(num == beginGroupList.size()){
            flag = true;
        }
        return flag;
    }
    
    //查看即将开抢列表中的各个商品对应详情页的规格
    public boolean clickBuy(WebDriver driver){
        log.info("执行查看即将开抢列表中的各个商品对应详情页的规格操作!");
        boolean flag = false;
        int num = 0;
        List<WebElement> beginGroupList = enterBegin(driver);
        for(int i=1;i<=beginGroupList.size();i++){
            driver.findElement(By.xpath("//*[@id='limitList-container']/a["+i+"]/div/div[2]/h3")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
            WebElement mBuy = driver.findElement(By.id("goodsDetail-mBuy"));
            if(mBuy.getAttribute("class").contains("disabled")){
                mBuy.click();
                if("产品详情".equals(driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText())){
                    num++;
                }
                driver.findElement(By.id("header-back")).click();
                new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
            }
        }
        if(num == beginGroupList.size()){
            flag = true;
        }
        return flag;
    }

    //进入正在抢购页面
    public List<WebElement> enterOnGoing(WebDriver driver){
        driver.findElement(By.xpath("//*[@id='navContainer']/div[2]/ul/li[2]")).click();
        WebElement beginGroup = driver.findElement(By.id("limitList-container"));
        return beginGroup.findElements(By.tagName("a"));
    }
    
    //判定正在抢购页面正常和可进入详情页
    public boolean enterOnGoingGroupInfo(WebDriver driver){
        log.info("执行判定正在抢购页面正常和可进入详情页操作!");
        List<WebElement> onGoingList = enterOnGoing(driver);
        return clickGroupInfo(driver, onGoingList);
    }
    
    //判定正在抢购页面对应详情页的内容
    public boolean judgeOnGoingGroupInfo(WebDriver driver){
        log.info("执行判定正在抢购页面对应详情页的内容操作!");
        List<WebElement> onGoingList = enterOnGoing(driver);
        return judgeGroupInfo(driver, onGoingList);
    }
    
    //判定正在抢购页面对应详情页的分享
    public boolean judgeOnGoingGroupInfoShare(WebDriver driver){
        log.info("执行判定正在抢购页面对应详情页的分享操作!");
        List<WebElement> onGoingList = enterOnGoing(driver);
        return judgeGroupInfoShare(driver, onGoingList);
    } 
    
    //查看正在抢购列表中的各个商品对应详情页的规格,未选择规格点击立即抢购，提示请选择规格
    public boolean clickOnGoingSpec(WebDriver driver){
        log.info("执行查看正在抢购列表中的各个商品对应详情页的规格,未选择规格点击立即抢购，提示请选择规格操作!");
        boolean flag = false;
        int num = 0;
        List<WebElement> onGoingList = enterOnGoing(driver);
        for(int i=1;i<=onGoingList.size();i++){
            driver.findElement(By.xpath("//*[@id='limitList-container']/a["+i+"]/div/div[2]/h3")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
            SeleniumUtil.mouseClick(driver, driver.findElement(By.id("select-spec")));
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("selcetedSize-text")));
            if(SeleniumUtil.isElementExist(driver, By.xpath("//*[@id='select-popup']/div[2]/ul/li")) 
                    && SeleniumUtil.isElementExist(driver, By.id("goodsDetail-buy"))){
                driver.findElement(By.id("goodsDetail-buy")).click();
                if("产品详情".equals(driver.findElement(By.xpath("//*[@id='pageHeaderContainer']/div/h2")).getText())){
                    num++;
                }
            }
            SeleniumUtil.mouseClick(driver, driver.findElement(By.className("laymshade")));
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
            SeleniumUtil.mouseClick(driver,  driver.findElement(By.id("header-back")));
            if(SeleniumUtil.isElementExist(driver, By.id("mall-goodsDetail"))){
                SeleniumUtil.mouseClick(driver,  driver.findElement(By.id("header-back")));
            }
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
        }
        if(num == onGoingList.size()){
            flag = true;
        }
        return flag;
    }
    
    //查看正在抢购列表中的各个商品对应详情页的规格,选择规格点击立即抢购，提示前往登录，登录后进入订单确认界面
    public void clickOnGoingSpec2(WebDriver driver, String userMobile, String password){
        log.info("执行查看正在抢购列表中的各个商品对应详情页的规格,未选择规格点击立即抢购，提示请选择规格操作!");
        driver.findElement(By.xpath("//*[@id='navContainer']/div[2]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id='limitList-container']/a[1]/div/div[2]/h3")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
        SeleniumUtil.mouseClick(driver, driver.findElement(By.id("select-spec")));
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("selcetedSize-text")));
        driver.findElement(By.xpath("//*[@id='select-popup']/div[2]/ul/li")).click();
        driver.findElement(By.id("goodsDetail-buy")).click();
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-createOrder")));
    }
    
    //查看正在抢购列表的各个商品详情页的数量要与列表页一致
    public boolean checkPoductLimitedNum(WebDriver driver){
        log.info("执行查看正在抢购列表的各个商品详情页的数量要与列表页一致操作!");
        boolean flag = false;
        int num = 0;
        List<WebElement> onGoingList = enterOnGoing(driver);
        for(int i=1;i<=onGoingList.size();i++){
            String limitedNum = driver.findElement(By.xpath("//*[@id='limitList-container']/a["+i+"]/div/div[2]/div/div[1]/p[1]")).getText();
            driver.findElement(By.xpath("//*[@id='limitList-container']/a["+i+"]/div/div[2]/h3")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
            WebElement proLimitedNum = driver.findElement(By.xpath("//*[@id='mall-goodsDetail']/div[1]/div[1]/div[3]/div[3]/div[2]/div"));
            if(limitedNum.equals(proLimitedNum.getText().replace(" ", ""))){
                num++;
            }
            driver.findElement(By.id("header-back")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
        }
        if(num == onGoingList.size()){
            flag = true;
        }
        return flag;
    }
    
    //查看正在抢购列表的各个商品详情页的商品可选数量不得超过库存数
    public boolean checkPoductMaxNum(WebDriver driver){
        log.info("执行查看正在抢购列表的各个商品详情页的数量要与列表页一致操作!");
        boolean flag = false;
        int num = 0;
        List<WebElement> onGoingList = enterOnGoing(driver);
        for(int i=1;i<=onGoingList.size();i++){
            driver.findElement(By.xpath("//*[@id='limitList-container']/a["+i+"]/div/div[2]/h3")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
            WebElement proLimitedNum = driver.findElement(By.xpath("//*[@id='mall-goodsDetail']/div[1]/div[1]/div[3]/div[3]/div[2]/div"));
            List numList = splitOrderNum(proLimitedNum.getText().replace(" ", ""));
            int limitedNum = Integer.parseInt(numList.get(1).toString());
            
            SeleniumUtil.mouseClick(driver, driver.findElement(By.id("select-spec")));
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("selcetedSize-text")));
            WebElement changeNum = driver.findElement(By.xpath("//*[@id='change-num']/div[2]/div/input"));
            int orderNum = Integer.parseInt(changeNum.getAttribute("value"));
            int limitedMaxNum = canOrderNum(driver, orderNum, Integer.parseInt(numList.get(0).toString()));
            if(limitedNum==limitedMaxNum){
                num++;
            }
            SeleniumUtil.mouseClick(driver, driver.findElement(By.className("laymshade")));
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
            driver.findElement(By.id("header-back")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
        }
        if(num == onGoingList.size()){
            flag = true;
        }
        return flag;
    }
    
    
    //点击后查询可购买数量
    public int canOrderNum(WebDriver driver, int orderNum, int maxNum){
        driver.findElement(By.xpath("//*[@id='change-num']/div[2]/div/a[2]")).click();
        WebElement changeNum = driver.findElement(By.xpath("//*[@id='change-num']/div[2]/div/input"));
        int canOrderNum = Integer.parseInt(changeNum.getAttribute("value"));
        while(orderNum<canOrderNum){
            orderNum = canOrderNum;
            driver.findElement(By.xpath("//*[@id='change-num']/div[2]/div/a[2]")).click();
            canOrderNum = Integer.parseInt(changeNum.getAttribute("value"));
            if(orderNum>maxNum){
                break;
            }
        }
        return orderNum;
    }
    
    //数量字符串拆分,获取可购买数量
    public List splitOrderNum(String sourceStr){
        List numList = new ArrayList();
        String[] sourceStrArray = sourceStr.split("/");
        String maxNum = sourceStrArray[0].substring(2, sourceStrArray[0].length()-1);
        String finishedNum = sourceStrArray[1].substring(2, sourceStrArray[1].length()-1);
        int limitedNum = Integer.parseInt(maxNum)-Integer.parseInt(finishedNum);
        numList.add(Integer.parseInt(maxNum));
        numList.add(limitedNum);
        return numList;
    }

    //在已登录情况下，正在抢购列表中的各个商品对应详情页中,未选择规格点击立即抢购，提示请选择规格
    public boolean groupBuyWithLoginWithOutSpec(WebDriver driver, String userMobile, String password){
        log.info("执行在正在抢购列表中的各个商品对应详情页中,未选择规格点击立即抢购，提示请选择规格操作!");
        boolean flag = false;
        enterGroup(driver, userMobile, password);
        driver.findElement(By.xpath("//*[@id='navContainer']/div[2]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id='limitList-container']/a[1]/div/div[2]/h3")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
        driver.findElement(By.id("goodsDetail-mBuy")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("selcetedSize-text")));
        driver.findElement(By.id("goodsDetail-buy")).click();
        if(SeleniumUtil.isElementExist(driver, By.id("mall-goodsDetail"))){
            flag = true;
        }
        return flag;
    } 
    
    //在已登录情况下，在正在抢购列表中的各个商品对应详情页中,选择规格后点击立即抢购，进入订单确认界面
    public void groupBuyWithLoginWithSpec(WebDriver driver, String userMobile, String password){
        log.info("执行在正在抢购列表中的各个商品对应详情页中,选择规格后点击立即抢购，进入订单确认界面操作!");
        enterGroup(driver, userMobile, password);
        driver.findElement(By.xpath("//*[@id='navContainer']/div[2]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id='limitList-container']/a[1]/div/div[2]/h3")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
        driver.findElement(By.id("goodsDetail-mBuy")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("selcetedSize-text")));
        driver.findElement(By.xpath("//*[@id='select-popup']/div[2]/ul/li")).click();
        driver.findElement(By.id("goodsDetail-buy")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-createOrder")));
    }    
    
    //在已登录情况下，在正在抢购列表中的各个商品对应详情页中,点击【商品描述】【商品参数】【保障说明】
    public boolean groupBuyInfoWithLogin(WebDriver driver, String userMobile, String password){
        log.info("执行在已登录情况下，在正在抢购列表中的各个商品对应详情页中,点击【商品描述】【商品参数】【保障说明】操作!");
        boolean flag = false;
        enterGroup(driver, userMobile, password);
        driver.findElement(By.xpath("//*[@id='navContainer']/div[2]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id='limitList-container']/a[1]/div/div[2]/h3")).click();
        WebElement btn = driver.findElement(By.xpath("//*[@id='detail-tab']/div[1]/ul/li[1]"));
        SeleniumUtil.mouseClick(driver, btn);
        if("active".equals(btn.getAttribute("class"))){
            flag = true;
        }
        return flag;
    }
    
    //在未登录情况下，进入详情页点击“立即抢购”提示登录，登录后，进入订单页面
    public void joinGroupBuyWithOutLogin(WebDriver driver, String userMobile, String password){
        log.info("执行在未登录情况下，进入详情页点击“立即抢购”提示登录，登录后，进入订单页面操作!");
        driver.findElement(By.xpath("//*[@id='navContainer']/div[2]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id='limitList-container']/a[1]/div/div[2]/h3")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
        driver.findElement(By.id("goodsDetail-mBuy")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("selcetedSize-text")));
        driver.findElement(By.xpath("//*[@id='select-popup']/div[2]/ul/li")).click();
        driver.findElement(By.id("goodsDetail-buy")).click();
        Login login = new Login();
        login.testLogin(driver, userMobile, password);
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-createOrder")));
    }
    
    //在已登录情况下，进入详情页点击“立即抢购”提示登录，登录后，进入订单页面
    public void joinGroupBuyWithLogin(WebDriver driver, String userMobile, String password){
        log.info("执行在未登录情况下，进入详情页点击“立即抢购”提示登录，登录后，进入订单页面操作!");
        enterGroup(driver, userMobile, password);
        driver.findElement(By.xpath("//*[@id='navContainer']/div[2]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id='limitList-container']/a[1]/div/div[2]/h3")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
        driver.findElement(By.id("goodsDetail-mBuy")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("selcetedSize-text")));
        driver.findElement(By.xpath("//*[@id='select-popup']/div[2]/ul/li")).click();
        driver.findElement(By.id("goodsDetail-buy")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-createOrder")));
    }
    
    
    
    //在登录情况下，点击购物车弹出并显示“我的购物”字样
    public boolean clickMyshoppingCart(WebDriver driver, String userMobile, String password){
        log.info("执行在登录情况下，点击购物车弹出并显示“我的购物”字样操作!");
        boolean flag = false;
        enterGroup(driver, userMobile, password);
        WebElement shoppingCart = driver.findElement(By.xpath("//*[@id='to-myShopping']/div"));
        shoppingCart.click();
        if(shoppingCart.getAttribute("class").contains("active")){
            flag = true;
        }
        return flag;
    }
    
    //弹出并显示“我的购物”字样后，点击小车
    public void clickCart(WebDriver driver, String userMobile, String password){
        log.info("执行弹出并显示“我的购物”字样后，点击小车操作!");
        enterGroup(driver, userMobile, password);
        WebElement shoppingCart = driver.findElement(By.xpath("//*[@id='to-myShopping']/div"));
        shoppingCart.click();
        if(shoppingCart.getAttribute("class").contains("active")){
            shoppingCart.click();
            if(SeleniumUtil.isElementExist(driver, By.id("toList-btn"))){
                SeleniumUtil.mouseClick(driver, driver.findElement(By.id("mall-home")));   
            }
        }
    }
    
    //未登录情况下，点击小车后后弹出，并显示“我的购物”字样后，而后点击“我的购物”字样，再点击地址管理
    public void clickCartWithOutLogin(WebDriver driver, String userMobile, String password){
        log.info("执行未登录情况下，点击小车后后弹出，并显示“我的购物”字样后，而后点击“我的购物”字样，再点击地址管理操作!");
        WebElement shoppingCart = driver.findElement(By.xpath("//*[@id='to-myShopping']/div"));
        shoppingCart.click();
        if(shoppingCart.getAttribute("class").contains("active")){
            shoppingCart.click();
            if(SeleniumUtil.isElementExist(driver, By.id("toList-btn"))){
                driver.findElement(By.xpath("//*[@id='toList-btn']/div[1]/a")).click();
                Login login = new Login();
                login.testLogin(driver, userMobile, password);
            }
        }
    }
    
    //已登录情况下，点击小车后后弹出，并显示“我的购物”字样后，而后点击“我的购物”字样，进入相应页面
    public void clickCartWithLogin(WebDriver driver, String userMobile, String password){
        log.info("执行已登录情况下，点击小车后后弹出，并显示“我的购物”字样后，而后点击“我的购物”字样，进入相应页面操作!");
        enterGroup(driver, userMobile, password);
        WebElement shoppingCart = driver.findElement(By.xpath("//*[@id='to-myShopping']/div"));
        shoppingCart.click();
        if(shoppingCart.getAttribute("class").contains("active")){
            shoppingCart.click();
            if(SeleniumUtil.isElementExist(driver, By.id("toList-btn"))){
                driver.findElement(By.xpath("//*[@id='toList-btn']/div[1]/a")).click();
                new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-orderList")));
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //通用方法：点击团购商品名称进入详情页面
    public boolean clickGroupInfo(WebDriver driver, List<WebElement> list){
        boolean flag = false;
        int num = 0;
        for(int i=1;i<=list.size();i++){
            WebElement titleElement = driver.findElement(By.xpath("//*[@id='limitList-container']/a["+i+"]/div/div[2]/h3"));
            String title = titleElement.getText();
            titleElement.click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
            if(title.equals(driver.findElement(By.xpath("//*[@id='mall-goodsDetail']/div[1]/div[1]/div[3]/div[1]")).getText())){
                num++;
            }
            driver.findElement(By.id("header-back")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
        }
        if(num == list.size()){
            flag = true;
        }
        return flag;
    }   
    
    //通用方法：判定正在抢购页面的内容
    public boolean judgeGroupInfo(WebDriver driver, List<WebElement> list){
        log.info("执行判定正在抢购页面的内容操作!");
        boolean flag = false;
        int num = 0;
        for(int i=1;i<=list.size();i++){
            driver.findElement(By.xpath("//*[@id='limitList-container']/a["+i+"]/div/div[2]/h3")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
            if(SeleniumUtil.isElementExist(driver, By.xpath("//*[@id='mall-goodsDetail']/div[1]/div[1]/div[1]/a/img"))
                    && SeleniumUtil.isElementExist(driver, By.id("limitTimeShow"))
                    && SeleniumUtil.isElementExist(driver, By.xpath("//*[@id='mall-goodsDetail']/div[1]/div[1]/div[3]/div[1]"))
                    && SeleniumUtil.isElementExist(driver, By.xpath("//*[@id='mall-goodsDetail']/div[1]/div[1]/div[3]/div[3]/div[1]/span/span"))
                    && SeleniumUtil.isElementExist(driver, By.xpath("//*[@id='mall-goodsDetail']/div[1]/div[1]/div[3]/div[3]/div[1]/del"))
                    && SeleniumUtil.isElementExist(driver, By.xpath("//*[@id='mall-goodsDetail']/div[1]/div[1]/div[3]/div[3]/div[2]/div"))){
                num++;
            }
            driver.findElement(By.id("header-back")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
        }
        if(num == list.size()){
            flag = true;
        }
        return flag;
    }
    
    //通用方法：查看详情页下各个商品的分享功能
    public boolean judgeGroupInfoShare(WebDriver driver, List<WebElement> list){
        log.info("执行查看详情页下各个商品的分享功能操作!");
        boolean flag = false;
        int num = 0;
        for(int i=1;i<=list.size();i++){
            driver.findElement(By.xpath("//*[@id='limitList-container']/a["+i+"]/div/div[2]/h3")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
            driver.findElement(By.id("goodsDetail-share")).click();
            List<WebElement> shareList = judgeShare(driver);
            if(shareList.size() == 4){
                num++;
            }
            driver.findElement(By.className("laymshade")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-goodsDetail")));
            driver.findElement(By.id("header-back")).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mall-home")));
        }
        if(num == list.size()){
            flag = true;
        }
        return flag;
    } 
}
