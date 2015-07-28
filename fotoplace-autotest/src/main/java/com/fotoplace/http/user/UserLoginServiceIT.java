package com.fotoplace.http.user;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.fotoplace.http.jsons.UserServiceJSON;
import com.fotoplace.user.test.modl.UserLogin;
import com.pajk.test.client.JsonRequestUtil;
import com.pajk.test.client.ResultDO;
import com.pajk.test.database.DBInfo;

public class UserLoginServiceIT {
	
	public static final String conn =  DBInfo.getConn("fotoplaceDB");
	public static final String serverIP = DBInfo.getConn("serverIP");
	
	 @Test(description = "用户登录-正常场景，创建人：郭强")	 
      public void UserLogin_normal() throws Exception {
    	
    	String userlogin_Json = UserServiceJSON.UserloginJson("13774294435", "123456","23","2.1.2","Android4.3.3");
    	
    	String url = "http://" + serverIP + "/api/user/user_login.php";
    	
    	ResultDO respone =  JsonRequestUtil.doPost(url, userlogin_Json);
    	
    	//判断http返回结果
     	int statusCode =  respone.getStatusCode();
    	Assert.assertEquals(statusCode, 200);
    	
        
    	String reg =  respone.getResultString();
    	UserLogin userlogin =  new UserLogin();
    	userlogin = JSON.parseObject(reg, userlogin.getClass());
    	
    	Assert.assertEquals(userlogin.getUserName(), "13774294435");
    	Assert.assertEquals(userlogin.getPassword(), "123456");
    	Assert.assertEquals(userlogin.getSeriesNumber(), "23");
    	Assert.assertEquals(userlogin.getOsType(), "Android4.3.3");
    	Assert.assertEquals(userlogin.getClientVersion(), "2.1.2");
    	
        String userJson =  JSON.toJSONString(userlogin, true);
        System.out.println("json" +userJson);
    	
        
    	
    	
    }
}
