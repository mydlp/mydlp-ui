package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.InventoryCategory;

@Secured(AuthSecurityRole.ROLE_USER)
public interface InventoryService {

	public List<InventoryCategory> getInventory();
	
	public InventoryBase save(InventoryBase item);
	
	public void saveAll(List<InventoryBase> items);

	public void remove(InventoryBase item);
}