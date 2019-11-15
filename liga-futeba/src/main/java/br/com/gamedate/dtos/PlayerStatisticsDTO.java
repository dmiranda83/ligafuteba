package br.com.gamedate.dtos;

public class PlayerStatisticsDTO {
	
	private Object nome;
	private Object gols;
	private Object assists;
	private Object mediaGols;
	private Object freq;
	
	public Object getNome() {
		return nome;
	}
	public void setNome(Object nome) {
		this.nome = nome;
	}
	public Object getGols() {
		return gols;
	}
	public void setGols(Object gols) {
		this.gols = gols;
	}
	public Object getAssists() {
		return assists;
	}
	public void setAssists(Object assists) {
		this.assists = assists;
	}
	public Object getFreq() {
		return freq;
	}
	public void setFreq(Object freq) {
		this.freq = freq;
	}
	public Object getMediaGols() {
		return mediaGols;
	}
	public void setMediaGols(Object mediaGols) {
		this.mediaGols = mediaGols;
	}

}
