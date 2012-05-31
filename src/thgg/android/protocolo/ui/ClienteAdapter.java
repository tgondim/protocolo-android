package thgg.android.protocolo.ui;

import java.util.List;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Cliente;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClienteAdapter extends BaseAdapter {

	private Context ctx;
	
	private List<Cliente> listaClientes;

	public ClienteAdapter(Context newCtx, List<Cliente> newListaClientes) {
		this.ctx = newCtx;
		this.listaClientes = newListaClientes;
	}
	
	@Override
	public int getCount() {
		return listaClientes.size();
	}

	@Override
	public Object getItem(int position) {
		return listaClientes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaClientes.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Cliente cliente = listaClientes.get(position);
		
		View view = LayoutInflater.from(ctx).inflate(R.layout.linha_cliente, null);
		
		TextView txtId = (TextView)view.findViewById(R.id.txtIdLinhaCliente);
		TextView txtCliente = (TextView)view.findViewById(R.id.txtNomeLinhaCliente);
		
		txtId.setText(String.valueOf(cliente.getId()));
		txtCliente.setText(cliente.getNome());
		
		return view;
	}

}
