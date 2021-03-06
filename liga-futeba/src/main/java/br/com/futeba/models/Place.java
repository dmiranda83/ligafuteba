package br.com.futeba.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

@Entity(name = "place")
public class Place implements Serializable {

	private static final long serialVersionUID = 2062908763035875940L;

	@Id
	@Column(name = "place_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String name;

	private String type;
	private String address;
	private String city;
	private String neighborhood;
	private String zipCode;

	public Place() {
	}

	public Place(Long id, @NotNull String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Place(@NotNull String name) {
		super();
		this.name = name;
	}
	public Place(Long id, @NotNull String name, String type, String zipCode) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.zipCode = zipCode;
	}

	public Place(@NotNull String name, String type, String zipCode) {
		super();
		this.name = name;
		this.type = type;
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(final String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}

	public Boolean isPlaceWithoutZipCode() {
		return StringUtils.isEmpty(this.zipCode);
	}
}
