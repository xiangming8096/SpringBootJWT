package com.lt.web.utils;

import com.lt.web.domain.User;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTutil {
    //读取配置文件 获取 密钥
    private static final String KEY = "VGxWNGNHUkVWbUZrVkdzeFRtMVZNMDVXYkhoaWR6MDk=";

    //30分钟
    private static final long time30Minute = 30 * 60 * 1000;
    //15天
    private static final long time15Day = 15 * 24 * 60 * 60 * 1000;

    //创建30分钟jwt
    public static String createJWT30Minute(User user) {
        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //创建jwt
        JwtBuilder jwt = createJWT(user, now);
        //设置过期时间
        jwt.setExpiration(new Date(nowMillis + time30Minute));
        return jwt.compact();
    }

    //创建15天jwt
    public static String createJWT15Day(User user) {
        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //创建jwt
        JwtBuilder jwt = createJWT(user, now);
        //设置过期时间
        jwt.setExpiration(new Date(nowMillis + time15Day));
        return jwt.compact();
    }

    //创建JWT
    private static JwtBuilder createJWT(User user, Date now) {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm Header = SignatureAlgorithm.HS256;

        //创建payload的私有声明
        Map<String, Object> body = new HashMap<>();
        body.put("username", user.getUsername());
        body.put("id", user.getId());

        //生成签发人
        //String signer = user.getUsername();

        //创建jwt
        /**
         *
         * //下面就是在为payload添加各种标准声明和私有声明了
         *         //这里其实就是new一个JwtBuilder，设置jwt的body
         *         JwtBuilder builder = Jwts.builder()
         *                 //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
         *                 .setClaims(claims)
         *                 //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
         *                 .setId(UUID.randomUUID().toString())
         *                 //iat: jwt的签发时间
         *                 .setIssuedAt(now)
         *                 //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
         *                 .setSubject(subject)
         *                 //设置签名使用的签名算法和签名使用的秘钥
         *                 .signWith(signatureAlgorithm, key);
         */

        return Jwts.builder().setClaims(body).setIssuedAt(now).signWith(Header, KEY);
    }

    //解析JWT
    public static boolean verifyJwt(String token) {
        /*
        //获取jwt解析器
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(KEY)
                //设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        */
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        } catch (Exception e){
            return false;
        }

        return (Integer)claims.get("exp") > (Integer)claims.get("iat");
    }

}
