package thgg.android.protocolo.ui;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.RepositorioVias;
import thgg.android.protocolo.model.Via;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroViasActivity extends Activity implements OnClickListener {

	private Via via;
	
	private TextView txtId;
	private EditText etxtDescricao;
	
	private Button btnSalvar;
	private Button btnCancelar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_vias);
		
		txtId = (TextView)findViewById(R.id.txtIdVia);
		etxtDescricao = (EditText)findViewById(R.id.etxtDescricaoVia);
		btnSalvar = (Button)findViewById(R.id.btnSalvarVia);
		btnCancelar = (Button)findViewById(R.id.btnCancelarVia);
		
		btnSalvar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		long id = getIntent().getLongExtra("id", -1);
		if (id != -1) {
			atualizaVia(id);
		} else {
			via = new Via();
		}			
	}
	
	private void atualizaVia(long id) {
		via = RepositorioVias.getRepositorio(this).procurar(id);
		txtId.setText("Id: " + via.getId());
		etxtDescricao.setText(via.getDescricao());
	}
	
	@Override
	public void onClick(View v) {
		if (v.equals(btnSalvar)) {
			via.setDescricao(etxtDescricao.getText().toString());
			RepositorioVias.getRepositorio(this).salvar(via);
			finish();			
		} else if (v.equals(btnCancelar)) {
			this.finish();
		}
	}
}
