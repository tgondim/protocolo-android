package thgg.android.protocolo.model;

public class Canal {

	private long id;
	
	private String descricao;
	
	public Canal(String descricao) {
		this.descricao = descricao;
	}
	
	Canal(long id, String descricao) {
		this(descricao);
		this.id = id;
	}
	
	public Canal() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		return this.getId() == ((Canal)o).getId();
	}
	
	@Override
	public String toString() {
		return this.getDescricao();
	}
}
