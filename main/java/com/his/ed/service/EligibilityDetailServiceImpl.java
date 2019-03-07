package com.his.ed.service;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.ed.dao.EligibilityRepository;
import com.his.ed.entity.EligibilityDetailEntity;
import com.his.ed.model.EligibilityDetailModel;
import com.his.util.DateFormator;

@Service("edDetailService")
public class EligibilityDetailServiceImpl implements EligibilityDetailService {

	@Autowired
	private EligibilityRepository eligRepository;

	@Override
	public EligibilityDetailModel findByCaseNum(Long caseNum) {
		Date createdDate=null;
		Date updatedDate=null;
		EligibilityDetailModel model=null;
		EligibilityDetailEntity entity = eligRepository.findByCaseNum(caseNum);
		if(null!=entity) {
		 model = new EligibilityDetailModel();
		createdDate=DateFormator.sqlDateToUtilDate(entity.getCreatedDate());
		updatedDate=DateFormator.sqlDateToUtilDate(entity.getUpdatedDate());
		BeanUtils.copyProperties(entity, model);
		model.setCreatedDate(createdDate);
		model.setUpdatedDate(updatedDate);
	}
		return model;
	}

}
