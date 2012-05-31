package thgg.android.protocolo.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioVias {

	private static RepositorioVias instancia;
	
	private SQLiteDatabase db;
	
	private RepositorioVias(Context newContext) {
		db = new DBProtocolo(newContext).getWritableDatabase();
	}
	
	public static RepositorioVias getRepositorio(Context newContext) {
		if (instancia == null) {
			instancia = new RepositorioVias(newContext);
		}
		return instancia;
	}
	
	public void salvar(Via via) {
		if (via.getId() == 0) {
			inserir(via);
		} else {
			atualizar(via);
		}
	}
	
	public void excluir(long id) {
		db.delete("vias", "_id=?", new String[]{String.valueOf(id)});
	}
	
	public Via procurar(long id) {
		Cursor cursor = db.query("vias", 
				new String[]{"_id", "descricao"}, 
				"_id=?", 
				new String[]{String.valueOf(id)}, 
				null, 
				null, 
				null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			Via via = new Via(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
					cursor.getString(cursor.getColumnIndexOrThrow("descricao")));
			return via;
		}
		return null;
	}
	public ArrayList<Via> listar(String colunaOrdenar) {
		ArrayList<Via> listaVias = new ArrayList<Via>();
		Cursor cursor = db.query("vias", 
				null, 
				null, 
				null, 
				null, 
				null, 
				colunaOrdenar);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Via via;
			while (!cursor.isAfterLast()) {
				via = new Via(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
						cursor.getString(cursor.getColumnIndexOrThrow("descricao")));
				listaVias.add(via);
				cursor.moveToNext();
			}
		}
		return listaVias;
	}
	
	private void inserir(Via via) {
		ContentValues cv = new ContentValues();
		cv.put("descricao", via.getDescricao());
		db.insert("vias", null, cv);
	}
	
	private void atualizar(Via via) {
		ContentValues cv = new ContentValues();
		cv.put("_id", via.getId());
		cv.put("descricao", via.getDescricao());
		db.update("vias", cv, "_id=?", new String[]{String.valueOf(via.getId())});		
	}
	
		
	
	
}
