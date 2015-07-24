package com.fotoplace.http.user;
 import com.alibaba.fastjson.JSON;
import com.fotoplace.http.jsons.UserServiceJSON;
import com.fotoplace.user.test.modl.UserRegister;
import com.pajk.test.api.BaseTest;
import com.pajk.test.client.JsonRequestUtil;
import com.pajk.test.client.ResultDO;
import org.testng.Assert;
import org.testng.annotations.*;

 
public class AddUserServiceIT extends BaseTest{
	
	public static String serverIP="123.59.13.113:8180";
	
	   
	 /**
     * @return
     * @throws Exception
     */
    @Test(description = "新增用户信息-正常场景，创建人：郭强")
    public void addUser_normal() throws Exception{
    	 
    	String addUserJson =  UserServiceJSON.CreateUserServiceJson("12", "13774294435", "123456", "5623", "ios7.0", "ios", "123456");
    	
    	String URL ="http://" + serverIP + "/api/user/user_reg.php";
    
    	ResultDO  respone =  JsonRequestUtil.doPost(URL, addUserJson);
    	
    	int statusCode =  respone.getStatusCode();
    	Assert.assertEquals(statusCode, 200);
    	
    	String reg_json =  respone.getResultString();
    	
    	UserRegister userinfo = new UserRegister();
    	userinfo = JSON.parseObject(reg_json, UserRegister.class);
    	
        Assert.assertEquals(userinfo.getOsType(), "ios");    
        Assert.assertEquals(userinfo.getMobile(), "13774294435");
        Assert.assertEquals(userinfo.getPassword(), "");    
    	
    } 
	
	

}
