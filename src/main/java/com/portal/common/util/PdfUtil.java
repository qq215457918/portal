package com.portal.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class PdfUtil {
	private static Logger logger = LoggerFactory.getLogger(PdfUtil.class);
    /** 输出流类型 */
    private static final String CONTENT_TYPE = "application/pdf";

    /**
     * @Title: creatPdf 
     * @Description: 生成Pdf
     * @param templPath  模板的位置 例如excel/temp1.xls(实际就是/template/excel/temp1.xls)
     * @param list  传入的数据
     * @param targetFileName  生成的文件名字
     * @param request
     * @param response 
     * @return void
     * @throws
     */
	public static void creatPdf(String templPath, List<? extends Object> list, String targetFileName, 
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
	
	
	/**
     * 默认执行方法,通过输出文件流实现导出Excel
     * @author 闫冬
     */
    protected void doExecute(String templPath, String targetFileName, Map<Object,Object > map,
						         HttpServletRequest request, HttpServletResponse response) throws Exception{
    	 //获得openoffice 转换连接
    	OpenOfficeConnection connection = null ;
    	 // 取得模板文件流
        InputStream template = null ;
         // 定义byte输出流 (excel输出)
        ByteArrayOutputStream out = null;
        
        //流转换(输出流转换成输入流)
        ByteArrayInputStream inputStream = null ;
        //response 输出流
        OutputStream outputStream = null; 
        try {

            // 取得模板文件流
            template = new FileInputStream(request.getSession().getServletContext().getRealPath("/template/"+templPath));
           
            // 定义byte输出流
            out = new ByteArrayOutputStream();
            // 取得模板文件对象
            Workbook workbook = new XLSTransformer().transformXLS(template, map);
            workbook.write(out);
            //流转换(输出流转换成输入流)
            inputStream = new ByteArrayInputStream(out.toByteArray());
    		// 获得文件格式
    		DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();
    		//pdf格式
    		DocumentFormat pdfFormat = formatReg.getFormatByFileExtension("pdf");
    		//excel格式
    		DocumentFormat xlsFormat = formatReg.getFormatByFileExtension("xls");
    		// stream 流的形式 响应输入流
    		outputStream = response.getOutputStream();
            //获得openoffice 转换连接
            connection = new SocketOpenOfficeConnection( 8100);
            //打开连接
    		connection.connect();
    		//转换器对象
    		DocumentConverter converter = new OpenOfficeDocumentConverter( connection);
    		//开始转换
    		converter.convert(inputStream, xlsFormat, outputStream, pdfFormat);
    		//关闭连接对象
    		connection.disconnect();
            // 取得模板文件中的数据
            String result = outputStream.toString();
            // 设置输出数据类型
            response.setContentType(CONTENT_TYPE);
            // 设置输出数据长度
            response.setContentLength(result.length());
            // 设置文件名称
            response.setHeader("Content-Disposition", "inline; filename=\"" + targetFileName + "\"");
            // 将文件流输出到画面
            response.getOutputStream().write(result.getBytes());	
		} catch(ConnectException e){
			logger.error("oppen office 转换环境没有开启 参考:进入open office 安装目录program 下执行soffice -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard");
			logger.error(e.getMessage());
			if(connection != null){
				connection.disconnect();
			}
			if(null != template){
				template.close();
			}
			if(null != out){
				out.close();
			}
			if(null != inputStream){
				inputStream.close();
			}
			if(null != outputStream){
				outputStream.close();
			}
		}catch (Exception e) {
			logger.error("pdf文件生成错误!");
			logger.error(e.getMessage());
			if(connection != null){
				connection.disconnect();
			}
			if(null != template){
				template.close();
			}
			if(null != out){
				out.close();
			}
			if(null != inputStream){
				inputStream.close();
			}
			if(null != outputStream){
				outputStream.close();
			}
		}finally{
			if(connection != null){
				connection.disconnect();
			}
			if(null != template){
				template.close();
			}
			if(null != out){
				out.close();
			}
			if(null != inputStream){
				inputStream.close();
			}
			if(null != outputStream){
				outputStream.close();
			}
		}
    }

}
