package com.feelcolor.website.ssl;

import com.feelcolor.utils.AESSecurityUtil;
import com.feelcolor.utils.RSASignUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 请求https接口
 */
public class Restful {
    public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        HttpsURLConnection.setDefaultHostnameVerifier(new Restful().new NullHostNameVerifier());
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        URL url = new URL(
                "https://47.94.161.88/ssl/ssltest");
        // 打开restful链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");// POST GET PUT DELETE
        // 设置访问提交模式，表单提交
        conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        conn.setConnectTimeout(130000);// 连接超时 单位毫秒
        conn.setReadTimeout(130000);// 读取超时 单位毫秒
        // 读取请求返回值
        byte bytes[] = new byte[1024];
        InputStream inStream = conn.getInputStream();
        inStream.read(bytes, 0, inStream.available());
        System.out.println(new String(bytes, "utf-8"));
    }

    static TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // TODO Auto-generated method stub
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // TODO Auto-generated method stub

            System.out.println(chain[0]);
//            System.out.println("====================================================================");
//
//            System.out.println(Base64.getDecoder().decode(chain[0].getSignature()));
//            System.out.println("====================================================================");
//
//            PublicKey publicKey = chain[0].getPublicKey();
//
//            System.out.println("pk:"+Base64.getEncoder().encode(publicKey.getEncoded()));

//            System.out.println(RSASignUtil.verifyByPublicKey(chain[0],chain[0].getPublicKey(),Base64.getEncoder().encode(chain[0].getSignature())));

            byte[] a= new byte[0];
            try {
                a = AESSecurityUtil.encrypt("加密传输内容".getBytes(),"我是aes秘钥");
                System.out.println(new String(a));
                System.out.println(new String(AESSecurityUtil.decrypt(a,"我是aes秘钥")));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }



        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }
    } };

    public class NullHostNameVerifier implements HostnameVerifier {
        /*
         * (non-Javadoc)
         * 
         * @see javax.net.ssl.HostnameVerifier#verify(java.lang.String,
         * javax.net.ssl.SSLSession)
         */
        @Override
        public boolean verify(String arg0, SSLSession arg1) {
            // TODO Auto-generated method stub
            return true;
        }
    }
}