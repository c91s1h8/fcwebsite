package com.feelcolor.website.config;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alipay.api.DefaultAlipayClient;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
@Configuration
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016080600177148";
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCIek17cyNtpLWPui6a4vXr98y6Aqb8rR+e5Is243sxIO4hp+f79YmxPPr8dfM9WAwoCBMVanTJ5fGynQja9Omq9RClPDrYuuB5VIaDnfg5PqzkR96FHO2Z7ari4iEbTQIZ5+1pMBzwt3RqdPYJUo8uK04jCW1r1lbMGibKnoBFmAIagXvydMWEp/ZGhmY9z9wDzQYdnw+VRyWm6dHWkGH6YcZUTFW+Zf01RoMLRFbj0kBJXIFUsRWH3QiBf4jZFkz49BHn46ZCV1LRwRpZ70gUaqbop0LZHjzM5bIdMiKL7BOBO6R0dOTrTRo1VHy/bMkKjS1vjCx6dXsfXtfoKxI9AgMBAAECggEAPXt7dGXGTBqrgHhLjpM4XnlfzVxsheAoIpiq7qtEUDmZjafqpmNTCHmESD+KuNP1hgM9NaSjwGDKpcCAwMMckVudiBK+H+zwOziM8LY1oPRPRvfpkuYZVALxvQxKBdaHX8xP89cs8nRzCZJvVHvQ7OetZ7NTjAUfTQtq9lS5TKAlAB8/aODlLkcEWo8y+XGvlli/9sTo33FrPunja4ENX9NP+4j5WL0WQVyOnJ7meaCgYjYIdHf4J6ueGTHWlL92BshCvc8nf5Yj5iEozGufRkHGVVqXoaLfOPLvwgBeoPpgxIM+PMGT+F22kusoYgl+GAjDxB4tnGejD/qNkT2OsQKBgQDtF4Yi494VXl0eB1hxOScEgjvnmuJD7nsObuXB0oKOslTxyrnPkzsmePnZJCKPvomdB6ZBwWlf9AcCIgAcrqSawfWnlIURfiAaCo+tCKI27NBpeCyVqAdquvADwVBE+rPPKn7AFKM+08MDyUWTplw/wHrPhlcnDXO1xiQVRGEh4wKBgQCTXKMMIKf6NNK2YcNThotITAwNfDWaDQZV/krdyCa4dp/SOTya5QQSO0B9bZ88i5LX4cGIN0K+forQvr1Vs/tU3Jag7zipGhzmv7oXAs7s2/upR0RaeCJc4CuBBO3lL5x80DwNnPfChAPn3wT/QBokfETVUbEsdQrulzrZ2E61XwKBgQCvRs7RiGgsoABzLLcDrV7dBHLI/EX+NpYey3lr2vbOC6SkxFytyojWHmWHVCGA5znRL1CIGDaQy1VLDFvM2Ie0KWQ0qaXeksGkvt4M6+8Qb+57qm47jWc+TkkWXZm8BSNdKmteEWPGFMN9TxTy3PAh6Whhd02C/3zli0AtwMmWRQKBgDMaMfS3smjjPps+xeTGSLDWpmtawp/cmnDh0YadrUfU41GcxbaVatcDszaL+Gefo1m4EUwylbPH4E3Jh5Eb7DGemaHPs594zcL7hAYM1ENxehFXBI43EPuSRaPlWLZcz3C9KcYyw/yxmUUkou/9g9NZQOmU/dMD97mLuj3ndEwTAoGBAI3WovRxsWkcFyXyO1enXp0QcrgXfpsjJLbu2tgSxQD0wgu/I5hqFzNZZqGeLrt1eb2sF7Jh3EBYW4hP5s6Q+EcIFKDkTPzOpilltuqHEA+bC4XFfn1ORsvkt5N3ar/rGakFhYGQTP9n/92wKQ7fkGPAonxzjA3F4fqihr/l/Ied";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
    // 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuoSa48vvIdqmDjcaMdzkappQBRs/awariiZeyE6EFGFE8PTijkHuP6fQjES7yZmigmvBfw/vfoAv4jQNqmIqidwjCGJZccBLLiiIVQ0r5MHFLM35SIIxdrOK7v7ctjK6mtnFNY5nuQ8GkdDSci/YY4bgtOTLJDmvPQlCgp7JnE1ftFcgMl2jBBAqEUKy43aBTmwBXaAkBjpFfXTb/ZW3cCfKmB/IyTN/51TMsZxJ03LH+AR25WV+tT2mN5aJWsmV48LsS6VrOI/tlZdTml3v/PIbMDxEiP3Ty6XDoEEHXZ1JsjaKnBWxBuVCoG2+4zGQEZ+2+7xRS5IbHlgFuw8ihQIDAQAB";
    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://http://47.94.161.88:8080/aliPay/notify";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://http://47.94.161.88:8080";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关 沙盒网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // public static String gatewayUrl =
    // "https://openapi.alipay.com/gateway.do";
    public static String log_path = "d:\\";

    @Bean
    public DefaultAlipayClient alipayClient() {
        return new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, "json", charset, alipay_public_key,
                sign_type);
    }

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * 
     * @param sWord
     *            要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
