package com;

import com.filter.CompetenceFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * @author <a href="yuchen_1997_200486@126.com">yuchen</a>.
 * @since 2019/1/29
 */
public class FilterInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addFilter("competenceFilter"
                ,new CompetenceFilter()).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST)
                ,true
                ,"/user/saveUserInfo");
        servletContext.addFilter("etagFilter"
                ,new ShallowEtagHeaderFilter()).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST)
                ,true
                ,"/user/searchUserInfo");
    }
}
