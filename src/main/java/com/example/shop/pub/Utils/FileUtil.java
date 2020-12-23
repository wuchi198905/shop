package com.example.shop.pub.Utils;

/**
 * 文件操作工具类
 *
 * @author Z.jh
 * 2017年3月22日
 */
public class FileUtil {
	
	/**
	 * 获取文件扩展名
	 * @param filename
	 * @return
	 */
	public static String getExtName(String filename) {
		if (filename == null || filename.indexOf(".") < 0) {
			return "";
		}
		int index = filename.lastIndexOf(".");
		return filename.substring(index + 1);
	}
	
	public static void main(String[] args) {
		System.out.println(getExtName("abc.a"));
	}

}
