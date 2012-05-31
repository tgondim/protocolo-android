package thgg.android.protocolo.ui;

import java.util.ArrayList;
import java.util.Calendar;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Documento;
import thgg.android.protocolo.model.RepositorioDocumentos;
import thgg.android.protocolo.model.RepositorioVias;
import thgg.android.protocolo.model.Via;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class CadastroDocumentosActivity extends Activity implements OnClickListener {

	private Documento documento;
	
	private TextView txtNumero;
	
	private EditText etxtDescricao;
	private EditText etxtDestino;
	
	private Spinner spnVia;
	
	private DatePicker dpData;
	
	private TimePicker tpHora;
	
	private Button btnAnexarDocumento;
	private Button btnSalvar;
	private Button btnCancelar;
	
	private ArrayList<Via> listaVias;
	
	private ArrayAdapter<Via> spinnerViaAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_documentos);
		
		txtNumero = (TextView)findViewById(R.id.txtNumeroDocumento);
		etxtDescricao = (EditText)findViewById(R.id.etxtDescricaoDocumento);
		etxtDestino = (EditText)findViewById(R.id.etxtDestinoDocumento);
		dpData = (DatePicker)findViewById(R.id.dpDataDocumento);
		tpHora = (TimePicker)findViewById(R.id.tpHoraDocumento);
		btnAnexarDocumento = (Button)findViewById(R.id.btnAnexarDocumento);
		btnSalvar = (Button)findViewById(R.id.btnSalvarDocumento);
		btnCancelar = (Button)findViewById(R.id.btnCancelarDocumento);
		spnVia = (Spinner)findViewById(R.id.spnViaDocumento);
		
		tpHora.setIs24HourView(true);
		btnSalvar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);
		
		//Ainda nao foram implementados
		btnAnexarDocumento.setVisibility(View.GONE);
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		this.listaVias = RepositorioVias.getRepositorio(this).listar("descricao");
		spinnerViaAdapter = new ArrayAdapter<Via>(this, android.R.layout.simple_spinner_item, listaVias);
		spinnerViaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnVia.setAdapter(spinnerViaAdapter);		

		long id = getIntent().getLongExtra("id", -1);
		if (id != -1) {
			atualizaDocumento(id);
		} else {
			documento = new Documento();
		}
	}

	private void atualizaDocumento(long id) {
		documento = RepositorioDocumentos.getRepositorio(this).procurar(id);
		txtNumero.setText("Número: " + documento.getId());
		etxtDescricao.setText(documento.getDescricao());
		etxtDestino.setText(documento.getDestino());
		spnVia.setSelection(spinnerViaAdapter.getPosition(documento.getVia()));

		Calendar data = documento.getData();
		dpData.updateDate(data.get(Calendar.YEAR), 
				data.get(Calendar.MONTH), 
				data.get(Calendar.DATE));
		tpHora.setCurrentHour(data.get(Calendar.HOUR_OF_DAY));
		tpHora.setCurrentMinute(data.get(Calendar.MINUTE));
	}
	
	@Override
	public void onClick(View v) {
		if (v.equals(btnSalvar)) {
			documento.setDescricao(etxtDescricao.getText().toString());
			documento.setDestino(etxtDestino.getText().toString());
			documento.setVia((Via)spnVia.getSelectedItem());
			
			Calendar data = Calendar.getInstance();
			data.set(Calendar.DAY_OF_MONTH, dpData.getDayOfMonth());
			data.set(Calendar.MONTH, dpData.getMonth());
			data.set(Calendar.YEAR, dpData.getYear());
			data.set(Calendar.HOUR_OF_DAY, tpHora.getCurrentHour());
			data.set(Calendar.MINUTE, tpHora.getCurrentMinute());
			documento.setData(data);
			
			RepositorioDocumentos.getRepositorio(this).salvar(documento);
			finish();			
		} else if (v.equals(btnAnexarDocumento)) {
//			Intent intent = new Intent();
//			startActivity(intent);
		} else if (v.equals(btnCancelar)) {
			this.finish();
		}
	}

}
