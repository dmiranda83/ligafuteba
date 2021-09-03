package br.com.futeba.dtos;

import java.util.List;

public class TeamDTO {
	private Long id;
	private String name;
	private Boolean away;
	private String responsibleName;
	private String phoneContact1;
	private String phoneContact2;
	private String categoryId;
	private String placeID;
	List<String> playerIds;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getAway() {
		return away;
	}
	public void setAway(Boolean away) {
		this.away = away;
	}
	public String getResponsibleName() {
		return responsibleName;
	}
	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}
	public String getPhoneContact1() {
		return phoneContact1;
	}
	public void setPhoneContact1(String phoneContact1) {
		this.phoneContact1 = phoneContact1;
	}
	public String getPhoneContact2() {
		return phoneContact2;
	}
	public void setPhoneContact2(String phoneContact2) {
		this.phoneContact2 = phoneContact2;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getPlaceID() {
		return placeID;
	}
	public void setPlaceID(String placerID) {
		this.placeID = placerID;
	}
	public List<String> getPlayerIds() {
		return playerIds;
	}
	public void setPlayerIds(List<String> playerIds) {
		this.playerIds = playerIds;
	}
}
