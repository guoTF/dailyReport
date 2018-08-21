package com.seda.dailyReport.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http下载辅助类
 *
 * @author 王海涛
 * @project dxp
 * @Date 2017年5月15日
 */
public abstract class DownloadUtils {
	public static final int BUFFER_SIZE = 1024;

	/**
	 * 下载方法
	 *
	 * @param srcUrl
	 *            资源url
	 * @param filePath
	 *            文件存放在本地的绝对路径以及文件名
	 *
	 * @throws IOException
	 *             [参数说明]
	 *
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void download(String srcUrl, String filePath) throws IOException {
		long startPos = 0; // 开始下载位置

		// 转换空格
		srcUrl = srcUrl.replace(" ", "%20");

		byte[] buf = new byte[BUFFER_SIZE];
		int size = -1;

		// 如果远端资源和本地资源一样则不下载

		isFinished(srcUrl, filePath);

		// if (isFinished(srcUrl, filePath))
		// {
		// //System.out.println("finished.");
		// return;
		// }

		BufferedInputStream bis = null;
		RandomAccessFile raf = null;
		HttpURLConnection httpUrl = null;

		try {
			// 建立链接
			URL url = new URL(srcUrl);
			httpUrl = (HttpURLConnection) url.openConnection();

			// 根据URL获取文件路径
			File fileName = new File(filePath);
			startPos = fileName.length();

			if (fileName.exists()) {
				// 判断是否需要续传

				if (isContinue(srcUrl, filePath)) {
					// 获取获取文件已下载部分长度

					startPos = fileName.length();
					// 加入续传头信息

					httpUrl.setRequestProperty("RANGE", "bytes=" + startPos + "-");

					// System.out.println("isContinue");
				}
			}

			// 连接指定的资源

			httpUrl.connect();

			// 获取网络输入流

			bis = new BufferedInputStream(httpUrl.getInputStream());
			// 建立文件
			raf = new RandomAccessFile(fileName, "rw");
			raf.seek(startPos);

			// 保存文件
			while ((size = bis.read(buf)) != -1) {
				raf.write(buf, 0, size);
			}

		} finally {
			if (null != bis) {
				bis.close();
			}
			if (null != raf) {
				raf.close();
			}
			if (null != httpUrl) {
				httpUrl.disconnect();
			}
		}
	}

	/**
	 * 判断是否已经下载过了
	 *
	 * @param srcUrl
	 * @param fileName
	 * @return
	 * @throws IOException
	 *             [参数说明]
	 */
	private static boolean isFinished(String srcUrl, String fileName) throws IOException {

		// 建立链接
		// URL url = new URL(srcUrl);
		File file = new File(fileName);
		if (!file.exists()) {
			return false;
		} else {
			file.delete();
			return true;
		}

		// HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
		// if (httpUrl.getContentLength() == file.length()
		// && (file.lastModified() / 1000 >= httpUrl.getLastModified() / 1000))
		// {
		// httpUrl.disconnect();
		// file.delete();
		// return true;
		// }
		//
		// httpUrl.disconnect();
	}

	/**
	 * 判断是否需要需要断点续传
	 *
	 * @param srcUrl
	 * @param fileName
	 * @return
	 * @throws IOException
	 *             [参数说明]
	 */
	private static boolean isContinue(String srcUrl, String fileName) throws IOException {
		URL url = new URL(srcUrl);
		File file = new File(fileName);

		HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
		if (file.exists()) {
			if ((file.lastModified() / 1000 >= httpUrl.getLastModified() / 1000)) {
				httpUrl.disconnect();
				return true;
			} else {
				file.delete();
			}
		}

		httpUrl.disconnect();
		return false;
	}

	public static void main(String[] args) throws IOException {
		String url = "http://www.tradeplus.com.cn/unionpay/tranfile.aspx?typeid=seda&txnTime=20170515182623&settleDate=0504";
		String path = "D:/test.rar";

		DownloadUtils.download(url, path);
	}
	
	/**
	 * 文件下载
	 * @param realPath
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
    public static void downloadFile(String realPath, String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!StringUtils.isEmpty(fileName)) {
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + DownloadUtils.processFileName(request, fileName));
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * ie,chrom,firfox下处理文件名显示乱码 
     * @param request
     * @param fileName
     * @return
     */
    public static String processFileName(HttpServletRequest request, String fileName) {
        String codedfilename = null;  
        try {  
            String agent = request.getHeader("USER-AGENT");  
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent  
                    && -1 != agent.indexOf("Trident")) {// ie  
   
                String name = java.net.URLEncoder.encode(fileName, "UTF-8");  
   
                codedfilename = name;  
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等  
   
   
                codedfilename = new String(fileName.getBytes("UTF-8"), "iso-8859-1");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return codedfilename;  
    }  
}
