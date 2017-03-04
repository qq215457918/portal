package com.portal.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import jxl.read.biff.BiffException;

public interface ImportExcelUtil {

	/**
	 * 解析导入的excel文件，返回数据集
	 * @param FileInputStream fis Excel文件流
	 * @param int sheetNum 导入工作薄sheet号，默认为0
	 * @param int beginRow 导入数据开始行行号，从0开始，默认为1
	 * @param int beginCol 导入数据开始列，从0开始，默认为0
	 * @param int colspan 导入列表共有多少列
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> readXLSDocument(InputStream fis,int sheetNum,int beginRow,int beginCol,int colspan)
			throws Exception;
	/**
	 * 解析导入的excel文件，返回数据集
	 * @param File file Excel文件
	 * @param int sheetNum 导入工作薄sheet号，默认为0
	 * @param int beginRow 导入数据开始行行号，从0开始，默认为1
	 * @param int beginCol 导入数据开始列，从0开始，默认为0
	 * @param int colspan 导入列表共有多少列
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> readXLSDocument(File file,int sheetNum,int beginRow,int beginCol,int colspan)
			throws Exception;
	/**
	 * 解析导入的excel文件，返回数据集
	 * @param FileInputStream fis Excel文件流，默认读取第一个sheet数据
	 * @param int beginRow 导入数据开始行行号，第0行为表头，默认从1开始读取数据
	 * @param int colspan 导入列表共有多少列,默认从第一列开始导入
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> readXLSDocument(InputStream fis, int beginRow, int colspan) throws Exception ;
	/**
	 * 解析导入的excel文件，返回数据集
	 * @param File file Excel文件
	 * @param int beginRow 导入数据开始行行号，第0行为表头，默认从1开始读取数据
	 * @param int colspan 导入列表共有多少列,默认从第一列开始导入
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> readXLSDocument(File file, int beginRow, int colspan) throws Exception ;
	
	/**
	 * @Title: readXLSDocument2 
	 * @Description: 解析后台导入的空白客户Excel文件, 并存储到List结果集中
	 * @param files 解析文件
	 * @param area 所属区域
	 * @author Xia ZhengWei
	 * @date 2017年2月18日 下午10:24:45 
	 * @version V1.0
	 * @throws 
	 */
	public void readXLSDocument(MultipartFile[] files, String area) throws IOException, BiffException;
}
