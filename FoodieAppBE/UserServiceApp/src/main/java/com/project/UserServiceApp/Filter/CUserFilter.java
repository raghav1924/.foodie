package com.project.UserServiceApp.Filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CUserFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;

        String authHeader=httpServletRequest.getHeader("authorization");
        System.out.println("this is about authheader");
        System.out.println(authHeader);
        if (authHeader==null || !authHeader.startsWith("Bearer")) throw new ServletException("Token is Missing!!");
        else {
            System.out.println("this is about claims");
            String tok=authHeader.substring(7);
            Claims claims= Jwts.parser().setSigningKey("FoodieApp_key").parseClaimsJws(tok).getBody();

            System.out.println(claims);
            httpServletRequest.setAttribute("email",claims.get("user_email"));
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }
}
