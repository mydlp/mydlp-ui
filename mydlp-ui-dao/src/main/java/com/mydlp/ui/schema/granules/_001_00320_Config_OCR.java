package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _001_00320_Config_OCR extends AbstractGranule {

	@Override
	protected void callback() {

		Config numberOfThreads = new Config();
		numberOfThreads.setKey("ocr_number_of_threads");
		numberOfThreads.setValue("1");

		Config maxProcessingAge = new Config();
		maxProcessingAge.setKey("ocr_max_processing_age");
		maxProcessingAge.setValue("180000");

		Config waitingQueueSize = new Config();
		waitingQueueSize.setKey("ocr_waiting_queue_size");
		waitingQueueSize.setValue("100");


		getHibernateTemplate().saveOrUpdate(numberOfThreads);
		getHibernateTemplate().saveOrUpdate(maxProcessingAge);
		getHibernateTemplate().saveOrUpdate(waitingQueueSize);	
	}

}
