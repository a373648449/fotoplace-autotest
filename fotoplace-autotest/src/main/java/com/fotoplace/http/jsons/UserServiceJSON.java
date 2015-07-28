
package com.fotoplace.http.jsons;
import com.alibaba.fastjson.JSON;
import com.fotoplace.user.test.modl.NewPassword;
import com.fotoplace.user.test.modl.UserLogin;
import com.fotoplace.user.test.modl.UserRegister;
import com.fotoplace.user.test.modl.UserThirdLogin;

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
    	 
        UserRegister reg =  new UserRegister();
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
    	 
    	 UserLogin loginreg = new UserLogin();
    	 loginreg.setUserName(userName);
    	 loginreg.setPassword(password);
    	 loginreg.setSeriesNumber(seriesNumber);
    	 loginreg.setClientVersion(clientVersion);
    	 loginreg.setOsType(osType);
    	 
    	 String userJson =  JSON.toJSONString(loginreg,true);
   	     System.out.println("userlogin----"+userJson);
   	     
		 return userJson;
    	 
     }
     
     /**
     *@author guoqiang
	 *@see第三方登录
     *@param osType 手机型号
     *@param thirdPartType 第三方登录类型
     *@param thirdPartId 第三方登录ID
     *@param seriesNumber 序列号
     *@param clientVersion 客户端版本号
     *@param thirdPartName 第三方登录用户名
     *@param thirdPartAvatar 第三方登录头像
     *@param clintVersion 客户端版本号
     *@param seriesNumber 序列号
     */
     public static String UserThirdLoginJson(String osType,int thirdPartType,String thirdPartId,String authorization,String thirdPartName,
    		                                 String thirdPartAvatar,String clintVersion,String seriesNumber){
    	 
    	 
    	 UserThirdLogin userThirdReg= new UserThirdLogin();
    	 userThirdReg.setOsType(osType);
    	 userThirdReg.setThirdPartId(thirdPartId);
    	 userThirdReg.setThirdPartType(thirdPartType);
    	 userThirdReg.setThirdPartName(thirdPartName);
    	 userThirdReg.setThirdPartAvatar(thirdPartAvatar);
    	 userThirdReg.setAuthorization(authorization);
    	 userThirdReg.setClintVersion(clintVersion);
    	 userThirdReg.setOsType(osType);
    	 
    	 
    	 String userJson =  JSON.toJSONString(userThirdReg, true);
    	 System.out.println("UserThirdLogin------"+userJson);
    	 
		 return userJson;
    	 
    	 
     }
     
     public static String NewPasswordJson(String captcha,String password,String userName){
    	 
    	 NewPassword  newpassword = new NewPassword();
         newpassword.setCaptcha(captcha);
         newpassword.setPassword(password);
         newpassword.setUserName(userName);
         
         String newpassword_Json =  JSON.toJSONString(newpassword, true);
         System.out.println("newpassword-----" +newpassword_Json);
    	 
		 return newpassword_Json;
    	 
     }
	
}
 
