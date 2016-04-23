package com.spring.bently.wx.utils.httptool;

import com.spring.bently.wx.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wgq on 16-4-2.
 */
public class CustomHttpConnection {

    private static Logger log = LoggerFactory.getLogger(CustomHttpConnection.class) ;

    private HttpConnectionCommon hcc ;

  //  private String urlCustom ;

 //   private int timeout = 5000 ;

 //   private String requestMethod = "GET" ;

 //   private String contentType = "application/x-www-form-urlencoded" ;


  /*  //get请求
    public String httpGet() {
        log.info("httpGet请求开始~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        HttpURLConnection conn = this.getHttpUrlConnection() ;

        try {
            conn.connect();
            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null ;
            }

            String responseMsg = inputStreamToString(conn.getInputStream()) ;
            log.info("url = " + urlCustom + "requestMethod = " + requestMethod);
            log.info("responseMsg = " + responseMsg);
            log.info("httpGet请求结束~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return responseMsg ;
        } catch (IOException e) {
           // e.printStackTrace();
            log.warn("httpGet请求出现错误：" + e.getMessage());
        }finally {

        }
        return null ;
    }*/

    //请求
    public String httpClient(String requsetMessage) {
        log.info("http请求开始~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        HttpURLConnection conn = this.getHttpUrlConnection(requsetMessage) ;

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
            log.info("HttpConnectionCommon = " + hcc.toString() );
            log.info("responseMsg = " + responseMsg);
            log.info("http请求结束~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return responseMsg ;
        }catch (IOException e) {
            //e.printStackTrace();
            log.warn("http请求出现错误：" + e.getMessage());
        }finally {

        }

        return null ;
    }

    //得到HttpUrlConnection对象
    private HttpURLConnection getHttpUrlConnection(String requestMessage) {
        try {
            URL url = new URL(this.hcc.getUrlCustom()) ;
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(this.hcc.getTimeout());
            conn.setRequestMethod(this.hcc.getRequestMethod());
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("Content-Type", this.hcc.getContentType());
            if(!StringUtils.isEmpty(requestMessage)) {
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Length", String.valueOf(requestMessage.length()));
            }
            //conn.connect();
            return conn ;
        } catch (MalformedURLException e) {
          //  e.printStackTrace();
            log.warn("获取HttpUrlConnection出错：" + e.getMessage());
        } catch (IOException e) {
          //  e.printStackTrace();
            log.warn("获取HttpUrlConnection出错：" + e.getMessage());
        }
        return null ;
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

    private CustomHttpConnection() {}   //关闭午餐的构造方法


    public CustomHttpConnection(HttpConnectionCommon hcc) {
        this.hcc = hcc ;
    }

    public HttpConnectionCommon getHcc() {
        return hcc;
    }

    public void setHcc(HttpConnectionCommon hcc) {
        this.hcc = hcc;
    }
}
