
package com.fotoplace.http.jsons;
import com.alibaba.fastjson.JSON;
import com.fotoplace.user.test.modl.UserLoginIT;
import com.fotoplace.user.test.modl.UserRegisterIT;

/**
 * 类UserServiceJSON.java的实现描述：TODO 类实现描述
 * 
 * @author 郭强 2015年7月24日 下午16:31:49
 */
public class UserServiceJSON 
{  
	 /**
	  * 
     * @param countryNo  国家编码
     * @param mobile     手机号
     * @param password   密码
     * @param captcha    验证码
     * @param clientVersion 客户端版本型号 
     * @param osType   手机型号 
     * @param seriesNumber  
     * 
     */
     public static String CreateUserServiceJson(String countryNo,String mobile,String password
    		                                   ,String captcha,String clientVersion,String osType,String seriesNumber){
    	 
        UserRegisterIT reg =  new UserRegisterIT();
        reg.setMobile(mobile);
        reg.setPassword(password);
        reg.setCaptcha(captcha);
        reg.setSeriesNumber(seriesNumber);
        reg.setOsType(osType);
        reg.setClientVersion(clientVersion);
        reg.setCountryNo(countryNo);
        
        
    	 String userJson =  JSON.toJSONString(reg, true);
    	 
    	 System.out.println("CreateUserServiceJson---"+userJson);
    			  	 
		 return userJson;
    	 
     }
     

	 /**
	  * 
     * @param username  用户名
     * @param password  密码
     * @param seriesNumber 序列号
     * @param clientVersion 客户端版本号
     * @param osType 手机型号
     */
     public static String UserloginJson(String userName,String password,String seriesNumber,String clientVersion,String osType){
    	 
    	 UserLoginIT loginreg = new UserLoginIT();
    	 loginreg.setUserName(userName);
    	 loginreg.setPassword(password);
    	 loginreg.setSeriesNumber(seriesNumber);
    	 loginreg.setClientVersion(clientVersion);
    	 loginreg.setOsType(osType);
    	 
    	 String userJson =  JSON.toJSONString(loginreg,true);
   	     System.out.println("userlogin----"+userJson);
   	     
		 return userJson;
    	 
     }
	
}
 
