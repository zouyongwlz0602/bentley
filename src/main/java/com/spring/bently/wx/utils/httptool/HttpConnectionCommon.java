package com.spring.bently.wx.utils.httptool;

import com.spring.bently.wx.utils.StringUtils;

import java.io.Serializable;

/**
 * Created by wgq on 16-4-2.
 */
public class HttpConnectionCommon implements Serializable {

    private static final long serialVersionUID = 5084511292417825871L;
    private String urlCustom ;

    private int timeout = 5000 ;

    private String requestMethod = "GET" ;

    private String contentType = "application/x-www-form-urlencoded" ;

    public String getUrlCustom() {
        return urlCustom;
    }

    public void setUrlCustom(String urlCustom) {
        this.urlCustom = urlCustom;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public HttpConnectionCommon(String urlCustom) {
        this(urlCustom,0,null,null) ;
    }

    public HttpConnectionCommon(String urlCustom, String requestMethod) {
        this(urlCustom,0,requestMethod,null) ;
    }

    public HttpConnectionCommon(String urlCustom, int timeout, String requestMethod, String contentType) {
        this.urlCustom = urlCustom ;

        if(timeout != 0) {
            this.timeout = timeout ;
        }

        if(!StringUtils.isEmpty(requestMethod)) {
            this.requestMethod = requestMethod ;
        }

        if(!StringUtils.isEmpty(contentType)) {
            this.contentType = contentType ;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpConnectionCommon that = (HttpConnectionCommon) o;

        if (timeout != that.timeout) return false;
        if (contentType != null ? !contentType.equals(that.contentType) : that.contentType != null) return false;
        if (requestMethod != null ? !requestMethod.equals(that.requestMethod) : that.requestMethod != null)
            return false;
        if (urlCustom != null ? !urlCustom.equals(that.urlCustom) : that.urlCustom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = urlCustom != null ? urlCustom.hashCode() : 0;
        result = 31 * result + timeout;
        result = 31 * result + (requestMethod != null ? requestMethod.hashCode() : 0);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HttpConnectionCommon{" +
                "urlCustom='" + urlCustom + '\'' +
                ", timeout=" + timeout +
                ", requestMethod='" + requestMethod + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
