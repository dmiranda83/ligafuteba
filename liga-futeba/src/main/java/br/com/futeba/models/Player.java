package br.com.futeba.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "atletas")
public class Player implements Serializable {

    private static final long serialVersionUID = 2531069826794464004L;

    @Id
    @Column(name = "atleta_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "posicao_id", referencedColumnName = "posicao_id")
    private Position posicao;

    @ManyToMany(mappedBy = "atletas", cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
    @JsonIgnoreProperties("atletas")
    private List<Team> equipes = new ArrayList<>();

    public Player() {
    	//default constructor
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

    public Position getPosition() {
        return posicao;
    }

    public void setPosition(final Position posicoes) {
        this.posicao = posicoes;
    }

    public List<Team> getTeams() {
        return equipes;
    }

    public void setTeams(final List<Team> equipes) {
        this.equipes = equipes;
    }
}
