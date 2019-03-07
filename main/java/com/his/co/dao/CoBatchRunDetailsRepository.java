package com.his.co.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.his.co.entity.CoBatchRunDetailsEntity;

@Repository("coBatchRunDetailRepository")
public interface CoBatchRunDetailsRepository extends JpaRepository<CoBatchRunDetailsEntity, Serializable> {

}// CoBatchRunDetailsRepositor
