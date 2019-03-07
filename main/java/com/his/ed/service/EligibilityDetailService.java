package com.his.ed.service;

import org.springframework.stereotype.Service;

import com.his.ed.model.EligibilityDetailModel;

@Service
public interface EligibilityDetailService {

	public EligibilityDetailModel findByCaseNum(Long caseNum);

}
