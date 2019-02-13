package com.filter;

import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author <a href="yuchen_1997_200486@126.com">yuchen</a>.
 * @since 2019/1/28
 */
public class CompetenceFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest)servletRequest).getCookies();
        boolean flag = false;
        if(null != cookies){
            for(Cookie cookie :cookies){
                if(cookie.getName().equals("user")){
                    flag =true;
                    break;
                }
            }
            if(flag){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                ServletOutputStream out = servletResponse.getOutputStream();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("success",false);
                jsonObject.put("message","cookie is null");
                out.write(jsonObject.toString().getBytes());
                out.flush();
            }
        }
    }

    @Override
    public void destroy() {}
}
