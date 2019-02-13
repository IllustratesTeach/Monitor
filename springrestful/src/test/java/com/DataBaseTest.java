package com;


import com.config.DataBaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yuchen on 2018/11/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfig.class)
public class DataBaseTest {



    @Test
    public void test_dataBase(){
        System.out.println(System.getProperty("java.class.path"));
    }
}
