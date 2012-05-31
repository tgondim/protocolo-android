package thgg.android.protocolo.ui;

import java.text.DecimalFormat;
import java.util.List;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Verba;
import thgg.android.protocolo.util.StringUtils;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class VerbaAdapter extends BaseAdapter {

	private Context ctx;
	
	private List<Verba> listaVerbas;

	public VerbaAdapter(Context newCtx, List<Verba> newListaVerbas) {
		this.ctx = newCtx;
		this.listaVerbas = newListaVerbas;
	}
	
	@Override
	public int getCount() {
		return listaVerbas.size();
	}

	@Override
	public Object getItem(int position) {
		return listaVerbas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaVerbas.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Verba verba = listaVerbas.get(position);
		
		View view = LayoutInflater.from(ctx).inflate(R.layout.linha_verba, null);
		
		TextView txtId = (TextView)view.findViewById(R.id.txtIdLinhaVerba);
		TextView txtCliente = (TextView)view.findViewById(R.id.txtClienteLinhaVerba);
		TextView txtAcao = (TextView)view.findViewById(R.id.txtAcaoLinhaVerba);
		TextView txtValor = (TextView)view.findViewById(R.id.txtValorLinhaVerba);
		TextView txtCanal = (TextView)view.findViewById(R.id.txtCanalLinhaVerba);
		TextView txtConsultor = (TextView)view.findViewById(R.id.txtConsultorLinhaVerba);
		TextView txtData = (TextView)view.findViewById(R.id.txtDataLinhaVerba);
		
		txtId.setText(String.valueOf(verba.getId()));
		if (verba.getCliente() != null) {
			txtCliente.setText(verba.getCliente().getNome());
		}
		txtAcao.setText(verba.getAcao());
		
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("00.00;(00.00)");
		
		txtValor.setText("R$ " + df.format(verba.getValor()));
		if (verba.getCanal() != null) {
			txtCanal.setText(verba.getCanal().getDescricao());			
		}
		if (verba.getConsultor() != null) {	
			txtConsultor.setText(verba.getConsultor().getNome());
		}
		if (verba.getData() != null) {
			txtData.setText(StringUtils.getDataHora(verba.getData()));
		}
		
		return view;
	}

}
