package thgg.android.protocolo.model;

import java.util.ArrayList;
import java.util.Calendar;

import thgg.android.protocolo.util.StringUtils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioEventos {

	private static RepositorioEventos instancia;
	
	private SQLiteDatabase db;
	
	private Context ctx;
	
	private RepositorioEventos(Context newContext) {
		db = new DBProtocolo(newContext).getWritableDatabase();
		ctx = newContext;
	}
	
	public static RepositorioEventos getRepositorio(Context newContext) {
		if (instancia == null) {
			instancia = new RepositorioEventos(newContext);
		}
		return instancia;
	}
	
	public void salvar(Evento evento) {
		if (evento.getId() == 0) {
			inserir(evento);
		} else {
			atualizar(evento);
		}
	}
	
	public void excluir(long id) {
		db.delete("eventos", "_id=?", new String[]{String.valueOf(id)});
	}
	
	public Evento procurar(long id) {
		Cursor cursor = db.query("eventos", 
				new String[] {"_id", "titulo", "cliente", "consultor", "numero_pessoas", "data", "hora"}, 
				"_id=?", 
				new String[]{String.valueOf(id)}, 
				null, 
				null, 
				null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			Cliente cliente = RepositorioClientes.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("cliente")));
			Consultor consultor = RepositorioConsultores.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("consultor")));
			
			Calendar data = StringUtils.getCalendar(cursor.getString(cursor.getColumnIndexOrThrow("data")), 
					cursor.getString(cursor.getColumnIndexOrThrow("hora")));
			
			Evento evento = new Evento(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
					cursor.getString(cursor.getColumnIndexOrThrow("titulo")), 
					cliente, 
					consultor,
					cursor.getInt(cursor.getColumnIndexOrThrow("numero_pessoas")),
					data);
			return evento;
		}
		return null;
	}

	public ArrayList<Evento> listar(String colunaOrdenar) {
		ArrayList<Evento> listaEventos = new ArrayList<Evento>();
		Cursor cursor = db.query("eventos", 
				new String[] {"_id", "titulo", "cliente", "consultor", "numero_pessoas", "data", "hora"}, 
				null, 
				null, 
				null, 
				null, 
				colunaOrdenar);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Evento evento;
			while (!cursor.isAfterLast()) {
				Cliente cliente = RepositorioClientes.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("cliente")));
				Consultor consultor = RepositorioConsultores.getRepositorio(ctx).procurar(cursor.getLong(cursor.getColumnIndexOrThrow("consultor")));
				
				Calendar data = StringUtils.getCalendar(cursor.getString(cursor.getColumnIndexOrThrow("data")), 
						cursor.getString(cursor.getColumnIndexOrThrow("hora")));
				
				evento = new Evento(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), 
						cursor.getString(cursor.getColumnIndexOrThrow("titulo")), 
						cliente, 
						consultor,
						cursor.getInt(cursor.getColumnIndexOrThrow("numero_pessoas")),
						data);
				listaEventos.add(evento);
				cursor.moveToNext();
			}
		}
		return listaEventos;
	}
	
	private void inserir(Evento evento) {
		ContentValues cv = new ContentValues();
		cv.put("titulo", evento.getTitulo());
		cv.put("cliente", evento.getCliente().getId());
		cv.put("consultor", evento.getConsultor().getId());
		cv.put("numero_pessoas", evento.getNumeroPessoas());
		cv.put("data", StringUtils.getData(evento.getData()));
		cv.put("hora", StringUtils.getHora(evento.getData()));
		
		db.insert("eventos", null, cv);
	}

	private void atualizar(Evento evento) {
		ContentValues cv = new ContentValues();
		cv.put("_id", evento.getId());
		cv.put("titulo", evento.getTitulo());
		cv.put("cliente", evento.getCliente().getId());
		cv.put("consultor", evento.getConsultor().getId());
		cv.put("numero_pessoas", evento.getNumeroPessoas());
		cv.put("data", StringUtils.getData(evento.getData()));
		cv.put("hora", StringUtils.getHora(evento.getData()));
		
		db.update("eventos", cv, "_id=?", new String[]{String.valueOf(evento.getId())});		
	}
	
		
	
	
}
