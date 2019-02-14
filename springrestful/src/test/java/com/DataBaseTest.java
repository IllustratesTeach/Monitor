package com;


import com.config.DataBaseConfig;
import com.dao.UserDao;
import com.entity.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by yuchen on 2018/11/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfig.class)
public class DataBaseTest {

    @Autowired
    UserDao userDao;

    @Test
    public void test_queryEntity(){
        userDao.findBySex("1").forEach(t -> System.out.println(t.getName()));
        //Assert.assertEquals("斯科拉里",name);
    }

    @Test
    public void test_saveEntity(){
        Person person = new Person();
        person.setName("张修维");
        person.setSex("1");
        person.setAge("24");
        person.setInputtime(new Date());
        Assert.assertNotNull(userDao.save(person));
    }
}
