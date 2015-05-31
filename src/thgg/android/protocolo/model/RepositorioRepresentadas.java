	package thgg.android.protocolo.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioRepresentadas {

	private static RepositorioRepresentadas instancia;
	
	private SQLiteDatabase db;
	
	private RepositorioRepresentadas(Context newContext) {
		db = new DBProtocolo(newContext).getWritableDatabase();
	}
	
	public static RepositorioRepresentadas getRepositorio(Context newContext) {
		if (instancia == null) {
			instancia = new RepositorioRepresentadas(newContext);
		}
		return instancia;
	}
	
	public void salvar(Representada representada) {
		if (representada.getId() == 0) {
			inserir(representada);
		} else {
			atualizar(representada);
		}
	}
	
	public void excluir(long id) {
		db.delete("representadas", "_id=?", new String[]{String.valueOf(id)});
	}
	
	public Representada procurar(long id) {
		Cursor cursor = db.query("representadas", 
				new String[] {"_id", "razao_social"}, 
				"_id=?", 
				new String[]{String.valueOf(id)}, 
				null, 
				null, 
				null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			Representada representada = new Representada(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
					cursor.getString(cursor.getColumnIndexOrThrow("razao_social")));
			return representada;
		}
		return null;
	}
	public ArrayList<Representada> listar(String colunaOrdenar) {
		ArrayList<Representada> listaRepresentadas = new ArrayList<Representada>();
		Cursor cursor = db.query("representadas", 
				new String[] {"_id", "razao_social"}, 
				null, 
				null, 
				null, 
				null, 
				colunaOrdenar);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Representada representada;
			while (!cursor.isAfterLast()) {
				representada = new Representada(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
						cursor.getString(cursor.getColumnIndexOrThrow("razao_social")));
				listaRepresentadas.add(representada);
				cursor.moveToNext();
			}
		}
		return listaRepresentadas;
	}
	
	private void inserir(Representada representada) {
		ContentValues cv = new ContentValues();
		cv.put("razao_social", representada.getRazaoSocial());
		db.insert("representadas", null, cv);
	}
	
	private void atualizar(Representada representada) {
		ContentValues cv = new ContentValues();
		cv.put("_id", representada.getId());
		cv.put("razao_social", representada.getRazaoSocial());
		db.update("representadas", cv, "_id=?", new String[]{String.valueOf(representada.getId())});		
	}
	
}
