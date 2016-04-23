package com.spring.bently.wx.utils.httptool;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by wgq on 16-4-2.
 */
public class MyTrustManager implements X509TrustManager {
    /**
     * 我的证书
     * @param x509Certificates
     * @param s
     * @throws CertificateException
     */
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
