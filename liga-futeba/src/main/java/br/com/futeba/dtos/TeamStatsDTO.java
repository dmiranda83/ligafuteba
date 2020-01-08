package br.com.futeba.dtos;

public class TeamStatsDTO {
	
	private Object squad;
	private Object points;
	private Object scoredGoals;
	private Object concededGoals;
	private Object balanceGoals;
	private Object games;
	private Object winPercentage;
	
	public Object getSquad() {
		return squad;
	}
	public void setSquad(Object quadro) {
		this.squad = quadro;
	}
	public Object getPoints() {
		return points;
	}
	public void setPoints(Object pontos) {
		this.points = pontos;
	}
	public Object getGolsMarcados() {
		return scoredGoals;
	}
	public void setGolsMarcados(Object golsMarcados) {
		this.scoredGoals = golsMarcados;
	}
	public Object getGolsSofridos() {
		return concededGoals;
	}
	public void setGolsSofridos(Object golsSofridos) {
		this.concededGoals = golsSofridos;
	}
	public Object getSaldoDeGol() {
		return balanceGoals;
	}
	public void setSaldoDeGol(Object saldoDeGol) {
		this.balanceGoals = saldoDeGol;
	}
	public Object getJogos() {
		return games;
	}
	public void setJogos(Object jogos) {
		this.games = jogos;
	}
	public Object getAproveitamento() {
		return winPercentage;
	}
	public void setAproveitamento(Object aproveitamento) {
		this.winPercentage = aproveitamento;
	}
	
}
