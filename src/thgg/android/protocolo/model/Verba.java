package thgg.android.protocolo.model;

import java.util.Calendar;

public class Verba {

	private long id;
	
	private Cliente cliente;
	
	private String acao;
	
	private float valor;
	
	private Via via;
	
	private Canal canal;
	
	private Representante representante;

	private Representada representada;
	
	private Calendar data;
	
	public Verba(Cliente cliente, String acao, float valor, Via via, Canal canal, Representante representante, Representada representada, Calendar data) {
		this.cliente = cliente;
		this.acao = acao;
		this.valor = valor;
		this.via = via;
		this.canal = canal;
		this.representante = representante;
		this.representada = representada;
		this.data = data;
	}

	Verba(long id, Cliente cliente, String acao, float valor, Via via, Canal canal, Representante representante, Representada representada, Calendar data) {
		this(cliente, acao, valor, via, canal, representante, representada, data);
		this.id = id;
	}
	
	public Verba() {
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public long getId() {
		return id;
	}

	public Representada getRepresentada() {
		return representada;
	}

	public void setRepresentada(Representada representada) {
		this.representada = representada;
	}

	@Override
	public boolean equals(Object o) {
		return this.getId() == ((Verba)o).getId();
	}
	
	@Override
	public String toString() {
		return this.getCliente() + " " + this.getCanal() + " " + this.getRepresentante() + " " + this.getRepresentada();
	}
}
