package thgg.android.protocolo.ui;

import java.util.ArrayList;
import java.util.Calendar;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Cliente;
import thgg.android.protocolo.model.Consultor;
import thgg.android.protocolo.model.Evento;
import thgg.android.protocolo.model.RepositorioClientes;
import thgg.android.protocolo.model.RepositorioConsultores;
import thgg.android.protocolo.model.RepositorioEventos;
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

public class CadastroEventosActivity extends Activity implements OnClickListener {
	
	private Evento evento;
	
	private TextView txtNumero;
	
	private EditText etxtTitulo;
	private EditText etxtNumeroPessoas;
	
	private Spinner spnCliente;
	private Spinner spnConsultor;
	
	private DatePicker dpData;
	
	private TimePicker tpHora;
	
	private Button btnSalvar;
	private Button btnCancelar;
	
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Consultor> listaConsultores;
	
	private ArrayAdapter<Cliente> spinnerClienteAdapter;
	private ArrayAdapter<Consultor> spinnerConsultorAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_eventos);
		
		txtNumero = (TextView)findViewById(R.id.txtNumeroEvento);
		etxtTitulo = (EditText)findViewById(R.id.etxtTituloEvento);
		etxtNumeroPessoas = (EditText)findViewById(R.id.etxtNumeroPessoasEvento);
		dpData = (DatePicker)findViewById(R.id.dpDataEvento);
		tpHora = (TimePicker)findViewById(R.id.tpHoraEvento);
		btnSalvar = (Button)findViewById(R.id.btnSalvarEvento);
		btnCancelar = (Button)findViewById(R.id.btnCancelarEvento);
		spnCliente = (Spinner)findViewById(R.id.spnClienteEvento);
		spnConsultor = (Spinner)findViewById(R.id.spnConsultorEvento);
		
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

		this.listaConsultores = RepositorioConsultores.getRepositorio(this).listar("nome");
		spinnerConsultorAdapter = new ArrayAdapter<Consultor>(this, android.R.layout.simple_spinner_item, listaConsultores);
		spinnerConsultorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnConsultor.setAdapter(spinnerConsultorAdapter);		

		long id = getIntent().getLongExtra("id", -1);
		if (id != -1) {
			atualizaEvento(id);
		} else {
			evento = new Evento();
		}		
	}
	
	private void atualizaEvento(long id) {
		evento = RepositorioEventos.getRepositorio(this).procurar(id);
		txtNumero.setText("Número: " + evento.getId());
		etxtTitulo.setText(evento.getTitulo());
		etxtNumeroPessoas.setText(String.valueOf(evento.getNumeroPessoas()));
		spnCliente.setSelection(spinnerClienteAdapter.getPosition(evento.getCliente()));
		spnConsultor.setSelection(spinnerConsultorAdapter.getPosition(evento.getConsultor()));

		Calendar data = evento.getData();
		dpData.updateDate(data.get(Calendar.YEAR), 
				data.get(Calendar.MONTH), 
				data.get(Calendar.DATE));
		tpHora.setCurrentHour(data.get(Calendar.HOUR_OF_DAY));
		tpHora.setCurrentMinute(data.get(Calendar.MINUTE));
	}	
	
	@Override
	public void onClick(View v) {
		if (v.equals(btnSalvar)) {
			evento.setTitulo(etxtTitulo.getText().toString());
			evento.setNumeroPessoas(Integer.parseInt(etxtNumeroPessoas.getText().toString()));
			evento.setCliente((Cliente)spnCliente.getSelectedItem());
			evento.setConsultor((Consultor)spnConsultor.getSelectedItem());
			
			Calendar data = Calendar.getInstance();
			data.set(Calendar.DAY_OF_MONTH, dpData.getDayOfMonth());
			data.set(Calendar.MONTH, dpData.getMonth());
			data.set(Calendar.YEAR, dpData.getYear());
			data.set(Calendar.HOUR_OF_DAY, tpHora.getCurrentHour());
			data.set(Calendar.MINUTE, tpHora.getCurrentMinute());
			evento.setData(data);
			
			RepositorioEventos.getRepositorio(this).salvar(evento);
			finish();			
		} else if (v.equals(btnCancelar)) {
			this.finish();
		}
	}

}
