package com.portal.common.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

import jxl.Workbook;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @ClassName: ExportExcelJxl 
 * @Description: 导出Excel工具类
 * @author Miao Wenqiang
 * @date 2016年10月29日 下午1:17:46
 */
public class ExportExcelJxl{
	private static final Logger logger = Logger.getLogger(ExportExcelJxl.class);
	//private static final int MAX_SHEETS = 10;
	private static final int MAX_ROWS = 65500;
	private WritableWorkbook wwb;
	private ExportBean exportBean;
	private HttpServletResponse response;
	private OutputStream out  ;
	
	public ExportExcelJxl(HttpServletResponse response,ExportBean exportBean) throws Exception{
		this.exportBean = exportBean;
		this.response = response;
		try {
			try {
				this.out = response.getOutputStream();
			} catch (IOException e) {
				logger.error("获取输出流异常", e);
			}
			createWorkbook();
			putData();
			setHeader("application/vnd.ms-excel");
			flush();
			closeWorkbook();
		} catch (Exception e) {
			setHeader("text/html;charset=UTF-8");
			try {
				response.getOutputStream().println(e.getMessage());
			} catch (IOException e1) {
			}
		}
	}
	private void flush() throws Exception{
		try {
			out.flush();
		} catch (IOException e) {
			logger.error("输出文件异常", e);
		}
	}
	// 设定输出文件头
	public void setHeader(String type) {
		response.setContentType(type);
		response.setHeader("Content-Disposition", "attachment; filename=" + toUTF8String(exportBean.getExcelName() + ".xls"));
		
	}
	public void createWorkbook() throws Exception {
		try {
			closeWorkbook();
			/**根据导出模式创建工作薄的对象*/
			if(exportBean.getExportMode() == 0){//直接导出
				wwb = Workbook.createWorkbook(out);
			}else if(exportBean.getExportMode() == 1){//按模板导出
				Workbook wb = Workbook.getWorkbook(new File(exportBean.getSourceFile()));
				wwb = Workbook.createWorkbook(out,wb);
			}else{
				wwb=null;
			}
		} catch (Exception e) {
			logger.error("创建工作薄异常", e);
		}
	}
	public void closeWorkbook() throws Exception {
		// 输出缓存并关闭wwb
		if (wwb != null) {
			try {
				wwb.write();
				wwb.close();
			} catch (Exception e) {
				logger.error("关闭工作薄异常",e);
				e.printStackTrace();
			} finally {
				wwb = null;
			}
		}
	}
	public boolean putData() throws Exception {
		if (wwb == null)
			throw new Exception("没有创建工作薄");
		int index = 0;
		Object[][] data = exportBean.getData();
		if (data == null || data.length==0)
			throw new Exception("没有可导出的数据");
		try {
				WritableSheet sheet = createSheet();
				if (sheet == null)
					throw new Exception("不能创建Sheet");
				int row = exportBean.getStartRow();
				for (; index < data.length; index++) {
					for (int col = 0; col < data[index].length; col++) {
						WritableCell cell = createCell(row, col, data[index][col]);
						sheet.addCell(cell);
					}
					// 如果超出一个工作表的限制，就停止
					if (++row >= MAX_ROWS)break;
				}
			return true;
		} catch (Exception e) {
			logger.error("导出数据时异常", e);
		}
		return false;
	}
	
	private WritableSheet createSheet() {
		WritableSheet sheet = null;
		if(exportBean.getExportMode() == 1){//按模板导出
			sheet = wwb.getSheet(0);
		}
		if(sheet == null){
			sheet = wwb.createSheet(exportBean.getSheetName(),0);
		}
		return sheet;
	}
	/**
	 * 根据data的格式和值，创建一个用于写入文件的单元格
	 * 
	 * @param row
	 *            单元格的行位置
	 * @param col
	 *            单元格的列位置
	 * @param data
	 *            数据
	 * @return 可写入的单元格
	 */
	private WritableCell createCell(int row, int col, Object data) {
		if (data == null)
			return new Blank(col, row);
		else if (data instanceof Date)
			return new Label(col, row, format((Date) data, "yyyy-MM-dd HH:mm:ss"));
		else
			return new Label(col, row, data.toString());
	}
	/**
	 * 把日期格式化为字符串
	 * 
	 * @param date
	 *            日期对象
	 * @param format
	 *            格式化字串
	 * @return 格式化的日期字符串
	 */
	public static String format(Date date, String format) {
		if (date == null || format == null)
			return null;

		return DateFormatUtils.format(date, format);
	}
	/**
	 * 把字符串转成utf8编码，保证中文文件名不会乱码
	 * @param s
	 * @return
	 */
	public String toUTF8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
	
}
