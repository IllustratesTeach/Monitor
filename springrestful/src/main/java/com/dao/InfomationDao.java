package com.dao;

import com.entity.Infomation;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InfomationDao extends Repository<Infomation,Integer> {

    void save(Infomation infomation);

    List<Infomation> findByisStart(int isStart);

    Infomation findByid(String id);

    @Transactional
    void deleteInfomationByid(String id);
}