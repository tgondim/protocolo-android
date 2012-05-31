package thgg.android.protocolo.model;

public class Via {
	
	private long id;
	
	private String descricao;

	public Via(String descricao) {
		this.descricao = descricao;
	}

	Via(long id, String descricao) {
		this(descricao);
		this.id = id;
	}
	
	public Via() {
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
		return this.getId() == ((Via)o).getId();
	}
	
	@Override
	public String toString() {
		return this.getDescricao();
	}

}
