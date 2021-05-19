package com.emaolv.academy.jwt;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

/**
 * @author: liu jia
 * @description: JWT 单点登录测试
 * @date: Created in 2021/5/19 13:17
 */
public class JwtTest {

    private static long tokenExpiration = 1000*60*60*24;
    private static String tokenSignKey = "emaolv";
    @Test
    public void testCreatedToken() {
        // jwt 构造器
        JwtBuilder jwtBuilder = Jwts.builder();

        String jwtToken = jwtBuilder
                // 头
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                // 载荷 自定义信息
                .claim("nickname", "Jack")
                .claim("email", "233103498")
                // 默认信息 有效载荷
                .setSubject("QA学院用户信息") // 主题
                .setIssuer("emaolv") //  签发者
                .setAudience("emaolv") // 接受者
                .setIssuedAt(new Date()) //令牌签发时期
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))//令牌过期时间 一天
                .setNotBefore(new Date(System.currentTimeMillis() + 1000 * 20)) // 20秒后生效
                .setId(UUID.randomUUID().toString())

                // 签名哈希
                .signWith(SignatureAlgorithm.HS256, tokenSignKey)
                // 组装jwt字符串
                .compact();
        System.out.println("jwtToken");
        System.out.println(jwtToken);
    }

    @Test
    public void testGetUserInfo(){
        
        String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6IkphY2siLCJlbWFpbCI6IjIzMzEwMzQ5OCIsInN1YiI6IlFB5a2m6Zmi55So5oi35L-h5oGvIiwiaXNzIjoiZW1hb2x2IiwiYXVkIjoiZW1hb2x2IiwiaWF0IjoxNjIxNDAzMjMxLCJleHAiOjE2MjE0ODk2MzEsIm5iZiI6MTYyMTQwMzI1MSwianRpIjoiMzI0ZDFiODMtMGI1My00ZmU4LWEzZjAtODdjYmYzMDVmMWFiIn0.4l6Nl5SizPmIyKWo_6ILbqIkYnEPvXDv1GbHh8ZRajs";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(tokenSignKey)
                .parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        String nickname = (String)claims.get("nickname");
        String email = (String)claims.get("email");
        System.out.println("nickname："+nickname+" "+"email："+email);
        String id = claims.getId();
        System.out.println(id);


    }
}
