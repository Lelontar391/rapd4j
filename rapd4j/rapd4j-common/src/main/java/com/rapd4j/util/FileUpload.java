package com.rapd4j.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件
 * 
 * @project:IntegralSystem
 * @class:FileUpload
 * @description：
 * @author:xuzn
 * @date:2017-1-22 上午9:39:51
 * @modify:
 * @version:
 * 
 */
public class FileUpload {

	/**
	 * @param file
	 *            //文件对象
	 * @param filePath
	 *            //上传路径
	 * @param fileName
	 *            //文件名
	 * @return 文件名
	 */
	public static String fileUp(MultipartFile file, String filePath,
			String fileName) {
		String extName = ""; // 扩展名格式：
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0) {
				extName = file.getOriginalFilename().substring(
						file.getOriginalFilename().lastIndexOf("."));
			}
			copyFile(file.getInputStream(), filePath, fileName + extName)
					.replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName + extName;
	}

	/**
	 * 写文件到当前目录的upload目录中
	 * 
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private static String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}

	/*********************************cutimage*************************************/
	public static final List<String> ALLOW_TYPES = Arrays.asList("image/jpg",
			"image/jpeg", "image/png", "image/gif");

	// 文件重命名
	public static String rename(String fileName) {
		int i = fileName.lastIndexOf(".");
		String str = fileName.substring(i);
		return new Date().getTime() + "" + new Random().nextInt(99999999) + str;
	}

	// 校验文件类型是否是被允许的
	public static boolean allowUpload(String postfix) {
		return ALLOW_TYPES.contains(postfix);
	}
}
