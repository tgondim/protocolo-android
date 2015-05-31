package thgg.android.protocolo.ui;

import thgg.android.protocolo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProtocoloActivity extends Activity implements OnClickListener {
	
	Button btnDocumentos;
 	Button btnVerbas;
	Button btnEventos;
	Button btnPedidos;
//	Button btnConsultores;
	Button btnClientes;
	Button btnRepresentantes;
	Button btnRepresentadas;
	Button btnVias;
	Button btnCanais;
//	Button btnCriarCopia;
//	Button btnRestaurarCopia;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protocolo);
        
        btnDocumentos = (Button)findViewById(R.id.btnDocumentos);
        btnVerbas = (Button)findViewById(R.id.btnVerbas);
        btnEventos = (Button)findViewById(R.id.btnEventos);
        btnPedidos = (Button)findViewById(R.id.btnPedidos);
//        btnConsultores = (Button)findViewById(R.id.btnConsultores);
        btnClientes = (Button)findViewById(R.id.btnClientes);
        btnRepresentantes = (Button)findViewById(R.id.btnRepresentantes);
        btnRepresentadas = (Button)findViewById(R.id.btnRepresentadas);
        btnVias = (Button)findViewById(R.id.btnVias);
        btnCanais = (Button)findViewById(R.id.btnCanais);
//        btnCriarCopia = (Button)findViewById(R.id.btnCriarCopia);
//        btnRestaurarCopia = (Button)findViewById(R.id.btnRestaurarCopia);
        
        btnDocumentos.setOnClickListener(this);
        btnVerbas.setOnClickListener(this);
        btnEventos.setOnClickListener(this);
        btnPedidos.setOnClickListener(this);
//        btnConsultores.setOnClickListener(this);
        btnClientes.setOnClickListener(this);
        btnRepresentantes.setOnClickListener(this);
        btnRepresentadas.setOnClickListener(this);
        btnVias.setOnClickListener(this);
        btnCanais.setOnClickListener(this);
//        btnCriarCopia.setOnClickListener(this);
//        btnRestaurarCopia.setOnClickListener(this);
    }

	@Override
	public void onClick(View view) {
		Intent intent = null;
		if (view.equals(btnDocumentos)) {
			intent = new Intent("LISTA_DOCUMENTOS");
		} else if (view.equals(btnVerbas)) {
			intent = new Intent("LISTA_VERBAS");
		} else if (view.equals(btnEventos)) {
			intent = new Intent("LISTA_EVENTOS");
		} else if (view.equals(btnPedidos)) {
			intent = new Intent("LISTA_PEDIDOS");
//		} else if (view.equals(btnConsultores)) {
//			intent = new Intent("LISTA_CONSULTORES");
		} else if (view.equals(btnClientes)) {
			intent = new Intent("LISTA_CLIENTES");
		} else if (view.equals(btnRepresentantes)) {
			intent = new Intent("LISTA_REPRESENTANTES");
		} else if (view.equals(btnRepresentadas)) {
			intent = new Intent("LISTA_REPRESENTADAS");
		} else if (view.equals(btnVias)) {
			intent = new Intent("LISTA_VIAS");
		} else if (view.equals(btnCanais)) {
			intent = new Intent("LISTA_CANAIS");
//		} else if (view.equals(btnCriarCopia)) {
//			onBtnCriarCopiaClick(view.getContext());
//		} else if (view.equals(btnRestaurarCopia)) {
//			onBtnRestaurarCopiaClick(view.getContext());
		}
		startActivity(intent);
	}
	
//	private void onBtnCriarCopiaClick(Context ctx) {
//		
//	}

//	private void onBtnRestaurarCopiaClick(Context ctx) {
//		//FileUtil.copiaBanco(ctx, "/sdcard/data/protocolo/backup/dbProtocolo");
//	}
    
}