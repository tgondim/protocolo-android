package thgg.android.protocolo.ui;

import java.util.List;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Canal;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CanalAdapter extends BaseAdapter {

	private Context ctx;
	
	private List<Canal> listaCanais;

	public CanalAdapter(Context newCtx, List<Canal> newListaCanais) {
		this.ctx = newCtx;
		this.listaCanais = newListaCanais;
	}
	
	@Override
	public int getCount() {
		return listaCanais.size();
	}

	@Override
	public Object getItem(int position) {
		return listaCanais.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaCanais.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Canal canal = listaCanais.get(position);
		
		View view = LayoutInflater.from(ctx).inflate(R.layout.linha_canal, null);
		
		TextView txtId = (TextView)view.findViewById(R.id.txtIdLinhaCanal);
		TextView txtCanal = (TextView)view.findViewById(R.id.txtDescricaoLinhaCanal);
		
		txtId.setText(String.valueOf(canal.getId()));
		txtCanal.setText(canal.getDescricao());
		
		return view;
	}

}
