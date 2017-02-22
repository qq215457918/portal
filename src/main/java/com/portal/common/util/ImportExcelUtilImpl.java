package com.portal.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.portal.bean.CustomerInfo;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Service
public class ImportExcelUtilImpl implements ImportExcelUtil {

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
			throws Exception {
		//从输入流读取EXCEL文件
		Workbook workBook = null;
		List<Map<String, Object>> list = null;
		try {
			workBook = Workbook.getWorkbook(fis);
			//获取EXCEL文件工作薄内容，从sheet0开始
			Sheet sheet = workBook.getSheet(sheetNum); 
			list = readXLSDocumentBySheet(sheet,beginRow,beginCol,colspan);
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				if(fis!=null){fis.close();}
			} catch (IOException e) {
				throw new Exception("关闭文件流失败");
			}
		}
		return list;
	}

	/**
	 * 解析导入的excel文件，返回数据集
	 * @param FileInputStream fis Excel文件流，默认读取第一个sheet数据
	 * @param int beginRow 导入数据开始行行号，第0行为表头，默认从1开始读取数据
	 * @param int colspan 导入列表共有多少列,默认从第一列开始导入
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> readXLSDocument(InputStream fis, int beginRow,
			int colspan) throws Exception {
		//从输入流读取EXCEL文件
		Workbook workBook = null;
		List<Map<String, Object>> list = null;
		try {
			workBook = Workbook.getWorkbook(fis);
			//获取EXCEL文件工作薄内容，从sheet0开始
			Sheet sheet = workBook.getSheet(0); 
			list = readXLSDocumentBySheet(sheet,beginRow,0,colspan);
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				if(fis!=null){fis.close();}
			} catch (IOException e) {
				throw new Exception("关闭文件流失败");
			}
		}
		return list;
	}

	/**
	 * 从sheet中读取数据，返回数据集
	 * @param Sheet sheet 导入工作薄sheet
	 * @param int beginRow 导入数据开始行行号，从0开始，默认为1
	 * @param int beginCol 导入数据开始列，从0开始，默认为0
	 * @param int colspan 导入列表共有多少列
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	private List<Map<String, Object>> readXLSDocumentBySheet(Sheet sheet, int beginRow, int beginCol, int colspan)throws Exception  {
		int rows = sheet.getRows(); // 得到表里的行数
		if(rows< beginRow+1){
			throw new Exception("没有可导入的数据");
		}
		List<Map<String, Object>> dataSet = new ArrayList<Map<String, Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Cell cell = null;
		String content = null;
		for (int i = beginRow; i < rows; i++) {
			Map<String, Object> data = new HashMap<String, Object>();
			for(int j=beginCol, k = 0;j<colspan+beginCol;j++, k++){
				cell = sheet.getCell(j,i);
				if(cell.getType() == CellType.DATE){
					content = sdf.format(((DateCell)cell).getDate());
				}else{
					content = cell.getContents();
					if(content.indexOf("空白") > -1){
						content = "0";
					}else if(content.indexOf("登门") > -1){
						content = "1";
					}else if(content.indexOf("说明") > -1){
						content = "2";
					}else if(content.indexOf("成单") > -1){
						content = "3";
					}else if(content.indexOf("锁定") > -1){
						content = "4";
					}else if(content.indexOf("介绍") > -1){
						content = "5";
					}
				}
				if(content.indexOf("\r\n") > -1){
					content = content.replace("\r\n", "\\n");
				}
				if(content.indexOf("\n") > -1){
					content = content.replace("\n", "\\n");
				}
				data.put("p" + k, content);
			}
			dataSet.add(data);
		}
		return dataSet;
	}
	
	public List<Map<String, Object>> readXLSDocument(File file, int sheetNum,
			int beginRow, int beginCol, int colspan) throws Exception {
		//从输入流读取EXCEL文件
		Workbook workBook = null;
		List<Map<String, Object>> list = null;
		FileInputStream fis = null;
		try {
			if(file == null){
				throw new Exception("导入文件为空");
			}
        	fis = new FileInputStream(file);
			workBook = Workbook.getWorkbook(fis);
			//获取EXCEL文件工作薄内容，从sheet0开始
			Sheet sheet = workBook.getSheet(sheetNum); 
			list = readXLSDocumentBySheet(sheet,beginRow,beginCol,colspan);
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				if(fis!=null){fis.close();}
			} catch (IOException e) {
				throw new Exception("关闭文件流失败");
			}
		}
		return list;
	}

	public List<Map<String, Object>> readXLSDocument(File file, int beginRow, int colspan)
			throws Exception {
		//从输入流读取EXCEL文件
		Workbook workBook = null;
		List<Map<String, Object>> list = null;
		FileInputStream fis = null;
		try {
        	fis = new FileInputStream(file);
			workBook = Workbook.getWorkbook(fis);
			//获取EXCEL文件工作薄内容，从sheet0开始
			Sheet sheet = workBook.getSheet(0); 
			list = readXLSDocumentBySheet(sheet,beginRow,0,colspan);
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				if(fis!=null){fis.close();}
			} catch (IOException e) {
				throw new Exception("关闭文件流失败");
			}
		}
		return list;
	}
	
	/**
     * @Title: readXLSDocument2 
     * @Description: 解析后台导入的空白客户Excel文件, 并存储到数据库中
     * @param files 解析文件
     * @param result 返回结果
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2017年2月18日 下午10:24:45 
     * @version V1.0
	 * @throws IOException, BiffException 
     */
    public Map<String, Object> readXLSDocument(MultipartFile[] files, Map<String, Object> customers) throws IOException, BiffException {
        // 文件输入流
        InputStream inputStream = null;
        //从输入流读取EXCEL文件
        Workbook workBook = null;
        Sheet sheet = null;
        Cell cell = null;
        int rows = 0;
        // 电话号码
        String tellPhone = "";
        // 拨打时间
        String callTime = "";
        //Map<String, String> data = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CustomerInfo customerInfo = null;
        try {
            for (MultipartFile multipartFile : files) {
                // 循环获取文件对应的输入流
                inputStream = multipartFile.getInputStream();
                // 获取文件工作薄
                workBook = Workbook.getWorkbook(inputStream);
                //获取EXCEL文件工作薄内容，从sheet0开始
                sheet = workBook.getSheet(0);
                // 获取文件的行数
                rows = sheet.getRows();
                // 如果文件内容为空, 则继续读取下一个文件
                if(rows < 2) {
                    continue;
                }
                // 循环获取每行的内容并存入到数据库中
                for (int i = 1; i < rows; i++) {
                    // 获取电话号码
                    tellPhone = sheet.getCell(0, i).getContents();
                    // 获取拨打时间
                    cell = sheet.getCell(1,i);
                    if(StringUtil.isNotBlank(cell.getContents())) {
                        if(cell.getType() == CellType.DATE){
                            callTime = sdf.format(((DateCell)cell).getDate());
                        }else {
                            callTime = cell.getContents();
                        }
                    }
                    // 如果电话号码为空, 继续下一行
                    if(StringUtil.isNull(tellPhone)) {
                        continue;
                    }
                    
                    // 判断map是否包含次电话号码, 如果包含, 判断拨打时间是否重复, 不重复追加
                    // 根据电话查询map中是否存在
                    Object object = customers.get(tellPhone);
                    if(object != null) {
                        // 存在， 重新赋值
                        customerInfo = (CustomerInfo) object;
                        if(StringUtil.isNotBlank(customerInfo.getCallDates())) {
                            // 如果原来的拨打时间包含该时间则忽略
                            if(!customerInfo.getCallDates().contains(callTime)) {
                                customerInfo.setCallDates(customerInfo.getCallDates() + "," + callTime);
                            }
                        }else {
                            customerInfo.setCallDates(callTime);
                        }
                    }else {
                        // 不存在
                        customerInfo = new CustomerInfo();
                        customerInfo.setPhone(tellPhone);
                        customerInfo.setCallDates(callTime);
                        customers.put(tellPhone, customerInfo);
                    }
                }
            }
        } catch (IOException e) {
            throw e;
        } catch (BiffException e) {
            throw e;
        } finally{
            try {
                if(inputStream!=null){inputStream.close();}
            } catch (IOException e) {
                throw e;
            }
        }
        return customers;
    }

}
