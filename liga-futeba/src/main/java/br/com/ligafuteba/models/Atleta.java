package br.com.ligafuteba.models;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "atletas")
public class Atleta implements Serializable {

    private static final long serialVersionUID = 2531069826794464004L;

    @Id
    @Column(name = "atleta_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "posicao_id", referencedColumnName = "posicao_id")
    private Posicao posicao;

    @ManyToMany(mappedBy = "atletas", cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
    @JsonIgnoreProperties("atletas")
    private List<Equipe> equipes = new ArrayList<>();

    public Atleta() {
        // TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Posicao getPosicoes() {
        return posicao;
    }

    public void setPosicoes(final Posicao posicoes) {
        this.posicao = posicoes;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(final List<Equipe> equipes) {
        this.equipes = equipes;
    }
}
