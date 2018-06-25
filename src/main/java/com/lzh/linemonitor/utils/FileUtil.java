package com.lzh.linemonitor.utils;

import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by sunshine_lala on 16/10/20.
 */
public class FileUtil {

    /**
     * 复制远程文件到本地
     * @param url
     * @return
     */
    public static File copyFileByUrlToLocal(String url, String localFileStoragePath) {

        if (null == url || "".equalsIgnoreCase(url)) {
            throw new RuntimeException("file path is invalid : " + url);
        }

        File localFile = null;
        InputStream is = null;
        try {

            is = openConnection(url);
            localFile = new File(localFileStoragePath + getFileNameFromUrl(url));
            try {
                if (localFile.exists()) {
                    localFile.deleteOnExit();
                }
                localFile.createNewFile();
            } catch (Exception ex) {
            }
            FileUtils.copyInputStreamToFile(is, localFile);

        } catch (Exception e) {
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
            }
        }

        return localFile;
    }

    /**
     * Open connection.
     *
     * @param sourceUrl
     * @return
     * @throws IOException
     */
    private static InputStream openConnection(String sourceUrl) throws IOException {

        if (null == sourceUrl || 0 == sourceUrl.length()) {
            return null;
        }
        InputStream is = null;

        String parseFileName = parseFileName(sourceUrl);
        URL url = new URL(parseFileName);
        URLConnection urlConnection = url.openConnection();
        if (urlConnection instanceof HttpURLConnection) {
            int timeout = 1000 * 10; // 10 seconds
            HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
            httpUrlConnection.setConnectTimeout(timeout);
            httpUrlConnection.setReadTimeout(timeout);
            httpUrlConnection.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.81 Safari/537.36");
            is = httpUrlConnection.getInputStream();
        } else {
            is = urlConnection.getInputStream();
        }

        return is;
    }

    /**
     * Get filename from URL type path.
     * for example, a URL like "http://100.100.100.100/a.txt", it will return "a.txt".
     *
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getFileNameFromUrl(String url) throws UnsupportedEncodingException {

        int index = 0;
        if (url.contains("/")) {
            index = url.lastIndexOf("/");
        }

        return URLDecoder.decode(url.substring(index + 1), "UTF-8");

    }

    /**
     * Encode the special file name in URL.
     *
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String parseFileName(String url) throws UnsupportedEncodingException {

        int index = 0;
        if (url.contains("/")) {
            index = url.lastIndexOf("/");
        }

        return new StringBuilder(url.substring(0, index + 1))
            .append(URLEncoder.encode(url.substring(index + 1), "UTF-8")).toString();

    }

    public static String getImageStrFromUrl(String imgURL) {
        //        byte[] data = null;
        //        try {
        //            // 创建URL
        //            URL url = new URL(imgURL);
        //            // 创建链接
        //            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //            conn.setRequestMethod("GET");
        //            conn.setConnectTimeout(5 * 1000);
        //            InputStream inStream = conn.getInputStream();
        //            data = new byte[inStream.available()];
        //            inStream.read(data);
        //            inStream.close();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        //        // 对字节数组Base64编码
        //        BASE64Encoder encoder = new BASE64Encoder();
        //        // 返回Base64编码过的字节数组字符串
        //        return encoder.encode(data);

        URL url = null;
        InputStream is = null;
        ByteArrayOutputStream outStream = null;
        HttpURLConnection httpUrl = null;
        try {
            url = new URL(imgURL);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            is = httpUrl.getInputStream();
            outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = is.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            //        // 返回Base64编码过的字节数组字符串
            return encoder.encode(outStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outStream != null)
            {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }
        return null;

    }

    public static void main(String[] args) {
        System.out.println(getImageStrFromUrl(
            "https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=6e7553c9a6345982c58ae29434cf5690/faedab64034f78f00e9e5d4e72310a55b2191cf2.jpg"));
    }

}
