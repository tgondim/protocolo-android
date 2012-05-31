package thgg.android.protocolo.model;

import java.util.Calendar;

public class Evento {

	private long id;
	
	private String titulo;
	
	private Cliente cliente;
	
	private Consultor consultor;
	
	private int numeroPessoas;
	
	private Calendar data;
	
	public Evento(String titulo, Cliente cliente, Consultor consultor,
			int numeroPessoas, Calendar data) {
		this.titulo = titulo;
		this.cliente = cliente;
		this.consultor = consultor;
		this.numeroPessoas = numeroPessoas;
		this.data = data;
	}

	Evento(long id, String titulo, Cliente cliente,
			Consultor consultor, int numeroPessoas, Calendar data) {
		this(titulo, cliente, consultor, numeroPessoas, data);
		this.id = id;
	}
	
	public Evento() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}

	public int getNumeroPessoas() {
		return numeroPessoas;
	}

	public void setNumeroPessoas(int numeroPessoas) {
		this.numeroPessoas = numeroPessoas;
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
		return this.getId() == ((Evento)o).getId();
	}
	
	@Override
	public String toString() {
		return this.getTitulo() + " " + this.getCliente() + " " + 
			this.getConsultor();
	}
	
}
