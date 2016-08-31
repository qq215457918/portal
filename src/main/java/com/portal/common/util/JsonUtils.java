package com.portal.common.util;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@SuppressWarnings("rawtypes")
public class JsonUtils {
	
	/**
	 * 
	 *方法名:outJsonString
	 *返回值:void
	 *作     用:按照字符串方式输出json
	 *参     数:@param str
	 *参     数:@param response
	 *作     者:夏胜春
	 *日     期:2014年11月18日 下午1:51:55
	 */
	public static void outJsonString(String str,HttpServletResponse response) {
		try {
			response.setContentType("text/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *方法名:outJsonStringHtml
	 *返回值:void
	 *作     用:按照html方式输出json
	 *参     数:@param str
	 *参     数:@param response
	 *作     者:夏胜春
	 *日     期:2014年11月18日 下午1:52:25
	 */
	public static void outJsonStringHtml(String str,HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *方法名:outJsonStringHtml
	 *返回值:void
	 *作     用:按照页面打印方式输出json
	 *参     数:@param str
	 *参     数:@param response
	 *作     者:夏胜春
	 *日     期:2014年11月18日 下午1:52:25
	 */
	public  static void outJsonStringText(String str,HttpServletResponse response) {
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *方法名:setSuccess
	 *返回值:JSONObject
	 *作     用:设置成功
	 *参     数:@return
	 *作     者:夏胜春
	 *日     期:2014年11月19日 上午9:17:51
	 */
	public static JSONObject setSuccess(){
		JSONObject json = new JSONObject();
		json.put("status",1);
		return json;
	}
	/**
	 * 
	 *方法名:setError
	 *返回值:JSONObject
	 *作     用:失败方法
	 *参     数:@return
	 *作     者:夏胜春
	 *日     期:2014年11月19日 上午9:18:18
	 */
	public static JSONObject setError(){
		JSONObject json = new JSONObject();
		json.put("status",0);
		return json;
	}
	
	
	/**
	 * 
	 *方法名:json2string
	 *返回值:String
	 *作     用:json 转换成string
	 *参     数:@param json
	 *参     数:@return
	 *作     者:夏胜春
	 *日     期:2014年11月19日 上午9:35:38
	 */
	public static String json2string(JSONObject json){
		if(json == null){
			json = new JSONObject();
		}
		return json.toString();
	}
	/**
	 * 
	 *方法名:list2json
	 *返回值:JSONArray
	 *作     用:集合转换成json
	 *参     数:@param list
	 *参     数:@return
	 *作     者:夏胜春
	 *日     期:2014年11月19日 上午9:37:31
	 */
	public static JSONArray list2json( List list){
		if(list == null){
			list = new JSONArray();
		}
		return JSONArray.fromObject(list);
	}
	

	
}
