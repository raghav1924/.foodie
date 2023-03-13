package com.project.SellerServiceApp.Filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
        System.out.println("This is about testing");
        String authHeader=httpServletRequest.getHeader("authorization");
    if (authHeader==null || !authHeader.startsWith("Bearer")) throw new ServletException("Token is Missing!!");
    else {
        String tok=authHeader.substring(7);
        Claims claims= Jwts.parser().setSigningKey("FoodieApp_key").parseClaimsJws(tok).getBody();
        httpServletRequest.setAttribute("email",claims.get("seller_email"));
        System.out.println(httpServletRequest.getAttribute("email"));
        System.out.println("claims"+claims);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
    }


}
