	package thgg.android.protocolo.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioRepresentantes {

	private static RepositorioRepresentantes instancia;
	
	private SQLiteDatabase db;
	
	private RepositorioRepresentantes(Context newContext) {
		db = new DBProtocolo(newContext).getWritableDatabase();
	}
	
	public static RepositorioRepresentantes getRepositorio(Context newContext) {
		if (instancia == null) {
			instancia = new RepositorioRepresentantes(newContext);
		}
		return instancia;
	}
	
	public void salvar(Representante representante) {
		if (representante.getId() == 0) {
			inserir(representante);
		} else {
			atualizar(representante);
		}
	}
	
	public void excluir(long id) {
		db.delete("representantes", "_id=?", new String[]{String.valueOf(id)});
	}
	
	public Representante procurar(long id) {
		Cursor cursor = db.query("representantes", 
				new String[] {"_id", "nome"}, 
				"_id=?", 
				new String[]{String.valueOf(id)}, 
				null, 
				null, 
				null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			Representante representante = new Representante(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
					cursor.getString(cursor.getColumnIndexOrThrow("nome")));
			return representante;
		}
		return null;
	}
	public ArrayList<Representante> listar(String colunaOrdenar) {
		ArrayList<Representante> listaRepresentantes = new ArrayList<Representante>();
		Cursor cursor = db.query("representantes", 
				new String[] {"_id", "nome"}, 
				null, 
				null, 
				null, 
				null, 
				colunaOrdenar);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Representante representante;
			while (!cursor.isAfterLast()) {
				representante = new Representante(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
						cursor.getString(cursor.getColumnIndexOrThrow("nome")));
				listaRepresentantes.add(representante);
				cursor.moveToNext();
			}
		}
		return listaRepresentantes;
	}
	
	private void inserir(Representante representante) {
		ContentValues cv = new ContentValues();
		cv.put("nome", representante.getNome());
		db.insert("representantes", null, cv);
	}
	
	private void atualizar(Representante representante) {
		ContentValues cv = new ContentValues();
		cv.put("_id", representante.getId());
		cv.put("nome", representante.getNome());
		db.update("representantes", cv, "_id=?", new String[]{String.valueOf(representante.getId())});		
	}
	
}
