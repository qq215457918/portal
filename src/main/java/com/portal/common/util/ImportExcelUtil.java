package com.portal.common.util;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

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
}
