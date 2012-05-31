package thgg.android.protocolo.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioClientes {

	private static RepositorioClientes instancia;
	
	private SQLiteDatabase db;
	
	private RepositorioClientes(Context newContext) {
		db = new DBProtocolo(newContext).getWritableDatabase();
	}
	
	public static RepositorioClientes getRepositorio(Context newContext) {
		if (instancia == null) {
			instancia = new RepositorioClientes(newContext);
		}
		return instancia;
	}
	
	public void salvar(Cliente cliente) {
		if (cliente.getId() == 0) {
			inserir(cliente);
		} else {
			atualizar(cliente);
		}
	}
	
	public void excluir(long id) {
		db.delete("clientes", "_id=?", new String[]{String.valueOf(id)});
	}
	
	public Cliente procurar(long id) {
		Cursor cursor = db.query("clientes", 
				new String[] {"_id", "nome" }, 
				"_id=?", 
				new String[]{String.valueOf(id)}, 
				null, 
				null, 
				null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			Cliente cliente = new Cliente(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
					cursor.getString(cursor.getColumnIndexOrThrow("nome")));
			return cliente;
		}
		return null;
	}
	public ArrayList<Cliente> listar(String colunaOrdenar) {
		ArrayList<Cliente> listaCanais = new ArrayList<Cliente>();
		Cursor cursor = db.query("clientes", 
				new String[] {"_id", "nome" }, 
				null, 
				null, 
				null, 
				null, 
				colunaOrdenar);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Cliente cliente;
			while (!cursor.isAfterLast()) {
				cliente = new Cliente(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
						cursor.getString(cursor.getColumnIndexOrThrow("nome")));
				listaCanais.add(cliente);
				cursor.moveToNext();
			}
		}
		return listaCanais;
	}
	
	private void inserir(Cliente cliente) {
		ContentValues cv = new ContentValues();
		cv.put("nome", cliente.getNome());
		db.insert("clientes", null, cv);
	}
	
	private void atualizar(Cliente cliente) {
		ContentValues cv = new ContentValues();
		cv.put("_id", cliente.getId());
		cv.put("nome", cliente.getNome());
		db.update("clientes", cv, "_id=?", new String[]{String.valueOf(cliente.getId())});		
	}
	
}
