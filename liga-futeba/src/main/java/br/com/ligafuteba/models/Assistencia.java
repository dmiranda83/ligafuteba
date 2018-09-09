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

@Entity(name = "assistencias")
public class Assistencia implements Serializable {


	private static final long serialVersionUID = -3633732656049254149L;

	public Assistencia() {
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

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(final Atleta atleta) {
        this.atleta = atleta;
    }

	public Integer getQtdAssist() {
		return qtdAssist;
	}

	public void setQtdAssist(Integer qtdAssist) {
		this.qtdAssist = qtdAssist;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
}
