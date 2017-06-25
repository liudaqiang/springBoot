package com.hangdaoju.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hangdaoju.core.Global;
import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.dao.PersonDao;
import com.hangdaoju.model.Person;
import com.hangdaoju.service.EmailSendService;
import com.hangdaoju.service.PersonService;
import com.hangdaoju.util.EncryptUtils;
import com.hangdaoju.util.StringToMap;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDao personDao;

	/**
	 * 后台创建用户
	 */
	@Override
	public String register(Person person, Model model) {
		try {
			person.setPassword(EncryptUtils.Encrypt3DES(person.getPassword(), Global.getCuntomConfig("saltKey")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 校验用户名是否存在
		Person checkPerson = new Person();
		checkPerson.setUserName(person.getUserName());
		Person onlyPerson = personDao.findOne(checkPerson);
		if (onlyPerson != null) {
			model.addAttribute("error", "用户名重复");
			return "/error";
		}
		person.setCreateTime(new Date());
		person.setUpdateTime(new Date());
		// person.setUpdateUser(updateUser);
		// person.setCreateUser(updateUser);
		personDao.save(person);
		// 插入结束后返回到列表页面
		model.addAttribute("personList", personDao.findAll());
		return "/view/person/personList";
	}

	/*
	 * 用户登录
	 * 
	 * @see com.hangdaoju.service.PersonService#login(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Person login(String userName) {
		Person person = new Person();
		person.setUserName(userName);
		return personDao.findOne(person);
	}

	/*
	 * 修改用户密码
	 * 
	 * @see com.hangdaoju.service.PersonService#updatePersonPassword(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResponseModel updatePersonPassword(String userName, String oldPassword, String newPassword)
			throws Exception {
		Person person = new Person();
		person.setUserName(userName);
		person = personDao.findOne(person);
		if (person == null) {
			return new ResponseModel(300, null, "用户不存在");
		}
		if (!person.getPassword().equals(EncryptUtils.Encrypt3DES(oldPassword, Global.getCuntomConfig("saltKey")))) {
			return new ResponseModel(300, null, "原密码输入错误");
		}
		Update update = null;
		update = new Update().set("password", EncryptUtils.Encrypt3DES(newPassword, Global.getCuntomConfig("saltKey")));
		Criteria cirteria = new Criteria("user_name");
		cirteria.is(userName);
		Query query = new Query(cirteria);
		personDao.updateMulti(query, update);
		/*
		 * //用戶修改后 将原本的缓存去掉 让它再进行时重新登陆 EHCacheUtil.initCacheManager("person");
		 * EHCacheUtil.remove(userName);
		 */
		return new ResponseModel(200, null, "修改成功");
	}

	/*
	 * 找回密码
	 * 
	 * @see com.hangdaoju.service.PersonService#backPassword(java.lang.String)
	 */
	@Override
	public ResponseModel backPassword(String userName, HttpServletRequest request) {
		Person person = new Person();
		person.setUserName(userName);
		person = personDao.findOne(person);
		if (person == null) {
			return new ResponseModel(300, null, "用户不存在");
		}
		/**
		 * 发送邮件是一个较长的时间，这里利用多线程来写
		 */
		EmailSendService emailSendService = new EmailSendService(request.getScheme(), request.getServerName(),
				request.getServerPort(), request.getServletContext().getContextPath(), person.getUserName(),
				person.getEmail());
		Thread t = new Thread(emailSendService);
		t.start();
		return new ResponseModel(200, null, "发送邮件成功");
	}

	/*
	 * 通过email修改密码
	 * 
	 * @see com.hangdaoju.service.PersonService#updatePasswordByEmail(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public ResponseModel updatePasswordByEmail(String userName, String password) {
		Criteria cirteria = new Criteria("user_name");
		cirteria.is(userName);
		Query query = new Query(cirteria);
		Update update = null;
		try {
			update = new Update().set("password",
					EncryptUtils.Encrypt3DES(password, Global.getCuntomConfig("saltKey")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		personDao.updateMulti(query, update);
		return new ResponseModel(200, null, "重置密码成功");
	}

	/*
	 * 邮箱通过点击进入后台Service层不经过数据库
	 * 
	 * @see com.hangdaoju.service.PersonService#emailPassword(java.lang.String,
	 * org.springframework.ui.Model)
	 */
	@Override
	public String emailPassword(String param, Model model) {
		param = param.replace(" ", "+");
		// 开始解密
		String decryptParam = null;
		try {
			decryptParam = EncryptUtils.Decrypt3DES(param, Global.getCuntomConfig("saltKey"));// 解密
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("error", "解密错误");
			return "error";
		}
		try {
			Map<String, String> paramMap = StringToMap.stringToMap(decryptParam);
			long backTime = Long.parseLong(paramMap.get("backTime"));
			if (System.currentTimeMillis() - backTime > 10 * 60 * 1000) {// 时间超时校验
				model.addAttribute("userName", paramMap.get("userName"));
				return "outTime";
			}
			model.addAttribute("userName", paramMap.get("userName"));
			return "success";
		} catch (Exception e) {
			model.addAttribute("error", "转换map异常");
			return "error";
		}
	}

	/*
	 * 获得person列表
	 * 
	 * @see com.hangdaoju.service.PersonService#getList()
	 */
	@Override
	public List<Person> getList() {
		return personDao.findAll();
	}

	@Override
	public List<Person> findByCondition(Person person) {
		return personDao.findByCondition(person);
	}

	/*
	 * 根据id删除用户
	 * 
	 * @see com.hangdaoju.service.PersonService#deletePerson(java.lang.String)
	 */
	@Override
	public void deletePerson(String id) {
		Person person = new Person();
		person.setId(id);
		personDao.delete(person);
	}

	/*
	 * 根据id查询用户
	 * 
	 * @see com.hangdaoju.service.PersonService#findById(java.lang.String,
	 * org.springframework.ui.Model)
	 */
	@Override
	public Person findById(String id) {
		Person person = personDao.findById(id);
		try {
			person.setPassword(EncryptUtils.Decrypt3DES(person.getPassword(), Global.getCuntomConfig("saltKey")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}

	/*
	 * 修改用户信息
	 * 
	 * @see
	 * com.hangdaoju.service.PersonService#update(com.hangdaoju.model.Person,
	 * org.springframework.ui.Model)
	 */
	@Override
	public void update(Person person) {
		String id = person.getId();
		try {
			person.setPassword(EncryptUtils.Encrypt3DES(person.getPassword(), Global.getCuntomConfig("saltKey")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		personDao.updateById(id, person);
	}

	@Override
	public String add(Person person) {
		person.setId(null);
		return personDao.save(person).getId();
	}
}
