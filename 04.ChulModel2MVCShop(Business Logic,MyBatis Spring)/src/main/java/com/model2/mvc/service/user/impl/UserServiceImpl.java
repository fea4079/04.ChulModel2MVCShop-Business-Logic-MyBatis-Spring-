package com.model2.mvc.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.UserDao;;


//==> 회원관리 서비스 구현
@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
	
	///Field
	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		System.out.println("UserServiceImpl.java setUserDao "+userDao);
		this.userDao = userDao;
	}
	
	///Constructor
	public UserServiceImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void addUser(User user) throws Exception {
		System.out.println("UserServiceImpl.java addUser "+user);
		userDao.addUser(user);
	}

	public User getUser(String userId) throws Exception {
		System.out.println("UserServiceImpl.java getUser "+userId);
		return userDao.getUser(userId);
	}

	public Map<String , Object > getUserList(Search search) throws Exception {
		List<User> list= userDao.getUserList(search);
		int totalCount = userDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		System.out.println("UserServiceImpl.java getUserList "+search);
		return map;
	}

	public void updateUser(User user) throws Exception {
		System.out.println("UserServiceImpl.java updateUser "+user);
		userDao.updateUser(user);
	}

	public boolean checkDuplication(String userId) throws Exception {
		System.out.println("UserServiceImpl.java checkDuplication "+userId);
		boolean result=true;
		User user=userDao.getUser(userId);
		if(user != null) {
			result=false;
		}
		return result;
	}
}