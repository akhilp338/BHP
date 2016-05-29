package com.belhopat.backoffice.dto;

/**
 * @author BHP_DEV
 * request POJO data transfer object
 *
 */
public class FamilyInfoDTO {
	
	private String spouse;
	
	private String father;
	
	private String mother;
	
	private String children;
	
	private String familyContact1;
	
	private String familyContact2;
	
	private String familyEmail;

	public String getSpouse() {
		return spouse;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public String getFamilyContact1() {
		return familyContact1;
	}

	public void setFamilyContact1(String familyContact1) {
		this.familyContact1 = familyContact1;
	}

	public String getFamilyContact2() {
		return familyContact2;
	}

	public void setFamilyContact2(String familyContact2) {
		this.familyContact2 = familyContact2;
	}

	public String getFamilyEmail() {
		return familyEmail;
	}

	public void setFamilyEmail(String familyEmail) {
		this.familyEmail = familyEmail;
	}
	
}