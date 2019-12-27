package br.com.futeba.dtos;

public class PlayerStatsDTO {
	
	private Object name;
	private Object goals;
	private Object assists;
	private Object goalsAverage;
	private Object frequency;
	
	public Object getName() {
		return name;
	}
	public void setName(Object name) {
		this.name = name;
	}
	public Object getGoals() {
		return goals;
	}
	public void setGoals(Object goals) {
		this.goals = goals;
	}
	public Object getAssists() {
		return assists;
	}
	public void setAssists(Object assists) {
		this.assists = assists;
	}
	public Object getFrequency() {
		return frequency;
	}
	public void setFrequency(Object frequency) {
		this.frequency = frequency;
	}
	public Object getGoalsAverage() {
		return goalsAverage;
	}
	public void setGoalsAverage(Object goalsAverage) {
		this.goalsAverage = goalsAverage;
	}

}
