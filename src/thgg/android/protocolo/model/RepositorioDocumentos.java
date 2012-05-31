package thgg.android.protocolo.model;

import java.util.ArrayList;
import java.util.Calendar;

import thgg.android.protocolo.util.StringUtils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioDocumentos {

	private static RepositorioDocumentos instancia;
	
	private SQLiteDatabase db;
	
	private Context ctx;
	
	private RepositorioDocumentos(Context newContext) {
		db = new DBProtocolo(newContext).getWritableDatabase();
		ctx = newContext;
	}
	
	public static RepositorioDocumentos getRepositorio(Context newContext) {
		if (instancia == null) {
			instancia = new RepositorioDocumentos(newContext);
		}
		return instancia;
	}
	
	public void salvar(Documento doc) {
		if (doc.getId() == 0) {
			inserir(doc);
		} else {
			atualizar(doc);
		}
	}
	
	public void excluir(long id) {
		db.delete("documentos", "_id=?", new String[]{String.valueOf(id)});
	}
	
	public Documento procurar(long id) {
		Cursor cursor = db.query("documentos", 
				new String[] {"_id", "descricao", "destino", "via", "data", "hora"}, 
				"_id=?", 
				new String[]{String.valueOf(id)}, 
				null, 
				null, 
				null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			Via via = RepositorioVias.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("via")));
			
			Calendar data = StringUtils.getCalendar(cursor.getString(cursor.getColumnIndexOrThrow("data")), 
					cursor.getString(cursor.getColumnIndexOrThrow("hora")));
			
			Documento doc = new Documento(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
					cursor.getString(cursor.getColumnIndexOrThrow("descricao")), 
					cursor.getString(cursor.getColumnIndexOrThrow("destino")), 
					via, 
					data);
			return doc;
		}
		return null;
	}

	public ArrayList<Documento> listar(String colunaOrdenar) {
		ArrayList<Documento> listaDocumentos = new ArrayList<Documento>();
		Cursor cursor = db.query("documentos", 
				new String[] {"_id", "descricao", "destino", "via", "data", "hora"}, 
				null, 
				null, 
				null, 
				null, 
				colunaOrdenar);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Documento documento;
			while (!cursor.isAfterLast()) {
				Via via = RepositorioVias.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("via")));

				Calendar data = StringUtils.getCalendar(cursor.getString(cursor.getColumnIndexOrThrow("data")), 
						cursor.getString(cursor.getColumnIndexOrThrow("hora")));
				
				documento = new Documento(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
						cursor.getString(cursor.getColumnIndexOrThrow("descricao")), 
						cursor.getString(cursor.getColumnIndexOrThrow("destino")), 
						via, 
						data);
				listaDocumentos.add(documento);
				cursor.moveToNext();
			}
		}
		return listaDocumentos;
	}
	
	private void inserir(Documento doc) {
		ContentValues cv = new ContentValues();
		cv.put("descricao", doc.getDescricao());
		cv.put("destino", doc.getDestino());
		cv.put("via", doc.getVia().getId());
		cv.put("data", StringUtils.getData(doc.getData()));
		cv.put("hora", StringUtils.getHora(doc.getData()));
		
		db.insert("documentos", null, cv);
	}

	private void atualizar(Documento doc) {
		ContentValues cv = new ContentValues();
		cv.put("_id", doc.getId());
		cv.put("descricao", doc.getDescricao());
		cv.put("destino", doc.getDestino());
		cv.put("via", doc.getVia().getId());
		cv.put("data", StringUtils.getData(doc.getData()));
		cv.put("hora", StringUtils.getHora(doc.getData()));
		
		db.update("documentos", cv, "_id=?", new String[]{String.valueOf(doc.getId())});		
	}
	
		
	
	
}
