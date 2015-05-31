package thgg.android.protocolo.ui;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.RepositorioVerbas;
import thgg.android.protocolo.model.Verba;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class ListaVerbasActivity extends ListActivity implements OnItemClickListener {

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
		atualizarVerba();		
	}

	private void atualizarVerba() {
		ArrayList<Verba> listaVerbas = RepositorioVerbas.getRepositorio(this).listar(ordenacao);
		VerbaAdapter verbaAdapter = new VerbaAdapter(this, listaVerbas);
		setListAdapter(verbaAdapter);
	} 
	
	@Override
	protected void onListItemClick (ListView l, View v, int position, long id) {
		Intent intent = new Intent("CADASTRO_VERBAS");
		intent.putExtra("id", getListView().getItemIdAtPosition(position));
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.lista_verbas_menu, menu);
	    
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.novo_verba_lista_verbas:
			intent = new Intent("CADASTRO_VERBAS");
			intent.putExtra("id", 0);
			startActivity(intent);
			break;

		case R.id.novo_cliente_lista_verbas:
			intent = new Intent("CADASTRO_CLIENTES");
			intent.putExtra("id", 0);
			startActivity(intent);
			break;
			
		case R.id.novo_via_lista_verbas: 
			intent = new Intent("CADASTRO_VIAS");
			startActivity(intent);
			break;

		case R.id.novo_canal_lista_verbas: 
			intent = new Intent("CADASTRO_CANAIS");
			startActivity(intent);
			break;
		
		case R.id.novo_representante_lista_verbas: 
			intent = new Intent("CADASTRO_REPRESENTANTES");
			startActivity(intent);
			break;
			
		case R.id.novo_representada_lista_verbas: 
			intent = new Intent("CADASTRO_REPRESENTADAS");
			startActivity(intent);
			break;
			
//		case R.id.novo_sumario_lista_verbas: 
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
			RepositorioVerbas.getRepositorio(this).excluir(info.id);
			atualizarVerba();
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
			String[] listaOrdenacao = new String[] {"Numero de Protocolo", "Cliente", "Acao", "Valor", "Data"}; 
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
			ordenacao = "_id";
			break;
		case 1:
			ordenacao = "cliente";
			break;
		case 2:
			ordenacao = "acao";
			break;
		case 3:
			ordenacao = "valor";
			break;
		case 4:
			ordenacao = "data, hora";
			break;
		}
		atualizarVerba();
		getOrdenacaoDialog().dismiss();
	}	
	
}
