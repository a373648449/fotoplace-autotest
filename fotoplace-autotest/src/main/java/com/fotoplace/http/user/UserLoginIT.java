package com.fotoplace.http.user;

import org.testng.Assert;

import com.alibaba.fastjson.JSON;
import com.fotoplace.http.jsons.UserServiceJSON;
import com.fotoplace.user.test.modl.UserLogin;
import com.pajk.test.client.JsonRequestUtil;
import com.pajk.test.client.ResultDO;
import com.pajk.test.database.DBInfo;

public class UserLoginIT {
	
	public static final String conn =  DBInfo.getConn("fotoplaceDB");
	public static final String serverIP = DBInfo.getConn("serverIP");
	
		 
      public void getUserLogin_normal() throws Exception {
    	
    	String userlogin_Json = UserServiceJSON.UserloginJson("13774294435", "123456");
    	
    	String url = "http://" + serverIP + "/api/user/user_login.php";
    	
    	ResultDO respone =  JsonRequestUtil.doPost(url, userlogin_Json);
    	
    	//判断http返回结果
     	int statusCode =  respone.getStatusCode();
    	Assert.assertEquals(statusCode, 200);
    	
        
    	String reg =  respone.getResultString();
    	UserLogin userlogin = new UserLogin();
    	userlogin = JSON.parseObject(reg, userlogin.getClass());
    	
    	Assert.assertEquals(userlogin.getEmailphone(), "13774294435");
    	Assert.assertEquals(userlogin.getPassword(), "123456");
    	
        String userJson =  JSON.toJSONString(userlogin, true);
        System.out.println("json" +userJson);
    	
    	
    	
    }
}
