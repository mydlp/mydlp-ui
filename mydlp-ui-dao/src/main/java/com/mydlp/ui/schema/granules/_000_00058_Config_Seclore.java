package com.mydlp.ui.schema.granules;

import com.mydlp.ui.domain.Config;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00058_Config_Seclore extends AbstractGranule {

	@Override
	protected void callback() {

		Config secloreFSEnable = new Config();
		secloreFSEnable.setKey("seclore_fs_enable");
		secloreFSEnable.setValue("false");
		
		Config secloreFSAddress = new Config();
		secloreFSAddress.setKey("seclore_fs_address");
		secloreFSAddress.setValue("127.0.0.1");
		
		Config secloreFSPort = new Config();
		secloreFSPort.setKey("seclore_fs_port");
		secloreFSPort.setValue("443");
		
		Config secloreFSAppName = new Config();
		secloreFSAppName.setKey("seclore_fs_app_name");
		secloreFSAppName.setValue("policyserver");
		
		Config secloreFSHotFolderCabinetId = new Config();
		secloreFSHotFolderCabinetId.setKey("seclore_fs_hot_folder_cabinet_id");
		secloreFSHotFolderCabinetId.setValue("6");
		
		Config secloreFSHotFolderCabinetPassphrase = new Config();
		secloreFSHotFolderCabinetPassphrase.setKey("seclore_fs_hot_folder_cabinet_passphrase");
		secloreFSHotFolderCabinetPassphrase.setValue("seclore10");
		
		Config secloreFSServerPoolSize = new Config();
		secloreFSServerPoolSize.setKey("seclore_fs_server_pool_size");
		secloreFSServerPoolSize.setValue("8");
		
		Config secloreFSEndpointPoolSize = new Config();
		secloreFSEndpointPoolSize.setKey("seclore_fs_endpoint_pool_size");
		secloreFSEndpointPoolSize.setValue("2");
		
		getHibernateTemplate().saveOrUpdate(secloreFSEnable);
		getHibernateTemplate().saveOrUpdate(secloreFSAddress);
		getHibernateTemplate().saveOrUpdate(secloreFSPort);
		getHibernateTemplate().saveOrUpdate(secloreFSAppName);
		getHibernateTemplate().saveOrUpdate(secloreFSHotFolderCabinetId);
		getHibernateTemplate().saveOrUpdate(secloreFSHotFolderCabinetPassphrase);
		getHibernateTemplate().saveOrUpdate(secloreFSServerPoolSize);
		getHibernateTemplate().saveOrUpdate(secloreFSEndpointPoolSize);

	}

}
