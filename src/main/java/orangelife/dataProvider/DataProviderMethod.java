package orangelife.dataProvider;

import org.testng.annotations.DataProvider;

public class DataProviderMethod {

    //登录参数
    @DataProvider(name="loginData1")
    public static Object[][] loginData1(){
        return new Object[][]{{"18721102610", "123456"}};};
    
    @DataProvider(name="loginData2")
    public static Object[][] loginData2(){
        return new Object[][]{{"18721102610", "1234567"}};};
        
    @DataProvider(name="loginData3")
    public static Object[][] loginData3(){
        return new Object[][]{{"18721102611", "123456"}};};

    @DataProvider(name="loginData4")
    public static Object[][] loginData4(){
        return new Object[][]{{"18721102611", "1234567"}};};
        
    @DataProvider(name="loginData5")
    public static Object[][] loginData5(){
        return new Object[][]{{"18721102610", "123456", "411522198912156614"}};};
    
    //页脚测试:正确的用户名密码
    @DataProvider(name="pageFooterData")
    public static Object[][] pageFooterData(){
        return new Object[][]{{"18721102610", "123456"}};};

        
    //惠民服务员测试：申请用户
    @DataProvider(name="waiterData1")
    public static String[][] waiterData1() {
        return new String[][] {{"18721102610", "123456", "齐欢"}};
    };
        
    //惠民服务员测试:服务地址
    @DataProvider(name="waiterData2")
    public static String[][] waiterData2() {
        return new String[][] {{"18721102610", "123456", "普陀区中江路879弄"}};
    };
    
    //惠民服务员测试:邀请码
    @DataProvider(name="waiterData3")
    public static String[][] waiterData3() {
        return new String[][] {{"18721102610", "123456", "1314"}};
    };
    
    //惠民服务员测试:身份证号
    @DataProvider(name="waiterData4")
    public static String[][] waiterData4() {
        return new String[][] {{"18721102610", "123456", "411522198912156614"}};
    };
    
    //惠民服务员测试:备注信息
    @DataProvider(name="waiterData5")
    public static String[][] waiterData5() {
        return new String[][] {{"18721102610", "123456", "备注信息"}};
    };

    //惠民服务员测试:服务员注册
    @DataProvider(name="waiterData6")
    public static String[][] waiterData6() {
        return new String[][] {{"18721102610", "123456", "齐欢", "普陀区中江路879弄", "411522198912156614", "备注信息", "839849790@qq.com"}};
    };
    
    //惠民服务员申请审核测试：
    //1、审核中使用：loginData1
    //2、审核未通过：
    @DataProvider(name="auditData2")
    public static String[][] auditData2() {
        return new String[][] {{"18321035379", "123456"}};
    };
    //3、审核通过,首次登陆
    @DataProvider(name="auditData3")
    public static String[][] auditData3() {
        return new String[][] {{"18717823029", "123456"}};
    };
    //3、审核通过,非首次登陆
    @DataProvider(name="auditData4")
    public static String[][] auditData4() {
        return new String[][] {{"13817425177", "123456"}};
    };
    
    //公交查询
    @DataProvider(name="busSearchData1")
    public static String[][] busSearchData1() {
        return new String[][] {{"89"}};
    };
    
    //发票查询
    @DataProvider(name="invoiceSearchData1")
    public static String[][] invoiceSearchData1() {
        return new String[][] {{"1234567890", "12345678", "123"}};
    };
}
