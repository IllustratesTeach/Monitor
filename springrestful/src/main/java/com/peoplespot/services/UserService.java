package com.peoplespot.services;



import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService{

	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Transactional
	public void SaveUser(Map<String,Object> parammap) throws Exception
	{
		int flag = sqlSession.insert("userinfo.saveUser",parammap);
		if(flag<=0){
			throw new Exception("插入user表失败,影响行数为"+flag);
		}
		int deleteFlag = sqlSession.delete("useinfo.deleteUser","900");
		if(deleteFlag <= 0){
			throw new Exception("删除数据失败,影响行数为" + deleteFlag);
		}
	}
	
	public List<Map<String,Object>> QueryUserInfo()
	{
		List<Map<String,Object>> listResult = sqlSession.selectList("userinfo.searchUser");
		return listResult;
	}
}
