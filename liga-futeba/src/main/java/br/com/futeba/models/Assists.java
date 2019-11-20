package br.com.gamedate.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "assistencias")
public class Assists implements Serializable {


	private static final long serialVersionUID = -3633732656049254149L;

	public Assists() {
		//default constructor
    }

    @Id
    @Column(name = "assist_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer qtdAssist;

    @ManyToOne(optional = true)
    @JoinColumn(name = "atleta_id", referencedColumnName = "atleta_id")
    private Player atleta;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "equipe_id", referencedColumnName = "equipe_id")
    private Team equipe;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Player getAtleta() {
        return atleta;
    }

    public void setAtleta(final Player atleta) {
        this.atleta = atleta;
    }

	public Integer getQtdAssist() {
		return qtdAssist;
	}

	public void setQtdAssist(Integer qtdAssist) {
		this.qtdAssist = qtdAssist;
	}

	public Team getEquipe() {
		return equipe;
	}

	public void setEquipe(Team equipe) {
		this.equipe = equipe;
	}
}
