package thgg.android.protocolo.model;

import java.io.Serializable;

public class Representante implements Serializable {

	private static final long serialVersionUID = 8189829125153882267L;

	private long id;
	
	private String nome;

	public Representante(String nome) {
		this.nome = nome;
	}

	Representante(long id, String nome) {
		this(nome);
		this.id = id;
	}
	
	public Representante() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.getId() == ((Representante)o).getId();
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
