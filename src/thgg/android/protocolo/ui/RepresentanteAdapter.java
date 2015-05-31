package thgg.android.protocolo.ui;

import java.util.List;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Representante;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RepresentanteAdapter extends BaseAdapter {

	private Context ctx;
	
	private List<Representante> listaRepresentantes;

	public RepresentanteAdapter(Context newCtx, List<Representante> newListaRepresentantes) {
		this.ctx = newCtx;
		this.listaRepresentantes = newListaRepresentantes;
	}
	
	@Override
	public int getCount() {
		return listaRepresentantes.size();
	}

	@Override
	public Object getItem(int position) {
		return listaRepresentantes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaRepresentantes.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Representante representante = listaRepresentantes.get(position);
		
		View view = LayoutInflater.from(ctx).inflate(R.layout.linha_representante, null);
		
		TextView txtId = (TextView)view.findViewById(R.id.txtIdLinhaRepresentante);
		TextView txtRepresentante = (TextView)view.findViewById(R.id.txtNomeLinhaRepresentante);
		
		txtId.setText(String.valueOf(representante.getId()));
		txtRepresentante.setText(representante.getNome());
		
		return view;
	}

}
