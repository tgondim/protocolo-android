package thgg.android.protocolo.ui;

import java.util.ArrayList;
import java.util.Calendar;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Canal;
import thgg.android.protocolo.model.Cliente;
import thgg.android.protocolo.model.RepositorioCanais;
import thgg.android.protocolo.model.RepositorioClientes;
import thgg.android.protocolo.model.RepositorioRepresentadas;
import thgg.android.protocolo.model.RepositorioRepresentantes;
import thgg.android.protocolo.model.RepositorioVerbas;
import thgg.android.protocolo.model.RepositorioVias;
import thgg.android.protocolo.model.Representada;
import thgg.android.protocolo.model.Representante;
import thgg.android.protocolo.model.Verba;
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

public class CadastroVerbasActivity extends Activity implements OnClickListener {
	
	private Verba verba;
	
	private TextView txtNumero;
	
	private EditText etxtAcao;
	private EditText etxtValor;
	
	private Spinner spnCliente;
	private Spinner spnVia;
	private Spinner spnCanal;
	private Spinner spnRepresentante;
	private Spinner spnRepresentada;
	
	private DatePicker dpData;
	
	private TimePicker tpHora;
	
	private Button btnSalvar;
	private Button btnCancelar;
	
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Via> listaVias;
	private ArrayList<Canal> listaCanais;
	private ArrayList<Representante> listaRepresentantes;
	private ArrayList<Representada> listaRepresentadas;
	
	private ArrayAdapter<Cliente> spinnerClienteAdapter;
	private ArrayAdapter<Via> spinnerViaAdapter;
	private ArrayAdapter<Canal> spinnerCanalAdapter;
	private ArrayAdapter<Representante> spinnerRepresentanteAdapter;
	private ArrayAdapter<Representada> spinnerRepresentadaAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_verbas);
		
		txtNumero = (TextView)findViewById(R.id.txtNumeroVerba);
		spnCliente = (Spinner)findViewById(R.id.spnClienteVerba);
		etxtAcao = (EditText)findViewById(R.id.etxtAcaoVerba);
		etxtValor = (EditText)findViewById(R.id.etxtValorVerba);
		spnVia = (Spinner)findViewById(R.id.spnViaVerba);
		spnCanal = (Spinner)findViewById(R.id.spnCanalVerba);
		spnRepresentante = (Spinner)findViewById(R.id.spnRepresentanteVerba);
		spnRepresentada = (Spinner)findViewById(R.id.spnRepresentadaVerba);
		dpData = (DatePicker)findViewById(R.id.dpDataVerba);
		tpHora = (TimePicker)findViewById(R.id.tpHoraVerba);
		btnSalvar = (Button)findViewById(R.id.btnSalvarVerba);
		btnCancelar = (Button)findViewById(R.id.btnCancelarVerba);
		
		tpHora.setIs24HourView(true);
		btnSalvar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();

		this.listaClientes = RepositorioClientes.getRepositorio(this).listar("nome");
		spinnerClienteAdapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_spinner_item, listaClientes);
		spinnerClienteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCliente.setAdapter(spinnerClienteAdapter);		
		
		this.listaVias = RepositorioVias.getRepositorio(this).listar("descricao");
		spinnerViaAdapter = new ArrayAdapter<Via>(this, android.R.layout.simple_spinner_item, listaVias);
		spinnerViaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnVia.setAdapter(spinnerViaAdapter);		

		this.listaCanais = RepositorioCanais.getRepositorio(this).listar("descricao");
		spinnerCanalAdapter = new ArrayAdapter<Canal>(this, android.R.layout.simple_spinner_item, listaCanais);
		spinnerCanalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCanal.setAdapter(spinnerCanalAdapter);		
		
		this.listaRepresentantes = RepositorioRepresentantes.getRepositorio(this).listar("nome");
		spinnerRepresentanteAdapter = new ArrayAdapter<Representante>(this, android.R.layout.simple_spinner_item, listaRepresentantes);
		spinnerRepresentanteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnRepresentante.setAdapter(spinnerRepresentanteAdapter);		
	
		this.listaRepresentadas = RepositorioRepresentadas.getRepositorio(this).listar("razao_social");
		spinnerRepresentadaAdapter = new ArrayAdapter<Representada>(this, android.R.layout.simple_spinner_item, listaRepresentadas);
		spinnerRepresentadaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnRepresentada.setAdapter(spinnerRepresentadaAdapter);		

		long id = getIntent().getLongExtra("id", -1);
		if (id != -1) {
			atualizaVerba(id);
		} else {
			verba = new Verba();
		}
	}
	
	private void atualizaVerba(long id) {
		verba = RepositorioVerbas.getRepositorio(this).procurar(id);
		txtNumero.setText("Numero: " + verba.getId());
		spnCliente.setSelection(spinnerClienteAdapter.getPosition(verba.getCliente()));
		etxtAcao.setText(verba.getAcao());
		etxtValor.setText(String.valueOf(verba.getValor()));
		spnVia.setSelection(spinnerViaAdapter.getPosition(verba.getVia()));
		spnCanal.setSelection(spinnerCanalAdapter.getPosition(verba.getCanal()));
		spnRepresentante.setSelection(spinnerRepresentanteAdapter.getPosition(verba.getRepresentante()));
		spnRepresentada.setSelection(spinnerRepresentadaAdapter.getPosition(verba.getRepresentada()));
		
		Calendar data = verba.getData();
		dpData.updateDate(data.get(Calendar.YEAR), 
				data.get(Calendar.MONTH), 
				data.get(Calendar.DATE));
		tpHora.setCurrentHour(data.get(Calendar.HOUR_OF_DAY));
		tpHora.setCurrentMinute(data.get(Calendar.MINUTE));
	}
	
	@Override
	public void onClick(View v) {
		if (v.equals(btnSalvar)) {
			verba.setCliente((Cliente)spnCliente.getSelectedItem());
			verba.setAcao(etxtAcao.getText().toString());
			verba.setValor(Float.parseFloat(etxtValor.getText().toString()));
			verba.setVia((Via)spnVia.getSelectedItem());
			verba.setCanal((Canal)spnCanal.getSelectedItem());
			verba.setRepresentante((Representante)spnRepresentante.getSelectedItem());
			verba.setRepresentada((Representada)spnRepresentada.getSelectedItem());
			
			
			Calendar data = Calendar.getInstance();
			data.set(Calendar.DAY_OF_MONTH, dpData.getDayOfMonth());
			data.set(Calendar.MONTH, dpData.getMonth());
			data.set(Calendar.YEAR, dpData.getYear());
			data.set(Calendar.HOUR_OF_DAY, tpHora.getCurrentHour());
			data.set(Calendar.MINUTE, tpHora.getCurrentMinute());			
			verba.setData(data);
			
			RepositorioVerbas.getRepositorio(this).salvar(verba);
			finish();			
		} else if (v.equals(btnCancelar)) {
			this.finish();
		}		
	}

}