package thgg.android.protocolo.ui;

import java.util.List;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Via;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ViaAdapter extends BaseAdapter {

	private Context ctx;
	
	private List<Via> listaVias;

	public ViaAdapter(Context newCtx, List<Via> newListaVias) {
		this.ctx = newCtx;
		this.listaVias = newListaVias;
	}
	
	@Override
	public int getCount() {
		return listaVias.size();
	}

	@Override
	public Object getItem(int position) {
		return listaVias.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaVias.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Via via = listaVias.get(position);
		
		View view = LayoutInflater.from(ctx).inflate(R.layout.linha_via, null);
		
		TextView txtId = (TextView)view.findViewById(R.id.txtIdLinhaVia);
		TextView txtVia = (TextView)view.findViewById(R.id.txtDescricaoLinhaVia);
		
		txtId.setText(String.valueOf(via.getId()));
		txtVia.setText(via.getDescricao());
		
		return view;
	}

}
