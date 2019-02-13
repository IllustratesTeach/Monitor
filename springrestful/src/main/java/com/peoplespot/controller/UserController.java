package com.peoplespot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peoplespot.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	public UserController() {

	}

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> AddUserInfo(
			@RequestParam(value = "name", defaultValue = "") String name
			, @RequestParam(value = "password", defaultValue = "") String password
			, @RequestParam(value = "ip", defaultValue = "") String ip
			, @RequestParam(value = "marker", defaultValue = "") String marker, HttpServletRequest request) {
		HashMap<String, Object> resultMap = new HashMap<>();
		try{
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("name", "俞晨");
			paramMap.put("age", 32);
			paramMap.put("sex", 1);
			paramMap.put("inputtime", new Date());
			userService.SaveUser(paramMap);
			resultMap.put("success", true);
			resultMap.put("message", "数据保存成功");
		}
		catch(Exception ex){
			resultMap.put("success", false);
			resultMap.put("message", "数据保存失败:" + ex.getMessage());
		}

		return resultMap;
	}
	
	@RequestMapping(value = "/searchUserInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> SearchUserInfo(@RequestParam(value = "name", defaultValue = "") String name,HttpServletResponse response) {
		
		HashMap<String, Object> resultMap = new HashMap<>();
		try{
			List<Map<String,Object>> listmap = userService.QueryUserInfo();
			if(listmap.size()<=0){
				throw new Exception("没有查询结果");
			}
			else{
				resultMap.put("success", true);
				resultMap.put("message", "查询成功:" + name);
				resultMap.put("rows", listmap);
				Cookie cookie = new Cookie("user","yuchen");
				cookie.setMaxAge(10);
				response.addCookie(cookie);
			}
		}catch(Exception ex){
			resultMap.put("success", false);
			resultMap.put("message", ex.getMessage());
		}
		
		return resultMap;
	}
}