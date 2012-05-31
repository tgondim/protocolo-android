package thgg.android.protocolo.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioCanais {

	private static RepositorioCanais instancia;
	
	private SQLiteDatabase db;
	
	private RepositorioCanais(Context newContext) {
		db = new DBProtocolo(newContext).getWritableDatabase();
	}
	
	public static RepositorioCanais getRepositorio(Context newContext) {
		if (instancia == null) {
			instancia = new RepositorioCanais(newContext);
		}
		return instancia;
	}
	
	public void salvar(Canal canal) {
		if (canal.getId() == 0) {
			inserir(canal);
		} else {
			atualizar(canal);
		}
	}
	
	public void excluir(long id) {
		db.delete("canais", "_id=?", new String[]{String.valueOf(id)});
	}
	
	public Canal procurar(long id) {
		Cursor cursor = db.query("canais", 
				new String[] {"_id", "descricao"}, 
				"_id=?", 
				new String[]{String.valueOf(id)}, 
				null, 
				null, 
				null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			Canal canal = new Canal(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
					cursor.getString(cursor.getColumnIndexOrThrow("descricao")));
			return canal;
		}
		return null;
	}
	public ArrayList<Canal> listar(String colunaOrdenar) {
		ArrayList<Canal> listaCanais = new ArrayList<Canal>();
		Cursor cursor = db.query("canais", 
				new String[] {"_id", "descricao"}, 
				null, 
				null, 
				null, 
				null, 
				colunaOrdenar);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Canal canal;
			while (!cursor.isAfterLast()) {
				canal = new Canal(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
						cursor.getString(cursor.getColumnIndexOrThrow("descricao")));
				listaCanais.add(canal);
				cursor.moveToNext();
			}
		}
		return listaCanais;
	}
	
	private void inserir(Canal canal) {
		ContentValues cv = new ContentValues();
		cv.put("descricao", canal.getDescricao());
		db.insert("canais", null, cv);
	}
	
	private void atualizar(Canal canal) {
		ContentValues cv = new ContentValues();
		cv.put("_id", canal.getId());
		cv.put("descricao", canal.getDescricao());
		db.update("canais", cv, "_id=?", new String[]{String.valueOf(canal.getId())});		
	}
	
}
