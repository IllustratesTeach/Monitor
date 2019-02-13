package com.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.beans.PropertyVetoException;

/**
 * Created by yuchen on 2018/11/1.
 */
@Configuration
//@ComponentScan(basePackages = "com.soundsystem") //隐式声明
public class DataBaseConfig {

    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setMinPoolSize(10);
        dataSource.setMaxPoolSize(60);
        dataSource.setInitialPoolSize(10);
        dataSource.setAcquireRetryAttempts(30);
        dataSource.setAcquireRetryDelay(1000);
        dataSource.setBreakAfterAcquireFailure(true);
        dataSource.setTestConnectionOnCheckout(true);
        dataSource.setTestConnectionOnCheckin(true);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(ComboPooledDataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(ComboPooledDataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("config/mybatis-configuration.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory){
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
