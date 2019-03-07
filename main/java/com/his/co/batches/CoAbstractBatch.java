package com.his.co.batches;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.his.co.model.CoBatchRunDetailsModel;
import com.his.co.model.CoBatchSummaryModel;
import com.his.co.model.CoTriggersModel;
import com.his.co.service.CoTriggersService;
import com.his.co.service.CoTriggersServiceImpl;
import com.his.util.AppConstants;

/**
 * This is class is used to define all co batches functionality
 * 
 * @author admin
 *
 */
public abstract class CoAbstractBatch {
	@Autowired
	CoTriggersService coTrgService;

	/**
	 * This method is used to perform pre-processing
	 * 
	 * @param batchName
	 */
	public void preProcess(String batchName) {
		CoBatchRunDetailsModel model = new CoBatchRunDetailsModel();
		model.setBatchName(batchName);
		model.setBatchRunStatus(AppConstants.BATCH_RUN_STATUS);
		model.setStartDate(new Date());
		//coTrgService=new CoTriggersServiceImpl();
		coTrgService.saveBatchRunDetails(model);
	}

	/**
	 * This method is used to perform postProcessing logic
	 * 
	 * @param batchName
	 * @param totalTrggers
	 * @param succssCnt
	 * @param failureCnt
	 */
	public void postProcess(String batchName, Integer totalTrggers, Integer succssCnt, Integer failureCnt) {
		CoBatchSummaryModel model = null;
		model = new CoBatchSummaryModel();
		model.setBatchName(batchName);
		model.setTotalTriggerProcessed(totalTrggers);
		model.setSuccessTriggerCount(succssCnt);
		model.setFailureTriggerCount(failureCnt);
		// update batch summary
		coTrgService.saveBatchSummary(model);

	}

	/**
	 * This method is used to start batch execution
	 */
	public abstract void start();

	/**
	 * This method is used to process each trigger
	 * 
	 * @param model
	 */
	public abstract void process(CoTriggersModel model) throws Exception;
}
