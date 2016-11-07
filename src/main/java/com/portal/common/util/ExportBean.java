package com.portal.common.util;
/**
 * @ClassName: ExportExcelJxl 
 * @Description: 导出Excel工具类
 * @author Miao Wenqiang
 * @date 2016年10月29日 下午1:17:46
 */
public class ExportBean {

	private String excelName = null;//导出文件名
	private String sheetName = "Sheet1";//导出文件sheet名
	private String sourceFile = null;//按模板导出时制定模板路径,全路径
	private int startRow = 0;//按模板导出时需要指定写入数据起始行号
	private int exportMode = 0;//0直接导出 1按模板导出
	private Object[][] data = null;//导出数据集
	
	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getExportMode() {
		return exportMode;
	}
	public void setExportMode(int exportMode) {
		this.exportMode = exportMode;
	}
	public Object[][] getData() {
		return data;
	}
	public void setData(Object[][] data) {
		this.data = data;
	}
}
