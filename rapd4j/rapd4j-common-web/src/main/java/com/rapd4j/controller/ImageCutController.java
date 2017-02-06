package com.rapd4j.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rapd4j.constants.constants;
import com.rapd4j.util.FileUpload;
import com.rapd4j.util.PathUtil;




@Controller
@RequestMapping("cImage")
public class ImageCutController {

	private static final Log logger = 
			LogFactory.getLog(ImageCutController.class);

	@RequestMapping(value = "/uploadCImage")
	public String uploadHeadImage(HttpServletRequest request,
			@RequestParam(value = "x") String x,
			@RequestParam(value = "y") String y,
			@RequestParam(value = "h") String h,
			@RequestParam(value = "w") String w,
			/**
			 * @RequestParam(value = "imgFile")
			 * <input class="photo-file" type="file" 
			 * name="imgFile" id="fcupload"
			 * onchange="readURL(this);" />
			 */
			@RequestParam(value = "imgFile") MultipartFile imageFile)
			throws Exception {
		System.out.println("==========Start=============");
		String realPath = PathUtil.getClasspath();
		String resourcePath = constants.IMAGEPATHFILE;
		if (imageFile != null) {
			if (FileUpload.allowUpload(imageFile.getContentType())) {
				String fileName = FileUpload.rename(imageFile
						.getOriginalFilename());
				int end = fileName.lastIndexOf(".");
				String saveName = fileName.substring(0, end);
				File dir = new File(realPath + resourcePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File file = new File(dir, saveName + "_src.jpg");
				imageFile.transferTo(file);
				String srcImagePath = realPath + resourcePath + saveName;
				int imageX = Integer.parseInt(x);
				int imageY = Integer.parseInt(y);
				int imageH = Integer.parseInt(h);
				int imageW = Integer.parseInt(w);
				// 这里开始截取操作
				System.out.println("==========imageCutStart=============");
				imgCut(srcImagePath, imageX, imageY, imageW, imageH);
				System.out.println("==========imageCutEnd=============");
			}
		}
		return "cutImageSuccess";
	}

	/**
	 * 截取图片
	 * 
	 * @param srcImageFile
	 *            原图片地址
	 * @param x
	 *            截取时的x坐标
	 * @param y
	 *            截取时的y坐标
	 * @param desWidth
	 *            截取的宽度
	 * @param desHeight
	 *            截取的高度
	 */
	public static void imgCut(String srcImageFile, int x, int y, int desWidth,
			int desHeight) {
		try {
			Image img;
			ImageFilter cropFilter;
			BufferedImage bi = ImageIO
					.read(new File(srcImageFile + "_src.jpg"));
			int srcWidth = bi.getWidth();
			int srcHeight = bi.getHeight();
			if (srcWidth >= desWidth && srcHeight >= desHeight) {
				Image image = bi.getScaledInstance(srcWidth, srcHeight,
						Image.SCALE_DEFAULT);
				cropFilter = new CropImageFilter(x, y, desWidth, desHeight);
				img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(desWidth, desHeight,
						BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, null);
				g.dispose();
				// 输出文件
				ImageIO.write(tag, "JPEG", new File(srcImageFile + "_cut.jpg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("goCImage")
	public String goCImage(HttpServletRequest request,HttpServletResponse
			response) {
		
		return "ImageCut";
	}
}
