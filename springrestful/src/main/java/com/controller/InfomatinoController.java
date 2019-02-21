package com.controller;

import com.dao.InfomationDao;
import com.entity.Infomation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class InfomatinoController {

    @Autowired
    private InfomationDao infomationDao;


    @RequestMapping(value = "/addInfomation")
    public void addInfomation(Infomation infomation){
        String id = infomation.getId();
        if(null != id && id.length() > 0){
            Infomation oldInfomation = infomationDao.findByid(id);
            infomation.setInitTime(oldInfomation.getInitTime());
            infomationDao.save(infomation);
        }else{
            infomation.setIsStart(1);
            infomation.setInitTime(new Date());
            infomationDao.save(infomation);
        }
    }

    @RequestMapping(value = "/getInfomationList")
    @ResponseBody
    public List<Infomation> getInfomationList(){
        List<Infomation> infomationList = infomationDao.findByisStart(1);
        return infomationList;
    }

    @RequestMapping(value = "/getInfomation")
    @ResponseBody
    public Infomation getInfomation(String[] ids){
        String id = ids[0];
        Infomation infomation = infomationDao.findByid(id);
        return infomation;
    }

    @RequestMapping(value = "/deleteInfomation")
    public void deleteInfomation(String[] ids){
        for(String id :ids){
            infomationDao.deleteInfomationByid(id);
        }
    }

}