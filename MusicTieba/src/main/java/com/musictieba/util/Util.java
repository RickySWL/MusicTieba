package com.musictieba.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Util {
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Collection c) {
		if (c == null || c.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static String now() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String Time = sdf.format(now);
		return Time;
	}
	
	public static String Time() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String Time = sdf.format(now);
		return Time;
	}

	public static String getSuffix(String oldName) {
		int PoiIndex = oldName.lastIndexOf(".");
		String Suffix = oldName.substring(PoiIndex);
		return Suffix;
	}

	/**
	 * 储存文件
	 * 
	 * @author RickySun
	 * @param pathUrl
	 *            存储地址
	 * @param fileName
	 *            文件名
	 * @param suffix
	 *            文件后缀
	 * @param mf
	 *            临时文件
	 * @param folder
	 *            存放文件夹
	 * @return 存放地址
	 */
	public static String saveFile(String pathUrl, String fileName, String suffix, MultipartFile mf, String folder)
			throws Exception {
		String newFileName = fileName + suffix;
		File f = new File(pathUrl + "/" + newFileName);
		f.createNewFile();
		mf.transferTo(f);
		String Url = folder + newFileName;
		return Url;
	}

	public static boolean isPhoto(String Suffix) {
		String tail=Suffix.toLowerCase();
		String[] tails = { ".jpg", ".jpeg", ".png", ".gif", ".bmp" };
		for (int i = 0; i < tails.length; i++) {
			if (tails[i].equals(tail)) {
				return true;
			}
		}
		return false;
	}
	
}
