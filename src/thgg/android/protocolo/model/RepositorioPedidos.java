package thgg.android.protocolo.model;

import java.util.ArrayList;
import java.util.Calendar;

import thgg.android.protocolo.util.StringUtils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioPedidos {

	private static RepositorioPedidos instancia;
	
	private SQLiteDatabase db;
	
	private Context ctx;
	
	private RepositorioPedidos(Context newContext) {
		db = new DBProtocolo(newContext).getWritableDatabase();
		ctx = newContext;
	}
	
	public static RepositorioPedidos getRepositorio(Context newContext) {
		if (instancia == null) {
			instancia = new RepositorioPedidos(newContext);
		}
		return instancia;
	}
	
	public void salvar(Pedido verba) {
		if (verba.getId() == 0) {
			inserir(verba);
		} else {
			atualizar(verba);
		}
	}
	
	public void excluir(long id) {
		db.delete("pedidos", "_id=?", new String[]{String.valueOf(id)});
	}
	
	public Pedido procurar(long id) {
		Cursor cursor = db.query("pedidos", 
				new String[] {"_id", "numero", "representada", "cliente", "representante", "valor_total", "data", "hora"},
				"_id=?", 
				new String[]{String.valueOf(id)}, 
				null, 
				null, 
				null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			Representada representada = RepositorioRepresentadas.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("representada")));
			Cliente cliente = RepositorioClientes.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("cliente")));
			Representante representante = RepositorioRepresentantes.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("representante")));
			
			Calendar dataEmissao = StringUtils.getCalendar(cursor.getString(cursor.getColumnIndexOrThrow("data")), 
					cursor.getString(cursor.getColumnIndexOrThrow("hora")));
			
			Pedido pedido = new Pedido(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
					cursor.getString(cursor.getColumnIndexOrThrow("numero")),
					representada, 
					cliente,
					representante,
					cursor.getDouble(cursor.getColumnIndexOrThrow("valor_total")),
					dataEmissao);
			return pedido;
		}
		return null;
	}
	
	public ArrayList<Pedido> listar(String colunaOrdenar) {
		ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
		Cursor cursor = db.query("pedidos", 
				new String[] {"_id", "numero", "representada", "cliente", "representante", "valor_total", "data", "hora"}, 
				null, 
				null, 
				null, 
				null, 
				colunaOrdenar);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Pedido pedido;
			while (!cursor.isAfterLast()) {
				Representada representada = RepositorioRepresentadas.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("representada")));
				Cliente cliente = RepositorioClientes.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("cliente")));
				Representante representante = RepositorioRepresentantes.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("representante")));
				
				Calendar dataEmissao = StringUtils.getCalendar(cursor.getString(cursor.getColumnIndexOrThrow("data")), 
						cursor.getString(cursor.getColumnIndexOrThrow("hora")));
				
				pedido = new Pedido(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
						cursor.getString(cursor.getColumnIndexOrThrow("numero")),
						representada, 
						cliente,
						representante,
						cursor.getDouble(cursor.getColumnIndexOrThrow("valor_total")),
						dataEmissao);				
				listaPedidos.add(pedido);
				cursor.moveToNext();
			}
		}
		return listaPedidos;
	}
	
	private void inserir(Pedido pedido) {
		ContentValues cv = new ContentValues();
		cv.put("numero", pedido.getNumero());
		cv.put("representada", pedido.getRepresentada().getId());
		cv.put("cliente", pedido.getCliente().getId());
		cv.put("representante", pedido.getRepresentante().getId());
		cv.put("valor_total", pedido.getValorTotal());
		cv.put("data", StringUtils.getData(pedido.getDataEmissao()));
		cv.put("hora", StringUtils.getHora(pedido.getDataEmissao()));
		
		db.insert("pedidos", null, cv);
	}
	
	private void atualizar(Pedido pedido) {
		ContentValues cv = new ContentValues();
		cv.put("_id", pedido.getId());
		cv.put("numero", pedido.getNumero());
		cv.put("representada", pedido.getRepresentada().getId());
		cv.put("cliente", pedido.getCliente().getId());
		cv.put("representante", pedido.getRepresentante().getId());
		cv.put("valor_total", pedido.getValorTotal());
		cv.put("data", StringUtils.getData(pedido.getDataEmissao()));
		cv.put("hora", StringUtils.getHora(pedido.getDataEmissao()));
		
		db.update("pedidos", cv, "_id=?", new String[]{String.valueOf(pedido.getId())});		
	}
	
		
	
	
}
