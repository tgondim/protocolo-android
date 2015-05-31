package thgg.android.protocolo.ui;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Pedido;
import thgg.android.protocolo.model.RepositorioPedidos;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaPedidosActivity extends ListActivity implements OnItemClickListener {

	private View ordenacaoView;
	
	private ListView lvOrdenacao;
	 
	private ArrayAdapter<String> ordenacaoAdapter;
	
	private AlertDialog ordenacaoDialog;
	
	private String ordenacao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerForContextMenu(getListView());
		ordenacao = "data, hora";
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		atualizarPedido();		
	}

	private void atualizarPedido() {
		ArrayList<Pedido> listaPedidos = RepositorioPedidos.getRepositorio(this).listar(ordenacao);
		PedidoAdapter pedidoAdapter = new PedidoAdapter(this, listaPedidos);
		setListAdapter(pedidoAdapter);
	} 
	
	@Override
	protected void onListItemClick (ListView l, View v, int position, long id) {
		Intent intent = new Intent("CADASTRO_PEDIDOS");
		intent.putExtra("id", getListView().getItemIdAtPosition(position));
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.lista_pedidos_menu, menu);

		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.novo_pedido_lista_pedidos:
			intent = new Intent("CADASTRO_PEDIDOS");
			intent.putExtra("id", 0);
			startActivity(intent);
			break;

		case R.id.novo_cliente_lista_pedidos:
			intent = new Intent("CADASTRO_CLIENTES");
			intent.putExtra("id", 0);
			startActivity(intent);
			break;
			
		case R.id.novo_representada_lista_pedidos: 
			intent = new Intent("CADASTRO_REPRESENTADAS");
			startActivity(intent);
			break;
		
		case R.id.novo_representante_lista_pedidos: 
			intent = new Intent("CADASTRO_REPRESENTANTES");
			startActivity(intent);
			break;
			
//		case 5: 
//			intent = new Intent("RESUMO_VERBAS");
//			startActivity(intent);
//			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, 7, 0, "Excluir");
		menu.add(0, 8, 0, "Ordenar");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		
		switch (item.getItemId()) {
		case 7:
			RepositorioPedidos.getRepositorio(this).excluir(info.id);
			atualizarPedido();
			break;

		case 8:
			getOrdenacaoDialog().show();
			break;

		default:
			throw new InvalidParameterException("Opcao invalida");
		}
		return super.onContextItemSelected(item);
	}
	
	private View getOrdenacaoView() {
		if (ordenacaoView == null) {
			ordenacaoView = LayoutInflater.from(this).inflate(R.layout.ordenacao_dialog, null);
			String[] listaOrdenacao = new String[] {"Numero", "Cliente", "Representada", "Representante", "Data Emissao"}; 
			lvOrdenacao = (ListView)ordenacaoView.findViewById(R.id.lvOrdenacao);
			ordenacaoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaOrdenacao);
			lvOrdenacao.setAdapter(ordenacaoAdapter);
			lvOrdenacao.setOnItemClickListener(this);
		}
		return ordenacaoView;
	}

	private AlertDialog getOrdenacaoDialog() {
		if (ordenacaoDialog == null) {
			ordenacaoDialog = new AlertDialog.Builder(this)
				.setTitle(getResources().getString(R.string.ordenar_por))
				.setView(getOrdenacaoView())
				.create();
		}
		return ordenacaoDialog;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
		case 0:
			ordenacao = "numero";
			break;
		case 1:
			ordenacao = "cliente";
			break;
		case 2:
			ordenacao = "representada";
			break;
		case 3:
			ordenacao = "representante";
			break;
		case 4:
			ordenacao = "data, hora";
			break;
		}
		atualizarPedido();
		getOrdenacaoDialog().dismiss();
	}	
	
}
