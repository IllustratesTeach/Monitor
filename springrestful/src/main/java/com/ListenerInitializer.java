package com;

import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author <a href="yuchen_1997_200486@126.com">yuchen</a>.
 * @since 2019/1/31
 */
public class ListenerInitializer implements WebApplicationInitializer{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(new EnvironmentLoaderListener());
    }
}
