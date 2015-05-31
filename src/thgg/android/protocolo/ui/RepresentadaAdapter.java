package thgg.android.protocolo.ui;

import java.util.List;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Representada;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RepresentadaAdapter extends BaseAdapter {

	private Context ctx;
	
	private List<Representada> listaRepresentadas;

	public RepresentadaAdapter(Context newCtx, List<Representada> newListaRepresentadas) {
		this.ctx = newCtx;
		this.listaRepresentadas = newListaRepresentadas;
	}
	
	@Override
	public int getCount() {
		return listaRepresentadas.size();
	}

	@Override
	public Object getItem(int position) {
		return listaRepresentadas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaRepresentadas.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Representada representada = listaRepresentadas.get(position);
		
		View view = LayoutInflater.from(ctx).inflate(R.layout.linha_representada, null);
		
		TextView txtId = (TextView)view.findViewById(R.id.txtIdLinhaRepresentada);
		TextView txtRepresentada = (TextView)view.findViewById(R.id.txtRazaoSocialLinhaRepresentada);
		
		txtId.setText(String.valueOf(representada.getId()));
		txtRepresentada.setText(representada.getRazaoSocial());
		
		return view;
	}

}
