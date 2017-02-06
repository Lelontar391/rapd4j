//package com.liaobo.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.liaobo.constants.constants;
//import com.liaobo.entity.Salary;
//import com.liaobo.service.SalaryService;
//import com.liaobo.util.FileUpload;
//import com.liaobo.util.PathUtil;
//import com.liaobo.util.ReadExcelUtil;
//
//
//
///**
// * 导入数据库
// * 
// * @project:IntegralSystem
// * @class:RExcelController
// * @description：
// * @author:xuzn
// * @date:2017-1-22 下午12:46:49
// * @modify:
// * @version:
// * 
// */
//@Controller
//@RequestMapping(value = "/test")
//public class RExcelController {
//
//	private static final Log logger = 
//			LogFactory.getLog(RExcelController.class);
//	
//	@Autowired
//	private SalaryService salaryService;
//
//	/**
//	 * 从EXCEL导入到数据库
//	 */
//	@RequestMapping(value = "/readExcel")
//	public String readExcel(
//			/**
//			 * @RequestParam(value = "excel")
//			 * <input type="file" name="excel">
//			 * 这两个name必须一致 
//			 */
////			<input type="file" name="excel"> 
//			@RequestParam(value = "excel", required = false) MultipartFile file)
//			throws Exception {
//		if (null != file && !file.isEmpty()) {
//			String filePath = PathUtil.getClasspath() + constants.FILEPATHFILE; // 文件上传路径
//			String fileName = FileUpload.fileUp(file, filePath, "excel"); // 执行上传
//
//			List<Map<String, Object>> list = ReadExcelUtil.readExcel(filePath,
//					fileName, 2, 0, 0); 
//
//			Salary salary = new Salary();
//			/**
//			 * "id", var0 "uid", var1 "name", var2 "dept", var3 "jop", var4
//			 * "salary", var5 "award_salary", var6 "sum_salary", var7 "created",
//			 * var8 "updated" var9
//			 */
//
//			boolean flag = false;
//			for (int i = 0; i < list.size(); i++) {
//				if (list.size() <= constants.READEXCELNUM) {
//					salary.setUid(list.get(i).get("var" + 1).toString());
//					salary.setName(list.get(i).get("var" + 2).toString());
//					salary.setDept(list.get(i).get("var" + 3).toString());
//					salary.setJob(list.get(i).get("var" + 4).toString());
//					salary.setSalary(list.get(i).get("var" + 5).toString());
//					salary.setAwardSalary(list.get(i).get("var" + 6).toString());
//					salary.setSumSalary(list.get(i).get("var" + 7).toString());
//					System.out.println(list.get(i).get("var" + 0));
//					salaryService.txInsertSalary(salary);
//				}else {
//					return "ErrorExcel";
//				}
//				
//			}
//		}
//		return "ReadExcelSuccess";
//	}
//
//	@RequestMapping("goReadExcle")
//	public String goReadExcle(HttpServletRequest request,
//			HttpServletResponse response) {
//		return "UploadExcel";
//	}
//}
