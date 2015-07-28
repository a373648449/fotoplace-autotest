package com.fotoplace.http.user;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSON;
import com.fotoplace.http.jsons.UserServiceJSON;
import com.fotoplace.user.test.modl.UserThirdLogin;
import com.pajk.test.client.JsonRequestUtil;
import com.pajk.test.client.ResultDO;
import com.pajk.test.database.DBInfo;

public class UserThirdLoginIT {
	
	
	private static String conn =  DBInfo.getConn("conn");
	private static String serverIP =DBInfo.getConn("serverIP");
	
	
	@Test(description = "第三方登录-正常场景，创建人：郭强")	 
    public void UserThirdLogin_normal()throws Exception{
        
		String userThirdJson= UserServiceJSON.UserThirdLoginJson("ios7.3", 2, "3344", "dengxs", "北极星"
				                                                , "http://www.qq.com", "2.3.7", "2");
    	String url  = "http://" + serverIP + "/api/user/user_third_login.php";
		
    	ResultDO response = JsonRequestUtil.doPost(url, userThirdJson);
    	
    	int statuscode = response.getStatusCode();
    	Assert.assertEquals(statuscode, 200);
    	
    	String reg = response.getResultString();
    	UserThirdLogin userThird = new UserThirdLogin();
        userThird = JSON.parseObject(reg, userThird.getClass());
        Assert.assertEquals(userThird.getOsType(),"ios7.3");
        Assert.assertEquals(userThird.getThirdPartType(), 2);
        Assert.assertEquals(userThird.getThirdPartId(),"3344");
    	Assert.assertEquals(userThird.getAuthorization(), "dengxs");
    	Assert.assertEquals(userThird.getThirdPartName(), "北极星");
    	Assert.assertEquals(userThird.getThirdPartAvatar(), "http://www.qq.com");
    	Assert.assertEquals(userThird.getSeriesNumber(), "2");
    	Assert.assertEquals(userThird.getClintVersion(), "2.3.7");
    	
    	String userThird_json =  JSON.toJSONString(userThird, true);
    	System.out.println("josn+++" + userThird_json);
    	
    	
    }
   	

}
