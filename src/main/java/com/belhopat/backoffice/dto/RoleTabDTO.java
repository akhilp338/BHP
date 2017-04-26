package com.belhopat.backoffice.dto;

import java.util.List;

/**
 * @author BHP_DEV request POJO data transfer object
 *
 */
public class RoleTabDTO {

	private Long masterRoleId;

	private List<Long> activeTabIds;

	public Long getMasterRoleId() {
		return masterRoleId;
	}

	public void setMasterRoleId(Long masterRoleId) {
		this.masterRoleId = masterRoleId;
	}

	public List<Long> getActiveTabIds() {
		return activeTabIds;
	}

	public void setActiveTabIds(List<Long> activeTabIds) {
		this.activeTabIds = activeTabIds;
	}

	@Override
	public String toString() {
		return "RoleTabDTO [masterRoleId=" + masterRoleId + ", activeTabIds=" + activeTabIds + "]";
	}
}