package thgg.android.protocolo.model;

import java.io.Serializable;
import java.util.Calendar;

public class Pedido implements Serializable {

	private static final long serialVersionUID = -305354360153256724L;

	private long id;
	
	private String numero;
	
	private Representada representada;
	
	private Cliente cliente;
	
	private Representante representante;
	
	private double valorTotal;
	
	private Calendar dataEmissao;
	
	public Pedido(long id, String numero, double valorTotal, Calendar dataEmissao) {
		this.id= id;
		this.numero = numero;
		this.valorTotal = valorTotal;
		this.dataEmissao = dataEmissao;
	}
	
	public Pedido(long id, String numero, Representada representada, Cliente cliente, Representante representante, double valorTotal, Calendar dataEmissao) {
		this(id, numero, valorTotal, dataEmissao);
		this.representada = representada;
		this.cliente = cliente;
		this.representante = representante;
	}
	
	public Pedido() {
	}

	public long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Representada getRepresentada() {
		return representada;
	}

	public void setRepresentada(Representada representada) {
		this.representada = representada;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public Calendar getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Calendar dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.getId() == ((Pedido)o).getId();
	}
	
	@Override
	public String toString() {
		return this.getNumero();
	}
}
