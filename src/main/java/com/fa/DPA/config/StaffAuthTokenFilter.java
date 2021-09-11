package com.fa.DPA.config;

import com.fa.DPA.common.JwtUtils;
import com.fa.DPA.service.CustomerLoginService.CustomDetailImpl;
import com.fa.DPA.service.CustomerLoginService.CustomerDetailServiceImpl;
import com.fa.DPA.service.StaffLoginService.StaffDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StaffAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private StaffDetailsServiceImpl staffDetailsService;

    @Autowired
    private CustomDetailImpl customService;

    UserDetailsService UserDetailsService;

    private static final Logger Logger = LoggerFactory.getLogger(StaffAuthTokenFilter.class);

    /**
     * 
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getNameFromJwtToken(jwt);
                UserDetails userDetails = customService.loadUserByUsername(username);
                //UserDetails userDetails = returnUserdetail(username,staffDetailsService,customerDetailService);
                Logger.error("this is role : " + userDetails.getAuthorities());
                Logger.error("this is username : " + userDetails.getUsername());
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            Logger.error("Cannot set user authentication : {}", e);
        }
        filterChain.doFilter(request, response);
    }

    /**
     *
     * @param request
     * @return
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }


}
