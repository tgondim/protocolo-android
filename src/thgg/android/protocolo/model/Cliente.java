package thgg.android.protocolo.model;

public class Cliente {

	private long id;
	
	private String nome;

	public Cliente(String nome) {
		this.nome = nome;
	}

	Cliente(long id, String nome) {
		this(nome);
		this.id = id;
	}
	
	public Cliente() {
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
		return this.getId() == ((Cliente)o).getId();
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
