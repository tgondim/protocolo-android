package thgg.android.protocolo.ui;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.RepositorioRepresentantes;
import thgg.android.protocolo.model.Representante;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroRepresentantesActivity extends Activity implements OnClickListener {

	private Representante representante;
	
	private TextView txtId;
	private EditText etxtNome;
	
	private Button btnSalvar;
	private Button btnCancelar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_representantes);
		
		txtId = (TextView)findViewById(R.id.txtIdRepresentante);
		etxtNome = (EditText)findViewById(R.id.etxtNomeRepresentante);
		btnSalvar = (Button)findViewById(R.id.btnSalvarRepresentante);
		btnCancelar = (Button)findViewById(R.id.btnCancelarRepresentante);
		
		btnSalvar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		long id = getIntent().getLongExtra("id", -1);
		if (id != -1) {
			atualizaRepresentante(id);
		} else {
			representante = new Representante();
		}		
	}
	
	private void atualizaRepresentante(long id) {
		representante = RepositorioRepresentantes.getRepositorio(this).procurar(id);
		txtId.setText("Id: " + representante.getId());
		etxtNome.setText(representante.getNome());
	}
	
	@Override
	public void onClick(View v) {
		if (v.equals(btnSalvar)) {
			representante.setNome(etxtNome.getText().toString());
			RepositorioRepresentantes.getRepositorio(this).salvar(representante);
			finish();			
		} else if (v.equals(btnCancelar)) {
			this.finish();
		}
	}
}
