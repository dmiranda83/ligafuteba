package br.com.futeba.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "esportes")
public class Category implements Serializable{

	private static final long serialVersionUID = -1295682222773267245L;

	public Category() {
		//Default constructor
    }

    @Id
    @Column(name = "esporte_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String nome;

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
}
