package com.his.co.model;

import lombok.Data;

@Data()
public class CoBatchSummaryModel {
	private Integer summaryId;
	private String batchName;
	Integer totalTriggerProcessed;
	private Integer failureTriggerCount;
	private Integer successTriggerCount;

}// BatchSummaryModel
