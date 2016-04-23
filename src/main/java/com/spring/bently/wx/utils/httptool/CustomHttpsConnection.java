package com.spring.bently.wx.utils.httptool;


import com.spring.bently.wx.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


/**
 * Created by wgq on 16-4-2.
 */
public class CustomHttpsConnection {

    private static Logger log = LoggerFactory.getLogger(CustomHttpsConnection.class) ;

    private HttpConnectionCommon hcc ;

  //  private String urlCustom ;

  //  private int timeout = 5000 ;

  //  private String requestMethod = "GET" ;

  //  private String contentType = "application/x-www-form-urlencoded" ;

    public String httpsClient(String requsetMessage){
        log.info("https请求开始~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        HttpsURLConnection conn = this.getHttpsUrlConnection(requsetMessage) ;

        try{

            if("GET".equalsIgnoreCase(this.hcc.getRequestMethod())) {
                conn.connect();
            }

            if(!StringUtils.isEmpty(requsetMessage)) {
                postMsg(requsetMessage, conn.getOutputStream()) ;
            }

            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                log.debug("请求出错,错误代码：" + conn.getResponseCode());
                return null ;
            }

            String responseMsg = inputStreamToString(conn.getInputStream()) ;
            log.info("HttpConnectionCommon = " + this.hcc.toString());
            log.info("responseMsg = " + responseMsg);
            log.info("https请求结束~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return responseMsg ;
        }catch (IOException e) {
            //e.printStackTrace();
            log.warn("https请求出现错误：" + e.getMessage());
        }finally {

        }

        return null ;
    }

    private HttpsURLConnection getHttpsUrlConnection(String requestMessage) {
        try {
            URL url = new URL(this.hcc.getUrlCustom()) ;
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setConnectTimeout(this.hcc.getTimeout());
            conn.setRequestMethod(this.hcc.getRequestMethod());
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setSSLSocketFactory(this.createSSLSocketFactory());
            conn.setRequestProperty("Content-Type", this.hcc.getContentType());

            if(!StringUtils.isEmpty(requestMessage)) {
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Length", String.valueOf(requestMessage.length()));
            }

            return conn ;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.warn("获取HttpsUrlConnection对象出错：" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("获取HttpsUrlConnection对象出错：" + e.getMessage());
        }
        return null ;
    }

    private SSLSocketFactory createSSLSocketFactory() {
        TrustManager[] tm = { new MyTrustManager() };
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        SSLSocketFactory ssf = sslContext.getSocketFactory();

        return ssf ;
    }

    //post发送String消息
    private void postMsg(String requsetMessage,OutputStream outputStream) {

        try {
            outputStream.write(requsetMessage.getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //get和post返回的流转换成String
    private String inputStreamToString(InputStream inputStream) {

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream) ;
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader) ;
        StringBuilder sb = new StringBuilder() ;
        String readLine ;
        try {
            while((readLine = bufferedReader.readLine()) != null) {
                sb.append(readLine) ;
            }

            return sb.toString() ;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null ;
    }

    private CustomHttpsConnection() {}

    public CustomHttpsConnection(HttpConnectionCommon hcc) {
        this.hcc = hcc ;
    }

    public HttpConnectionCommon getHcc() {
        return hcc;
    }

    public void setHcc(HttpConnectionCommon hcc) {
        this.hcc = hcc;
    }
}
