package com.portal.common.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @ClassName: ExcelUtil 
 * @Description: Excel操作工具类
 * @author Xia ZhengWei
 * @date 2016年8月30日 下午10:16:44
 */
public class ExcelUtil {
    
    /** 输出流类型 */
    private static final String CONTENT_TYPE = "application/vnd.ms-excel";

    /**
     * @Title: creatExcel 
     * @Description: 生成excel
     * @param templPath  模板的位置 例如excel/temp1.xls(实际就是/template/excel/temp1.xls)
     * @param list  传入的数据
     * @param targetFileName  生成的文件名字
     * @param request
     * @param response 
     * @return void
     * @throws
     */
	public static void creatExcel(String templPath, List<? extends Object> list, String targetFileName, 
	    					         HttpServletRequest request, HttpServletResponse response) {
        try {
        	//获取模板的路径
        	 String realPath = request.getSession().getServletContext().getRealPath("/template/"+templPath);
             // 取得模板文件流
             InputStream template = new FileInputStream(realPath);
             // 定义byte输出流
             ByteArrayOutputStream out = new ByteArrayOutputStream();
             //封装数据
             Map<String, Object> map = new HashMap<String, Object>();
             //填充数据
             map.put("list", list);
             // 取得模板文件对象
             Workbook workbook = new XLSTransformer().transformXLS(template, map);
             workbook.write(out);
             // 取得模板文件中的数据
             byte[] result = out.toByteArray();
             // 设置输出数据类型
             response.setContentType(CONTENT_TYPE);
             // 设置输出数据长度
             response.setContentLength(result.length);
             // 转换文件名称
             targetFileName = new String((targetFileName+".xls").getBytes("GBK"), "ISO-8859-1");
             // 设置文件名称
             response.setHeader("Content-Disposition", "attachment; filename=\"" + targetFileName + "\"");
             // 将文件流输出到画面
             response.getOutputStream().write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
