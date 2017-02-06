package com.rapd4j.constants;

/**
 * 常量类
 * 
 * @project:IntegralSystem
 * @class:constants
 * @description：
 * @author:xuzn
 * @date:2017-1-22 下午1:52:03
 * @modify:
 * @version:
 * 
 */
public interface constants {

	// 充值消息
	public final String CMSG = "01";
	// 赠送消息
	public final String ZMSG = "02";
	// 点赞消息
	public final String DMSG = "03";
	// 反馈消息
	public final String FMSG = "04";
	// 消息已读
	public final String ISREAD_YES = "01";
	// 消息未读
	public final String ISREAD_NO = "02";
	// 文件上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/";
	//切割图像上传路径
	public static final String IMAGEPATHFILE = "uploadFiles/cutImage/";
	//图片上传路径
	public static final String IMAGEPATH = "uploadFiles/imageFile/";
	//导入EXCEL数据的上限
	public static final int READEXCELNUM = 1000;
}
