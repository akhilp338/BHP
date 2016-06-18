package com.belhopat.backoffice.dto;

/**
 * @author BHP_DEV
 * request POJO data transfer object
 *
 */
public class EmployeeViewDTO {
	
	private CandidateViewDTO candidate;
	
	private EmploymentInfoDTO employment;

	private OfficialInfoDTO official;
	
	private FamilyInfoDTO family;

	public EmploymentInfoDTO getEmployment() {
		return employment;
	}

	public CandidateViewDTO getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateViewDTO candidate) {
		this.candidate = candidate;
	}

	public void setEmployment(EmploymentInfoDTO employment) {
		this.employment = employment;
	}

	public OfficialInfoDTO getOfficial() {
		return official;
	}

	public void setOfficial(OfficialInfoDTO official) {
		this.official = official;
	}

	public FamilyInfoDTO getFamily() {
		return family;
	}

	public void setFamily(FamilyInfoDTO family) {
		this.family = family;
	}

}