package com.portal.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.portal.common.exception.SystemException;

/**
 * 类名称：FileUtil 文件操作共通类
 * 内容摘要：对于文件的共通操作
 * @version 1.0 2011-9-6
 */
public class FileUtil {
    
	//日志
	public static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	//允许上传的后缀名
	public static String[] ALLOWEXTS = new String[]{"jpg","jpeg","gif","png"};
	
    /**
     * @Title: createFile
     * @Description: 新建文件
     * @param filePath 文件路径
     * @throws SystemException
     */
    public static void createFile(String filePath) throws SystemException {
        File file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new SystemException(e, "新建文件失败。");
        }

    }

    /**
     * @Title: createDir
     * @Description: 新建文件夹
     * @param filePath 文件路径
     */
    public static void createDir(String filePath) {
        File file = new File(filePath);
        file.mkdirs();
    }

    /**
     * @Title: deleteFile
     * @Description: 删除指定路径的文件
     * @param filePath 文件路径
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.isDirectory()) {
            file.delete();
        }
        else {
            deleteDir(file);
        }
    }

    /**
     * @Title: deleteDir
     * @Description: 递归删除目录所有内容
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            for (int i = 0; i < listFiles.length && deleteDir(listFiles[i]); i++) {
            }
        }
        return dir.delete();
    }

    /**
     * @Title: copyFile
     * @Description: 文件拷贝
     * @param resourceFimeName 源文件的路径名称
     * @param targetFileName 目的文件的路径名称
     * @return
     * @throws IOException
     */
    public static boolean copyFile(String resourceFimeName, String targetFileName) throws IOException {

        return copyFile(new File(resourceFimeName), new File(targetFileName));
    }

    /**
     * @Title: copyFile
     * @Description: 文件拷贝
     * @param resourceFimeName 源文件的路径名称
     * @param targetFile 目的文件
     * @return
     * @throws IOException
     */
    public static boolean copyFile(String resourceFimeName, File targetFile) throws IOException {
        return copyFile(new File(resourceFimeName), targetFile);
    }

    /**
     * @Title: copyFile
     * @Description: 文件拷贝
     * @param resourceFile 源文件
     * @param targetFileName 目的文件的路径名称
     * @return
     * @throws IOException
     */
    public static boolean copyFile(File resourceFile, String targetFileName) throws IOException {
        return copyFile(resourceFile, new File(targetFileName));
    }

    /**
     * @Title: copyFile
     * @Description: 文件拷贝
     * @param resourceFile 源文件
     * @param targetFile 目的文件
     * @return
     * @throws IOException
     */
    public static boolean copyFile(File resourceFile, File targetFile){
        if (resourceFile == null || targetFile == null)
            return false;
        try {
        	 if (resourceFile.exists()) {
                 if (!targetFile.exists()) {
                     File parentFile = targetFile.getParentFile();
                     if (!parentFile.exists())
                         parentFile.mkdirs();
                     targetFile.createNewFile();
                 }
                 FileInputStream in = new FileInputStream(resourceFile);
                 FileOutputStream out = new FileOutputStream(targetFile);
                 byte[] buffer = new byte[1024 * 8];
                 int i = 0;

                 while ((i = in.read(buffer)) != -1) {
                     out.write(buffer, 0, i);
                 }
                 out.flush();
                 in.close();
                 out.close();
                 return true;

             }
             else {
                 return false;
             }
		} catch (Exception e) {
		    logger.info("copyFile 出现异常!");
			return false;
		}
       
    }
    
    /**
     * @Title: getExt 
     * @Description: 获得图片后缀名
     * @param fullName
     * @return 
     * @return String
     * @throws
     */
	public static String getExt(String fullName) {
		if (StringUtils.isNotBlank(fullName)) {
			return fullName.substring(fullName.lastIndexOf(".") + 1, fullName .length()).toLowerCase();
		}
		return null;
	}
	
	/**
	 * @Title: flieUpload 
	 * @Description: 上传图片
	 * @param files  文件
	 * @param path  上传的路径
	 * @param request
	 * @return String
	 * @throws
	 */
	public static String flieUpload(MultipartFile[] files, String path, HttpServletRequest request) {
		String newName = null;
 		for (MultipartFile file : files) {
			if(!file.isEmpty()){
				//文件全名
				String fullName = file.getOriginalFilename();
				//文件后缀名
				String extStr = FileUtil.getExt(fullName);
				//判断是否该类型的文件允许上传
				if(!ArrayUtils.contains(ALLOWEXTS, extStr)){
				    logger.info(fullName + "上传文件的类型不允许!");
				}else{
					//生成文件名
					newName = StringUtil.getUUID() + "." + extStr;
					
					String target = request.getSession().getServletContext().getRealPath("/upload/"+path) + "/" + newName; 
					try {
						FileUtils.copyInputStreamToFile(file.getInputStream(), new File(target));
					} catch (IOException e) {
					    logger.info("上传图片出现错误");
						e.printStackTrace();
					}
				}
			}
		}
 		return newName ;
	}

}
