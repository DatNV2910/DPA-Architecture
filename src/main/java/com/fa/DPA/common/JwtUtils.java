package com.fa.DPA.common;


import com.fa.DPA.service.CustomerLoginService.CustomerDetailImpl;
import com.fa.DPA.service.StaffLoginService.StaffDetailImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    public static final Logger Logger= LoggerFactory.getLogger(JwtUtils.class);

    @Value("${dpa.app.jwtSecret}")
    private String jwtSecret;

    @Value("${dpa.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userDetails.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }


    public String getNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch(SignatureException e){
            Logger.error("Invalid JWT signature : {}",e.getMessage());
        }catch (MalformedJwtException e){
            Logger.error("Invalid JWT token : {}",e.toString());
        }catch(ExpiredJwtException e){
            Logger.error("JWT token is expired : {}",e.getMessage());
        }catch (UnsupportedJwtException e){
            Logger.error("JWT token is unsupported : {}",e.getMessage());
        }catch (IllegalArgumentException e){
            Logger.error("JWT claims string is empty : {}",e.getMessage());
        }
        return false;
    }

}
