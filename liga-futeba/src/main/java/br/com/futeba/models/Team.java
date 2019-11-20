package br.com.futeba.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "equipes")
public class Team implements Serializable {

    private static final long serialVersionUID = 3457244849292203050L;

    public Team() {
    	//Default constructor
    }

    @Id
    @Column(name = "equipe_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String nome;

    @NotNull
    private Boolean visitante;

    @NotNull
    private String responsibleName;

    @NotNull
    private String phoneContact1;
    
    private String phoneContact2;

    @ManyToOne
    @JoinColumn(name = "esporte_id", referencedColumnName = "esporte_id")
    private Category esporte;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id", referencedColumnName = "estabelecimento_id")
    private Place estabelecimento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "equipe_atleta", joinColumns = { @JoinColumn(name = "equipe_id") }, inverseJoinColumns = { @JoinColumn(name = "atleta_id") })
    @JsonIgnoreProperties("equipes")
    private List<Player> atletas = new ArrayList<>();

    @OneToMany(mappedBy = "equipeVisitante")
    private List<Game> jogo;

    public Boolean getAway() {
        return visitante;
    }

    public void setAway(final Boolean visitante) {
        this.visitante = visitante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return nome;
    }

    public void setName(final String nome) {
        this.nome = nome;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(final String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getPhoneContact1() {
        return phoneContact1;
    }

    public void setPhoneContact1(final String phoneContact1) {
        this.phoneContact1 = phoneContact1;
    }

    public String getPhoneContact2() {
        return phoneContact2;
    }

    public void setPhoneContact2(final String phoneContact2) {
        this.phoneContact2 = phoneContact2;
    }

    public Category getCategory() {
        return esporte;
    }

    public void setCategory(final Category esporte) {
        this.esporte = esporte;
    }

    public Place getPlace() {
        return estabelecimento;
    }

    public void setPlace(final Place estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<Player> getPlayers() {
        return atletas;
    }

    public void setPlayers(final List<Player> atletas) {
        this.atletas = atletas;
    }
}
