package thgg.android.protocolo.ui;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Consultor;
import thgg.android.protocolo.model.RepositorioConsultores;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroConsultoresActivity extends Activity implements OnClickListener {

	private Consultor consultor;
	
	private TextView txtId;
	private EditText etxtNome;
	
	private Button btnSalvar;
	private Button btnCancelar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_consultores);
		
		txtId = (TextView)findViewById(R.id.txtIdConsultor);
		etxtNome = (EditText)findViewById(R.id.etxtNomeConsultor);
		btnSalvar = (Button)findViewById(R.id.btnSalvarConsultor);
		btnCancelar = (Button)findViewById(R.id.btnCancelarConsultor);
		
		btnSalvar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		long id = getIntent().getLongExtra("id", -1);
		if (id != -1) {
			atualizaConsultor(id);
		} else {
			consultor = new Consultor();
		}		
	}
	
	private void atualizaConsultor(long id) {
		consultor = RepositorioConsultores.getRepositorio(this).procurar(id);
		txtId.setText("Id: " + consultor.getId());
		etxtNome.setText(consultor.getNome());
	}
	
	@Override
	public void onClick(View v) {
		if (v.equals(btnSalvar)) {
			consultor.setNome(etxtNome.getText().toString());
			RepositorioConsultores.getRepositorio(this).salvar(consultor);
			finish();			
		} else if (v.equals(btnCancelar)) {
			this.finish();
		}
	}
}
