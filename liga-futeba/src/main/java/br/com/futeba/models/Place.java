package br.com.futeba.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "estabelecimentos")
public class Place implements Serializable{

	private static final long serialVersionUID = 2062908763035875940L;

	@Id
    @Column(name = "estabelecimento_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String nome;

    private String tipo;
    private String endereco;
    private String cidade;
    private String bairro;
    private String cep;

    public Place() {
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

    public String getType() {
        return tipo;
    }

    public void setType(final String tipo) {
        this.tipo = tipo;
    }

    public String getAddress() {
        return endereco;
    }

    public void setAddress(final String endereco) {
        this.endereco = endereco;
    }

    public String getCity() {
        return cidade;
    }

    public void setCity(final String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(final String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(final String cep) {
        this.cep = cep;
    }

}
