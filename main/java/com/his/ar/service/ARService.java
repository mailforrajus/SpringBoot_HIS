package com.his.ar.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.his.ar.entity.ARUserMaster;
import com.his.ar.model.UserMaster;

public interface ARService {
 
	public UserMaster saveUser(UserMaster um);
    
	public List<UserMaster> findAllUsers();
	
	public Page<ARUserMaster> findAllUsers(int pageNo,int pageSize);

	public UserMaster findUserByEmailAndPwd(String email, String pwd);
    
    public UserMaster findById(Integer userId);
    
	public UserMaster findByEmail(String email);
	
    public UserMaster findByIdEdit(int cid);

	public UserMaster saveUserUpadte(UserMaster um,boolean flage);
}
