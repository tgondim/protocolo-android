package thgg.android.protocolo.ui;

import java.util.List;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Documento;
import thgg.android.protocolo.util.StringUtils;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DocumentoAdapter extends BaseAdapter {

	private Context ctx;
	
	private List<Documento> listaDocumentos;

	public DocumentoAdapter(Context newCtx, List<Documento> newListaDocumentos) {
		this.ctx = newCtx;
		this.listaDocumentos = newListaDocumentos;
	}
	
	@Override
	public int getCount() {
		return listaDocumentos.size();
	}

	@Override
	public Object getItem(int position) {
		return listaDocumentos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaDocumentos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Documento doc = listaDocumentos.get(position);
		
		View view = LayoutInflater.from(ctx).inflate(R.layout.linha_documento, null);
		
		TextView txtId = (TextView)view.findViewById(R.id.txtIdLinhaDocumento);
		TextView txtDescricao = (TextView)view.findViewById(R.id.txtDescricaoLinhaDocumento);
		TextView txtDestino = (TextView)view.findViewById(R.id.txtDestinoLinhaDocumento);
		TextView txtVia = (TextView)view.findViewById(R.id.txtViaLinhaDocumento);
		TextView txtData = (TextView)view.findViewById(R.id.txtDataLinhaDocumento);
		
		txtId.setText(String.valueOf(doc.getId()));
		txtDescricao.setText(doc.getDescricao());
		txtDestino.setText(doc.getDestino());
		if (doc.getVia() != null) {
			txtVia.setText(doc.getVia().getDescricao());
		}
		if (doc.getData() != null) {
			txtData.setText(StringUtils.getDataHora(doc.getData()));
		}
		return view;
	}

}
