package thgg.android.protocolo.model;

import java.io.Serializable;

public class Representada implements Serializable{

	private static final long serialVersionUID = -3694751907108247461L;

	private long id;
	
	private String razaoSocial;
	
	public Representada(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	Representada(long id, String razaoSocial) {
		this(razaoSocial);
		this.id = id;
	}
	
	public Representada() {
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public long getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		return this.getId() == ((Representada)o).getId();
	}
	
	@Override
	public String toString() {
		return this.getRazaoSocial();
	}
}
