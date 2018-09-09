package br.com.ligafuteba.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "jogos")
public class Jogo implements Serializable {

    private static final long serialVersionUID = 2531069826794464004L;

    @Id
    @Column(name = "jogo_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Timestamp data;

    private Timestamp hora;
    
    private Integer quadro;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id", referencedColumnName = "estabelecimento_id")
    private Estabelecimento local;

    @ManyToOne(optional = true)
    @JoinColumn(name = "equipe_mandante_id", referencedColumnName = "equipe_id")
    private Equipe equipeMandante;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<GamePlayerData> gamePlayerData;
    
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<GamePlayerData> golsMandante;
    
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Assistencia> assMandante;

    @ManyToOne(optional = true)
    @JoinColumn(name = "equipe_visitante_id", referencedColumnName = "equipe_id")
    private Equipe equipeVisitante;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<GamePlayerData> golsVisitante;
    
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Assistencia> assVisitante;
    
    private Integer totalGolsMandante;
    
    private Integer totalGolsVisitante;
    
    private Integer pontos;

    public Jogo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(final Timestamp data) {
        this.data = data;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(final Timestamp hora) {
        this.hora = hora;
    }

    public Estabelecimento getLocal() {
        return local;
    }

    public void setLocal(final Estabelecimento local) {
        this.local = local;
    }

    public Equipe getEquipeMandante() {
        return equipeMandante;
    }

    public void setEquipeMandante(final Equipe equipeMandante) {
        this.equipeMandante = equipeMandante;
    }

    public Equipe getEquipeVisitante() {
        return equipeVisitante;
    }

    public void setEquipeVisitante(final Equipe equipeVisitante) {
        this.equipeVisitante = equipeVisitante;
    }

    public Set<GamePlayerData> getGamePlayerData() {
        return gamePlayerData;
    }

    public void setGamePlayerData(final Set<GamePlayerData> gamePlayerData) {
        this.gamePlayerData = gamePlayerData;
    }

    public Set<GamePlayerData> getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(final Set<GamePlayerData> golsVisitante) {
        this.golsVisitante = golsVisitante;
    }

	public Integer getQuadro() {
		return quadro;
	}

	public void setQuadro(Integer quadro) {
		this.quadro = quadro;
	}

	public Integer getTotalGolsMandante() {
		return totalGolsMandante;
	}

	public void setTotalGolsMandante(Integer totalGolsMandante) {
		this.totalGolsMandante = totalGolsMandante;
	}

	public Integer getTotalGolsVisitante() {
		return totalGolsVisitante;
	}

	public void setTotalGolsVisitante(Integer totalGolsVisitante) {
		this.totalGolsVisitante = totalGolsVisitante;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Set<Assistencia> getAssMandante() {
		return assMandante;
	}

	public void setAssMandante(Set<Assistencia> assMandante) {
		this.assMandante = assMandante;
	}

	public Set<Assistencia> getAssVisitante() {
		return assVisitante;
	}

	public void setAssVisitante(Set<Assistencia> assVisitante) {
		this.assVisitante = assVisitante;
	}

	public Set<GamePlayerData> getGolsMandante() {
		return golsMandante;
	}

	public void setGolsMandante(Set<GamePlayerData> golsMandante) {
		this.golsMandante = golsMandante;
	}
}
