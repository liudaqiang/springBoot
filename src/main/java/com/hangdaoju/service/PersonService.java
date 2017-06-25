package com.hangdaoju.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.Person;

public interface PersonService {
	/**
	 * 后台用户注册
	 * 
	 * @param userName
	 * @param password
	 * @param email
	 * @return
	 */
	public String register(Person person, Model model);

	/**
	 * 用户登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public Person login(String userName);

	/**
	 * 修改用户密码
	 * 
	 * @param userName
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public ResponseModel updatePersonPassword(String userName, String oldPassword, String newPassword) throws Exception;

	/**
	 * 找回密码
	 * 
	 * @param userName
	 * @return
	 */
	public ResponseModel backPassword(String userName, HttpServletRequest request);

	/**
	 * 通过email方式修改密码
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public ResponseModel updatePasswordByEmail(String userName, String password);

	/**
	 * 邮箱通过点击进入后台Service层不经过数据库
	 * 
	 * @param param
	 * @return
	 */
	public String emailPassword(String param, Model model);

	/**
	 * 获得用户列表(一会加分页)
	 * 
	 * @return
	 */
	public List<Person> getList();

	public List<Person> findByCondition(Person person);

	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 * @return
	 */
	public void deletePerson(String id);

	/**
	 * 根据id查询用户
	 * 
	 * @param id
	 * @return
	 */
	public Person findById(String id);

	/**
	 * 修改用户信息
	 * 
	 * @param person
	 * @param model
	 * @return
	 */
	public void update(Person person);

	String add(Person person);
}
