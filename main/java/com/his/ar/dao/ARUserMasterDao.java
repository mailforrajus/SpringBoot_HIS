package com.his.ar.dao;

import java.io.Serializable;

/***
 * this is class user for db operation
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.his.ar.entity.ARUserMaster;

@Repository("arUserMasterDao")
public interface ARUserMasterDao extends JpaRepository<ARUserMaster, Serializable> {

	@Query("SELECT ar FROM ARUserMaster ar where ar.email=:email")
	ARUserMaster findByEmail(@Param("email") String email);

	/***
	 * this is used for app login for case worker
	 * 
	 * @param email
	 * @param pwd
	 * @param activeSw
	 * @return class object
	 */
	@Query("SELECT ar FROM ARUserMaster ar where ar.email =:email and pwd =:pwd")
	ARUserMaster findUserByEmailAndPwd(@Param("email") String email, @Param("pwd") String pwd);

}
