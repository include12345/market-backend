package com.lihebin.market.wx.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lihebin.market.utils.LocalDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: some desc
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/26 11:06 下午
 */
@Slf4j
@Component
public class JwtHelper {

    public static final String TOKEN = "market-token";
    private static final String ISSUSER = "MARKET-SERVICE";
    private static final String SUBJECT = "this is a token";
    private static final String AUDIENCE = "MINIAPP";

    public String createToken(Long userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN);
            Map<String, Object> map = new HashMap<>();
            Date nowDate = new Date();
            Date expireDate = LocalDateUtil.addDateHours(nowDate, 2);
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            return JWT.create()
                    .withHeader(map)
                    .withClaim("userId", userId)
                    .withIssuer(ISSUSER)
                    .withSubject(SUBJECT)
                    .withAudience(AUDIENCE)
                    .withIssuedAt(nowDate)
                    .withExpiresAt(expireDate)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("createToken", e);
        }
        return null;
    }

    public Long getUserIdByToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUSER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            return claims.get("userId").asLong();
        } catch (Exception e) {
            log.error("getUserIdByToken", e);
        }
        return null;
    }
}
