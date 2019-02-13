package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import java.beans.PropertyVetoException;

/**
 * Created by yuchen on 2018/3/17.
 */
@Configuration
@EnableTransactionManagement
public class JDBCTransactionConfig implements TransactionManagementConfigurer {

    @Autowired
    DataBaseConfig dataBaseConfig;


    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        try {
            dataSourceTransactionManager.setDataSource(dataBaseConfig.dataSource());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSourceTransactionManager;
    }
}
