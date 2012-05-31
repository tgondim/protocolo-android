package thgg.android.protocolo.ui;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Canal;
import thgg.android.protocolo.model.RepositorioCanais;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroCanaisActivity extends Activity implements OnClickListener {

	private Canal canal;
	
	private TextView txtId;
	private EditText etxtDescricao;
	
	private Button btnSalvar;
	private Button btnCancelar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_canais);
		
		txtId = (TextView)findViewById(R.id.txtIdCanal);
		etxtDescricao = (EditText)findViewById(R.id.etxtDescricaoCanal);
		btnSalvar = (Button)findViewById(R.id.btnSalvarCanal);
		btnCancelar = (Button)findViewById(R.id.btnCancelarCanal);
		
		btnSalvar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		long id = getIntent().getLongExtra("id", -1);
		if (id != -1) {
			atualizaCanal(id);
		} else {
			canal = new Canal();
		}	
	}
	
	private void atualizaCanal(long id) {
		canal = RepositorioCanais.getRepositorio(this).procurar(id);
		txtId.setText("Id: " + canal.getId());
		etxtDescricao.setText(canal.getDescricao());
	}
	
	@Override
	public void onClick(View v) {
		if (v.equals(btnSalvar)) {
			canal.setDescricao(etxtDescricao.getText().toString());
			RepositorioCanais.getRepositorio(this).salvar(canal);
			finish();			
		} else if (v.equals(btnCancelar)) {
			this.finish();
		}
	}
}
