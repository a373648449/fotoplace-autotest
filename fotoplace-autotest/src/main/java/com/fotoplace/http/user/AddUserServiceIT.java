package com.fotoplace.http.user;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.fotoplace.http.jsons.UserServiceJSON;
import com.fotoplace.user.test.modl.UserRegister;
import com.pajk.test.api.BaseTest;
import com.pajk.test.client.JsonRequestUtil;
import com.pajk.test.client.ResultDO;
import com.pajk.test.database.DBInfo;

import org.testng.Assert;
import org.testng.annotations.*;


 
public class AddUserServiceIT extends BaseTest{
	
	 public final String serverIP = DBInfo.getConn("serverIP");
	 public final String conn =  DBInfo.getConn("conn");
 
/*	 
   @BeforeMethod
   public void beforemethod() throws Exception{
	   
	    String paysql = "delete from user_profile where mobile='13774294435' ";
	   DBOperate.execute(conn, paysql);
	   Thread.sleep(2000);
	    d
   }  
	 */
    @Test(description = "新增用户-正常场景，创建人：郭强")
    public void addUser_normal() throws Exception{
    	
    	
        String addUserJson =  UserServiceJSON.CreateUserServiceJson("12", "13774294435", "123456", "5623", "ios7.0", "ios", "3345");
    	
    	String URL ="http://" + serverIP + "/api/user/user_reg.php";
    	HttpSession session = null;
    	
    	ResultDO  respone = JsonRequestUtil.doPost(URL, addUserJson);
   
    	//判断http返回结果
     	int statusCode =  respone.getStatusCode();
    	Assert.assertEquals(statusCode, 200);
    	
    	
    	String reg_json =  respone.getResultString();
    	UserRegister userinfo = new UserRegister();
    	userinfo = JSON.parseObject(reg_json, UserRegister.class);
    	
    	//assert校验返回字段
        Assert.assertEquals(userinfo.getOsType(), "ios");    
        Assert.assertEquals(userinfo.getMobile(), "13774294435");
        Assert.assertEquals(userinfo.getPassword(), "123456");
        Assert.assertEquals(userinfo.getCountryNo(), "12");
        Assert.assertEquals(userinfo.getCaptcha(), "5623");
        Assert.assertEquals(userinfo.getSeriesNumber(), "3345");
        Assert.assertEquals(userinfo.getClientVersion(), "ios7.0");	
   
        String userJson =  JSON.toJSONString(userinfo, true);
        if(userJson!=null){
        
            System.out.println("json" +userJson);

        }
        
    }
}
