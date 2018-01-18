package br.com.ligafuteba.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "gols")
public class GamePlayerData implements Serializable {

    private static final long serialVersionUID = -8413600343702476736L;

    public GamePlayerData() {
    }

    @Id
    @Column(name = "gol_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer qtdGols;
    
    @NotNull
    private Integer presence;

    @ManyToOne(optional = true)
    @JoinColumn(name = "atleta_id", referencedColumnName = "atleta_id")
    private Atleta atleta;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "equipe_id", referencedColumnName = "equipe_id")
    private Equipe equipe;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getQtdGols() {
        return qtdGols;
    }

    public void setQtdGols(final Integer qtdGols) {
        this.qtdGols = qtdGols;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(final Atleta atleta) {
        this.atleta = atleta;
    }

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Integer getPresence() {
		return presence;
	}

	public void setPresence(Integer presence) {
		this.presence = presence;
	}

}
