package com.his.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.his.entity.ARUserMaster;

@Repository
public interface ArRepository extends JpaRepository<ARUserMaster, Integer> {

	@Query("select count(*) from ARUserMaster a where a.email=?1")
	int checkEmailCount(String emailId);

	@Query("FROM ARUserMaster a WHERE a.email=?1")
	ARUserMaster findByEmailId(String emailId);

	@Query("FROM ARUserMaster a WHERE a.email=:userName and a.pwd=encryptedPassWord")
	ARUserMaster findByEmailIdAndPassword(String userName, String encryptedPassWord);

	
}
