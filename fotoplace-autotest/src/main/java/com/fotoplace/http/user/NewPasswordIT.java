package com.fotoplace.http.user;

import org.testng.Assert;

import com.alibaba.fastjson.JSON;
import com.fotoplace.http.jsons.UserServiceJSON;
import com.fotoplace.user.test.modl.NewPassword;
import com.pajk.test.client.JsonRequestUtil;
import com.pajk.test.client.ResultDO;
import com.pajk.test.database.DBInfo;

public class NewPasswordIT {
	
	public static String conn =  DBInfo.getConn("conn");
	public static String serverIP = DBInfo.getConn("serverIP");

	
	
	public void newPassword_normal() throws Exception{
		
		String newPsswordJson =  UserServiceJSON.NewPasswordJson(101006, "101006","23332" );
		String url =  "http://" + serverIP + "/api/user/newpassword.php";
		
		
		ResultDO respons =  JsonRequestUtil.doPost(url, newPsswordJson);
		int statuscode =  respons.getStatusCode();
		Assert.assertEquals(statuscode,  200);
		
		NewPassword newpassword =  new NewPassword();
	    Assert.assertEquals(newpassword.getUid(),"101006");
	    Assert.assertEquals(newpassword.getUserName(), "101006");
	    Assert.assertEquals(newpassword.getPassword(), "23332");
	    
	    String newPassword_json = JSON.toJSONString(newPsswordJson, true);
	    System.out.println("newPassword+++" + newPassword_json);
		
		
	}
}
