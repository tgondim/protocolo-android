package thgg.android.protocolo.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioConsultores {

	private static RepositorioConsultores instancia;
	
	private SQLiteDatabase db;
	
	private RepositorioConsultores(Context newContext) {
		db = new DBProtocolo(newContext).getWritableDatabase();
	}
	
	public static RepositorioConsultores getRepositorio(Context newContext) {
		if (instancia == null) {
			instancia = new RepositorioConsultores(newContext);
		}
		return instancia;
	}
	
	public void salvar(Consultor consultor) {
		if (consultor.getId() == 0) {
			inserir(consultor);
		} else {
			atualizar(consultor);
		}
	}
	
	public void excluir(long id) {
		db.delete("consultores", "_id=?", new String[]{String.valueOf(id)});
	}
	
	public Consultor procurar(long id) {
		Cursor cursor = db.query("consultores", 
				new String[] {"_id", "nome" }, 
				"_id=?", 
				new String[]{String.valueOf(id)}, 
				null, 
				null, 
				null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			Consultor consultor = new Consultor(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
					cursor.getString(cursor.getColumnIndexOrThrow("nome")));
			return consultor;
		}
		return null;
	}
	public ArrayList<Consultor> listar(String colunaOrdenar) {
		ArrayList<Consultor> listaCanais = new ArrayList<Consultor>();
		Cursor cursor = db.query("consultores", 
				new String[] {"_id", "nome" }, 
				null, 
				null, 
				null, 
				null, 
				colunaOrdenar);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Consultor consultor;
			while (!cursor.isAfterLast()) {
				consultor = new Consultor(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
						cursor.getString(cursor.getColumnIndexOrThrow("nome")));
				listaCanais.add(consultor);
				cursor.moveToNext();
			}
		}
		return listaCanais;
	}
	
	private void inserir(Consultor consultor) {
		ContentValues cv = new ContentValues();
		cv.put("nome", consultor.getNome());
		db.insert("consultores", null, cv);
	}
	
	private void atualizar(Consultor consultor) {
		ContentValues cv = new ContentValues();
		cv.put("_id", consultor.getId());
		cv.put("nome", consultor.getNome());
		db.update("consultores", cv, "_id=?", new String[]{String.valueOf(consultor.getId())});		
	}
	
}
