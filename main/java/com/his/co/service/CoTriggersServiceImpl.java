package com.his.co.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.co.dao.CoBatchRunDetailsRepository;
import com.his.co.dao.CoBatchSummaryRepository;
import com.his.co.dao.CoPdfRepository;
import com.his.co.dao.CoTriggersRepository;
import com.his.co.entity.CoBatchRunDetailsEntity;
import com.his.co.entity.CoBatchSummaryEntity;
import com.his.co.entity.CoPdfEntity;
import com.his.co.entity.CoTriggersEntity;
import com.his.co.model.CoBatchRunDetailsModel;
import com.his.co.model.CoBatchSummaryModel;
import com.his.co.model.CoPdfModel;
import com.his.co.model.CoTriggersModel;
import com.his.util.AppConstants;
import com.his.util.DateFormator;

@Service("coTrgService")
public class CoTriggersServiceImpl implements CoTriggersService {

	@Autowired
	private CoTriggersRepository coTrgRepository;

	@Autowired
	private CoBatchSummaryRepository batchSummaryRepo;

	@Autowired
	private CoBatchRunDetailsRepository coBatchRunDetailRepository;
	@Autowired
	private CoPdfRepository coPdfRepository;

	/**
	 * This method is used to return all pending triggers
	 */
	@Override
	public List<CoTriggersModel> findPendingTriggers() {

		// making db call
		List<CoTriggersEntity> entities = coTrgRepository.findByTriggerStatus(AppConstants.PENDING_STATUS);
		List<CoTriggersModel> models = new ArrayList();
        if(entities.size()!=0) {
		for (CoTriggersEntity entity : entities) {
			CoTriggersModel model = new CoTriggersModel();
			// copying props from entity to model
			BeanUtils.copyProperties(entity, model);
			java.util.Date utildate=new Date(entity.getCreatedDate().getTime());
			model.setCreatedDate(utildate);
			models.add(model);
		}
		
		
        }

		return models;
	}

	@Override
	public boolean updatePendingTrigger(CoTriggersModel model) {
		CoTriggersEntity trgEntity = coTrgRepository.findById(model.getTriggerId()).get();
		trgEntity.setTriggerStatus(model.getTriggerStatus());
		coTrgRepository.save(trgEntity);
		return true;
	}

	@Override
	public CoBatchSummaryModel saveBatchSummary(CoBatchSummaryModel model) {
		CoBatchSummaryEntity entity = null;
		entity = new CoBatchSummaryEntity();
		// copy model to entity
		BeanUtils.copyProperties(model, entity);
		entity = batchSummaryRepo.save(entity);
		model.setSummaryId(entity.getSummaryId());
		return model;
	}// saveBatchSummary(CoBatchSummaryModel model)

	/**
	 * This method is used to insert batch run dtls
	 */
	@Override
	public CoBatchRunDetailsModel saveBatchRunDetails(CoBatchRunDetailsModel model) {
		Date startDate=null;
		CoBatchRunDetailsEntity entity = new CoBatchRunDetailsEntity();
		BeanUtils.copyProperties(model, entity);
		startDate=DateFormator.utilDateToSqlDate(model.getStartDate());
		entity.setStartDate(startDate);
		CoBatchRunDetailsEntity savedEntity = coBatchRunDetailRepository.save(entity);
		model.setRunSeq(entity.getRunSeq());
		return model;
	}

	@Override
	public CoPdfModel savePdf(CoPdfModel model) {
		 CoPdfEntity entity=null;
		 Integer pdfId=null;
		 entity=new CoPdfEntity();
		 //convert model to entity
		BeanUtils.copyProperties(model, entity);
		//call repository method
		pdfId=coPdfRepository.save(entity).getCoPdfId();
		model.setCoPdfId(pdfId);
		return model;
	}
}
