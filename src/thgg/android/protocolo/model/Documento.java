package thgg.android.protocolo.model;

import java.util.Calendar;

public class Documento {

	private long id;
	
	private String descricao;
	private String destino;
	private Via via;
	
	private Calendar data;
	
	public Documento(String descricao, String destino, Via via, Calendar data) {
		super();
		this.descricao = descricao;
		this.destino = destino;
		this.via = via;
		this.data = data;
	}

	Documento(long id, String descricao, String destino, Via via,
			Calendar dataHora) {
		this(descricao, destino, via, dataHora);
		this.id = id;
	}

	public Documento() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar dataHora) {
		this.data = dataHora;
	}

	public long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.getId() == ((Documento)o).getId();
	}
}
