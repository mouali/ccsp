package fr.ccsp.back.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Pli.
 */

@Document(collection = "pli")
public class Pli implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotNull
	@Field("description")
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public Pli description(String description) {
		this.description = description;
		return this;
	}

	public Pli() {
		super();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Pli(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Pli pli = (Pli) o;
		if (pli.id == null || id == null) {
			return false;
		}
		return Objects.equals(id, pli.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "Pli{" + "id=" + id + ", description='" + description + "'" + '}';
	}
}