package com.belhopat.backoffice.dto;

/**
 * @author BHP_DEV
 * request POJO data transfer object
 *
 */
public class CandidateViewDTO {
	
	private PersonalInfoDTO personal;
	
	private EmploymentInfoDTO employment;

	private OfficialInfoDTO official;
	
	private FamilyInfoDTO family;

	public PersonalInfoDTO getPersonal() {
		return personal;
	}

	public void setPersonal(PersonalInfoDTO personal) {
		this.personal = personal;
	}

	public EmploymentInfoDTO getEmployment() {
		return employment;
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