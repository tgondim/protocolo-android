package thgg.android.protocolo.model;

import java.util.Calendar;

public class Verba {

	private long id;
	
	private Cliente cliente;
	
	private String acao;
	
	private float valor;
	
	private Via via;
	
	private Canal canal;
	
	private Consultor consultor;
	
	private Calendar data;
	
	public Verba(Cliente cliente, String acao, float valor, Via via, Canal canal, Consultor consultor, Calendar data) {
		this.cliente = cliente;
		this.acao = acao;
		this.valor = valor;
		this.via = via;
		this.canal = canal;
		this.consultor = consultor;
		this.data = data;
	}

	Verba(long id, Cliente cliente, String acao, float valor, Via via, Canal canal, Consultor consultor, Calendar data) {
		this(cliente, acao, valor, via, canal, consultor, data);
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

	public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
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

	@Override
	public boolean equals(Object o) {
		return this.getId() == ((Verba)o).getId();
	}
	
	@Override
	public String toString() {
		return this.getCliente() + " " + this.getCanal() + " " + this.getConsultor();
	}
}
