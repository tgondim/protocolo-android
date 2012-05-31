package thgg.android.protocolo.ui;

import java.util.List;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Consultor;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ConsultorAdapter extends BaseAdapter {

	private Context ctx;
	
	private List<Consultor> listaConsultores;

	public ConsultorAdapter(Context newCtx, List<Consultor> newListaConsultores) {
		this.ctx = newCtx;
		this.listaConsultores = newListaConsultores;
	}
	
	@Override
	public int getCount() {
		return listaConsultores.size();
	}

	@Override
	public Object getItem(int position) {
		return listaConsultores.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaConsultores.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Consultor consultor = listaConsultores.get(position);
		
		View view = LayoutInflater.from(ctx).inflate(R.layout.linha_consultor, null);
		
		TextView txtId = (TextView)view.findViewById(R.id.txtIdLinhaConsultor);
		TextView txtConsultor = (TextView)view.findViewById(R.id.txtNomeLinhaConsultor);
		
		txtId.setText(String.valueOf(consultor.getId()));
		txtConsultor.setText(consultor.getNome());
		
		return view;
	}

}
