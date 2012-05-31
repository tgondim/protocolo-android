package thgg.android.protocolo.ui;

import java.util.List;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Evento;
import thgg.android.protocolo.util.StringUtils;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventoAdapter extends BaseAdapter {

	private Context ctx;
	
	private List<Evento> listaEventos;

	public EventoAdapter(Context newCtx, List<Evento> newListaEventos) {
		this.ctx = newCtx;
		this.listaEventos = newListaEventos;
	}
	
	@Override
	public int getCount() {
		return listaEventos.size();
	}

	@Override
	public Object getItem(int position) {
		return listaEventos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaEventos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Evento evento = listaEventos.get(position);
		
		View view = LayoutInflater.from(ctx).inflate(R.layout.linha_evento, null);
		
		TextView txtId = (TextView)view.findViewById(R.id.txtIdLinhaEvento);
		TextView txtTitulo = (TextView)view.findViewById(R.id.txtTituloLinhaEvento);
		TextView txtCliente = (TextView)view.findViewById(R.id.txtClienteLinhaEvento);
		TextView txtConsultor = (TextView)view.findViewById(R.id.txtConsultorLinhaEvento);
		TextView txtNumeroPessoas = (TextView)view.findViewById(R.id.txtNumeroPessoasLinhaEvento);
		TextView txtData = (TextView)view.findViewById(R.id.txtDataLinhaEvento);
		
		txtId.setText(String.valueOf(evento.getId()));
		txtTitulo.setText(evento.getTitulo());
		if (evento.getCliente() != null) {
			txtCliente.setText(evento.getCliente().getNome());			
		}
		if (evento.getConsultor() != null) {
			txtConsultor.setText(evento.getConsultor().getNome());			
		}
		txtNumeroPessoas.setText(String.valueOf(evento.getNumeroPessoas()) + " pessoas");
		if (evento.getData() != null) {
			txtData.setText(StringUtils.getDataHora(evento.getData()));
		}
		return view;
	}

}
