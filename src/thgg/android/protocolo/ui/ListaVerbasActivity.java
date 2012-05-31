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
		menu.add(0, 1, 0, getResources().getString(R.string.nova_verba));
		menu.add(0, 2, 0, getResources().getString(R.string.novo_cliente));
		menu.add(0, 3, 0, getResources().getString(R.string.nova_via));
		menu.add(0, 4, 0, getResources().getString(R.string.novo_canal));
		menu.add(0, 5, 0, getResources().getString(R.string.novo_consultor));
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case 1:
			intent = new Intent("CADASTRO_VERBAS");
			intent.putExtra("id", 0);
			startActivity(intent);
			break;

		case 2:
			intent = new Intent("CADASTRO_CLIENTES");
			intent.putExtra("id", 0);
			startActivity(intent);
			break;
			
		case 3: 
			intent = new Intent("CADASTRO_VIAS");
			startActivity(intent);
			break;

		case 4: 
			intent = new Intent("CADASTRO_CANAIS");
			startActivity(intent);
			break;
		
		case 5: 
			intent = new Intent("CADASTRO_CONSULTORES");
			startActivity(intent);
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, 5, 0, "Excluir");
		menu.add(0, 7, 0, "Ordenar");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		
		switch (item.getItemId()) {
		case 6:
			RepositorioVerbas.getRepositorio(this).excluir(info.id);
			atualizarVerba();
			break;

		case 7:
			getOrdenacaoDialog().show();
			break;

		default:
			throw new InvalidParameterException("Opção inválida");
		}
		return super.onContextItemSelected(item);
	}
	
	private View getOrdenacaoView() {
		if (ordenacaoView == null) {
			ordenacaoView = LayoutInflater.from(this).inflate(R.layout.ordenacao_dialog, null);
			String[] listaOrdenacao = new String[] {"Número de Protocolo", "Cliente", "Ação", "Valor", "Data"}; 
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
