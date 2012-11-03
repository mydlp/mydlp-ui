package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.InventoryBase;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN})
public interface InventoryService {

	public List<InventoryBase> getInventory();
	
	public InventoryBase save(InventoryBase item);
	
	public void saveAll(List<InventoryBase> items);

	public void remove(InventoryBase item);
}