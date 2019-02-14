package com.dao;

import com.entity.Person;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author <a href="yuchen_1997_200486@126.com">yuchen</a>.
 * @since 2019/2/13
 */
public interface UserDao extends Repository<Person,Integer>{
    List<Person> findBySex(String sex);

    Person save(Person person);


}
