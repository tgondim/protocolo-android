package thgg.android.protocolo.ui;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.RepositorioRepresentadas;
import thgg.android.protocolo.model.Representada;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroRepresentadasActivity extends Activity implements OnClickListener {

	private Representada representada;
	
	private TextView txtId;
	private EditText etxtRazaoSocial;
	
	private Button btnSalvar;
	private Button btnCancelar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_representadas);
		
		txtId = (TextView)findViewById(R.id.txtIdRepresentada);
		etxtRazaoSocial = (EditText)findViewById(R.id.etxtRazaoSocialRepresentada);
		btnSalvar = (Button)findViewById(R.id.btnSalvarRepresentada);
		btnCancelar = (Button)findViewById(R.id.btnCancelarRepresentada);
		
		btnSalvar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		long id = getIntent().getLongExtra("id", -1);
		if (id != -1) {
			atualizaRepresentada(id);
		} else {
			representada = new Representada();
		}		
	}
	
	private void atualizaRepresentada(long id) {
		representada = RepositorioRepresentadas.getRepositorio(this).procurar(id);
		txtId.setText("Id: " + representada.getId());
		etxtRazaoSocial.setText(representada.getRazaoSocial());
	}
	
	@Override
	public void onClick(View v) {
		if (v.equals(btnSalvar)) {
			representada.setRazaoSocial(etxtRazaoSocial.getText().toString());
			RepositorioRepresentadas.getRepositorio(this).salvar(representada);
			finish();			
		} else if (v.equals(btnCancelar)) {
			this.finish();
		}
	}
}
