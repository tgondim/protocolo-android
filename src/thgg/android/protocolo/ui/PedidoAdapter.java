package thgg.android.protocolo.ui;

import java.text.DecimalFormat;
import java.util.List;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Pedido;
import thgg.android.protocolo.util.StringUtils;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PedidoAdapter extends BaseAdapter {

	private Context ctx;
	
	private List<Pedido> listaPedidos;

	public PedidoAdapter(Context newCtx, List<Pedido> newListaPedidos) {
		this.ctx = newCtx;
		this.listaPedidos = newListaPedidos;
	}
	
	@Override
	public int getCount() {
		return listaPedidos.size();
	}

	@Override
	public Object getItem(int position) {
		return listaPedidos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaPedidos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Pedido pedido = listaPedidos.get(position);
		
		View view = LayoutInflater.from(ctx).inflate(R.layout.linha_pedido, null);
		
		TextView txtId = (TextView)view.findViewById(R.id.txtIdLinhaPedido);
		TextView txtNumero = (TextView)view.findViewById(R.id.txtNumeroLinhaPedido);
		TextView txtRepresentada = (TextView)view.findViewById(R.id.txtRepresentadaLinhaPedido);
		TextView txtCliente = (TextView)view.findViewById(R.id.txtClienteLinhaPedido);
		TextView txtRepresentante = (TextView)view.findViewById(R.id.txtRepresentanteLinhaPedido);
		TextView txtValorTotal = (TextView)view.findViewById(R.id.txtValorTotalLinhaPedido);
		TextView txtDataEmissao = (TextView)view.findViewById(R.id.txtDataEmissaoLinhaPedido);
		
		txtId.setText(String.valueOf(pedido.getId()));
		if (pedido.getCliente() != null) {
			txtCliente.setText(pedido.getCliente().getNome());
		}
		txtNumero.setText(pedido.getNumero());
		
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("00.00;(00.00)");
		
		txtValorTotal.setText("R$ " + df.format(pedido.getValorTotal()));
		if (pedido.getRepresentada() != null) {
			txtRepresentada.setText(pedido.getRepresentada().getRazaoSocial());			
		}
		if (pedido.getRepresentante() != null) {	
			txtRepresentante.setText(pedido.getRepresentante().getNome());
		}
		if (pedido.getDataEmissao() != null) {
			txtDataEmissao.setText(StringUtils.getDataHora(pedido.getDataEmissao()));
		}
		
		return view;
	}

}
