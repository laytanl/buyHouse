package com.csuft.buyhouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csuft.buyhouse.Dao.UserDao;
import com.csuft.buyhouse.entity.User;
import com.csuft.buyhouse.util.PlatformException;

@Service
public class UserService{
	@Autowired
	UserDao userDao;
	
	
	public void login(User user) {
		User query=new User();
		query.setUserPassword(user.getUserPassword());
		query.setUserPhone(user.getUserPhone());
		User dbuser=userDao.templateOne(query);
		if(dbuser==null) {
			throw new PlatformException("用户名或密码错误");
		}
		
	}
	
	
	public void save(User user) {
		User query=new User();
		query.setUserPhone(user.getUserPhone());
		User dbuser=userDao.templateOne(query);
		if(dbuser!=null) {
			throw new PlatformException("用户名已经存在，注册失败");
		}
		try {
			userDao.insertTemplate(user,true);
		} catch (Exception e) {
			new PlatformException("注册失败");
		}
	}
	
}