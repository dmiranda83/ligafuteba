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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "equipes")
public class Equipe implements Serializable {

    private static final long serialVersionUID = 3457244849292203050L;

    public Equipe() {
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
    private String nome_responsavel;

    @NotNull
    private String telefone_contato1;

    private String telefone_contato2;

    @ManyToOne
    @JoinColumn(name = "esporte_id", referencedColumnName = "esporte_id")
    private Esporte esporte;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id", referencedColumnName = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "equipe_atleta", joinColumns = { @JoinColumn(name = "equipe_id") }, inverseJoinColumns = { @JoinColumn(name = "atleta_id") })
    @JsonIgnoreProperties("equipes")
    private List<Atleta> atletas = new ArrayList<>();

    @OneToMany(mappedBy = "equipeVisitante")
    private List<Jogo> jogo;

    public Boolean getVisitante() {
        return visitante;
    }

    public void setVisitante(final Boolean visitante) {
        this.visitante = visitante;
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

    public String getNome_responsavel() {
        return nome_responsavel;
    }

    public void setNome_responsavel(final String nome_responsavel) {
        this.nome_responsavel = nome_responsavel;
    }

    public String getTelefone_contato1() {
        return telefone_contato1;
    }

    public void setTelefone_contato1(final String telefone_contato1) {
        this.telefone_contato1 = telefone_contato1;
    }

    public String getTelefone_contato2() {
        return telefone_contato2;
    }

    public void setTelefone_contato2(final String telefone_contato2) {
        this.telefone_contato2 = telefone_contato2;
    }

    public Esporte getEsporte() {
        return esporte;
    }

    public void setEsporte(final Esporte esporte) {
        this.esporte = esporte;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(final Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(final List<Atleta> atletas) {
        this.atletas = atletas;
    }
}
