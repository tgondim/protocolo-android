package thgg.android.protocolo.ui;

import java.util.ArrayList;
import java.util.Calendar;

import thgg.android.protocolo.R;
import thgg.android.protocolo.model.Cliente;
import thgg.android.protocolo.model.Pedido;
import thgg.android.protocolo.model.RepositorioClientes;
import thgg.android.protocolo.model.RepositorioPedidos;
import thgg.android.protocolo.model.RepositorioRepresentadas;
import thgg.android.protocolo.model.RepositorioRepresentantes;
import thgg.android.protocolo.model.Representada;
import thgg.android.protocolo.model.Representante;
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

public class CadastroPedidosActivity extends Activity implements OnClickListener {
	
	private Pedido pedido;
	
	private TextView txtId;
	
	private EditText etxtNumero;
	private EditText etxtValorTotal;
	
	private Spinner spnCliente;
	private Spinner spnRepresentada;
	private Spinner spnRepresentante;
	
	private DatePicker dpData;
	
	private TimePicker tpHora;
	
	private Button btnSalvar;
	private Button btnCancelar;
	
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Representada> listaRepresentadas;
	private ArrayList<Representante> listaRepresentantes;
	
	private ArrayAdapter<Cliente> spinnerClienteAdapter;
	private ArrayAdapter<Representada> spinnerRepresentadaAdapter;
	private ArrayAdapter<Representante> spinnerRepresentanteAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_pedidos);
		
		txtId = (TextView)findViewById(R.id.txtIdPedido);
		spnCliente = (Spinner)findViewById(R.id.spnClientePedido);
		etxtNumero = (EditText)findViewById(R.id.etxtNumeroPedido);
		etxtValorTotal = (EditText)findViewById(R.id.etxtValorTotalPedido);
		spnRepresentada = (Spinner)findViewById(R.id.spnRepresentadaPedido);
		spnRepresentante = (Spinner)findViewById(R.id.spnRepresentantePedido);
		dpData = (DatePicker)findViewById(R.id.dpDataPedido);
		tpHora = (TimePicker)findViewById(R.id.tpHoraPedido);
		btnSalvar = (Button)findViewById(R.id.btnSalvarPedido);
		btnCancelar = (Button)findViewById(R.id.btnCancelarPedido);
		
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
		
		this.listaRepresentadas = RepositorioRepresentadas.getRepositorio(this).listar("razao_social");
		spinnerRepresentadaAdapter = new ArrayAdapter<Representada>(this, android.R.layout.simple_spinner_item, listaRepresentadas);
		spinnerRepresentadaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnRepresentada.setAdapter(spinnerRepresentadaAdapter);		
		
		this.listaRepresentantes = RepositorioRepresentantes.getRepositorio(this).listar("nome");
		spinnerRepresentanteAdapter = new ArrayAdapter<Representante>(this, android.R.layout.simple_spinner_item, listaRepresentantes);
		spinnerRepresentanteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnRepresentante.setAdapter(spinnerRepresentanteAdapter);		

		long id = getIntent().getLongExtra("id", -1);
		if (id != -1) {
			atualizaPedido(id);
		} else {
			pedido = new Pedido();
		}
	}
	
	private void atualizaPedido(long id) {
		pedido = RepositorioPedidos.getRepositorio(this).procurar(id);
		txtId.setText("Id: " + pedido.getId());
		spnCliente.setSelection(spinnerClienteAdapter.getPosition(pedido.getCliente()));
		etxtNumero.setText(pedido.getNumero());
		etxtValorTotal.setText(String.valueOf(pedido.getValorTotal()));
		spnRepresentada.setSelection(spinnerRepresentadaAdapter.getPosition(pedido.getRepresentada()));
		spnRepresentante.setSelection(spinnerRepresentanteAdapter.getPosition(pedido.getRepresentante()));
		
		Calendar data = pedido.getDataEmissao();
		dpData.updateDate(data.get(Calendar.YEAR), 
				data.get(Calendar.MONTH), 
				data.get(Calendar.DATE));
		tpHora.setCurrentHour(data.get(Calendar.HOUR_OF_DAY));
		tpHora.setCurrentMinute(data.get(Calendar.MINUTE));
	}
	
	@Override
	public void onClick(View v) {
		if (v.equals(btnSalvar)) {
			pedido.setCliente((Cliente)spnCliente.getSelectedItem());
			pedido.setNumero(etxtNumero.getText().toString());
			pedido.setValorTotal(Double.parseDouble(etxtValorTotal.getText().toString()));
			pedido.setRepresentada((Representada)spnRepresentada.getSelectedItem());
			pedido.setRepresentante((Representante)spnRepresentante.getSelectedItem());
			
			Calendar data = Calendar.getInstance();
			data.set(Calendar.DAY_OF_MONTH, dpData.getDayOfMonth());
			data.set(Calendar.MONTH, dpData.getMonth());
			data.set(Calendar.YEAR, dpData.getYear());
			data.set(Calendar.HOUR_OF_DAY, tpHora.getCurrentHour());
			data.set(Calendar.MINUTE, tpHora.getCurrentMinute());			
			pedido.setDataEmissao(data);
			
			RepositorioPedidos.getRepositorio(this).salvar(pedido);
			finish();			
		} else if (v.equals(btnCancelar)) {
			this.finish();
		}		
	}

}