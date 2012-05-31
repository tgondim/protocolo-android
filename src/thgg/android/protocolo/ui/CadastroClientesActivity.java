package thgg.android.protocolo.ui;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Cliente;
import thgg.android.protocolo.model.RepositorioClientes;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroClientesActivity extends Activity implements OnClickListener {

	private Cliente cliente;
	
	private TextView txtId;
	private EditText etxtNome;
	
	private Button btnSalvar;
	private Button btnCancelar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_clientes);
		
		txtId = (TextView)findViewById(R.id.txtIdCliente);
		etxtNome = (EditText)findViewById(R.id.etxtNomeCliente);
		btnSalvar = (Button)findViewById(R.id.btnSalvarCliente);
		btnCancelar = (Button)findViewById(R.id.btnCancelarCliente);
		
		btnSalvar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		long id = getIntent().getLongExtra("id", -1);
		if (id != -1) {
			atualizaCliente(id);
		} else {
			cliente = new Cliente();
		}		
	}
	
	private void atualizaCliente(long id) {
		cliente = RepositorioClientes.getRepositorio(this).procurar(id);
		txtId.setText("Id: " + cliente.getId());
		etxtNome.setText(cliente.getNome());
	}
	
	@Override
	public void onClick(View v) {
		if (v.equals(btnSalvar)) {
			cliente.setNome(etxtNome.getText().toString());
			RepositorioClientes.getRepositorio(this).salvar(cliente);
			finish();			
		} else if (v.equals(btnCancelar)) {
			this.finish();
		}
	}
}
