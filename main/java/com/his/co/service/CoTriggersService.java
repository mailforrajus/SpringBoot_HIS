package com.his.co.service;

import java.util.List;

import com.his.co.model.CoBatchRunDetailsModel;
import com.his.co.model.CoBatchSummaryModel;
import com.his.co.model.CoPdfModel;
import com.his.co.model.CoTriggersModel;

public interface CoTriggersService {

	public List<CoTriggersModel> findPendingTriggers();

	public boolean updatePendingTrigger(CoTriggersModel model);

	public CoBatchSummaryModel saveBatchSummary(CoBatchSummaryModel model);

	public CoBatchRunDetailsModel saveBatchRunDetails(CoBatchRunDetailsModel model);
	public CoPdfModel savePdf(CoPdfModel model);

}
