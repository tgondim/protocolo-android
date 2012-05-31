package thgg.android.protocolo.model;

public class Consultor {

	private long id;
	
	private String nome;

	public Consultor(String nome) {
		this.nome = nome;
	}

	Consultor(long id, String nome) {
		this(nome);
		this.id = id;
	}
	
	public Consultor() {
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
		return this.getId() == ((Consultor)o).getId();
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
