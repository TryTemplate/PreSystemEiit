package com.eiit.presystemeiit.utils;

import com.eiit.presystemeiit.common.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger LOG = LogManager.getLogger(JwtUtil.class);

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(Constants.AES_KEY.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
     * 加密方法(创建令牌)
     *
     * @param id        唯一标识
     * @param subject   加密内容
     * @param ttlMillis 有效时间
     * @return
     */
    public static String createJWT(String id, String subject, long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
//                .setClaims(claims)            //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(id)                    //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)            //iat: jwt的签发时间
                .setSubject(subject)        //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么token，roldid之类的，作为用户的唯一标志。
                .signWith(signatureAlgorithm, key);//设置签名使用的签名算法和签名使用的秘钥
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            DateFormat frm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
            System.out.println("jwt create time : " + frm.format(new Date(nowMillis)));
            System.out.println("jwt effective time : " + ttlMillis / 1000 + " s");
            System.out.println("jwt losted time : " + frm.format(exp));
            builder.setExpiration(exp);        //设置过期时间
        }
        return builder.compact();            //就开始压缩为xxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx这样的jwt
    }

    /**
     * 解密方法(解析令牌)
     *
     * @param jwt 加密字符串(令牌)
     * @return
     */
    public static Claims parseJWT(String jwt) {

        SecretKey key = generalKey();  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)         //设置签名的秘钥
                .parseClaimsJws(jwt).getBody();//设置需要解析的jwt
        return claims;
    }


    public static void main(String[] args) {

        //加密
        String jwt = createJWT("001", "value", 1000L);
        System.out.println("加密后：" + jwt);

        //解密
        Claims claims = parseJWT(jwt);
        System.out.println("过期日期:\t\t\t\t" + claims.getSubject() + "\n机器码:\t\t\t\t\t" + claims.getId());


        System.out.println("{\"a\":\"a\"}".getBytes().length);

    }


    /**
     * 获取当前CPU序列号
     *
     * @return
     */
    public static String getCpuId() {

        String osName = DmcUtils.getOSName();
        String cupid = null;

        if (osName.contains("windows")) {
            System.out.println("osName :\t" + osName);
            cupid = DmcUtils.getCPUID_Windows();
            System.out.println("cupId :\t\t" + cupid);
        }

        if (osName.contains("linux") || osName.contains("cent") || osName.contains("ubuntu")) {
            System.out.println("osName :\t" + osName);
            try {
                cupid = DmcUtils.getCPUID_linux();
                System.out.println("cupId :\t\t" + cupid);
            } catch (InterruptedException e) {
                LOG.error("----------------- 获取CPU序列码异常 :\t" + e.getMessage());
            }
        }

        return cupid;
    }

    /**
     * 生成授权文件秘钥
     *
     * @param cpuid 需要授权的CPU序列号
     * @param day   需要授权多少天 旧秘钥失效,新秘钥天数为准
     * @return
     */
    public static String getSQWJMY(String cpuid, Integer day) {

        if (day == null || day == 0)
            return null;

        long times = 1000L * 3600 * 24 * day;

        String jwt = null;
        try {

            //加密
            Date date = new Date();
            date = DateUtil.dayAdd(date, day);
            String datas = DateUtil.getDate_yyyy_MM_dd(date);
            jwt = createJWT(cpuid, datas, times);
        } catch (Exception e) {
            LOG.error("---------- 生成授权码失败");
            LOG.error("----------------- CPUID :\t" + cpuid);
            return null;
        }

        return jwt;
    }


}
