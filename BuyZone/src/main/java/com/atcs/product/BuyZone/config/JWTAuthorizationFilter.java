package com.atcs.product.BuyZone.config;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class JWTAuthorizationFilter implements Filter {

    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Cookie[] cookies = req.getCookies()==null?(new Cookie[]{}) : req.getCookies();
        Boolean flag = false;


        for(Cookie c:cookies)
        {
            if(c.getName().equals(HEADER_STRING))
            {

                flag = true;
                break;
            }
        }
        req.setAttribute("isToken",flag);


            filterChain.doFilter(servletRequest,servletResponse);



    }







    }
